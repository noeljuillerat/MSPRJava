package main.java;

import java.awt.Desktop;
import java.io.*;

public class testConfig {
    public static void main(String[] args) throws Exception {
        readFile();
        writePage();

    }

    public  static void writePage () throws IOException {
        File f = new File("docker-nginx/data/source.html");
        try (
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        ) {
            bw.write("<html><body><h1>Blah, Blah!</h1>");
            bw.write("<textarea cols=75 rows=10>");
            for (int ii=0; ii<20; ii++) {
                bw.write("Blah blah..");
            }
            bw.write("</textarea>");
            bw.write("</body></html>");
        } catch(IOException e) {
            //log or propagate to the caller
        }
        Desktop.getDesktop().browse(f.toURI());

    }

    public  static void readFile () throws IOException {
        Reader reader = null;
        try {
            reader = new FileReader("staff.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create a BufferedReader with buffer array size of 16384 (32786 bytes = 32 KB).
        BufferedReader br = new BufferedReader(reader, 16384);

        String line = null;

        while((line = br.readLine())!= null) {
            System.out.println(line);
        }
        br.close();
    }
}
