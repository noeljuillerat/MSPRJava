package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //get la liste des agents
        List<String> listStaff = new ArrayList<>();
        listStaff = readFileGetStaff("Donnees/staff.txt");
        //générer le page d'index
        printIndexPage(listStaff);
        //get la liste des matériels
        List<List<String>> listMater = new ArrayList<>();
        listMater = readFileGetListMater("Donnees/liste.txt");
        //get les informations des agents
        List<Staff> listObjetStaff =  new ArrayList<>();
        GetStaffData getStaffData = new GetStaffData(listStaff);
        listObjetStaff = getStaffData.getData();
        //générer les pages personnels des agents
        for(Staff staff : listObjetStaff){
            PrintStaffPage printStaffPage = new PrintStaffPage(staff,listMater);
            printStaffPage.writePage();
        }
        //générer le fichier des passwords
        PrintPasswords printPasswords = new PrintPasswords(listObjetStaff);
        printPasswords.printfichier();
    }

    public static List<List<String>> readFileGetListMater(String path) throws IOException {
        Reader reader = null;
        List<List<String>> listMater = new ArrayList<>();
        try {
            reader = new FileReader(path);
            // Create a BufferedReader with buffer array size of 16384 (32786 bytes = 32 KB).
            BufferedReader br = new BufferedReader(reader, 16384);
            int n = 0;
            for(String line=br.readLine(); line!=null; line=br.readLine())
            {
                List<String> listUnSortMater = new ArrayList<>();

                String[] data = line.split("    ");//1 tab = 4 spaces
                for(int i=0; i<data.length; i++){
                    listUnSortMater.add(data[i]);
                }
                listMater.add(listUnSortMater);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listMater;
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
            }
            //TODO: Doublons de codes pour sauter une ligne vide dans le fichier en txt
            String line = null;
            while((line=br.readLine())!=null)
            {
                listStaff.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listStaff;
    }

    public static void printIndexPage (List<String> listStaff) throws IOException {
        File f = new File("docker-nginx/data/index.html");
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        ) {
            //Header
            bw.write("<body style=\"width:80%;\">\n" +
                    "<div style=\"width:50%; display:flex; margin:20px; flex-direction:column;align-items:center;\">\n");
            //Logo de l'entreprise
            bw.write("<img alt=\"logo de l'entreprise\" style=\"width:30%; heigth:15%;\" src=\"logo.png\">");
            //Section du liste des agents
            bw.write("<div style=\"display:inline-block;\">\n" +
                    "<ul style=\"list-style:none; text-align:center;\">");
            for (int i=0; i<listStaff.size(); i++) {
                bw.write("<li><a href=\""+listStaff.get(i));
                bw.write("/"+ listStaff.get(i)+".html\">");
                // TODO: Affichage du Nom et Prenom du l'Argent
                bw.write(listStaff.get(i));
                bw.write("</a></li>");
            }
            bw.write("</ul></div></div></body></html>");
        } catch(IOException e) {
            //log or propagate to the caller
        }
        //Desktop.getDesktop().browse(f.toURI());
    }
}
