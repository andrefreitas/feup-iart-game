
public class Bot extends Player{

	int difficulty; //1-fácil, 2-médio, 3-difícil
	
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
