package inf353;
import java.io.*;

public class DictionnaireNaif {
    public static int N=100;
    public static char[] t;
    public int l;

    public DictionnaireNaif() {
        t = new char[N*40];
        l=0;
    }

    /**
     * é.i: qcq
     * é.f.: le dictionnaire est vide.
     */
    public void vider(){
        t = new char[N*40];
        l=0;
    }
    
    /**
     * é.i.: le dictionnaire contient D0 (un ensemble de N mots).
     * é.f.: si m appartient à D0; le dictionnaire est inchangé
     *       sinon, le dictionnaire contient D0 U {m}
     *
     * @param m
     */
    public void ajouterMot(String m){
        if (m.length()>40) {
            System.out.println("le mot ne peut pas etre ajouter");
        }
        else{
            int lm=l;
            int i=0;
            while(i!=m.length()){
                t[lm]=m.charAt(i);
                lm=lm+1;
                i=i+1;
                
            }
            t[lm]='0';
            l=l+40;
            
        }
    }
    public void display(char[] t)
    {
        int i=0;
      while(i!=N)
      {
        System.out.print(t[i]+" ");
        i++;
      }
    }
    /**
     * renvoie l'entier associé à m;
     * @param m
     * @return
     */
     public int indiceMot(String m){
	 	 if (contient(m)==false) {
             return -1;
         }else{
             int i=0;
             while(i!=N*40){
                 int k=i;
                 int lm=0;
                 while(lm!=m.length() && t[k]==m.charAt(lm)){
                     k=k+1;
                     lm=lm+1;
                 }
                 if (lm==m.length()) {
                     // i=N*40;
                     return i/40;
                 }else{
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
     public String motIndice(int i){
         String m="";
         if (i>=N) {
             System.out.println("l'indice ne peut etre contenu dans t");
             return null;
         } else {
             
             int k=(i*40);
             while (k != k + 40 && t[k] != '0') {
                 m = m + t[k];
                 k = k + 1;
             }
             return m;

         }
         // return null;
     }

    public boolean contient(String m){
        if (m.length()>40) {
            System.out.println("le mot ne peut etre contenu dans t");
            return false;
        } else {
            int i=0;
            while(i!=N*40){
                int k=i;
                int lm=0;
                while(lm!=m.length() && t[k]==m.charAt(lm)){
                    k=k+1;
                    lm=lm+1;
                }
                if (lm==m.length()) {
                    // i=N*40;
                    return true;
                }else{
                    i=i+40;
                }
            }
        }
        return false;
}

        /**
     * renvoie le nombre de mots de m.
     * @return
     */
    public int nbMots(){
        int i=0;
        int n=0;
        while(i!=N)
        {
            if(t[i]=='0')
            {
             n=n+1;
            }
            i=i+1;
        }
        return n;
    }

    public boolean contientPrefixe (String prefixe){
        if (prefixe.length() > 40) {
            return false;
        }
        int i = 0;
        while (i < N*40){
            int k=i;
            int j = 0;
            while(j<prefixe.length() && t[k] == prefixe.charAt(j)){
                k = k+1;
                j = j+1;
            }
            if (j == prefixe.length()){
                return true;
            }
            else{
                i = i+40;
            }
        }
        return false;
    }

    public String plusLongPrefixe() {
        if (l == 0) {     
            return ""; 
        }
        int i = 0;
        char[] prefixe = new char[40];
        while(i < 40){
            char c = t[i];
            int j = 1;
            while(j < N  && t[i + j*40] == c){ //C'est pour vérifier que le caractère est le même pour tous les mots
                j = j+1;
            }
            if(j==N){
                prefixe[i] = c;
            }
            else{
                break;//arrete la recherche du prefixe si ce n'est pas égal
            }
            i = i+1;
        }
        return new String(prefixe); //Permet de renvoyer le tableau de caractère prefixe sous forme de string
    }

   public static void main(String[] args) {
        DictionnaireNaif d =new DictionnaireNaif();
        d.ajouterMot("hello");
        d.ajouterMot("hellhdshbsjh");
        d.ajouterMot("hello");
        System.out.println("le tableau apres le remplissage:");
        d.display(t);
        System.out.println();
        // System.out.println(d.t);
        // System.out.println(d.t[40]);
        // System.out.println(d.l);
         //System.out.println(d.indiceMot("hello"));
         System.out.println(d.contient("hell"));
        // d.vider();
        // System.out.println("\nle tableau apres le vidage:");
        // d.display(t);
        System.out.println();
        System.out.println(d.contientPrefixe("zin"));
        System.out.println(d.motIndice(2));
         System.out.println();
        System.out.println(d.nbMots());
        // System.out.println("ca marche");
}

}