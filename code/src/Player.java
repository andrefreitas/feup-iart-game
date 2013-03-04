
public abstract class Player {

	public String name;
	public int nMoves=0;
	public int nPieces;
	public char color;
	public Board board;
	public boolean finishedPlay = false;
	
	public Player(String name, int nMoves, int nPieces, char color, Board board)
	{
		this.name=name;
		this.nMoves=nMoves;
		this.nPieces=nPieces;
		this.color=color;
		this.board=board;
	}
	
	abstract public void play();
}
