/*The class Snake represents the snakes on the board. It contains the variable snakeId which corresponds 
to the "name" of the snake, the headId which determines which square of the board the snake's head 
is located in and the tailId which determines where the snake's tail is located.*/

public class Snake {

	int snakeId;
	int headId;
	int TailId;
	
	//The 'empty' constructor of the class simply creates a snake by setting its variables equal to 0.
	Snake(){
		
		snakeId = 0;
		headId = 0;
		TailId = 0;
	}
	
	//This constructor creates a snake by setting its variables equal to the arguments we provide.
	Snake(int snakeId, int headId, int TailId){
		
		this.snakeId = snakeId;
		this.headId = headId;
		this.TailId = TailId;
	}
	
	//This constructor creates a snake by setting its variables equal to the variables of the snake we provided as an argument
	Snake(Snake snake){
		
		this.snakeId = snake.snakeId;
		this.headId = snake.headId;
		this.TailId = snake.TailId;
	}
	
	//It sets the variable snakeId equal to the argument we provide.
	void setSnakeId(int snakeId) {
		
		this.snakeId = snakeId;
	}
	
	//It returns variable snakeId.
	int getSnakeId() {
		
		return snakeId;
	}
	
	//It sets the variable headId equal to the argument we provide.
	void setHeadId(int headId) {
		
		this.headId = headId;
	}
	
	//It returns variable headId.
	int getHeadId() {
		
		return headId;
	}
	
	//It sets the variable headId equal to the argument we provide.
	void setTailId(int TailId) {
		
		this.TailId = TailId;
	}
	
	//It returns variable tailId.
	int getTailId() {
		
		return TailId;
	}
}
