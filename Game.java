package cs211;
//@author Oluwatooni Adeagbo
//@Student number:19311483


import java.util.*;
public class Game {
public static Scanner sc = new Scanner (System.in);//make scanner public so it can be used throughout methods

	public static void main(String[] args) {//the intro
		System.out.println("Hello and Welcome to Hangman! \n");
		Menu();//calls menu
	}
	public static void Menu() {
		System.out.println("Type 'Start' to begin");
		System.out.println("Type 'Rules' to see the Rulebook");
		String s = sc.nextLine();
		
		while(s.equalsIgnoreCase("Start")==false && s.equalsIgnoreCase("Rules")==false) {//if it doesn't equal start or rules it will continually ask for a different input
			System.out.println("Sorry that command isn't valid.\n");
			s=sc.nextLine();
			
			if(s.equalsIgnoreCase("Start")||s.equalsIgnoreCase("Rules")) {//when it matches it'll break out of the while loop
				break;
			}
			
		}
		
		if(s.equalsIgnoreCase("Start")) {//if it equals start then it goes to mode select
			ModeSelect();
		}
		
		else if (s.equalsIgnoreCase("Rules")) {// if it equals rules then it goes to rules
			Rules();
		}
		
	}
	
	public static void ModeSelect () {//first it'll ask what mode you want
		System.out.println("What difficulty would you like?\n");
		System.out.println("Type 'Easy' for Easy (Short words, 7 lives)\n");
		System.out.println("Type 'Normal' for Normal (Medium words, 5 lives)\n");	
		System.out.println("Type 'Hard' for Hard (Longer words, 3 lives)\n");
		String s=sc.nextLine();
		while(s.equalsIgnoreCase("Easy")==false && s.equalsIgnoreCase("Normal")==false&&s.equalsIgnoreCase("Hard")==false) {//if it doesn't match any of these it asks for input until it does
			System.out.println("Sorry that command isn't valid.\n");
			 s=sc.nextLine();
			 
			if(s.equalsIgnoreCase("Easy")||s.equalsIgnoreCase("Normal")||s.equalsIgnoreCase("Hard")) {//if it does match then it breaks out of the loop
				break;
			}
			
		}
		
		if(s.equalsIgnoreCase("Easy")) {	//and goes to word choose with the mode selected
			WordChoose(s);
		}
		
		else if (s.equalsIgnoreCase("Normal")) {
		
			WordChoose(s);
		}
		
		else if (s.equalsIgnoreCase("Hard")) {		
			WordChoose(s);
		}
		
	}
	
