package com.example.newsapp.api;

import com.example.newsapp.models.ApiResponse;
import com.google.gson.Gson;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiService {

    private static final String TOKEN = "dxeypwykqp7tnxh:mUWnI2TxlZ3EbhD12GOG";
    private static final String BASE_URL = "https://api.majidapi.ir/news";
    private OkHttpClient client;
    private Gson gson;

    public ApiService() {
        this.client = getUnsafeOkHttpClient();
        this.gson = new Gson();
    }

    public interface NewsCallback {
        void onSuccess(ApiResponse response);
        void onFailure(String error);
    }

    public void getNews(int page, NewsCallback callback) {
        String url = BASE_URL + "?action=list&page=" + page + "&token=" + TOKEN;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("خطا در اتصال: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    ApiResponse apiResponse = gson.fromJson(json, ApiResponse.class);
                    callback.onSuccess(apiResponse);
                } else {
                    callback.onFailure("خطای سرور: " + response.code());
                }
                response.close();
            }
        });
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager)trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}