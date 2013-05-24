package logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import algorithms.MiniMax;

/**
 * Classe auxiliar para testes de desempenho e eficácia da inteligência artificial
 */
public class Tests 
{
	public static int numJogada = 0; //Número da jogada atual numa situação de teste
	
	public static void test1(int jogadas) 
	{
		numJogada=0;
		Move nextMove=null;
		
		while(numJogada<jogadas && Game.board.gameOver()=='X')
		{
			Vector<Move> moves = Game.board.getPossibleMoves(Game.board.turn);
			if(moves.size()==0)
				break;
						
			nextMove=null;
			int moveVal=-10000;
			for(Move m : moves)
			{				
				int minimaxVal=MiniMax.play(m, 0, false, moveVal, 10000, Game.board.turn);
				if(minimaxVal>moveVal)
				{
					moveVal=minimaxVal;
					nextMove=m;
				}				
			}
			
			if(nextMove==null)
				System.out.println("Sem jogadas possiveis...size: "+moves.size());
			else
			{				
				Game.board.makeMove(nextMove);
				Game.board.playedMoves.add(nextMove.getHashKey());
			}
			
			numJogada++;			
		}
		
		System.out.println("Jogada "+numJogada);		
		System.out.println("Peças pretas: "+Game.board.freeBlacks+", peças brancas: "+Game.board.freeWhites);
		Game.board.getMatrix();
		System.out.println("Vitoria: "+Game.board.gameOver());
	}

	public static void test2()	
	{		
		char winner='X';
		Move nextMove=null;
		
		while(Game.board.gameOver()=='X')
		{
			Vector<Move> moves=Game.board.getPossibleMoves(Game.board.turn);
			if(moves.size()==0)
			{
				if(Game.board.turn=='B')
					winner='W';
				else
					winner='B';
				
				break;
			}
			
			if(Game.board.turn=='W')
			{
				
				int i=0;
				for(Move m : moves)
				{
					System.out.println("id: "+i);
					m.showMove();
					i++;
				}
				System.out.println("Id: ");
				
				String sread=null;				
				try 
				{
					BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
					sread=in.readLine();
				} catch (Exception e) {e.printStackTrace();}
				
				nextMove=moves.get(Integer.parseInt(sread));
			}else
			{
				nextMove=null;
				int moveVal=-10000;
				for(Move m : moves)
				{
					
					int minimaxVal=MiniMax.play(m, 0,false, moveVal, 10000, Game.board.turn);
					if(minimaxVal>moveVal)
					{
						moveVal=minimaxVal;
						nextMove=m;
					}
					
				}
			}
			Game.board.makeMove(nextMove);
			Game.board.playedMoves.add(nextMove.getHashKey());
			System.out.println("Played: ");
			nextMove.showMove();
			Game.board.getMatrix();
		}
		if(winner!='X')
			System.out.println("Sem jogadas possiveis, vitoria: "+winner);
		else
			System.out.println("Vitoria: "+Game.board.gameOver());
	}
}
