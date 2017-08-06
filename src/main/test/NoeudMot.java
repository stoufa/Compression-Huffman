package main.test;

import java.util.HashMap;

/**
 * Noeud de l'arbre des codes ( des mots )
 * 
 * @author Stoufa
 *
 */
public class NoeudMot {

    /**
     * fr�quence d'apparence du mot
     */
    protected int frequence;

    /**
     * constructeur
     * 
     * @param frequence
     *            fr�quence d'apparence du mot
     */
    public NoeudMot( int frequence ) {
        super();
        this.frequence = frequence;
    }

    /**
     * @return la fr�quence d'apparence de la lettre
     */
    public int getFrequence() {
        throw new Error( "la m�thode getFrequence() ne doit pas �tre appel�e � partir de la classe Noeud" );
    }

    /**
     * permet de modifier la fr�quence d'apparence du mot
     * 
     * @param frequence
     *            la fr�quence d'apparence du mot
     */
    public void setFrequence( int frequence ) {
        this.frequence = frequence;
    }

    /**
     * permet de remplir la table codes par les diff�rents codes
     * 
     * @param codes
     *            la table d'hachage o� on va stocker les codes
     * @param prefix
     *            cha�ne de caract�res utilis� dans la cr�ation des codes (
     *            initialement vide )
     */
    public void remplirTable( HashMap<String, String> codes, String prefix ) {
        throw new Error( "la m�thode remplirTable() ne doit pas �tre appel�e � partir de la classe Noeud" );
    }

    /**
     * permet de remplir la table codes par les diff�rents codes
     * 
     * @param codes
     *            la table d'hachage o� on va stocker les codes
     */
    public void remplirTable( HashMap<String, String> codes ) {
        remplirTable( codes, "" );
    }

    /**
     * permet d'afficher l'arbre
     * 
     * @param code
     *            0 ou 1
     * @param indent
     *            d�calage, des espaces pour diff�rencier les diff�rents niveaux
     *            de l'arbre ( initialement vide )
     */
    public void afficher( int code, String indent ) {
        throw new Error( "la m�thode afficher() ne doit pas �tre appel�e � partir de la classe Noeud" );
    }

    /**
     * permet d'afficher l'arbre
     */
    public void afficher() {
        afficher( 0, "" );
    }

    /**
     * utilis�e pour repr�senter l'arbre des codes
     * 
     * @param buffer
     *            cha�ne mutable ( modifiable ) contenant le r�sultat du codage
     */
    public void encode( StringBuffer buffer ) {
        throw new Error( "la m�thode encode() ne doit pas �tre appel�e � partir de la classe Noeud" );
    }

    @Override
    /**
     * retourne une cha�ne qui repr�sente l'objet courant ( le noeud )
     */
    public String toString() {
        throw new Error( "la m�thode toString() ne doit pas �tre appel�e � partir de la classe Noeud" );
    }

    /**
     * 
     * @param n
     *            le noeud qu'on va comparer avec
     * @return true si on est moins prioritaire ( plus grande frequence )
     * @return false sinon
     */
    public boolean moinsPriotaireQue( NoeudMot n ) {
        return frequence > n.frequence;
    }

    /**
     * 
     * @param n
     *            le noeud qu'on va comparer avec
     * @return true si on est plus prioritaire ( plus petite frequence )
     * @return false sinon
     */
    public boolean plusPrioritaireQue( NoeudMot n ) {
        return frequence < n.frequence;
    }

    /**
     * retourne le nombre de feuilles, c'est � dire le nombre de caract�res
     * non diff�rents dans la cha�ne de caract�res � coder
     * @return
     */
    public int nbFeuilles() {
        throw new Error( "la m�thode encode() ne doit pas �tre appel�e � partir de la classe Noeud" );
    }
}
