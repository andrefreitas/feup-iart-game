package game;

import init.NineMansMorris;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

//import complexity.Profiling;

class Adjacent{
	public Vector<String> adj=new Vector<String>();
	
}

class Mill{
	public Vector<String> mill1=new Vector<String>();
	public Vector<String> mill2=new Vector<String>();
}


public class Board {
	public HashMap<String,Slot> board= new HashMap<String,Slot>();
	private HashMap<String,Adjacent> adjacentsBoard= new HashMap<String,Adjacent>();
	private HashMap<String,Mill> millsBoard= new HashMap<String,Mill>();
	
	public Vector<Piece> blackPieces = new Vector<Piece>();
	public Vector<Piece> whitePieces = new Vector<Piece>();
	
	public HashSet<String> playedMoves = new HashSet<String>();
	public Move lastMove=null;
	
	public int black;
	public int white;
	
	private int blackMoves;
	private int whiteMoves;
	
	public int blackStage;
	public int whiteStage;
	public char turn;
	
	public Board(){
		black=9;
		white=9;
		blackMoves=0;
		whiteMoves=0;
		blackStage=0;
		whiteStage=0;
		turn='B';
		initBoard();
		initAdjacentsBoard();
		initmillsBoard();
		
	}

	private void initmillsBoard() {
		Mill a0=new Mill();
		a0.mill1.add("3-0");
		a0.mill1.add("6-0");
		a0.mill2.add("0-3");
		a0.mill2.add("0-6");
		millsBoard.put("0-0",a0);
		
		Mill a1=new Mill();
		a1.mill1.add("0-0");
		a1.mill1.add("0-6");
		a1.mill2.add("1-3");
		a1.mill2.add("2-3");
		millsBoard.put("0-3",a1);
		
		Mill a2=new Mill();
		a2.mill1.add("0-0");
		a2.mill1.add("0-3");
		a2.mill2.add("3-6");
		a2.mill2.add("6-6");
		millsBoard.put("0-6",a2);
		
		Mill a3=new Mill();
		a3.mill1.add("0-0");
		a3.mill1.add("6-0");
		a3.mill2.add("3-1");
		a3.mill2.add("3-2");
		millsBoard.put("3-0",a3);
		
		Mill a4=new Mill();
		a4.mill1.add("0-0");
		a4.mill1.add("3-0");
		a4.mill2.add("6-3");
		a4.mill2.add("6-6");
		millsBoard.put("6-0",a4);
		
		Mill a5=new Mill();
		a5.mill1.add("6-0");
		a5.mill1.add("6-6");
		a5.mill2.add("5-3");
		a5.mill2.add("4-3");
		millsBoard.put("6-3",a5);
		
		Mill a6=new Mill();
		a6.mill1.add("6-0");
		a6.mill1.add("6-3");
		a6.mill2.add("3-6");
		a6.mill2.add("0-6");
		millsBoard.put("6-6",a6);
		
		Mill a7=new Mill();
		a7.mill1.add("6-6");
		a7.mill1.add("0-6");
		a7.mill2.add("3-4");
		a7.mill2.add("3-5");
		millsBoard.put("3-6",a7);
		
		Mill a8=new Mill();
		a8.mill1.add("1-3");
		a8.mill1.add("1-5");
		a8.mill2.add("3-1");
		a8.mill2.add("5-1");
		millsBoard.put("1-1",a8);
		
		Mill a9=new Mill();
		a9.mill1.add("1-1");
		a9.mill1.add("1-5");
		a9.mill2.add("0-3");
		a9.mill2.add("2-3");
		millsBoard.put("1-3",a9);
		
		Mill a10=new Mill();
		a10.mill1.add("1-1");
		a10.mill1.add("1-3");
		a10.mill2.add("3-5");
		a10.mill2.add("5-5");
		millsBoard.put("1-5",a10);
		
		Mill a11=new Mill();
		a11.mill1.add("1-1");
		a11.mill1.add("5-1");
		a11.mill2.add("5-3");
		a11.mill2.add("5-5");
		millsBoard.put("3-1",a11);
		
		Mill a12=new Mill();
		a12.mill1.add("3-1");
		a12.mill1.add("1-1");
		a12.mill2.add("5-3");
		a12.mill2.add("5-5");
		millsBoard.put("5-1",a12);
		
		Mill a13=new Mill();
		a13.mill1.add("4-3");
		a13.mill1.add("6-3");
		a13.mill2.add("5-1");
		a13.mill2.add("5-5");
		millsBoard.put("5-3",a13);
		
		Mill a14=new Mill();
		a14.mill1.add("5-1");
		a14.mill1.add("5-3");
		a14.mill2.add("3-5");
		a14.mill2.add("1-5");
		millsBoard.put("5-5",a14);
		
		Mill a15=new Mill();
		a15.mill1.add("1-5");
		a15.mill1.add("5-5");
		a15.mill2.add("3-4");
		a15.mill2.add("3-6");
		millsBoard.put("3-5",a15);
		
		Mill a16=new Mill();
		a16.mill1.add("3-2");
		a16.mill1.add("4-2");
		a16.mill2.add("2-3");
		a16.mill2.add("2-4");
		millsBoard.put("2-2",a16);
		
		Mill a17=new Mill();
		a17.mill1.add("2-2");
		a17.mill1.add("2-4");
		a17.mill2.add("0-3");
		a17.mill2.add("1-3");
		millsBoard.put("2-3",a17);
		
		Mill a18=new Mill();
		a18.mill1.add("2-2");
		a18.mill1.add("2-3");
		a18.mill2.add("3-4");
		a18.mill2.add("4-4");
		millsBoard.put("2-4",a18);
		
		Mill a19=new Mill();
		a19.mill1.add("2-2");
		a19.mill1.add("4-2");
		a19.mill2.add("3-1");
		a19.mill2.add("3-0");
		millsBoard.put("3-2",a19);
		
		Mill a20=new Mill();
		a20.mill1.add("3-2");
		a20.mill1.add("2-2");
		a20.mill2.add("4-3");
		a20.mill2.add("4-4");
		millsBoard.put("4-2",a20);
		
		Mill a21=new Mill();
		a21.mill1.add("4-2");
		a21.mill1.add("4-4");
		a21.mill2.add("5-3");
		a21.mill2.add("6-3");
		millsBoard.put("4-3",a21);
		
		Mill a22=new Mill();
		a22.mill1.add("4-2");
		a22.mill1.add("4-3");
		a22.mill2.add("3-4");
		a22.mill2.add("2-4");
		millsBoard.put("4-4",a22);
		
		Mill a23=new Mill();
		a23.mill1.add("2-4");
		a23.mill1.add("4-4");
		a23.mill2.add("3-5");
		a23.mill2.add("3-6");
		millsBoard.put("3-4",a23);
	}

