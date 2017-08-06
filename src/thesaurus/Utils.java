package thesaurus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import main.Copy;
import main.Huffman;

public class Utils {

    static Gson                                      gson     = new Gson();
    //static List<String> list = new ArrayList<>();
    //static HashMap<String, Integer> wordsMap = new HashMap<>();
    static HashMap<String, HashMap<String, Integer>> wordsMap = new HashMap<>();

    public static void getFilenames( String path ) throws IOException {
        File folder = new File( path );
        File[] listOfFiles = folder.listFiles();

        //        List<String> words = new ArrayList<>();

        for ( int i = 0; i < listOfFiles.length; i++ ) {
            if ( listOfFiles[i].isFile() ) {
                if ( listOfFiles[i].getName().endsWith( ".txt" ) ) { // .txt file
                    System.out.println( "[TextFile] : " + listOfFiles[i].getName() );
                    //list.add( listOfFiles[i].getName() );

                    //System.out.println( "File's content : " );
                    //readFile( path + "/" + listOfFiles[i].getName() );
                    //System.out.println( "=================" );

                    //                    System.out.println(
                    //                            gson.toJson(
                    //                                    Copy.getWords( path + "/" + listOfFiles[i].getName() ) ) );

                    //                    String[] wordsArr = Copy.getWords( path + "/" + listOfFiles[i].getName() );
                    //                    for ( String word : wordsArr ) {
                    //                        if ( !words.contains( word ) ) {
                    //                            words.add( word );
                    //                        }
                    //                    }
                    //                    System.out.println( words );

                    //                    String[] wordsArr = Copy.getWords( path + "/" + listOfFiles[i].getName() );
                    //                    for ( String word : wordsArr ) {
                    //                        if ( wordsMap.containsKey( word ) ) {
                    //                            Integer frequency = wordsMap.get( word );
                    //                            wordsMap.put( word, frequency + 1 );
                    //                        } else {
                    //                            wordsMap.put( word, new Integer( 1 ) ); // 1st appearence
                    //                        }
                    //                    }

                    wordsMap.put( listOfFiles[i].getName(),
                            (HashMap<String, Integer>) getWords( path + "/" + listOfFiles[i].getName() ) );

                } else {
                    System.out.println( "[File] : " + listOfFiles[i].getName() );
                }
            } else if ( listOfFiles[i].isDirectory() ) {
                System.out.println( "[Directory] : " + listOfFiles[i].getName() );
                //list.addAll( getFilenames( path + "/" + listOfFiles[i].getName() ) );
                getFilenames( path + "/" + listOfFiles[i].getName() );
            }
        }

        //return list;
    }

    public static void readFile( String path ) {
        try {
            File file = new File( path );
            Scanner input = new Scanner( file );
            while ( input.hasNextLine() ) {
                String line = input.nextLine();
                System.out.println( line );
            }
            input.close();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public static Map<String, Integer> getWords( String path ) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        String[] wordsArr = Copy.getWords( path );
        for ( String word : wordsArr ) {
            if ( map.containsKey( word ) ) {
                Integer frequency = map.get( word );
                map.put( word, frequency + 1 );
            } else {
                map.put( word, new Integer( 1 ) ); // 1st appearence
            }
        }
        return map;
    }

    public static void main( String[] args ) throws IOException {
        //List<String> tArrayList = Utils.getFilenames( "data" );
        Utils.getFilenames( "data" );
        //System.out.println( tArrayList );

        String dictionnary = gson.toJson( wordsMap );
        //System.out.println( dictionnary );

        StringBuffer res = new StringBuffer();
        Huffman.encode2( dictionnary, res );
        //        System.out.println( "--------------" );
        System.out.println( res );

        //        System.out.println( "========" );
        //        System.out.println( gson.toJson( wordsMap ) );
    }

}
