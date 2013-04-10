package algorithms;
import java.util.Vector;
import init.NineMansMorris;
import game.Move;

public class MiniMax {
	public static int play(Move move,int nMoves,boolean max,int alpha, int beta, char turn){
		nMoves++;
		char v;
		if(move.value=='P')
			v='B';
		else 
			v='P';
		//System.out.println("Turn: "+move.value);
		NineMansMorris.board.makeMove(move);
		//NineMansMorris.board.getMatrix();
		
		
		if(NineMansMorris.board.stopMiniMax(nMoves)){
			//undo move
			NineMansMorris.board.unmakeMove(move);
			return NineMansMorris.board.evaluate(turn);
		}else if(max){
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				//System.out.println("MAX: ");
				alpha=Math.max(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			NineMansMorris.board.unmakeMove(move);
			return alpha;
		}
		else if(!max){
			
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				//System.out.println("MIN: ");
				beta=Math.min(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			NineMansMorris.board.unmakeMove(move);
			return beta;
		}
		
		return 0;
	}

	
}
