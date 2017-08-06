package main.test;

import main.TasVideException;

/**
 * classe représentant un tas ( heap ) de mots
 * 
 * @author Stoufa
 *
 */
public class TasMots {

    /**
     * taille minimale de la liste des noeuds
     */
    private static final int TAILLE_INITIALE = 1;

    /**
     * liste des noeuds
     */
    NoeudMot[]               noeuds;

    /**
     * nombre d'éléments dans le tas
     */
    int                      nb;

    /**
     * constructeur
     */
    TasMots() {
        nb = 0;
        noeuds = new NoeudMot[TAILLE_INITIALE];
    }

    /**
     * @return true si le tas contient un seul élément
     * @return false sinon
     */
    public boolean singleton() {
        return nb == 1;
    }

    /**
     * @param i
     * @return indice du fils gauche
     */
    static int filsGauche( int i ) {
        return 2 * i + 1;
    }

    /**
     * @param i
     * @return indice du fils droit
     */
    //    static int filsDroit( int i ) {
    //        return 2 * ( i + 1 );
    //    }

    /**
     * @param i
     * @return indice du frére droit
     */
    static int frereDroit( int i ) {
        return i + 1;
    }

    /**
     * @param i
     * @return indice du pere
     */
    static int pere( int i ) {
        return ( i - 1 ) / 2;
    }

    /**
     * ajoute un élément dans le tas [ Question-15 ]
     * 
     * @param nn
     *            le nouveau noeud à ajouter
     */
    void ajouter( NoeudMot nn ) {
        if ( nb == noeuds.length ) {
            NoeudMot[] nouvelleListeNoeuds = new NoeudMot[noeuds.length * 2];
            // copie de l'ancienne liste
            for ( int j = 0; j < noeuds.length; j++ ) {
                nouvelleListeNoeuds[j] = noeuds[j];
            }
            noeuds = nouvelleListeNoeuds;
        }
        noeuds[nb] = nn;
        int i = nb;
        nb++;
        while ( pere( i ) >= 0 ) { // tant qu'on a un père
            // si on est plus prioritaire que lui
            if ( noeuds[i].plusPrioritaireQue( noeuds[pere( i )] ) ) {
                // on permute le noeud actuel avec son père
                NoeudMot tmp = noeuds[pere( i )];
                noeuds[pere( i )] = noeuds[i];
                noeuds[i] = tmp;
                // mise à jour des indices
                i = pere( i );
            } else {
                break;
            }
        }

        /*
         * if ( nb == noeuds.length ) { Noeud[] nouvelleListeNoeuds = new
         * Noeud[noeuds.length * 2]; // copie de l'ancienne liste
         * //System.arraycopy( noeuds, 0, nouvelleListeNoeuds, 0, noeuds.length
         * ); for ( int j = 0; j < noeuds.length; j++ ) { nouvelleListeNoeuds[j]
         * = noeuds[j]; } noeuds = nouvelleListeNoeuds; } int i; for ( i = nb; i
         * > 0 && noeuds[pere( i )].moinsPriotaireQue( nn ); i = pere( i ) ) {
         * noeuds[i] = noeuds[pere( i )]; System.out.println( "i = " + i ); }
         * noeuds[i] = nn; nb++;
         */
    }

