package applictions;
import java.util.Random;
import java.util.Scanner; 

class TicTacToe
{
	static char[][] board;

	public TicTacToe()
	{
		board =	new char[3][3];
		initBoard();
	}

	void initBoard() {
		for(int i =0  ; i<board.length; i++) {
			for(int j =0 ; j < board[i].length;j++) {
				board[i][j]= ' ';
			}

		}
	}

	static	void dispBoard() {
		System.out.println("--------------");

		for(int i =0;i< board.length; i++) {
			System.out.print(" | ");
			for(int j =0 ; j < board[i].length;j++) {
				System.out.print(board[i][j]+ " | ");
			}
			System.out.println();
			System.out.println("--------------");

		}
	}

	static void placeMark(int row ,int col , char mark) {
		if(row >=0 && row<=2 && col>=0 && col <=2) {
			board[row][col] = mark;
		}
		else {
			System.out.println("Invalid Input");
		}
	}

	static boolean checkcolWin() {

		for(int j =0 ; j<=2; j++) {
			if(board[0][j]!= ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j] ) {
				return true;
			}
		}
		return false;
	}

	static boolean checkrowWin() {

		for(int i =0 ; i<=2; i++) {
			if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2] ) {
				return true;
			}
		}
		return false;
	}


	static boolean checkdigwin() {
		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || 
				board[0][2] == board[1][1] && board[1][1] == board[2][0]  ) {
			return true;
		}

		return false;
	}
	
	static boolean checkDraw() {
		for(int i = 0 ; i<=2 ; i++) {
			for(int j =0 ; j<=2 ; j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}


}

abstract class Player
{
	String name ;
	char mark;
	abstract void makingMove();
	
	 boolean isValidMark(int row , int col)
		{

			if(row >=0 && row <=2 &&
					col >= 0 && col <= 2 ) 
			{
				if(TicTacToe.board[row][col] == ' ')
				{
					return true;
				}
			}
			return false;
		}
	
}

class humanPlayer extends Player
{
	

	humanPlayer(String name , char mark)
	{
		this.name = name;
		this.mark = mark;
	}

	void makingMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do 
		{
			System.out.println("Enter the row and colu");
			row = scan.nextInt();
			col = scan.nextInt();
		} while (!isValidMark(row, col));


        TicTacToe.placeMark(row, col, mark);
    
	} 
	
}

class AIPlayer extends Player
{
	AIPlayer(String name , char mark)
	{
		this.name = name;
		this.mark = mark;
	}

	void makingMove() {
		Scanner scan = new Scanner(System.in);
		int row ;
		int col;
		do 
		{
	      Random r	= new Random();
	       row = r.nextInt(3);
	       col = r.nextInt(3);
	      
		} while (!isValidMark(row, col));


        TicTacToe.placeMark(row, col, mark);
    
	} 
}


public class Langing {

	public static void main(String[] args) {

		TicTacToe t1 = new TicTacToe();

		humanPlayer h1  = new humanPlayer("King" ,'X');
		 AIPlayer  h2  = new AIPlayer("AI", 'O');

		Player cp = h1 ;  /// this use inhertitance and polymorphism using tight coupling;
		

		while(true) {
			System.out.println(cp.name + "Turn");
			cp.makingMove();
			TicTacToe.dispBoard();
			if(TicTacToe.checkcolWin() || TicTacToe.checkrowWin() || TicTacToe.checkdigwin())
			{
				System.out.println(cp.name + " Has won");
				break;
			}else if(TicTacToe.checkDraw()) {
				System.out.println("Game is a Draw");
				break;
			}
			
			else
			{
				if(cp == h1) {
					cp = h2;
				}else {
					cp = h1;
				}
			}
			

		}

	
	}
	
}