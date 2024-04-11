
/*The class Ladder represents the ladders on the board. It has the variable ladderId, which corresponds to 
the "name" of the ladder, topSquareId that determines which square of the board the top end of the ladder 
is located on, bottomSquareId that determines where the bottom end of the ladder is located, and the variable 
broken which indicates whether the ladder is broken or not. */

public class Ladder {

	int ladderId;
	int topSquareId;
	int bottomSquareId;
	boolean broken;
	
	//The 'empty' constructor of the class simply creates a ladder by setting its variables equal to 0 and variable broken equal to 'false'.
	Ladder(){
		
		ladderId = 0;
		topSquareId = 0;
		bottomSquareId = 0;
		broken = false;
	}
	
	//This constructor creates a ladder by setting its variables equal to the arguments we provide.
	Ladder(int ladderId, int topSquareId, int bottomSquareId, boolean broken){
		
		this.ladderId = ladderId;
		this.topSquareId = topSquareId;
		this.bottomSquareId = bottomSquareId;
		this.broken = broken;
	}
	
	//This constructor creates a ladder by setting its variables equal to the variables of the ladder we provided as an argument
	Ladder(Ladder ladder){
		
		this.ladderId = ladder.ladderId;
		this.topSquareId = ladder.topSquareId;
		this.bottomSquareId = ladder.bottomSquareId;
		this.broken = ladder.broken;
	}
	
	//It sets the variable ladderId equal to the argument we provide.
	void setLadderId(int ladderId) {
		
		this.ladderId = ladderId;
	}
	
	//It returns variable ladderId
	int getLadderId() {
		
		return ladderId;
	}
	
	//It sets the variable topSquareId equal to the argument we provide.
	void setTopSquareId(int topSquareId) {
		
		this.topSquareId = topSquareId;
	}
	
	//It returns variable topSquareId
	int getTopSquareId() {
		
		return topSquareId;
	}
	
	//It sets the variable bottomSquareId equal to the argument we provide.
	void setBottomSquareId(int bottomSquareId) {
		
		this.bottomSquareId = bottomSquareId;
	}
	
	//It returns variable bottomSquareId
	int getBottomSquareId() {
		
		return bottomSquareId;
	}
	
	//It sets the variable broken equal to the argument we provide.
	void setBroken(boolean broken) {
		
		this.broken = broken;
	}
	
	//It returns variable broken
	boolean getBroken() {
		
		return broken;
	}
}
