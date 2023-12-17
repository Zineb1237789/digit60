package inf353;
import java.lang.Math;
import inf353.*;

public class recherche {
    public int nbDoc = 0;
    public int nbTerm = 0;
    public CelluleEntier tete;
    public MatriceCreuse M;
    public DictionnaireNaifH requeteDict;
    public DictionnaireNaifH lecteurDicTerm;
    public DictionnaireNaifH lecteurDicDocs;

    // public void lirefichiers() {
    //     LecteurDocumentNaif l = new LecteurDocumentNaif<>();
    //     l.demarrer();
    //     while (!l.finDeSequence()) {
    //         lecteurDicDocs.ajouterMot(l.elementCourant());
    //         l.avancer();
    //     }
    // }

    // public void lireTermes() {
    //     LecteurDocumentNaif l = new LecteurDocumentNaif<>();
    //     l.demarrer();
    //     while (!l.finDeSequence()) {
    //         lecteurDicTerm.ajouterMot(l.elementCourant());
    //         l.avancer();
    //     }
    // }

    // public void LireRequete(String path) {
    //   LecteurDocumentNaif l = new LecteurDocumentNaif<>(path);
    //     l.demarrer();
    //     while (!l.finDeSequence()) {
    //         requeteDict.ajouterMot(l.elementCourant());
    //         l.avancer();
    //     }
    // }
    // public void LectureMatrice()
    // {
    //    LecteurDocumentNaif l = new LecteurDocumentNaif<>();
    //     l.demarrer();
    //     while (!l.finDeSequence()) {
    //         M.ajouterMot(l.elementCourant());
    //         l.avancer();
    //     }
    // }
    public void calculPerti() {
        int i = 0;
        int n = 0;
        float somme = 0;

        CelluleEntier cc = tete;
        CelluleEntier prec = null;
        // la pertinence de chaque document
        while (i != lecteurDicoDocs.nbMots()) {
            int j = 0;
            while (j != requeteDict.nbMots()) {
                if (lecteurDicoTerm.contient(requeteDict.motIndice(i))) {
                    somme = (float) (somme + 1+ Math.log(M.val(i, lecteurDicoTerm.indiceMot(requeteDict.motIndice(i)))));
                }
                j++;
            }
            while (cc != null) {
                prec = cc;
                cc = cc.suiv;
                if (prec == null)
                    tete = new CelluleEntier(i, somme, tete);
                else {
                    prec.suiv = new CelluleEntier(i, somme, prec.suiv);
                }
                i++;
            }
        }
        AffichagePerti();
    }

    public void AffichagePerti() {
        // bubble sort
        // d'abord on va trier les cellules contenant la pertinence de chacun des
        // documents et apres on affichera les 10 premiers documents les plus pertinents
        if (tete == null) {
            System.out.println("votre liste chainee est vide!!");
        } else {
            CelluleEntier list;
            list = tete;
            CelluleEntier change = new CelluleEntier();
            int x = 0, d = 0;
            do {
                list = tete;
                x = 0;
                while (list.suiv != null) {
                    if (list.pertinence < list.suiv.pertinence) {
                        // first swap
                        change.pertinence = list.pertinence;
                        change.indice = list.indice;
                        // second swap
                        list.pertinence = list.suiv.pertinence;
                        list.indice = list.suiv.indice;
                        // third swap
                        list.suiv.pertinence = change.pertinence;
                        list.suiv.indice = change.indice;
                        x = 1;
                    }
                    list = list.suiv;

                }
            } while (x == 1);

            CelluleEntier tmp = tete;
            while (tmp != null && d != 10) {

                System.out.println(lecteurDicoDocs.motIndice(tmp.indice));
                tmp = tmp.suiv;
                d = d + 1;
            }

        }
    }

}
