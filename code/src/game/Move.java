package game;

public class Move {
	public int stage;
	public char value;
	public int[] initPos;
	public int[] finalPos;
	public Piece removedPiece;
	
	
	public Move(int st, char v, int ix, int iy, int fx, int fy, Piece p)
	{
		stage=st;
		value=v;
		if(stage==0)
		{
			initPos=null;
			
		}else{
			initPos = new int[2];
			initPos[0]=ix;
			initPos[1]=iy;
			
		}
		finalPos = new int[2];
		finalPos[0]=fx;
		finalPos[1]=fy;
		
		removedPiece=p;
	}
	
	public Boolean valid()
	{
		
		
		return true;
	}
}