	public void initAdjacentsBoard(){
		Adjacent a0=new Adjacent();
		a0.adj.add("3-0");
		a0.adj.add("0-3");
		adjacentsBoard.put("0-0",a0);
		
		Adjacent a1=new Adjacent();
		a1.adj.add("0-0");
		a1.adj.add("0-6");
		a1.adj.add("1-3");
		adjacentsBoard.put("0-3",a1);
		
		Adjacent a2=new Adjacent();
		a2.adj.add("3-6");
		a2.adj.add("0-3");
		adjacentsBoard.put("0-6",a2);
		
		Adjacent a3=new Adjacent();
		a3.adj.add("0-0");
		a3.adj.add("3-1");
		a3.adj.add("6-0");
		adjacentsBoard.put("3-0",a3);
		
		Adjacent a4=new Adjacent();
		a4.adj.add("3-0");
		a4.adj.add("6-3");
		adjacentsBoard.put("6-0",a4);
		
		Adjacent a5=new Adjacent();
		a5.adj.add("6-0");
		a5.adj.add("5-3");
		a5.adj.add("6-6");
		adjacentsBoard.put("6-3",a5);
		
		Adjacent a6=new Adjacent();
		a6.adj.add("3-6");
		a6.adj.add("6-3");
		adjacentsBoard.put("6-6",a6);
		
		Adjacent a7=new Adjacent();
		a7.adj.add("6-6");
		a7.adj.add("3-5");
		a7.adj.add("0-6");
		adjacentsBoard.put("3-6",a7);
		
		Adjacent a8=new Adjacent();
		a8.adj.add("1-3");
		a8.adj.add("3-1");
		adjacentsBoard.put("1-1",a8);
		
		Adjacent a9=new Adjacent();
		a9.adj.add("1-1");
		a9.adj.add("1-5");
		a9.adj.add("0-3");
		a9.adj.add("2-3");
		adjacentsBoard.put("1-3",a9);
		
		Adjacent a10=new Adjacent();
		a10.adj.add("1-3");
		a10.adj.add("3-5");
		adjacentsBoard.put("1-5",a10);
		
		Adjacent a11=new Adjacent();
		a11.adj.add("1-1");
		a11.adj.add("3-0");
		a11.adj.add("3-2");
		a11.adj.add("5-1");
		adjacentsBoard.put("3-1",a11);
		
		Adjacent a12=new Adjacent();
		a12.adj.add("3-1");
		a12.adj.add("5-3");
		adjacentsBoard.put("5-1",a12);
		
		Adjacent a13=new Adjacent();
		a13.adj.add("5-1");
		a13.adj.add("5-5");
		a13.adj.add("4-3");
		a13.adj.add("6-3");
		adjacentsBoard.put("5-3",a13);
		
		Adjacent a14=new Adjacent();
		a14.adj.add("5-3");
		a14.adj.add("3-5");
		adjacentsBoard.put("5-5",a14);
		
		Adjacent a15=new Adjacent();
		a15.adj.add("5-5");
		a15.adj.add("3-4");
		a15.adj.add("3-6");
		a15.adj.add("1-5");
		adjacentsBoard.put("3-5",a15);
		
		Adjacent a16=new Adjacent();
		a16.adj.add("2-3");
		a16.adj.add("3-2");
		adjacentsBoard.put("2-2",a16);
		
		Adjacent a17=new Adjacent();
		a17.adj.add("2-2");
		a17.adj.add("1-3");
		a17.adj.add("2-4");
		adjacentsBoard.put("2-3",a17);
		
		Adjacent a18=new Adjacent();
		a18.adj.add("2-3");
		a18.adj.add("3-4");
		adjacentsBoard.put("2-4",a18);
		
		Adjacent a19=new Adjacent();
		a19.adj.add("2-2");
		a19.adj.add("3-1");
		a19.adj.add("4-2");
		adjacentsBoard.put("3-2",a19);
		
		Adjacent a20=new Adjacent();
		a20.adj.add("3-2");
		a20.adj.add("4-3");
		adjacentsBoard.put("4-2",a20);
		
		Adjacent a21=new Adjacent();
		a21.adj.add("4-2");
		a21.adj.add("4-4");
		a21.adj.add("5-3");
		adjacentsBoard.put("4-3",a21);
		
		Adjacent a22=new Adjacent();
		a22.adj.add("3-4");
		a22.adj.add("4-3");
		adjacentsBoard.put("4-4",a22);
		
		Adjacent a23=new Adjacent();
		a23.adj.add("2-4");
		a23.adj.add("3-5");
		a23.adj.add("4-4");
		adjacentsBoard.put("3-4",a23);
	}
	
