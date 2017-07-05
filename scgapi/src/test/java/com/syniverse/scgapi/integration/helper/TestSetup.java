/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.helper;

import com.syniverse.scgapi.AuthInfo;
import com.syniverse.scgapi.Scg;
import com.syniverse.scgapi.Session;
import java.io.File;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author jaase
 */
public class TestSetup {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager
            .getLogger(TestSetup.class);

    private String authConfigPath = System.getProperty("authFile");
    private String testConfigPath = System.getProperty("testConfig");
    private AuthInfo auth = null;
    private Session session = null;
    private TestConfig testConfig= null;

    public TestSetup() throws Exception {

        if (!isTestable()) {
            return;
        }

        String cwd = new java.io.File( "." ).getCanonicalPath();
        LOGGER.info("Starting tests. Current dir is {}, auth file is {} and test config file is {}",
                cwd, authConfigPath, testConfigPath);

        auth = new AuthInfo(new File(authConfigPath));
        testConfig = TestConfig.load(new File(testConfigPath));

        Scg scg = new Scg();
        session = scg.connect(testConfig.url, auth,
                testConfig.okHttplogLevel);
    }

    public Session getSession() {
        return session;
    }

    public TestConfig getTestConfig() {
        return testConfig;
    }

    final public boolean isTestable() {
        return authConfigPath != null && testConfigPath!= null;
    }
}