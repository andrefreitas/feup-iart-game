
public class Slot {

	public int x;
	public int y;
	
	public int pixelx=0;
	public int pixely=0;
	
	public Slot[] adjacents;
	public Slot[] mill1 = new Slot[2];
	public Slot[] mill2 = new Slot[2];
	
	public boolean occupied;
	
	public Piece piece=null;
}
