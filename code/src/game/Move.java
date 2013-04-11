package game;

public class Move {
	public final int stage;
	public final char value;
	public final int[] initPos;
	public final int[] finalPos;
	public final Piece removedPiece;
	
	
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
	
	public void showMove()
	{
		System.out.println("ShowMove - stage: "+stage+" value: "+value);
		if(stage!=0)
			System.out.println("Initpos: "+initPos[0]+"-"+initPos[1]);
		System.out.println("FinalPos: "+finalPos[0]+"-"+finalPos[1]);
		if(removedPiece!=null)
			System.out.println("Removed Piece: "+removedPiece.keyPos+" value: "+removedPiece.getValue());
	}
	
	

	public String getHashKey() {
		String ret=""+stage+value+finalPos[0]+finalPos[1];
		if(initPos!=null)
			ret+=""+initPos[0]+initPos[1];
		if(removedPiece!=null)
			ret+=""+removedPiece.keyPos+removedPiece.getValue();
		return ret;
	}
}
