package inf353;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class DictionnaireNaif {
    public static int N = 100;
    public static char[] t;
    public int l;

    public DictionnaireNaif() {
        t = new char[N * 40];
        l = 0;
    }

    /**
     * é.i: qcq
     * é.f.: le dictionnaire est vide.
     */
    public void vider() {
        l = 0;
    }

    /**
     * é.i.: le dictionnaire contient D0 (un ensemble de N mots).
     * é.f.: si m appartient à D0; le dictionnaire est inchangé
     *       sinon, le dictionnaire contient D0 U {m}
     *
     * @param m
     */
    public void ajouterMot(String m) {
        int lm = l;
        int i = 0;
        while (i != m.length()) {
            t[lm] = m.charAt(i);
            lm = lm + 1;
            i = i + 1;

        }
        t[lm] = '0';
        l = l + 40;

    }

    public void display(char[] t) {
        int i = 0;
        while (i != N) {
            System.out.print(t[i] + " ");
            i++;
        }
    }

    /**
     * renvoie l'entier associé à m;
     * @param m
     * @return
     */
public int indiceMot(String m){
            int i = 0;
            while(i != l){
                String s = motIndice(i/40);
                if(s.length() != m.length()){
                    i = i+40;
                }
                else{
                    int k=0;
                    while(k != s.length() && s.charAt(k)==m.charAt(k)){
                        k = k+1;
                    }
                    if (k == s.length()){
                        return i/40;
                    }
                    else{
                        i=i+40;
                    }
                    
                }
            }
            return -1;
        
    }
    /**
    * renvoie le mot associé à l'entier i;
    * @param i l'indice du mot à renvoyer
    * @return
    */
    public String motIndice(int i) {
        String m = "";
        int k = i * 40;
        while (k != (i + 1) * 40 && t[k] != '0') {
            m = m + t[k];
            k = k + 1;
        }

        return m;

    }
    // return null;

    public boolean contient(String m) {
        int i = 0;
        boolean prime = false;
        while (i != nbMots()) {
            String s = motIndice(i);
            if (s.length() != m.length()) {
                prime = false||prime;
            } else {
                int j = 0;
                while (j != s.length() && s.charAt(j) == m.charAt(j)) {
                    j = j + 1;
                }
                prime = (prime || j == s.length());
            }
            i = i + 1;
        }
        return prime;

    }

    /**
    * renvoie le nombre de mots de m.
    * @return
    */
    public int nbMots() {
        int i = 0;
        int n = 0;
        while (i != l) {
            if (t[i] == '0') {
                n = n + 1;
            }
            i = i + 1;
        }
        return n;
    }

    public boolean contientPrefixe(String prefixe) {

        int i = 0;
        while (i < N * 40) {
            int k = i;
            int j = 0;
            while (j < prefixe.length() && t[k] == prefixe.charAt(j)) {
                k = k + 1;
                j = j + 1;
            }
            if (j == prefixe.length()) {
                return true;
            } else {
                i = i + 40;
            }
        }
        return false;
    }
    public void sauver(String monFichier) throws IOException{
    int i =0;
    try{
        File file = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\inf353\\ressources\\ " + monFichier+ ".txt");
        if(file.createNewFile()){
            FileWriter ecrire = new FileWriter(file);
            while (i!=nbMots()) {
                ecrire.write(motIndice(i) + " ");
                i =  i+1;
            }
            ecrire.write("\n");
            ecrire.close();
        }
    }
    catch(IOException e){
        System.out.println("an error");
        e.getStackTrace();
    }
    }

   /**  public static void main(String[] args) throws IOException{
        DictionnaireNaif d = new DictionnaireNaif();
        DictionnaireNaif d1 = new DictionnaireNaif();
        d.ajouterMot("hello");
        d.ajouterMot("hellhdshbsjh");
        d.ajouterMot("hell");
        d.sauver("doss");
       // System.out.println("le tableau apres le remplissage:");
        //d.display(t);
       // System.out.println();
        // System.out.println(d.t);
        // System.out.println(d.t[40]);
        // System.out.println(d.l);
        //System.out.println(d.indiceMot("hello"));
       // System.out.println();
        //System.out.println(d.indiceMot("hell"));
        //System.out.println(d.contient("hell"));
        // d.vider();
        // System.out.println("\nle tableau apres le vidage:");
        // d.display(t);
        //System.out.println();
        //System.out.println(d.contientPrefixe("zin"));
       // System.out.println(d.motIndice(2));
        //System.out.println();
        //System.out.println(d.nbMots());
        // System.out.println("ca marche");
    }*/
}

