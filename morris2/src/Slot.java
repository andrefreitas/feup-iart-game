
public class Slot {

	public int x;
	public int y;
	
	public int pixelx;
	public int pixely;
	
	public Slot[] adjacents;
	public Slot[] mill1 = new Slot[2];
	public Slot[] mill2 = new Slot[2];
	
	public boolean occupied;
	
	public Piece piece=null;
}
