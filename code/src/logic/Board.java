package logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

//import complexity.Profiling;

/**
 * Classe auxiliar para guardar um vetor de strings que representa os identificadores das slots adjacentes a uma dada slot.
 */
class AdjacentSlots
{
	public Vector<String> adj=new Vector<String>();	
}

/**
 * Classe auxiliar para guardar dois vectors de strings que representam os identificadores 
 * das slots dos dois mills nos quais se encontra determinada slot.
 */
class SlotMills
{
	public Vector<String> mill1=new Vector<String>();
	public Vector<String> mill2=new Vector<String>();
}

/**
 * Classe que representa o tabuleiro e os jogadores, armazenando as slots, a peça que ocupa cada uma, as adjacências 
 * às mesmas e as mills nas quais se encontram, as peças de cada jogador, etc.
 * Fornece métodos para fazer jogadas, verificar se o jogo terminou, obter jogadas possíveis para um jogador, etc.
 */
public class Board 
{
	//TODO: Acabar (enorme) refactoring e documentação dos métodos
	
	HashMap<String,Piece> slotMap= new HashMap<String,Piece>(); //Mapa de slots do tabuleiro e a peça que cada uma contém (ou null)
	HashMap<String,AdjacentSlots> adjacentsMap= new HashMap<String,AdjacentSlots>(); //Peças adjacentes a cada slot
	HashMap<String,SlotMills> millsMap= new HashMap<String,SlotMills>(); //Mills nas quais se encontra cada slot

	public Vector<Piece> blackPieces = new Vector<Piece>();
	public Vector<Piece> whitePieces = new Vector<Piece>();

	public HashSet<String> playedMoves = new HashSet<String>();
	public Move lastMove=null;

	//Número de peças livres (sem jogar) na posse dos jogadores
	public int freeBlacks;
	public int freeWhites;

	//Número total de jogadas de cada jogador
	private int blackMoves;
	private int whiteMoves;

	//Stage na qual se encontra cada jogador
	public int blackStage;
	public int whiteStage;	
	
	public char turn; //Identificador do jogador atual	
	public int difficulty=3; //Grau de dificuldade

	public int difficulty2=3;
	
	public Board()
	{
		freeBlacks=9;
		freeWhites=9;
		blackMoves=0;
		whiteMoves=0;
		blackStage=0;
		whiteStage=0;
		turn='B';
		BoardInit.init(this);		
	}

	public char gameOver()
	{
		boolean moves=false;
		if(freeBlacks>3 && blackStage>0)
		{
			for(Piece p:blackPieces)
			{
				for(String s:adjacentsMap.get(p.keyPos).adj)
				{
					if(slotMap.get(s)==null)
					{
						moves=true;
						break;
					}
				}
				if(moves)
					break;
			}
			if(moves==false)
				return 'W';
		}
		moves=false;
		if(freeWhites>3 && whiteStage>0)
		{
			for(Piece p:whitePieces)
			{
				for(String s:adjacentsMap.get(p.keyPos).adj)
				{
					if(slotMap.get(s)==null)
					{
						moves=true;
						break;
					}
				}
				if(moves)
					break;
			}
			if(moves==false)
				return 'B';
		}

		if(freeBlacks<3)
			return 'W';
		if(freeWhites<3)
			return 'B';

		return 'X';
	}

