import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestService
{
     static int sendGetTemp() throws Exception {

         String url = "https://arduinotemp.herokuapp.com/lasttemp";

         HttpURLConnection httpClient =
                 (HttpURLConnection) new URL(url).openConnection();

         httpClient.setRequestMethod("GET");

         //add request header
         httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

         int responseCode = httpClient.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);

         String temp;
         try (BufferedReader in = new BufferedReader(
                 new InputStreamReader(httpClient.getInputStream()))) {

             StringBuilder response = new StringBuilder();
             String line;

             while ((line = in.readLine()) != null) {
                 response.append(line);
             }
             Document doc = Jsoup.parse(String.valueOf(response));
             Elements elements = doc.select("body");
//             System.out.println("Ultima temperatura inregistrata: " + elements.text());
             temp = elements.text();
         }

         return  Integer.valueOf(temp);
     }

    // !!!! sendGetHum() Functia de get hum

    static void sendPostState() {



        //        var values = new HashMap<String, String>() {{
//            put("state", String.valueOf(ActionAgent.getAState()));
//        }};
//
//        var objectMapper = new ObjectMapper();
//        String requestBody = objectMapper
//                .writeValueAsString(values);
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://httpbin.org/post"))
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        HttpResponse<String> response = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());


    }

}
