import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList; 
/*
 @Author: Edson ZAndamela
 @Date: March 27th 2019
 @Version: 1.0
*/
public class titac{
	static char[] boardArr = new char[9];		//Board Array-stores input
	static int[] emptySpots = new int[9];
	static int[] movesArr = new int[9];			//stores all moves
	static int[] scoresArr = new int[9];			//stores all moves
	static Scanner Sc = new Scanner(System.in);	//Instantiates scanner
	static boolean moveTracker = false;			//Keeps track of player's move
	static boolean moveTracker1 = false;			//Keeps track of Computer's move
	static boolean arraStats = false;			//Keeps track of Array status
	static char compMove = 'O';
	static char aiPlayer = 'O';				//AI player for bestMOve test
	static char humanPlayer = 'X';			//Human player for bestMOve test
	static char emptyCell = '-';
	static char testPlayer = 'W';
	static int PcPc = 0;
	/*
	@param: Current state of boardArr
	@ret: Prints current state of boardArray
	*/
	public static void PrintState(char[] boardArr){
		int n = boardArr.length;
		char[] part1 = Arrays.copyOfRange(boardArr, 0, (n + 1)/3);
		char[] part2 = Arrays.copyOfRange(boardArr, (n - 6), n -3);
		char[] part3 = Arrays.copyOfRange(boardArr, (n - 3), n);
		System.out.print("Current State is: ");
		System.out.println();
		System.out.println("\t" + Arrays.toString(part1));
		System.out.println("\t" + Arrays.toString(part2));
		System.out.println("\t" + Arrays.toString(part3));
		System.out.println();
	}
	/*
	@ret: Returns true of move is valid and false otherwise
	@param: loc, location to store move
	@param: Arr[], board
	*/
	public static boolean moveChecker(int loc, char boardArr[]){
			if(loc>8){
			        moveTracker=false; 
			}
			else if(loc<0){
			        moveTracker=false;
			}
			else if(boardArr[loc] == '-'){
				moveTracker = true;
				boardArr[loc] = humanPlayer;	//store X in 
			}
			else{
				moveTracker = false;
			}	
		return moveTracker;
	}
	/*
	@ret: Returns true if move is valid and false otherwise
	@param: loc, location to store move
	@param: Arr[], board
	*/
	public static boolean moveCheckerPC(int bestmove, char boardArr[]){
		for(int i = 0; i < boardArr.length; i++){
			if((i == bestmove) && boardArr[i] == '-'){
				moveTracker1 = true;
				boardArr[bestmove] = aiPlayer;	//store X in 
			}else{
				moveTracker1 = false;
			}	
		}
		return moveTracker1;
	}
	/*
	@return: true if array/board is full
	@param: Arr, takes boardArray
	*/
	public static boolean arrayStatus(char boardArr[]){
		for(int j = 0; j < boardArr.length; j++){
			if(boardArr[j] != '-'){	//if there's no space
				arraStats = true;	//Array is fulll
			}else{
				arraStats = false;	//Otherwise Arr is not full
				break;
			}
		}
			return arraStats;		//return status
	}
	/*
	@return: Returns true if Player Wins
	@param: Takes boardArr
	*/
	public static boolean resultChecker(char boardArr[]){
		boolean result = false;
		for(int k = 0; k < boardArr.length; k++){
			if(((boardArr[0]) == 'X') && ((boardArr[1]) == 'X') && ((boardArr[2]) == 'X')){
				result = true;
			}else if(((boardArr[3]) == 'X') && ((boardArr[4]) == 'X') && ((boardArr[5]) == 'X')){
				result = true;
			}else if(((boardArr[6]) == 'X') && ((boardArr[7]) == 'X') && ((boardArr[8]) == 'X')){
				result = true;
			}else if(((boardArr[0]) == 'X') && ((boardArr[3]) == 'X') && ((boardArr[6]) == 'X')){
				result = true;
			}else if(((boardArr[1]) == 'X') && ((boardArr[4]) == 'X') && ((boardArr[7]) == 'X')){
				result = true;
			}else if(((boardArr[2]) == 'X') && ((boardArr[5]) == 'X') && ((boardArr[8]) == 'X')){
				result = true;
			}else if(((boardArr[0]) == 'X') && ((boardArr[4]) == 'X') && ((boardArr[8]) == 'X')){
				result = true;
			}else if(((boardArr[2]) == 'X') && ((boardArr[4]) == 'X') && ((boardArr[6]) == 'X')){
				result = true;
			}
		}
			return result;
	}
	/*
	@return: Returns true if Computer Wins
	@param: Takes boardArr
	*/
	public static boolean resultCheckerComp(char boardArr[]){
		boolean result = false;
		for(int k = 0; k < boardArr.length; k++){
			if(((boardArr[0]) == 'O') && ((boardArr[1]) == 'O') && ((boardArr[2]) == 'O')){
				result = true;
			}else if(((boardArr[3]) == 'O') && ((boardArr[4]) == 'O') && ((boardArr[5]) == 'O')){
				result = true;
			}else if(((boardArr[6]) == 'O') && ((boardArr[7]) == 'O') && ((boardArr[8]) == 'O')){
				result = true;
			}else if(((boardArr[0]) == 'O') && ((boardArr[3]) == 'O') && ((boardArr[6]) == 'O')){
				result = true;
			}else if(((boardArr[1]) == 'O') && ((boardArr[4]) == 'O') && ((boardArr[7]) == 'O')){
				result = true;
			}else if(((boardArr[2]) == 'O') && ((boardArr[5]) == 'O') && ((boardArr[8]) == 'O')){
				result = true;
			}else if(((boardArr[0]) == 'O') && ((boardArr[4]) == 'O') && ((boardArr[8]) == 'O')){
				result = true;
			}else if(((boardArr[2]) == 'O') && ((boardArr[4]) == 'O') && ((boardArr[6]) == 'O')){
				result = true;
			}
		}
			return result;
	}
	/*
	Determines the score of a human move
	@ret: Returns the location of human successfull move
	@ret: Returns the score of a human successfull move
	@param: boardArr, Takes the boardArr
	@param: humanPlayer, Takes the player
	*/
	public static int[]getHumanMoveScore(char boardArr[], int loc, char somePlayer){
		int humanLocMove = 0;
		int humanScrMove = 0;
		for(int i = 0; i < boardArr.length; i++){
			if((i == loc) && boardArr[i] == '-'){
				boardArr[loc] = somePlayer;
				humanLocMove = loc+1;
				humanScrMove = 10;
				return new int[] {humanLocMove, humanScrMove};
			}else{
				humanLocMove = 1000;
				humanScrMove = 0;
			}	
		}
		return new int[] {humanLocMove, humanScrMove};
	}
	/*
	Determines the score of a computer move
	@ret: Returns the location of computer successfull move
	@ret: Returns the score of a computer successfull move
	@param: boardArr, Takes the boardArr
	@param: humanPlayer, Takes the player
	*/
	public static int[]getAiMoveScore(char boardArr[], int loc, char somePlayer){
		int aiLocMove = 0;
		int aiScrMove = 0;
		for(int i = 0; i < boardArr.length; i++){
			if((i == loc) && boardArr[i] == '-'){
				boardArr[loc] = somePlayer;
				aiLocMove  = loc+1;
				aiScrMove = -10;
				return new int[] {aiLocMove, aiScrMove};
			}else{
				aiLocMove = -1000;
				aiScrMove = 0;
			}	
		}
		return new int[] {aiLocMove, aiScrMove};
	}
	/*
	Makes move & updates board
	@ret: Returns board with new move
	@param: boardArr, Takes the boardArr
	@param: player, Takes the player
	*/
	public static char[] Move(char boardArr[], char player, int index){
		char[] newBoardArr = boardArr.clone();
		newBoardArr[index] = player;
		return newBoardArr;
}
	/*
	Finds possible moves
	@ret: Returns array of poassible moves
	@param: boardArr, Takes the boardArr
	*/
	public static Integer[] getPossibleMoves(char boardArr[]){
		List<Integer> mylist = new LinkedList<Integer>();
		for(int i = 0; i < boardArr.length; i++){
				if(boardArr[i] == ('-')){
					mylist.add(i);
			}
	}
	Integer[] pMoves = new Integer[mylist.size()];
	mylist.toArray(pMoves);
	return pMoves;
}
	/*
	Computes MiniMax
	@ret: Returns integer - Best Min or Best Max
	@param: boardArr, Takes the boardArr
	*/
	public static Integer miniMax(char boardArr[], char player){
		int score = 0;								//Keeps track of scocre
		boolean human = resultChecker(boardArr);	//Ret true if human or 'X' wins
		boolean comp = resultCheckerComp(boardArr);	//Ret true if computer or 'O' wins
		if(human == true){							//if humanPlayer wins
			score = 10;
			return score;							//set score
		}else if(comp == true){						//if computer wins
			score  = -10;
			return score;							//Set score	
		}else if(getPossibleMoves(boardArr).length == 0){	//if computer wins
			score  = 0;
			return score;
		}
		Integer myMiniMax = null;
		for(Integer index : getPossibleMoves(boardArr)){
			boardArr = Move(boardArr, player, index);
			Integer result = miniMax(boardArr, player);
			if(myMiniMax == null || player == (humanPlayer) && myMiniMax < result || player == (aiPlayer) && result < myMiniMax){
				myMiniMax = result;
			} 
		}
		return myMiniMax + (player == (humanPlayer) ? -1 : 1);
}
/*
Computes best move for computer
@ret: Returns
@param:
*/
public static int getBestMove(char boardArr[], char player){
	Integer myMiniMax = null;
	int bestMove = -1000;
	for(Integer index : getPossibleMoves(boardArr)){
		boardArr = Move(boardArr, player, index);
		Integer result = miniMax(boardArr, player);
		if(myMiniMax == null || player == (humanPlayer) && myMiniMax < result || player == (aiPlayer) && result < myMiniMax){
				myMiniMax = result;
				bestMove = index;
			}
	}
	return bestMove;
}
	public static void main(String[] args){
		char[] boardArr = new char[] {'-', '-', '-', '-', '-', '-', '-', '-', '-'};
		System.out.println("\t" + "Welcome to Tic Tac Toe!");	//Welcome statement		
		PrintState(boardArr);									//Prints initial state of boardArray
		//Computer + player moves	
		while((!resultChecker(boardArr)) && (!arrayStatus(boardArr)) && (!resultCheckerComp(boardArr))){	//while we dont have a winner/board !=full
			System.out.println("\t" + "Please, make your move in row major order");
			int loc = Sc.nextInt();			//scans for move location
			loc = loc -1;					//Adjusts location issue 1-9
		    boolean teste = moveChecker(loc, boardArr);
		    //teste=true; 
		    if(teste == false){
		    	System.out.println("Error!!! You can't make that move OR Slot is taken, please try another!");
		    	continue;
		    }		//Checks and makes player's move if possible
		    int aiMove = getBestMove(boardArr, aiPlayer);	//calls computer's best move
		    moveCheckerPC(aiMove, boardArr);		//Checks and makes computer's move if possible
		    PrintState(boardArr);					//Prints the state of board 
		}
		if((resultChecker(boardArr)) == true){
			System.out.println("\t" + "Congratulations!");	//declares player winner	
			System.out.println("\t" + "You Won!!!");	//Declares computer winner	
			System.out.println();
		}else if((resultCheckerComp(boardArr)) == true){
			System.out.println("\t" + "You Lose!!!");	//declares Computer winner	
			System.out.println("\t" + "See you next time!");	//Declares computer winner
			System.out.println();	
		}else{
			System.out.println("\t" + "Tie!!!");	//Declares Tie
			System.out.println("\t" + "You're such a good Player!");	//Declares Tie
			System.out.println();
		}
	}
}

