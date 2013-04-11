package init;

import java.util.Vector;

//import complexity.Profiling;

import algorithms.MiniMax;
import game.Board;
import game.Move;
import game.Piece;

public class NineMansMorris {

	public static int i;
	public static Board board;
	
	public static void main(String[] args) {
		board=new Board();
		test1(200);
	}
	
	public static void test1(int jogadas)
	{
		i=0;
		
		while(i<jogadas && board.gameOver()=='X')
		{
			Vector<Move> moves=board.getPossibleMoves(board.turn);
			if(moves.size()==0)
				break;
			
			/*if(i>=50)
			{
				System.out.println("moves ninemasnmorris...");
				board.getMatrix();
				for(Move m : moves)
				{
					m.showMove();
				}
			}*/
			
			
			//Profiling.self.finishProfiling();
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
			{
				System.out.println("Sem jogadas possiveis...size: "+moves.size());
				System.out.println("blackPieces");
				for(Piece pTeste: board.blackPieces)
				{
					System.out.println("key: "+pTeste.keyPos);
					System.out.println("x: "+pTeste.x);
					System.out.println("y: "+pTeste.y);
					System.out.println("value: "+pTeste.getValue());
					System.out.println("\n");
				}
				System.out.println("whitePieces");
				for(Piece pTeste: board.whitePieces)
				{
					System.out.println("key: "+pTeste.keyPos);
					System.out.println("x: "+pTeste.x);
					System.out.println("y: "+pTeste.y);
					System.out.println("value: "+pTeste.getValue());
					System.out.println("\n");
				}
			}else{
				/*if(i>=27){
					System.out.println("NineMansMorris move:");
					nextMove.showMove();
				}*/
				board.makeMove(nextMove);
				board.playedMoves.add(nextMove.getHashKey());
			}
			i++;
			System.out.println("Jogada "+i+" - "+nextMove.value+" valor: "+moveVal+" next turn: "+board.turn);
			if(nextMove.stage==0)
			{
				System.out.println("Stage 0 pos: "+nextMove.finalPos[0]+"-"+nextMove.finalPos[1]);
			}else 
			{
				System.out.println("Stage "+nextMove.stage+" init: "+nextMove.initPos[0]+"-"+nextMove.initPos[1]+", final: "+nextMove.finalPos[0]+"-"+nextMove.finalPos[1]);
			}
			if(nextMove.removedPiece!=null)
			{
				System.out.println("Peça removida: "+nextMove.removedPiece.keyPos+" - "+nextMove.removedPiece.getValue());
			}
			System.out.println("Peças pretas: "+board.black+", peças brancas: "+board.white);
			board.getMatrix();
			/*System.out.println("blackPieces");
			for(Piece pTeste: board.blackPieces)
			{
				System.out.println("key: "+pTeste.keyPos);
				System.out.println("x: "+pTeste.x);
				System.out.println("y: "+pTeste.y);
				System.out.println("value: "+pTeste.getValue());
				System.out.println("\n");
			}
			System.out.println("whitePieces");
			for(Piece pTeste: board.whitePieces)
			{
				System.out.println("key: "+pTeste.keyPos);
				System.out.println("x: "+pTeste.x);
				System.out.println("y: "+pTeste.y);
				System.out.println("value: "+pTeste.getValue());
				System.out.println("\n");
			}*/
			
		}
		System.out.println("Vitoria: "+board.gameOver());
	}

}
