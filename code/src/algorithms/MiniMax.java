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
		
		NineMansMorris.board.makeMove(move);
		NineMansMorris.board.getMatrix();
		if(NineMansMorris.board.stopMiniMax(nMoves)){
			return NineMansMorris.board.evaluate(turn);
		}else if(max){
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				alpha=Math.max(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			return alpha;
		}
		else if(!max){
			
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				beta=Math.min(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			return beta;
		}
		
		return 0;
	}
}
