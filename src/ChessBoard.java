import java.util.*;

public class ChessBoard
{
    private ArrayList<ChessPiece> Pieces;
    private ArrayList<ChessPiece> Taken;
    private boolean whiteTurn;
    
    public ChessBoard()//Andy Chao
    {
        Taken = new ArrayList<ChessPiece>();
        Pieces = new ArrayList<ChessPiece>(32);
        for( int i = 1; i <= 8; i++)
        {
            Pieces.add( new Pawn(TeamColor.WHITE, new Position(i, 2)));
            Pieces.add( new Pawn(TeamColor.BLACK, new Position(i, 7)));
        }
        for( int i = 1; i <= 2; i++)
        {
            Pieces.add( new Rook( TeamColor.WHITE, new Position( 7*i - 6, 1) ));
            Pieces.add( new Rook( TeamColor.BLACK, new Position( 7*i - 6, 8) ));
            Pieces.add( new Bishop( TeamColor.WHITE, new Position( 3*i, 1) ));
            Pieces.add( new Bishop( TeamColor.BLACK, new Position( 3*i, 8) ));
            Pieces.add( new Knight( TeamColor.WHITE, new Position( 5*i - 3, 1) ));
            Pieces.add( new Knight( TeamColor.BLACK, new Position( 5*i - 3, 8) ));
        }
        Pieces.add( new King(TeamColor.WHITE, new Position(5,1)));
        Pieces.add( new King(TeamColor.BLACK, new Position(5,8)));
        Pieces.add( new Queen(TeamColor.WHITE, new Position(4,1)));
        Pieces.add( new Queen(TeamColor.BLACK, new Position(4,8)));
        whiteTurn = true;
    }
    
    public boolean whiteTurn()
    {
        return whiteTurn;
    }
    
    public void changeTurn()
    {
        whiteTurn = !whiteTurn;
    }
    
    public ArrayList<ChessPiece> getPiecesArray()
    {
        return Pieces;
    }
    
    public int getIndex(ChessPiece piece)
    {
        int count = 0;
        for( ChessPiece x : Pieces )
        {
            if( x.equals(piece))
                return count;
            count ++;
        }
        return -1;
    }
    
    public ChessPiece getPiece( int index )
    {
        return Pieces.get(index);
    }
    
    public ChessPiece getPiece( Position piece )//Daniel Fang
    {
        for( ChessPiece x : Pieces )
        {
            if( x.getPosition().equalsPosition( piece ) )
                return x;
        }
        return null;
    }
    
    public ArrayList<Position> potentialMove(Knight piece)//Andy Chao
    {
        ArrayList<Position> answer = new ArrayList<Position>();
        
        int x = piece.getPosition().getx();
        int y = piece.getPosition().gety();
        TeamColor color = piece.getColor();
        
        if( getPiece(new Position(x - 1, y + 2)) == null
                || getPiece(new Position(x - 1, y + 2)).getColor() != color )
            answer.add( new Position(x - 1, y + 2));
        
        if( getPiece(new Position(x + 1, y + 2)) == null
                || getPiece(new Position(x + 1, y + 2)).getColor() != color )
            answer.add( new Position(x + 1, y + 2));
        
        if( getPiece(new Position(x - 2, y + 1)) == null
                || getPiece(new Position(x - 2, y + 1)).getColor() != color )
            answer.add( new Position(x - 2, y + 1));
        
        if( getPiece(new Position(x + 2, y + 1)) == null
                || getPiece(new Position(x + 2, y + 1)).getColor() != color )
            answer.add( new Position(x + 2, y + 1));

        if( getPiece(new Position(x - 2, y - 1)) == null
                || getPiece(new Position(x - 2, y - 1)).getColor() != color )    
            answer.add( new Position(x - 2, y - 1));

        if( getPiece(new Position(x + 2, y - 1)) == null
                || getPiece(new Position(x + 2, y - 1)).getColor() != color )    
            answer.add( new Position(x + 2, y - 1));

        if( getPiece(new Position(x - 1, y - 2)) == null
                || getPiece(new Position(x - 1, y - 2)).getColor() != color )
            answer.add( new Position(x - 1, y - 2));

        if( getPiece(new Position(x + 1, y - 2)) == null
                || getPiece(new Position(x + 1, y - 2)).getColor() != color )    
            answer.add( new Position(x + 1, y - 2));
        
        return updatedMoves(answer);
        
    }
    
