//Ryan Palmer LE, John Gazzara LK, Carmine Freda LN, Nick Gray LE
//Final Project
//I pledge my honor that I have abided by the Stevens Honor System- Ryan Palmer, John Gazzara, Carmine Freda, Nick Gray
//This is written in Java
//Runs on with a Java Runtime Environment

import java.util.*;
class TicTacToe
{
	public static Piece [][] game = new Piece [3][3];
	public static int turn = 0;
	public static boolean win = false;
	
	
	public static void main(String [] argv)
	{
		menu();
		
	}


	private static void menu()
	{
		System.out.println("Welcome to TicTacToe!"); //display menu
		System.out.println("To display instructions type 'i'!");
		System.out.println("To play multiplayer type 'm'!");
		System.out.println("To play single player type 's'!");
		System.out.println("");
		Scanner in = new Scanner(System.in);
		char input = in.next().charAt(0);

		if(input == 'i') //display instructions
			manual();
		if(input == 'm')  //play multiplayer
		{
			multi();
			return;
		}
		if(input == 's') //play singleplayer
		{
			single();
			return;
		}


	}

	private static void single()
	{
		buildBoard();
		int player;
		
		try
		{
		while(win == false)
		{
			if (turn % 2 == 0) //changes player based on turn number
				player = 0;
			else 
				player = 1;
			printBoard();
			System.out.println();
			
			int x = 0;
			int y = 0;			

			if(player == 1) //Is the human player
			{
			System.out.println("Turn: " + turn);
			System.out.println("Player: " + (player+1));
			System.out.println("Please insert coordinates.");
			Scanner userin = new Scanner(System.in);
		
			userin.useDelimiter(",\\s*|\\s+");   
			x = userin.nextInt()-1;
			y = userin.nextInt()-1;
			if (checkValid(x,y) == false)  //this checks to see if the input is valid
			{
				System.out.println("oops that doesn't go there!");
				continue;

			}

			if (game[x][y].getState() != -1)    //this checks to see if the space is not blank when filling a square
			{
				System.out.println("CHEATER!");
				continue;
			}
			game[x][y].setState(player);
			turn ++;
			checkWin(player);
			checkLose();
			}
		
			if(player == 0)     //this the computer AI
			{
				if((checkPossWin(0) != null) && (game[checkPossWin(0).getx1()][checkPossWin(0).gety1()].getState() == -1))
				{	
					loc go = checkPossWin(0);
					game[go.getx1()][go.gety1()].setState(player);
				}

				else if((checkPossWin(1) != null) && (game[checkPossWin(1).getx1()][checkPossWin(1).gety1()].getState() == -1))
				{
					loc go = checkPossWin(1);
					game[go.getx1()][go.gety1()].setState(player);
				}

				else if(turn == 0)
					game[0][0].setState(player);

				else if(game[2][2].getState() == -1)
					game[2][2].setState(player);
				

				else if(game[2][0].getState() == -1)
					game[2][0].setState(player);
 
				else if((checkValid(x+1,y) == true) && (game[x+1][y].getState() == -1))
					game[x+1][y].setState(player);

				else if((checkValid(x-1,y) == true) && (game[x-1][y].getState() == -1))
					game[x-1][y].setState(player);

				else if((checkValid(x,y+1) == true) && (game[x][y+1].getState() == -1))
					game[x][y+1].setState(player);

				else if((checkValid(x,y-1) == true) && (game[x][y-1].getState() == -1))
					game[x][y-1].setState(player);		
				
				
				else if((checkValid(x-1,y-1) == true) && (game[x-1][y-1].getState() == -1))
					game[x-1][y-1].setState(player);
				
				else if((checkValid(x+1,y+1) == true) && (game[x+1][y+1].getState() == -1))
					game[x+1][y+1].setState(player);	
			
				else if((checkValid(x+1,y-1) == true) && (game[x+1][y-1].getState() == -1))
					game[x+1][y-1].setState(player);
				
				else if((checkValid(x-1,y+1) == true) && (game[x-1][y+1].getState() == -1))
					game[x-1][y+1].setState(player);					
			
				else
				{
					for(int j=0; j < game.length; j++)
					{
						for(int z=0; z < game[0].length; z++)
						{
							if(game[j][z].getState() == -1)
								game[j][z].setState(player);
						}
					}

				}
	
				checkWin(player);
				turn ++;
				checkLose();
				

			}
		}
	
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Array index out of bounds");
		}
		catch(InputMismatchException l)
		{
			System.err.println("Thanks for playing.");

		}
	}

