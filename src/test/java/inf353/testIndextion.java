package inf353;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * testIndextion
 */
public class testIndextion {

    @Test
    public void Indexationtest1()throws IOException{
        Indexation I = new Indexation();
        I.Indexer("src/main/java/inf353/ressources/test.txt", "src/main/java/inf353/ressources/test1.txt", "src/main/java/inf353/ressources/dosso.txt");
        MatriceIndexNaive m = new MatriceIndexNaive("src/main/java/inf353/ressources/dosso.txt");
        assertTrue(m.val(0,0)!=-1);
    }
}