import java.awt.image.BufferedImage;

public class Pawn extends ChessPiece {

    public Pawn(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {
        delta_row = loc.getRow() - dest.getRow();
        delta_col = loc.getCol() - dest.getCol();

        //checks if the piece is blocked
        if (!loc.getBoard().blocked(loc, dest))
            return false;

        //can't attack your own team
        if ((dest.getChessPiece() != null && dest.getChessPiece().getTeam() == team))
            return false;

        //bottom pawns
        if (!team) {
            //if it has never moved and there is a piece to kill, it can move diagonally
            if (loc.getRow() == 6 && dest.isPiece())
                return (delta_row == 1 && Math.abs(delta_col) == 1);

            //if it has never moved it can jump one or two
            if (loc.getRow() == 6)
                return ((delta_row == 1 || delta_row == 2) && delta_col == 0);

            //if there is a piece in the attack spot, move diagonally
            if (dest.isPiece())
                return (delta_row == 1 && Math.abs(delta_col) == 1);

            return (delta_row == 1 && delta_col == 0);
        }

        //top pawns
        else {
            //if it has never moved and there is a piece to kill, then it can move diagonally
            if (loc.getRow() == 1 && dest.isPiece())
                return (delta_row == -1 && Math.abs(delta_col) == 1);

            //if it has never moved, it can jump one or two
            if (loc.getRow() == 1)
                return (delta_row == -1 || delta_row == -2) && delta_col == 0;

            //if there is a piece in the destination, then it can move diagonally
            if (dest.isPiece())
                return (delta_row == -1 && Math.abs(delta_col) == 1);

            return (delta_row == -1) && delta_col == 0;
        }
    }

    public boolean isKing() {
        return false;
    }
}
