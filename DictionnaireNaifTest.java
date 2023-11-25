package inf353;


import org.junit.Test;


import static org.junit.Assert.*;

import java.beans.Transient;

public class DictionnaireNaifTest {
  
  
    public void testvider2() throws Exception {
        DictionnaireNaif f = new DictionnaireNaif();
        DictionnaireNaif expected = new DictionnaireNaif();
        
    assertTrue(expected.equals(f));
    }
    /*@Test
   public void testvider2() throws Exception {
    DictionnaireNaif f = new DictionnaireNaif();
    DictionnaireNaif expected = new DictionnaireNaif();
    f.vider();  
    assertTrue(expected.equals(f));
    }
    */

    @Test
    public void testnbMots1() throws Exception {
        DictionnaireNaif f = new DictionnaireNaif();
        f.ajouterMot("mac");
        f.ajouterMot("mac jvfjvjkr rhehbfhe");
        assertTrue(f.nbMots() == 2);

    }
      @Test
      public void testnbMots2() throws Exception {
          DictionnaireNaif f = new DictionnaireNaif();
          f.ajouterMot("mac     jvfjvjkr     rhehbfhe");
          assertTrue(f.nbMots() == 1);

      }
      @Test
      public void testnbMots3() throws Exception {
          DictionnaireNaif f = new DictionnaireNaif();
          f.ajouterMot("mac     jvfjvjkr     rhehbfhe");
          assertFalse(f.nbMots() == 3);

      }
      @Test
      public void testnbMots4() throws Exception {
          DictionnaireNaif f = new DictionnaireNaif();
          f.ajouterMot(" ");
          assertTrue(f.nbMots() == 1);

      }
        @Test
        public void testnbMots5() throws Exception {
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("tchfgyjyfgftu");
            assertTrue(f.nbMots() == 1);

        }
      @Test
      public void testnbMots6() throws Exception {
          DictionnaireNaif f = new DictionnaireNaif();
          f.ajouterMot("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
          assertTrue(f.nbMots() == 1);
      }

        @Test
        public void testcontient1() throws Exception {
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("a");
            assertTrue(f.contient("a") == true);

        }
     @Test
     public void testcontient2() throws Exception {
         DictionnaireNaif f = new DictionnaireNaif();
         f.ajouterMot("mama");
         f.ajouterMot("mama");
         assertTrue(f.contient("mama") == true);

     }
     @Test
     public void testcontient3() throws Exception {
         DictionnaireNaif f = new DictionnaireNaif();
         f.ajouterMot("mama");
         f.ajouterMot("mama15820");
         assertTrue(f.contient("mama9552") == false);

     }
    
    @Test
    public void testcontient4() throws Exception {
        DictionnaireNaif f = new DictionnaireNaif();
        f.ajouterMot("mama");
        f.ajouterMot("mama");
        assertTrue(f.contient("mam") == false);

    }
      @Test
      public void testcontient5() throws Exception {
          DictionnaireNaif f = new DictionnaireNaif();
          assertFalse(f.contient("mam") == true);

      }
         @Test
         public void testcontientPrefixe() throws Exception {
             DictionnaireNaif f = new DictionnaireNaif();
             f.ajouterMot("mama");
             assertTrue(f.contientPrefixe("am") == false);
         }
          @Test
          public void testcontientPrefixe1() throws Exception {
              DictionnaireNaif f = new DictionnaireNaif();
              f.ajouterMot("mama");
              f.ajouterMot("mam");
              assertTrue(f.contientPrefixe("m") == true);
          }
         @Test
         public void testcontientPrefixe2() throws Exception {
             DictionnaireNaif f = new DictionnaireNaif();
             f.ajouterMot("mama");
             f.ajouterMot("mam");
             assertTrue(f.contientPrefixe("m") == true);
         }
       
            @Test
            public void testplusLongPrefixe1() throws Exception {
                DictionnaireNaif f = new DictionnaireNaif();
                f.ajouterMot("mam");
                f.ajouterMot("mama");
                f.ajouterMot("a");
                
                assertTrue(f.plusLongPrefixe().equals("mam"));
            }
        @Test
        public void testplusLongPrefixe2() throws Exception {
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("mama");
            f.ajouterMot("mam");
            assertTrue(f.plusLongPrefixe().equals("mam"));
        }
        @Test
        public void testplusLongPrefixe3() throws Exception {
               DictionnaireNaif f = new DictionnaireNaif();
               f.ajouterMot("mama");
               f.ajouterMot("mam");
               assertFalse(f.plusLongPrefixe().equals(""));
           }
        @Test
        public void motIndice() throws Exception {     
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("crayon");
            assertTrue((f.motIndice(0)).equals("crayon"));
        }
   
        @Test
        public void motindice1() throws Exception {     
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("carte");
            f.ajouterMot("carnet");
            assertTrue((f.motIndice(1)).equals("carnet"));
        
   }
   

        @Test
        public void indiceMot() throws Exception {
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("issac");
            assertTrue((f.indiceMot("issac")) == 0);
        }
        @Test
        public void indiceMot2() throws Exception {
            DictionnaireNaif f = new DictionnaireNaif();
            f.ajouterMot("issac");
            f.ajouterMot("issa");
            assertTrue(f.indiceMot("issa") == 1);
        }
             @Test
        public void indiceMot3() throws Exception {
            DictionnaireNaif f=new DictionnaireNaif();
            f.ajouterMot("issac");
            f.ajouterMot("issa");
            f.ajouterMot("iss");
            assertTrue((f.indiceMot("iss")) == 2);
        }
    
}