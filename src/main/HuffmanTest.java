package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.arbreDeCodes.Feuille;
import main.arbreDeCodes.Interne;
import main.arbreDeCodes.Noeud;

/**
 * classe permettant de tester la classe Huffman
 * @author Stoufa
 *
 */
public class HuffmanTest {

    Noeud                      arbre1           = new Interne(
            new Interne(
                    new Interne(
                            new Feuille( 2, 'd' ),
                            new Feuille( 2, 'c' ) ),
                    new Feuille( 4, 'b' ) ),
            new Feuille( 8, 'a' ) );
    String                     texte            = "aaaabbcdaaaabbcd";
    String                     texteApresCodage = "0000101011011100001010110111";
    HashMap<Character, String> codes            = new HashMap<>();
    Etat                       e1, e2, e3, e4;

    boolean                    initialized      = false;

    public void init() {
        if ( !initialized ) {
            initialized = true;
            arbre1.remplirTable( codes );
            e1 = new Etat( "0" );
            e2 = new Etat( "10" );
            e3 = new Etat( "110" );
            e4 = new Etat( "111" );
        }
    }

    @Before
    public void setUp() throws Exception {
        init();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDecodeLettre() {
        assertEquals( "a", Huffman.decodeLettre( codes, e1 ) );
        assertEquals( "b", Huffman.decodeLettre( codes, e2 ) );
        assertEquals( "c", Huffman.decodeLettre( codes, e3 ) );
        assertEquals( "d", Huffman.decodeLettre( codes, e4 ) );
    }

    @Test
    public void testCodeExiste() {
        StringBuffer character = new StringBuffer();
        assertTrue( Huffman.codeExiste( codes, "0", character ) ); //System.out.println(character);	// a
        assertTrue( Huffman.codeExiste( codes, "10", character ) ); //System.out.println(character);	// b
        assertTrue( Huffman.codeExiste( codes, "110", character ) ); //System.out.println(character);	// c
        assertTrue( Huffman.codeExiste( codes, "111", character ) ); //System.out.println(character);	// d
    }

    @Test
    public void testEncode() {
        StringBuffer codage = new StringBuffer();
        arbre1.encode( codage );
        String codageAttendu = "000101100100101100011101100010101100001";
        assertEquals( codageAttendu, codage.toString() );
    }
}
