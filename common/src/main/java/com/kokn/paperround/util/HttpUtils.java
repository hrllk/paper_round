package com.kokn.paperround.util;

import okhttp3.*;

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
}