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
public MatriceIndexNaive(String monFichier) throws IOException{
    int ligne=0;
    int colonne=0;
try{
    //File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" + monFichier + ".txt");
    //if(file.exists() && file.canRead()){
        
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" + monFichier + ".txt");
            FileReader lecteur = new FileReader(file);
            BufferedReader lire = new BufferedReader(lecteur);
            String line;
            while ((line=lire.readLine())!=null) {
                String[] ec = line.split(" ");
           //parcour du fichier matrice pour recuperer les dimensions(ligne et colonne)
                int k=0;
                while (k!= ec.length) {
                    k = k+1;
                    colonne = k;
            
                }
            ligne = ligne + 1;
        }
        lecteur.close();
        //initialisation de la matrix avec les dimension recuperer
        matrice = new int[ligne][colonne];
        FileReader lecteur1 = new FileReader(file);
        BufferedReader lire1 = new BufferedReader(lecteur1);
        String line1;
        int i=0;
        while ((line1= lire1.readLine())!=null && i<ligne) {
            String[] ec1 = line1.split(" ");
            int j=0;
            while (j!=ec1.length) {
                matrice[i][j] = Integer.parseInt(ec1[j]);
                j = j+1;
            }
            System.out.println();
            i = i+1;
        }
        lecteur1.close();
        //System.out.println(matrice[i][j]);
    }
        
catch(Exception e){
    System.out.println("an error");
    // e.getStackTrace();
}
    /*// creation du fichier
    File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\inf353\\ressources\\" + monFichier + ".txt");
    // creation de l'objcet file read
    if(file.exists()){
        FileReader lire = new FileReader(file);
    //creation de l'objet bufferedRead
    BufferedReader bw = new BufferedReader(lire);
    String textLine;
    while((textLine = bw.readLine()) != null){

        String[] text = textLine.split(" ");
        //parcour du tableau string et  colonne
        int i=0;
        while (i!=text.length) {
            colonne = i;
            i = i + 1;
        }
        ligne = ligne +1;
    }
    //fermeture de memoir tampon
    lire.close();
    //creation de la matrice
    matrice = new int [ligne][colonne];
    //creer un object de lecture
    FileReader lire1 = new FileReader(file);
    //creer la memoire tampom
    BufferedReader bw1 = new BufferedReader(lire1);
    //creer un tableau de mot en lecture des lignes
    String textLine1;
    int i=0;
    while ((textLine1 = bw1.readLine())!= null) {
        String[] text1 = textLine1.split(" ");
        //o rcour et initialisation de la matrice
        int j=0;
            while (j<text1.length) {
                matrice[i][j] = Integer.parseInt(text1[j]);
                j = j+1;
            }
            System.out.println();
            i = i +1;
        }
    lire1.close();
}
    }
    catch(IOException e){
    System.out.println("error");
    e.getStackTrace();
}*/
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

    /**public static void main(String[] args)throws IOException {
        //MatriceIndexNaive M = new MatriceIndexNaive(3,4);
        MatriceIndexNaive M = new MatriceIndexNaive("\\src\\main\\java\\inf353\\ressources\\doss.txt");
        //M.sauver("tet");
        int i=0;
        while (i!=M.ndoc) {
            int j=0;
            while (j!=M.nterm) {
                System.out.print(M.val(i,j)+"\t");
                j=j+1;
            }
            System.out.println();
            i=i+1;
        }

    }*/

}