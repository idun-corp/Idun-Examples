using System;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;
using Azure.Messaging.EventHubs;
using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;

namespace Idun.StreamingApi.Examples.FunctionApp
{
    public class Function1
    {
        private static readonly JsonSerializerOptions JsonSerializerOptions
            = new JsonSerializerOptions { PropertyNamingPolicy = JsonNamingPolicy.CamelCase, NumberHandling = JsonNumberHandling.AllowReadingFromString };

        [FunctionName("Function1")]
        public async Task Run(
            [EventHubTrigger("", Connection = "EventHubConnectionString", ConsumerGroup = "%EventHubConsumerGroup%")] EventData[] events,
            ILogger log)
        {
            foreach (EventData eventData in events)
            {
                try
                {
                    var json = eventData.EventBody.ToObjectFromJson<JsonElement>(JsonSerializerOptions);
                    var sensorObservation = eventData.EventBody.ToObjectFromJson<SensorObservation>(JsonSerializerOptions);

                    log.LogInformation($"Processed a sensor observation:\n" +
                        $"\tValue:\t\t{sensorObservation.Observation.Value},\n" +
                        $"\tQuantity Kind:\t{sensorObservation.Observation.QuantityKind},\n" +
                        $"\tSensor Id:\t{sensorObservation.Observation.SensorId},\n");

                    await Task.Yield();
                }
                catch (Exception e)
                {
                    log.LogError(e, $"Failed to processed a message with id: {eventData.MessageId}");
                }
            }
        }
    }
}
