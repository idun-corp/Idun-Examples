using System;
using System.Threading.Tasks;
using Azure.Storage.Blobs;
using Azure.Messaging.EventHubs;
using Microsoft.Extensions.Configuration;
using System.Text.Json;
using System.Text.Json.Serialization;
using Microsoft.Extensions.Logging;

namespace Idun.StreamingApi.Examples.DedicatedProcessor
{
    public class Program
    {
        private static readonly JsonSerializerOptions JsonSerializerOptions
            = new JsonSerializerOptions { PropertyNamingPolicy = JsonNamingPolicy.CamelCase, NumberHandling = JsonNumberHandling.AllowReadingFromString };

        private static async Task Main(string[] args)
        {
            var configuration = new ConfigurationBuilder()
                .AddJsonFile("appsettings.json", optional: false)
                .Build();

            var logger = LoggerFactory.Create(logger => logger.AddConsole()).CreateLogger<Program>();

            var storageClient = new BlobContainerClient(
                configuration.GetValue<string>("BlobStorageConnectionString"),
                configuration.GetValue<string>("BlobContainerName"));

            var processor = new EventProcessorClient(
                storageClient,
                configuration.GetValue<string>("EventHubConsumerGroup"),
                configuration.GetValue<string>("EventHubConnectionString"),
                configuration.GetValue<string>("EventHubName"));

            processor.ProcessEventAsync += async eventArgs =>
            {
                try
                {
                    var sensorObservation = eventArgs.Data.EventBody.ToObjectFromJson<SensorObservation>(JsonSerializerOptions);

                    logger.LogInformation($"Processed a sensor observation:\n" +
                        $"\tValue:\t\t{sensorObservation.Observation.Value},\n" +
                        $"\tQuantity Kind:\t{sensorObservation.Observation.QuantityKind},\n" +
                        $"\tSensor Id:\t{sensorObservation.Observation.SensorId},\n");

                    logger.LogInformation("Press any key to read next one");

                    Console.ReadKey(true);

                    await Task.Yield();
                }
                catch (Exception e)
                {
                    logger.LogError(e, $"Failed to processed a message with id: {eventArgs.Data.MessageId}");
                }

                await eventArgs.UpdateCheckpointAsync(eventArgs.CancellationToken);
            };

            processor.ProcessErrorAsync += async eventArgs =>
            {
                logger.LogError(eventArgs.Exception, $"Failed to processed a message with on partition: {eventArgs.PartitionId}");

                await Task.Yield();
            };

            await processor.StartProcessingAsync();

            logger.LogInformation($"You are listening to EventHub stream. Press Escape to stop listener.");

            while (Console.ReadKey(true) is ConsoleKeyInfo key)
            {
                if (key.Key == ConsoleKey.Escape)
                {
                    break;
                }
            }

            await processor.StopProcessingAsync();
        }
    }
}
