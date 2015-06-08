public final class Position//Ali Zolfaghari
{
    private final int x, y;
    
    public Position( int a, int b )
    {
        x = a;
        y = b;
    }
    
    public int getx()
    {
        return x;
    }
    
    public int gety()
    {
        return y;
    }
    
    public boolean equalsPosition( Position q )
    {
        return ( x == q.getx() && y == q.gety() );
    }
    
    public boolean isValidPosition()
    {
        if( x < 1 
            || x > 8 
            || y < 1 
            || y > 8 )
            return false;
        return true;
    }
}
