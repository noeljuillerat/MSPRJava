package main.java;

import java.awt.Desktop;
import java.io.*;

public class testConfig {
    public static void main(String[] args) throws Exception {
        File f = new File("filesHtmls/source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>Blah, Blah!</h1>");
        bw.write("<textarea cols=75 rows=10>");
        for (int ii=0; ii<20; ii++) {
            bw.write("Blah blah..");
        }
        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

        Desktop.getDesktop().browse(f.toURI());
    }

}
