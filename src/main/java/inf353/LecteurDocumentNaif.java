package inf353;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class LecteurDocumentNaif<T>{
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

    public boolean estAlphanumerique(char ch){
        return (ch>47 && ch<58) || (ch>64 && ch<91) || (ch>96 && ch<123);
        // return (ch>47 && ch<58) || (ch>64 && ch<91) || (ch>96 && ch<122) || (ch>191 && ch<220) || (ch>223 && ch<252);
    }
    public char minuscule(char ch){
        // if((ch>64 && ch<91)|| (ch>191 && ch<220)){
        if((ch>64 && ch<91)){
            return (char) (ch + 32);
        }else{
            return (char) ch;
        }
    }

public boolean estSeparateur(char c) {
        return (c == ' ' || c == '.' || c == '!' || c == ';' || c == '\'' || c == ':' || c == '?' || c == ','
                || c == '"' || c == '/' || c == ')' || c == '(' || c == '[' || c == ']' || c == '{' || c == '}'|| c=='\n'||c=='_'||c=='-');
                
    }

    
    // public static void main(String[] args) throws java.io.IOException {
    //       LecteurDocumentNaif lecteur = new LecteurDocumentNaif(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\LEMONDE94-003500-19940228-utf8");
    //       lecteur.demarrer();
    //         while (!lecteur.finDeSequence()) {
    //             System.out.print(" "+lecteur.elementCourant());
                
    //             lecteur.avancer();
    //     }

    // }


}
