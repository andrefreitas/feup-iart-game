import javax.swing.JLabel;


public class Piece {
	
	public JLabel image;
	public JLabel white;
	public JLabel black;
	
	public boolean active;
	public char color; //'p'-preta, 'b'-branca;
	
	public int coordx;
	public int coordy;
	public int coordx_old;
	public int coordy_old;
	public int deltx;
	public int delty;
	
	public int xOnBoard;
	public int yOnBoard;
	public int xOnBoard_old;
	public int yOnBoard_old;
	
	public int mySize;
}
