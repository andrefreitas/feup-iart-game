package cli;

public class CLI {
	public static void printBoard(char [][]representation){
		for(int i=0; i<7;i++){
			for (int j=0; j<7;j++){
				System.out.print(representation[i][j] +" ");
			}
			System.out.print("\n");
		}
	}
}
