package inf353;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;



public class LecteurDocumentNaif  {
    public String mot;
    public BufferedReader lect;
    public int caractere;
    public char caractereLu;
    public boolean fin;

    public LecteurDocumentNaif(String chemin) {
        mot = "";
        fin = false;

        try {
           
            FileInputStream fileInputStream = new FileInputStream(chemin);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            
            this.lect = new BufferedReader(inputStreamReader);

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
                || c == '"' || c == '/' || c == ')' || c == '(' || c == '[' || c == ']' || c == '{' || c == '}'|| c=='\n'||c=='_'||c=='-');
    }

    
    public static void main(String[] args) throws java.io.IOException {
          LecteurDocumentNaif lecteur = new LecteurDocumentNaif("C:\\Users\\danie\\Downloads\\inf353_projet\\digit60\\src\\main\\java\\inf353\\ressources\\sample\\sample\\lemonde94\\19940111\\LEMONDE94-001098-19940111");
        
          lecteur.demarrer();
            while (!lecteur.finDeSequence()) {
                System.out.print(" "+lecteur.elementCourant());
                
                lecteur.avancer();
        }

    }
   

}