import java.util.*;
class TicTacToe
{
	public static Piece [][] game = new Piece [3][3];
	public static int turn = 0;
	public static boolean win = false;
	
	
	public static void main(String [] argv)
	{
		buildBoard();
		int player;
		
		try
		{
		while(win == false)
		{
			if (turn % 2 == 0)
				player = 0;
			else 
				player = 1;
			printBoard();
			System.out.println();
			System.out.println("Turn: " + turn);
			System.out.println("Player: " + player);
			System.out.println("Please insert coordinates.");
			Scanner userin = new Scanner(System.in);
			userin.useDelimiter(",\\s*|\\s+");
			/*
			if (derp.next().equalsIgnoreCase("quit"))
				{
					break;
				}
			*/
			int x = userin.nextInt();
			int y = userin.nextInt();
			if (game[x][y].getState() != -1)
			{
				System.out.println("CHEATER!");
				continue;
			}
			game[x][y].setState(player);
			turn ++;
			checkWin();
		}
		System.out.println("You Win!");
		printBoard();
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("You had one job! That isn't a valid coordinate!! D:");
		}
		
	}
	
	private static void checkWin()
	{
		for (int idx= 0; idx < game.length; idx++)
		{
			if ((game[idx][0].getState() == game[idx][1].getState()) && (game[idx][0].getState() == game[idx][2].getState()) && (game[idx][0].getState() != -1))
				win = true;
		}
		for (int idx= 0; idx < game[0].length; idx++)
		{
			if ((game[0][idx].getState() == game[1][idx].getState()) && (game[0][idx].getState() == game[2][idx].getState()) && (game[0][idx].getState() != -1))
				win = true;
		}
		if ((game[0][0].getState() == game[1][1].getState()) && (game[0][0].getState() == game[2][2].getState()) && (game[0][0].getState() != -1))
			win = true;
		if ((game[0][2].getState() == game[1][1].getState()) && (game[0][2].getState() == game[2][0].getState()) && (game[0][2].getState() != -1))
			win = true;
	}
	
	public static void buildBoard()
	{
		Piece a1 = new Piece(0,0,-1);
		Piece a2 = new Piece(1,0,-1);
		Piece a3 = new Piece(2,0,-1);
		Piece a4 = new Piece(0,1,-1);
		Piece a5 = new Piece(1,1,-1);
		Piece a6 = new Piece(2,1,-1);
		Piece a7 = new Piece(0,2,-1);
		Piece a8 = new Piece(1,2,-1);
		Piece a9 = new Piece(2,2,-1);
		
		game [0][0] = a1;
		game [1][0] = a2;
		game [2][0] = a3;
		game [0][1] = a4;
		game [1][1] = a5;
		game [2][1] = a6;
		game [0][2] = a7;
		game [1][2] = a8;
		game [2][2] = a9;
		
	}
	
	public static void printBoard()
	{

		Piece [] order = new Piece [9];
		
		order[0] = game[0][0];
		order[1] = game[1][0];
		order[2] = game[2][0];
		order[3] = game[0][1];
		order[4] = game[1][1];
		order[5] = game[2][1];
		order[6] = game[0][2];
		order[7] = game[1][2];
		order[8] = game[2][2];
		
		
		char [] order2 = new char [order.length];
		
		
		for (int idx = 0; idx < order.length; idx++)
		{
			if (order[idx].getState() == 0)
				order2[idx] = 'X';
			if (order[idx].getState() == 1)
				order2[idx] = 'O';
			if (order[idx].getState() == -1)
				order2[idx] = ' ';
		}
		System.out.print(order2[0]);
		System.out.print(" | ");
		System.out.print(order2[1]);
		System.out.print(" | ");
		System.out.println(order2[2]);
		System.out.println("---------");
		System.out.print(order2[3]);
		System.out.print(" | ");
		System.out.print(order2[4]);
		System.out.print(" | ");
		System.out.println(order2[5]);
		System.out.println("---------");
		System.out.print(order2[6]);
		System.out.print(" | ");
		System.out.print(order2[7]);
		System.out.print(" | ");
		System.out.println(order2[8]);
	}
	
	
}