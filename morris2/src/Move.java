
public class Move {

	public Piece peca;
	public Slot dest;
	public Slot orig;
	
	public Slot dest_old; //tem de ser atribuido por 'copy' e nao '='
	public Slot orig_old; //----------------------------------------
	
	public int value; //usado para minimax
}
