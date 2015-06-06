

public class Rook extends ChessPiece//Andy Chao
{
    private boolean canCastle;
    
    public Rook( TeamColor color, Position place )
    {
        super(color, place);
        canCastle = true;
    }
    
    public boolean getCastle()
    {
        return canCastle;
    }
    
    public void changeCastle()
    {
        canCastle = false;
    }
    
    @Override
    public String getImageName()
    {
        if( super.getColor() == TeamColor.WHITE )
            return "rook w";
        return "rook b";
    }
}
