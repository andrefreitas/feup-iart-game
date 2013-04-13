package init;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Vector;

//import complexity.Profiling;

import algorithms.MiniMax;
import game.Board;
import game.Move;
import game.Piece;
import graphical.GameWindow;
import graphical.MainMenu;

public class NineMansMorris {

	public static int i;
	public static Board board;
	public static int gameType=0; //0-human vs human; 1-human vs minimax
	public static char botColor='B';
	
	public static void main(String[] args) {
		//board=new Board();
		Date d1=new Date();
		long t1=d1.getTime();
		
		new MainMenu();
		//test1(500);
		//test2();
		
		d1=new Date();
		long t2=d1.getTime();
		System.out.println("Tempo: "+(t2-t1));
	}
	
	private static void test2() {
		
		char winner='X';
		Move nextMove=null;
		while(board.gameOver()=='X')
		{
			Vector<Move> moves=board.getPossibleMoves(board.turn);
			if(moves.size()==0)
			{
				if(board.turn=='B')
				{
					winner='W';
				}else
				{
					winner='B';
				}
				
				break;
			}
			
			if(board.turn=='W')
			{
				//board.getMatrix();
				int i=0;
				for(Move m : moves)
				{
					System.out.println("id: "+i);
					m.showMove();
					i++;
				}
				System.out.println("Id: ");
				
				String sread=null;
				
				try {
					BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
					sread=in.readLine();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				nextMove=moves.get(Integer.parseInt(sread));
			}else
			{
				nextMove=null;
				int moveVal=-10000;
				for(Move m : moves)
				{
					
					int minimaxVal=MiniMax.play(m, 0,false, moveVal, 10000, board.turn);
					if(minimaxVal>moveVal)
					{
						moveVal=minimaxVal;
						nextMove=m;
					}
					
				}
			}
			board.makeMove(nextMove);
			board.playedMoves.add(nextMove.getHashKey());
			System.out.println("Played: ");
			nextMove.showMove();
			board.getMatrix();
		}
		if(winner!='X')
			System.out.println("Sem jogadas possiveis, vitoria: "+winner);
		else
			System.out.println("Vitoria: "+board.gameOver());
	}

	public static void test1(int jogadas)
	{
		i=0;
		Move nextMove=null;
		while(i<jogadas && board.gameOver()=='X')
		{
			Vector<Move> moves=board.getPossibleMoves(board.turn);
			if(moves.size()==0)
				break;
			
			
			nextMove=null;
			int moveVal=-10000;
			for(Move m : moves)
			{
				
				int minimaxVal=MiniMax.play(m, 0,false, moveVal, 10000, board.turn);
				if(minimaxVal>moveVal)
				{
					moveVal=minimaxVal;
					nextMove=m;
				}
				
			}
			if(nextMove==null)
			{
				System.out.println("Sem jogadas possiveis...size: "+moves.size());
				
			}else{
				
				board.makeMove(nextMove);
				board.playedMoves.add(nextMove.getHashKey());
			}
			i++;
			
			
			
		}
		System.out.println("Jogada "+i);
		
		System.out.println("Peças pretas: "+board.black+", peças brancas: "+board.white);
		board.getMatrix();
		System.out.println("Vitoria: "+board.gameOver());
	}

	public static void playBot(Vector<Move> moves) {
		
		Move nextMove=null;
		int moveVal=-10000;
		for(Move m : moves)
		{
			
			int minimaxVal=MiniMax.play(m, 0,false, moveVal, 10000, board.turn);
			if(minimaxVal>moveVal)
			{
				moveVal=minimaxVal;
				nextMove=m;
			}
			
		}
		nextMove.showMove();
		board.makeMove(nextMove);
		((GameWindow) GameWindow.self).botPlay(nextMove);
		board.getMatrix();
		System.out.println("Turn: "+board.turn);
	}

}
