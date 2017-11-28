package be.vdab.training;

import be.vdab.training.domain_jaxb.*;
import be.vdab.training.utilities.DateUtility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.File;
import java.time.LocalDate;

public class MyAppHelper {
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    private static final String CONTACT_JAXB_XML2 = "C:\\wim\\oak3 - cronos- training\\cursus_data_input_output\\Contact_2_JAXB.xml";
    private static final String EMPLOYEE_JAXB_XML = "C:\\wim\\oak3 - cronos- training\\cursus_data_input_output\\Employee.xml";

    public static void marshallContact() throws JAXBException {

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
        marshaller.setProperty("jaxb.noNamespaceSchemaLocation", "Contact.xsd");

        File file = new File(CONTACT_JAXB_XML2);

        // write JAXB object to XML file

        marshaller.marshal(contact, file);

    }

    public static void marshallEmployee() throws JAXBException, DatatypeConfigurationException {

        Employee employee = OBJECT_FACTORY.createEmployee();

        employee.setFname("wim");
        employee.setLname("Van den Brande");

        ProjectType project1 = OBJECT_FACTORY.createProjectType();
        project1.setName("dummy project");
        project1.setProjectEndDate(DateUtility.convertLocalDatetoXMLGregorianCalendar(LocalDate.of(2017, 12, 31)));
        project1.setProjectStartDate(DateUtility.convertLocalDatetoXMLGregorianCalendar(LocalDate.of(2017, 1, 1)));

        employee.getProject().add(project1);

        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);

        File file = new File(EMPLOYEE_JAXB_XML);

        // write JAXB object to XML file

        marshaller.marshal(employee, file);
    }
}
