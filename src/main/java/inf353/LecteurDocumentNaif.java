package inf353;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;



public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
     public String mot;
    public BufferedReader lect;// serve a rassemblait les lignes dans le tableau
    public int caractere;
    public char caractereLu;
    public boolean fin;

    public LecteurDocumentNaif(String chemin) {
        mot = "";
        fin = false;

        try{
            FileReader fileReader = new FileReader(chemin);
            this.lect = new BufferedReader(fileReader);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       

    }

   public void demarrer() throws java.io.IOException {
    
    this.avancer();
}


    

    /**
     * Passage à l'élément suivant
     */
    public void avancer() throws java.io.IOException {
        
        caractere = lect.read();
        caractereLu = (char) caractere;

    
        while (caractere != -1 && this.estSeparateur(caractereLu)) {
            caractere = lect.read();
            caractereLu = (char) caractere;
        }

    
        mot = "";
        while (caractere != -1 && !this.estSeparateur(caractereLu)) {
            mot = mot + caractereLu;
            caractere = lect.read();
            caractereLu = (char) caractere;
        }
        if (caractere == -1) {
            fin = true;
            lect.close();
        }

        
    }

    /**
     * vrai ssi la séquence est épuisée
     * @return
     */
    public boolean finDeSequence() {
        return fin;
    }

    /**
     * renvoie l'élément courant
     * @return
     */
    public String elementCourant() {
        return mot;
    }

  

  public boolean estSeparateur(char c) {
        return (c == ' ' || c == '.' || c == '!' || c == ';' || c == '\'' || c == ':' || c == '?' || c == ','
                || c == '"' || c == '/' || c == ')' || c == '(' || c == '[' || c == ']' || c == '{' || c == '}'|| c=='\n');
    }

    public void prochainEspace() {

        

    }
    public static void main(String[] args) throws java.io.IOException {
          LecteurDocumentNaif lecteur = new LecteurDocumentNaif("C:\\Users\\danie\\Downloads\\inf353_projet\\src\\main\\resources\\sample\\sample\\ats94\\ats_19940327.sgml\\ATS.940327.0002");
          int i=0;
          lecteur.demarrer();
            while (!lecteur.finDeSequence()) {
                System.out.print(" "+lecteur.elementCourant());
                
                lecteur.avancer();
        }

    }
   

}