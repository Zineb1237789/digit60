package inf353;

/**
 * MatriceIndexTest
 */

import org.junit.Test;


import static org.junit.Assert.*;

import java.beans.Transient;

public class MatriceIndexTest {

    @Test
    public void testSauver1() {
        MatriceIndexNaive M = new MatriceIndexNaive(1,4); 
        assertTrue(M.val(0,0)==0);
    }
    @Test
    public void testVal1() { 
        MatriceIndexNaive M = new MatriceIndexNaive(2,4);
        // MatriceIndexNaive f = new MatriceIndexNaive("Dosso");
        assertTrue(M.val(0,3)==0);
    }
    @Test
    public void testVal2() {
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);

        assertFalse(M.val(0, 0)!=0);
    }
    @Test
    public void testincremente1() {
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);
        int v1=M.val(0,0)+1;
        M.incremente(0,0);
        assertTrue(M.val(0,0)==v1);
    }
}       