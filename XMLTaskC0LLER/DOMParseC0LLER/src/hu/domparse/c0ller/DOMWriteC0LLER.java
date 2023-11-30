package hu.domparse.c0ller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringJoiner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

public class DOMWriteC0LLER {
    public static void main(String[] args) {
        try {
        	 // Creating a new document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Filling up the document with data
            addData(doc);
            
            File outputFile = new File("XMLC0LLER1.xml");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true));

            // Printing out the root element and the prolog
            Element rootElement = doc.getDocumentElement();
            String rootName = rootElement.getTagName();
            StringJoiner rootAttributes = new StringJoiner(" ");
            NamedNodeMap rootAttributeMap = rootElement.getAttributes();

            for (int i = 0; i < rootAttributeMap.getLength(); i++) {
                Node attribute = rootAttributeMap.item(i);
                rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }

            System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            System.out.println("<" + rootName + " " + rootAttributes.toString() + ">\n");
            writer.println("<" + rootName + " " + rootAttributes.toString() + ">\n");

            // Processing the elements
            NodeList librarianList = doc.getElementsByTagName("librarian");
            NodeList businessCardList = doc.getElementsByTagName("business_card");
            NodeList borrowerList = doc.getElementsByTagName("borrower");
            NodeList handingOverList = doc.getElementsByTagName("handing_over");
            NodeList bookList = doc.getElementsByTagName("book");
            NodeList borrowedByList = doc.getElementsByTagName("borrowed_by");
            NodeList authorList = doc.getElementsByTagName("author");
            NodeList writtenByList = doc.getElementsByTagName("written_by");

            // Printing out the (formatted) XML to the console and to the file
            printNodeList(librarianList, writer);
            printNodeList(businessCardList, writer);
            printNodeList(borrowerList, writer);
            printNodeList(handingOverList, writer);
            printNodeList(bookList, writer);
            printNodeList(borrowedByList, writer);
            printNodeList(authorList, writer);
            printNodeList(writtenByList, writer);

            // Closing the root element
            System.out.println("</" + rootName + ">");
            writer.append("</" + rootName + ">");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void addData(Document doc) {
    	
        Element rootElement = doc.createElement("C0LLER_Library");
        rootElement.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xs:noNamespaceSchemaLocation", "XMLSchemaC0LLER.xsd");
        doc.appendChild(rootElement);

        addLibrarian(doc, rootElement, "01", "Klaudia", "Fehér", "2000-05-17", "23", "0630-080-8126", "0620-871-4015");
        addLibrarian(doc, rootElement, "02", "Albert", "Pintér", "1988-12-17", "35", "0620-155-0102", null);
        addLibrarian(doc, rootElement, "03", "Erzsébet", "Farkas", "1966-03-12", "57", "0670-440-8020", null);

        addBusinessCard(doc, rootElement, "11", "01", "Fehér Klaudia");
        addBusinessCard(doc, rootElement, "12", "02", "Pintér Albert");
        addBusinessCard(doc, rootElement, "13", "03", "Farkas Erzsébet");

        addBorrower(doc, rootElement, "21", "Árpád", "Jakab", "1965-01-12", "58", "Miskolc", "Vitéz utca", "31", "0630-651-4518", "0670-749-3819", null);
        addBorrower(doc, rootElement, "22", "Georgina", "Somogyi", "1972-10-24", "51", "Arnót", "Kisfaludy utca", "17", "0620-845-4708", null, "somogyigeorgina@citromail.hu");
        addBorrower(doc, rootElement, "23", "Maja", "Rácz", "2000-11-28", "23", null, null, null, null, null, "rmaja@freemail.hu");

        addHandingOver(doc, rootElement, "32", "02", "21", null, "1");
        addHandingOver(doc, rootElement, "31", "03", "22", "2023-08-23","1");
        addHandingOver(doc, rootElement, "33", "01", "23", "2022-12-16", "0");

        addBook(doc, rootElement, "31", "Harry Potter és a Bölcsek Köve", "1");
        addBook(doc, rootElement, "32", "Ahol a folyami rákok énekelnek", "1");
        addBook(doc, rootElement, "33", "Fordulópont", "1");

        addBorrowedBy(doc, rootElement, "32", "21", "0", "2023-03-17");
        addBorrowedBy(doc, rootElement, "31", "22", "0", "2023-09-01");
        addBorrowedBy(doc, rootElement, "33", "23", "800", "2023-02-16");

        addAuthor(doc, rootElement, "41", "Joanne", "Rowling", "1965-07-31", "58");
        addAuthor(doc, rootElement, "42", "Delia", "Owens", "1949-04-04", "74");
        addAuthor(doc, rootElement, "43", "Danielle", "Steel", "1947-08-14", "76");

        addWrittenBy(doc, rootElement, "31", "41", "2001-12-13");
        addWrittenBy(doc, rootElement, "32", "42", "2018-08-14");
        addWrittenBy(doc, rootElement, "33", "43", "2019-07-21");
    }
    
