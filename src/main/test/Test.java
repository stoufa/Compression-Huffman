package main.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import main.Copy;
import main.Etat;
import main.Huffman;
import main.NoeudDico;
import main.TasVideException;
import main.arbreDeCodes.Feuille;
import main.arbreDeCodes.Interne;
import main.arbreDeCodes.Noeud;

/**
 * classe utilisé pour le test
 * @author Stoufa
 *
 */
public class Test {

    /**
     * fonction point d'entrée
     * @param args	arguments
     * @throws IOException 
     * @throws TasVideException 
     */
    public static void main( String[] args ) throws IOException, TasVideException {
        //testAffichageArbres(); //	[ Question-4 ]
        //testGenerationCodes(); //	[ Question-5 ]
        //testCodage(); //	[ Question-7 ]
        testCompressionDecompression(); //	[ Question-19 ]
        //testDictionnaire(); //  [ Question-24 + Question-25 ]
        //testCodageDecodageDictionnaire(); //  [ Question-26 ]
        //testNumerotationArbre(); //  [ Question-27 ]
        //testHuffmanMots();

        //testDecodeArbre();
        //Huffman.encodeASCII( new StringBuffer(), 'z' );
    }

    /**
     * test de l'algorithme de Huffman appliqué sur les mots
     * @throws IOException
     * @throws TasVideException
     */
    public static void testHuffmanMots() throws IOException, TasVideException {
        String[] mots = Copy.getWords( "test2.txt" );
        HashMap<String, Integer> map = new HashMap<>();
        for ( String mot : mots ) {
            if ( map.containsKey( mot ) ) { // le mot existe déja
                Integer nbOcc = map.get( mot );
                map.put( mot, nbOcc + 1 );
            } else {
                map.put( mot, new Integer( 1 ) ); // 1ére apparition
            }
        }
        System.out.println( map );
        // à ce niveau on a la liste des mots avec leur occurrences
        // on doit les ajouter dans le dictionnaire pour les numéroter
        TasMots tasMots = new TasMots();
        Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
        while ( it.hasNext() ) {
            Entry<String, Integer> pair = (Entry<String, Integer>) it.next();
            FeuilleMot feuilleMot = new FeuilleMot( pair.getKey(), pair.getValue() );
            tasMots.ajouter( feuilleMot );
        }
        NoeudMot arbreCodage;
        if ( tasMots.singleton() ) { // cas des chaînes qui ne contiennent qu'une
            // seule lettre comme aaaaaaaaaa
            NoeudMot nSingleton = tasMots.retirer();
            arbreCodage = new InterneMot( nSingleton, nSingleton );
        }
        while ( !tasMots.singleton() ) {
            NoeudMot nx = tasMots.retirer();
            NoeudMot ny = tasMots.retirer();
            NoeudMot nn = new InterneMot( nx, ny );
            tasMots.ajouter( nn );
        }
        arbreCodage = tasMots.retirer();

        System.out.println( arbreCodage );

        HashMap<String, String> codes = new HashMap<>();
        arbreCodage.remplirTable( codes );

        System.out.println( codes );
    }

    /**
     * test : numérotation du dictionnaire [ Question-27 ]
     */
    public static void testNumerotationArbre() throws IOException {
        NoeudDico dictionnaire = null;

        String[] mots = Copy.getWords( "test.txt" );
        for ( String mot : mots ) {
            if ( dictionnaire == null ) {
                dictionnaire = new NoeudDico( mot );
            } else {
                dictionnaire.ajouter( mot );
            }
        }
        dictionnaire.numeroter();
        dictionnaire.afficher();
    }

    /**
     * test codage / décodage du dictionnaire [ Question-26 ]
     */
    public static void testCodageDecodageDictionnaire() throws IOException, TasVideException {
        NoeudDico dictionnaire = null;

        String[] mots = Copy.getWords( "words.txt" );
        for ( String mot : mots ) {
            if ( dictionnaire == null ) {
                dictionnaire = new NoeudDico( mot );
            } else {
                dictionnaire.ajouter( mot );
            }
        }

        System.out.println( "dictionnaire : " );
        dictionnaire.afficher();

        StringBuffer buffer = new StringBuffer();
        dictionnaire.encode( buffer );
        System.out.println( "dictionnaire codé : " + buffer.toString() );

        NoeudDico dictionnaireDecode = NoeudDico.decode( buffer.toString() );
        System.out.println( "dictionnaire décodé : " );
        dictionnaireDecode.afficher();
    }

    /**
     * test du dictionnaire [ Question-24 et Question-25 ]
     */
    public static void testDictionnaire() throws IOException {
        NoeudDico dictionnaire = null;

        String[] mots = Copy.getWords( "words.txt" );
        for ( String mot : mots ) {
            if ( dictionnaire == null ) {
                dictionnaire = new NoeudDico( mot );
            } else {
                dictionnaire.ajouter( mot );
            }
        }
        dictionnaire.afficher();
    }

