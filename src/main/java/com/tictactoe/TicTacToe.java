package com.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class TicTacToe{
    private  char board[]= new char[10];
    private int occupiedBlockCount = 0;
    Scanner inputSc = new Scanner(System.in);
    // Use Case 1
    public  TicTacToe(){
        for(int i = 1; i < 10; i++){
            board[i] = ' ';
        }
    }
    // Use Case 2
    public char takePlayerInput(){
    	Scanner inputSc2 = new Scanner(System.in);
    	
    	String playerInput;
        
        System.out.print("Choose X or O: ");
        if(inputSc2.hasNext())
            playerInput = inputSc2.next();
        else
        	playerInput = "";
        
        if(!playerInput.toUpperCase().equals("O") && !playerInput.toUpperCase().equals("X")){
            System.out.println("Wrong input. Please choose again.");
            char v = this.takePlayerInput();
            return v;
            
        }

        return playerInput.toUpperCase().charAt(0);
    }

    // Use Case 2
    public char takeComputerInput(char playerInput){
        
        if (playerInput=='O'){
            return 'X';    
        }
        else{
            return 'O';
        }
    }
//    
    // Use Case 3
    public void showBoard(){
        System.out.println("Showing TicTacToe Board\n");
        for(int i = 1; i < 10; i++){
            if (i == 4 || i == 7){
                System.out.println("|\n");
            }
            System.out.print( "| " + board[i] + " ");
        }
        System.out.print( "|\n\n");

    }


    // Use Case 4
    public int checkMoveLocation(){
        try{
           System.out.print("\nPlease enter desired location from 1 to 9: ");
           int locationIndex = inputSc.nextInt();
           
           try{
                if(board[locationIndex] == ' ')
                    return locationIndex;
                else
                    return 0;
               }
            catch(ArrayIndexOutOfBoundsException e){
                 System.out.print("\nLocation Out Of Range ");
                return this.checkMoveLocation();
              }
          }
        catch(InputMismatchException e){
            System.out.print("\nInput Missmatch ");
            return this.checkMoveLocation();
        }
    }

    // Use Case 5
    public void move(char userInput, int indexLocation){
        board[indexLocation]=userInput;
        this.occupiedBlockCount++;
    }
    
    public void playerMove(char playerInput) {
    	int locationIndex = this.checkMoveLocation();
        System.out.println("Location Availble at index number " + locationIndex );
        if(locationIndex != 0){
            this.move(playerInput, locationIndex);
        }
        else{
            System.out.println("Field if occupied! Choose different location.");
            playerMove(playerInput);
        }
    }
    
   //UC-6
    public String PlayingToss() {
    	Random rnd = new Random();
    	int status = rnd.nextInt(2);
    	if (status == 1)
    		return "player";
    	else
    		return "computer";
    }
    
  //UC-7
    public String winOrTie() {
    	String match="";
        for(int i = 1; i <= 8; i++) {
        	switch(i) {
		        	case 1:match = String.valueOf(board[1]) + board[2] + board[3];break;
		        	case 2:match = String.valueOf(board[4]) + board[5] + board[6];break;
		        	case 3:match = String.valueOf(board[7]) + board[8] + board[9];break;
		        	case 4:match = String.valueOf(board[1]) + board[4] + board[7];break;
		        	case 5:match = String.valueOf(board[2]) + board[5] + board[8];break;
		        	case 6:match = String.valueOf(board[3]) + board[6] + board[9];break;
		        	case 7:match = String.valueOf(board[1]) + board[5] + board[9];break;
		        	case 8:match = String.valueOf(board[3]) + board[5] + board[7];break;  	
        			}
        	if(match.equals("XXX")) {
        		return "X";
        	}
        	else if(match.equals("OOO")) {
        		return "O";
        	}
        }
        if(occupiedBlockCount>=9) {
        	return "Tie";
        }
        else {
        	return "notDoneYet";
        }
        	
        	
        
    }
    
  // UC-8 
    public boolean computerCheck(char computerInput) {
    	ArrayList<Integer> vacantIndex = new ArrayList<>();
    	for(int i = 1; i < 10; i++) {
    		if(board[i] == ' ') {
    			vacantIndex.add(i);
    		}
    	}
    	HashMap<Integer, String> pairMap = new HashMap<>();
    	pairMap.put(1,"2,3 4,7 5,9");
    	pairMap.put(2,"1,3 5,8");
    	pairMap.put(3,"1,2 6,9 5,7");
    	pairMap.put(4,"1,7 5,6");
    	pairMap.put(5,"1,9 4,6 3,7");
    	pairMap.put(6,"3,9 4,5");
    	pairMap.put(7,"1,4 8,9 3,5");
    	pairMap.put(8,"7,9 2,5");
    	pairMap.put(9,"3,6 7,8 1,5");

    	for(Integer keyIndex : vacantIndex) {
    		String pairValue[] = pairMap.get(keyIndex).split(" ");
    		for(String pair : pairValue) {
    			String index[] = pair.split(",");
    			int loc1 = Integer.parseInt(index[0]);
    			int loc2 = Integer.parseInt(index[1]);
    			if(board[loc1] == board[loc2] && board[loc1] == computerInput) {
					  board[keyIndex] = computerInput;
					  return true;
				  }
    		}
    		
    	}
    	return false;    	
    	}
    
    
    public boolean blockPlayerWinnig(char computerInput, char playerInput) {
    	    	ArrayList<Integer> vacantIndex = new ArrayList<>();
    	    	for(int i = 1; i < 10; i++) {
    	    		if(board[i] == ' ') {
    	    			vacantIndex.add(i);
    	    		}
    	    	}
    	    	HashMap<Integer, String> pairMap = new HashMap<>();
    	    	pairMap.put(1,"2,3 4,7 5,9");
    	    	pairMap.put(2,"1,3 5,8");
    	    	pairMap.put(3,"1,2 6,9 5,7");
    	    	pairMap.put(4,"1,7 5,6");
    	    	pairMap.put(5,"1,9 4,6 3,7");
    	    	pairMap.put(6,"3,9 4,5");
    	    	pairMap.put(7,"1,4 8,9 3,5");
    	    	pairMap.put(8,"7,9 2,5");
    	    	pairMap.put(9,"3,6 7,8 1,5");

    	    	for(Integer keyIndex : vacantIndex) {
    	    		String pairValue[] = pairMap.get(keyIndex).split(" ");
    	    		for(String pair : pairValue) {
    	    			String index[] = pair.split(",");
    	    			int loc1 = Integer.parseInt(index[0]);
    	    			int loc2 = Integer.parseInt(index[1]);
    	    			if(board[loc1] == board[loc2] && board[loc1] == playerInput) {
    						  board[keyIndex] = computerInput;
    						  return true;
    					  }
    	    		}
    	    		
    	    	}
    	    	return false;    	
    	   }
  
    public boolean takeCorner(char computerInput) {
    	int cornerLocations[] = {1,3,7,9};
    	for(int location : cornerLocations) {
    		if(board[location] == ' ') {
    			board[location] = computerInput;
    			return true;
    		}
    	}
    	return false;
    }

    public boolean takeCenterOrAnyOtherPosition(char computerInput) {
    	int centerPos = 5;
    	int sidePos[] = {2,4,6,8};
    	if(board[centerPos] == ' ') {
    		board[centerPos] = computerInput;
    		return true;
    	}
    	else {
    		for(int side : sidePos) {
    			if(board[side] == ' ') {
    	    		board[side] = computerInput;
    	    		return true;
    	    	}
    		}
    		return false;
    	}
    }
    public static void main(String []args){
        TicTacToe ticTacToeObj = new TicTacToe();
        System.out.println(ticTacToeObj.PlayingToss());
        while(true) {
        	ticTacToeObj.showBoard();

            char playerInput = ticTacToeObj.takePlayerInput();
            char computerInput = ticTacToeObj.takeComputerInput(playerInput);
            System.out.println("Player Input " + playerInput);
            System.out.println("Compute Input " + computerInput);
            ticTacToeObj.playerMove(playerInput);
            boolean computerWinnigStatus = ticTacToeObj.computerCheck(computerInput);
            boolean blockStatus = false;
            if(!computerWinnigStatus) {
                    blockStatus = ticTacToeObj.blockPlayerWinnig(computerInput, playerInput);
            }
//            String data = ticTacToeObj.winOrTie();
            System.out.println(blockStatus);
        	
        }
        
    }
    
    
    
    
}