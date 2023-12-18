/*
package inf353;
import java.io.IOException;
import java.lang.Math;
import inf353.*;

public class recherche {
    public CelluleEntier tete;
    MatriceCreuse M;
    DictionnaireH H;
    public String[] t = new String[100];
    public recherche() throws IOException{
 try{
      M = new MatriceCreuse("D:\\353_projet\\french\\matriceIndexee.txt");
    H = new DictionnaireH("D:\\353_projet\\french\\dictionnaire.txt");

   }
   catch(IOException e){
    System.out.println(e.getMessage());

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
            i = i + 1;
            lecteur.avancer();
        }

    }

    public void calculPerti()
    {
     int i=0;
     int j=0;
     int nd=0;
     int nt=0;
     float somme=0;
     int indice;
      CelluleEntier cc = tete;
     CelluleEntier prec = null;
     while(i!=t.length)//parcours du tableau de la requete
     {

        System.out.println(this.H);
        System.out.println(t[i]);
       if(H.contient(t[i]))
       {indice=H.indiceMot(t[i]);
        //parcours de la matrice
       while(nd!=M.nbdoc)//parcours des documents
       {
         nt=0;
        while(nt!=M.nbCellule(nd))//parcours de termes dans chaque document
        {
          if(indice==M.valIndice(nd, nt))
          somme=somme+M.val(nd,nt);//calcul de la pertinence
          nt++;
        }
          while (cc != null) 
          {
             prec = cc;
             cc = cc.suiv;
             if (prec == null)
                 tete = new CelluleEntier(i, somme, tete);
             else {
                 prec.suiv = new CelluleEntier(i, somme, prec.suiv);
             }

         }
         somme=0;
         nd++;
       }
       
     }
     i++;
    }
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

               // System.out.println(lecteurDicoDocs.motIndice(tmp.indice));
                tmp = tmp.suiv;
                d = d + 1;
            }

        }
    }
 public static void main(String[] args) throws IOException {
    recherche R=new recherche();
    R.lireRequete("D:\\353_projet\\inf353-tests\\C091");
    R.calculPerti();
 }
}
*/
package inf353;
import java.io.IOException;
import java.lang.Math;
import inf353.*;

public class recherche {
    public CelluleEntier tete;
    MatriceCreuse M;
    DictionnaireH H;
    public String[] t = new String[100];
    public recherche() throws IOException{
    try{
      M = new MatriceCreuse("D:\\353_projet\\french\\matriceIndexee.txt");
      H = new DictionnaireH("D:\\353_projet\\french\\dictionnaire.txt");

    }
    catch(IOException e){
    
        System.out.println(e.getMessage());
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
            i = i + 1;
            lecteur.avancer();
        }

    }

    public void calculPerti()
    {
     int i=0;
     int j=0;
     int nd=0;
     int nt=0;
     float somme=0;
     int indice;
      CelluleEntier cc = tete;
     CelluleEntier prec = null;
     while(i!=t.length)//parcours du tableau de la requete
     {

        //System.out.println(this.H.contient(t[i]));
       if(this.H.contient(t[i]))
       {

        indice=H.indiceMot(t[i]);

        //parcours de la matrice
       while(nd!=M.nbdoc)//parcours des documents
       {
         nt=0;
        while(nt!=M.nbCellule(nd))//parcours de termes dans chaque document
        {
          if(indice==M.valIndice(nd, nt))
          somme=somme+M.val(nd,nt);//calcul de la pertinence
          nt++;
        }
          while (cc != null) 
          {
             prec = cc;
             cc = cc.suiv;
             if (prec == null)
                 tete = new CelluleEntier(i, somme, tete);
             else {
                 prec.suiv = new CelluleEntier(i, somme, prec.suiv);
             }

         }
         somme=0;
         nd++;
       }
       
     }
     i++;
    }
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

               // System.out.println(lecteurDicoDocs.motIndice(tmp.indice));
                tmp = tmp.suiv;
                d = d + 1;
            }

        }
    }
 public static void main(String[] args) throws IOException {
    recherche R=new recherche();
    R.lireRequete("D:\\353_projet\\inf353-tests\\C091");
    R.calculPerti();
 }
}