    private static void addLibrarian(Document doc, Element rootElement, String librarianId, String firstName, String lastName, String dateOfBirth, String age, String phoneNumber1, String phoneNumber2) {
        Element librarian = doc.createElement("librarian");
        librarian.setAttribute("librarian_id", librarianId);

        Element name = doc.createElement("name");
        Element firstNameElement = createElement(doc, "first_name", firstName);
        Element lastNameElement = createElement(doc, "last_name", lastName);
        name.appendChild(firstNameElement);
        name.appendChild(lastNameElement);

        Element dateOfBirthElement = createElement(doc, "date_of_birth", dateOfBirth);
        Element ageElement = createElement(doc, "age", age);

        librarian.appendChild(name);
        librarian.appendChild(dateOfBirthElement);
        librarian.appendChild(ageElement);

        if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
            Element phoneNumber1Element = createElement(doc, "phone_number", phoneNumber1);
            librarian.appendChild(phoneNumber1Element);
        }
        if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
            Element phoneNumber2Element = createElement(doc, "phone_number", phoneNumber2);
            librarian.appendChild(phoneNumber2Element);
        }

        rootElement.appendChild(librarian);
    }

    private static void addBusinessCard(Document doc, Element rootElement, String cardId, String librarianId, String writtenName) {
        Element businessCard = doc.createElement("business_card");
        businessCard.setAttribute("card_id", cardId);
        businessCard.setAttribute("librarian_id", librarianId);

        Element writtenNameElement = createElement(doc, "written_name", writtenName);

        businessCard.appendChild(writtenNameElement);

        rootElement.appendChild(businessCard);
    }
    
    private static void addBorrower(Document doc, Element rootElement, String borrowerId, String firstName, String lastName, String dateOfBirth, String age, String city, String street, String houseNumber, String phoneNumber1, String phoneNumber2, String emailAddress) {
        Element borrower = doc.createElement("borrower");
        borrower.setAttribute("borrower_id", borrowerId);

        Element name = doc.createElement("name");
        Element firstNameElement = createElement(doc, "first_name", firstName);
        Element lastNameElement = createElement(doc, "last_name", lastName);
        name.appendChild(firstNameElement);
        name.appendChild(lastNameElement);

        Element dateOfBirthElement = createElement(doc, "date_of_birth", dateOfBirth);
        Element ageElement = createElement(doc, "age", age);
                
        borrower.appendChild(name);
        borrower.appendChild(dateOfBirthElement);
        borrower.appendChild(ageElement);

        if(city != null && !city.isEmpty()) {
        	Element address = doc.createElement("address");
        	
            Element cityElement = createElement(doc, "city", city);
            Element streetElement = createElement(doc, "street", street);
            Element houseNumberElement = createElement(doc, "house_number", houseNumber);
            
            address.appendChild(cityElement);
            address.appendChild(streetElement);
            address.appendChild(houseNumberElement);
            
            borrower.appendChild(address);
        }

        if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
            Element phoneNumber1Element = createElement(doc, "phone_number", phoneNumber1);
            borrower.appendChild(phoneNumber1Element);
        }
        if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
            Element phoneNumber2Element = createElement(doc, "phone_number", phoneNumber2);
            borrower.appendChild(phoneNumber2Element);
        }

        if (emailAddress != null && !emailAddress.isEmpty()) {
            Element emailAddressElement = createElement(doc, "email_address", emailAddress);
            borrower.appendChild(emailAddressElement);
        }

        rootElement.appendChild(borrower);
    }

    private static void addHandingOver(Document doc, Element rootElement, String bookId, String librarianId, String borrowerId, String handingDate, String lendingOrTaking) {
        Element handingOverSwitchboard = doc.createElement("handing_over");
        handingOverSwitchboard.setAttribute("book_id", bookId);
        handingOverSwitchboard.setAttribute("librarian_id", librarianId);
        handingOverSwitchboard.setAttribute("borrower_id", borrowerId);

        Element lendingOrTakingElement = createElement(doc, "lending_or_taking", lendingOrTaking);
        Element handingDateElement = createElement(doc, "handing_date", handingDate);

        handingOverSwitchboard.appendChild(lendingOrTakingElement);
        handingOverSwitchboard.appendChild(handingDateElement);

        rootElement.appendChild(handingOverSwitchboard);
    }

    private static void addBook(Document doc, Element rootElement, String bookId, String title, String borrowable) {
        Element book = doc.createElement("book");
        book.setAttribute("book_id", bookId);

        Element titleElement = createElement(doc, "title", title);
        Element borrowableElement = createElement(doc, "borrowable", borrowable);

        book.appendChild(titleElement);
        book.appendChild(borrowableElement);

        rootElement.appendChild(book);
    }

    private static void addBorrowedBy(Document doc, Element rootElement, String bookId, String borrowerId, String lateFee, String deadline) {
        Element borrowedBySwitchboard = doc.createElement("borrowed_by");
        borrowedBySwitchboard.setAttribute("book_id", bookId);
        borrowedBySwitchboard.setAttribute("borrower_id", borrowerId);

        Element lateFeeElement = createElement(doc, "late_fee", lateFee);
        Element deadlineElement = createElement(doc, "deadline", deadline);

        borrowedBySwitchboard.appendChild(lateFeeElement);
        borrowedBySwitchboard.appendChild(deadlineElement);

        rootElement.appendChild(borrowedBySwitchboard);
    }

    private static void addAuthor(Document doc, Element rootElement, String authorId, String firstName, String lastName, String dateOfBirth, String age) {
        Element author = doc.createElement("author");
        author.setAttribute("author_id", authorId);

        Element name = doc.createElement("name");
        Element firstNameElement = createElement(doc, "first_name", firstName);
        Element lastNameElement = createElement(doc, "last_name", lastName);
        name.appendChild(firstNameElement);
        name.appendChild(lastNameElement);

        Element dateOfBirthElement = createElement(doc, "date_of_birth", dateOfBirth);
        Element ageElement = createElement(doc, "age", age);

        author.appendChild(name);
        author.appendChild(dateOfBirthElement);
        author.appendChild(ageElement);

        rootElement.appendChild(author);
    }

    private static void addWrittenBy(Document doc, Element rootElement, String bookId, String authorId, String publicationDate) {
        Element writtenBySwitchboard = doc.createElement("written_by");
        writtenBySwitchboard.setAttribute("book_id", bookId);
        writtenBySwitchboard.setAttribute("author_id", authorId);

        Element publicationDateElement = createElement(doc, "publication_date", publicationDate);

        writtenBySwitchboard.appendChild(publicationDateElement);

        rootElement.appendChild(writtenBySwitchboard);
    }

    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
    
    // Printing out the content of the NodeList recursively
    private static void printNodeList(NodeList nodeList, PrintWriter writer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
          Node node = nodeList.item(i);
          printNode(node, 1, writer);
          System.out.println("");
          writer.println("");
        }
        System.out.println("");
        writer.println("");
    }

    // Printing out the content of the Node recursively
	private static void printNode(Node node, int indent, PrintWriter writer) {
	    if (node.getNodeType() == Node.ELEMENT_NODE) {
	      Element element = (Element) node;
	      String nodeName = element.getTagName();
	      StringJoiner attributes = new StringJoiner(" ");
	      NamedNodeMap attributeMap = element.getAttributes();
	
	      // Name and attributes
	      for (int i = 0; i < attributeMap.getLength(); i++) {
	        Node attribute = attributeMap.item(i);
	        attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
	      }
	
	      System.out.print(getIndentString(indent));
	      System.out.print("<" + nodeName + " " + attributes.toString()+ ">");
	
	      writer.print(getIndentString(indent));
	      writer.print("<" + nodeName + " " + attributes.toString() + ">");
	
	      // Content
	      NodeList children = element.getChildNodes();
	      if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
	        System.out.print(children.item(0).getNodeValue());
	        writer.print(children.item(0).getNodeValue());
	      } else {
	        System.out.println();
	        writer.println();
	        for (int i = 0; i < children.getLength(); i++) {
	          printNode(children.item(i), indent + 1, writer);
	        }
	        System.out.print(getIndentString(indent));
	        writer.print(getIndentString(indent));
	      }
	      
	      // Closing the node
	      System.out.println("</" + nodeName + ">");
	      writer.println("</" + nodeName + ">");
	    }
	}
	
	// Indenting
	private static String getIndentString(int indent) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < indent; i++) {
	      sb.append("  "); // 2 spaces per indent level
	    }
	    return sb.toString();
	}
}

