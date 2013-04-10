package init;

import java.util.Vector;

import algorithms.MiniMax;
import game.Board;
import game.Move;

public class NineMansMorris {

	public static Board board;
	
	public static void main(String[] args) {
		board=new Board();
		test1(8);
	}
	
	public static void test1(int jogadas)
	{
		int i=0;
		while(i<jogadas && board.gameOver()=='X')
		{
			Vector<Move> moves=board.getPossibleMoves(board.turn);
			Move nextMove=null;
			int moveVal=-10000;
			for(Move m : moves)
			{
				/*
				if(m.stage==0)
					System.out.println("Xfinal: "+m.finalPos[0]+", Yfinal: "+m.finalPos[1]);
				else
					System.out.println("Xinit: "+m.initPos[0]+", Yinit: "+m.initPos[1]
							+"Xfinal: "+m.finalPos[0]+", Yfinal: "+m.finalPos[1]);
				*/
				int minimaxVal=MiniMax.play(m, 0,false, -10000, 10000, board.turn);
				if(minimaxVal>moveVal)
				{
					moveVal=minimaxVal;
					nextMove=m;
				}
				//System.out.println("minimax: "+minimaxVal);
			}
			if(nextMove==null)
				System.out.println("Sem jogadas possiveis...");
			else
				board.makeMove(nextMove);
			System.out.println("Jogada - "+nextMove.value+" valor: "+moveVal+" board.turn: "+board.turn);
			board.getMatrix();
			i++;
		}
	}

}