    public ArrayList<Position> potentialMove(Bishop piece)//Ali Zolfaghari
    {
        ArrayList<Position> answer = new ArrayList<Position>();
        
        int x = piece.getPosition().getx();
        int y = piece.getPosition().gety();
        
        TeamColor color = piece.getColor();
        
        while( x <= 8 && y <= 8 )
        {
            if( getPiece(new Position(x + 1, y + 1)) != null
                && getPiece(new Position(x + 1, y + 1)).getColor() != color )
            {
                answer.add( new Position( x + 1, y + 1));
                break;
            }
            if( getPiece(new Position(x + 1, y + 1)) != null
                    && getPiece(new Position(x + 1, y + 1)).getColor() == color )
                break;
            if( getPiece(new Position( x + 1, y + 1)) == null )
                answer.add( new Position( x + 1, y + 1));
            x++;
            y++;
        }
        
        x = piece.getPosition().getx();
        y = piece.getPosition().gety();
        
        while( x <= 8 && y >= 1 )
        {
            if( getPiece(new Position(x + 1, y - 1)) != null
                    && getPiece(new Position(x + 1, y - 1)).getColor() != color )
            {
                answer.add( new Position( x + 1, y - 1));
                break;
            }
            if( getPiece(new Position(x + 1, y - 1)) != null
                    && getPiece(new Position(x + 1, y - 1)).getColor() == color )
                break;
            if( getPiece(new Position( x + 1, y - 1)) == null )
                answer.add( new Position( x + 1, y - 1));
            x++;
            y--;
        }
        
        x = piece.getPosition().getx();
        y = piece.getPosition().gety();
        
        while( x >= 1 && y <= 8 )
        {
            if( getPiece(new Position(x - 1, y + 1)) != null
                    && getPiece(new Position(x - 1, y + 1)).getColor() != color )
            {
                answer.add( new Position( x - 1, y + 1));
                break;
            }
            if( getPiece(new Position(x - 1, y + 1)) != null
                    && getPiece(new Position(x - 1, y + 1)).getColor() == color )
                break;
            if( getPiece(new Position( x - 1, y + 1)) == null )
                answer.add( new Position( x - 1, y + 1));
            x--;
            y++;
        }
        
        x = piece.getPosition().getx();
        y = piece.getPosition().gety();
        
        while( x >= 1 && y >= 1 )
        {
            if( getPiece(new Position(x - 1, y - 1)) != null
                    && getPiece(new Position(x - 1, y - 1)).getColor() != color )
            {
                answer.add( new Position( x - 1, y - 1));
                break;
            }
            if( getPiece(new Position(x - 1, y - 1)) != null
                    && getPiece(new Position(x - 1, y - 1)).getColor() == color )
                break;
            if( getPiece(new Position( x - 1, y - 1)) == null )
                answer.add( new Position( x - 1, y - 1));
            x--;
            y--;
        }
        return updatedMoves(answer);
    }
    
    public ArrayList<Position> potentialMove(Rook piece)//Andy Chao
    {
        ArrayList<Position> answer = new ArrayList<Position>();
        
        int x = piece.getPosition().getx();
        int y = piece.getPosition().gety();
        
        while( y <= 8 )
        {
            if( getPiece(new Position(x, y + 1)) != null
                    && getPiece(new Position(x, y + 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x, y + 1));
                break;
            }
            if( getPiece(new Position(x, y + 1)) != null
                    && getPiece(new Position(x, y + 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x, y + 1)) == null )
                answer.add( new Position( x, y + 1));
            y++;
            
        }
        
        y = piece.getPosition().gety();
        
        while( y >= 1 )
        {
            if( getPiece(new Position(x, y - 1)) != null
                    && getPiece(new Position(x, y - 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x, y - 1));
                break;
            }
            if( getPiece(new Position(x, y - 1)) != null
                    && getPiece(new Position(x, y - 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x, y - 1)) == null )
                answer.add( new Position( x, y - 1));
            y--;
        }
        
        y = piece.getPosition().gety();
        
        while( x >= 1 )
        {
            if( getPiece(new Position(x - 1, y)) != null
                    && getPiece(new Position(x - 1, y)).getColor() != piece.getColor())
            {
                answer.add( new Position( x - 1, y));
                break;
            }
            if( getPiece(new Position(x - 1, y)) != null
                    && getPiece(new Position(x - 1, y)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x - 1, y)) == null )
                answer.add( new Position( x - 1, y));
            x--;
        }
        
        x = piece.getPosition().getx();
        
