package algorithms;
import java.util.Vector;
import init.NineMansMorris;
import game.Move;

public class MiniMax {
	public static int play(Move move,boolean max,int alpha, int beta){
		if(NineMansMorris.board.gameOver()){
			return NineMansMorris.board.evaluate();
		}else if(max){
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(move.value, move.stage);
			for(Move movei:childmoves){
				alpha=Math.max(alpha,play(move,!max,alpha,beta));
				if(beta<alpha) return 0;
			}
			return alpha;
		}
		else if(!max){
			Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(move.value, move.stage);
			for(Move movei:childmoves){
				beta=Math.min(alpha,play(move,!max,alpha,beta));
				if(beta<alpha) return 0;
			}
			return beta;
		}
		return 0;
	}
}
