package main.arbreDeCodes;

import java.util.HashMap;

/**
 * Noeud interne de l'arbre des codes [ Question-3 ]
 * 
 * @author Stoufa
 *
 */
public class Interne extends Noeud {

    /**
     * fils droit et gauche du noeud interne
     */
    protected Noeud filsGauche, filsDroit;

    /**
     * constructeur [ Question-3 ]
     * 
     * @param filsGauche
     *            fils gauche
     * @param filsDroit
     *            fils droit
     */
    public Interne( Noeud filsGauche, Noeud filsDroit ) {
        super( 0 );
        int frequenceFilsGauche = ( filsGauche == null ) ? 0 : filsGauche.getFrequence();
        int frequenceFilsDroit = ( filsDroit == null ) ? 0 : filsDroit.getFrequence();
        frequence = frequenceFilsGauche + frequenceFilsDroit;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
    }

    /**
     * @return le fils gauche
     */
    public Noeud getFilsGauche() {
        return filsGauche;
    }

    /**
     * permet de modifier le fils gauche
     * 
     * @param filsGauche
     *            fils gauche
     */
    public void setFilsGauche( Noeud filsGauche ) {
        this.filsGauche = filsGauche;
    }

    /**
     * @return le fils droit
     */
    public Noeud getFilsDroit() {
        return filsDroit;
    }

    /**
     * permet de modifier le fils droit
     * 
     * @param filsDroit
     *            fils droit
     */
    public void setFilsDroit( Noeud filsDroit ) {
        this.filsDroit = filsDroit;
    }

    /**
     * @return la fréquence d'apparence de la lettre
     */
    @Override
    public int getFrequence() {
        return filsDroit.getFrequence() + filsGauche.getFrequence();
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
        filsDroit.remplirTable( codes, prefix + "0" );
        filsGauche.remplirTable( codes, prefix + "1" );
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
        indent += " ";
        filsDroit.afficher( 0, indent );
        System.out.println( indent + code );
        filsGauche.afficher( 1, indent );
    }

    /**
     * utilisée pour représenter l'arbre des codes [ Question-10 ]
     * 
     * @param buffer
     *            chaîne mutable ( modifiable ) contenant le résultat du codage
     */
    public void encode( StringBuffer buffer ) {
        // parcours préfixe
        buffer.append( '0' ); // noeud actuel
        filsGauche.encode( buffer ); // fils gauche
        filsDroit.encode( buffer ); // fils droit
    }

    @Override
    /**
     * retourne une chaîne qui représente l'objet courant ( le noeud )
     */
    public String toString() {
        return String.format( "[%s,%s]", filsGauche.toString(), filsDroit.toString() );
    }

    @Override
    public int nbFeuilles() {
        return filsGauche.nbFeuilles() + filsDroit.nbFeuilles();
    }
}
