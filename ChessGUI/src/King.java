import java.awt.image.BufferedImage;

public class King extends ChessPiece{

    public King(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {//move legal for a king allows it to move like a king
        dc=loc.getCol()-dest.getCol();
        dr=loc.getRow()-dest.getRow();
        dc=Math.abs(dc);
        dr=Math.abs(dr);
        if(loc.getBoard().blocked(loc,dest)==false)//checks if blocked
            return false;
        if((dest.getChessPiece()!=null && dest.getChessPiece().getColor()==loc.getChessPiece().getColor()))//no killing own team
            return false;
        if((dc>1||dr>1)||(dest==loc))//returns false if moved too far
            return false;
        else//otherwise it allows the move
            return true;
    }
    public boolean isKing(){
        return true;
    }
}
