import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

public class Square extends JPanel implements MouseListener{
    //private member variables
    private JPanel colorPane;
    protected Color paint;
    public static GameBoard board;
    private int row, col;
    private Color OC;
    protected ChessPiece piece;
    private boolean first=false;
    public boolean highlighted=false;

    public Square(int r, int c, Color p){//construtor
        row=r;
        col=c;
        paint=p;
        this.setBackground(p);
        this.addMouseListener(this);
    }
    //accesors
    public int getRow(){return row;}
    public int getCol(){return col;}
    public Color getColour(){return paint;}

    public void mouseClicked(MouseEvent e) {}//i dont use this

    public void mouseEntered(MouseEvent e) {//when the mouse enters the color of the square changes
        this.setBackground(Color.BLUE);//makes it blue
    }
    public void setBackground(Color e){//sets the background of the square
        if(!highlighted)//if its not highlighted
            super.setBackground(e);//then restore the background
    }
    public void mouseExited(MouseEvent e) {//when mouse leaves the square
        this.setBackground(paint);//then the background should change back
    }
    //override paintComponent
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(piece != null)
            piece.draw(g);
    }

    public void mousePressed(MouseEvent e) {//used so when you press the mouse it will count instead of click
        board.clicked(this);//tells the board its been clicked
    }

    public void mouseReleased(MouseEvent e) {}//not used

    public void setPiece(ChessPiece chessPiece) {//sets the piece on a square
        piece=chessPiece;
        this.repaint();
    }
    public ChessPiece getChessPiece(){//gets the chess piece
        return piece;
    }
    public boolean isPiece(){//if there is piece
        return piece!=null;
    }
    public String toString(){//toString to help out
        return "("+row+","+col+")";
    }
    public GameBoard getBoard(){//accesing game board
        return board;
    }
    public boolean equals(Square other){
        return row==other.row && col==other.col;
    }
    public void restore(){
        this.setBackground(paint);
    }
}
