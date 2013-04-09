package game;

public class Piece {
	private char value;
	private int x;
	private int y;
	
	public Piece(char value,int x, int y){
		this.value=value;
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getValue(){
		return value;
	}
}
