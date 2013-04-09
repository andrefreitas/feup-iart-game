package algorithms;
import java.util.Vector;
import init.NineMansMorris;
import game.Move;

public class MiniMax {
	public static int play(Move move,boolean max,int alpha, int beta, char turn){
		char v;
		if(move.value=='P')
			v='B';
		else 
			v='P';
		
		if(NineMansMorris.board.gameOver()!='X'){
			return NineMansMorris.board.evaluate(turn);
		}else if(max){
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v, move.stage);
			for(Move movei:childmoves){
				alpha=Math.max(alpha,play(movei,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			return alpha;
		}
		else if(!max){
			
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v, move.stage);
			for(Move movei:childmoves){
				beta=Math.min(alpha,play(movei,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			return beta;
		}
		
		return 0;
	}
}
