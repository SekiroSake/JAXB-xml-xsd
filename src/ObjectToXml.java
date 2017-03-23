import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.*;
//import javax.xml.bind.JAXBContext;  
//import javax.xml.bind.Marshaller;  

public class ObjectToXml {
	public static void main(String[] args) throws Exception {
		ObjectToXml objXml = new ObjectToXml();
		objXml.generateXml();
	}
	
	public void generateXml() throws JAXBException, IOException {

		Employee emp1 = new Employee(1, "Vimal Jaiswal", 50000);

        File f = new File("simple.xml");
        JAXBContext context= JAXBContext.newInstance(Employee.class);
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(emp1, f);
        jaxbMarshaller.marshal(emp1, System.out);

    }
}