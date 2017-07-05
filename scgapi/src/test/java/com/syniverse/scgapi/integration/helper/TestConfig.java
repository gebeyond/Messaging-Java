/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * @author jaase
 */
public class TestConfig {
    public String url;
    public Long mdnRangeStart;
    public String senderIdCountry = "USA";
    public HttpLoggingInterceptor.Level okHttplogLevel = HttpLoggingInterceptor.Level.BASIC;

    // If callSenderId is null, calls will be excluded from the testcases.
    public String callSenderId;
    public String callLeftLeg;
    public String callRightLeg;
    public String callPlayBackUrl;

    static public TestConfig load(File testFile) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        TypeReference<TestConfig> typeRef
                = new TypeReference<TestConfig>() {};

        return mapper.readValue(testFile, typeRef);
    }
}
