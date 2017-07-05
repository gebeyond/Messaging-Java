/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.Contact;
import com.syniverse.scgapi.ContactGroup;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class ContactGroupFT {

    private final TestSetup setup;

    public ContactGroupFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }


    @Test(expected = com.syniverse.scgapi.Error.ServerFailure.class)
    public void testsCRUD() throws Exception {
        String uuid = UUID.randomUUID().toString();

        ContactGroup cg = new ContactGroup();
        ContactGroup.Resource res = new ContactGroup.Resource(setup.getSession());

        cg.setDescription("CRUD");
        cg.setExternalId(uuid);
        cg.setName("Test");

        String new_id = res.create(cg);
        ContactGroup cg_received = res.get(new_id);

        assertEquals(uuid, cg_received.getExternalId());
        assertEquals("Test", cg_received.getName());

        assertEquals("CRUD", cg_received.getDescription());

        cg_received.setDescription("DURC");
        cg_received.update();

        ContactGroup cg_received2 = res.get(new_id);

        assertEquals("DURC", cg_received2.getDescription());

        cg_received.delete();

        // Throws com.syniverse.scgapi.Error.ServerFailure
        res.get(new_id);
    }

    @Test
    public void testsAndDeleteContacts() throws Exception {
        Long mdn = setup.getTestConfig().mdnRangeStart;
        String uuid = UUID.randomUUID().toString();
        String uuidC1 = UUID.randomUUID().toString();
        String uuidC2 = UUID.randomUUID().toString();

        Contact.Resource cres = new Contact.Resource(setup.getSession());
        Contact c = new Contact();
        c.setPrimaryMdn(mdn.toString());
        c.setExternalId(uuidC1);
        final String cId = cres.create(c);

        mdn++;
        c.setPrimaryMdn(mdn.toString());
        c.setExternalId(uuidC2);
        final String c2Id = cres.create(c);

        ContactGroup cg = new ContactGroup();
        ContactGroup.Resource res = new ContactGroup.Resource(setup.getSession());

        cg.setDescription("CRUD");
        cg.setExternalId(uuid);
        cg.setName("Test");

        String new_id = res.create(cg);
        cg = res.get(new_id);

        assertTrue(cg.listContacts(null).getAllAsList().isEmpty());

        cg.AddContact(c2Id);
        cg.AddContact(cres.get(cId));

        assertTrue(cg.listContacts(null).getAllAsList().size() == 2);

        cg.DeleteContact(c2Id);

        assertTrue(cg.listContacts(null).getAllAsList().size() == 1);

        try {
            cg.delete();
            cres.delete(c2Id);
            cres.delete(cId);
        } catch (Exception ex) {
            // Ignore
        }
    }
}
