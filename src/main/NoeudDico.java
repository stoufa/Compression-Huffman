package main;

import java.util.HashMap;

import main.arbreDeCodes.Feuille;
import main.arbreDeCodes.Noeud;

/**
 * classe repr�sentant un noeud de l'arbre du dictionnaire [ Question-21 ]
 * @author Stoufa
 *
 */
public class NoeudDico {

    /**
     * chaque noeud contient une lettre [ Question-21 ]
     */
    char      lettre;
    /**
     * chaque noeud peut avoir un frere [ Question-21 ]
     */
    NoeudDico frere;
    /**
     * chaque noeud peut avoir un fils  [ Question-21 ]
     */
    NoeudDico fils;
    /**
     * entier associ� � chaque mot du dictionnaire  [ Question-27 ]
     */
    int       val;

    /**
     * Constructeur r�cursif [ Question-22 ]
     * @param mot   le mot � ajouter dans le dictionnaire
     * @param i     l'indice de la lettre
     */
    public NoeudDico( String mot, int i ) {
        this.lettre = mot.charAt( i ); // on met � jour la lettre
        i++; // on avance le compteur
        if ( i < mot.length() ) { // si on n'a pas arriv� encore � la fin de la cha�ne ...
            fils = new NoeudDico( mot, i ); // passer le reste de la cha�ne au fils apr�s sa cr�ation
        }
    }

    /**
     * Constructeur simple [ Question-23 ]
     * @param c         caract�re courant
     * @param fils      fils du noeud courant
     * @param frere     fr�re du noeud courant
     */
    NoeudDico( char c, NoeudDico fils, NoeudDico frere ) {
        this.lettre = c;
        this.fils = fils;
        this.frere = frere;
    }

    /**
     * si l'utilisateur n'a pas donn� un indice c'est 0 par d�faut
     * ( on commence � ajouter le mot � partir de son premier caract�re )
     * @param mot
     */
    public NoeudDico( String mot ) {
        int i = 0;
        this.lettre = mot.charAt( i ); // on met � jour la lettre
        i++; // on avance le compteur
        if ( i < mot.length() ) { // si on n'a pas arriv� encore � la fin de la cha�ne ...
            fils = new NoeudDico( mot, i ); // passer le reste de la cha�ne au fils apr�s sa cr�ation
        }
    }

    /**
     * si l'utilisateur n'a pas donn� un indice c'est 0 par d�faut
     * ( on commence � ajouter le mot � partir de son premier caract�re )
     * @param mot
     * @return
     */
    public NoeudDico ajouter( String mot ) {
        return ajouter( mot, 0 );
    }

    /**
     * permet d'ajouter le mot ( mot ) � partir de l'indice ( i )   [ Question-24 ]
     * @param mot   le mot � ajouter
     * @param i     indice � partir duquel on va ajouter le mot
     * @return      dictionnaire des mots apr�s l'ajout du mot
     */
    public NoeudDico ajouter( String mot, int i ) {
        if ( i == mot.length() ) { // si on a arriv� � la fin de la cha�ne
            return this; // on retourne l'objet courant
        }
        if ( mot.charAt( i ) == this.lettre ) { // si la lettre courante du mot est la m�me lettre stock� dans le noeud
            i++; // on avance l'indice
            if ( fils != null ) { // si le noeud courant a un fils
                fils = fils.ajouter( mot, i ); // on ajoute le mot au fils
            } else { // si le noeud courant n'a pas un fils
                if ( i < mot.length() ) { // si l'indice est valide : i entre 0 et mot.length() - 1
                    //fils = new NoeudDico( mot, i + 1 ); // on cr�e un fils ..................... i+1 ? ou i! on a d�ja incr�menter i ligne 56 ! TODO
                    fils = new NoeudDico( mot, i );
                }
            }
            return this; // on retourne l'objet courant ( apr�s l'ajout du mot chez le fils )
        }

        // si la lettre courant du mot est avant la lettre stock� dans le noeud ( order alphab�tique : a, b, c, ... )
        if ( mot.charAt( i ) < this.lettre ) {
            NoeudDico tmp = new NoeudDico( mot, i ); // On cr�e un nouveau dictionnaire � partir de cette lettre
            tmp.frere = this; // et on devient le fr�re de ce noeud
            return tmp; // et on retourne le nouveau dictionnaire cr�e
            /*
             * exemple :
             * this [b]
             * mot : ___[a]___
             * tmp [a]
             * [a] -> [b]
             */
        }
        // dans ce cas : mot.charAt( i ) > this.lettre
        if ( this.frere == null ) { // si on n'a pas un noeud fr�re
            this.frere = new NoeudDico( mot, i ); // on cr�e un noeud fr�re
            return this; // on retourne le dictionnaire courant
            /*
             * exemple :
             * this [a]
             * mot : ___[b]___
             * tmp [b]
             * [a] -> [b]
             */
        }
        // dans ce cas : mot.charAt( i ) > this.lettre et on a un fr�re
        this.frere = frere.ajouter( mot, i ); // on ajoute le mot au niveau du noeud fr�re
        return this; // et on retourne le dictionnaire courant
    }