	public void makeMove(Move m)
	{
		/*if(NineMansMorris.i>=27)
		{
			System.out.println("MakeMove start");
			getMatrix();
		}*/
		Boolean blackTurn=false;
		Vector<Piece> pieces=null;
		Vector<Piece> piecesRemove=null;
		if(m.value=='B')
		{
			blackTurn=true;
			pieces=blackPieces;
			piecesRemove=whitePieces;
		}else if(m.value=='W')
		{
			pieces=whitePieces;
			piecesRemove=blackPieces;
		}

		if(m.stage==0)
		{
			String strFinalPos = m.finalPos[0]+"-"+m.finalPos[1];
			Piece p=new Piece(m.value,m.finalPos[0],m.finalPos[1]);
			pieces.add(p);
			slotMap.put(strFinalPos, p);

			if(m.removedPiece!=null)
			{
				slotMap.put(m.removedPiece.keyPos,null);
				int idx=0;
				for(Piece pR : piecesRemove)
				{
					if(pR.keyPos.startsWith(m.removedPiece.keyPos))
						break;
					idx++;
				}
				piecesRemove.remove(idx);
				if(m.removedPiece.getValue()=='B')
					freeBlacks--;
				else
					freeWhites--;
			}

		}else
		{
			String strInitPos = m.initPos[0]+"-"+m.initPos[1];
			String strFinalPos = m.finalPos[0]+"-"+m.finalPos[1];
			Piece myP=slotMap.get(strInitPos);

			if(myP==null)
			{
				System.out.println("peça: "+strInitPos+" não existe no board.");
				this.getMatrix();
				System.out.println("move: "+m.value+" final: "+m.finalPos[0]+"-"+m.finalPos[1]);
				if(m.removedPiece==null)
					System.out.println("remove piece é null");
				else
					System.out.println("remove piece é: "+m.removedPiece.keyPos);				
				System.exit(1);
			}
			myP.x=m.finalPos[0];
			myP.y=m.finalPos[1];
			myP.keyPos=strFinalPos;
			slotMap.put(strFinalPos,myP);
			slotMap.put(strInitPos,null);

			if(m.removedPiece!=null)
			{
				if(slotMap.get(m.removedPiece.keyPos)==null)
				{
					System.out.println("Peça a remover já removida em board: "+m.removedPiece.keyPos);
					System.out.println("move stage: "+m.stage);
					System.out.println("move value: "+m.value);
					System.out.println("move init: "+m.initPos[0]+"-"+m.initPos[1]);
					System.out.println("move final: "+m.finalPos[0]+"-"+m.finalPos[1]);
					System.out.println("blackPieces");
					for(Piece pTeste: blackPieces)
					{
						System.out.println("key: "+pTeste.keyPos);
						System.out.println("x: "+pTeste.x);
						System.out.println("y: "+pTeste.y);
						System.out.println("value: "+pTeste.getValue());
						System.out.println("\n");
					}
					System.out.println("whitePieces");
					for(Piece pTeste: whitePieces)
					{
						System.out.println("key: "+pTeste.keyPos);
						System.out.println("x: "+pTeste.x);
						System.out.println("y: "+pTeste.y);
						System.out.println("value: "+pTeste.getValue());
						System.out.println("\n");
					}
					getMatrix();
					System.exit(1);
				}
				slotMap.put(m.removedPiece.keyPos,null);
				//pieces.remove(m.removedPiece);
				int idx=0;
				for(Piece pR : piecesRemove)
				{
					if(pR.keyPos.startsWith(m.removedPiece.keyPos))
						break;
					
					idx++;
				}
				if(idx>=piecesRemove.size())
				{
					System.out.println("tentativa de remover: "+m.removedPiece.keyPos+" "+m.removedPiece.getValue()+" falhada...");
					System.out.println(""+m.removedPiece.x+" - "+m.removedPiece.y);
					System.out.println(""+m.stage+" "+m.value+" "+m.initPos[0]+" "+m.initPos[1]+" "+
							m.finalPos[0]+" "+m.finalPos[1]);
					System.out.println("blackPieces");
					for(Piece pTeste: blackPieces)
					{
						System.out.println("key: "+pTeste.keyPos);
						System.out.println("x: "+pTeste.x);
						System.out.println("y: "+pTeste.y);
						System.out.println("value: "+pTeste.getValue());
						System.out.println("\n");
					}
					System.out.println("whitePieces");
					for(Piece pTeste: whitePieces)
					{
						System.out.println("key: "+pTeste.keyPos);
						System.out.println("x: "+pTeste.x);
						System.out.println("y: "+pTeste.y);
						System.out.println("value: "+pTeste.getValue());
						System.out.println("\n");
					}
					this.getMatrix();
				}
				piecesRemove.remove(idx);
				if(m.removedPiece.getValue()=='B')
				{
					freeBlacks--;
				}
				else
				{
					freeWhites--;
				}
			}
		}

		if(blackTurn)
		{
			turn='W';
			blackMoves++;
		}else
		{
			turn='B';
			whiteMoves++;
		}

		if(blackMoves>=9 && blackStage==0)
			blackStage=1;
		if(whiteMoves>=9 && whiteStage==0)
			whiteStage=1;

		if(freeBlacks<=3)
			blackStage=2;
		if(freeWhites<=3)
			whiteStage=2;

		lastMove=m;
		/*if(NineMansMorris.i>=27)
		{
			System.out.println("MakeMove");
			m.showMove();
			getMatrix();
		}*/
	}

