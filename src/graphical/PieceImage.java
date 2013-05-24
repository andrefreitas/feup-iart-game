package graphical;

import javax.swing.JLabel;

/**
 * Classe auxiliar para desenho de uma slot do tabuleiro, que pode estar desocupada ou ocupada com uma peça preta ou branca.
 */
public class PieceImage 
{
	private char visible='X'; //Cor atualmente visível (X-sem cor, B - black, W - white)
	private char last='X'; //Cor desenhada na última chamada a um método de desenho para esta slot
	private JLabel black=null;
	private JLabel white=null;
	
	public String pos="out";
	
	public PieceImage(JLabel b, JLabel w)
	{
		black=b;
		white=w;
	}

	/**
	 * Desenhar a peça como branca.
	 */
	public void showWhite()
	{
		
		white.setVisible(true);
		black.setVisible(false);
		white.repaint();
		black.repaint();
		visible='W';
		last='W';
	}
	
	/**
	 * Desenhar a peça como preta.
	 */
	public void showBlack()
	{
		
		white.setVisible(false);
		black.setVisible(true);
		white.repaint();
		black.repaint();
		visible='B';
		last='B';
	}
	
	/**
	 * Fazer a peça desaparecer da slot (visualmente).
	 */
	public void hide()
	{
		
		white.setVisible(false);
		black.setVisible(false);
		visible='X';
	}
	
	/**
	 * Desenhar a peça com a cor da última vez que foi desenhada (ou sem peça).
	 */
	public void showLast()
	{
		
		if(last=='B')
			showBlack();
		if(last=='W')
			showWhite();
		if(last=='X')
			hide();
	}
	
	public char getVisible()
	{
		return visible;
	}

}
