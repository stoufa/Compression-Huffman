package main;

/**
 * classe repr�sentant l'exception lev�e lors d'un ac�es en suppression sur un tas vide		[ Question-16 ]
 * @author Stoufa
 *
 */
public class TasVideException extends Exception {
    /**
     * generated serial version ID
     */
    private static final long serialVersionUID = -5743434513657939676L;

    public TasVideException() {
        super( "Exception: le tas est vide !" );
    }
}
