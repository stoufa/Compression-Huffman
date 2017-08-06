package main.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import main.Huffman;

public class HuffmanGUI {

    private JFrame      frmCompressionHuffman;
    private JScrollPane spTaInput;
    private JTextArea   taInput;
    private JTextArea   taOutput;
    private JTextArea   taOutputTree;
    private JScrollPane spTaOutputTree;
    private JScrollPane spTaOutput;

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
                    HuffmanGUI window = new HuffmanGUI();
                    window.frmCompressionHuffman.setVisible( true );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the application.
     */
    public HuffmanGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmCompressionHuffman = new JFrame();
        frmCompressionHuffman.setResizable( false );
        frmCompressionHuffman.setTitle( "Compression Huffman :: Mustapha SAHLI - 2 ING GLSI 1" );
        frmCompressionHuffman.setBounds( 100, 100, 534, 423 );
        frmCompressionHuffman.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frmCompressionHuffman.getContentPane().setLayout( null );
        frmCompressionHuffman.setLocationRelativeTo( null );

        JButton btnStart = new JButton( "Start" );
        btnStart.setBounds( 221, 350, 287, 23 );
        btnStart.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
                String inputText = taInput.getText();
                if ( inputText.trim().isEmpty() ) {
                    JOptionPane.showMessageDialog( null, "le texte ne doit pas être vide !", "erreur !",
                            JOptionPane.ERROR_MESSAGE );
                    return;
                }
                StringBuffer texteApresCodage = new StringBuffer();
                Huffman.encode( inputText, texteApresCodage );
                String outputText = texteApresCodage.toString();
                String treeStr = Huffman.arbreCodes.toString();
                taOutput.setText( outputText );
                taOutputTree.setText( treeStr );
            }
        } );
        frmCompressionHuffman.getContentPane().add( btnStart );

        spTaInput = new JScrollPane();
        spTaInput.setBounds( 10, 11, 199, 362 );
        spTaInput.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        frmCompressionHuffman.getContentPane().add( spTaInput );

        taInput = new JTextArea();
        taInput.setText( "lorem ipsum dolor set amet" );
        taInput.setLineWrap( true );
        spTaInput.setViewportView( taInput );

        spTaOutput = new JScrollPane();
        spTaOutput.setBounds( 221, 106, 287, 233 );
        frmCompressionHuffman.getContentPane().add( spTaOutput );

        taOutput = new JTextArea();
        spTaOutput.setViewportView( taOutput );
        taOutput.setLineWrap( true );

        spTaOutputTree = new JScrollPane();
        spTaOutputTree.setBounds( 221, 12, 287, 83 );
        frmCompressionHuffman.getContentPane().add( spTaOutputTree );

        taOutputTree = new JTextArea();
        taOutputTree.setLineWrap( true );
        spTaOutputTree.setViewportView( taOutputTree );
    }
}
