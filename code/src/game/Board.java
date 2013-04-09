package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

class Adjacent{
	public Vector<String> adj=new Vector<String>();
	
}


public class Board {
	private HashMap<String,Piece> board= new HashMap<String,Piece>();
	private HashMap<String,Adjacent> adjacentsBoard= new HashMap<String,Adjacent>();
	public Board(){
		initBoard();
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
		board.put("0-0",null);
		board.put("0-3",null);
		board.put("0-6",null);
		board.put("1-1",null);
		board.put("1-3",null);
		board.put("1-5",null);
		board.put("2-2",null);
		board.put("2-3",null);
		board.put("2-4",null);
		board.put("3-0",null);
		board.put("3-1",null);
		board.put("3-2",null);
		board.put("3-4",null);
		board.put("3-5",null);
		board.put("3-6",null);
		board.put("4-2",null);
		board.put("4-3",null);
		board.put("4-4",null);
		board.put("5-1",null);
		board.put("5-3",null);
		board.put("5-5",null);
		board.put("6-0",null);
		board.put("6-3",null);
		board.put("6-6",null);
	}

	public Boolean putPiece(Piece p){
		String key=p.getX()+"-"+p.getY();
		if (board.containsKey(key) && board.get(key)==null){
			board.put(key, p);
			return true;
		}
		return false;
	}
	
	public Piece removePiece(int x,int y){
		String key=x+"-"+y;
		if(board.containsKey(key) && board.get(key)!=null){
			Piece p=board.get(key);
			board.put(key, null);
			return p;
		}
			
		return null;
	}
	
	
	public static void main(String[]args ){
		Board b=new Board();
	}
}
