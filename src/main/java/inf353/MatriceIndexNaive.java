package inf353;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatriceIndexNaive implements MatriceIndex {

    int[][] matrice;
    int ndoc;
    int nterm;
    String nameFile;
//premier constructeur de la matrixIndexNaif
    public MatriceIndexNaive(int ndoc, int nterm){

        this.ndoc = ndoc;
        this.nterm = nterm;
        matrice = new int[ndoc][nterm];

        // permet de parcourir les documents
        for(int doc=0;doc<this.ndoc; doc++){

          //permet de parcourire les indices termes du document
            for(int term=0; term < this.nterm; term++){

                matrice[doc][term]  = 0;
                
            }
        }
        
    }
/**deuxième constructeur de la matriceIndexNaif
* monFichier correspond au nom du fichier matrice
*
*/
public int ligne;
public int colonne;
public MatriceIndexNaive(String monFichier) throws IOException{
    ligne = 0;
    colonne=0;
try{
    //declaration des variables
    ligne = 0;
    colonne = 0;
    // construction du fichier a partir du fichier source
    File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" + monFichier+ ".txt");
    // contruction de la memoir tampon et du lecteur de fichier pour recuperer les dimension
    BufferedReader lire = new BufferedReader(new FileReader(file));
    //creation de memoir stocker les chaine de lignes
    String element;
    while ((element = lire.readLine())!= null) {
        //creation d'un tableau pour stocker les chaines en differenciant les espaces
        String[] chaine = element.split(" ");
        //parcour du tableau representant les Strings d'une ligne pour avoir nb colonne
        int i = 0;
        while(i!=chaine.length + 1){
            colonne = i; // incrementation de colonne
            i = i+1;
        }
        ligne = ligne +1; // incrementation de ligne
        
    }
    // a la sortie on obtient ligne, colonne teste effectuer
    //System.out.println(ligne);
   // System.out.println(colonne);
   //remplissage de la matrice
   //construction de la matrice avec les informations ligne et colonne
matrice = new int[ligne][colonne];
//memoire tampon pour une noveelle lecture du fichier en remplissant la matrice
BufferedReader lire1 = new BufferedReader(new FileReader(file));
String ec;// string pour stocker la ligne
while ((ec = lire1.readLine())!=null) {
    String[] chaine1 = ec.split(" "); // stocker les strings de ligne dans le tableau
    //parcour du tableau pour remplir en lisant le fichier par ligne puis colonne de ligne
    int j = 0;
    int i = 0;
        while(j!=colonne){
        //affectation a matrice le string d'entier du fichier a la position
        matrice[i][j]=Integer.parseInt(chaine1[j]);
        System.out.print(matrice[i][j] + " "); // affichage d'elements
        j = j+1;
    }
     System.out.println(); // affichage de matrice en entier
    i = i+1;
   // System.out.print(matrice[i][j] + " ");
    
    }
}
catch(Exception e){
    System.out.println("error is : " + e.getMessage());
}

}

/**
     * Sauvegarde de la matrice d'occurence dans le fichier nomDeFichier. Le format est libre,
     * mais doit privilégier la vitesse de chargement et la compacité (taille du fichier).
     *
     * @param nomDeFichier
     */
    public void sauver(String nomDeFichier) throws FileNotFoundException{
//utilisation d'exception pour creer le fichier et ecrire
    try{
         // creation du fichier nomDeFichier  suivant le chemin
        File file = new  File(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" + nomDeFichier + ".txt");
        // verifier si le fichier existe ou non
        if(file.createNewFile()){
            //creer l'ecriveur dans le fichier
            FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" + nomDeFichier + ".txt");
            // reserver la memoire
           // BufferedWriter memo = new BufferedWriter(writer);
            // parcpour du document pour ecrire
            for(int doc = 0; doc<this.ndoc; doc++){
                //parcour les indices de terme
                for(int term = 0; term< this.nterm; term++){
                    writer.write(this.matrice[doc][term] + " ");
                }
                writer.write("\n");// a la ligne apres le parcour du premier document
            }
            writer.close();
            System.out.println(System.getProperty("user.dir")+ "\\src\\main\\java\\inf353\\ressources\\");
        }
        else{
            System.out.println("File already exists.");
        }
    }
    catch(IOException e){
        System.out.println("An error occurred.");
        e.getStackTrace();
        }
}

    /**
     * retourne le nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @return       le nombre d'occurences du terme dans le document
     */
    public int val(int ndoc, int nterm){

        return matrice[ndoc][nterm]; //nombre d'occurence du terme dans le document
    }

    /**
     * Ajoute 1 au nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     */
    public void incremente(int ndoc, int nterm){

        matrice[ndoc][nterm] = matrice[ndoc][nterm] + 1;

    }

    /**
     * affecte à val le numéro d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @param val    la nouvelle valeur du nombre d'occurence
     */
    public void affecte(int ndoc, int nterm, int val){

            matrice[ndoc][nterm] = val;

    }

    //  public static void main(String[] args)throws IOException {
    // //     //MatriceIndexNaive M = new MatriceIndexNaive(3,4);
    //      MatriceIndexNaive M = new MatriceIndexNaive("dosso");
    // //    // M.sauver("doss");
        // int i=0;
        // while (i!=M.ndoc) {
        //     int j=0;
        //     while (j!=M.nterm) {
        //         System.out.print(M.val(i,j)+"\t");
        //         j=j+1;
        //     }
        //     System.out.println();
        //     i=i+1;
        // }
        // while(i!=M.colonne){
        //     int j = 0;
        //     while(j!= M.colonne){
        //         System.out.println(M.val(i, j) + "\t");
        //         j = j+1;
        //     }
        //     i = i+1;
        // }

    }
