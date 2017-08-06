package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import main.arbreDeCodes.Feuille;
import main.arbreDeCodes.Interne;
import main.arbreDeCodes.Noeud;

/**
 * classe contenant le méchanisme du codage/décodage de Huffman [ Question-7 ]
 * 
 * @author Stoufa
 *
 */
public class Huffman {

    /**
     * l'arbre de codage
     */
    public static Noeud arbreCodes = null;

    /**
     * [ Question-7 ] N.B. : Il faut utiliser StringBuffer plutôt qu'un simple
     * String car String est passé en paramétre en valeur / copie ! le résultat
     * de sa modification dans la méthode ne va pas affecter sa valeur de départ
     * 
     * @param texte
     *            le texte à coder
     * @param codes
     *            l'ensemble des codes
     * @param resultat
     *            le résultat du codage
     */
    public static void encodeTexte( String texte, HashMap<Character, String> codes, StringBuffer resultat ) {
        for ( int i = 0; i < texte.length(); i++ ) {
            resultat.append( codes.get( texte.charAt( i ) ) );
        }
    }

    /**
     * permet de décoder un texte [ Question-9 ]
     * 
     * @param arbreCode
     *            l'arbre de codage
     * @param e
     *            l'état : contient la chaîne de codage
     * @return la chaîne décodée
     */
    public static String decodeTexte( Noeud arbreCode, Etat e ) {
        // Construction du code décrit par arbreCode
        HashMap<Character, String> codes = new HashMap<>();
        arbreCode.remplirTable( codes );

        String resultat = "";
        while ( e.valide() ) {
            resultat += decodeLettre( codes, e );
        }
        // System.out.println("resultat: " + resultat);
        return resultat;

        // StringBuffer chaineResultat = new StringBuffer();
        // encodeTexte(e.getChaine(), codes, chaineResultat);
        // return chaineResultat.toString();
    }

    /**
     * permet de décoder une lettre [ Question-9 ]
     * 
     * @param codes
     *            la liste des codes
     * @param e
     *            l'état : contient la chaîne de codage
     * @return la lettre décodée
     */
    public static String decodeLettre( HashMap<Character, String> codes, Etat e ) {
        String code = "";
        StringBuffer character = new StringBuffer();
        do {
            if ( e.nextBit() ) {
                code += '1';
            } else {
                code += '0';
            }
            // System.out.println(String.format("code: %s / etat: %s", code,
            // e));
            // System.out.println("codes: " + codes);
            // System.out.println("codeExiste ? : " + codeExiste(codes, code,
            // character));
            if ( codeExiste( codes, code, character ) ) {
                // System.out.println(String.format("codeExiste: code: %s |
                // character: %s / etat: %s", code, character, e));
                return String.valueOf( character.charAt( 0 ) ); // retourner le
                                                                // caractére
                                                                // trouvé
            }
        } while ( true );
    }

    /**
     * fonction qui cherche le caractére qui correspond au code passée en
     * paramétre
     * 
     * @param codes
     *            l'ensemble des codes
     * @param code
     *            le code qu'on cherche le caractére correspondant
     * @param character
     *            le caractére résultat
     * @return true si le caractére est trouvé
     * @return false sinon
     */
    public static boolean codeExiste( HashMap<Character, String> codes, String code, StringBuffer character ) {
        character.delete( 0, character.length() ); // vider la chaine
        // parcourir les codes pour chercher le caractére
        // http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator<Entry<Character, String>> it = codes.entrySet().iterator();
        while ( it.hasNext() ) {
            Entry<Character, String> pair = (Entry<Character, String>) it.next();
            if ( code.equals( pair.getValue() ) ) { // code trouvé
                character.append( pair.getKey() );
                return true;
            }
            // System.out.println(pair.getKey() + " = " + pair.getValue());
            // it.remove(); // avoids a ConcurrentModificationException
        }
        return false;
    }

