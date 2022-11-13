package utils;

import java.net.http.HttpResponse;

public interface ApiService {
    void sendPostRequests(String title, String description, String url, String imageUrl,
                          int price) throws Exception;

    HttpResponse<String> getResponse();
}