	public static void Rules() {//rules printed out
		System.out.println("Here are the rules:");
		System.out.println("A word is selected at random.\n Depending on the difficulty you picked your word may be long or short.");
		System.out.println("Pick a single letter out of the alphabet.\nIf it is right it will appear in every instance of it in the word.");
		System.out.println("However, If you are wrong a portion of the hangman is added.");
		System.out.println("In Normal and Hard you have 2 hints. Type 'Hint' to use one.");
		System.out.println("If you know the word type 'Guess The Whole Word' to type out the whole word");
		System.out.println("If you get the word wrong, a portion of the hangman is added");
		System.out.println("When the hangman is completed or the word is completed the game is over.");
		System.out.println("Return to Menu?\n Type 'Yes' to go back to the Menu");
		String s =sc.nextLine();
		while(s.equalsIgnoreCase("Yes")==false) {//if not yes, loop
			System.out.println("Sorry that command isn't valid. \n");
			s=sc.nextLine();
			
			if(s.equalsIgnoreCase("Yes")) {
				break;
			}
			
		}
		
		if(s.equalsIgnoreCase("Yes")) {//if yes goes back to menu
			Menu();
		}
	}
	public static void WordChoose(String mode) {//this makes sure the word is only filled with letters
		String l =wordChoosen(mode);//string calls me wordchoosen method to decide its length and the word

		char [] ar =l.toCharArray();// turns string into a character array
		char alphabet [] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int counter = 0;
		boolean easy =true;
		boolean normal =true;
		boolean hard = true;

		for(int i =0;i<ar.length;i++) {//2D array
			counter =0;//resets counter each time it goes through j
			
			for(int j=0;j<26;j++) {
				
				if(ar[i]==alphabet[j]) {
					counter =1;//if it matches a letter from the array alphabet it get the value one
				}
				if(j==25 && counter==0) {
					if(mode.equalsIgnoreCase("Easy")) {
						easy =false;//if it gets through the whole alphabet without matching anything the boolean corresponding with the mode chosen become false
					}
					else if(mode.equalsIgnoreCase("Normal")) {
						normal=false;
					}
					else if(mode.equalsIgnoreCase("Hard")) {
						hard=false;
					}
					
					break;//it then breaks out of the inner for loop
				}

			}
			
			if(counter==0) {//if the counter is 0 it break out of the outer for loop
				break;
			}
		}
		if(easy==false||normal==false ||hard ==false){
			WordChoose(mode);//if the booleans are false it calls the method again(recursion) and gets a different word till it matches the requirements
		}
		else {//if the booleans are true it goes here and the life counter is decided by what mode they are playing
			int death =0;
			if(mode.equalsIgnoreCase("Easy")) {
				 death =7;		
			}
			else if(mode.equalsIgnoreCase("Normal")) {
				 death =5;
			}
			else if(mode.equalsIgnoreCase("Hard")) {
				 death =3;
			}
			GameStart(death, ar,mode,l);//it then calls gamestart to finally start the game bring into it the live counter the array of the word and the string of ther word
		}
		
	}
	public static Random rand = new Random();//public Random so it can be used any where in the code from here and down
	
	public static String wordChoosen(String mode) {
			Dictionary wordy = new Dictionary ();//object made form dictionary
		int ran =rand.nextInt(wordy.getSize());//gets a random number from that is within the range of wordy.getSize()
		String l= wordy.getWord(ran);//String l is the word at the pos of ran
		
		if(mode.equalsIgnoreCase("Easy")) {//if the mode is easy then the length of the word is less than 7.
			
			while(l.length()>7) {//if the string l is bigger than 7 it is run through this while loop until it is smaller than seven
				ran =rand.nextInt(wordy.getSize());
				l= wordy.getWord(ran);
			}
		}
		else if(mode.equalsIgnoreCase("Normal")) {
			
			while(l.length()>12 && l.length()<7) {//in normal if the string is less than seven and greater than 12 it loops until it is with range
				ran =rand.nextInt(wordy.getSize());
				l= wordy.getWord(ran);
			}
		}
		else if(mode.equalsIgnoreCase("Hard")) {
			
			while( l.length()<13) {//in hard if the word is lets than 13 then it loops
				ran =rand.nextInt(wordy.getSize());
				l= wordy.getWord(ran);
			}
		}
		l=l.toLowerCase();//changes it to lower case to make it easier
		l=l.replaceAll("\\s", "");//if there's any space in the word (particularly a space a the start or the end) it deletes it
		return l;//gets new word and returns it
	}
	
