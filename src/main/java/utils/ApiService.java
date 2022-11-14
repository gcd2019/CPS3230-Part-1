package utils;

import com.google.gson.Gson;
import jsonConstructor.AlertRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    protected HttpResponse <String> response;

    public boolean sendPostRequests(int alertType, String title, String description, String url, String imageUrl,
                                 int price) throws Exception {
        AlertRequest alertRequest = new AlertRequest();

        alertRequest.setAlertType(alertType);
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

        if (response == null)
            return false;

        System.out.println(response.body());

        return true;
    }

    public boolean sendDeleteRequests() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.marketalertum.com/Alert?userId=e7ee93d2-cf55-45da-a41e-6581361e3f20"))
                .DELETE()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response == null)
            return false;

        System.out.println(response.body());

        return true;
    }
    public HttpResponse<String> getResponse() {
        return response;
    }
}
