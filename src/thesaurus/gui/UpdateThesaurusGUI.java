package thesaurus.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.google.gson.Gson;

import main.Copy;
import main.Huffman;

public class UpdateThesaurusGUI {

    private JFrame                                   frmUpdateThesaurus;
    static HashMap<String, HashMap<String, Integer>> wordsMap = new HashMap<>();
    private JTextArea                                taOutput;
    static Gson                                      gson     = new Gson();

    /**
     * Launch the application.
     */
    public static void main( String[] args ) {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        } catch ( Throwable e ) {
            e.printStackTrace();
        }
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    UpdateThesaurusGUI window = new UpdateThesaurusGUI();
                    window.frmUpdateThesaurus.setVisible( true );
                    window.frmUpdateThesaurus.setLocationRelativeTo( null ); // centered
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the application.
     */
    public UpdateThesaurusGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmUpdateThesaurus = new JFrame();
        frmUpdateThesaurus.setResizable( false );
        frmUpdateThesaurus.setTitle( "Update Thesaurus" );
        frmUpdateThesaurus.setBounds( 100, 100, 700, 400 );
        frmUpdateThesaurus.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frmUpdateThesaurus.getContentPane().setLayout( null );

        JButton btnUpdate = new JButton( "Update" );
        btnUpdate.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
                try {
                    update( "data" );
                    String dictionnary = gson.toJson( wordsMap );
                    StringBuffer res = new StringBuffer();
                    Huffman.encode2( dictionnary, res );
                    display( "=======================================" );
                    display( res.toString() );
                    //double tauxCompression = res.toString().length() / dictionnary.length() * 100;
                    //System.out.println( "taux de compression : " + tauxCompression );
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        } );
        btnUpdate.setBounds( 10, 327, 664, 23 );
        frmUpdateThesaurus.getContentPane().add( btnUpdate );

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds( 10, 11, 664, 305 );
        frmUpdateThesaurus.getContentPane().add( scrollPane );

        taOutput = new JTextArea();
        scrollPane.setViewportView( taOutput );
        taOutput.setLineWrap( true );
    }

    protected void update( String path ) throws IOException {
        File folder = new File( path );
        File[] listOfFiles = folder.listFiles();

        for ( int i = 0; i < listOfFiles.length; i++ ) {
            if ( listOfFiles[i].isFile() ) {
                if ( listOfFiles[i].getName().endsWith( ".txt" ) ) { // .txt file
                    //System.out.println( "[TextFile] : " + listOfFiles[i].getName() );

                    display( "============= " + listOfFiles[i].getName() + " =============" );
                    wordsMap.put( listOfFiles[i].getName(),
                            (HashMap<String, Integer>) getWords( path + "/" + listOfFiles[i].getName() ) );
                    //System.out.println( res );

                    //taOuput.setText( taOuput.getText() + "\n" + listOfFiles[i].getName() );

                } else {
                    //System.out.println( "[File] : " + listOfFiles[i].getName() );
                }
            } else if ( listOfFiles[i].isDirectory() ) {
                //System.out.println( "[Directory] : " + listOfFiles[i].getName() );
                update( path + "/" + listOfFiles[i].getName() );
            }
        }
    }

    public Map<String, Integer> getWords( String path ) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        String[] wordsArr = Copy.getWords( path );
        for ( String word : wordsArr ) {
            if ( map.containsKey( word ) ) {
                Integer frequency = map.get( word );
                map.put( word, frequency + 1 );
                display( String.format( "%s ( %d )", word, frequency + 1 ) ); // display the word
            } else {
                map.put( word, new Integer( 1 ) ); // 1st appearence
                display( String.format( "%s ( %d )", word, 1 ) ); // display the word
            }
        }
        return map;
    }

    public void display( String str ) {
        String newStr = taOutput.getText();
        if ( !newStr.isEmpty() ) { // if the text isn't empty, ...
            newStr = newStr + "\n"; // ... add  \n
        }
        newStr = newStr + str;
        taOutput.setText( newStr );
    }
}