    /**
     * convertit un caractére en sa représentation binaire sous forme de chaîne
     * de caractéres [ Question-10 ]
     * 
     * @param buffer
     *            résultat : représentation binaire
     * @param c
     *            le caractére
     * @return la représentation en binaire du caractére c
     */
    public static void encodeASCII( StringBuffer buffer, char c ) {
        for ( int i = 128; i > 0; i = i >> 1 ) {
            // System.out.println(i);
            if ( ( c & i ) == 0 ) {
                buffer.append( '0' );
            } else {
                buffer.append( '1' );
            }
        }
    }

    /**
     * décode la chaîne de codage contenu dans l'état e et construit l'arbre de
     * codage correspondante [ Question-11 ]
     * 
     * @param e
     *            l'état : contient la chaîne de codage
     * @return l'arbre de codage correspondante à l'état e
     */
    public static Noeud decodeArbre( Etat e ) {
        Noeud temp = null, sup = null, feuille = null;
        // int numIteration = 1;
        while ( true ) {
            // System.out.println();
            // System.out.println("Iteration " + numIteration);
            // numIteration++;

            // test
            // for(int readCharIndex = 0; readCharIndex <
            // e.getChaine().length(); readCharIndex++) {
            // if (readCharIndex == e.getPosition()) {
            // System.out.print("[" + e.getChaine().charAt(readCharIndex) +
            // "]");
            // } else {
            // System.out.print(e.getChaine().charAt(readCharIndex));
            // }
            // }
            // System.out.println();

            if ( e.nextBit() ) { // bit lu = 1, noeud feuille
                // System.out.println("Bit lu = 1, noeud feuille");

                // lire le code ASCII de la lettre
                // System.out.println("lecture du code ASCII");
                /*
                 * String codeASCII = ""; for(int i = 0; i < 8; ++i) { // les
                 * caractéres sont codés sur 8 bits if (e.nextBit()) { // bit lu
                 * = 1 codeASCII += '1'; } else { // bit lu = 0 codeASCII +=
                 * '0'; } } //System.out.println("codeASCII: " + codeASCII);
                 * 
                 * // déterminer la lettre int decimalASCII =
                 * ascii2char(codeASCII); //System.out.println("decimalASCII: "
                 * + decimalASCII); char c = (char) decimalASCII;
                 * //System.out.println("lettre: " + c);
                 */

                // déterminer la lettre
                char c = decodeASCII( e );

                // créer noeud feuille
                // System.out.println("création noeud feuille");
                feuille = new Feuille( 0, c );
                // System.out.println("Noeud Feuille créée");

                if ( temp == null ) { // arbre == null aussi ! c'est le premier
                                      // noeud de l'arbre
                                      // System.out.println("temp == null, premier noeud de
                                      // l'arbre !");
                    arbreCodes = feuille;
                    // System.out.println("arbre = feuille, return arbre");
                    return arbreCodes;
                } else {
                    // test
                    if ( temp instanceof Feuille ) {
                        throw new Error( "instance de Feuille !" );
                    }

                    // System.out.print("filsGauche == null ? ");
                    if ( ( (Interne) temp ).getFilsGauche() == null ) {
                        // System.out.println("oui");
                        ( (Interne) temp ).setFilsGauche( feuille );
                        // System.out.println("temp.fg = feuille");
                        // reboucler
                        // System.out.println("reboucler");
                    } else {
                        // System.out.println("non");
                        // System.out.print("filsDroit == null ? ");
                        if ( ( (Interne) temp ).getFilsDroit() == null ) {
                            // System.out.println("oui");
                            ( (Interne) temp ).setFilsDroit( feuille );
                            // System.out.println("temp.fd = feuille");

                            // trouver le noeud supérieur de temp
                            boolean supAtteint = false;
                            // System.out.println("Trouver le noeud parent");
                            do {
                                // System.out.println("noeudSuperieur");
                                sup = noeudSuperieur( arbreCodes, temp );
                                // System.out.print("sup == null ? ");
                                if ( sup == null ) { // pas de sommet !
                                    // System.out.println("oui");
                                    // System.out.println("pas de sommet !,
                                    // return arbre");
                                    return arbreCodes;
                                } else {
                                    // System.out.println("non");
                                    temp = sup;
                                    // System.out.println("temp = sup");
                                    // System.out.print("temp.fg != null &&
                                    // temp.fd != null ?");
                                    if ( ( ( (Interne) temp ).getFilsGauche() != null )
                                            && ( ( (Interne) temp ).getFilsDroit() != null ) ) {
                                        // il faut remonter d'avantage !
                                        // System.out.println("oui");
                                        // System.out.println("Il faut remonter
                                        // encore !");
                                    } else {
                                        // sinon, on peut continuer, le parent
                                        // est atteint
                                        // System.out.println("non");
                                        // System.out.println("parent atteint");
                                        supAtteint = true;
                                        // System.out.println("supAtteint =
                                        // true");
                                    }
                                }
                            } while ( !supAtteint );

                            // reboucler
                            // System.out.println("reboucler");

                        } else {
                            // System.out.println("non");
                            // impossible à atteindre !
                            // System.out.println("Impossible !");
                        }
                    }
                }
            } else { // bit lu = 0, noeud interne
                // System.out.println("Bit lu = 0, noeud interne");
                // System.out.print("temp == null ? ");
                if ( temp == null ) { // arbre == null aussi ! c'est le premier
                                      // noeud de l'arbre
                                      // System.out.println("oui");
                                      // System.out.println("temp == null, premier noeud de
                                      // l'arbre !");
                    arbreCodes = new Interne( null, null );
                    // System.out.println("arbre = new interne(null, null)");
                    temp = arbreCodes;
                    // System.out.println("temp = arbre");
                    // reboucler
                    // System.out.println("reboucler");
                } else {
                    // System.out.println("non");
                    // System.out.print("filsGauche == null ? ");
                    if ( ( (Interne) temp ).getFilsGauche() == null ) {
                        // System.out.println("oui");
                        ( (Interne) temp ).setFilsGauche( new Interne( null, null ) );
                        // System.out.println("temp.fg = new interne(null,
                        // null)");
                        temp = ( (Interne) temp ).getFilsGauche();
                        // System.out.println("temp = temp.fg");
                        // reboucler
                        // System.out.println("reboucler");
                    } else {
                        // System.out.println("non");
                        // System.out.print("filsDroit == null ? ");
                        if ( ( (Interne) temp ).getFilsDroit() == null ) {
                            // System.out.println("oui");
                            ( (Interne) temp ).setFilsDroit( new Interne( null, null ) );
                            // System.out.println("temp.fd = new interne(null,
                            // null)");
                            temp = ( (Interne) temp ).getFilsDroit();
                            // System.out.println("temp = temp.fd");
                            // reboucler
                            // System.out.println("reboucler");
                        } else {
                            // System.out.println("non");
                            // impossible à atteindre !
                            // System.out.println("Impossible !");
                        }
                    }
                }
            }
        }
    }

