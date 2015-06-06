public class Knight extends ChessPiece//Andy Chao
{
    public Knight( TeamColor color, Position place )
    {
        super(color, place);
    }
    
    @Override
    public String getImageName()
    {
        if( super.getColor() == TeamColor.WHITE )
            return "knight w";
        return "knight b";
    }
}
