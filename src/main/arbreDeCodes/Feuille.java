package main.arbreDeCodes;

import java.util.HashMap;

import main.Huffman;

/**
 * Noeud feuille de l'arbre des codes [ Question-2 ]
 * 
 * @author Stoufa
 *
 */
public class Feuille extends Noeud {

    /**
     * la lettre
     */
    protected char lettre;

    /**
     * constructeur [ Question-2 ]
     * 
     * @param frequence
     *            fréquence d'apparence de la lettre
     * @param lettre
     *            la lettre
     */
    public Feuille( int frequence, char lettre ) {
        super( frequence );
        this.lettre = lettre;
    }

    /**
     * @return la lettre
     */
    public char getLettre() {
        return lettre;
    }

    /**
     * @return la fréquence d'apparence de la lettre
     */
    @Override
    public int getFrequence() {
        return frequence;
    }

    /**
     * permet de modifier la fréquence d'apparence de la lettre
     * 
     * @param lettre
     *            la fréquence d'apparence de la lettre
     */
    public void setLettre( char lettre ) {
        this.lettre = lettre;
    }

    /**
     * permet de remplir la table codes par les différents codes [ Question-5 ]
     * 
     * @param codes
     *            la table d'hachage où on va stocker les codes
     * @param prefix
     *            chaîne de caractéres utilisé dans la création des codes (
     *            initialement vide )
     */
    public void remplirTable( HashMap<Character, String> codes, String prefix ) {
        codes.put( new Character( lettre ), prefix );
    }

    /**
     * permet d'afficher l'arbre [ Question-6 ]
     * 
     * @param code
     *            0 ou 1
     * @param indent
     *            décalage, des espaces pour différencier les différents niveaux
     *            de l'arbre ( initialement vide )
     */
    public void afficher( int code, String indent ) {
        System.out.println( String.format( "%s%d: '%c'", indent, code, lettre ) );
    }

    /**
     * utilisée pour représenter l'arbre des codes [ Question-10 ]
     * 
     * @param buffer
     *            chaîne mutable ( modifiable ) contenant le résultat du codage
     */
    public void encode( StringBuffer buffer ) {
        buffer.append( '1' );
        Huffman.encodeASCII( buffer, lettre );
    }

    @Override
    /**
     * retourne une chaîne qui représente l'objet courant ( le noeud )
     */
    public String toString() {
        // return lettre + "";
        return String.format( "<%c, %d>", lettre, frequence );
    }

    @Override
    public int nbFeuilles() {
        return 1;
    }
}
