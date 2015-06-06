

public class Pawn extends ChessPiece//Dean Viner
{
    private boolean enPassant;
    
    public Pawn( TeamColor color, Position place )
    {
        super(color, place);
        enPassant = false;
    }
    
    public void setPassant(boolean set)
    {
        enPassant = set;
    }
    
    public boolean getPassant()
    {
        return enPassant;
    }
    
    @Override
    public String getImageName()
    {
        if( super.getColor() == TeamColor.WHITE )
            return "pawn w";
        return "pawn b";
    }
}