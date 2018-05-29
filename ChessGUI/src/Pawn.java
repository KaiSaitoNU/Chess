import java.awt.image.BufferedImage;

public class Pawn extends ChessPiece{

    public Pawn(BufferedImage im, boolean tm, Square lc) {//Constructor
        super(im, tm, lc);
    }

    public boolean isMoveLegal(Square dest) {//move legal for a pawn allows it to move like a pawn
        dr=loc.getRow()-dest.getRow();
        dc=loc.getCol()-dest.getCol();
        if(loc.getBoard().blocked(loc,dest)==false)//checks if blocked
            return false;
        if((dest.getChessPiece()!=null && dest.getChessPiece().getColor()==loc.getChessPiece().getColor()))//no killing own team
            return false;
        if(team==false){//bottom
            if(loc.getRow()==6 && dest.isPiece())//if it has never moved and there is a piece to kill
                return(dr==1&&Math.abs(dc)==1);//then it can kill diagonally
            if(loc.getRow()==6)//if it has never moved
                return ((dr==1||dr==2)&& dc==0);//then it can move one or two
            else if(dest.isPiece())//if there is a piece in the destination
                return(dr==1&&Math.abs(dc)==1);//move diagonally to kill it
            else
                return (dr==1) && dc==0;//otherwise move only one
        }
        else{//top
            if(loc.getRow()==1 && dest.isPiece())//if it has never moved and there is a piece to kill
                return(dr==-1&&Math.abs(dc)==1);//then it can kill diagonally
            if(loc.getRow()==1)//if it has never moved
                return (dr==-1||dr==-2)&& dc==0;//then it can move one or two
            else if(dest.isPiece())//if there is a piece in the destination
                return(dr==-1&&Math.abs(dc)==1);//move diagonally to kill it
            else
                return (dr==-1)&& dc==0;//otherwise move only one
        }
    }

    public boolean isKing() { return false; }
}