        while( x <= 8 )
        {
            if( getPiece(new Position(x + 1, y)) != null
                    && getPiece(new Position(x + 1, y)).getColor() != piece.getColor())
            {
                answer.add( new Position( x + 1, y));
                break;
            }
            if( getPiece(new Position(x + 1, y)) != null
                    && getPiece(new Position(x + 1, y)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x + 1, y)) == null )
                answer.add( new Position( x, y));
            x--;
        }
        
        return updatedMoves(answer);
    }
    
    public ArrayList<Position> potentialMove(Queen piece)//Daniel Fang
    {
        ArrayList<Position> answer = new ArrayList<Position>();
        
        int x = piece.getPosition().getx();
        int y = piece.getPosition().gety();
        
        while( y <= 8 )
        {
            if( getPiece(new Position(x, y + 1)) != null
                    && getPiece(new Position(x, y + 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x, y + 1));
                break;
            }
            if( getPiece(new Position(x, y + 1)) != null
                    && getPiece(new Position(x, y + 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x, y + 1)) == null )
                answer.add( new Position( x, y + 1));
            y++;
            
        }
        
        y = piece.getPosition().gety();
        
        while( y >= 1 )
        {
            if( getPiece(new Position(x, y - 1)) != null
                    && getPiece(new Position(x, y - 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x, y - 1));
                break;
            }
            if( getPiece(new Position(x, y - 1)) != null
                    && getPiece(new Position(x, y - 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x, y - 1)) == null )
                answer.add( new Position( x, y - 1));
            y--;
        }
        
        y = piece.getPosition().gety();
        
        while( x >= 1 )
        {
            if( getPiece(new Position(x - 1, y)) != null
                    && getPiece(new Position(x - 1, y)).getColor() != piece.getColor())
            {
                answer.add( new Position( x - 1, y));
                break;
            }
            if( getPiece(new Position(x - 1, y)) != null
                    && getPiece(new Position(x - 1, y)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x - 1, y)) == null )
                answer.add( new Position( x - 1, y));
            x--;
        }
        
        x = piece.getPosition().getx();
        
        while( x <= 8 )
        {
            if( getPiece(new Position(x + 1, y)) != null
                    && getPiece(new Position(x + 1, y)).getColor() != piece.getColor())
            {
                answer.add( new Position( x + 1, y));
                break;
            }
            if( getPiece(new Position(x + 1, y)) != null
                    && getPiece(new Position(x + 1, y)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x + 1, y)) == null )
                answer.add( new Position( x, y));
            x--;
        }
        
        x = piece.getPosition().getx();
        
        while( x <= 8 && y <= 8 )
        {
            if( getPiece(new Position(x + 1, y + 1)) != null
                    && getPiece(new Position(x + 1, y + 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x + 1, y + 1));
                break;
            }
            if( getPiece(new Position(x + 1, y + 1)) != null
                    && getPiece(new Position(x + 1, y + 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x + 1, y + 1)) == null )
                answer.add( new Position( x + 1, y + 1));
            x++;
            y++;
        }
        
        x = piece.getPosition().getx();
        y = piece.getPosition().gety();
        
        while( x <= 8 && y >= 1 )
        {
            if( getPiece(new Position(x + 1, y - 1)) != null
                    && getPiece(new Position(x + 1, y - 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x + 1, y - 1));
                break;
            }
            if( getPiece(new Position(x + 1, y - 1)) != null
                    && getPiece(new Position(x + 1, y - 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x + 1, y - 1)) == null )
                answer.add( new Position( x + 1, y - 1));
            x++;
            y--;
        }
        
        x = piece.getPosition().getx();
        y = piece.getPosition().gety();
        
        while( x >= 1 && y <= 8 )
        {
            if( getPiece(new Position(x - 1, y + 1)) != null
                    && getPiece(new Position(x - 1, y + 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x - 1, y + 1));
                break;
            }
            if( getPiece(new Position(x - 1, y + 1)) != null
                    && getPiece(new Position(x - 1, y + 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x - 1, y + 1)) == null )
                answer.add( new Position( x - 1, y + 1));
            x--;
            y++;
        }
        
        x = piece.getPosition().getx();
        y = piece.getPosition().gety();
        
        while( x >= 1 && y >= 1 )
        {
            if( getPiece(new Position(x - 1, y - 1)) != null
                    && getPiece(new Position(x - 1, y - 1)).getColor() != piece.getColor())
            {
                answer.add( new Position( x - 1, y - 1));
                break;
            }
            if( getPiece(new Position(x - 1, y - 1)) != null
                    && getPiece(new Position(x - 1, y - 1)).getColor() == piece.getColor())
                break;
            if( getPiece(new Position( x - 1, y - 1)) == null )
                answer.add( new Position( x - 1, y - 1));
            x--;
            y--;
        }
        return updatedMoves(answer);    
    }
    
    public ArrayList<Position> potentialMove( King piece )//Daniel Fang
    {
        ArrayList<Position> answer = new ArrayList<Position>();
        TeamColor color = piece.getColor();
        
        int x = piece.getPosition().getx();
        int y = piece.getPosition().gety();
        
        answer.add( new Position( x - 1, y ));
        answer.add( new Position( x + 1, y ));
        answer.add( new Position( x, y + 1 ));
        answer.add( new Position( x, y - 1 ));
        answer.add( new Position( x - 1, y - 1 ));
        answer.add( new Position( x - 1, y + 1 ));
        answer.add( new Position( x + 1, y - 1 ));
        answer.add( new Position( x + 1, y + 1 ));
        
        
        if( piece.getCastle())
        {
            for( int i = x - 1; i > 1; i-- )
            {
                if( getPiece(new Position( i, y )) != null || inCheck( new Position( i, y), piece.getColor()))
                {
                    break;
                }
                if( i == 2 
                        && getPiece( new Position( 1, y )) instanceof Rook
                        && getPiece( new Position( 1, y )).getColor() == color )
                {
                   if(((Rook)getPiece( new Position( 1, y ))).getCastle())
                       answer.add( new Position( x - 2, y));
                }
            }
            for( int i = x + 1; i < 8; i++ )
            {
                if( getPiece(new Position( i, y )) != null || inCheck( new Position( i, y), piece.getColor()))
                {
                    break;
                }
                if( i == 7 
                        && getPiece( new Position( 8, y )) instanceof Rook
                        && getPiece( new Position( 8, y )).getColor() == color )
                {
                   if(((Rook)getPiece( new Position( 8, y ))).getCastle())
                       answer.add( new Position( x + 2, y));
                }
            }
        }
        
        return updatedMoves(answer);
    }
    
    public ArrayList<Position> potentialMove( Pawn piece )//Dean Viner
    {
        ArrayList<Position> answer = new ArrayList<Position>();
        
        int x = piece.getPosition().getx();
        int y = piece.getPosition().gety();
        TeamColor color = piece.getColor();
        if( color == TeamColor.WHITE )
        {
            if( getPiece( new Position( x, y + 1)) == null )
            {
                answer.add( new Position( x, y + 1));
                if( getPiece( new Position( x, y + 2)) == null
                    && piece.getNumMoves() == 0 )
                {
                    answer.add( new Position( x, y + 2));
                }
            }
            for( ChessPiece q : Pieces )
            {
                if( q.getColor() != color)
                {
                    if(q.getPosition().equalsPosition(new Position( x + 1, y + 1))
                            || q.getPosition().equalsPosition(new Position( x - 1, y + 1)))
                        answer.add( q.getPosition());
                    if( q instanceof Pawn
                            && (q.getPosition().equalsPosition( new Position( x - 1, y))
                            || q.getPosition().equalsPosition( new Position( x + 1, y)))
                            &&((Pawn)q).getPassant())
                        answer.add(q.getPosition());
                        
                }
            }
        }
        if( color == TeamColor.BLACK)
        {
            if( getPiece( new Position( x, y - 1)) == null )
            {
                answer.add( new Position( x, y - 1));
                if( getPiece( new Position( x, y - 2)) == null
                    && piece.getNumMoves() == 0 )
                {
                answer.add( new Position( x, y - 2));
                }
            }
            
            for( ChessPiece q : Pieces )
            {
                if( q.getColor() != color)
                {
                    if(q.getPosition().equalsPosition(new Position( x + 1, y - 1))
                            || q.getPosition().equalsPosition(new Position( x - 1, y - 1)))
                        answer.add( q.getPosition());
                    if( q instanceof Pawn
                        && (q.getPosition().equalsPosition( new Position( x - 1, y))
                        || q.getPosition().equalsPosition( new Position( x + 1, y)))
                        &&((Pawn)q).getPassant())
                    answer.add(q.getPosition());
                }
            }
        }
        return updatedMoves(answer);
    }
    
    public ArrayList<Position> potentialMove( ChessPiece piece )//Dean Viner
    {
        if( piece instanceof Knight )
            return potentialMove((Knight)piece);
        if( piece instanceof Rook )
            return potentialMove((Rook)piece);
        if( piece instanceof Bishop )
            return potentialMove((Bishop)piece);
        if( piece instanceof Queen )
            return potentialMove((Queen)piece);
        if( piece instanceof Pawn )
            return potentialMove((Pawn)piece);
        if( piece instanceof King )
            return potentialMove((King)piece);
        return null;
    }
    
    public ArrayList<Position> updatedMoves(ArrayList<Position> old )//Ali Zolfaghari
    {
        int i = 0;
        ArrayList<Position>newMoves = old;
        while( i < newMoves.size() )
        {
            if( !newMoves.get(i).isValidPosition())
                newMoves.remove(i);
            else
                i++;
        }
        return newMoves;
    }
    
    public boolean inCheck( Position Square, TeamColor color )//Daniel Fang
    {
        for( ChessPiece x : Pieces )
        {
            if( x.getColor() != color)
            {
                for( Position q : potentialMove(x) )
                {
                    if( q.equalsPosition(Square))
                        return true;
                }
            }
        }
        return false;
    }
    
    public boolean kingInCheck(TeamColor color)//Andy Chao
    {
        for( ChessPiece x : Pieces )
        {
            if( x instanceof King && x.getColor() == color )
                return inCheck( x.getPosition(), color);
        }
        return false;
    }
    
    public boolean checkMate(TeamColor color)//Daniel Fang
    {
        boolean check = kingInCheck(color);
        for( ChessPiece x : Pieces)
        {
            if(x.getColor() == color && canMove(x))
                return false;
        }
        return check;
    }
    
    public ChessBoard getBoard()
    {
        return this;
    }
    
    public ArrayList<Position> canMoveList( ChessPiece piece )//Dean Viner
    {
        TeamColor color = piece.getColor();
        ArrayList<Position> answer = new ArrayList<Position>();
        
        ChessBoard temp = getBoard();
        int index = getIndex(piece);
        for(Position x : potentialMove(piece))
        {
            temp.getPiece(index).setPosition(x);
            if( !temp.kingInCheck(color))
                answer.add(x);
        }
        return answer;    
    }
    
    public boolean canMove( ChessPiece piece)//Dean Viner
    {
        return canMoveList(piece).size() > 0;
    }
    
    public void promotion( Pawn piece, String changeTo )//Ali Zolfaghari
    {
        if( piece.getPosition().gety() == 1 || piece.getPosition().gety() == 8)
        {
            if( changeTo.equals("Queen") )
            {
                Pieces.add( new Queen( piece.getColor(), piece.getPosition()));
                Pieces.remove(getPiece(piece.getPosition()));
            }
            if( changeTo.equals("Bishop") )
            {
                Pieces.add( new Bishop( piece.getColor(), piece.getPosition()));
                Pieces.remove(getPiece(piece.getPosition()));
            }
            if( changeTo.equals("Rook") )
            {
                Pieces.add( new Rook( piece.getColor(), piece.getPosition()));
                Pieces.remove(getPiece(piece.getPosition()));
            }
            if( changeTo.equals("Knight") )
            {
                Pieces.add( new Knight( piece.getColor(), piece.getPosition()));
                Pieces.remove(getPiece(piece.getPosition()));
            }
        }
                
    }
    
    public void move( Position original, Position newP )//Andy Chao
    {
        ChessPiece piece;
        TeamColor color;
        if( getPiece(original) != null)
        {
            piece = getPiece(original);
            color = piece.getColor();
            for( Position x : canMoveList( piece ))
            {
                if( x.equalsPosition(newP) )
                {
                    if( getPiece(newP) != null )
                    {
                        Taken.add(Pieces.remove(getIndex(getPiece(newP))));
                    }
                    piece.setPosition(newP);
                    piece.addMove();
                    changeTurn();
                    break;
                }
            }
            if( piece instanceof King)
            {
                ((King)piece).changeCastle();
                if( newP.getx() == original.getx() - 2 )
                {
                    getPiece(new Position( 1, original.gety())).setPosition( new Position(original.getx() - 1, original.gety()));
                }
                if( newP.getx() == original.getx() + 2 )
                {
                    getPiece(new Position( 8, original.gety())).setPosition( new Position(original.getx() + 1, original.gety()));
                }
            }
            if( piece instanceof Rook)
                ((Rook)piece).changeCastle();
            if( piece instanceof Pawn
                    && (newP.gety() == original.gety() + 2
                    || newP.gety() == original.gety() - 2))
                ((Pawn)piece).setPassant(true);
            for(ChessPiece x: Pieces)
            {
                if( x instanceof Pawn && x.getColor() != color )
                    ((Pawn)x).setPassant(false);
            }
        }    
    }
}