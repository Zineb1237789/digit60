package inf353;
import inf353.LecteurDocumentNaif;
import inf353.DictionnaireNaif;
import inf353.MatriceIndexNaive;
public class Indexation{
    int lig;
    int col;

    public Indexation(){
        lig = 0;
        col = 0;
    }
    //creation d'une methode indexer qui prend en parametre doc, saveDic, saveMat
    public void Indexer(String doc, String saveDic, String saveMat){
        //construis des objects de ces classes
        MatriceIndexNaive m = new MatriceIndexNaive(saveMat);
        LecteurDocumentNaif lecDoc = new LecteurDocumentNaif(doc);//utilisation du deuxieme constructeur de la matrice
        DictionnaireNaif dic = new DictionnaireNaif();
        // demarrage
        lecDoc.demarrer();
        while(!lecDoc.finDeSequence()){
            // utilisation du dictionnaire pour concerver les mots
            dic.ajouterMot(lecDoc.elementCourant());
            lecDoc.avancer();
        }
        //initialisation de colonne de l'index
        if(m.colonne>=dic.nbMots()){
            col = m.colonne;
        }
        else{
            col = dic.nbMots();
        }
        lig = m.ligne +1; //incrementation dela ligne apres parcoure des colonne
        // generons la matrix en utilisant le premier constructeur
        MatriceIndexNaive m1 = new MatriceIndexNaive(lig + 1, col);
        //parcour de la matrice m pour former la matrice m1 en utilisant affecte de matrice
        int i = 0;
        while(i<m.ligne){
            int j = 0;
            while(j< m.colonne){
                m1.affecte(i,j,m.val(i,j));
                j = j+1;
            }
            i = i+1;
        }
        //demarrer avec le lecteur pour effectuer l'incrementation de la matrice m1 creer plus haut
        lecDoc.demarrer();
        while(!lecDoc.finDeSequence()){
            if(dic.contient(lecDoc.elementCourant())){
                m1.incremente(lig, dic.motIndice(lecDoc.elementCourant()));
            }
            lecDoc.avancer();
        }
        //sauvegarde des elements
        dic.sauver(saveDic);
        m1.sauver(saveMat);
        lig = lig +1;
    }


}