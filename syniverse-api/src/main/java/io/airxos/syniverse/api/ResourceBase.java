/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Implementation detail
 * @author jaase
 */
public class ResourceBase {

    protected Session session_;

    public interface ExecuteFn <T>  {
        T execute() throws ScgException;
    }

    void ResetApi() {
        throw new RuntimeException("ResetApi not implemented!");
    }

    class SaopAccessToken {
        public String accessToken;
        public Long validityTime = 0L;
    };

    public interface AuthService {
        @GET("saop-rest-data/v1/apptoken-refresh")
        Call<SaopAccessToken> refresh(
                @Query("consumerkey") String key,
                @Query("consumersecret") String secret,
                @Query("oldtoken") String token);
    }

    synchronized void ResetAuthToken() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.syniverse.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        AuthService authSvc = retrofit.create(AuthService.class);

        try {
            Response<SaopAccessToken> resp = authSvc.refresh(
                    session_.getAuth().getKey(),
                    session_.getAuth().getSecret(),
                    session_.getAuth().getToken()).execute();
            if (!resp.isSuccessful()) {
                throw new RuntimeException("Failed to get a new auth token");
            }
            SaopAccessToken token = resp.body();
            assert token != null;
            session_.getAuth().setToken(token.accessToken);
        } catch (IOException ex) {
            Logger.getLogger(ResourceBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized void DoResetApi() {
        ResetApi();
    }

    public <T> T ExecuteWithRetry(ExecuteFn<T> fn, int recursion) throws ScgException {
        try {
            return fn.execute();
        } catch (Error.AuthError ex) {
            if (recursion >= session_.getAuth().getRetries()) {
                throw ex;
            }
            // Try to get a new token and reset the stage
            ResetAuthToken();
            DoResetApi();
            return ExecuteWithRetry(fn, recursion + 1);
        }
    }

}
