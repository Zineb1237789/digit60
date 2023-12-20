package inf353;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Recherches {
    public int somme;
    public int produit;

    public void recherche(String fichier) throws IOException {
        try {
            // recupertion des fichiers generer par l'indexation
            // recuperation de la matrice generer
            MatriceCreuse matrice = new MatriceCreuse(
                    "C:\\Users\\dosso\\Desktop\\353_projet\\dossiers\\french\\matriceIndexee.txt");
            // recuperation du dictionnaire
            DictionnaireH dict = new DictionnaireH("C:\\Users\\dosso\\Desktop\\353_projet\\dossiers\\french\\dictionnaire.txt");

            // creation d'un fichier de lecture a partir de la liste de fichier generer par
            // l'indexation
            FileReader lecture = new FileReader(
                    "C:\\Users\\dosso\\Desktop\\353_projet\\dossiers\\french\\listeDocuments.txt");
            BufferedReader bw = new BufferedReader(lecture);
            // distinction de separateur en creant un
            String[] doc = bw.readLine().split(" ");
            // creation d'un objet de lecteur de document
            LecteurDocumentNaif<String> lect = new LecteurDocumentNaif<String>(fichier);
            // creation d'un tableau de type int qui s'initialise depuis le dict
            //int[] ref = new int[dict.N];
            MatriceCreuse ref = new MatriceCreuse();
            // proceder a la lecture
            lect.demarrer();
            while (!lect.finDeSequence()) {
                // verifier si l'element courant appartientdans le dictionnaire
                if (dict.contient(lect.elementCourant())) {
                    // incrementation de ref
                    //ref[dict.indiceMot(lect.elementCourant())] = ref[dict.indiceMot(lect.elementCourant())] + 1;
                    ref.incremente(0,dict.indiceMot(lect.elementCourant()));
                }
                lect.avancer(); // avancer
            }
            bw.close();
            lecture.close();
            // recupereration de la longueur de ref

            // calcul de pertinence
            int i = 0;
            somme = 0;
        //     float[] produit = new float[matrice.nbdoc];
        //    // System.out.println(matrice.nbdoc);
        //     while (i != 87182) {
        //         int h = 0;
        //         // parcour du tableau ref
        //         // while (j != ref.nbdoc) {
        //         //     somme = somme + ref[j] * matrice.val(j, i);
        //         //     j = j + 1;

        //         // }
        //         // produit[i] = somme;
        //         if(ref.val(0,h)!=0){
        //             somme = somme + (ref.val(0, h) * matrice.val(i, h));
        //         i = i + 1;
        //     }
        // }
       
        float[] produit = new float[87162];
        while(i!=87162){
            
            float somme = 0;
            int j = 0;
            while(j!=dict.nbMots() +1){
                if(ref.val(0, j)!=0){
                    somme = somme + (ref.val(0, j) * matrice.val(i, j));
					//System.out.println(somme);
                }
                j = j + 1;
            }
				//System.out.println("produits scalaires-" + i +"/"+ produits.length + " " + somme);
				produit[i] = somme/500;
                // System.out.println(produits[i]);
            i = i + 1 ;
        }

            // parcour du tableau produit pour le retour de documents
            for (int j = 0; j < produit.length; j++) {
                int index = j;
                for (int k = j + 1; k != produit.length; k++) {

                    if (produit[k] > produit[index]) {
                        index = k;
                    }
                }
                float min = produit[index];
                produit[index] = produit[j];
                produit[j] = min;
                String mindoc = doc[index];
                doc[index] = doc[j];
                doc[j] = mindoc;
            }

            int id = 0;
            int indiceReq = 1;
            int cste = 0;
            //int id = 0;
            int col = 0;
            File file = new File("treceval.txt");
            if(!file.exists()){
                file.createNewFile();
                FileWriter ecrire = new FileWriter(file);
                BufferedWriter bv = new BufferedWriter(ecrire);
                while (id != 500) {
                //System.out.println(doc[id]);
                //System.out.println(produit[id]);
                bv.write(100 + "\t" + cste + "\t" + doc[id] + "\t" + id + "\t" + produit[id] + "\t" + "STANDARD");
                bv.write("\n");

                id = id + 1;
            }
            bv.close();
            ecrire.close();
                }
            //System.out.println(doc[id]);

           //while (col <= 5){
                //File file = new File("treceval.txt");
                // try {
                //     if (!file.exists()) {
                //         file.createNewFile();
                //     FileWriter ecrire = new FileWriter(file);
                //     BufferedWriter bv = new BufferedWriter(ecrire);
                //     while (id != 10) {
                //         bv.write(100 + "\t" + cste + "\t" + doc[id] + "\t" + id + "\t" + produit[id] + "\t" + "STANDARD");
                //         bv.write("\n");
                //         id = id + 1;

                //     }
                //     bv.close();
                //     ecrire.close();
                    
                //     }
                   
                    //int ligne = 0;
                // try (FileWriter writer1 = new FileWriter(file);
                //     BufferedWriter bWriter = new BufferedWriter(writer1)) {
                //     while (id != 10) {
                //         bWriter.write(100 + "\t" + cste + "\t" + doc[id] + "\t" + id + "\t" + produit[id] + "\t" + "STANDARD");
                //         bWriter.write("\n");
                //         id = id + 1;
                //     }
                //     }
                    //col = col+1;
            
        //     } catch (IOException e) {
        //     System.out.print(e.getMessage());
        //    // e.printStackTrace();
        //     }
        //}
        // Saut à la ligne après chaque ligne
        System.out.println();

    }
    catch (IOException e) {
            System.out.print(e.getMessage());
           // e.printStackTrace();
            }
            
            
            
            
            // int id = 0;
            // while (id != 10) {
            //     System.out.println(doc[id]);
            //     System.out.println(produit[id]);
            //     id = id + 1;
            // }
        

    }
    // catch (IOException e) {
    //         System.out.println(e.getMessage());
    //     }
    //}

    public static void main(String[] args) throws IOException {

        Recherches I = new Recherches();
        I.recherche(/*"Soulèvement d'Indiens dans le sud du Mexique"*/ "C:\\Users\\dosso\\Desktop\\353_projet\\inf353-tests\\inf353-tests\\C096");

    }

}

