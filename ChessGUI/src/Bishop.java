import java.awt.image.BufferedImage;

public class Bishop extends ChessPiece{

    public Bishop(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {//move legal for a bishop allows it to move like a bishop
        dc=loc.getCol()-dest.getCol();//change in column
        dr=loc.getRow()-dest.getRow();//change in row
        if(loc.getBoard().blocked(loc,dest)==false)//checks if blocked
            return false;
        if((dest.getChessPiece()!=null && dest.getChessPiece().getColor()==loc.getChessPiece().getColor()))//no killing own team
            return false;
        return ((Math.abs(dc/dr)==1));//returns true if it moves diagonal
    }

    public boolean isKing() { return false; }
}
