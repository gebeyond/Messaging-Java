
# SCG Java SDK
This is the Java version of the SCG API.
We have prepared simple to use Java classes, representing the
different REST API interfaces.

The Java API hides some of the REST API's constraints, like
lists being returned in logical pages of <i>n</i> records. With the
Java SDK, the list() method returns a iterator that returns
items, until there are no more.

Please register for a free account at https://developer.syniverse.com to get your API keys.

## How to use the SDK
All the data objects follow a common pattern.

You get a handle to a resource (like Contact or SenderId) by getting
an instance of <Object>.Resource(session).

The session object is obtained from Scg.connect(), and gives you
sequential (blocking) access to the REST API. If you want to
process many requests in parallel, you need many session objects.

The resource object you get from  <Object>.Resource(session)
typically has <b>list()</b> and <b>create()</b> methods (depending
on the actual REST API methods available for that data type).

To list the data objects for a resource, like your Contacts,
you can call list() without any arguments to get all the contacts,
or list(Map<String, String>) to filter the result-set. list()
returns an iterator that let's you iterate over the data-set.

You can create an object by calling create() with an object (template)
of the data type you aim to create, with the relevant members assigned
valid values.

```java
    // Create from object
    Contact template = new Contact();
    template.setFirstName("John");
    template.setLastName("Doe");
    template.setPrimaryMdn("123456789");

    Contact.Resource res = new Contact.Resource(session);
    String id = res.create(template);
```

All objects that can be updated or deleted has <b>update()</b> and/or
<b>delete()</b> methods. The resource of an object also have <b>delete()</b>
methods, so if you need to delete an object you just know by it's id,
there is no need to instatiate it. You just call:

```java
    res.delete(id)
```

Some objects has methods that let you add/query or delete
other objects or references it holds to other objects. For
example, when you create a message-request to a group of
contacts, you may generate a large number of message objects.
These can be iterated over from MessageRequest.listMessages()

```java
    MessageRequest.Resource mrqRes = new MessageRequest.Resource(session);
    MessageRequest request = mrqRes.get("qteDxVrAhlMlmTwDrMAvN3");
    for(Message msg: request.listMessages(null)) {
        // Do something with msg
    }
```

## Error handling
Errors are reported trough exceptions.

# Some examples

## Listing Sender Id's
If you want to list available Sender Id's, it can be done as easy as:

```java
    // Construct an instance of the authentication object
    // with authentication data from auth.json
    AuthInfo auth = new AuthInfo(new File("auth.json"));

    // Prepare a session to the server.
    Scg scg = new Scg();
    Session session = scg.connect("https://api.syniverse.com", auth);

    // Request the complete list of sender id's from the server,
    // where the class_id is COMMECRIAL and state is ACTIVE, and iterate
    // over them one by one.

    Map<String, String> filter = new HashMap<>();
    filter.put("class_id", "COMMERCIAL");
    filter.put("state", "ACTIVE");

    SenderId.Resource res = new  SenderId.Resource(session);
    for (SenderId sid : res.list(filter)) {
        System.out.println("Sender id " + sid.getId()
            + " has capabilities " +  sid.getCapabilities().toString());
    }

```

This should produce output like:
```text
Sender id oX1iQToXaWAXY6u3yLhja4 has capabilities [WECHAT]
Sender id m2sb4eA3mlEConWJzsfYq6 has capabilities [FACEBOOK]
Sender id ln9sk9JF6insXcJ5nUzKK3 has capabilities [SMS]
Sender id AE0vtyghu8dIrrpXesXPK1 has capabilities [MMS, SMS]
Sender id 3hTOgeTWYlflMB2zmYNoP has capabilities [SMS]
Sender id dY2GycQpj6OE1x2mH1Ezc6 has capabilities [VOICE]
Sender id KdNRN6dl5IkwIH5B829XJ2 has capabilities [MMS, SMS]
Sender id tcS8h40LXgvJsGMQI93WK4 has capabilities [MMS, SMS]
```

## Adding and updating a Contact
```java

    // Construct an instance of the authentication object
    // with authentication data from auth.json
    AuthInfo auth = new AuthInfo(new File("auth.json"));

    // Prepare a session to the server.
    Scg scg = new Scg();
    Session session = scg.connect("https://api.syniverse.com", auth);

    // Get a handle to the Contacts resource
    Contact.Resource res = new Contact.Resource(session);

    // Create a contact. Note that we get a contact id (string), not a Contact
    // instance from Create.

    Contact template = new Contact();
    template.setFirstName("John");
    template.setLastName("Doe");
    template.setPrimaryMdn("123456789");

    String id = res.create(template);

    // Get an instance of the contact
    Contact contact = res.get(id);

    // Let's add some information

    contact.setExternalId("Test Extid 12345");

    //Update the contact on the server
    contact.update();

    // Verify that the server has the updated Contact
    Contact vcontact = res.get(id);


    System.out.println("Contact id " + vcontact.getId()
        + ", " + vcontact.getFirstName() "
        + " " + vcontact.getLastName()
        + " has external id \"" + vcontact.getExternalId() + "\"");

    // Delete the contact on the server
    contact.delete();
```

This should produce output similar to:
```text
Contact id xzrw4ukXaINXzgwu5XLnb1, John Doe has external id "Test Extid 12345"
```