    /**
     * [ Question-25 ]
     * affiche le dictionnaire
     */
    public void afficher() {
        afficher( "" );
    }

    /**
     * [ Question-25 ]
     * permet l'affichage du dictionnaire, utilis� la afficher()
     * @param prefix    cha�ne stockant les mots � afficher
     */
    private void afficher( String prefix ) {
        if ( fils == null ) { // si on n'a pas un fils
            System.out.println( prefix + lettre + "(" + val + ")" ); // on affiche la cha�ne actuelle
        } else { // si on a un fils
            fils.afficher( prefix + lettre ); // le mot n'est pas encore termin� ! on d�l�gue l'affichage au fils
        }
        if ( frere != null ) { // si on a un noeud fr�r�
            frere.afficher( prefix ); // on affiche ce qu'il a :)
        }
    }

    /**
     * [ Question-26 ]
     * m�thode de comptage des fr�quences des lettres dans le dictionnaire
     * @param map   HashMap contenant les lettres et leurs fr�quences
     */
    void initialiseFrequencesLettres( HashMap<Character, Feuille> map ) {
        Character key = new Character( lettre );
        if ( !map.containsKey( key ) ) {
            map.put( key, new Feuille( 1, lettre ) );
        } else {
            Feuille feuille = map.get( key );
            feuille.frequence++;
            map.put( key, feuille );
        }
        if ( fils != null ) { // si on a un noeud fils
            fils.initialiseFrequencesLettres( map ); // on l'explore
        }
        if ( frere != null ) { // si on a un noeud fr�re
            frere.initialiseFrequencesLettres( map ); // on l'explore
        }
    }

    /**
     * [ Question-26 ]
     * codage du dictionnaire
     * @param buffer    cha�ne r�sultat
     * @throws TasVideException
     */
    public void encode( StringBuffer buffer ) throws TasVideException {
        HashMap<Character, Feuille> lettres = new HashMap<>();
        initialiseFrequencesLettres( lettres );

        System.out.println( "lettres : " + lettres );

        Noeud arbreCode = Huffman.construitCode( lettres );

        System.out.println( "arbre de codage : " + arbreCode );

        arbreCode.encode( buffer );
        buffer.append( ";" ); // pour s�parer l'arbre du dictionnaire

        System.out.println( "arbre cod� : " + buffer.toString() );

        HashMap<Character, String> codes = new HashMap<>();
        arbreCode.remplirTable( codes );

        System.out.println( "codes : " + codes );

        this.encode( buffer, codes );

        //System.out.println( "arbre + dictionnaire : " + buffer.toString() );
        //System.err.println( "test" );
        //System.exit( 0 );
    }

    /**
     * [ Question-26 ]
     * codage du dictionnaire, utilis�e par public void encode( StringBuffer buffer ) throws TasVideException;
     * @param buffer    cha�ne r�sultat
     * @param codes     codes de huffman
     */
    void encode( StringBuffer buffer, HashMap<Character, String> codes ) {
        buffer.append( '0' ); // on ajoute 0 : d�limiteur entre les noeuds
        buffer.append( codes.get( new Character( lettre ) ) );
        if ( fils == null ) { // si on n'a pas un fils
            buffer.append( '1' ); // on ajoute 1
        } else { // si on a un fils
            fils.encode( buffer, codes ); // on l'encode
        }
        if ( frere == null ) { // si on n'a pas un noeud fr�re
            buffer.append( '1' ); // on ajoute 1
        } else { // si on a un noeud fr�re
            frere.encode( buffer, codes ); // on l'encode
        }
    }

    public static NoeudDico decode( String str ) {
        String[] parts = str.split( ";" );
        //System.out.println( "arbre cod� : " + parts[0] );
        Noeud arbre = Huffman.decodeArbre( new Etat( parts[0] ) ); // la 1ere partie est l'arbre de codage cod� en binaire
        //System.out.println( "arbre : " + arbre );
        //System.err.println( "test" );
        //System.exit( 0 );

        HashMap<Character, String> codes = new HashMap<>();
        arbre.remplirTable( codes );

        //        System.out.println( codes );
        //        System.exit( 0 );

        String dictStr = parts[1]; // 2�me partie est le dictionnaire cod� en binaire
        Etat etat = new Etat( dictStr );
        NoeudDico dictionnaire = new NoeudDico( '\0', null, null );

        decodeRec( etat, codes, dictionnaire );

        return dictionnaire;
    }

