import java.awt.Point;
import javax.swing.JLabel;

public class Piece 
{	
	public JLabel image;
	
	public boolean active;
		
	public Player owner; //substitui a cor, permite mais funcionalidade com menos código
	
	public Point coord = new Point();
	public Point coord_old = new Point();
	public Point delt = new Point();
	
	public Slot OnBoard;
	public Slot OnBoardOld;
	
	public final int mySize=47;
	
	public Piece(int x, int y, Player owner, JLabel image)
	{
		coord.setLocation(x,y);
		coord_old.setLocation(x,y);		
		this.owner=owner;
		this.image=image;
				
		active=true;
		OnBoard=null;
		OnBoardOld=null;
		
		image.setBounds(coord.x, coord.y, mySize, mySize);
	}
}
