package main.test;

/**
 * classe représentant les informations à propos le mot
 * utilisée dans la version de l'algorithme de Huffman
 * traitant les mots
 * @author Stoufa
 *
 */
class InfoMot {
    int num;   // numéro du mot
    int nbOcc; // nombre d'occurrences

    public InfoMot( int num, int nbOcc ) {
        this.num = num;
        this.nbOcc = nbOcc;
    }

    @Override
    public String toString() {
        return String.format( "#%d|%d", num, nbOcc );
    }

    public boolean plusPrioritaireQue( InfoMot infoMot ) {
        return nbOcc < infoMot.nbOcc;
    }

    public boolean moinsPrioritaireQue( InfoMot infoMot ) {
        return nbOcc > infoMot.nbOcc;
    }
}
