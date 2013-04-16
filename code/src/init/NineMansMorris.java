package init;

import graphical.MainMenu;
//import game.Tests;
//import complexity.Profiling;

/**
 * Classe de iniciação da aplicação NineMansMorris
 */
public class NineMansMorris 
{	
	/**
	 * Método main da aplicação, responsável por criar o menu da aplicação
	 * @param args Argumentos da linha de comandos
	 */
	public static void main(String[] args) 
	{			
		long before=System.currentTimeMillis();		
		
		new MainMenu();
		//Tests.test1(500);
		//Tests.test2();
		
		long after=System.currentTimeMillis();
		System.out.println("Tempo em milis para criação do menu: "+(after-before));
	}
}
