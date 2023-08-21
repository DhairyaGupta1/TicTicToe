package oops_4;

import java.util.Scanner;

public class TicTacToe {
	
	private Player player1, player2;
	private Board board;
	private int numPlayers;
	
	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		t.startGame();							// fine eventhough startGame is not void because we access this via object of TicTacToe
	}
	
	public void startGame() {
		Scanner input = new Scanner(System.in);
		//take player input
		
		player1 = takePlayerInput(++numPlayers);
		if(player1.getSymbol() == ' ') {
			System.out.println("Invalid Symbol, please enter another symbol:");
			player1.setSymbol(input.next().charAt(0));
		}
		player2 = takePlayerInput(++numPlayers);
		if(player2.getSymbol() == ' ') {
			System.out.println("Invalid Symbol, please enter another symbol:");
			player2.setSymbol(input.next().charAt(0));
		}
		
		while(player1.getSymbol() == player2.getSymbol()) {
			System.out.println("Symbol already taken, please enter another symbol:");
			player2.setSymbol(input.next().charAt(0));
		}
		
		//Create board game
		board = new Board(player1.getSymbol(), player2.getSymbol());
		
		//play the game
		boolean player1Turn = true;
		int status = board.INCOMPLETE;
		while(status == board.INCOMPLETE || status == board.INVALID_MOVE) {
			if(player1Turn) {
				System.out.println("Player 1 - " + player1.getName() + "'s turn");
				System.out.println("Enter x:");
				int x = input.nextInt();
				System.out.println("Enter y:");
				int y = input.nextInt();
				
				status = board.move(player1.getSymbol(),x,y);
				
				if(status == Board.INVALID_MOVE) {
					System.out.println("Invalid Move !! Please try again");
					continue;
				}
			}else {
				System.out.println("Player 2 - " + player2.getName() + "'s turn");
				System.out.println("Enter x:");
				int x = input.nextInt();
				System.out.println("Enter y:");
				int y = input.nextInt();
				
				status = board.move(player2.getSymbol(),x,y);
				
				if(status == Board.INVALID_MOVE) {
					System.out.println("Invalid Move !! Please try again");
					continue;
				}
			}

			player1Turn = !player1Turn;
			board.print();
		}
		
		if(status == board.PLAYER1_WINS) {
			System.out.println("Player 1 - " + player1.getName() + " wins !!");
		}else if(status == board.PLAYER2_WINS) {
			System.out.println("Player 2 - " + player2.getName() + " wins !!");
		}else {
			System.out.println("Draw !!");
		}
		
		input.close();
	}
	
	private Player takePlayerInput(int num) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter player " + num + " name:");
		String name = input.nextLine();
		System.out.println("Enter player " + num + " symbol:");
		char symbol = input.next().charAt(0);
		
		Player p = new Player(name, symbol);
		
		return p;
	}
}