## Sending a SMS to a GSM number
```java
    // Construct an instance of the authentication object
    // with authentication data from auth.json
    AuthInfo auth = new AuthInfo(new File("auth.json"));

    // Prepare a session to the server.
    Scg scg = new Scg();
    Session session = scg.connect("https://api.syniverse.com", auth);

    // Send SMS message
    MessageRequest.Resource res = new MessageRequest.Resource(session);
    MessageRequest mrq = new MessageRequest();
    mrq.setFrom("sender_id:" + "tcS8h40LXgvJsGMQI93WK4");
    mrq.setTo("123456789");
    mrq.setBody("Hello World");

    String reqId = res.create(mrq);

    System.out.println("Sent message request " + reqId);
```

where the settings are changed to a real SenderId and GSM number.

This should produce output similar to:
```text
Sent message request aQWY9PeMCO01TEH9bk1ek5
```

## Sending a Message to a Contact

This works as above, except for the to field in create()
```java
    String contactId = "<Id of an existing contact>";
    ...
    mrq.setTo("contact:" + contactId);
    ...
```

## Sending a Message to a Group
```java
    // Construct an instance of the authentication object
    // with authentication data from auth.json
    AuthInfo auth = new AuthInfo(new File("auth.json"));

    // Prepare a session to the server.
    Scg scg = new Scg();
    Session session = scg.connect("https://api.syniverse.com", auth);
    Contact.Resource contactRes = new Contact.Resource(session);

    // Create some contacts
    Contact template = new Contact();
    template.setFirstName("Bob")
    template.setPrimaryMdn("123456789")
    Srtring bobId = contactRes.create(template);

    template.setFirstName("Alice")
    template.setPrimaryMdn("123456788")
    Srtring aliceId = contactRes.create(template);

    // Create a group
    ContactGroup.Resource groupRes = new ContactGroup.Resource(session);
    ContactGroup groupTemplate = new ContactGroup();
    groupTemplate.setName("Our Friends");
    String groupId = groupRes.create(groupTemplate);
    ContactGroup friends = groupRes.get(groupId);


    // add our new friends to the group
    friends.AddContact(bobId);
    friends.AddContact(aliceId);

    // Send a message to our new friends
    MessageRequest.Resource res = new MessageRequest.Resource(session);
    MessageRequest mrq = new MessageRequest();
    mrq.setFrom("sender_id:" + "tcS8h40LXgvJsGMQI93WK4");
    mrq.setTo("group:" + friends.getId());
    mrq.setBody("Hello World");

    String reqId = res.create(mrq);
    System.out.println("Sent message request " + reqId);
```

## Sending a MMS with an attachment

```java
    // Construct an instance of the authentication object
    // with authentication data from auth.json
    AuthInfo auth = new AuthInfo(new File("auth.json"));

    // Prepare a session to the server.
    Scg scg = new Scg();
    Session session = scg.connect("https://api.syniverse.com", auth);

    // Upload an attachment
    Attachment attachmentTemplate = new Attachment();
    attachmentTemplate.setName("test_upload");
    attachmentTemplate.setType("image/jpeg");
    attachmentTemplate.setFilename("cute-cat.jpg");

    Attachment.Resource attachmentRes = new Attachment.Resource(session);
    String attId = attachmentRes.create(attachmentTemplate);
    Attachment att = attachmentRes.get(attId);

    att.uploadContent("/images/cats/cute.jpg");
    System.out.println("Uploaded attachment " + att.getId());

    // Send MMS message
    MessageRequest.Resource mrqRes = new MessageRequest.Resource(session);
    MessageRequest mrq = new MessageRequest();
    mrq.setFrom("sender_id:" + "tcS8h40LXgvJsGMQI93WK4");
    mrq.setTo("123456789");
    mrq.setBody("Hello World");
    mrq.addAttachment(att.getId());
    String reqId = mrqRes.create(mrq);

    System.out.println("Sent message request " + reqId);
```

This should produce output similar to:
```text
Uploaded attachment wMjURamVl9ITSXRJSkMoR4
Sent message request 9NeqCbNXBYvRO73jC2rbc5
```

## Checking the state of a Message Request
```java
    // Construct an instance of the authentication object
    // with authentication data from auth.json
    AuthInfo auth = new AuthInfo(new File("auth.json"));

    // Prepare a session to the server.
    Scg scg = new Scg();
    Session session = scg.connect("https://api.syniverse.com", auth);

    MessageRequest.Resource mrqRes = new MessageRequest.Resource(session);
    MessageRequest mrq = mrqRes.get("qteDxVrAhlMlmTwDrMAvMM");

    System.out.println("Message Request " + mrq.getId()
        + " is in state " + mrq.getState()
        + " with " + mrq.getDeliveredCount().toString()
        + " delivered and " + mrq.getFailedCount().toString()
        + "failed messages");

    for(Message msg : mrq.listMessages()) {
        System.out.println(" - Message " + msg.getId()
            + " is in state " + msg.getState()
            + ", error code: " + msg.getFailureCode()
            + ", error reason: " + msg.getFailureDetails());
    }
```

This should produce output similar to:
```text
Message Request qteDxVrAhlMlmTwDrMAvMM is in state COMPLETED with 2 delivered and 0 failed messages
 - Message HCHDrmhOX3rXvi3av1mXY6 is in state DELIVERED, error code: null, error reason: null
 - Message FDLoHMUmLjjYK51J531Xo5 is in state DELIVERED, error code: null, error reason: null
```