	private static loc checkPossWin(int play)    //this checks to see if the computer or the player can possibly get a win
	{						//play = player number
		
		for (int idx= 0; idx < game.length; idx++)   //this checks vertical wins
		{
			if (((game[idx][0].getState() == play) && (game[idx][1].getState() == play)) && (game[idx][0].getState() != -1) && (game[idx][2].getState() == -1))
			{	
				loc winLoc = new loc(idx,2);
				return winLoc;
			}
	
			if (((game[idx][1].getState() == play) && (game[idx][2].getState() == play )) && (game[idx][1].getState() != -1) && (game[idx][0].getState() == -1))
			{	
				loc winLoc = new loc(idx,0);
				return winLoc;
			}
			
			if (((game[idx][0].getState() == play) && (game[idx][2].getState() == play)) && (game[idx][0].getState() != -1) && (game[idx][1].getState() == -1))
			{	
				loc winLoc = new loc(idx,1);
				return winLoc;
			}

		}
		for (int idx= 0; idx < game[0].length; idx++)  //these check horizontal wins
		{
			if (((game[0][idx].getState() == play) && (game[1][idx].getState() == play)) && (game[0][idx].getState() != -1) && (game[2][idx].getState() == -1))
			{		
				loc winLoc = new loc(2,idx);
				return winLoc;
			}
	
			if (((game[1][idx].getState() == play) && (game[2][idx].getState() == play)) && (game[1][idx].getState() != -1) && (game[0][idx].getState() == -1))
			{		
				loc winLoc = new loc(0,idx);
				return winLoc;
			}
			
			if (((game[0][idx].getState() == play) && (game[2][idx].getState() == play)) && (game[0][idx].getState() != -1) && (game[1][idx].getState() == -1))
			{		
				loc winLoc = new loc(1,idx);
				return winLoc;
			}

		}
		if (((game[0][0].getState() == play) && (game[1][1].getState() == play)) && (game[0][0].getState() != -1) && (game[2][2].getState() == -1))         //these check for diagonal wins
			{
				loc winLoc = new loc(2,2);
				return winLoc;
			}
		if (((game[1][1].getState() == play) && (game[2][2].getState() == play)) && (game[1][1].getState() != -1) && (game[0][0].getState() == -1))
			{
				loc winLoc = new loc(0,0);
				return winLoc;
			}
		if (((game[0][0].getState() == play) && (game[2][2].getState() == play)) && (game[0][0].getState() != -1) && (game[1][1].getState() == -1))
			{
				loc winLoc = new loc(1,1);
				return winLoc;
			}





		if (((game[0][2].getState() == play) && (game[1][1].getState() == play)) && (game[0][2].getState() != -1) && (game[2][0].getState() == -1))
			{
				loc winLoc = new loc(2,0);
				return winLoc;
			}

		if (((game[1][1].getState() == play) && (game[2][0].getState() == play)) && (game[1][1].getState() != -1) && (game[0][2].getState() == -1))
			{
				loc winLoc = new loc(0,2);
				return winLoc;
			}

		if (((game[0][2].getState() == play) && (game[2][0].getState() == play)) && (game[0][2].getState() != -1) && (game[1][1].getState() == -1))
			{
				loc winLoc = new loc(1,1);
				return winLoc;
			}
		
		else //if there is no possibillity of a win it returns null
			{
				return null;
			}
		
	


	}

	
	private static boolean checkValid(int x, int y)  //this checks to see if an input is a valid coordinate
	{
		if((x <= 2) && (x >= 0) && (y <= 2) && (y >= 0))
			return true;
		else
			return false;

	}

