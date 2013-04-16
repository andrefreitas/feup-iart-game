package init;

import graphical.MainMenu;
//import game.Tests;
//import complexity.Profiling;

/**
 * Classe de inicia��o da aplica��o NineMansMorris
 */
public class NineMansMorris 
{	
	/**
	 * M�todo main da aplica��o, respons�vel por criar o menu da aplica��o
	 * @param args Argumentos da linha de comandos
	 */
	public static void main(String[] args) 
	{			
		long before=System.currentTimeMillis();		
		
		new MainMenu();
		//Tests.test1(500);
		//Tests.test2();
		
		long after=System.currentTimeMillis();
		System.out.println("Tempo em milis para cria��o do menu: "+(after-before));
	}
}
