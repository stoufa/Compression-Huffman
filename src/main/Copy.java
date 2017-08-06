package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.arbreDeCodes.Noeud;

public class Copy {

    public static void main( String[] args ) throws IOException {

        /*
        if ( args.length != 2 ) {
            System.err.println(
                    "Usage: java Copy from to" );
            System.exit( 1 );
        }
        FileReader reader = new FileReader( args[0] );
        FileWriter writer = new FileWriter( args[1] );
        */

        // Encodage
        String texte = readFile( "in.txt" );
        System.out.println( "texte : " + texte );
        StringBuffer texteApresCodage = new StringBuffer( "" );
        Huffman.encode( texte, texteApresCodage );
        String texteApresCodageStr = texteApresCodage.toString();
        writeInFile( "out.txt", texteApresCodageStr ); // Sauvegarde du texte codé dans un fichier
        System.out.println( "texteApresCodage : " + texteApresCodageStr );
        System.out.println( "arbre : " + Huffman.arbreCodes );

        // Sauvegarde de l'arbre de codage dans un fichier
        StringBuffer buffer = new StringBuffer();
        Huffman.arbreCodes.encode( buffer );
        System.out.println( "arbre codé : " + buffer.toString() );
        writeInFile( "arbre.txt", buffer.toString() );

        // Décodage
        String chaineCode = readFile( "out.txt" );
        Etat e = new Etat( chaineCode );
        Noeud a = Huffman.decodeArbre( new Etat( readFile( "arbre.txt" ) ) );
        System.out.println( "arbre décodé : " + a );
        String chaineDecode = Huffman.decodeTexte( a, e );
        System.out.println( "chaîne décodée : " + chaineDecode );
        writeInFile( "out2.txt", chaineDecode );

        double nbBitsAvantCodage = chaineDecode.length() * 8; // chaque caractére est codé sur 8 bits
        double nbBitsApresCodage = chaineCode.length();
        double tauxCompression = nbBitsApresCodage / nbBitsAvantCodage * 100;
        System.out.println( "taux de compression : " + tauxCompression + "%" );
    }

    /**
     * permet de lire un fichier et retourner son contenu
     * @param filename  le nom du fichier à lire
     * @return  le contenu du fichier lu
     * @throws IOException
     */
    public static String readFile( String filename ) throws IOException {
        String content = "";
        FileReader reader = new FileReader( filename );
        while ( true ) {
            int c = reader.read();
            if ( c == -1 ) {
                // Si la fin de fichier est atteinte
                break;
            }
            content = content + (char) c;
        }
        reader.close();
        return content;
    }

    /**
     * permet d'écrire dans un fichier
     * @param filename  le nom du fichier à écrire
     * @param content   le contenu à écrire dans le fichier
     * @throws IOException
     */
    public static void writeInFile( String filename, String content ) throws IOException {
        FileWriter writer = new FileWriter( filename );
        writer.write( content );
        writer.close();
    }

    /**
     * retourne un tableau de mots lus à partir du fichier filename
     * @param filename  nom du fichier contenant les mots
     * @return          tableau de mots
     * @throws IOException
     */
    public static String[] getWords( String filename ) throws IOException {
        String content = readFile( filename );
        //System.out.println( "file read" );
        // Splitting the string with any whitespace chars as delimiters
        // http://stackoverflow.com/questions/225337/how-do-i-split-a-string-with-any-whitespace-chars-as-delimiters
        return content.toLowerCase().replaceAll( "\\W", " " ).split( "\\s+" );
        // .replaceAll( "\\W", " " ) to remove punctuation
        // https://coderanch.com/t/404713/java/strip-punctuation
    }

}
