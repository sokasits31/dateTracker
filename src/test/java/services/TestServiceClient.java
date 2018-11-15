package services;


import org.junit.jupiter.api.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceClient {

    //Consume Your First RESTful Web Service in IntelliJ
    @Test
    public void testGoogleApiJSON() throws Exception {
        /*

        //javax.ws.rs.NotAllowedException: HTTP 405 Method Not Allowed
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://localhost:8080/dateTracker/services/events/search/all");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals("???", response);

        */
    }
}