package inf353;

public class DictionnaireNaif {
    int N=100;
    char[] t;
    int l;

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
            System.out.println("le mot ne peut etre ajouter");
        }else{
            int lm=l;
            int i=0;
            while(i!=m.length()){
                t[lm]=m.charAt(i);
                lm=lm+1;
                i=i+1;
            }
            l=l+40;
        }
    }

    /**
     * renvoie l'entier associé à m;
     * @param m
     * @return
     */
    public int indiceMot(String m){
        if (m.length()>40) {
            System.out.println("le mot ne peut etre contenu dans t");
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
        }else{
            int k=(i*40);
            while (k!=k+40 && t[k]!='\0') {
                m=m+t[k];
                k=k+1;
            }
            return m;
        }
        // return null;
    }

    public boolean contient(String m){
        if (indiceMot(m)!=-1) {
            return true;
        }
        return false;
    }
    // public boolean contient(String m){
    //     if (m.length()>40) {
    //         System.out.println("le mot ne peut etre contenu dans t");
    //         return false;
    //     }else{
    //         int i=0;
    //         while(i!=N*40){
    //             int k=i;
    //             int lm=0;
    //             while(lm!=m.length() && t[k]==m.charAt(lm)){
    //                 k=k+1;
    //                 lm=lm+1;
    //             }
    //             if (lm==m.length()) {
    //                 // i=N*40;
    //                 return true;
    //             }else{
    //                 i=i+40;
    //             }
    //         }
    //     }
    //     return false;
    // }

        /**
     * renvoie le nombre de mots de m.
     * @return
     */
    public int nbMots(){
        int cpt=0;
        int i=1;
        int k=0;
        while(i<=N){
            if (t[k]!='\0') {
                cpt=cpt+1;
            }
            k=i*40;
            i=i+1;
        }
        return cpt;
    }

    /**public static void main(String[] args) {
        DictionnaireNaif d =new DictionnaireNaif();
        d.ajouterMot("hello");
        d.ajouterMot("bonjour");
        d.ajouterMot("buenos dias");
        // System.out.println(d.t);
        // System.out.println(d.t[40]);
        // System.out.println(d.l);
        System.out.println(d.indiceMot("buenos dias"));
        System.out.println(d.contient("buenos dias"));
        // System.out.println(d.motIndice(2));
        // System.out.println(d.nbMots());
        // System.out.println("ca marche");
    }*/
}
