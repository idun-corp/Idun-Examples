using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace B2cClientCredentialFlow
{
    class Program
    {
        static async Task Main(string[] args)
        {
            var clientId = "<CLIENT_ID>";
            var clientSecret = "<CLIENT_SECRET>";
            var proptechOsApiUrl = "<PROPTECHOS_API_URL>";
            var proptechOsApiIdUri = "<PROPTECHOS_API_ID_URI>";

            using (var httpClient = new HttpClient())
            {
                var loginRequest = new HttpRequestMessage(HttpMethod.Post, $"https://login.microsoftonline.com/proptechos.onmicrosoft.com/oauth2/v2.0/token")
                {
                    Content = new FormUrlEncodedContent(new Dictionary<string, string>
                    {
                        { "client_id", clientId },
                        { "client_secret", clientSecret },
                        { "scope", $"{proptechOsApiIdUri}/.default" },
                        { "grant_type", "client_credentials" },
                    }.AsEnumerable()),
                };
                var loginResponse = await httpClient.SendAsync(loginRequest);
                var loginResponseJson = await HandleResponse(loginResponse);
                PrintLoginResponse(loginResponseJson);

                var proptechOsRequest = new HttpRequestMessage(HttpMethod.Get, $"{proptechOsApiUrl}/realestate")
                {
                    Headers =
                    {
                        Authorization = new AuthenticationHeaderValue(
                            loginResponseJson.Value<string>("token_type"),
                            loginResponseJson.Value<string>("access_token")),
                    }
                };
                var proptechOsResponse = await httpClient.SendAsync(proptechOsRequest);
                var proptechOsResponseJson = await HandleResponse(proptechOsResponse);
                PrintProptechOsResponse(proptechOsResponseJson);
            }
        }

        static void PrintLoginResponse(JObject loginResponseJson)
        {
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine("Login response:");
            Console.WriteLine(loginResponseJson);
            Console.WriteLine("\n");
        }

        static void PrintProptechOsResponse(JObject proptechOsResponseJson)
        {
            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.WriteLine("ProptechOS response:");
            Console.WriteLine(proptechOsResponseJson);
        }

        static async Task<JObject> HandleResponse(HttpResponseMessage response)
        {
            var responseJson = await Deserialize(response.Content);

            if (!response.IsSuccessStatusCode)
            {
                Console.ForegroundColor = ConsoleColor.Red;

                throw new Exception(responseJson.ToString());
            }

            return responseJson;
        }

        static async Task<JObject> Deserialize(HttpContent content)
        {
            var stream = await content.ReadAsStreamAsync();

            if (stream == null || !stream.CanRead)
            {
                await Task.CompletedTask;
            }

            using (var streamReader = new StreamReader(stream))
            using (var jsonTextReader = new JsonTextReader(streamReader))
            {
                return (JObject) JsonSerializer.Create().Deserialize(jsonTextReader, typeof(JObject));
            }
        }
    }
}
