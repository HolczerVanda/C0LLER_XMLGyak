package hu.domparse.c0ller;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.StringWriter;

public class DOMModifyC0LLER {
    public static void main(String[] args) {
        try {
            // Reading in the file
        	File xmlFile = new File("C:\\Users\\vanda\\Desktop\\XMLTaskC0LLER\\XMLC0LLER.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            
            // Implementation of changes
           
            //Changing the first and last name of a librarian
            NodeList librarianList = doc.getElementsByTagName("librarian");
            Element librarian = (Element) librarianList.item(1);
            librarian.getElementsByTagName("first_name").item(0).setTextContent("Nagy");
            librarian.getElementsByTagName("last_name").item(0).setTextContent("Gergo");
            
            //Changing the written name on a business card
            NodeList businessCardList = doc.getElementsByTagName("business_card");
            Element businessCard = (Element) businessCardList.item(1);
            businessCard.getElementsByTagName("written_name").item(0).setTextContent("Nagy Gergo");
            
            //Changing the phone number of a borrower
            NodeList borrowerList = doc.getElementsByTagName("borrower");
            Element borrower = (Element) borrowerList.item(0);
            borrower.getElementsByTagName("phone_number").item(1).setTextContent("0620-829-4357");
            
            //Changing the title of a book
            NodeList bookList = doc.getElementsByTagName("book");
            Element book = (Element) bookList.item(2);
            book.getElementsByTagName("title").item(0).setTextContent("Szamjatek");
            
            //Changing the age of an author
            NodeList authorList = doc.getElementsByTagName("author");
            Element author = (Element) authorList.item(1);
            author.getElementsByTagName("age").item(0).setTextContent("68");
            
            
            // Transforming the modified document to a string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Creating a StringWriter to write the XML string
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            // Getting the modified XML as a string
            String output = writer.toString();
            // Printing out the modified XML to the console
            System.out.println(output);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

