package algorithms;
import java.util.Vector;
import init.NineMansMorris;
import game.Move;

public class MiniMax {
	public static int play(Move move,int nMoves,boolean max,int alpha, int beta, char turn){
		nMoves++;
		char v;
		if(move.value=='B')
			v='W';
		else 
			v='B';
		//System.out.println("Minimax: "+nMoves);
		//System.out.println("Turn: "+move.value);
		/*if(move.removedPiece!=null && move.removedPiece.x==2 && move.removedPiece.y==4 && move.removedPiece.getValue()=='W' && NineMansMorris.board.white>4)
		{
			System.out.println("Erro nabiço....."+move.stage+" "+move.value+" ");
		}*/
		NineMansMorris.board.makeMove(move);
		
		
		Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
		
		if(NineMansMorris.board.stopMiniMax(nMoves,childmoves.size()) || childmoves==null || childmoves.size()==0){
			//undo move
			
			int boardEvaluation=NineMansMorris.board.evaluate(turn);
			NineMansMorris.board.unmakeMove(move);
			
			return boardEvaluation;
		}else if(max){
			//Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				//System.out.println("MAX: ");
				/*if(NineMansMorris.i>=27)
				{
					System.out.println("Minimax move: ");
					movei.showMove();
				}*/
				alpha=Math.max(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			NineMansMorris.board.unmakeMove(move);
			
			return alpha;
		}
		else if(!max){
			
			//Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				//System.out.println("MIN: ");
				/*if(NineMansMorris.i>=27)
				{
					System.out.println("Minimax move: ");
					movei.showMove();
				}*/
				beta=Math.min(beta,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			NineMansMorris.board.unmakeMove(move);
			
			return beta;
		}
		
		return 0;
	}

	
}
