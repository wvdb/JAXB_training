package be.vdab.training;

import be.vdab.training.domain_jaxb.Contact;
import be.vdab.training.domain_jaxb.ObjectFactory;
import be.vdab.training.domain_jaxb.PhoneType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MyAppHelper {
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    private static final String CONTACT_JAXB_XML2 = "C:\\wim\\oak3 - cronos- training\\cursus_data_input_output\\Contact_2_JAXB.xml";

    public static void myMarshaller() throws JAXBException {

        Contact contact = OBJECT_FACTORY.createContact();

        contact.setAddress("Liège");
        contact.setCountry("Belgium");
        contact.setName("David Goffin");
        contact.setNotes("A tennis player we won't have the next decade.");

        PhoneType phone1 = OBJECT_FACTORY.createPhoneType();
        phone1.setType("private_mobile");
        phone1.setValue("123");

        PhoneType phone2 = OBJECT_FACTORY.createPhoneType();
        phone2.setType("private_fixed");
        phone2.setValue("234");

        contact.getPhone().add(phone1);
        contact.getPhone().add(phone2);

        JAXBContext jaxbContext = JAXBContext.newInstance(Contact.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);

        File file = new File(CONTACT_JAXB_XML2);

        // write JAXB object to XML

        marshaller.marshal(contact, file);

    }
}
