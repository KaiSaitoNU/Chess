import java.awt.image.BufferedImage;

public class Queen extends ChessPiece{

    public Queen(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest){//move legal for a queen allows it to move like a queen
        Rook r = new Rook(null, team, loc);//make a rook
        Bishop b = new Bishop(null, team, loc);//make a bishop
        if(loc.getBoard().blocked(loc,dest)==false)//check if blocked
            return false;
        if((dest.getChessPiece()!=null && dest.getChessPiece().getColor()==loc.getChessPiece().getColor()))//no killing own team
            return false;
        return (b.isMoveLegal(dest)||r.isMoveLegal(dest))&&(dest!=loc);//moves like a rook and bishop
    }

    public boolean isKing() { return false; }

}
