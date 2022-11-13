package com.kokn.paperround.util;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {


    public static String get(String requestUrl, Map<String, String> header) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder()
                .url(requestUrl);

        if (header != null){
            builder.headers(Headers.of(header));
        }

        builder.get();
        Request request = builder.build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public static Response getResponse(String requestUrl, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder()
                .url(requestUrl);

        if (header != null)
            builder.headers(Headers.of(header));

        builder.get();
        Request request = builder.build();
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}