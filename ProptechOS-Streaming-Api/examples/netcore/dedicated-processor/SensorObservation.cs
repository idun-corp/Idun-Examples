using System;
using System.Text.Json.Serialization;

namespace Idun.StreamingApi.Examples.DedicatedProcessor
{
    public class SensorObservation
    {
        public Observation Observation { get; set; }

        [JsonPropertyName("sensor")]
        public Uri SensorUri { get; set; }
    }

    public class Observation
    {
        public Guid SensorId { get; set; }

        public double? Value { get; set; }

        public string QuantityKind { get; set; }

        public DateTime ObservationTime { get; set; }
    }
}
