package inf353;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class MatriceCreuse implements MatriceIndex {
    /**
     * Sauvegarde de la matrice d'occurence dans le fichier nomDeFichier. Le format est libre,
     * mais doit privilégier la vitesse de chargement et la compacité (taille du fichier).
     *
     * @param nomDeFichier 
     */
    public CelluleEntier[] matrice;
    public int nbdoc = 95000;
    public int nbdocCourant;
    public CelluleEntier tete;
    String nameFile;

    public MatriceCreuse() {
        this.matrice = new CelluleEntier[nbdoc];
        tete = null;
    }

    

    public MatriceCreuse(String nomDeFichier) throws IOException {
        this.matrice = new CelluleEntier[nbdoc];
        this.tete = null;
        this.nameFile = nomDeFichier;
        try (BufferedReader lect = new BufferedReader(new FileReader(/*System.getProperty("user.dir") +"\\src\\main\\java\\inf353\\ressources\\" +*/ nameFile /*+ ".txt"*/))) {
            String ligne;
            nbdocCourant = 0;
            while ((ligne = lect.readLine()) != null && nbdocCourant < nbdoc) {
                String[] cellules = ligne.split(" ");
                for (String cellule : cellules) {
                    String[] separe = cellule.split("[()]");
                    if(separe.length >= 2){
                        int occurence = valNombre(separe[1]);
                        int indice = valNombre(separe[0]);
                        this.affecte(nbdocCourant, indice, occurence);
                    }
                }
                    nbdocCourant = nbdocCourant + 1;
            }
            lect.close();
    } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    
    
    
    public void sauver(String nomDeFichier) throws FileNotFoundException {
        File file = new File(
                /*System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" +*/ nomDeFichier /*+ ".txt"*/);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer1 = new FileWriter(file);
                    BufferedWriter bWriter = new BufferedWriter(writer1)) {
                for (int i = 0; i < nbdoc; i++) {
                    CelluleEntier current = matrice[i];
                    while (current != null) {
                        bWriter.write(current.indice + "(" + current.occurence + ") ");
                        current = current.suiv;
                    }
                    bWriter.write('\n');
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * retourne le nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @return       le nombre d'occurences du terme dans le document
     */
    public int val(int ndoc, int i) {
        tete = matrice[ndoc];
        CelluleEntier cc = tete;
        while (cc != null && cc.indice != i) {
            cc = cc.suiv;

        }
        if (cc == null) {
            return 0;
        } else {
            return cc.occurence;
        }
    }

    /**
     * Ajoute 1 au nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     */
    public void incremente(int ndoc, int i) {
        CelluleEntier current = matrice[ndoc];
        CelluleEntier fictif = new CelluleEntier();
        fictif.suiv = current;
        CelluleEntier cc = current;
        while (cc != null && cc.indice != i) {
            cc = cc.suiv;
        }
        if (cc == null) {
            fictif.suiv = new CelluleEntier(i, 1, current);
            matrice[ndoc] = fictif.suiv;
        } else {
            cc.occurence = cc.occurence + 1;
        }
    }

    /**
     * affecte à val le numéro d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @param val    la nouvelle valeur du nombre d'occurence
     */
    public void affecte(int ndoc, int i, int val) {
        if (val != 0) {
            CelluleEntier current = matrice[ndoc];
            CelluleEntier fictif = new CelluleEntier();
            fictif.suiv = current;
            CelluleEntier cc = current;
            while (cc != null && cc.indice != i) {
                cc = cc.suiv;
            }
            if (cc == null) {
                fictif.suiv = new CelluleEntier(i, val, current);
                matrice[ndoc] = fictif.suiv;
            } else {
                cc.occurence = val;
            }
        }
    }

    public int valChiffre(char c) {
        return c - '0';
    }

    private int valNombre(String s) {
        int nombre = 0;
        if (s != null) {
            int i = 0;
            while (i < s.length()) {
                nombre = nombre * 10 + valChiffre(s.charAt(i)); // ex: valNombre("37") = 3*10 + 7
                i++;
            }
        }
        return nombre;
    }

    // public static void main(String[] args) throws IOException {
    //     MatriceCreuse matrice = new MatriceCreuse();
    //     matrice.affecte(0, 5, 7);
    //     matrice.incremente(0, 0);
    //     matrice.affecte(4, 4, 4);
    //     matrice.affecte(4, 4, 4);
    //     matrice.affecte(4, 8, 4);
    //     matrice.affecte(7, 8, 4);
    //     System.out.println(matrice.val(4, 5));
    //     System.out.println(matrice.val(0, 5));
    //     System.out.println(matrice.val(0, 0));
    //     matrice.incremente(4, 10);
    //     matrice.sauver("dos");
    //     MatriceCreuse mat = new MatriceCreuse("dos");
    //     mat.sauver("dosbis");

    // }

}
