package main.arbreDeCodes;

import java.util.HashMap;

/**
 * Noeud de l'arbre des codes [ Question-1 ]
 * 
 * @author Stoufa
 *
 */
public class Noeud {

    /**
     * fréquence d'apparence de la lettre
     */
    public int frequence;

    /**
     * constructeur [ Question-1 ]
     * 
     * @param frequence
     *            fréquence d'apparence de la lettre
     */
    public Noeud( int frequence ) {
        super();
        this.frequence = frequence;
    }

    /**
     * @return la fréquence d'apparence de la lettre
     */
    public int getFrequence() {
        //return frequence;
        throw new Error( "la méthode getFrequence() ne doit pas être appelée à partir de la classe Noeud" );
    }

    /**
     * permet de modifier la fréquence d'apparence de la lettre
     * 
     * @param frequence
     *            la fréquence d'apparence de la lettre
     */
    public void setFrequence( int frequence ) {
        this.frequence = frequence;
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
        throw new Error( "la méthode remplirTable() ne doit pas être appelée à partir de la classe Noeud" );
    }

    /**
     * permet de remplir la table codes par les différents codes [ Question-5 ]
     * 
     * @param codes
     *            la table d'hachage où on va stocker les codes
     */
    public void remplirTable( HashMap<Character, String> codes ) {
        remplirTable( codes, "" );
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
        throw new Error( "la méthode afficher() ne doit pas être appelée à partir de la classe Noeud" );
    }

    /**
     * permet d'afficher l'arbre [ Question-6 ]
     */
    public void afficher() {
        afficher( 0, "" );
    }

    /**
     * utilisée pour représenter l'arbre des codes [ Question-10 ]
     * 
     * @param buffer
     *            chaîne mutable ( modifiable ) contenant le résultat du codage
     */
    public void encode( StringBuffer buffer ) {
        throw new Error( "la méthode encode() ne doit pas être appelée à partir de la classe Noeud" );
    }

    @Override
    /**
     * retourne une chaîne qui représente l'objet courant ( le noeud )
     */
    public String toString() {
        throw new Error( "la méthode toString() ne doit pas être appelée à partir de la classe Noeud" );
    }

    /**
     * [ Question-13 ]
     * 
     * @param n
     *            le noeud qu'on va comparer avec
     * @return true si on est moins prioritaire ( plus grande frequence )
     * @return false sinon
     */
    public boolean moinsPriotaireQue( Noeud n ) {
        return frequence > n.frequence;
    }

    /**
     * [ Question-13 ]
     * 
     * @param n
     *            le noeud qu'on va comparer avec
     * @return true si on est plus prioritaire ( plus petite frequence )
     * @return false sinon
     */
    public boolean plusPrioritaireQue( Noeud n ) {
        return frequence < n.frequence;
    }

    /**
     * retourne le nombre de feuilles, c'est à dire le nombre de caractéres
     * non différents dans la chaîne de caractéres à coder
     * @return
     */
    public int nbFeuilles() {
        throw new Error( "la méthode encode() ne doit pas être appelée à partir de la classe Noeud" );
    }
}
