package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Board {
	private HashMap<String,Piece> board= new HashMap<String,Piece>();
	
	public Board(){
		initBoard();
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
