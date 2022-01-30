package main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PrintPasswords {

    public List<Staff> listObjetStaff;

    public PrintPasswords(List<Staff> listObjetStaff){
        this.listObjetStaff = listObjetStaff;
    }

    public void printfichier(){
        File f = new File("docker-nginx/auth/testPasswd");
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        ) {
            for (int i=0; i< listObjetStaff.size(); i++) {
                //user: prenom.nom
                String user = listObjetStaff.get(i).prenom.toLowerCase(Locale.ROOT)+"."+listObjetStaff.get(i).nom.toLowerCase(Locale.ROOT);
                bw.write(user+":"+listObjetStaff.get(i).motDePasse+"\n");
            }
        } catch(IOException e) {
            //log or propagate to the caller
        }
        //Desktop.getDesktop().browse(f.toURI());


    }
}
