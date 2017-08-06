package main.test;

import java.util.HashMap;

public class InterneMot extends NoeudMot {

    /**
     * fils droit et gauche du noeud interne
     */
    protected NoeudMot filsGauche, filsDroit;

    /**
     * constructeur [ Question-3 ]
     * 
     * @param filsGauche
     *            fils gauche
     * @param filsDroit
     *            fils droit
     */
    public InterneMot( NoeudMot filsGauche, NoeudMot filsDroit ) {
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
    public NoeudMot getFilsGauche() {
        return filsGauche;
    }

    /**
     * permet de modifier le fils gauche
     * 
     * @param filsGauche
     *            fils gauche
     */
    public void setFilsGauche( NoeudMot filsGauche ) {
        this.filsGauche = filsGauche;
    }

    /**
     * @return le fils droit
     */
    public NoeudMot getFilsDroit() {
        return filsDroit;
    }

    /**
     * permet de modifier le fils droit
     * 
     * @param filsDroit
     *            fils droit
     */
    public void setFilsDroit( NoeudMot filsDroit ) {
        this.filsDroit = filsDroit;
    }

    /**
     * @return la fr�quence d'apparence de la lettre
     */
    @Override
    public int getFrequence() {
        return filsDroit.getFrequence() + filsGauche.getFrequence();
    }

    /**
     * permet de remplir la table codes par les diff�rents codes [ Question-5 ]
     * 
     * @param codes
     *            la table d'hachage o� on va stocker les codes
     * @param prefix
     *            cha�ne de caract�res utilis� dans la cr�ation des codes (
     *            initialement vide )
     */
    public void remplirTable( HashMap<String, String> codes, String prefix ) {
        filsDroit.remplirTable( codes, prefix + "0" );
        filsGauche.remplirTable( codes, prefix + "1" );
    }

    /**
     * permet d'afficher l'arbre [ Question-6 ]
     * 
     * @param code
     *            0 ou 1
     * @param indent
     *            d�calage, des espaces pour diff�rencier les diff�rents niveaux
     *            de l'arbre ( initialement vide )
     */
    public void afficher( int code, String indent ) {
        indent += " ";
        filsDroit.afficher( 0, indent );
        System.out.println( indent + code );
        filsGauche.afficher( 1, indent );
    }

    /**
     * utilis�e pour repr�senter l'arbre des codes [ Question-10 ]
     * 
     * @param buffer
     *            cha�ne mutable ( modifiable ) contenant le r�sultat du codage
     */
    public void encode( StringBuffer buffer ) {
        // parcours pr�fixe
        buffer.append( '0' ); // noeud actuel
        filsGauche.encode( buffer ); // fils gauche
        filsDroit.encode( buffer ); // fils droit
    }

    @Override
    /**
     * retourne une cha�ne qui repr�sente l'objet courant ( le noeud )
     */
    public String toString() {
        return String.format( "[%s,%s]", filsGauche.toString(), filsDroit.toString() );
    }

    @Override
    public int nbFeuilles() {
        return filsGauche.nbFeuilles() + filsDroit.nbFeuilles();
    }
}
