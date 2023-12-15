package inf353;
import java.io.*;

public class CelluleEntier {

    /**
     * Attributs de la cellule : un élément de la séquence et son successeur.
     */
    public int indice;
    public int occurence;
    public float pertinence;
    public CelluleEntier suiv;

    /**
     * Constructeur vide (l'élément et le suivant ne sont pas spécifiés).
     */
    public CelluleEntier() {
        super();
    }

    /**
     * Constructeur à partir d'un élément (le suivant n'est pas spécifié).
     */
    public CelluleEntier(int indice,int occurence) {
        this.indice = indice;
        this.occurence = occurence; 
    }

    /**
     * Constructeur à partir d'un suivant (l'élément n'est pas spécifié).
     */
    public CelluleEntier(CelluleEntier suiv) {
        this.suiv = suiv;
    }

    /**
     * Constructeur à partir d'un élément et d'un suivant.
     */
    public CelluleEntier(int indice, int occurence, CelluleEntier suiv) {
        this.indice = indice;
        this.occurence = occurence;
        this.suiv = suiv;
    }
      public CelluleEntier(int indice, float pertinence, CelluleEntier suiv) {
        this.indice = indice;
        this.pertinence = pertinence;
        this.suiv = suiv;
    }

    
}
