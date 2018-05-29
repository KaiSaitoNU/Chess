import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameBoard extends JFrame {
    private static final int ROWS = 8, COLS = 8;
    private Square[][] squares;//array of squares
    private Vector b_pieces = new Vector<ChessPiece>(); //array of black pieces
    private Vector w_pieces = new Vector<ChessPiece>(); //array of white pieces
    private BufferedImage[] sprites = new BufferedImage[12];
    private Square first_click, second_click;//first clicked square and last
    private int tracker = 1;//tracks who's turn it should be birthed

    private GameBoard() {
        super("CHESS");
        BufferedImage img = null;
        this.setLayout(new GridLayout(ROWS, COLS));
        Square.board = this;
        squares = new Square[ROWS][COLS];

        // Adds Squares to the GameBoard
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((c + r) % 2 == 0)
                    squares[r][c] = new Square(r, c, Color.WHITE);
                else
                    squares[r][c] = new Square(r, c, Color.BLACK);
                this.add(squares[r][c]);

            }
        }
        this.setSize(750, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        try {
            img = ImageIO.read(new File("sprites.png"));
        } catch (IOException e) {
        }

        int width = 64;
        int height = 64;
        int rows = 2;
        int cols = 6;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sprites[(i * cols) + j] = img.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }

        //Creates all the pawns
        for (int i = 0; i < 8; i++) {
            Pawn d = new Pawn(sprites[11], true, squares[1][i]);
            Pawn r = new Pawn(sprites[5], false, squares[6][i]);

            w_pieces.addElement(d);
            b_pieces.addElement(r);

            squares[1][i].setPiece(d);
            squares[6][i].setPiece(r);
        }

        //Creates all the knights
        for (int i = 0; i < 8; i += 7) {
            for (int j = 1; j < 7; j += 5) {
                if (i == 0) {
                    Knight d = new Knight(sprites[10], true, squares[i][j]);
                    w_pieces.addElement(d);
                    squares[i][j].setPiece(d);
                } else {
                    Knight r = new Knight(sprites[4], false, squares[i][j]);
                    b_pieces.addElement(r);
                    squares[i][j].setPiece(r);
                }
            }
        }

        //Creates all the rooks
        for (int i = 0; i < 8; i += 7) {
            for (int j = 0; j < 8; j += 7) {
                if (i == 0) {
                    Rook d = new Rook(sprites[8], true, squares[i][j]);
                    w_pieces.addElement(d);
                    squares[i][j].setPiece(d);
                } else {
                    Rook r = new Rook(sprites[2], false, squares[i][j]);
                    b_pieces.addElement(r);
                    squares[i][j].setPiece(r);
                }
            }
        }

        //Creates all the bishops
        for (int i = 0; i < 8; i += 7) {
            for (int j = 2; j < 6; j += 3) {
                if (i == 0) {
                    Bishop d = new Bishop(sprites[9], true, squares[i][j]);
                    w_pieces.addElement(d);
                    squares[i][j].setPiece(d);
                } else {
                    Bishop r = new Bishop(sprites[3], false, squares[i][j]);
                    b_pieces.addElement(r);
                    squares[i][j].setPiece(r);
                }
            }
        }

        King d_King = new King(sprites[6], true, squares[0][4]);
        w_pieces.addElement(d_King);
        squares[0][4].setPiece(d_King);

        King r_King = new King(sprites[0], false, squares[7][4]);
        b_pieces.addElement(r_King);
        squares[7][4].setPiece(r_King);

        Queen d_Queen = new Queen(sprites[7], true, squares[0][3]);
        w_pieces.addElement(d_Queen);
        squares[0][3].setPiece(d_Queen);

        Queen r_Queen = new Queen(sprites[1], false, squares[7][3]);
        b_pieces.addElement(r_Queen);
        squares[7][3].setPiece(r_Queen);
    }

    public static void main(String[] args) {
        new GameBoard();
    }

    private boolean turn() {
        return (tracker % 2 == 0);
    }

    public void clicked(Square s) {
        if (first_click == null) {//first click
            if (s.getChessPiece() == null)//not allowed to click an empty square
                return;
            if (turn() != s.getChessPiece().getTeam()) {//not allowed to move if its not your turn
                JOptionPane.showMessageDialog(this, "Not your turn");
                return;
            }
            first_click = s;
            first_click.setBackground(Color.BLUE);//when clicked color of square changes
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if (first_click.getChessPiece().isMoveLegal(squares[r][c])) {//checks possible moves
                        squares[r][c].setBackground(Color.RED);//highlights the squares
                        squares[r][c].highlighted = true;//tells the variable  that the squares have been highlighted
                    }
                }
            }
        } else {//second click
            second_click = s;
            if ((first_click.getChessPiece().isMoveLegal(second_click))) {//checks if the move is legal to the new loc
                if (first_click.getChessPiece().isAttackMove(second_click)) {
                    if (second_click.getChessPiece().getTeam())
                        w_pieces.remove(second_click.getChessPiece());
                    else
                        b_pieces.remove(second_click.getChessPiece());
                }
                first_click.getChessPiece().move(second_click);//this is where it makes it move
                tracker++;//tracks the number of turns that have passes
                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        squares[r][c].highlighted = false;//tells the variable that is not going to be highlighted anymore
                        squares[r][c].restore();//un-highlights the possible moves
                    }
                }
            } else
                JOptionPane.showMessageDialog(this, "FALSE MOVE");//returns a statement to tell you if you made a false move
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    squares[r][c].highlighted = false;//if it was a false move take off the highlighted moves
                    squares[r][c].restore();
                }
            }
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    for (int r2 = 0; r2 < 8; r2++) {
                        for (int c2 = 0; c2 < 8; c2++) {
                            if (squares[r][c].getChessPiece() != null && squares[r2][c2].getChessPiece() != null) {
                                if (squares[r][c].getChessPiece().isMoveLegal(squares[r2][c2]) && squares[r2][c2].getChessPiece().isKing())
                                    JOptionPane.showMessageDialog(this, "CHECK");
                            }
                        }
                    }
                }
            }
            first_click.restore();//restores everything
            second_click.restore();
            first_click = null;
        }
    }

    public boolean blocked(Square orig, Square dest) {//blocks pieces function
        int R = orig.getRow(), C = orig.getCol();
        int changeRow = dest.getRow() - R;
        int changeCol = dest.getCol() - C;
        int deltaRow = 0, deltaCol = 0;
        if (changeRow != 0) {
            deltaRow = changeRow / Math.abs(changeRow);
            R += deltaRow;
        }
        if (changeCol != 0) {
            deltaCol = changeCol / Math.abs(changeCol);
            C += deltaCol;
        }
        if (squares[R][C] == dest)
            return true;
        while (isValid(R, C) && squares[R][C] != dest) {//this is where it checks each square if its blocked
            if (squares[R][C].getChessPiece() == null) {//keeps going if its empty
                R += deltaRow;
                C += deltaCol;
            } else
                return false;
        }
        return true;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < ROWS && c < COLS;
    }
}
