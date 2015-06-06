

public class Bishop extends ChessPiece //Ali Zolfahari
{
    public Bishop( TeamColor color, Position place )
    {
        super(color, place);
    }
    
    @Override
    public String getImageName()
    {
        if( super.getColor() == TeamColor.WHITE )
            return "bishop w";
        return "bishop b";
    }
}