    /*
     * public static int ascii2char(String ascii) { int c = 0; for(int i =
     * ascii.length() - 1; i >= 0; --i) { // parcours de droite à gauche if
     * (ascii.charAt(i) == '1') { // sinon, c'est pas la peine de calculer la
     * puissance de 2 puisque ça va être multiplié par 0 ensuite ! c += (int)
     * Math.pow(2, ascii.length() - 1 - i); } } return c; }
     */

    /**
     * convertit la représentation binaire d'un caractére sous forme de chaîne
     * de caractéres en caractére [ Question-11 ]
     * 
     * @param e
     *            l'état : contient la chaîne de codage
     * @return le caractére
     */
    public static char decodeASCII( Etat e ) {
        if ( !e.valide() ) {
            throw new Error( "fin de code atteinte !" );
        }
        char c = 0;
        for ( int i = 128; i > 0; i = i >> 1 ) {
            if ( e.nextBit() ) {
                c |= i;
            }
        }
        return c;
    }

    /**
     * retourne le noeud parent du noeud passé en paramétre
     * http://stackoverflow.com/questions/23856618/finding-the-parent-of-a-node-
     * in-a-binary-tree
     * 
     * @param p
     *            noeud dont on cherche le parent
     * @param root
     *            le sommet de l'arbre
     * @return noeud parent
     */
    public static Noeud noeudSuperieur( Noeud root, Noeud p ) {
        return noeudSuperieurHelper( root, p );
    }

