package com.firstlinesoftware.lesson2.serialization;

import com.firstlinesoftware.lesson2.domain.User;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SerializationExample {

    private final UserService userService = new UserService();

    private final User user = User.builder()
            .id(1)
            .confirmed(true)
            .name("Sergey Kunzetsov")
            .email("sergey.kuznetsov@firstlinesoftware.com")
            .phone("880000000")
            .build();

    @Test
    public void testUserSerializationDeserialization() throws IOException, ClassNotFoundException {
        final File file = new File("user.tmp");
        userService.serializeUserToFile(user, file);
        final User serializedUser = userService.deserializeUserFromFile(file);
        assertEquals(user, serializedUser);
    }


    @Test
    public void testJsonSerialization() throws IOException {
        final File file = new File("user.json");
        userService.writeUserAsJsonToFile(user, file);
        final User newUser = userService.readUserFromJsonFile(file);
        assertEquals(user, newUser);
    }

    @Test
    public void testXmlSerialization() throws JAXBException {
        final File file = new File("user.xml");
        userService.writeUserAsXmlToFile(user, file);
        final User serializedUser = userService.readUserFromXmlFile(file);
        assertEquals(this.user, serializedUser);
    }
}
