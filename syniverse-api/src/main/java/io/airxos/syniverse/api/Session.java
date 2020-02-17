/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import io.gsonfire.GsonFireBuilder;
import io.gsonfire.PostProcessor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Session handle for requests to the SCG API server.
 *
 * End users of this SDK will normally not call methods directly on the
 * session instance. In stead, you will pass the session instance
 * to constructors of resource objects.
 *
 * @author jaase
 */
public class Session {
    private final String baseUrl;
    private final String apiUrl;
    private AuthInfo auth;
    private final Interceptor logInterceptor;

    Session(final String baseUrl, AuthInfo auth) {
        this(baseUrl, auth, HttpLoggingInterceptor.Level.NONE);
    }

    Session(final String baseUrl, AuthInfo auth, HttpLoggingInterceptor.Level logLevel) {
        this.baseUrl = baseUrl;
        this.apiUrl = baseUrl + "/scg-external-api/api/v1/";
        this.auth = auth;
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);
        logInterceptor = interceptor;
    }

    final String getHostname() {
        String hostname;
        try {
            URL url = new URL(baseUrl);
            hostname = url.getHost();
            if (url.getPort() > 0) {
                if (url.getPort() != 80 && url.getPort() != 443) {
                    hostname = hostname + ":" + url.getPort();
                }
            }
        } catch (MalformedURLException ex) {
            hostname = "unknown";
        }

        return hostname;
    }

    public OkHttpClient getOkClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Builder builder= chain.request().newBuilder();

                    if (auth.getCompanyId() != null) {
                        builder.header("int-companyId", auth.getCompanyId().toString());
                    }

                    if (auth.getAppId() != null) {
                        builder.header("int-appId", auth.getAppId().toString());
                    }

                    if (auth.getQuotaPlan()!= null) {
                        builder.header("int-quota-plan", auth.getQuotaPlan());
                    }

                    if (auth.getToken() != null) {
                        builder.header("Authorization", "Bearer " + auth.getToken());
                    }

                    if (auth.getFakeTransactionId() != null) {
                        builder.header("int-txnId", auth.getFakeTransactionId());
                    }

                    Request request = builder
                            .header("Host", getHostname())
                            .header("Accept", "*/*")
                            .build();

                    return chain.proceed(request);
                })
                .addInterceptor(logInterceptor)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .build();
    }

    String getBaseUrl() {
        return baseUrl;
    }

    AuthInfo getAuth() {
        return auth;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public static <T> T Execute(Call<T> service) throws ScgException{

        Response<T> resp;
        try {
            resp = service.execute();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        if (resp.isSuccessful())
            return resp.body();

        if (resp.code() == 401) {
            // Auth error
            throw new Error.AuthError();
        }
        try {
            assert resp.errorBody() != null;
            final String reason = resp.errorBody().string();
            throw new Error.ServerFailure(resp.code(), reason);
        } catch (IOException ex) {
            throw new Error.ServerFailure(resp.code(), resp.errorBody().toString());
        }
    }

    interface CratingApiCallback {
        void onCreatingApi(GsonFireBuilder builder);
    }

    public <apiT, dataT> apiT GetApi(Class<apiT> apiClass,
            Class<dataT> dataClass, Object resource) {
        PostProcessor<dataT> pp = new BaseData.Deserialize<>(resource, dataClass);
        return GetApi(apiClass, dataClass, pp, null);
    }

    public <apiT, dataT> apiT GetApi(Class<apiT> apiClass,
            Class<dataT> dataClass, Object resource,
            CratingApiCallback fireHookCallback) {
        PostProcessor<dataT> pp = new BaseData.Deserialize<>(resource, dataClass);
        return GetApi(apiClass, dataClass, pp, fireHookCallback);
    }

    private <apiT, dataT> apiT GetApi(Class<apiT> apiClass,
            Class<dataT>  dataClass, PostProcessor<dataT> postProcessor,
            CratingApiCallback fireHookCallback) {
        GsonFireBuilder builder = new GsonFireBuilder()
                .registerPostProcessor(dataClass, postProcessor)
                .enableHooks(dataClass);

        if (fireHookCallback != null) {
            fireHookCallback.onCreatingApi(builder);
        }

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(getOkClient())
            .addConverterFactory(GsonConverterFactory.create(builder.createGsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create()))
            .build();

        return retrofit.create(apiClass);
    }
}