    /**
     * test de compression / décompression [ Question-19 ]
     */
    public static void testCompressionDecompression() throws IOException {
        // Encodage
        String texte = Copy.readFile( "in.txt" );
        System.out.println( "texte : " + texte );
        StringBuffer texteApresCodage = new StringBuffer( "" );
        Huffman.encode( texte, texteApresCodage );
        String texteApresCodageStr = texteApresCodage.toString();
        Copy.writeInFile( "out.txt", texteApresCodageStr ); // Sauvegarde du texte codé dans un fichier
        System.out.println( "texteApresCodage : " + texteApresCodageStr );
        System.out.println( "arbre : " + Huffman.arbreCodes );

        // Sauvegarde de l'arbre de codage dans un fichier
        StringBuffer buffer = new StringBuffer();
        Huffman.arbreCodes.encode( buffer );
        System.out.println( "arbre codé : " + buffer.toString() );
        Copy.writeInFile( "arbre.txt", buffer.toString() );

        // Décodage
        String chaineCode = Copy.readFile( "out.txt" );
        Etat e = new Etat( chaineCode );
        Noeud a = Huffman.decodeArbre( new Etat( Copy.readFile( "arbre.txt" ) ) );
        System.out.println( "arbre décodé : " + a );
        String chaineDecode = Huffman.decodeTexte( a, e );
        System.out.println( "chaîne décodée : " + chaineDecode );
        Copy.writeInFile( "out2.txt", chaineDecode );

        double nbBitsAvantCodage = chaineDecode.length() * 8; // chaque caractére est codé sur 8 bits
        double nbBitsApresCodage = chaineCode.length();
        double tauxCompression = nbBitsApresCodage / nbBitsAvantCodage * 100;
        System.out.println( "taux de compression : " + tauxCompression + "%" );
    }

    /**
     * test décodage de l'arbre
     */
    public static void testDecodeArbre() {
        Noeud arbre = Huffman.decodeArbre( new Etat(
                "00010110010101001000001001001110010110111110111010001011100011011000010001011011011011011100101110101101100110010110100101011101100101110010101101100" ) );
        System.out.println( arbre );
        String texteApresCodage = "0001001000111111010000011000010110010010110000101111000101000110010101111";
        String texteOriginal = Huffman.decodeTexte( arbre, new Etat( texteApresCodage ) );
        System.out.println( "texteOriginal: " + texteOriginal );
    }

    /**
     * test d'affichage des arbres [ Question-4 ]
     */
    public static void testAffichageArbres() {
        System.out.println( "Arbre 1" );
        Noeud arbre1 = new Interne(
                new Interne(
                        new Interne(
                                new Feuille( 2, 'd' ),
                                new Feuille( 2, 'c' ) ),
                        new Feuille( 4, 'b' ) ),
                new Feuille( 8, 'a' ) );
        arbre1.afficher();
        System.out.println( arbre1 );

        System.out.println();

        System.out.println( "Arbre 2" );
        Noeud arbre2 = new Interne(
                new Interne(
                        new Feuille( 2, 'd' ),
                        new Feuille( 2, 'c' ) ),
                new Interne(
                        new Feuille( 4, 'b' ),
                        new Feuille( 8, 'a' ) ) );
        arbre2.afficher();
        System.out.println( arbre2 );
    }

    /**
     * test de génération des codes [ Question-5 ]
     */
    public static void testGenerationCodes() {
        Noeud arbre1 = new Interne(
                new Interne(
                        new Interne(
                                new Feuille( 2, 'd' ),
                                new Feuille( 2, 'c' ) ),
                        new Feuille( 4, 'b' ) ),
                new Feuille( 8, 'a' ) );
        HashMap<Character, String> codes = new HashMap<>();
        System.out.println( codes );
        arbre1.remplirTable( codes );
        System.out.println( codes );

        codes.clear();

        Noeud arbre2 = new Interne(
                new Interne(
                        new Feuille( 2, 'd' ),
                        new Feuille( 2, 'c' ) ),
                new Interne(
                        new Feuille( 4, 'b' ),
                        new Feuille( 8, 'a' ) ) );
        System.out.println( codes );
        arbre2.remplirTable( codes );
        System.out.println( codes );
    }

    /**
     * test de codage [ Question-7 ]
     */
    public static void testCodage() {
        Noeud arbre1 = new Interne(
                new Interne(
                        new Interne(
                                new Feuille( 2, 'd' ),
                                new Feuille( 2, 'c' ) ),
                        new Feuille( 4, 'b' ) ),
                new Feuille( 8, 'a' ) );
        HashMap<Character, String> codes = new HashMap<>();
        System.out.println( codes );
        arbre1.remplirTable( codes );
        System.out.println( codes );

        String texte = "aaaabbcdaaaabbcd";
        String resultatAttendu = "0000101011011100001010110111";
        StringBuffer resultat = new StringBuffer( "" );
        Huffman.encodeTexte( texte, codes, resultat );
        System.out.println( "R  = " + resultat );
        System.out.println( "RA = " + resultatAttendu );

        if ( resultat.toString().equals( resultatAttendu ) ) {
            System.out.println( "Correct!" );
        } else {
            System.out.println( "Erreur!" );
        }
    }

    public static void testDecodage() {
        Noeud arbre1 = new Interne(
                new Interne(
                        new Interne(
                                new Feuille( 2, 'd' ),
                                new Feuille( 2, 'c' ) ),
                        new Feuille( 4, 'b' ) ),
                new Feuille( 8, 'a' ) );
        String texte = "aaaabbcdaaaabbcd";
        String texteApresCodage = "0000101011011100001010110111";
        if ( Huffman.decodeTexte( arbre1, new Etat( texteApresCodage ) ).equals( texte ) ) {
            System.out.println( "Correct!" );
        } else {
            System.out.println( "Erreur!" );
        }
    }
}
