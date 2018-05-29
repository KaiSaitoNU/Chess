import java.awt.image.BufferedImage;

public class King extends ChessPiece {

    public King(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {
        delta_col = Math.abs(loc.getCol() - dest.getCol());
        delta_row = Math.abs(loc.getRow() - dest.getRow());

        //checks if piece is blocked
        if (!loc.getBoard().blocked(loc, dest))
            return false;

        //can't attack your own team
        else if ((dest.getChessPiece() != null && dest.getChessPiece().getTeam() == team))
            return false;

        //returns false if you don't choose a surrounding square
        if ((delta_col > 1 || delta_row > 1) || (dest == loc))
            return false;

        return true;
    }

    public boolean isKing() {
        return true;
    }
}
