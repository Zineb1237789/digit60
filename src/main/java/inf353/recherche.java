package inf353;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import inf353.*;

public class recherche {
    public CelluleEntier tete = null;
    public MatriceCreuse M;
    public DictionnaireH H, C;
    public String[] t = new String[50000000];
    public java.util.List<String> liste = new java.util.ArrayList<>();
    // public float[] tabPertinence;

    public recherche() throws IOException {
        try {
            M = new MatriceCreuse("C:\\353_projet\\french\\matriceIndexee.txt");
            H = new DictionnaireH("C:\\353_projet\\french\\dictionnaire.txt");

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    // remplissage du tableau qui contient les noms des fichiers
    public void rempliTabNomsFich() throws IOException {

        try (FileReader fichier = new FileReader("C:\\353_projet\\french\\listeDocuments.txt");
                BufferedReader bw = new BufferedReader(fichier)) {

            // Lire la première ligne du fichier
            String ligne = bw.readLine();

            // Vérifier si la ligne n'est pas nulle (fin du fichier)
            while (ligne != null) {
                // Diviser la ligne en mots en utilisant l'espace comme délimiteur
                String[] mots = ligne.split(" ");

                // Ajouter chaque mot à la liste
                for (String mot : mots) {
                    liste.add(mot);
                }

                // Lire la ligne suivante
                ligne = bw.readLine();
            }

        }
    }

    public void lireRequete(String cheminFichier) throws IOException {
        // Utiliser le lecteur de document naif
        LecteurDocumentNaif<String> lecteur = new LecteurDocumentNaif<String>(cheminFichier);

        // Démarrer la lecture
        lecteur.demarrer();
        int i = 0;

        // Lire les éléments du document et les ajouter au dictionnaire
        while (!lecteur.finDeSequence()) {
            String mot = lecteur.elementCourant();
            t[i] = mot;
            // System.out.println(t[i]);
            i = i + 1;
            lecteur.avancer();
        }

    }

    public void calculPerti() {
        int i = 0;
        int j = 0;
        int nd = 0;
        int nt = 0;
        float somme = 0;
        int indice;
        int N = this.M.nbdoc;
        CelluleEntier cc = tete;
        CelluleEntier prec = null;
        while (i != t.length)// parcours du tableau de la requete
        {

            // System.out.println(this.H.contient(t[i]));
            if (this.H.contient(t[i])) {

                indice = this.H.indiceMot(t[i]);

                // parcours de la matrice
                while (nd != N)// parcours des documents
                {
                    nt = 0;
                    while (nt != this.M.nbCellule(nd))// parcours de termes dans chaque document
                    {
                        if (indice == this.M.valIndice(nd, nt))
                            {
                                somme = somme + M.val(nd, nt);// calcul de la pertinence
                            }
                            //System.out.println(M.val(nd,nt));
                        nt++;
                    }
                    
                    ajouter(nd, somme);
                    somme = 0;
                    nd++;

                }

            }
            i++;
        }
    }

    public void ajouter(int indice, float pertinence) {
        CelluleEntier p = new CelluleEntier(indice, pertinence);
        CelluleEntier l = tete;
        if (tete == null) {
            tete = p;
        } else {
            while (l.suiv != null) {
                l = l.suiv;
            }
            l.suiv = p;
        }

    }

    public void display() {
        CelluleEntier tmp = tete;
        while (tmp != null) {
            System.out.println("la pertinence du document numero: " + tmp.indice + "est: " + tmp.pertinence);
            tmp = tmp.suiv;
        }
    }

    public void triEtAffichagePerti() {// dans cette methode on va trier le tableau de partinence et afficher
        int d = 0;
        if (tete == null) {
            System.out.println("the linked list is empty.");
        } else {
            CelluleEntier tmp = tete;
            CelluleEntier change = new CelluleEntier();
            int x = 0;
            do {
                tmp = tete;
                x = 0;
                while (tmp.suiv != null) {
                    if (tmp.pertinence < tmp.suiv.pertinence) {
                        change.indice = tmp.indice;
                        change.pertinence = tmp.pertinence;
                        // second swap
                        tmp.indice = tmp.suiv.indice;
                        tmp.pertinence = tmp.suiv.pertinence;
                        // third swap
                        tmp.suiv.indice = change.indice;
                        tmp.suiv.pertinence = change.pertinence;
                        x = 1;
                    }
                    tmp = tmp.suiv;
                }
            } while (x == 1);

        }
        CelluleEntier current = tete;
        while (current != null && d != 500) {
            System.out.println(liste.get(current.indice));
            current = current.suiv;
            d++;
        }

    }

    public static void main(String[] args) throws IOException {

        recherche R = new recherche();
        R.lireRequete("C:\\353_projet\\french\\zineb.txt");
        R.rempliTabNomsFich();
        R.calculPerti();
        // R.display();
        R.triEtAffichagePerti();
        //R.display();
    }
}