	public void initBoard(){
		board.put("0-0",new Slot());
		board.put("0-3",new Slot());
		board.put("0-6",new Slot());
		board.put("1-1",new Slot());
		board.put("1-3",new Slot());
		board.put("1-5",new Slot());
		board.put("2-2",new Slot());
		board.put("2-3",new Slot());
		board.put("2-4",new Slot());
		board.put("3-0",new Slot());
		board.put("3-1",new Slot());
		board.put("3-2",new Slot());
		board.put("3-4",new Slot());
		board.put("3-5",new Slot());
		board.put("3-6",new Slot());
		board.put("4-2",new Slot());
		board.put("4-3",new Slot());
		board.put("4-4",new Slot());
		board.put("5-1",new Slot());
		board.put("5-3",new Slot());
		board.put("5-5",new Slot());
		board.put("6-0",new Slot());
		board.put("6-3",new Slot());
		board.put("6-6",new Slot());
	}

	public char gameOver()
	{
		if(black<3)
			return 'W';
		if(white<3)
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
			board.get(strFinalPos).piece=p;
			
			
			
			if(m.removedPiece!=null)
			{
				board.get(m.removedPiece.keyPos).piece=null;
				int idx=0;
				for(Piece pR : piecesRemove)
				{
					if(pR.keyPos.startsWith(m.removedPiece.keyPos))
					{
						break;
					}
					idx++;
				}
				piecesRemove.remove(idx);
				if(m.removedPiece.getValue()=='B')
				{
					black--;
				}else
				{
					white--;
				}
			}
			
		}else
		{
			String strInitPos = m.initPos[0]+"-"+m.initPos[1];
			String strFinalPos = m.finalPos[0]+"-"+m.finalPos[1];
			Piece myP=board.get(strInitPos).piece;
			
			if(myP==null)
			{
				System.out.println("peça: "+strInitPos+" não existe no board.");
				this.getMatrix();
				System.out.println("move: "+m.value+" final: "+m.finalPos[0]+"-"+m.finalPos[1]);
				if(m.removedPiece==null)
				{
					System.out.println("remove piece é null");
				}else
				{
					System.out.println("remove piece é: "+m.removedPiece.keyPos);
				}
				System.exit(1);
			}
			myP.x=m.finalPos[0];
			myP.y=m.finalPos[1];
			myP.keyPos=strFinalPos;
			board.get(strFinalPos).piece=myP;
			board.get(strInitPos).piece=null;
			
			if(m.removedPiece!=null)
			{
				if(board.get(m.removedPiece.keyPos).piece==null)
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
				board.get(m.removedPiece.keyPos).piece=null;
				//pieces.remove(m.removedPiece);
				int idx=0;
				for(Piece pR : piecesRemove)
				{
					if(pR.keyPos.startsWith(m.removedPiece.keyPos))
					{
						break;
					}
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
					black--;
				}else
				{
					white--;
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
		
		if(black<=3)
			blackStage=2;
		if(white<=3)
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
		
		if(white<3 || black<3)
			return ret;
		
		int stage;
		if(turn=='B')
		{
			stage=blackStage;
		}else{
			stage=whiteStage;
		}
		//Profiling prof=new Profiling(stage,black+white);
		if(stage==0)
		{
			
			for(String strBoard : board.keySet())
			{
				if(board.get(strBoard).piece==null)
				{
					
					String[] strSplit=strBoard.split("-");
					int x=Integer.parseInt(strSplit[0]);
					int y=Integer.parseInt(strSplit[1]);
					Vector<String> PiecesToRemove = millFormed(x,y,turn,-1,-1);
					if(PiecesToRemove!=null && PiecesToRemove.size()>0)
					{
						// criar moves com pessas removidas
						for(String strRemove : PiecesToRemove)
						{
							if(board.get(strRemove).piece==null)
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
							Piece pRemove=board.get(strRemove).piece.clone();
							Move m=new Move(stage,turn,0,0,x,y,pRemove);
							ret.add(m);
						//	prof.inc();
						}
						
					}else
					{
						
						Move m=new Move(stage,turn,0,0,x,y,null);
						ret.add(m);
					}
				}
				//prof.inc();
			}
		}else if(stage==1)
		{
			Vector<Piece> pieces=null;
			if(turn=='B')
			{
				pieces=blackPieces;
			}else if(turn=='W')
			{
				pieces=whitePieces;
			}
			for(Piece p : pieces)
			{
				Adjacent a=adjacentsBoard.get(p.keyPos);
				for(String strAdj : a.adj)
				{
					if(board.get(strAdj).piece==null)
					{
						
						String[] strSplit = strAdj.split("-");
						int x=Integer.parseInt(strSplit[0]);
						int y=Integer.parseInt(strSplit[1]);
						Vector<String> PiecesToRemove = millFormed(x,y,turn,p.getX(),p.getY());
						if(PiecesToRemove!=null && PiecesToRemove.size()>0)
						{
							// criar moves com pessas removidas
							for(String strRemove : PiecesToRemove)
							{
								Piece pRemove=board.get(strRemove).piece.clone();
								Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,pRemove);
								ret.add(m);
								//prof.inc();
							}
							
						}else
						{
							Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,null);
							ret.add(m);
						}
						
						
					}
					//prof.inc();
				}
				//prof.inc();
			}
		}else if(stage==2)
		{
			Vector<Piece> pieces=null;
			if(turn=='B')
			{
				pieces=blackPieces;
			}else if(turn=='W')
			{
				pieces=whitePieces;
			}
			for(Piece p : pieces)
			{
				
				for(String strBoard : board.keySet())
				{
					if(board.get(strBoard).piece==null)
					{
					
						String[] strSplit = strBoard.split("-");
						int x=Integer.parseInt(strSplit[0]);
						int y=Integer.parseInt(strSplit[1]);
						Vector<String> PiecesToRemove = millFormed(x,y,turn,p.getX(),p.getY());
						if(PiecesToRemove!=null && PiecesToRemove.size()>0)
						{
							// criar moves com pessas removidas
							for(String strRemove : PiecesToRemove)
							{
								Piece pRemove=board.get(strRemove).piece.clone();
								Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,pRemove);
								ret.add(m);
								//prof.inc();
							}
							
						}else
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
	
	private Vector<String> millFormed(int x, int y, char turn, int currentX, int currentY) {
		
		Vector<String> onMill=new Vector<String>();
		Vector<String> ret=new Vector<String>();
		
		
		String pieceKey=x+"-"+y;
		Mill mill=millsBoard.get(pieceKey);
		if(board.get(mill.mill1.get(0)).piece!=null && 
				!board.get(mill.mill1.get(0)).piece.keyPos.startsWith(currentX+"-"+currentY) && 
				board.get(mill.mill1.get(0)).piece.getValue() == turn &&
		   board.get(mill.mill1.get(1)).piece!=null && 
				   !board.get(mill.mill1.get(1)).piece.keyPos.startsWith(currentX+"-"+currentY) &&
				   board.get(mill.mill1.get(1)).piece.getValue() == turn ||
		   board.get(mill.mill2.get(0)).piece!=null && 
				   !board.get(mill.mill2.get(0)).piece.keyPos.startsWith(currentX+"-"+currentY) &&
				   board.get(mill.mill2.get(0)).piece.getValue() == turn &&
		   board.get(mill.mill2.get(1)).piece!=null && 
				   !board.get(mill.mill2.get(1)).piece.keyPos.startsWith(currentX+"-"+currentY) &&
				   board.get(mill.mill2.get(1)).piece.getValue() == turn)
		{
			
			Vector<Piece> pieces=null;
			if(turn=='B')
			{
				pieces=whitePieces;  //peças adversarias
			}else if(turn=='W')
			{
				pieces=blackPieces;  //peças adversarias
			}
			for(Piece p : pieces)
			{
				if(board.get(p.keyPos).piece!=null)
				{
					Mill millAdv=millsBoard.get(p.keyPos);
					if(board.get(millAdv.mill1.get(0)).piece!=null && board.get(millAdv.mill1.get(0)).piece.getValue() != turn &&
					   board.get(millAdv.mill1.get(1)).piece!=null && board.get(millAdv.mill1.get(1)).piece.getValue() != turn ||
					   board.get(millAdv.mill2.get(0)).piece!=null && board.get(millAdv.mill2.get(0)).piece.getValue() != turn &&
					   board.get(millAdv.mill2.get(1)).piece!=null && board.get(millAdv.mill2.get(1)).piece.getValue() != turn)
					{
						onMill.add(p.keyPos);
					}else
					{
						ret.add(p.keyPos);
					}
				}
			}
			
			
		}else
		{
			return null;
		}
		
		
		if(ret.size()==0)
			return onMill;
		else
			return ret;
	}

	public char[][] getMatrix() {
		char[][] matrix = new char[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				String key = i + "-" + j;
				if (board.containsKey(key)) {
					Piece p = board.get(key).piece;
					if (p != null)
						matrix[i][j] = p.getValue();
					else
						matrix[i][j] = 'O';
				}
				else {
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
	public int evaluate(char value) {
		int ret=0;
		if(this.gameOver()==value)
			ret= 1000;
		else if(this.gameOver()!='X')
			ret= -1000;
		else if(this.gameOver()=='X')
		{
			if(false && playedMoves.contains(lastMove.getHashKey()))
			{
				if(lastMove.value==value)
				{
					ret= -50;
				}else
				{
					ret= 50;
				}
				
			}else
			if(value=='B')
			{
				ret= black - white;
				ret*=2;
			}else
			{
				ret= white-black;
				ret*=2;
			}
		}
		if(ret<-999 || ret>999)
			return ret;
		
		for(Piece p : blackPieces)
		{
			for(String s:this.millsBoard.get(p.keyPos).mill1)
			{
				if(board.get(s).piece!=null && board.get(s).piece.getValue()=='B')
				{
					if(value=='B')
						ret++;
					else
						ret--;
				}
			}
			for(String s:this.millsBoard.get(p.keyPos).mill2)
			{
				if(board.get(s).piece!=null && board.get(s).piece.getValue()=='B')
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
			for(String s:this.millsBoard.get(p.keyPos).mill1)
			{
				if(board.get(s).piece!=null && board.get(s).piece.getValue()=='W')
				{
					if(value=='W')
						ret++;
					else
						ret--;
				}
			}
			for(String s:this.millsBoard.get(p.keyPos).mill2)
			{
				if(board.get(s).piece!=null && board.get(s).piece.getValue()=='W')
				{
					if(value=='W')
						ret++;
					else
						ret--;
				}
			}
		}
		
			
		return ret;
	}

	public boolean stopMiniMax(int nMoves) {
		
		if(nMoves>3)
			return true;
		
		if(gameOver()=='X')
			return false;
		else 
			return true;
	}

	public void unmakeMove(Move move) {
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
					board.get(move.removedPiece.keyPos).piece=poux;
					black++;
					
					
				}else
				{
					Piece poux=move.removedPiece.clone();
					whitePieces.add(poux);
					board.get(move.removedPiece.keyPos).piece=poux;
					white++;
				}
			}
			int finalX=move.finalPos[0];
			int finalY=move.finalPos[1];
			Piece p=board.get(finalX+"-"+finalY).piece;
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
			}else
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
			board.get(finalX+"-"+finalY).piece=null;
			
		}else{
			if(move.removedPiece!=null)
			{
				if(move.removedPiece.getValue()=='B')
				{
					Piece poux=move.removedPiece.clone();
					blackPieces.add(poux);
					board.get(move.removedPiece.keyPos).piece=poux;
					black++;
					
				}else
				{
					Piece poux=move.removedPiece.clone();
					whitePieces.add(poux);
					board.get(move.removedPiece.keyPos).piece=poux;
					white++;
				}
			}
			int finalX=move.finalPos[0];
			int finalY=move.finalPos[1];
			Piece p=board.get(finalX+"-"+finalY).piece;
			if(p.getValue()=='B')
			{
				
				
				blackMoves--;
				this.turn='B';
			}else
			{
				
				whiteMoves--;
				this.turn='W';
			}
			p.x=move.initPos[0];
			p.y=move.initPos[1];
			p.keyPos=p.x+"-"+p.y;
			board.get(p.keyPos).piece=p;
			board.get(finalX+"-"+finalY).piece=null;
		}
		if(move.value=='B')
		{
			blackStage=move.stage;
		}else{
			whiteStage=move.stage;
		}
		if(black<=3)
			blackStage=2;
		else if(blackMoves>=9)
			blackStage=1;
		else blackStage=0;
		
		if(white<=3)
			whiteStage=2;
		else if(whiteMoves>=9)
			whiteStage=1;
		else whiteStage=0;
		/*if(NineMansMorris.i>=27)
		{
			System.out.println("UnmakeMove");
			move.showMove();
			getMatrix();
		}*/
	}
	
	
}
