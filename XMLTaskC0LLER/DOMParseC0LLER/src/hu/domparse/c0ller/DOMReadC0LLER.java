package hu.domparse.c0ller;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.StringJoiner;

public class DOMReadC0LLER {
    public static void main(String[] args) {
        try {
        	// Reading in the file
            File xmlFile = new File("C:\\Users\\vanda\\Desktop\\XMLTaskC0LLER\\XMLC0LLER.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            File outputFile = new File("read.xml");
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
            System.out.print("<" + nodeName + " " + attributes.toString() + ">");

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
