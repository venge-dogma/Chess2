
public class Queen extends ChessPiece //Daniel Fang
{
    public Queen( TeamColor color, Position place )
    {
        super(color, place);
    }
    
    @Override
    public String getImageName()
    {
        if( super.getColor() == TeamColor.WHITE )
            return "queen w";
        return "queen b";
    }
}
