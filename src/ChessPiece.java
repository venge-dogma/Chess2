import java.util.*;

public abstract class ChessPiece//Dean Viner
{
    private TeamColor team;
    private Position location;
    private int counter;
    
    public ChessPiece( TeamColor color, Position a )
    {
        team = color;
        location = a;
        counter = 0;
    }
    
    public TeamColor getColor()
    {
        return team;
    }
    
    public int getNumMoves()
    {
        return counter;
    }
    
    public Position getPosition()
    {
        return location;
    }
    
    public void setPosition( Position x )
    {
        location = x;
    }
    public abstract String getImageName();
}