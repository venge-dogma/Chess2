import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

//Reference taken from Andrew Thompson http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
public class GUI implements ActionListener
{
    
    private JPanel mainPanel = new JPanel( new BorderLayout( 3, 3 ) );
    private JButton[][] squares;
    private JPanel board = new JPanel();
    private JFrame frame = new JFrame( "Chess" );
    private boolean firstSelected;
    private Position first;
    private Position second;
    private ChessBoard Board = new ChessBoard();

        public GUI()
        {
            squares = new JButton[8][8];
            firstSelected = false;
            frame.setSize( 900, 900 );
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

            board.setLayout( new GridLayout( 8, 8 ) );

            Insets square = new Insets( 0, 0, 0, 0 );
            for( int i = 0; i < squares.length; i++ )
               for( int j = 0; j < squares[ i ].length; j++ )
               {
                   JButton button = new JButton();
                   button.setMargin( square );
                   if( i % 2 == 1 && j % 2 == 1 || i % 2 == 0 && j % 2 == 0 )
                       button.setBackground( Color.WHITE );
                   else
                       button.setBackground( Color.BLACK );
                   squares[ i ][ j ] = button;
               }

            //Dean Viner

            display( Board.getPiecesArray() );

            for( int i = 0; i < 8; i++)
            {
                for( int j = 0; j < 8; j++)
                {
                    squares[i][j].addActionListener( this );
                }
            }

            mainPanel.add( board );
            frame.add( mainPanel );
            frame.pack();
        }
    
    //Andy Chao
    public void display( ArrayList<ChessPiece> pieces )
    {
        for( int i = 0; i < squares.length; i++ )
            for( int j = 0; j < squares[ i ].length; j++ )
            {
                squares[ i ][ j ].setIcon( new ImageIcon( new BufferedImage(95, 95, BufferedImage.TYPE_INT_ARGB)) );
            }
        for( int i = 0; i < pieces.size(); i++ )
        {
            squares[ 8 - pieces.get( i ).getPosition().gety() ][ pieces.get( i ).getPosition().getx() - 1 ].setIcon( new ImageIcon( pieces.get( i ).getImageName() + ".png", "" ) );
        }
        
        for( int i = 0; i < squares.length; i++ )
            for( int j = 0; j < squares[ i ].length; j++ )
            {
                board.add( squares[ i ][ j ] );
            }
    }
    
    @Override
    public void actionPerformed( ActionEvent event )
    {
        if( !firstSelected )
        {
            for( int i = 0; i < 8; i++ )
            {
                for( int j = 0; j < 8; j++ )
                {
                    if( squares[i][j].equals(event.getSource()))
                    {
                        System.out.println( Board.getPiece(new Position( j+1, 8-i)) != null);
                        if( Board.getPiece(new Position( j+1, 8-i)) != null
                                && ((Board.getPiece(new Position( j+1, 8-i)).getColor() == TeamColor.WHITE
                                && Board.whiteTurn())
                                || (Board.getPiece(new Position( j+1, 8-i)).getColor() == TeamColor.BLACK
                                && !Board.whiteTurn())))
                        {
                            first = new Position( j + 1, 8 - i);
                            firstSelected = true;
                            System.out.println( "first");
                        }
                    }
                }
            }
        }
        else
        {
            for( int i = 0; i < 8; i++ )
            {
                for( int j = 0; j < 8; j++ )
                {
                    if( squares[i][j].equals(event.getSource()))
                    {
                        second = new Position( j + 1, 8 - i);
                        firstSelected = false;
                        Board.move( first, second );
                        display( Board.getPiecesArray() );
                        System.out.println("move");
                    }
                }
            }
        }
    }
    
    //Andy Chao
    public JFrame getFrame()
    {
        return frame;
    }
    
    public ChessBoard getBoard()
    {
        return Board;
    }
}