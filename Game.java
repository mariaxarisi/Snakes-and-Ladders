import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//The class Game represents the game itself. The game is divided into rounds.

public class Game {

	int round;
	
	//The 'empty' constructor of the class simply creates a game by setting its variables equal to 0.
	Game(){
		
		round =0;
	}
	
	//This constructor creates a game by setting its variables equal to the arguments we provide.
	Game(int round){
		
		this.round = round;
	}
	
	//It sets the variable rount equal to the argument we provide.
	void setRound(int round) {
		
		this.round = round;
	}
	
	//It returns variable round.
	int getRound() {
		
		return round;
	}
	
	//It defines the order in which players play.
	ArrayList<Integer> setTurns(ArrayList<Player> players){
		
		Map<Integer, Integer> dices = new HashMap<Integer, Integer>();
		ArrayList<Integer> playersinorder = new ArrayList<Integer>();
		int min, id;
		
		for(int i=0; i<players.size(); i++)
			dices.put(players.get(i).playerId, (int)(Math.random()*5)+1);
		
		do {
			
			min = dices.get(players.get(0).playerId);
			id = 0;
			for(int i=1; i<players.size(); i++) {
				
				if(min > dices.get(players.get(i).playerId)) {
					min = dices.get(players.get(i).playerId);
					id = i;
				}
			}
			
			playersinorder.add(players.get(id).playerId);
			players.remove(id);
			
		}while(players.isEmpty() == false);
			
		return playersinorder;
	}
	
	public static void main(String[] args) {
		
		Game game = new Game(1);
		Board board = new Board(10, 20, 3, 3, 6);
		Player player1 = new Player(1, "Maria", 0, board);
		HeuristicPlayer player2 = new HeuristicPlayer(2, "Machine", 0, board);
		
		board.createBoard();
		board.createElementBoard();
		
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		players.add(player1);
		players.add(player2);
		
		order = game.setTurns(players);
		
		int round = 0;
		int id1 = 1;
		int id2 = 1;
		int[] p1 = new int[4];
		
		if(order.get(0) == player1.playerId) {
			
			System.out.println("Player 1 plays first");
			System.out.println();
			do {
				
				round++;
				game.setRound(round);
				
				p1 = player1.move(id1, (int)(Math.random()*5)+1);
				id1 = p1[0];
				id2 = player2.getNextMove(id2);
				player2.statistics(game.getRound());
				System.out.println();
				
			}while(game.getRound() <= 100 && id1<board.getN()*board.getM() && id2<board.getN()*board.getM());
		}
		else if(order.get(0) == player2.playerId) {
			
			System.out.println("Player 2 plays first");
			System.out.println();
			do {
				
				round++;
				game.setRound(round);
				
				id2 = player2.getNextMove(id2);
				player2.statistics(game.getRound());
				p1 = player1.move(id1, (int)(Math.random()*5)+1);
				id1 = p1[0];
				System.out.println();
				
			}while(game.getRound() <= 100 && id1<board.getN()*board.getM() && id2<board.getN()*board.getM());
		}
		
		if(id1 > board.getN()*board.getM())
			id1 = board.getN()*board.getM();
		if(id2 > board.getN()*board.getM())
			id2 = board.getN()*board.getM();
		
		double final1 = 0.65*id1 + 0.35*player1.getScore();
		double final2 = 0.65*id2 + 0.35*player2.getScore();
		
		System.out.println();
		System.out.println();
		
		System.out.println("At round " + game.getRound() + " player "+ player1.playerId + " is at position " + id1 + " and has score " + player1.getScore() + 
				" , additionally player " + player2.playerId + " is at position " + id2 + " and has score " + player2.getScore()); 
		
		System.out.println();
		
		if(final1==final2){
			
			if(id1==id2)
				System.out.println("The game resulted in a draw!");
			else if(id1>id2)
				System.out.println("Player 1 won!");
			else
				System.out.println("Player 2 won!");	
		}
		else if(final1>final2)
			System.out.println("Player 1 won!");
		else
			System.out.println("Player 2 won!");
	}

}
