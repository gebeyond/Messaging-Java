/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.Channel;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class ChannelFT {

    final TestSetup setup;

    public ChannelFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }

    @Test(expected = com.syniverse.scgapi.Error.ServerFailure.class)
    public void testsCRUD() throws Exception {
        Channel.Resource res = new Channel.Resource(setup.getSession());

        Channel ch = new Channel();
        ch.setName("CRUD channel");
        ch.setDescription("Test");

        String new_id = res.create(ch);
        Channel channelReceived = res.get(new_id);

        assertEquals(new_id, channelReceived.getId());
        assertEquals(ch.getName(), channelReceived.getName());

        channelReceived.setName("Testing");
        channelReceived.update();

        Channel channelReceived2 = res.get(new_id);

        assertEquals("Testing", channelReceived2.getName());
        assertFalse(res.list(null).getAllAsList().isEmpty());

        channelReceived2.delete();

        // Throws com.syniverse.scgapi.Error.ServerFailure
        res.get(new_id);
    }
}
