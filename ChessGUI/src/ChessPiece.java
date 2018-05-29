import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ChessPiece {
    protected boolean team;//team its on
    protected Square loc;//square its on
    protected BufferedImage img;//image of piece
    protected double delta_row, delta_col;

    public ChessPiece(BufferedImage im, boolean tm, Square lc) {
        img = im;
        team = tm;
        loc = lc;
    }

    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, 90, 90, null, null);
    }

    public abstract boolean isMoveLegal(Square dest);

    public abstract boolean isKing();

    public boolean isAttackMove(Square dest) {
        return dest.getChessPiece() != null && team != dest.getChessPiece().getTeam();
    }

    public void move(Square dest) {
        dest.setPiece(this);
        loc.setPiece(null);
        loc = dest;
    }

    public boolean getTeam() {
        return team;
    }
}
