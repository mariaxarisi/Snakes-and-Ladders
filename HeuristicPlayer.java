import java.util.ArrayList;
import java.util.HashMap;

/* The class HeuristicPlayer implements an "intelligent" player who has the ability to set the dice he rolls, 
 * evaluates his moves, and executes the move that benefits him the most (inherits the Player class).*/

public class HeuristicPlayer extends Player{

	ArrayList<Integer[]> path;
	
	//The 'empty' constructor of the class simply creates a HeuristicPlayer by setting its variables equal to 0.
	HeuristicPlayer(){
		
		super();
		path = new ArrayList<Integer[]>();
	}
	
	//This constructor creates a HeuristicPlayer by setting its variables equal to the arguments we provide.
	HeuristicPlayer(int playerId, String name, int score, Board board){
		
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
		path = new ArrayList<Integer[]>();
	}
	
	//Evaluates the move of heuristicPlayer without changing the board.
	double evaluate(int currentPos, int dice) {
		
		int[] end = {0,0,0,0};
		boolean[] l = new boolean[board.ladders.length];//saves the state of the ladders before executing the method.
		int[] p = new int[board.presents.length];//σsaves the state of the present's points before executing the method
		int steps, temp1, temp2;
		double evaluate;
		
		for(int i=0; i<board.ladders.length; i++)
			l[i] = board.ladders[i].broken;
		for(int i=0; i<board.presents.length; i++)
			p[i] = board.presents[i].points;
		
		temp1 = score;
		end = plainmove(currentPos, dice);
		steps = end[0] - currentPos;
		temp2 = score;
		temp2 -= temp1;
		evaluate = steps * 0.65 + temp2 * 0.35; //evaluation method
		
		/*The score remains the same. It is just an evaluation! Additionally, we restore the state of the
		 ladders and the points of the presents that may have been used*/
		score = temp1;
		for(int i=0; i<l.length; i++)
			board.ladders[i].broken = l[i];
		for(int i=0; i<p.length; i++)
			board.presents[i].points = p[i];
		
		return evaluate;
	}
	
	//A method that implements the move of HeuristicPlayer.
	int getNextMove(int currentPos) {
		 
		 HashMap<Integer, Double> evaluation = new  HashMap<Integer, Double>();//Associates each roll with the right evaluation
		 int max, temp;
		 int[] end = {0,0,0,0};
		 Integer[] p = new Integer[6];
		 
		 for(int i=1; i<7; i++)
			 evaluation.put(i, evaluate(currentPos, i));
		 
		 max = 1;
		 for(int i=2; i<7; i++) {
			 
			 if(evaluation.get(i) > evaluation.get(max))
				 max = i;
		 }
		 
		 temp = score;
		 end = plainmove(currentPos, max);
		 
		 p[0] = max;//stores to path the perfect roll
		 p[1] = score - temp;//stores to path all the gained points
		 p[2] = Math.abs(end[0] - currentPos);//stores to path the number of steps
		 p[3] = end[3];//stores to path the number of presents player recieved
		 p[4] = end[1];//stores to path the number of times player got bitten by a snake
		 p[5] = end[2];//stores to path the number of ladders player went up
		 path.add(p);
		 
		 return end[0];
	 }
	 
	//Prints on the screen what happened in the HeuristicPlayer during the given round, as well as everything that has occurred up to that moment
	void statistics(int round) {
		 
		 Integer[] p = new Integer[6];
		 int snakes=0, ladders=0, presents=0;
		 
		 for(int i=0; i<path.size(); i++) {
			 
			 p = path.get(i);
			 snakes += p[4];
			 ladders += p[5];
			 presents += p[3];
			 if(i+1 == round)
				 System.out.println("Player " + playerId + " at round " + round + " set the dice equal to " + p[0] + ", got stung by "
						 + p[4] + " snakes, went up " + p[5] + " ladders and gained " + p[3] + " presents.");
		 }
		 
		 System.out.println("Player " + playerId + " between rounds 1 and " + round + " got stung by " + snakes + " snakes, went up " + ladders + 
				 " ladders and gained " + presents + " presents.");
	 }
}
