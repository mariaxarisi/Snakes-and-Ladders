
/*The class Board represents the game board. The variables N and M are the dimensions of the board. The array 
* squares represents the squares of the board, the array snakes represents the snakes, the array ladders 
* represents the ladders, and the array presents represents the gifts.*/

public class Board {

	int N, M;
	int[][] squares;
	Snake[] snakes;
	Ladder[] ladders;
	Present[] presents;
	
	//The 'empty' constructor of the class simply creates a board by setting its variables equal to 0 and allocates space for the arrays.
	Board(){
		
		N = 0;
		M = 0;
		squares  = new int[N][M];
		snakes = new Snake[0];
		ladders = new Ladder[0];
		presents = new Present[0];
	}
	
	//This constructor creates a board by setting its variables equal to the arguments we provide.
	Board(int N, int M, int snake, int ladder, int present) {
		
		this.N = N;
		this.M = M;
		
		squares  = new int[N][M];
		
		snakes = new Snake[snake];
		for(int i=0; i<snake; i++)
			snakes[i] = new Snake();
		
		ladders = new Ladder[ladder];
		for(int i=0; i<ladder; i++)
			ladders[i] = new Ladder();
		
		presents = new Present[present];
		for(int i=0; i<present; i++)
			presents[i] = new Present();
	}
	
