import java.awt.image.BufferedImage;

public class Knight extends ChessPiece{

    public Knight(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {//move legal for a knight allows it to move like a knight
        dc=Math.abs(loc.getCol()-dest.getCol());
        dr=Math.abs(loc.getRow()-dest.getRow());
        if((dest.getChessPiece()!=null && dest.getChessPiece().getColor()==loc.getChessPiece().getColor()))//not allowed to kill teamates
            return false;
        if((dc==2&&dr==1)||(dc==1&&dr==2))//the movement for a knight that is acceptable
            return true;
        else//those that are not
            return false;
    }

    public boolean isKing() { return false; }
}