	private static void multi()   //this is the multiplayer option
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
			int x = userin.nextInt()-1;
			int y = userin.nextInt()-1;
			if (checkValid(x,y) == false)
			{
				System.out.println("oops that doesn't go there!");
				continue;

			}
			if (game[x][y].getState() != -1)
			{
				System.out.println("CHEATER!");
				continue;
			}
			game[x][y].setState(player);
			turn ++;
			checkWin(player);
			checkLose();
		}
	
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{
			while(true)
			{
				System.err.println("Oh no...");
				System.err.println("---------------------------------------");
			}
		}
		catch(InputMismatchException l)
		{
			System.err.println("Thanks for playing.");
		}

	}
	
	private static void manual()    //this displays the game manual
	{
		System.out.println("To win, get three of your pieces in a row before your opponent.");
		System.out.println("Type in coordinates using the format (horizontal),(vertical) or (horizontal) (vertical)  \n  where the coordinates are integers 1, 2, or 3. For example top left is 1,1 or 1 1 and one below that is 1,2 or 1 2.");
		System.out.println(" ");
		System.out.println("Press any key other than an integer to quit.");
		System.out.println("");
		menu();
		return;


	}
	
	private static void checkLose()  //this checks the board for a draw
	{
		if (turn == 9)
		{
			System.out.println("It's a draw!");
			printBoard();
			win = true;
		}
		return;
	
	}
	
	private static void checkWin(int play)  //this checks the board for a win
	{
		for (int idx= 0; idx < game.length; idx++)
		{
			if ((game[idx][0].getState() == game[idx][1].getState()) && (game[idx][0].getState() == game[idx][2].getState()) && (game[idx][0].getState() != -1))
			{	win = true;
				if (play == 1)
					System.out.println("Player 2 wins!");
				else
					System.out.println("Player 1 wins!!");
				
				printBoard();
			}

		}
		for (int idx= 0; idx < game[0].length; idx++)
		{
			if ((game[0][idx].getState() == game[1][idx].getState()) && (game[0][idx].getState() == game[2][idx].getState()) && (game[0][idx].getState() != -1))
			{	win = true;
				if (play == 1)
					System.out.println("Player 2 wins!");
				else
					System.out.println("Player 1 wins!");

				printBoard();
			}
		}
		if ((game[0][0].getState() == game[1][1].getState()) && (game[0][0].getState() == game[2][2].getState()) && (game[0][0].getState() != -1))
			{
			win = true;
			if (play == 1)
				System.out.println("Player 2 wins!");
			else
				System.out.println("Player 1 wins!");

			printBoard();
			}
		if ((game[0][2].getState() == game[1][1].getState()) && (game[0][2].getState() == game[2][0].getState()) && (game[0][2].getState() != -1))
			{
			win = true;
			if (play == 1)
				System.out.println("Player 2 wins!");
			else
				System.out.println("Player 1 wins!");

			printBoard();
			}
	}
	
	public static void buildBoard()  //this contructs the board matrix
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
	
	public static void printBoard()  //this prints the board on the screen
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
		System.out.print("   1  ");
		System.out.print(" 2  ");
		System.out.println(" 3  ");
		System.out.print(" 1 ");
		System.out.print(order2[0]);
		System.out.print(" | ");
		System.out.print(order2[1]);
		System.out.print(" | ");
		System.out.println(order2[2]);
		System.out.println("   ---------");
		System.out.print(" 2 ");
		System.out.print(order2[3]);
		System.out.print(" | ");
		System.out.print(order2[4]);
		System.out.print(" | ");
		System.out.println(order2[5]);
		System.out.println("   ---------");
		System.out.print(" 3 ");
		System.out.print(order2[6]);
		System.out.print(" | ");
		System.out.print(order2[7]);
		System.out.print(" | ");
		System.out.println(order2[8]);
	}
	
	
}
