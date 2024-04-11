
/* The class Player represents a player in the game. Players are identified by their playerId, 
have a name (name), a score (score), and play on a specific game board (board). */

public class Player {

	int playerId;
	String name;
	int score;
	Board board;
	
	//The 'empty' constructor of the class simply creates a player by setting its variables equal to 0.
	Player(){
		
		playerId = 0;
		name = "";
		score = 0;
		board = null;
	}
	
	//This constructor creates a player by setting its variables equal to the arguments we provide.
	Player(int playerId, String name, int score, Board board){
		
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
	}
	
	//It sets the variable playerId equal to the argument we provide.
	void setPlayerId(int playerId) {
		
		this.playerId = playerId;
	}
	
	//It returns variable playerId
	int getPlayerId() {
		
		return playerId;
	}
	
	//It sets the variable name equal to the argument we provide.
	void setName(String name) {
		
		this.name = name;
	}
	
	//It returns variable name
	String getNmae() {
		
		return name;
	}
	
	//It sets the variable score equal to the argument we provide.
	void setScore(int score) {
		
		this.score = score;
	}
	
	//It returns variable score
	int getScore() {
		
		return score;
	}
	
	//It sets the variable board equal to the argument we provide.
	void setBoard(Board board) {
		
		this.board = board;
	}
	
	//It returns variable board
	Board getBoard() {
		
		return board;
	}
	
	//A method that implements the movement of the player.
	int[] move(int id, int die) {
		 
		/* At the array sum we store first the position of the player after the movement, then the 
		 * number of snakes from which he was bitten, the number of ladders he went up and all the presents he got.*/
		int[] sum = {0, 0, 0, 0};
		int[] temp = {0, 0, 0, 0};
		id += die;
		 
		//presents
		for(int i=0; i<board.presents.length; i++) {
			 
			 if(id == board.presents[i].presentSquareId) {
				 
				 System.out.println("Player " + playerId + " gain a present");
				 score += board.presents[i].points;//counts the points of the present
				 board.presents[i].points = 0;//sets the points of the present equal to zero
				 sum[3]++;//stores the number of presents player received
				 break;
			 }
		 }
 
		//snakes
		for(int i=0; i<board.snakes.length; i++) {
			 
			 if(id == board.snakes[i].headId) {
				 
				 System.out.println("Player " + playerId + " got stung by a snake");
				 id = board.snakes[i].TailId;//moves the player at the tail of the snake
				 sum[1]++;//stores the number of times player got bitten by a snake
				 temp = move(id, 0);
				 for(int j=0; j<4; j++)
					 sum[j] += temp[j];
				 break;
			 }
		 }
		 
		//ladders
		for(int i=0; i<board.ladders.length; i++) {
			 
			 if(id == board.ladders[i].bottomSquareId && board.ladders[i].broken==false) {
				 
				 System.out.println("Player " + playerId + " went up a stair");
				 id = board.ladders[i].topSquareId;//moves the player at the top end of the ladder
				 board.ladders[i].broken = true;//breaks the ladder
				 sum[2]++;//stores the number of ladders player went up
				 temp = move(id, 0);
				 for(int j=0; j<4; j++)
					 sum[j] += temp[j];
				 break;
			 }
		 }
		 sum[0] = id;
		 return sum;
	 }
	
	/*Method plainMove is the same method as move, but it doesn't print anything at the screen.
	 * It is useful for the class Heuristic player*/
	int[] plainmove(int id, int die) {
		 
		int[] sum = {0, 0, 0, 0};
		int[] temp = {0, 0, 0, 0};
		id += die;
		 
		 for(int i=0; i<board.presents.length; i++) {
			 
			 if(id == board.presents[i].presentSquareId) {
				
				 score += board.presents[i].points;
				 board.presents[i].points = 0;
				 sum[3]++;
				 break;
			 }
		 }
 
		 for(int i=0; i<board.snakes.length; i++) {
			 
			 if(id == board.snakes[i].headId) {

				 id = board.snakes[i].TailId;
				 sum[1]++;
				 temp = plainmove(id, 0);
				 for(int j=0; j<4; j++)
					 sum[j] += temp[j];
				 break;
			 }
		 }
		 
		 for(int i=0; i<board.ladders.length; i++) {
			 
			 if(id == board.ladders[i].bottomSquareId && board.ladders[i].broken==false) {
				 
				 id = board.ladders[i].topSquareId;
				 board.ladders[i].broken = true;
				 sum[2]++;
				 temp = plainmove(id, 0);
				 for(int j=0; j<4; j++)
					 sum[j] += temp[j];
				 break;
			 }
		 }
		 sum[0] = id;
		 return sum;
	 }
}