	public static void GameStart (int death, char[] chosenword,String mode,String l) {
		int maxDeath =death;//maxDeath is used to decide what switch statement in hang man is used
		char word[] = new char [chosenword.length];//this is the empty array where the right guessed word will be put
		int count=0;
		int hint =2;//hints for normal and hard
		String s ="";
    	int counter =0;
    	String input ="";
    	String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < word.length; i++) {
            word[i] = '_';//fills the array with blanks that will be replaced in with the right inputs

        }
        //had to learn how to use a do while loop for this lolololol
    	do {
    		if(death ==0) {//if the lives run out then it prints out a full hangman  and the full word then breaks out of the do loop
	    		System.out.println(Hangman(maxDeath,death));

    			System.out.println("\nYou Lose!\nThe correct anwser was: \n"+ l);    			
	    		System.out.println();
    			break;
    		}
    		else if ((input.equalsIgnoreCase(l)))//if when they guess the whole word it is true they break out of the do loop
    		{break;}
    		else {//if neither then it'll proceed as normal

	    		System.out.println("Guess the Word!\n");
	    		System.out.println("Type 'Guess the Whole Word' to guess the entire word.");
	    		System.out.println("Type 'Reset' to Reset the game and go to Mode Select");
	    		if(mode.equalsIgnoreCase("Normal")||mode.equalsIgnoreCase("Hard")) {//only prints out if mode is normal or hard
	    			System.out.println("Type 'Hint' for a hint\n Hints left ="+hint);
	    		}
	    		System.out.println("Guessed letters: "+s);//guess letter are put in s
	    		System.out.println();		
	    		System.out.println(Hangman(maxDeath,death));//prints out hangman figures
	    		System.out.println("Lives: "+death);
	    		System.out.println("\n\n");
	            for (int i = 0; i <word.length; i++) {
	                System.out.print(word[i]+" ");
	            }
	            System.out.println();
				input = sc.nextLine();
				//if it meets any of there's conditions it goes into the while loop
				while((input.length()>1)||(input.length() ==1 &&s.contains(input))||!(letters.contains(input))||input.isEmpty()||input.equalsIgnoreCase("Guess the Whole Word")||input.equalsIgnoreCase("Reset")||input.equalsIgnoreCase("Hint")) {
								
					if(input.equalsIgnoreCase("Hint")) {//if hint is typed in as the input then it is goes through here
				
						if ((mode.equalsIgnoreCase("Normal")||mode.equalsIgnoreCase("Hard"))&& input.equalsIgnoreCase("Hint") && hint!=0) {//if it is the mode normal or hard
							hint--;//take a hint away
							int q = rand.nextInt(word.length);//pick a random pos 
							while (!(word[q]=='_')) {//if the word at the pos isnt blank it change it
								q= rand.nextInt(word.length);
							}
							
							char bb = chosenword[q];// gets the character from the original word at the position
							for(int i =0;i<word.length;i++) {
			
								if(chosenword[i]==bb) {//checks the entire string to make see if there's any other positions with the same character
									word[i]=chosenword[i];//if there is then it is filled
									counter++;//counter that sees if the word is finished and right is added to (is used for the while part of the do while loop)
								}
							}
							s+=bb +", ";break;// character is added to string of guessed words
						}
									
						else if((mode.equalsIgnoreCase("Easy")||hint ==0 ) && input.equalsIgnoreCase("Hint")) {//if its in easy or theres no more hints it will print out this
							System.out.println("Sorry you cannot use hints. Please type out a letter instead!\n");									
						}											
					}
					
					else if(input.equalsIgnoreCase("Guess the Whole Word")) {
						System.out.println("Please guess the whole word.\n");
						input= sc.nextLine();
						if(input.equalsIgnoreCase(l)) {//if you guess the word right then it breaks out of the do loop
							System.out.println("Correct!\nYou win!");break;
						}

						else if (input.equalsIgnoreCase("Guess the Whole Word")==false) {//if wrong you it prints of the thing again and asks you for another word or letter
							System.out.println("Wrong anwser");
							death--;
							System.out.println("Guess the Word!\n");
							System.out.println("Type 'Guess the Whole Word' to guess the entire word.");
							System.out.println("Type 'Reset' to Reset the game and go to Mode Select");
							if(mode.equalsIgnoreCase("Normal")||mode.equalsIgnoreCase("Hard")) {
								System.out.println("Type 'Hint' for a hint\n Hints left ="+hint);
							}
							System.out.println("Guessed letters: "+s);//maybe make a char array for guessed words?
							System.out.println();		
							System.out.println(Hangman(maxDeath,death));
							System.out.println("Lives: "+death);
							System.out.println("\n\n");
							for (int i = 0; i <word.length; i++) {
								System.out.print(word[i]+" ");
							}
						}
						
					}
					
					else if(input.equalsIgnoreCase("Reset")) {//goes back to mode select
						ModeSelect();break;
					}
					
					else if((input.length()>1)) {
						System.out.println("Please type in only one character.");
						System.out.println("If you wish to guess the word please type 'Guess the Whole Word'.");
					}
					
					else if (input.length() ==1 &&s.contains(input)){
						System.out.println("Sorry you have already used this letter. Pick another");				
					}
					
					else if (!(letters.contains(input))) {
						System.out.println("Please type letters only.");						
					}
					
					else if (input.isEmpty()) {
						System.out.println("You cannot enter an empty character. Try again.");
					}

					System.out.println();
					input= sc.nextLine();
			
				}
				if(input.equalsIgnoreCase("Hint")||((mode.equalsIgnoreCase("Normal")||mode.equalsIgnoreCase("Hard"))&& input.equalsIgnoreCase("Hint") && hint!=0)||(input.equalsIgnoreCase(l))) {}//doesnt print anything for hints
				else {
					s+=input+", ";
					count =0;
					for (int i = 0; i < word.length; i++) {
						
						if (input.charAt(0) == chosenword[i]) {
							if(count!=1) {
								System.out.println("Correct Anwser!");
							}
							word[i] = input.charAt(0);;
							counter++;
							count =1;
							
						}
						else if(count ==0 && i==word.length-1) {
							death --;
							System.out.println("Wrong Anwser!");
						}
						
					}
				}	
    		}   
    		
				
	    		
    	}	
    	while (counter!=word.length); {//if the counter is not as long a s the length of the word(if you havent won the do will continue but when you have
    		if(death ==0) {}//nothing for losing
    		else if(input.equalsIgnoreCase(l)) {}
    		else if (input.equalsIgnoreCase("Reset")) {}
    		else {
    			for(int i =0;i<word.length;i++) {
    				System.out.print(word[i]+" ");
    			}
    		System.out.println();
            System.out.println("You win!");
    		}
            
        }
    	System.out.println("\nType 'Reset' to Reset Game.\n Type 'Menu' to go to Menu");
    	String b =sc.nextLine();
    	while(b.equalsIgnoreCase("Reset")==false && b.equalsIgnoreCase("Menu")==false) {
			System.out.println("Sorry that command isn't valid. \n");
			b=sc.nextLine();
			if(b.equalsIgnoreCase("Reset")||b.equalsIgnoreCase("Menu")) {
				break;
			}
		}
		if(b.equalsIgnoreCase("Menu")) {
			Menu();
		}
		else if(b.equalsIgnoreCase("Reset")) {
			ModeSelect();
		}
    	
	}
	
	public static String Hangman (int maxDeath, int death) {
		String s ="";
		if(maxDeath ==7) {//decides with switch statement to go to
			switch(death) {
			case 0:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|            |\n");
				s+=("|           _|_\n");
				s+=("|            |\n");
				s+=("|           / \\\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 1:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|            |\n");
				s+=("|           _|_\n");
				s+=("|            |\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 2:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|            |\n");
				s+=("|           _|_\n");
				s+=("|            |\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 3:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|            |\n");
				s+=("|           _|_\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 4:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|            |\n");
				s+=("|           _\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 5:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|            |\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 6:
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|           ___ \n");
				s+=("|          |___|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;
				
			case 7:	
				s+=("____________\n");
				s+=("|           |\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("|\n");
				s+=("_______");break;

			}
		}
			else if (maxDeath ==5) {
				switch(death) {
				case 0:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|           _|_\n");
					s+=("|            |\n");
					s+=("|           / \\\n");
					s+=("|\n");
					s+=("_______");break;
					
				case 1:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|           _|_\n");
					s+=("|            |\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;
					
				case 2:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|           _|_\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;

				case 3:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;
					
				case 4:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;
					
				case 5:	
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;

				}
			}
			else if(maxDeath ==3) {
				switch(death) {
				case 0:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|           _|_\n");
					s+=("|            |\n");
					s+=("|           / \\\n");
					s+=("|\n");
					s+=("_______");break;

				case 1:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|           _|_\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;

				case 2:
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|           ___ \n");
					s+=("|          |___|\n");
					s+=("|            |\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;

				case 3:	
					s+=("____________\n");
					s+=("|           |\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("|\n");
					s+=("_______");break;
				}				
			}
		return s;//returns it to the the method its called from
	}	
}