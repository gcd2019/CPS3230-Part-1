package test.cps3230.spies;

import com.google.gson.Gson;
import jsonConstructor.AlertRequest;
import utils.ApiService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServiceSpy implements ApiService {

    protected HttpResponse <String> response;

    public void sendPostRequests(String title, String description, String url, String imageUrl,
                                 int price) throws Exception {
        AlertRequest alertRequest = new AlertRequest();

        alertRequest.setAlertType(6);
        alertRequest.setHeading(title);
        alertRequest.setDescription(description);
        alertRequest.setUrl(url);
        alertRequest.setImageUrl(imageUrl);
        alertRequest.setPostedBy("e7ee93d2-cf55-45da-a41e-6581361e3f20");
        alertRequest.setPriceInCents(price);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(alertRequest);

        System.out.println(jsonRequest);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.marketalertum.com/Alert"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public HttpResponse<String> getResponse() {
        return response;
    }
}
