package game;

public class Piece {
	private char value;
	private int x;
	private int y;
	public String keyPos;
	
	public Piece(char value,int x, int y){
		this.value=value;
		this.x=x;
		this.y=y;
		keyPos=x+"-"+y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public char getValue(){
		return value;
	}
	
	public Piece clone()
	{
		Piece ret = new Piece(value,x,y);
		return ret;
	}
}
