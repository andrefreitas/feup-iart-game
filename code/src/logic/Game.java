package logic;

import graphical.GameWindow;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.Vector;

import algorithms.MiniMax;

/**
 * Classe que armazena o estado geral do jogo, inicia o tabuleiro e fornece o método para pedir uma jogada ao CPU.
 */
public class Game 
{
	public static FileOutputStream st;
	public static Board board;
	public static int gameType=0; //0-human vs human; 1-human vs minimax; 2-minimax vs minimax (TODO)
	public static char botColor='B'; //B - black, W - white, aplicável quando o tipo de jogo é humano contra minimax
	
	public static void init(int selected_type, int selected_difficulty) 
	{
		try {
			st=new FileOutputStream((new File("dados.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game.board=new Board();
		Game.gameType=selected_type;
		Game.board.difficulty=selected_difficulty;		
		//System.out.println("Game type "+Game.gameType);
		//System.out.println("Game difficulty: "+Game.board.difficulty);	
	}
	
	public static void init(int selected_type, int selected_difficulty, int selected_difficulty2)
	{
		try {
			st=new FileOutputStream(new File("dados.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game.board=new Board();
		Game.gameType=selected_type;
		Game.board.difficulty=selected_difficulty;	
		Game.board.difficulty2=selected_difficulty2;
		//System.out.println(""+Game.board.difficulty+" - "+Game.board.difficulty2);
	}
	
	/**
	 * Método auxiliar que permite pedir uma jogada ao computador. Invoca o minimax com os devidos parâmetros iniciais, escolhe jogadas aleatórias em caso de empate...
	 * @param moves Vector de jogadas possíveis.
	 */
	public static void playBot(Vector<Move> moves) 
	{
		long before=System.currentTimeMillis();	
		
		Move nextMove=null;
		int moveVal=-10000;
		
		for(Move m : moves)
		{			
			int minimaxVal=MiniMax.play(m, 0,false, /*moveVal*/ -10000, 10000, board.turn); //TODO: Na verdade isto trata-se de um nó max (porque é a raiz), por isso ao invocar play alfa deveria ser igual a moveVal e não -10000 sempre, por favor verifica
			//System.out.println("minimax: "+minimaxVal);
			if(minimaxVal==moveVal)
			{
				Random rd= new Random();
				double nrd=((double)1/((double)moves.size()/2));
				
				if(rd.nextDouble()<=nrd)
				{
					//System.out.println("rand: <="+nrd);
					moveVal=minimaxVal;
					nextMove=m;
				}
			}
			if(minimaxVal>moveVal)
			{
				moveVal=minimaxVal;
				nextMove=m;
			}			
		}
		
		long after=System.currentTimeMillis();
		
		if(board.turn=='B')
		{
			
			
			try {
				
				
				
				byte[] data = ((""+(after-before)+"\n").getBytes());
				st.write(data);
				
				
			} catch (IOException e) {
				
			}
		}
		//nextMove.showMove();
		//System.out.println("Valor jogada: "+moveVal);
		board.makeMove(nextMove);
		((GameWindow) GameWindow.self).botPlay(nextMove);
		//board.getMatrix();
		//System.out.println("Turn: "+board.turn);
		
	}
}
