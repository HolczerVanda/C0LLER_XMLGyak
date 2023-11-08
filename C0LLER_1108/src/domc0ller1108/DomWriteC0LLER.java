package domc0ller1108;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DomWriteC0LLER {
    public static void main(String[] args) {
    	
        try {
        	File xml = new File("C:\\Users\\TEMP.IIT.005\\Desktop\\C0LLER_1108\\C0LLER_kurzusfelvetel.xml");
            
            try (BufferedReader br = new BufferedReader(new FileReader(xml))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            writeXmlContentToFile(xml);
                
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void writeXmlContentToFile(File inputFile) {
        File outputFile = new File("result.xml");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Written out the XML content to 'result.xml'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

