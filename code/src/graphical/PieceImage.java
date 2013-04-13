package graphical;

import javax.swing.JLabel;

public class PieceImage {

	private char visible='X';
	private char last='X';
	private JLabel black=null;
	private JLabel white=null;
	
	public String pos="out";
	
	public PieceImage(JLabel b, JLabel w)
	{
		black=b;
		white=w;
	}
	
	public void showWhite()
	{
		System.out.println("showWhite "+pos);
		white.setVisible(true);
		black.setVisible(false);
		white.repaint();
		black.repaint();
		visible='W';
		last='W';
	}
	
	public void showBlack()
	{
		System.out.println("showBlack "+pos);
		white.setVisible(false);
		black.setVisible(true);
		white.repaint();
		black.repaint();
		visible='B';
		last='B';
	}
	
	public void hidde()
	{
		System.out.println("hidde "+pos);
		white.setVisible(false);
		black.setVisible(false);
		visible='X';
	}
	
	public void showLast()
	{
		System.out.println("showLast "+pos);
		if(last=='B')
			showBlack();
		if(last=='W')
			showWhite();
		if(last=='X')
			hidde();
		
		
	}
	
	public char getVisible()
	{
		return visible;
	}
}
