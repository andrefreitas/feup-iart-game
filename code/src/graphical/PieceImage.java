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
		white.setVisible(true);
		black.setVisible(false);
		visible='W';
		last='W';
	}
	
	public void showBlack()
	{
		white.setVisible(false);
		black.setVisible(true);
		visible='B';
		last='B';
	}
	
	public void hidde()
	{
		white.setVisible(false);
		black.setVisible(false);
		visible='X';
	}
	
	public void showLast()
	{
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
