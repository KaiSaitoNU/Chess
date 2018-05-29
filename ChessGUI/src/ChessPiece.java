import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JOptionPane;

public abstract class ChessPiece {
    protected boolean team;//team its on
    protected Square loc;//square its on
    protected BufferedImage img;//image of piece
    protected double dr, dc;

    public ChessPiece(BufferedImage im, boolean tm, Square lc){//constructor
        img = im;
        team = tm;
        loc = lc;
    }

    public void draw(Graphics g){//draws the picture
        g.drawImage(img,0,0,90,90,null,null);
    }

    public abstract boolean isMoveLegal(Square dest);//determines if the move is allowed

    public abstract boolean isKing();

    public boolean isAttackMove(Square dest){
        return dest.getChessPiece() != null;
    }

    public void move(Square dest){//actually moves the piece
        dest.setPiece(this);
        loc.setPiece(null);
        loc=dest;

    }
    public boolean getColor(){return team;}//gets the team its on
    public Square getLoc(){
        return loc;
    }
}
