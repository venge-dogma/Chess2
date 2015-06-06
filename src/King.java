

public class King extends ChessPiece//Daniel Fang
{
    private boolean canCastle;
    
    public King( TeamColor color, Position place )
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
            return "king w";
        return "king b";
    }
}