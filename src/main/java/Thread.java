/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author
 *         33611
 */
public class SecondThread implements Runnable {
    /*
     * Un attribut
     * partagé par
     * tous les threads
     */
    private int counter;

    /**
     * Démarrage de cinq threads basés sur un même objet
     * 
     * @param counter
     */
    public SecondThread(int counter) {
        this.counter = counter;

        // On démarre cinq threads sur le même objet
        for (int i = 0; i < 5; i++) {
            (new Thread(this)).start();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                File f = new File("C:/Users/33611/Desktop/EPSI/Cours/MSPR/JAVA/source" + i + ".html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                // Header
                bw.write("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "</head>");
                // Body + Section Logo
                bw.write("<body style=\"width:80%; display:flex; flex-direction:column; align-items:left;\">\n" +
                        "<section style=\"display:flex; align-items:center; margin:20px;\">\n" +
                        "<div style=\"width:50%; display:flex; margin:20px; flex-direction:column; align-items:left; align-self:flex-start;\">\n");
                bw.write("<img alt=\"logo de l'entreprise\" style=\"width:30%; heigth:15%;\" src=\"logo.png\">");
                // Section Info Agent
                bw.write("<div style=\"display:inline-block;\">\n" +
                        "<ul style=\"list-style:none; text-align:left;\">");
                bw.write("<li>Nom: " + staff.nom + "</li>\n");
                bw.write("<li>Nom: " + staff.prenom + "</li>\n");
                bw.write("<li>Nom: " + staff.misson + "</li>\n");
                // Image
                bw.write("</ul></div></div><img alt=\"exemple de carte d'intentitée\" src=\"");
                // Path du fichie Image
                bw.write("../../../Donnees/" + nomfichier + "/" + nomfichier + ".jpg\"");
                bw.write(" style=\"width:30%; heigth:15%;\">");
                bw.write(
                        "</section><section id=\"materiels\"  \n style=\"width:50%; heigth:50%; padding:20px; margin-left:50px; display:flex; flex-direction:column; align-items:center;\" > \n"
                                +
                                "<label> Affectation des matériels </label>\n<div style=\"display: inline-block;\"><ul style=\"list-style:none; text-align:right;\">");
                // List Materiels
                for (int j = 0; j < listMater.size(); j++) {// chaque matriel du liste
                    String isCheked = String.valueOf(' ');
                    for (String m : staff.affecMater) {// chaque materiel de l'Agent
                        if (listMater.get(j).get(0).equals(m)) {
                            isCheked = "checked";
                        }
                    }
                    bw.write("<li>" + listMater.get(j).get(1));
                    bw.write("<input type=\"checkbox\"" + isCheked + "></li>\n");
                }
                bw.write("</ul></div></section></body></html>");
                bw.close();

                Desktop.getDesktop().browse(f.toURI());

                Thread.sleep(30);
            }
        } catch (InterruptedException exception) {
        } catch (IOException ex) {
            Logger.getLogger(SecondThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}