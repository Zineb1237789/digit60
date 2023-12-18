package inf353;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Indexation {
public int attribut;

    //sauvons tous les fichers dans une liste par leurs noms
    public void sauverFichier(String nomDeFichier,File fichier) throws FileNotFoundException {
        try {
            //creation de l'object file
            File file = new File(nomDeFichier);
            //ecrit dans le fichier donner par son chemin absolue (nom des fichiers)
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write(fichier.getName() + " ");
            fileWriter.close();//fermeture
        }
        catch (IOException e) {
            System.out.println(e.getMessage());// renvoie le messfrage d'erreur
        }
    }
    // parcouront les dossiers et sous dossiers(folder) et lister les fichiers dans le fichier "fichier"
    public static File[] parcourirDossier(File dossier) {
        // Liste pour stocker les fichiers
        File[] fichiers = dossier.listFiles();
        File[] result = new File[0]; // Initialisation avec un tableau vide
        
        // Vérifiez si la liste de fichiers n'est pas nulle
        if (fichiers != null) {
            //utilisation de la boucle pour chaque element de la liste Fichiers
            for (File fichier : fichiers) {
                if (fichier.isFile()) {
                    // Si le fichier est un fichier (et non un dossier), l'ajouter au résultat
                    result = ajouterElement(result, fichier);
                }
                else if (fichier.isDirectory()) {
                    // Si le fichier est un dossier, récursivement parcourir son contenu
                    File[] sousResultat = parcourirDossier(fichier);
                    // Fusionner les résultats actuels avec les résultats du sous-dossier
                    result = fusionner(result, sousResultat);
                }
            }
        }
        return result;
    }
    //realisation de la fonction ajouter de type de retour listFile et la dynamisation de la longueur de la liste de fichier
    public static File[] ajouterElement(File[] tableau, File element) {

        File[] nouveauTableau = new File[tableau.length + 1]; //object nouvel de taile dynamique
        System.arraycopy(tableau, 0, nouveauTableau, 0, tableau.length); //copy du contenu
        nouveauTableau[tableau.length] = element;
        return nouveauTableau; //retoune la nouvel list
    }

    // Méthode pour fusionner deux tableaux de fichiers
    public static File[] fusionner(File[] tab1, File[] tab2) {
        File[] resultat = new File[tab1.length + tab2.length];
        System.arraycopy(tab1, 0, resultat, 0, tab1.length);
        System.arraycopy(tab2, 0, resultat, tab1.length, tab2.length);
        return resultat;
    }
    //creation de la methode pour indexer
    public void Indexer(String fichierDossier) throws IOException {
        //creation d'un objcet fichier
        File fichier = new File(fichierDossier);
        File[] documents; // definir une list( tableau) qui va stocker les fichier contenu dans dossier
        documents=parcourirDossier(fichier);
        // Vérifiez si la liste n'est pas nulle
        //DictionnaireNaif dict = new DictionnaireNaif();
        DictionnaireH dict = new DictionnaireH();
        //utilisation du premier constructeur de la matrice (taile documentts et taille dictioonaire(nbmot))
       // MatriceIndexNaive matrice = new MatriceIndexNaive(documents.length, dict.N);
        MatriceCreuse matrice = new MatriceCreuse();
        int i = 0;
        //porcourons les documents
        while (i != documents.length) {
            //creation du lecteur
            LecteurDocumentNaif<String> lect = new LecteurDocumentNaif<String>(documents[i].getAbsolutePath());
            //sauvegarde par appelle a la fonction
            sauverFichier(fichierDossier+ "/listeDocuments.txt", documents[i]);
            //demarrage de lecture
            lect.demarrer();
            while (!lect.finDeSequence()) {
                ////System.out.println(l.elementCourant() + "----------------------------------------");
                    dict.ajouterMot(lect.elementCourant());
                    attribut = attribut +1;
                matrice.incremente(i, dict.indiceMot(lect.elementCourant())); //incrementation de la matrice
                lect.avancer();
            }
            i = i + 1; // document suivant
        }
        //matcreux.sauver(fichierDossier + "/matricreuse.txt");
        matrice.sauver(fichierDossier + "/matriceIndexee.txt"); //sauver matrice dans le fichier qui sera dans le repertoire
        dict.sauver(fichierDossier + "/dictionnaire.txt"); //sauver matrice dans le fichier qui sera dans le repertoire

    }
    public int nbMots(){
        return attribut;
        }
    

    public static void main(String[] args) throws IOException {
        Indexation I = new Indexation();

        I.Indexer("C:\\353_projet\\echantillon_5000");
        System.out.println(I.nbMots());
        
        
    }

    // public static void main(String[] args)throws IOException {
    //     Indexation I =new Indexation();
    //     I.Indexer(args[0]);
    // }

}