    /**
     * 
     * @param noeudCourant
     *            le noeud à partir duquel on va démarrer la recherche
     * @param p
     *            le noeud dont on cherche le parent
     * @return le noeud supérieur / parent du noeud p
     */
    private static Noeud noeudSuperieurHelper( Noeud noeudCourant, Noeud p ) {
        if ( estSommetArbre( p ) || noeudCourant == null || noeudCourant instanceof Feuille ) {
            return null;
        } else {
            if ( ( ( (Interne) noeudCourant ).getFilsGauche() == p )
                    || ( ( (Interne) noeudCourant ).getFilsDroit() == p ) ) {
                return noeudCourant;
            } else {
                Noeud resultat = noeudSuperieurHelper( ( (Interne) noeudCourant ).getFilsGauche(), p );
                if ( resultat != null ) {
                    return resultat;
                }
                return noeudSuperieurHelper( ( (Interne) noeudCourant ).getFilsDroit(), p );
            }
        }
    }

    /**
     * @param p
     *            le noeud
     * @return true si p est le sommet de l'arbre
     * @return false sinon
     */
    private static boolean estSommetArbre( Noeud p ) {
        return p == arbreCodes;
    }

    /**
     * [ Question-17 ]
     * 
     * @param texte
     *            le texte à analyser
     * @return tableau de noeuds feuilles
     */
    static List<Feuille> initialiseFrequences( String texte ) {
        ArrayList<Feuille> lettres = new ArrayList<>();
        HashMap<Character, Feuille> lettresMap = new HashMap<>();

        for ( int i = 0; i < texte.length(); i++ ) {
            char c = texte.charAt( i );
            Character key = new Character( c );
            if ( lettresMap.containsKey( key ) ) {

                Feuille feuille = lettresMap.get( key );
                feuille.setFrequence( feuille.getFrequence() + 1 );
                lettresMap.put( key, feuille );

            } else { // 1ére apparition

                lettresMap.put( key, new Feuille( 1, c ) );

            }
        }

        //System.out.println( "fréquences : " + lettresMap );

        // http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator<Entry<Character, Feuille>> it = lettresMap.entrySet().iterator();
        while ( it.hasNext() ) {
            Entry<Character, Feuille> pair = it.next();
            lettres.add( pair.getValue() );
            // System.out.println( pair.getKey() + " = " + pair.getValue() );
            // it.remove(); // avoids a ConcurrentModificationException
        }

        // System.out.println( lettres );

        return lettres;
    }

    /**
     * construit un arbre de code à partir d'un tableau de feuilles construit
     * avec la méthode initialiseFrequences() [ Question-18 ]
     * 
     * @param lettres
     *            tableau de noeuds feuilles
     * @return arbre des codes
     * @throws TasVideException
     *             si le tas est vide
     */
    static Noeud construitCode( List<Feuille> lettres ) throws TasVideException {
        Tas t = new Tas();
        //System.out.println( "lettres : " + lettres );
        for ( int i = 0; i < lettres.size(); i++ ) {
            if ( lettres.get( i ) != null ) {
                t.ajouter( lettres.get( i ) );
            }
            //System.out.println( "i : " + i );
            //System.out.println( "tas : " + t );
            //System.out.println( "-------------------" );
        }
        //System.out.println( "tas : " + t );
        //t.maxHeap();

        /*
        Noeud[] tab = t.trier( true );
        System.out.println();
        for ( Noeud n : tab ) {
            System.out.println( n );
        }
        System.err.println( "test" );
        System.exit( 0 ); // test
        */

        if ( t.singleton() ) { // cas des chaînes qui ne contiennent qu'une
                               // seule lettre comme aaaaaaaaaa
            Noeud nSingleton = t.retirer();
            return new Interne( nSingleton, nSingleton );
        }
        while ( !t.singleton() ) {
            Noeud nx = t.retirer();
            Noeud ny = t.retirer();
            Noeud nn = new Interne( nx, ny );
            t.ajouter( nn );
            //System.out.println( "\ntas : " + t );
        }
        return t.retirer();
    }

