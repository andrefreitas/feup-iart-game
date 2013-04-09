package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

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
	
	private Vector<Piece> blackPieces = new Vector<Piece>();
	private Vector<Piece> whitePieces = new Vector<Piece>();
	
	private int black;
	private int white;
	
	public Board(){
		black=9;
		white=9;
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
		a9.adj.add("3-0");
		a9.adj.add("3-2");
		a9.adj.add("5-1");
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
			return 'B';
		if(white<3)
			return 'P';
		
		return 'X';
	}
	
	public void makeMove(Move m)
	{
		Vector<Piece> pieces=null;
		if(m.value=='P')
		{
			pieces=blackPieces;
		}else if(m.value=='B')
		{
			pieces=whitePieces;
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
				pieces.remove(m.removedPiece);
			}
			
		}else
		{
			String strInitPos = m.initPos[0]+"-"+m.initPos[1];
			String strFinalPos = m.finalPos[0]+"-"+m.finalPos[1];
			Piece myP=board.get(strInitPos).piece;
			myP.x=m.finalPos[0];
			myP.y=m.finalPos[1];
			board.get(strFinalPos).piece=myP;
			board.get(strInitPos).piece=null;
			
			if(m.removedPiece!=null)
			{
				board.get(m.removedPiece.keyPos).piece=null;
				pieces.remove(m.removedPiece);
			}
		}
	}
	
	public Vector<Move> getPossibleMoves(char turn,int stage)
	{
		Vector<Move> ret = new Vector<Move>();
		
		if(stage==0)
		{
			for(String strBoard : board.keySet())
			{
				if(board.get(strBoard).piece==null)
				{
					String[] strSplit=strBoard.split("-");
					int x=Integer.parseInt(strSplit[0]);
					int y=Integer.parseInt(strSplit[1]);
					Vector<String> PiecesToRemove = millFormed(x,y,turn);
					if(PiecesToRemove.size()>0)
					{
						// criar moves com pessas removidas
						for(String strRemove : PiecesToRemove)
						{
							Move m=new Move(stage,turn,0,0,x,y,board.get(strRemove).piece);
							ret.add(m);
						}
						
					}else
					{
						Move m=new Move(stage,turn,0,0,x,y,null);
						ret.add(m);
					}
				}
			}
		}else if(stage==1)
		{
			Vector<Piece> pieces=null;
			if(turn=='P')
			{
				pieces=blackPieces;
			}else if(turn=='B')
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
						//TODO continuar...
						String[] strSplit = strAdj.split("-");
						int x=Integer.parseInt(strSplit[0]);
						int y=Integer.parseInt(strSplit[1]);
						Vector<String> PiecesToRemove = millFormed(x,y,turn);
						if(PiecesToRemove.size()>0)
						{
							// criar moves com pessas removidas
							for(String strRemove : PiecesToRemove)
							{
								Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,board.get(strRemove).piece);
								ret.add(m);
							}
							
						}else
						{
							Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,null);
							ret.add(m);
						}
						
						
					}
				}
			}
		}else if(stage==2)
		{
			Vector<Piece> pieces=null;
			if(turn=='P')
			{
				pieces=blackPieces;
			}else if(turn=='B')
			{
				pieces=whitePieces;
			}
			for(Piece p : pieces)
			{
				
				for(String strBoard : board.keySet())
				{
					if(board.get(strBoard).piece==null)
					{
						//TODO continuar...
						String[] strSplit = strBoard.split("-");
						int x=Integer.parseInt(strSplit[0]);
						int y=Integer.parseInt(strSplit[1]);
						Vector<String> PiecesToRemove = millFormed(x,y,turn);
						if(PiecesToRemove!=null && PiecesToRemove.size()>0)
						{
							// criar moves com pessas removidas
							for(String strRemove : PiecesToRemove)
							{
								Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,board.get(strRemove).piece);
								ret.add(m);
							}
							
						}else
						{
							Move m=new Move(stage,turn,p.getX(),p.getY(),x,y,null);
							ret.add(m);
						}
						
						
					}
				}
			}
		}
		
		return ret;
	}
	
	private Vector<String> millFormed(int x, int y, char turn) {
		
		Vector<String> onMill=new Vector<String>();
		Vector<String> ret=new Vector<String>();
		
		// TODO se mill formada retornar pessas possiveis de remover
		String pieceKey=x+"-"+y;
		Mill mill=millsBoard.get(pieceKey);
		if(board.get(mill.mill1.get(0)).piece!=null && board.get(mill.mill1.get(0)).piece.getValue() == turn &&
		   board.get(mill.mill1.get(1)).piece!=null && board.get(mill.mill1.get(1)).piece.getValue() == turn ||
		   board.get(mill.mill2.get(0)).piece!=null && board.get(mill.mill2.get(0)).piece.getValue() == turn &&
		   board.get(mill.mill2.get(1)).piece!=null && board.get(mill.mill2.get(1)).piece.getValue() == turn)
		{
			
			Vector<Piece> pieces=null;
			if(turn=='P')
			{
				pieces=whitePieces;  //peças adversarias
			}else if(turn=='B')
			{
				pieces=blackPieces;  //peças adversarias
			}
			for(Piece p : pieces)
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
			}
			System.out.print("\n");
		}
		return matrix;
	}

	
	
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
	
	
}
