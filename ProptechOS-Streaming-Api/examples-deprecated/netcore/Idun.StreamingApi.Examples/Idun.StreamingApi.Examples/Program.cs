using Microsoft.Azure.EventHubs;
using Microsoft.Azure.EventHubs.Processor;
using Microsoft.Extensions.Configuration;
using System;
using System.IO;
using System.Threading.Tasks;

namespace IdunStreamClientSample
{
    class Program
    {
        static void Main(string[] args)
        {
            StartAsync().GetAwaiter().GetResult();
        }

        private static async Task StartAsync()
        {
            var builder = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json");
            IConfiguration configuration = builder.Build();

            var ehConnectionString = configuration["EhConnectionString"];
            var eventHubName = configuration["EhEntityPath"];
            var storageContainerName = configuration["StorageContainerName"];
            var storageAccountName = configuration["StorageAccountName"];
            var storageAccountKey = configuration["StorageAccountKey"];
            var storageConnectionString = string.Format("DefaultEndpointsProtocol=https;AccountName={0};AccountKey={1}", storageAccountName, storageAccountKey);

            var eventProcessorHost = new EventProcessorHost(
                Guid.NewGuid().ToString(),
                eventHubName,
                PartitionReceiver.DefaultConsumerGroupName,
                ehConnectionString,
                storageConnectionString,
                storageContainerName               
                );

            var eventProcessorOptions = new EventProcessorOptions
            {
                InitialOffsetProvider = (partitionId) => EventPosition.FromEnqueuedTime(DateTime.UtcNow)
            };
            await eventProcessorHost.RegisterEventProcessorAsync<SimpleEventProcessor>(eventProcessorOptions);

            Console.WriteLine("Receiving. Press Enter to stop worker.");
            Console.ReadLine();
            await eventProcessorHost.UnregisterEventProcessorAsync();
        }
    }
}
