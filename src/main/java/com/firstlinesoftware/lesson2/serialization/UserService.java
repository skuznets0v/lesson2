package com.firstlinesoftware.lesson2.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstlinesoftware.lesson2.domain.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class UserService {

    public UserService() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void serializeUserToFile(User user, File file) throws IOException {
        try (final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(user);
        }
    }

    public User deserializeUserFromFile(File file) throws IOException, ClassNotFoundException {
        try (final ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            final Object user = in.readObject();
            if (!(user instanceof User)) {
                throw new IllegalStateException();
            }
            return ((User) user);
        }
    }


    private final ObjectMapper mapper = new ObjectMapper();

    public void writeUserAsJsonToFile(User user, File file) throws IOException {
        mapper.writeValue(file, user);
    }

    public User readUserFromJsonFile(File file) throws IOException {
        return mapper.readValue(file, User.class);
    }


    private final Marshaller marshaller;

    private final Unmarshaller unmarshaller;

    public void writeUserAsXmlToFile(User user, File file) throws JAXBException {
        marshaller.marshal(user, file);
    }

    public User readUserFromXmlFile(File file) throws JAXBException {
        return (User) unmarshaller.unmarshal(file);
    }
}
