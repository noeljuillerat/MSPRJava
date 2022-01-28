package main.java;

import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class testConfig {
    public static void main(String[] args) throws Exception {
        List<String> listStaff = new ArrayList<>();
        listStaff = readFileGetStaff("Donnees/staff.txt");
        getStaffData(listStaff);
        writePage();

    }

    public static void getStaffData(List<String> listStaff) throws IOException {
        List<Staff> listObjetStaff = new ArrayList<>();

        if(listStaff != null) {
            for (int i=0; i<listStaff.size(); i++){
                //get all data of a staff
                String staffNom = listStaff.get(i);
                List<String> listInfo = readFileGetStaff("Donnees/" + staffNom + "/" + staffNom + ".txt");
                System.out.println("Donnees/" + staffNom + "/" + staffNom + ".txt");
                Staff stafObjet = new Staff();
                stafObjet.nom = listInfo.get(0);
                stafObjet.prenom = listInfo.get(1);
                stafObjet.misson = listInfo.get(2);
                stafObjet.motDePasse= listInfo.get(3);
                stafObjet.affecMater = new ArrayList<>();
                for (int j=5; j<listInfo.size(); j++){
                    stafObjet.affecMater.add(listInfo.get(j));
                    //System.out.println(listInfo.get(j));
                }
                listObjetStaff.add(stafObjet);
            }
        }

    }

    public static void writePage () throws IOException {
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
        //Desktop.getDesktop().browse(f.toURI());
    }

    public static List<String> readFileGetStaff (String path) throws IOException {
        Reader reader = null;
        List<String> listStaff = new ArrayList<>();
        try {
            reader = new FileReader(path);
            // Create a BufferedReader with buffer array size of 16384 (32786 bytes = 32 KB).
            BufferedReader br = new BufferedReader(reader, 16384);
            for(String line=br.readLine(); line!=null; line=br.readLine())
            {
                listStaff.add(line);
                System.out.println(line);
            }
            String line = null;
            while((line=br.readLine())!=null)
            {
                listStaff.add(line);
                System.out.println(line);
            }
            //br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listStaff;
    }
}
