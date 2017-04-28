/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * Authentication data while using the API.
 *
 * You should have one instance of this class for each API
 * Application registration you use.
 *
 * The access-token may be updated with a new valid token if it
 * expires.
 *
 * All the methods for this class are thread-safe.
 *
 * @author jaase
 */
public class AuthInfo {

    static final int DEFAULT_RETRIES = 3;

    public static class ConfigData {
        public String key;
        public String secret;
        public String token;
        public Integer retries;

        // For internal automated testing
        public Long appid;
        public Integer companyid;
        public String quotaplan;
        public String transactionid;
    }

    private final String key;
    private final String secret;
    private String token;
    private int retries = DEFAULT_RETRIES;

    // For internal automated testing
    private final Long appid;
    private final Integer companyid;
    private final String quotaplan;
    private final String transactionid;

    /**
     *
     * @param key from SAOP store
     * @param secret from SAOP store
     * @param token The current or last obtained Auth Token
     *          from the SAOP store. The token may be re-freshed
     *          behind the scenes if the numRetries value is greater than 0
     * @param retries Number of times to re-try refreshing the
     *          auth token before giving up. This value is used by
     *          behind the scenes by all the methods that communicate
     *          with the REST server. Set it to 0 to disable automatic
     *          re-fresh of the auth token.
     */
    public AuthInfo(final String key,
            final String secret,
            final String token,
            int retries) {
        this.key = key;
        this.secret = secret;
        this.token = token;
        this.retries = retries;
        this.appid = null;
        this.companyid = null;
        this.quotaplan = null;
        this.transactionid = null;
    }

    /**
     *
     * @param key from SAOP store
     * @param secret from SAOP store
     * @param token The current or last obtained Auth Token
     *          from the SAOP store. The token may be re-freshed
     *          behind the scenes.
     *
     * The default value for retries is 3
     */
    public AuthInfo(final String key,
            final String secret,
            final String token) {
        this(key, secret, token, DEFAULT_RETRIES);
    }

    /**
     *
     * @param configFile Json file containing configuration.
     *
     * @exception IOException on error.
     */
    public AuthInfo(final File configFile) throws IOException {
        ConfigData config = loadConfig(configFile);

        this.key = config.key;
        this.secret = config.secret;
        this.token = config.token;
        if (config.retries != null) {
            this.retries = config.retries;
        }
        this.appid = config.appid;
        this.companyid = config.companyid;
        this.quotaplan = config.quotaplan;
        this.transactionid = config.transactionid;
    }

    /**
     *
     * @return the consumer key.
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @return the consumer secret.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Internal method for CI
     * @exclude
     */
    public Long getAppId() {
        return appid;
    }

    /**
     * Internal method for CI
     * @exclude
     */
    public Integer getCompanyId() {
        return companyid;
    }

    /**
     * Internal method for CI
     * @exclude
     */
    public String getQuotaPlan() {
        return quotaplan;
    }

    /**
     * Internal method for CI
     * @exclude
     */
    public String getFakeTransactionId() {
        return transactionid;
    }


    /**
     *
     * The SDK may refresh the token if it times out. The
     * token returned can therefore be a previous seen token,
     * or a new token.
     *
     * @return the current access token
     */
    public synchronized String getToken() {
        return token;
    }

    /**
     *
     * @param token New token to use
     */
    public synchronized void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return The number of retries the SDK will do in order
     *      to renew the token in case of an authentication
     *      failure. If 0, no retries will be attempted.
     */
    public int getRetries() {
        return retries;
    }

    private ConfigData loadConfig(File file) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        TypeReference<ConfigData> typeRef
                = new TypeReference<ConfigData>() {};

        return mapper.readValue(file, typeRef);
    }
}
