package inf353;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import inf353.LecteurDocumentNaif;
import java.lang.Math;

public class DictionnaireH implements Dictionnaire{
    public int l;
    public int N = 5000000;
    public String[] t;
    public LecteurDocumentNaif lect;

    public DictionnaireH(String chemin) throws IOException {
        l = 0;
        t = new String[N];
        lect = new LecteurDocumentNaif(chemin);
        lect.demarrer();
        while(!lect.finDeSequence()){
            ajouterMot(lect.elementCourant());
            lect.avancer();
        }
    }
    public DictionnaireH(){
        t = new String[N];
        l = 0;
    }

    public void vider() {
        l = 0;

    }


    public void ajouterMot(String mot) {

        int n = (mot.hashCode() & Integer.MAX_VALUE) % N;
        if (!contient(mot) && !motInterdit(mot) ) {
            if (t[n] == null) {
                t[n] = mot;
            } else {
                while (t[n] != null) {
                    n = n + 1;
                }
                t[n] = mot;
            }
            l = l + 1;
        }
    }

    public int indiceMot(String mot) {
        int n = (mot.hashCode() & Integer.MAX_VALUE) % N;
        while (n != N  && t[n]!=null && !t[n].equals(mot) ) {
            n = n + 1;
        }
        return n;
    }
        
    

    public int nbMots() {
        return l;
    }

    public String motIndice(int i) {
          return t[i];
        
    }

    public boolean motInterdit(String m){
        return (m.equals("leur") ||m.equals("là") ||m.equals("ma") ||m.equals("maintenant") ||m.equals("mais") ||m.equals("mais") ||m.equals("mine") ||m.equals("moins") ||m.equals("mon") ||m.equals("mot") ||m.equals("même") ||m.equals("ni") ||m.equals("")    ||m.equals("elle") ||m.equals("elles") ||m.equals("en") ||m.equals("encore") ||m.equals("essai") ||m.equals("est") ||m.equals("et") ||m.equals("eu") ||m.equals("fait") ||m.equals("faites") ||m.equals("fois") ||m.equals("font") ||m.equals("force") ||m.equals("hors") ||m.equals("haut") ||m.equals("ici") ||m.equals("il") ||m.equals("ils") ||m.equals("je") ||m.equals("juste") ||m.equals("la") ||m.equals("le") ||m.equals("les") ||m.equals("alors") ||m.equals("au") ||m.equals("aucuns") ||m.equals("aussi") ||m.equals("autre") ||m.equals("avant") ||m.equals("ave") ||m.equals("avoir") ||m.equals("bon") ||m.equals("car") ||m.equals("ce") ||m.equals("cela") ||m.equals("ces") ||m.equals("ceux") ||m.equals("chaque") ||m.equals("ci") ||m.equals("comme") ||m.equals("comment") ||m.equals("d") ||m.equals("des") ||m.equals("du") ||m.equals("dedans") ||m.equals("dehors") ||m.equals("depuis") ||m.equals("deux") ||m.equals("devrait") ||m.equals("doit") ||m.equals("donc") ||m.equals("dos") ||m.equals("droite")|| m.equals("début") ||m.equals("nommés") || m.equals("notre") || m.equals("nous") || m.equals("nouveaux")
                || m.equals("ou") || m.equals("où") || m.equals("par") || m.equals("parce")
                || m.equals("parole") || m.equals("pas") || m.equals("personnes") || m.equals("peut")
                || m.equals("peu") || m.equals("pièce") || m.equals("plupart") || m.equals("pour")
                || m.equals("pourquoi") || m.equals("quand") || m.equals("que") || m.equals("quel")
                || m.equals("quelle") || m.equals("quelles") || m.equals("quels") || m.equals("qui")
                || m.equals("sa") || m.equals("sans") || m.equals("ses") || m.equals("seulement")
                || m.equals("si") || m.equals("sien") || m.equals("son") || m.equals("sont")
                || m.equals("sous") || m.equals("soyez") || m.equals("sujet") || m.equals("ta")
                || m.equals("tandis") || m.equals("tellement") || m.equals("tels") || m.equals("tes")
                || m.equals("ton") || m.equals("tous") || m.equals("tout") || m.equals("trop")
                || m.equals("très") || m.equals("tu") || m.equals("valeur") || m.equals("voie")
                || m.equals("voient") || m.equals("vont") || m.equals("votre") || m.equals("vous")
                || m.equals("vu") || m.equals("ça") || m.equals("étaient") || m.equals("état")
                || m.equals("étions") || m.equals("été") || m.equals("être"));
    }

    public boolean contient(String m) {
        if (m != null) {
            int n = (m.hashCode() & Integer.MAX_VALUE) % N;
            if (t[n] == null) {
                return false;
            } else if (t[n].equals(m)) {

                return true;
            } else {
                while (n != N && t[n] != null && !t[n].equals(m)) {
                    n = n + 1;
                }
                return n != N;

            }
    
        } else {
            return false;
        }

    }
        
    public void sauver(String monFichier) throws IOException {
        try {
            File file = new File(monFichier);
            if (file.createNewFile()) {
                FileWriter ecrire = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(ecrire);
                for (int i = 0; i < N; i++) {
                    if (t[i] != null) {
                        bw.write(motIndice(i) + " ");
                    }
                }
                bw.write("\n");
                bw.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

public static void main(String[] args)throws IOException {
    DictionnaireH hh = new DictionnaireH();
    hh.ajouterMot("hjfj");
    hh.ajouterMot("hjfj");
    hh.ajouterMot("hjfj");
    hh.ajouterMot("hjfjuefuezugfezfuef");
    hh.ajouterMot("hjfjherfhehhe");
    hh.ajouterMot("hjfeefef");
    hh.sauver("dicto.txt");
    System.out.println(hh.contient("hjfj"));
    System.out.println(hh.contient("h"));
    System.out.println(hh.contient("hjfjuefuezugfezfuef"));
    System.out.println(hh.nbMots());
    System.out.println(hh.indiceMot("hjfj"));
    System.out.println(hh.motIndice(hh.indiceMot("hjfj")));
}

}

