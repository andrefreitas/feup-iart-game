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
		/*if(move.removedPiece!=null && move.removedPiece.x==2 && move.removedPiece.y==4 && move.removedPiece.getValue()=='B' && NineMansMorris.board.white>4)
		{
			System.out.println("Erro nabiço....."+move.stage+" "+move.value+" ");
		}*/
		NineMansMorris.board.makeMove(move);
		if(NineMansMorris.i>=42)
		{
			//System.out.println("makeMove: "+move.getHashKey()+" "+move.stage+" "+move.value+" "+move.initPos.toString()+" "+move.finalPos.toString() );
			if(move.removedPiece!=null)
			{
				//System.out.println("removed: "+move.removedPiece.keyPos+" "+move.removedPiece.getValue());
			}
		}
		//NineMansMorris.board.getMatrix();
		
		Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
		
		if(NineMansMorris.board.stopMiniMax(nMoves) || childmoves==null || childmoves.size()==0){
			//undo move
			
			int boardEvaluation=NineMansMorris.board.evaluate(turn);
			NineMansMorris.board.unmakeMove(move);
			if(NineMansMorris.i>=42)
			{
				//System.out.println("UnmakeMove: "+move.getHashKey());
			}
			return boardEvaluation;
		}else if(max){
			//Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				//System.out.println("MAX: ");
				alpha=Math.max(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			NineMansMorris.board.unmakeMove(move);
			if(NineMansMorris.i>=42)
			{
				//System.out.println("UnmakeMove: "+move.getHashKey());
			}
			//System.out.println("minimax retorna alpha: "+alpha);
			return alpha;
		}
		else if(!max){
			
			//Vector<Move> childmoves=NineMansMorris.board.getPossibleMoves(v);
			for(Move movei:childmoves){
				//System.out.println("MIN: ");
				beta=Math.min(beta,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			NineMansMorris.board.unmakeMove(move);
			if(NineMansMorris.i>=42)
			{
				//System.out.println("UnmakeMove: "+move.getHashKey());
			}
			//System.out.println("minimax retorna beta: "+beta);
			return beta;
		}
		
		return 0;
	}

	
}
