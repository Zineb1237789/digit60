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
        if (!contient(mot)) {
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

