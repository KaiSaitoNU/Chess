import java.awt.image.BufferedImage;

public class Rook extends ChessPiece{

    public Rook(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest){//move legal for a rook allows it to move like a rook
        dc=loc.getCol()-dest.getCol();
        dr=loc.getRow()-dest.getRow();
        if(loc.getBoard().blocked(loc,dest)==false)//check if blocked
            return false;
        if((dest.getChessPiece()!=null && dest.getChessPiece().getColor()==loc.getChessPiece().getColor()))//no killing own team
            return false;
        return ((dc==0||dr==0)&&(dest!=loc));//moves up down left right
    }

    public boolean isKing() { return false; }
}

