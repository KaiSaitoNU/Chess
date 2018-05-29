import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square extends JPanel implements MouseListener {
    public static GameBoard board;
    public boolean highlighted = false;
    private Color paint;
    private ChessPiece piece;
    private int row, col;

    public Square(int r, int c, Color p) {
        row = r;
        col = c;
        paint = p;
        setBackground(p);
        addMouseListener(this);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void mouseClicked(MouseEvent e) {
    }

    //If the mouse is hovering over the square, color it blue
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.BLUE);
    }

    //Colors the square
    public void setBackground(Color e) {
        if (!highlighted)
            super.setBackground(e);
    }

    //Returns the color of the square to the default when the mouse leaves the square
    public void mouseExited(MouseEvent e) {
        restore();
    }

    //override paintComponent
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (piece != null)
            piece.draw(g);
    }

    //Calls GameBoard's clicked function if the mouse is pressed
    public void mousePressed(MouseEvent e) {
        board.clicked(this);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public ChessPiece getChessPiece() {
        return piece;
    }

    public boolean isPiece() {
        return piece != null;
    }

    //Place a piece on a square
    public void setPiece(ChessPiece chessPiece) {
        piece = chessPiece;
        repaint();
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public GameBoard getBoard() {
        return board;
    }

    public void restore() {
        setBackground(paint);
    }
}
