import java.awt.image.BufferedImage;

public class Bishop extends ChessPiece {

    public Bishop(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {
        delta_col = loc.getCol() - dest.getCol();
        delta_row = loc.getRow() - dest.getRow();

        //checks if piece is blocked
        if (!loc.getBoard().blocked(loc, dest))
            return false;

        //can't attack your own team
        if ((dest.getChessPiece() != null && dest.getChessPiece().getTeam() == team))
            return false;

        //returns true if the square is in a diagonal location
        return ((Math.abs(delta_col / delta_row) == 1));
    }

    public boolean isKing() {
        return false;
    }
}
