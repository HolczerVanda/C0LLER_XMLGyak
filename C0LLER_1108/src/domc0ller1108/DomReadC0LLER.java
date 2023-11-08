package domc0ller1108;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class DomReadC0LLER {
    public static void main(String[] args) {

        try {
        	File xml = new File("C:\\Users\\TEMP.IIT.005\\Desktop\\C0LLER_1108\\C0LLER_kurzusfelvetel.xml");
            
            try (BufferedReader br = new BufferedReader(new FileReader(xml))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}