    /**
     * proc�dure de d�codage r�cursive
     * @param e
     * @param codes
     * @param dictionnaire
     */
    private static void decodeRec( Etat e, HashMap<Character, String> codes, NoeudDico dictionnaire ) {
        if ( e.nextBit() ) { // bit lu = 1

            // impossible !
            //System.out.println( "impossible !" );

        } else { // bit lu = 0

            //System.out.println( "bit lu = 0" );
            //System.out.println( "lettre ?" );

            //System.out.println( codes );
            //System.out.println( "1: " + e.chaine );
            //            e.afficher();

            // d�terminer la lettre
            String charStr = Huffman.decodeLettre( codes, e );
            //System.out.println( "2: " + e.chaine );
            //e.afficher();

            //System.out.println( "charStr : " + charStr );
            dictionnaire.lettre = charStr.charAt( 0 );

            //System.out.println( "lettre : " + dictionnaire.lettre );

            //System.exit( 0 );

            if ( e.peek() == '1' ) { // bit lu = 1

                // fils nul
                dictionnaire.fils = null;
                e.position++; // avancer la position

                //System.out.println( "bit lu = 1" );
                //System.out.println( "fils nul" );

            } else { // bit lu = 0

                //System.out.println( "bit lu = 0" );
                //System.out.println( "fils non nul" );
                //System.out.println( "d�but appel r�cursive --------\\" );

                // fils non nul
                NoeudDico fils = new NoeudDico( '\0', null, null );
                decodeRec( e, codes, fils );
                dictionnaire.fils = fils;

                //System.out.println( "fin appel r�cursive --------/" );

            }

            if ( e.peek() == '1' ) { // bit lu = 1

                //                System.out.println( "bit lu = 1" );
                //                System.out.println( "frere nul" );

                // frere nul
                dictionnaire.frere = null;
                e.position++; // avancer la position

            } else { // bit lu = 0

                //                System.out.println( "bit lu = 0" );
                //                System.out.println( "frere non nul" );
                //                System.out.println( "d�but appel r�cursive --------\\" );

                // frere non nul
                NoeudDico frere = new NoeudDico( '\0', null, null );
                decodeRec( e, codes, frere );
                dictionnaire.frere = frere;

                //                System.out.println( "fin appel r�cursive --------/" );

            }

        }
    }

    /**
     * [ Question-26 ]
     * d�codage du dictionnaire
     * @param e contient la cha�ne repr�sentant le dictionnaire cod�
     * @return  dictionnaire d�cod�
     */
    public static NoeudDico decode( Etat e ) {
        if ( !e.valide() ) {
            throw new Error( "Erreur : fin du dictionnaire inattendue" );
        }
        if ( e.nextBit() ) { // si la valeur du bit est �gale � 1
            return null; // on retourne null ( voir codage, on ajoute 1 si le fils ou le fr�re sont null )
        } else { // si la valeur du bit est �gale � 0
            // on d�code la lettre et on la met dans un dictionnaire qu'on va le retourner
            // le d�codage contient en appelant r�cursivement la fonction pour les noeuds fils et fr�re
            return new NoeudDico( Huffman.decodeASCII( e ), decode( e ), decode( e ) );
        }
    }

    /**
     * [ Question-27 ]
     * permet de num�roter les mots du dictionnaire
     * utilis�e par numeroter()
     * @param n
     * @return
     */
    public int numeroter( int n ) {
        if ( fils == null ) { // si on n'a pas un noeud fils
            val = n + 1; // c'est la fin d'un nouveau mot, on incr�mente le nombre
        } else { // si on a un noeud fils
            val = fils.numeroter( n ); // notre valeur est la valeur retourn�e par le fils
        }
        if ( frere == null ) { // si on n'a pas un noeud frere
            return val; // on retourne notre valeur
        } else { // si on a un noeud frere
            return frere.numeroter( val ); // on retourne la valeur retourn�e par le noeud fr�re
        }
    }

    /**
     * [ Question-27 ]
     * permet de num�roter les mots du dictionnaire
     */
    public void numeroter() {
        numeroter( 0 ); // on passe 0 pour commencer la num�rotation par 1
    }

    //    void recherche( int n, StringBuffer buffer ) {
    //        if ( n <= v ) {
    //            buffer.append( lettre );
    //            if ( fils != null ) {
    //                fils.recherche( n, buffer );
    //            }
    //        } else {
    //            if ( frere != null ) {
    //                frere.recherche( n, buffer );
    //            } else {
    //                throw new Error( "Not found !" );
    //            }
    //        }
    //    }

}
