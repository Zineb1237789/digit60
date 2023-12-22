package inf353;

    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import org.tartarus.snowball.ext.frenchStemmer;
    import java.io.*;
    //import org.tartarus.snowball.ext.EnglishStemmer;
    //import org.tartarus.snowball.SnowballStemmer;
    
    
public class LecteurDocumentNaif<T>{
   
    //public class LecteurDocumentNaif{
        public String mot;
        public BufferedReader lect;
        public int caractere;
        public char caractereLu;
        public boolean fin;
        public frenchStemmer stemmer;
    
        public LecteurDocumentNaif(String chemin) {
            this.stemmer = new frenchStemmer();
            //SnowballStemmer stemmer = new englishStemmer();
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
    
        public String stem(String mot) {
            stemmer.setCurrent(mot);
            stemmer.stem();    
        return stemmer.getCurrent();
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
           String s = "";
           int i =0;
           while(s.length() != mot.length()){
               s = s + minuscule(mot.charAt(i));
               i = i+1;
           }
            return s;
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
    
        
        
        public static void main(String[] args) throws java.io.IOException {
            LecteurDocumentNaif lecteur = new LecteurDocumentNaif("H:\\353_projet\\french\\lemonde94\\19940101\\LEMONDE94-000001-19940101");
            try{
                 lecteur.demarrer();
                 while (!lecteur.finDeSequence()) {
                     System.out.println(" "+lecteur.elementCourant());          
                     lecteur.avancer();
             }
             } catch (IOException e) {
                 e.printStackTrace();
             }
     }
    }