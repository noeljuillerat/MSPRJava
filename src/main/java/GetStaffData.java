package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.Main.readFileGetStaff;

public class GetStaffData {

    public List<String> listStaff;

    public GetStaffData(List<String> listStaff){
        this.listStaff = listStaff;
    }
    public List<Staff> getData() throws IOException {
        List<Staff> listObjetStaff = new ArrayList<>();

        if(listStaff != null) {
            for (int i=0; i<listStaff.size(); i++){
                //get all data of a staff
                String staffNom = listStaff.get(i);
                List<String> listInfo = readFileGetStaff("Donnees/" + staffNom + "/" + staffNom + ".txt");
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
        return  listObjetStaff;
    }

}

