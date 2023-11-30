package hu.domparse.c0ller;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMQueryC0LLER {
    public static void main(String[] args) {
        try {
        	// Reading in the file
        	File xmlFile = new File("C:\\Users\\vanda\\Desktop\\XMLTaskC0LLER\\XMLC0LLER.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Queries
            firstQuery(doc);
            secondQuery(doc);
            thirdQuery(doc);
            fourthQuery(doc);
            fifthQuery(doc);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void firstQuery(Document doc)
    {
    	// Querying the name of all librarians
        NodeList librarianList = doc.getElementsByTagName("librarian");
        System.out.println("1, Librarians: ");
        for (int i = 0; i < librarianList.getLength(); i++) {
            Element librarian = (Element) librarianList.item(i);
            String firstName = librarian.getElementsByTagName("first_name").item(0).getTextContent();
            String lastName = librarian.getElementsByTagName("last_name").item(0).getTextContent();
            System.out.println(lastName + " " + firstName);
        }
        System.out.println();
    }
    
    private static void secondQuery(Document doc)
    {
    	// Querying the data of the borrower with ID '22'
        String borrowerId = "22";
        NodeList borrowerList = doc.getElementsByTagName("borrower");
        for (int i = 0; i < borrowerList.getLength(); i++) {
            Element borrower = (Element) borrowerList.item(i);
            if (borrower.getAttribute("borrower_id").equals(borrowerId)) {
                String firstName = borrower.getElementsByTagName("first_name").item(0).getTextContent();
                String lastName = borrower.getElementsByTagName("last_name").item(0).getTextContent();
                String dateOfBirth = borrower.getElementsByTagName("date_of_birth").item(0).getTextContent();
                String age = borrower.getElementsByTagName("age").item(0).getTextContent();
                String city = borrower.getElementsByTagName("city").item(0).getTextContent();
                String street = borrower.getElementsByTagName("street").item(0).getTextContent();
                String houseNumber = borrower.getElementsByTagName("house_number").item(0).getTextContent();

                System.out.println("2, Data of the borrower with ID '" + borrowerId + "':");
                System.out.println("Name: " + lastName + " " + firstName);
                System.out.println("Date of birth: " + dateOfBirth);
                System.out.println("Age: " + age);
                System.out.println("Address: " + city + ", " + street + " " + houseNumber);

                NodeList phoneNumberList = borrower.getElementsByTagName("phone_number");
                System.out.print("Phone number(s): ");
                for (int j = 0; j < phoneNumberList.getLength(); j++) {
                    String phoneNumber = phoneNumberList.item(j).getTextContent();
                    System.out.print(phoneNumber);
                    if(j<phoneNumberList.getLength()-1)
                    	System.out.print(", ");
                }

                System.out.println();
                Node emailNode = borrower.getElementsByTagName("email_address").item(0);
                if (emailNode != null) {
                    String emailAddress = emailNode.getTextContent();
                    System.out.println("Email address: " + emailAddress);
                }
                break;
            }
        }
        System.out.println();
    }
    
    private static void thirdQuery(Document doc)
    {
    	// Querying the name of the author(s) who are 60 years old or older
        NodeList authorList = doc.getElementsByTagName("author");
        System.out.println("3, Author(s) who are 60 years old or older:");
        for (int i = 0; i < authorList.getLength(); i++) {
            Element author = (Element) authorList.item(i);
            int age = Integer.parseInt(author.getElementsByTagName("age").item(0).getTextContent());

            if (age >= 60) {
                String firstName = author.getElementsByTagName("first_name").item(0).getTextContent();
                String lastName = author.getElementsByTagName("last_name").item(0).getTextContent();
                System.out.println(firstName + " " + lastName);
            }
        }
        System.out.println();
    }
    
    private static void fourthQuery(Document doc)
    {
    	// Querying the data of the book with the title 'Ahol a folyami rákok énekelnek'
        String bookTitle = "Ahol a folyami rákok énekelnek";
        boolean isBorrowable = false;

        NodeList bookList = doc.getElementsByTagName("book");
        for (int i = 0; i < bookList.getLength(); i++) {
            Element book = (Element) bookList.item(i);
            String title = book.getElementsByTagName("title").item(0).getTextContent();

            if (title.equals(bookTitle)) {
                isBorrowable = "1".equals(book.getElementsByTagName("borrowable").item(0).getTextContent());
                System.out.println("4, Data of the book: ");
                System.out.println("Title: " + title);
                System.out.println("Borrowable: " + (isBorrowable ? "yes" : "no"));
                break;
            }
        }
        System.out.println();
    }
    
    private static void fifthQuery(Document doc)
    {
    	// Querying the title of the book borrowed by 'Rácz Maja'
        String borrowerName = "Rácz Maja";

        NodeList borrowerList = doc.getElementsByTagName("borrower");
        for (int i = 0; i < borrowerList.getLength(); i++) {
            Element borrower = (Element) borrowerList.item(i);
            String firstName = borrower.getElementsByTagName("first_name").item(0).getTextContent();
            String lastName = borrower.getElementsByTagName("last_name").item(0).getTextContent();
            String borrowerFullName = lastName + " " + firstName;

            if (borrowerFullName.equals(borrowerName)) {
                String borrowerId = borrower.getAttribute("borrower_id");

                NodeList borrowedByList = doc.getElementsByTagName("borrowed_by");
                System.out.println("5, Title of the book borrowed by " + borrowerName + ": ");
                for (int j = 0; j < borrowedByList.getLength(); j++) {
                    Element borrowedBy = (Element) borrowedByList.item(j);
                    if (borrowedBy.getAttribute("borrower_id").equals(borrowerId)) {
                        String bookId = borrowedBy.getAttribute("book_id");

                        NodeList bookList = doc.getElementsByTagName("book");
                        for (int k = 0; k < bookList.getLength(); k++) {
                            Element book = (Element) bookList.item(k);
                            if (book.getAttribute("book_id").equals(bookId)) {
                                String bookTitle = book.getElementsByTagName("title").item(0).getTextContent();
                                System.out.println(bookTitle);
                            }
                        }
                    }
                }
                break; 
            }
        }
    }
}

