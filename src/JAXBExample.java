
import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class JAXBExample {
	public static void main(String[] args) {

		Name name = new Name();
		name.setName("Adam");

		try {

			File file = new File("xml_folder/file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Name.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(name, file);
			jaxbMarshaller.marshal(name, System.out);
			
			boolean valid = validateXMLSchema("xml_folder/name.xsd","xml_folder/file.xml");
			System.out.println("Validation result: " + valid);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	

	   public static boolean validateXMLSchema(String xsdPath, String xmlPath){
	      try {
	         SchemaFactory factory =
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Schema schema = factory.newSchema(new File(xsdPath));
	            Validator validator = schema.newValidator();
	            validator.validate(new StreamSource(new File(xmlPath)));
	      } catch (IOException e){
	         System.out.println("Exception: "+e.getMessage());
	         return false;
	      }catch(SAXException e1){
	         System.out.println("SAX Exception: "+e1.getMessage());
	         return false;
	      }
			
	      return true;
		
	   }
}