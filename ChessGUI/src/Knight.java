import java.awt.image.BufferedImage;

public class Knight extends ChessPiece {

    public Knight(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {
        delta_col = Math.abs(loc.getCol() - dest.getCol());
        delta_row = Math.abs(loc.getRow() - dest.getRow());

        //can't attack your own team
        if ((dest.getChessPiece() != null && dest.getChessPiece().getTeam() == team))
            return false;

        //any 2-1 move is legal
        return ((delta_col == 2 && delta_row == 1) || (delta_col == 1 && delta_row == 2));
    }

    public boolean isKing() {
        return false;
    }
}
