
public class Bot extends Player{

	int difficulty; //1-f�cil, 2-m�dio, 3-dif�cil
	
	public Bot(String name, int nMoves, int nPieces, char color, int difficulty, Board board)
	{
		super(name,nMoves,nPieces,color,board);
		this.difficulty=difficulty;
	}

	@Override
	public void play() 
	{
		//TODO: COMPLETA-ME
	}
}
