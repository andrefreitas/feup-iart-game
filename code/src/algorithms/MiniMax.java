package algorithms;
import java.util.Vector;

import logic.Game;
import logic.Move;

/**
 * Classe que implementa o algoritmo MiniMax no contexto do jogo.
 */
public class MiniMax 
{
	/**
	 * Método que implementa a inteligência artificial do jogo através do algoritmo MiniMax com cortes alfa e beta.
	 * @param move Movimento a fazer, avaliar e ramificar
	 * @param nMoves Profundidade da árvore minimax.
	 * @param max Se true, trata-se de um nó max, se false de um nó min.
	 * @param alpha Valor de corte alfa.
	 * @param beta Valor de corte beta.
	 * @param turn Turno das peças pretas ou dos brancas.
	 * @return Valor máximo ou mínimo dos filhos conforme seja um nó max/min.
	 */
	public static int play(Move move, int nMoves, boolean max, int alpha, int beta, char turn)
	{
		nMoves++;
		char v;
		if(move.value=='B')
			v='W';
		else 
			v='B';
		//System.out.println("Minimax: "+nMoves);
		//System.out.println("Turn: "+move.value);
		/*if(move.removedPiece!=null && move.removedPiece.x==2 && move.removedPiece.y==4 && move.removedPiece.getValue()=='W' && Game.board.white>4)
		{
			System.out.println("Erro nabiço....."+move.stage+" "+move.value+" ");
		}*/
		Game.board.makeMove(move);		
		Vector<Move> childmoves=Game.board.getPossibleMoves(v);
		
		if(Game.board.stopMiniMax(nMoves,childmoves.size()) || childmoves==null || childmoves.size()==0)
		{
			//undo move			
			int boardEvaluation=Game.board.evaluate(turn);
			Game.board.unmakeMove(move);			
			return boardEvaluation;
		}
		else if(max)
		{
			//Vector<Move> childmoves=Game.board.getPossibleMoves(v);
			for(Move movei:childmoves)
			{
				//System.out.println("MAX: ");
				/*if(Game.i>=27)
				{
					System.out.println("Minimax move: ");
					movei.showMove();
				}*/
				alpha=Math.max(alpha,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			Game.board.unmakeMove(move);
			
			return alpha;
		}
		else if(!max)
		{			
			//Vector<Move> childmoves=Game.board.getPossibleMoves(v);
			for(Move movei:childmoves)
			{
				//System.out.println("MIN: ");
				/*if(Game.i>=27)
				{
					System.out.println("Minimax move: ");
					movei.showMove();
				}*/
				beta=Math.min(beta,play(movei,nMoves,!max,alpha,beta,turn));
				if(beta<alpha) break;
			}
			//undo move
			Game.board.unmakeMove(move);
			
			return beta;
		}
		
		return 0;
	}
}