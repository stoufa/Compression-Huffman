package main.test;

import java.util.HashMap;

/**
 * Noeud feuille de l'arbre des codes
 * 
 * @author Stoufa
 *
 */
public class FeuilleMot extends NoeudMot {

    /**
     * le mot
     */
    String mot;

    /**
     * le num�ro du mot
     */
    //int    num;

    /**
     * @return la fr�quence d'apparence du mot;
     */
    @Override
    public int getFrequence() {
        return frequence;
    }

    /**
     * constructeur
     * 
     * @param frequence
     *            fr�quence d'apparence du mot
     * @param mot
     *            le mot
     */
    public FeuilleMot( String mot, int frequence ) {
        super( frequence );
        this.mot = mot;
    }

    //    public FeuilleMot( String mot, int num, int frequence ) {
    //        super( frequence );
    //        this.mot = mot;
    //        this.num = num;
    //    }

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
        codes.put( mot, prefix );
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
        System.out.println( String.format( "%s%d: '%s'", indent, code, mot ) );
    }

    @Override
    /**
     * retourne une cha�ne qui repr�sente l'objet courant ( le noeud )
     */
    public String toString() {
        // return lettre + "";
        return String.format( "<%s, %d>", mot, frequence );
    }

    @Override
    public int nbFeuilles() {
        return 1;
    }
}
