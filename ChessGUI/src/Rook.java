import java.awt.image.BufferedImage;

public class Rook extends ChessPiece {

    public Rook(BufferedImage im, boolean tm, Square lc) {//Constructor
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

        //can move anywhere vertically or horizontally
        return ((delta_col == 0 || delta_row == 0) && (dest != loc));
    }

    public boolean isKing() {
        return false;
    }
}

