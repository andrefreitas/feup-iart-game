package init;

import java.util.Vector;

import algorithms.MiniMax;
import game.Board;
import game.Move;

public class NineMansMorris {

	public static Board board;
	
	public static void main(String[] args) {
		board=new Board();
		Vector<Move> moves=board.getPossibleMoves('P');
		
		for(Move m : moves)
		{
			if(m.stage==0)
				System.out.println("Xfinal: "+m.finalPos[0]+", Yfinal: "+m.finalPos[1]);
			else
				System.out.println("Xinit: "+m.initPos[0]+", Yinit: "+m.initPos[1]
						+"Xfinal: "+m.finalPos[0]+", Yfinal: "+m.finalPos[1]);
			
			System.out.println("minimax: "+MiniMax.play(m, 0,true, -10000, 10000, board.turn));
		}
	}

}
