/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Main entry point to the Syniverse API.
 *
 * @author jaase
 */
public class Scg {

    /**
     * Constructor
     */
    public Scg() {
    }

    /**
     * Prepare a connection to a SCG API server
     *
     * @param url url Url to the API server. Typically
     *          "https://api.syniverse.com".
     * @param auth Authentocation data anse a secret token you
     *          obtain from the Syniverse developer portal.
     * @return Session instance
     * @throws Exception if errors are detected.
     */
    public Session connect(final String url, AuthInfo auth) throws Exception {
        Session session = new Session(url, auth);
        return session;
    }

    /**
     * Prepare a connection to a SCG API server
     *
     * @param url url Url to the API server. Typically
     *          "https://api.syniverse.com".
     * @param auth Authentocation data anse a secret token you
     *          obtain from the Syniverse developer portal.
     * @param logLevel Log Level for the communication layer. Useful
     *          for trouble-shooting during development.
     * @return Session instance
     * @throws Exception if errors are detected.
     */
    public Session connect(final String url, AuthInfo auth,
            HttpLoggingInterceptor.Level logLevel) throws Exception {
        Session session = new Session(url, auth, logLevel);
        return session;
    }
}