	public Vector<Move> getPossibleMoves(char turn)
	{
		Vector<Move> ret = new Vector<Move>();

		if(freeWhites<3 || freeBlacks<3)
			return ret;

		int stage;
		if(turn=='B')
			stage=blackStage;
		else
			stage=whiteStage;
		
		//Profiling prof=new Profiling(stage,black+white);
		if(stage==0)
		{
			for(String strBoard : slotMap.keySet())
			{
				if(slotMap.get(strBoard)==null)
				{
					int x=Character.getNumericValue(strBoard.charAt(0));
					int y=Character.getNumericValue(strBoard.charAt(2));
					Vector<String> PiecesToRemove = millFormed(x,y,turn,-1,-1);
					if(PiecesToRemove!=null && PiecesToRemove.size()>0)
					{
						// criar moves com pessas removidas
						for(String strRemove : PiecesToRemove)
						{
							if(slotMap.get(strRemove)==null)
							{
								System.out.println("Piece "+strRemove+" não existe em board!!");

								System.out.println("blackPieces");
								for(Piece pTeste: blackPieces)
								{
									System.out.println("key: "+pTeste.keyPos);
									System.out.println("x: "+pTeste.x);
									System.out.println("y: "+pTeste.y);
									System.out.println("value: "+pTeste.getValue());
									System.out.println("\n");
								}
								System.out.println("whitePieces");
								for(Piece pTeste: whitePieces)
								{
									System.out.println("key: "+pTeste.keyPos);
									System.out.println("x: "+pTeste.x);
									System.out.println("y: "+pTeste.y);
									System.out.println("value: "+pTeste.getValue());
									System.out.println("\n");
								}
							}
							Piece pRemove=slotMap.get(strRemove).clone();
							Move m=new Move(stage,turn,0,0,x,y,pRemove);
							ret.add(m);
							//	prof.inc();
						}

					}
					else
					{
						Move m=new Move(stage,turn,0,0,x,y,null);
						ret.add(m);
					}
				}
				//prof.inc();
			}
		}
		else if(stage==1)
		{
			Vector<Piece> pieces=null;
			if(turn=='B')
				pieces=blackPieces;
			else if(turn=='W')
				pieces=whitePieces;
			
			for(Piece p : pieces)
			{
				AdjacentSlots a=adjacentsMap.get(p.keyPos);
				for(String strAdj : a.adj)
				{
					if(slotMap.get(strAdj)==null)
					{
						int x=Character.getNumericValue(strAdj.charAt(0));
						int y=Character.getNumericValue(strAdj.charAt(2));
						Vector<String> PiecesToRemove = millFormed(x,y,turn,p.getX(),p.getY());
						if(PiecesToRemove!=null && PiecesToRemove.size()>0)
						{
							// criar moves com pessas removidas
							for(String strRemove : PiecesToRemove)
							{
								Piece pRemove=slotMap.get(strRemove).clone();
								Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,pRemove);
								ret.add(m);
								//prof.inc();
							}

						}
						else
						{
							Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,null);
							ret.add(m);
						}

					}
					//prof.inc();
				}
				//prof.inc();
			}
		}
		else if(stage==2)
		{
			Vector<Piece> pieces=null;
			if(turn=='B')
				pieces=blackPieces;
			else if(turn=='W')
				pieces=whitePieces;
			
			for(Piece p : pieces)
			{

				for(String strBoard : slotMap.keySet())
				{
					if(slotMap.get(strBoard)==null)
					{
						int x=Character.getNumericValue(strBoard.charAt(0));
						int y=Character.getNumericValue(strBoard.charAt(2));
						
						Vector<String> PiecesToRemove = millFormed(x,y,turn,p.getX(),p.getY());
						if(PiecesToRemove!=null && PiecesToRemove.size()>0)
						{
							// criar moves com pessas removidas
							for(String strRemove : PiecesToRemove)
							{
								Piece pRemove=slotMap.get(strRemove).clone();
								Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,pRemove);
								ret.add(m);
								//prof.inc();
							}
						}
						else
						{
							Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,null);
							ret.add(m);
						}

					}
					//prof.inc();
				}
				//prof.inc();
			}
			//System.out.println("Stage 2, jogadas possiveis: "+ret.size());
		}
		//prof.finishProfiling();

		/*for(Move tMove: ret)
		{
			if(tMove.removedPiece!=null && (board.get(tMove.removedPiece.keyPos).piece==null || 
					board.get(tMove.removedPiece.keyPos).piece!=null && 
					board.get(tMove.removedPiece.keyPos).piece.getValue()!=tMove.removedPiece.getValue()))
			{
				System.out.println("ERRO remover peça inexistente turn: "+this.turn);
				System.out.println("move: "+tMove.stage+" "+tMove.value+" "+tMove.initPos[0]+"-"+tMove.initPos[1]+
						" "+tMove.finalPos[0]+"-"+tMove.finalPos[1]+" remove: "+tMove.removedPiece.keyPos+" "+tMove.removedPiece.getValue());
				System.out.println("blackPieces");
				for(Piece pTeste: blackPieces)
				{
					System.out.println("key: "+pTeste.keyPos);
					System.out.println("x: "+pTeste.x);
					System.out.println("y: "+pTeste.y);
					System.out.println("value: "+pTeste.getValue());
					System.out.println("\n");
				}
				System.out.println("whitePieces");
				for(Piece pTeste: whitePieces)
				{
					System.out.println("key: "+pTeste.keyPos);
					System.out.println("x: "+pTeste.x);
					System.out.println("y: "+pTeste.y);
					System.out.println("value: "+pTeste.getValue());
					System.out.println("\n");
				}

			}
		}*/

		return ret;
	}

	private Vector<String> millFormed(int x, int y, char turn, int currentX, int currentY) 
	{
		Vector<String> onMill=new Vector<String>();
		Vector<String> ret=new Vector<String>();

		String pieceKey=x+"-"+y;
		SlotMills mill=millsMap.get(pieceKey);
		if(slotMap.get(mill.mill1.get(0))!=null && 
				!slotMap.get(mill.mill1.get(0)).keyPos.startsWith(currentX+"-"+currentY) && 
				slotMap.get(mill.mill1.get(0)).getValue() == turn &&
				slotMap.get(mill.mill1.get(1))!=null && 
				!slotMap.get(mill.mill1.get(1)).keyPos.startsWith(currentX+"-"+currentY) &&
				slotMap.get(mill.mill1.get(1)).getValue() == turn ||
				slotMap.get(mill.mill2.get(0))!=null && 
				!slotMap.get(mill.mill2.get(0)).keyPos.startsWith(currentX+"-"+currentY) &&
				slotMap.get(mill.mill2.get(0)).getValue() == turn &&
				slotMap.get(mill.mill2.get(1))!=null && 
				!slotMap.get(mill.mill2.get(1)).keyPos.startsWith(currentX+"-"+currentY) &&
				slotMap.get(mill.mill2.get(1)).getValue() == turn)
		{

			Vector<Piece> pieces=null;
			if(turn=='B')
				pieces=whitePieces;  //peças adversarias			
			else if(turn=='W')
				pieces=blackPieces;  //peças adversarias
			
			for(Piece p : pieces)
			{
				if(slotMap.get(p.keyPos)!=null)
				{
					SlotMills millAdv=millsMap.get(p.keyPos);
					if(slotMap.get(millAdv.mill1.get(0))!=null && slotMap.get(millAdv.mill1.get(0)).getValue() != turn &&
							slotMap.get(millAdv.mill1.get(1))!=null && slotMap.get(millAdv.mill1.get(1)).getValue() != turn ||
							slotMap.get(millAdv.mill2.get(0))!=null && slotMap.get(millAdv.mill2.get(0)).getValue() != turn &&
							slotMap.get(millAdv.mill2.get(1))!=null && slotMap.get(millAdv.mill2.get(1)).getValue() != turn)
					{
						onMill.add(p.keyPos);
					}
					else
						ret.add(p.keyPos);
				}
			}
		}
		else
			return null;


		if(ret.size()==0)
			return onMill;
		else
			return ret;
	}

	public char[][] getMatrix() 
	{
		char[][] matrix = new char[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) 
			{
				String key = i + "-" + j;
				if (slotMap.containsKey(key)) 
				{
					Piece p = slotMap.get(key);
					if (p != null)
						matrix[i][j] = p.getValue();
					else
						matrix[i][j] = 'O';
				}
				else 
				{
					matrix[i][j] = 'X';
				}
				System.out.print(matrix[i][j]+" ");
			}

			System.out.print("\n");
		}
		System.out.println("\n");
		return matrix;
	}


	/*
	public Boolean putPiece(Piece p){
		String key=p.getX()+"-"+p.getY();
		if (board.containsKey(key) && board.get(key).piece==null){
			board.get(key).piece=p;
			return true;
		}
		return false;
	}

	public Piece removePiece(int x,int y){
		String key=x+"-"+y;
		if(board.containsKey(key) && board.get(key).piece!=null){
			Piece p=board.get(key).piece;
			board.put(key, null);
			return p;
		}

		return null;
	}



	public boolean putIsValid(int x, int y){
		String key=x+"-"+y;
		return board.containsKey(key) && board.get(key)==null;
	}
	 */
	public int evaluate(char value) 
	{
		int ret=0;
		if(this.gameOver()==value)
			return 1000;
		else if(this.gameOver()!='X')
			return -1000;
		else if(this.gameOver()=='X')
		{

			if(value=='B')
			{
				ret= freeBlacks - freeWhites;
				ret*=100;
			}
			else
			{
				ret= freeWhites-freeBlacks;
				ret*=100;
			}
		}

		ret += evalFormation(value);

		for(Piece p : blackPieces)
		{
			for(String s:this.millsMap.get(p.keyPos).mill1)
			{
				if(slotMap.get(s)!=null && slotMap.get(s).getValue()=='B')
				{
					if(value=='B')
						ret++;
					else
						ret--;
				}
			}
			for(String s:this.millsMap.get(p.keyPos).mill2)
			{
				if(slotMap.get(s)!=null && slotMap.get(s).getValue()=='B')
				{
					if(value=='B')
						ret++;
					else
						ret--;
				}
			}
		}

		for(Piece p : whitePieces)
		{
			for(String s:this.millsMap.get(p.keyPos).mill1)
			{
				if(slotMap.get(s)!=null && slotMap.get(s).getValue()=='W')
				{
					if(value=='W')
						ret++;
					else
						ret--;
				}
			}
			for(String s:this.millsMap.get(p.keyPos).mill2)
			{
				if(slotMap.get(s)!=null && slotMap.get(s).getValue()=='W')
				{
					if(value=='W')
						ret++;
					else
						ret--;
				}
			}
		}

		if(ret>990)
			return 990;
		if(ret<-990)
			return -990;

		return ret;
	}

	private int evalFormation(char value)
	{
		Boolean firstMill=false;
		int ret=0;
		for(Piece p0: blackPieces)
		{
			for(String strP1: adjacentsMap.get(p0.keyPos).adj)
			{
				if(slotMap.get(strP1)==null)
				{
					SlotMills m = millsMap.get(strP1);
					Piece m10=slotMap.get(m.mill1.get(0));
					Piece m11=slotMap.get(m.mill1.get(1));
					Piece m20=slotMap.get(m.mill2.get(0));
					Piece m21=slotMap.get(m.mill2.get(1));
					if(m10!=null && m11!=null && !m10.keyPos.startsWith(p0.keyPos) && !m11.keyPos.startsWith(p0.keyPos)
							&& m10.getValue()==p0.getValue() && m11.getValue()==p0.getValue())
					{
						firstMill=true;
						if(value=='B')
							ret+=90;
						else
							ret+=-90;
						break;
					}
					if(m20!=null && m21!=null && !m20.keyPos.startsWith(p0.keyPos) && !m21.keyPos.startsWith(p0.keyPos)
							&& m20.getValue()==p0.getValue() && m21.getValue()==p0.getValue())
					{
						firstMill=true;
						if(value=='B')
							ret+=90;
						else
							ret+=-90;
						break;
					}
				}
			}
			if(firstMill)
			{
				SlotMills m = millsMap.get(p0.keyPos);
				Piece m10=slotMap.get(m.mill1.get(0));
				Piece m11=slotMap.get(m.mill1.get(1));
				Piece m20=slotMap.get(m.mill2.get(0));
				Piece m21=slotMap.get(m.mill2.get(1));
				if(m10!=null && m11!=null && m10.getValue()==p0.getValue() && m11.getValue()==p0.getValue() ||
						m20!=null && m21!=null && m20.getValue()==p0.getValue() && m21.getValue()==p0.getValue())
				{
					if(value=='B')
						ret+=500;
					else
						ret+=-500;
					break;
				}
			}
		}
		firstMill=false;
		for(Piece p0: whitePieces)
		{
			for(String strP1: adjacentsMap.get(p0.keyPos).adj)
			{
				if(slotMap.get(strP1)==null)
				{
					SlotMills m = millsMap.get(strP1);
					Piece m10=slotMap.get(m.mill1.get(0));
					Piece m11=slotMap.get(m.mill1.get(1));
					Piece m20=slotMap.get(m.mill2.get(0));
					Piece m21=slotMap.get(m.mill2.get(1));
					if(m10!=null && m11!=null && !m10.keyPos.startsWith(p0.keyPos) && !m11.keyPos.startsWith(p0.keyPos)
							&& m10.getValue()==p0.getValue() && m11.getValue()==p0.getValue())
					{
						firstMill=true;
						if(value=='W')
							ret+=90;
						else
							ret+=-90;
						break;
					}
					if(m20!=null && m21!=null && !m20.keyPos.startsWith(p0.keyPos) && !m21.keyPos.startsWith(p0.keyPos)
							&& m20.getValue()==p0.getValue() && m21.getValue()==p0.getValue())
					{
						firstMill=true;
						if(value=='W')
							ret+=90;
						else
							ret+=-90;
						
						break;
					}
				}
			}
			if(firstMill)
			{
				SlotMills m = millsMap.get(p0.keyPos);
				Piece m10=slotMap.get(m.mill1.get(0));
				Piece m11=slotMap.get(m.mill1.get(1));
				Piece m20=slotMap.get(m.mill2.get(0));
				Piece m21=slotMap.get(m.mill2.get(1));
				if(m10!=null && m11!=null && m10.getValue()==p0.getValue() && m11.getValue()==p0.getValue() ||
						m20!=null && m21!=null && m20.getValue()==p0.getValue() && m21.getValue()==p0.getValue())
				{
					if(value=='W')
						ret+=500;
					else
						ret+=-500;
					break;
				}
			}
		}


		return ret;
	}

	public boolean stopMiniMax(int nMoves, int sizeMoves) 
	{
		int diff=difficulty;
		if(Game.gameType==2 && turn!='B')
		{
			diff=difficulty2;
			
		}
		
		if(nMoves>diff || (diff>2 && sizeMoves>30 && nMoves>2))
			return true;

		if(gameOver()=='X')
			return false;
		else 
			return true;
	}

	public void unmakeMove(Move move) 
	{
		/*if(NineMansMorris.i>=27)
			System.out.println("UnmakeMove start");
		 */
		if(move.stage==0)
		{
			if(move.removedPiece!=null)
			{
				if(move.removedPiece.getValue()=='B')
				{
					Piece poux=move.removedPiece.clone();
					blackPieces.add(poux);
					slotMap.put(move.removedPiece.keyPos, poux);
					freeBlacks++;
				}
				else
				{
					Piece poux=move.removedPiece.clone();
					whitePieces.add(poux);
					slotMap.put(move.removedPiece.keyPos, poux);
					freeWhites++;
				}
			}
			int finalX=move.finalPos[0];
			int finalY=move.finalPos[1];
			Piece p=slotMap.get(finalX+"-"+finalY);
			if(p.getValue()=='B')
			{
				//blackPieces.remove(p);
				int i=0;
				for(;i<blackPieces.size();i++)
				{
					if(blackPieces.get(i).keyPos.startsWith(p.keyPos))
					{
						break;
					}
				}
				blackPieces.remove(i);
				blackMoves--;
				this.turn='B';
			}
			else
			{
				//whitePieces.remove(p);
				int i=0;
				for(;i<whitePieces.size();i++)
				{
					if(whitePieces.get(i).keyPos.startsWith(p.keyPos))
					{
						break;
					}
				}
				whitePieces.remove(i);
				whiteMoves--;
				this.turn='W';
			}
			slotMap.put(finalX+"-"+finalY,null);

		}
		else
		{
			if(move.removedPiece!=null)
			{
				if(move.removedPiece.getValue()=='B')
				{
					Piece poux=move.removedPiece.clone();
					blackPieces.add(poux);
					slotMap.put(move.removedPiece.keyPos, poux);
					freeBlacks++;
				}
				else
				{
					Piece poux=move.removedPiece.clone();
					whitePieces.add(poux);
					slotMap.put(move.removedPiece.keyPos, poux);
					freeWhites++;
				}
			}
			
			int finalX=move.finalPos[0];
			int finalY=move.finalPos[1];
			Piece p=slotMap.get(finalX+"-"+finalY);
			if(p.getValue()=='B')
			{
				blackMoves--;
				this.turn='B';
			}
			else
			{
				whiteMoves--;
				this.turn='W';
			}
			
			p.x=move.initPos[0];
			p.y=move.initPos[1];
			p.keyPos=p.x+"-"+p.y;
			slotMap.put(p.keyPos,p);
			slotMap.put(finalX+"-"+finalY,null);
		}
		
		if(move.value=='B')
			blackStage=move.stage;
		else
			whiteStage=move.stage;
		
		if(freeBlacks<=3)
			blackStage=2;
		else if(blackMoves>=9)
			blackStage=1;
		else 
			blackStage=0;

		if(freeWhites<=3)
			whiteStage=2;
		else if(whiteMoves>=9)
			whiteStage=1;
		else 
			whiteStage=0;
		/*if(NineMansMorris.i>=27)
		{
			System.out.println("UnmakeMove");
			move.showMove();
			getMatrix();
		}*/
	}
	
	public HashMap<String,Piece> getSlotMap()
	{
		return slotMap;
	}
	
	public HashMap<String,AdjacentSlots> getAdjacentsMap()
	{
		return adjacentsMap;
	}
	
	public HashMap<String,SlotMills> getMillsMap()
	{
		return millsMap;
	}
}