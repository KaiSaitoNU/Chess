import java.awt.image.BufferedImage;

public class Queen extends ChessPiece {

    public Queen(BufferedImage im, boolean tm, Square lc) {
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {
        Rook r = new Rook(null, team, loc);
        Bishop b = new Bishop(null, team, loc);

        //checks if piece is blocked
        if (!loc.getBoard().blocked(loc, dest))
            return false;

        //can't attack your own team
        if ((dest.getChessPiece() != null && dest.getChessPiece().getTeam() == team))
            return false;

        //queens moves like a rook-bishop hybrid
        return (b.isMoveLegal(dest) || r.isMoveLegal(dest)) && (dest != loc);
    }

    public boolean isKing() {
        return false;
    }
}
