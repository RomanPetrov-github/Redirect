package com.tele2.digital.pojo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class IncidentBuilder {

    private final String xml;

    public IncidentBuilder(String xml) {
        this.xml = xml;
    }

    public Incident build()throws JAXBException{
        StringReader reader = new StringReader(xml);

        JAXBContext context = JAXBContext.newInstance(Incident.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Incident incident = (Incident) unmarshaller.unmarshal(reader);

        incident.createListRedirects();

        return incident;
    }
}