	//This constructor creates a board by setting its variables equal to the variables of the board we provided as an argument
	Board(Board board){
		
		this.N = board.N;
		this.M = board.M;
		
		squares  = new int[N][M];
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++)
				squares[i][j] = board.squares[i][j];
			
		}
		
		snakes = new Snake[board.snakes.length];
		for(int i=0; i<board.snakes.length; i++)
			snakes[i] = new Snake(board.snakes[i]);
		
		ladders = new Ladder[board.ladders.length];
		for(int i=0; i<board.ladders.length; i++)
			ladders[i] = new Ladder(board.ladders[i]);
		
		presents = new Present[board.presents.length];
		for(int i=0; i<board.presents.length; i++)
			presents[i] = new Present(board.presents[i]);
	}
	
	//It sets the variable N equal to the argument we provide.
	void setN(int N) {
		 this.N = N;
	}
	
	//It returns variable N.
	int getN() {
		 
		return N;
	}
	
	//It sets the variable M equal to the argument we provide.
	void setM(int M) {
		
		this.M = M;
	}
	
	//It returns variable M.
	int getM() {
		
		return M;
	}
	
	//It initializes the elements of the N*M array 'squares'.
	void setSquares(int N, int M) {
		
		int count=1;
		for(int i= N-1; i>=0; i--) {
			
			if(i%2 == 0) {
				for(int j=M-1; j>=0; j--) {
					
					squares[i][j] = count;
					count++;
				}
			}
			else {
				for(int j=0; j<M; j++) {
					
					squares[i][j] = count;
					count++;
				}
			}
		}
	}
	
	//It returns variable squares
	int[][] getSquares(){
		
		return squares;
	}
	
	//It sets the array snakes eqqual to the array we provide as an input.
	void setSnakes(Snake[] snake) {
		
		for(int i=0; i<snake.length; i++) {
			
			snakes[i].snakeId = snake[i].snakeId;
			snakes[i].headId = snake[i].headId;
			snakes[i].TailId = snake[i].TailId;
		}
	}
	
	//It returns array snakes
	Snake[] getSnakes() {
		
		return snakes;
	}
	
	//It sets array ladders equal to the array we provide as an input
	void setLadders(Ladder[] ladder) {
		
		for(int i=0; i<ladders.length; i++) {
			
			ladders[i].ladderId = ladder[i].ladderId;
			ladders[i].topSquareId = ladder[i].topSquareId;
			ladders[i].bottomSquareId = ladder[i].bottomSquareId;
			ladders[i].broken = ladder[i].broken;
		}
	}
	
	//It returns array ladders
	Ladder[] getLadders() {
		
		return ladders;
	}
	
	//It sets array presents equal to the array we provide as an input
	void setPresents(Present[] present) {
		
		for(int i=0; i<presents.length; i++) {
			
			presents[i].presentId = present[i].presentId;
			presents[i].presentSquareId = present[i].presentSquareId;
			presents[i].points = present[i].points;
		}
	}
	
	//It returns array presents
	Present[] getPresents() {
		
		return presents;
	}
	
	//It creates the board by randomly placing the necessary number of snakes, ladders, and gifts on it
	void createBoard() {
		
		//creates the squares of the board
		int count=1;
		for(int i= N-1; i>=0; i--) {
			
			if(i%2 == 0) {
				for(int j=M-1; j>=0; j--) {
					
					squares[i][j] = count;
					count++;
				}
			}
			else {
				for(int j=0; j<M; j++) {
					
					squares[i][j] = count;
					count++;
				}
			}
		}
		
		for(int i=0; i<snakes.length; i++) {
			
			snakes[i].snakeId = i;
			snakes[i].TailId = (int)(Math.random()*(N*M-M-1)) + 1;//we can't place the tail of the snake at the last row of the board
			boolean F;//checks if two heads of separate snakes coincide
			do {
				
				F = false;
				//The head goes above the tail
				snakes[i].headId = (int)(Math.random()*(N*M-1-snakes[i].TailId-1))+snakes[i].TailId+1;
				
				//If the head coincides with another already existing head, the process repeats.
				for(int j=0; j<snakes[i].getSnakeId(); j++) {
					
					if(snakes[i].headId == snakes[j].getHeadId()) {
						
						F = true;
						break;
					}
				}
			}while(F);
		}
		
		for(int i=0; i<ladders.length; i++) {
			
			ladders[i].ladderId = i;
			boolean F, L;
			do {
				
				F = false;//checks if snake heads coincide with the bottom part of the ladders
				L = false;//checks if two bottom parts of separate ladders coincide
				ladders[i].bottomSquareId = (int)(Math.random()*(N*M-M-1)) + 1;//we can't place the bottom part of a ladder at the last row of the board
				
				//If the bottom part of a ladder coincides with a snake head, the process repeats
				for(int j=0; j<snakes.length; j++) {
					
					if(snakes[j].getHeadId() == ladders[i].bottomSquareId) {
						
						F = true;
						break;
					}
				}
				
				//If the bottom part of a ladder coincides with another already existing bottom part, the process repeats.
				for(int k=0; k<ladders[i].getLadderId(); k++) {
					
					if(ladders[i].bottomSquareId == ladders[k].getBottomSquareId()) {
						
						L = true;
						break;
					}
				}
			}while(F || L);
			//the top part of the ladder should be above its bottom part
			ladders[i].topSquareId = (int)(Math.random()*(N*M-1-ladders[i].bottomSquareId-1)) +ladders[i].bottomSquareId+1;
		}
		
		for(int i=0; i<presents.length; i++) {
			
			presents[i].presentId = i;
			presents[i].presentSquareId = (int)(Math.random()*(N*M-1)) + 1;
			presents[i].points = (int)(Math.random()*50);//gives random points to the presents(up to 50)
		}
	}
	
	//It creates and prints 3 tables representing the board with only snakes, ladders, or gifts, and one table containing all.
	void createElementBoard() {
		
		String[][] elementBoardSnakes = new String[N][M];
		String[][] elementBoardLadders = new String[N][M];
		String[][] elementBoardPresents = new String[N][M];
		
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) {
				
				elementBoardSnakes[i][j] = new String();
				elementBoardSnakes[i][j] = "---";
				
				for(int k=0; k<snakes.length; k++) {
					
					if(snakes[k].getHeadId() == squares[i][j]) {
						
						elementBoardSnakes[i][j] = "SH" + snakes[k].snakeId;
						break;
					}
					if(snakes[k].TailId == squares[i][j]) {
						
						elementBoardSnakes[i][j] = "ST" + snakes[k].snakeId;
						break;
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) {
				
				elementBoardLadders[i][j] = new String();
				elementBoardLadders[i][j] = "---";
				
				for(int k=0; k<ladders.length; k++) {
					
					if(ladders[k].broken)
						break;
					else if(ladders[k].topSquareId == squares[i][j]) {
						
						elementBoardLadders[i][j] = "LU" + ladders[k].ladderId;
						break;
					}
					else if(ladders[k].bottomSquareId == squares[i][j]) {
						
						elementBoardLadders[i][j] = "LD" + ladders[k].ladderId;
						break;
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) {
				
				elementBoardPresents[i][j] = new String();
				elementBoardPresents[i][j] = "---";
				
				for(int k=-0; k<presents.length; k++) {
					
					if(presents[k].presentSquareId == squares[i][j]) {
						
						elementBoardPresents[i][j] = "PR" + presents[k].presentId;
						break;
					}
				}
			}
		}
		
		System.out.println("< elementBoardSnakes >");
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardSnakes[i][j] + " ");
			System.out.println();
		}
		System.out.println("< elementBoardLadders >");
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardLadders[i][j] + " ");
			System.out.println("");
		}
		System.out.println("< elementBoardPresents >");
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardPresents[i][j] + " ");
			System.out.println("");
		}
		System.out.println("< elementBoardAll >");
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardPresents[i][j] + elementBoardLadders[i][j] + elementBoardSnakes[i][j] + " ");
			System.out.println("");
		}
	}
}
