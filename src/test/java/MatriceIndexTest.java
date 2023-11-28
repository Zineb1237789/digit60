package inf353;

/**
 * MatriceIndexTest
 */

import org.junit.Test;


import static org.junit.Assert.*;

import java.beans.Transient;

public class MatriceIndexTest {

    @Test
    public void testSauver1() throws Exception {
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);        
        assertTrue(M.equals(M));
    }
    @Test
    public void testVal1() throws Exception { 
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);

        assertTrue(M.val(0, 0)==0);
    }
    @Test
    public void testVal2() throws Exception {
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);

        assertFalse(M.val(0, 0)!=0);
    }
     @Test
    public void testincremente1() throws Exception {
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);
        int v1=M.val(0,0)+1;
        assertTrue(M.incremente(0,0)==v1);
    }
    @Test
    public void testincremente2() throws Exception {
        MatriceIndexNaive M = new MatriceIndexNaive(3,4);
        int v1=M.val(0,0)+1;
        assertFalse(M.incremente(0,0)!=v1);
    }
}       