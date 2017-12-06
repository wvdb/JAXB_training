package be.vdab.training;

import be.vdab.training.domain.Contact;
import be.vdab.training.domain.ObjectFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MyApp {
    private static final String CONTACT_JAXB_XML = "C:\\wim\\oak3 - cronos- training\\cursus_data_input_output\\Contact_JAXB.xml";

    public static void main(String[] args) throws InterruptedException, ExecutionException, JAXBException, XPathExpressionException, ParserConfigurationException, IOException, SAXException, DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();

        // use the XML-POJO counterparts (JAXB classes)

        Contact contact = objectFactory.createContact();

        contact.setAddress("Liège");
        contact.setCountry("Belgium");
        contact.setName("David Goffin");
        contact.setNotes("A tennis player we won't have the next decade.");

        Contact.Phone phone1 = objectFactory.createContactPhone();
        phone1.setType("private_mobile");
        phone1.setValue("123");

        Contact.Phone phone2 = objectFactory.createContactPhone();
        phone2.setType("private_fixed");
        phone2.setValue("234");

        // TODO : to explain (get list and add)

        contact.getPhone().add(phone1);
        contact.getPhone().add(phone2);

        JAXBContext jaxbContext = JAXBContext.newInstance(Contact.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);

        // write the JAXB-object to flat file

        File file = new File(CONTACT_JAXB_XML);
        marshaller.marshal(contact, file);

        // part 2 : additional steps: start from an XML file and retrieve nodes
        // XPATH - retrieve phones

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(file);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/contact/phone";
        NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

        for (int lengthNodes=0; lengthNodes < nodes.getLength(); lengthNodes++) {
            Node node = nodes.item(lengthNodes);
            System.out.println("Phone = " + node.getFirstChild().getNodeValue());
        }


        // part 3 : using the JAXB classes (writing JAXB to XML)

        MyAppHelper.marshallContact();
        MyAppHelper.marshallEmployee();
    }
}
