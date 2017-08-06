package thesaurus;

public class WordInfo {

    private String word;
    private int    frequency;
    private String filename;

    public WordInfo( String word, int frequency, String filename ) {
        super();
        this.word = word;
        this.frequency = frequency;
        this.filename = filename;
    }

    public String getWord() {
        return word;
    }

    public void setWord( String word ) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency( int frequency ) {
        this.frequency = frequency;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename( String filename ) {
        this.filename = filename;
    }

}
