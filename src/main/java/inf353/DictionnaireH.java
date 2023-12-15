package inf353;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DictionnaireH implements Dictionnaire{
    public int l;
    public int N = 5000000;
    public String[] t;

    public DictionnaireH(int n) {
        this.N = n;
        t = new String[n];
        l = 0;
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
        

        int n = (m.hashCode() & Integer.MAX_VALUE) % N;
        if (t[n] == null) {
            return false;
        } else if (t[n].equals(m)) {
            
            return true;
        } else {
            while (n != N && t[n]!=null && !t[n].equals(m)) {
                n = n + 1;
            }
            return n != N;
            
        }

    }

    public void sauver(String monFichier) throws IOException{
    int i =0;
    try{
        File file = new File( monFichier);
        if(file.createNewFile()){
            FileWriter ecrire = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(ecrire);
            while (i!=nbMots()) {
                if(t[i] != null){
                    //bw.write(motIndice(i));
                    bw.write(motIndice(i) + " ");
                }
                //bw.write(motIndice(i) + " ");
                i =  i+1;
            }
            ecrire.write("\n");
            ecrire.close();
        }
    }
    catch(IOException e){
        System.out.println(e.getMessage());
    }

    // public static void main(String[] args) {
    //     DictionnaireH hh = new DictionnaireH();
    //     hh.ajouterMot("hjfj");
    //     hh.ajouterMot("hjfj");
    //     hh.ajouterMot("hjfj");
    //     hh.ajouterMot("hjfjuefuezugfezfuef");
    //     hh.ajouterMot("hjfjherfhehhe");
    //     hh.ajouterMot("hjfeefef");
    //     System.out.println(hh.contient("hjfj"));
    //     System.out.println(hh.contient("h"));
    //     System.out.println(hh.contient("hjfjuefuezugfezfuef"));
    //     System.out.println(hh.nbMots());
    //     System.out.println(hh.indiceMot("hjfj"));
    //     System.out.println(hh.motIndice(hh.indiceMot("hjfj")));
    // }
}
}
