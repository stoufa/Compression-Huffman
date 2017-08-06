package main;

/**
 * classe permettant de stocker la chaîne de codage [ Question-8 ]
 * 
 * @author Stoufa
 *
 */
public class Etat {

    /**
     * la chaîne de codage
     */
    protected String chaine;
    /**
     * la position de lecture actuelle
     */
    protected int    position;

    /**
     * constructeur
     * 
     * @param chaine
     *            la chaîne de codage
     */
    public Etat( String chaine ) {
        super();
        this.chaine = chaine;
        this.position = 0;
    }

    /**
     * @return true si la position est valide ( dans les limites de la chaîne )
     * @return false sinon
     */
    public boolean valide() {
        return this.position >= 0 && this.position <= ( this.chaine.length() - 1 );
    }

    /**
     * N.B. cette méthode permet d'avancer la position !
     * 
     * @return true si la valeur du bit actuel est égale à 1
     * @return false si la valeur du bit actuel est égale à 0
     */
    public boolean nextBit() {
        if ( !valide() ) {
            throw new Error( "position invalide !" );
        }
        switch ( this.chaine.charAt( this.position ) ) {
        case '0':
            this.position++;
            return false;
        case '1':
            this.position++;
            return true;
        default:
            throw new Error( "un bit doit étre 0 ou 1 !" );
        }
    }

    /**
     * @return la chaîne de codage
     */
    public String getChaine() {
        return chaine;
    }

    /**
     * permet de modifier la chaîne de codage
     * 
     * @param chaine
     *            la chaîne de codage
     */
    public void setChaine( String chaine ) {
        this.chaine = chaine;
    }

    /**
     * permet de récupérer la position du bit actuel à lire
     * 
     * @return la position du bit actuel à lire
     */
    public int getPosition() {
        return position;
    }

    /**
     * permet de modifier la position du bit actuel à lire
     * 
     * @param position
     *            la position du bit actuel à lire
     */
    public void setPosition( int position ) {
        this.position = position;
    }

    @Override
    /**
     * retourne une chaîne qui représente l'objet courant ( le noeud )
     */
    public String toString() {
        return String.format( "chaine: %s | position: %d", chaine, position );
    }

    public void afficher() {
        int i;
        for ( i = 0; i < position; i++ ) {
            System.out.print( chaine.charAt( i ) );
        }
        System.out.print( "[" + chaine.charAt( position ) + "]" );
        for ( i = position + 1; i < chaine.length(); i++ ) {
            System.out.print( chaine.charAt( i ) );
        }
        System.out.println();
    }

    /**
     * retourne le caractére actuel sans avancer la position
     * @return
     */
    public char peek() {
        return chaine.charAt( position );
    }
}