    /**
     * méthode de codage [ Question-18 ]
     * 
     * @param texte
     *            le texte à coder
     * @param texteApresCodage
     *            le texte aprés son codage
     */
    public static void encode( String texte, StringBuffer texteApresCodage ) {
        try {
            List<Feuille> feuilles = initialiseFrequences( texte );
            Noeud a = construitCode( feuilles );
            // on sauvegarde l'arbre pour des utilisations ultérieurs
            // exemple sauvegarde de l'arbre dans un fichier
            arbreCodes = a;
            encode( texte, a, texteApresCodage );
        } catch ( TasVideException e ) {
            e.printStackTrace();
        }
    }

    /**
     * méthode de codage [ Question-18 ]
     * version 2 de la méthode :
     *  public static void encode( String texte, StringBuffer texteApresCodage )
     * elle permet de sauvegarder aussi l'arbre de codage avant le texte ( séparés par un # )
     * @param texte
     *            le texte à coder
     * @param texteApresCodage
     *            le texte aprés son codage
     * @throws IOException 
     */
    public static void encode2( String texte, StringBuffer texteApresCodage ) throws IOException {
        try {
            List<Feuille> feuilles = initialiseFrequences( texte );
            Noeud a = construitCode( feuilles );
            // on sauvegarde l'arbre pour des utilisations ultérieurs
            // exemple sauvegarde de l'arbre dans un fichier
            arbreCodes = a;
            encode( texte, a, texteApresCodage );

            // Sauvegarde de l'arbre de codage
            StringBuffer buffer = new StringBuffer();
            Huffman.arbreCodes.encode( buffer );
            //System.out.println( "arbre codé : " + buffer.toString() );
            //Copy.writeInFile( "arbre.txt", buffer.toString() );

            //            System.out.println( texteApresCodage );

            //texteApresCodage.append( "#" + buffer.toString() );
            texteApresCodage.insert( 0, buffer.toString() + "#" );

            //            System.out.println( "**************" );
            //            System.out.println( texteApresCodage );

            //System.err.println( buffer.toString() );
        } catch ( TasVideException e ) {
            e.printStackTrace();
        }
    }

    /**
     * utilisée par encode(String texte, StringBuffer texteApresCodage) [
     * Question-18 ]
     * 
     * @param texte
     *            le texte à coder
     * @param arbreCode
     *            arbre des codes
     * @param texteApresCodage
     *            le texte aprés son codage
     */
    public static void encode( String texte, Noeud arbreCode, StringBuffer texteApresCodage ) {
        HashMap<Character, String> codes = new HashMap<>();
        arbreCode.remplirTable( codes );
        // System.out.println( arbreCode );
        //System.out.println( "codes : " + codes );
        encodeTexte( texte, codes, texteApresCodage );
    }

    /**
     * convertit une HashMap<Character, Feuille> en une liste List<Feuille>
     * @param map
     * @return
     */
    static List<Feuille> mapToList( HashMap<Character, Feuille> map ) {
        ArrayList<Feuille> listFeuilles = new ArrayList<>();
        // http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator<Entry<Character, Feuille>> it = map.entrySet().iterator();
        while ( it.hasNext() ) {
            Entry<Character, Feuille> pair = it.next();
            listFeuilles.add( pair.getValue() );
            // System.out.println( pair.getKey() + " = " + pair.getValue() );
            // it.remove(); // avoids a ConcurrentModificationException
        }
        return listFeuilles;
    }

    public static Noeud construitCode( HashMap<Character, Feuille> lettres ) throws TasVideException {
        return construitCode( mapToList( lettres ) );
    }
}
