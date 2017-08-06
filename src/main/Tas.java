package main;

import main.arbreDeCodes.Noeud;

/**
 * classe représentant un tas ( heap ) [ Question-14 ]
 * 
 * @author Stoufa
 *
 */
public class Tas {

    /**
     * taille minimale de la liste des noeuds
     */
    private static final int TAILLE_INITIALE = 1;

    /**
     * liste des noeuds
     */
    Noeud[]                  noeuds;

    /**
     * nombre d'éléments dans le tas
     */
    int                      nb;

    /**
     * constructeur
     */
    Tas() {
        nb = 0;
        noeuds = new Noeud[TAILLE_INITIALE];
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
     * @param pos la position à tester
     * @return true si pos est la position d'une feuille
     * ( noeud qui ne posséde pas de fils )
     */
    //    boolean estFeuille( int pos ) {
    //        return pos >= ( nb / 2 ) && pos <= nb;
    //    }

    /**
     * permute deux éléments d'indices fpos et spos
     * @param fpos
     * @param spos
     */
    //    private void swap( int fpos, int spos ) {
    //        Noeud tmp;
    //        tmp = noeuds[fpos];
    //        noeuds[fpos] = noeuds[spos];
    //        noeuds[spos] = tmp;
    //        System.out.println( String.format( "swap(%d, %d)", fpos, spos ) );
    //    }

    /**
     * permet de garder la propriété d'un tas
     * @param pos
     * http://www.sanfoundry.com/java-program-implement-max-heap/
     */
    //    private void maxHeapify( int pos ) {
    //        if ( !estFeuille( pos ) ) {
    //            if ( noeuds[pos].moinsPriotaireQue( noeuds[filsGauche( pos )] ) ||
    //                    noeuds[pos].moinsPriotaireQue( noeuds[filsDroit( pos )] ) ) {
    //                if ( noeuds[filsGauche( pos )].plusPrioritaireQue( noeuds[filsDroit( pos )] ) ) {
    //                    swap( pos, filsGauche( pos ) );
    //                    maxHeapify( filsGauche( pos ) );
    //                } else {
    //                    swap( pos, filsDroit( pos ) );
    //                    maxHeapify( filsDroit( pos ) );
    //                }
    //            }
    //        }
    //    }

    /**
     * trier un tas [ Question-20 ]
     */
    //    public void maxHeap() {
    //        for ( int pos = ( nb / 2 ); pos >= 1; pos-- ) {
    //            maxHeapify( pos );
    //        }
    //    }

    /**
     * ajoute un élément dans le tas [ Question-15 ]
     * 
     * @param nn
     *            le nouveau noeud à ajouter
     */
    void ajouter( Noeud nn ) {
        if ( nb == noeuds.length ) {
            Noeud[] nouvelleListeNoeuds = new Noeud[noeuds.length * 2];
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
                Noeud tmp = noeuds[pere( i )];
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
    Noeud retirer() throws TasVideException {
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
        Noeud noeudEnTete = noeuds[0];
        Noeud noeudAdeplacer = noeuds[nb];
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
            str = str + noeuds[i];
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
    public Noeud[] trier( boolean asc ) throws TasVideException {
        Noeud[] tab = new Noeud[nb];
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
