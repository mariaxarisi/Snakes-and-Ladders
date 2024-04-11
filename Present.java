
/*The class Present represents the presents on the board. It contains the variable presentId which corresponds to
* the 'name' of the present, the presentSquareId which determines which square of the board the present is located in
* and the points which determines the value of the present. */

public class Present {

	int presentId;
	int presentSquareId;
	int points;
	
	//The 'empty' constructor of the class simply creates a present by setting its variables equal to 0.
	Present(){
		
		presentId = 0;
		presentSquareId = 0;
		points = 0;
	}
	
	//This constructor creates a present by setting its variables equal to the arguments we provide.
	Present(int presentId, int presentSquareId, int points){
		
		this.presentId = presentId;
		this.presentSquareId = presentSquareId;
		this.points = points;
	}
	
	//This constructor creates a present by setting its variables equal to the variables of the present we provided as an argument
	Present(Present present){
		
		this.presentId = present.presentId;
		this.presentSquareId = present.presentId;
		this.points = present.points;
	}
	
	//It sets the variable presentId equal to the argument we provide.
	void setPresentId(int presentId) {
		
		this.presentId = presentId;
	}
	
	//It returns variable presentId.
	int getPresentId() {
		
		return presentId;
	}
	
	//It sets the variable presentSquareId equal to the argument we provide.
	void setPresentSquareId(int presentSquareId) {
		
		this.presentSquareId = presentSquareId;
	}
	
	//It returns variable presentSquareId.
	int getPresentSquareId() {
		
		return presentSquareId;
	}
	
	//It sets the variable points equal to the argument we provide.
	void setPoints(int points) {
		
		this.points = points;
	}
	
	//It returns variable points.
	int getPoints() {
		
		return points;
	}
}