    /**
     * retire un élément du tas et le retourne [ Question-16 ]
     * 
     * @return l'élément retiré du tas
     * @throws TasVideException
     *             si le tas est vide !
     */
    NoeudMot retirer() throws TasVideException {
        //        if ( nb == 0 ) {
        //            throw new TasVideException();
        //        }
        //        // Sauvegarder la racine
        //        Noeud racine = noeuds[0];
        //
        //        // écraser la racine par le dernier élément
        //        noeuds[0] = noeuds[nb - 1];
        //
        //        // éffacer le dernier élément
        //        noeuds[nb - 1] = null;
        //
        //        nb--;
        //
        //        int i = 0;
        //
        //        Noeud noeudMax;
        //        int indiceNoeudMax;
        //
        //        while ( i < nb ) {
        //            // Récupération du noeud le plus prioritaire des fils
        //            if ( frereDroit( i ) < nb ) { // le frére droit existe
        //                if ( noeuds[frereDroit( i )].plusPrioritaireQue( noeuds[filsGauche( i )] ) ) {
        //                    indiceNoeudMax = frereDroit( i );
        //                    noeudMax = noeuds[indiceNoeudMax];
        //                } else {
        //                    indiceNoeudMax = filsGauche( i );
        //                    noeudMax = noeuds[indiceNoeudMax];
        //                }
        //            } else {
        //                indiceNoeudMax = filsGauche( i );
        //                noeudMax = noeuds[indiceNoeudMax];
        //            }
        //
        //            //System.out.println( "i = " + i );
        //            //System.out.println( noeudMax == null );
        //            if ( noeudMax != null && noeuds[i].moinsPriotaireQue( noeudMax ) ) { // si le noeud courant est moins prioritaire que le noeud max
        //                // on les permute
        //                Noeud tmp = noeuds[i];
        //                noeuds[i] = noeudMax;
        //                noeudMax = tmp;
        //                // et on met à jour les indices
        //                i = indiceNoeudMax;
        //            } else { // sinon, le noeud est bien placé
        //                break;
        //            }
        //        }
        //
        //        return racine;

        if ( nb == 0 ) {
            throw new TasVideException();
        }
        nb--;
        NoeudMot noeudEnTete = noeuds[0];
        NoeudMot noeudAdeplacer = noeuds[nb];
        noeuds[nb] = null;
        int i;
        for ( i = filsGauche( 0 ); i < nb; i = filsGauche( i ) ) {
            if ( frereDroit( i ) < nb && noeuds[frereDroit( i )].plusPrioritaireQue( noeuds[i] ) ) {
                i = frereDroit( i );
            }
            if ( noeudAdeplacer.plusPrioritaireQue( noeuds[i] ) ) {
                break;
            }
            noeuds[pere( i )] = noeuds[i];
        }
        noeuds[pere( i )] = noeudAdeplacer;
        return noeudEnTete;
    }

    @Override
    public String toString() {
        String str = "";
        //        int nbElmMax; // nombre d'éléments maximum à afficher sur la ligne
        //        int level = 0;
        //        int nbElmAffiches = 0; // nombre d'éléments affichés
        //        boolean endDisplay = false;
        //
        //        while ( !endDisplay ) {
        //            nbElmMax = (int) Math.pow( 2, level ); // 2^compteur => 2^0, 2^1,
        //                                                   // 2^2, ... => 1, 2, 4, ...
        //            for ( int i = 0; i < nbElmMax; i++ ) {
        //                if ( nbElmAffiches < noeuds.length && noeuds[nbElmAffiches] != null ) {
        //                    str = str + noeuds[nbElmAffiches] + " ";
        //                    nbElmAffiches++;
        //                } else {
        //                    endDisplay = true;
        //                    break;
        //                }
        //                str = str + "\n";
        //            }
        //
        //            level++;
        //        }

        for ( int i = 0; i < nb; i++ ) {
            str = str + noeuds[i].toString();
            if ( i != noeuds.length - 1 ) {
                str = str + " / ";
            }
        }

        //        for ( int i = 1; i <= nb / 2; i++ ) {
        //            str = str + " PARENT : " + noeuds[i] + " LEFT CHILD : " + noeuds[2 * i]
        //                    + " RIGHT CHILD :" + noeuds[2 * i + 1] + "\n";
        //        }

        return str;
    }

    /**
     * [ Question-20 ]
     * retourne un tableau trié de feuilles
     * @param asc   true : ascendent ; false : descendant
     * @return tableau trié
     * @throws TasVideException
     */
    public NoeudMot[] trier( boolean asc ) throws TasVideException {
        NoeudMot[] tab = new NoeudMot[nb];
        int indice, increment;
        if ( asc ) {
            indice = 0;
            increment = 1;
        } else {
            indice = nb - 1;
            increment = -1;
        }
        while ( nb > 0 ) {
            tab[indice] = retirer();
            indice += increment;
        }
        return tab;
    }

}
