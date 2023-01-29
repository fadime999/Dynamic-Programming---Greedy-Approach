import java.io.File;
import java.io.IOException;
import java.util.*;

import com.sun.jdi.event.MethodExitEvent;

public class Main {
	public static Piece dynamic_selected[] = new Piece[90];
	public static Piece random_selected[] = new Piece[90];
	public static Piece greedy_selected[] = new Piece[90];

	public static void dynamicProgrammingApproach(Piece piece[], int gold_amount, int max_level_allowed,int number_of_available_pieces_per_level) {
		int max = 0;
		int index = 0;
		for (int i = 0; i < piece.length; i++) {
			max = 0;
			if (piece[i] != null) {
				for (int j = 0; j < number_of_available_pieces_per_level; j++) {//This loop rotates for each type of piece.
					if (piece[j] != null) { 
						if (piece[j].getPiece_type().equalsIgnoreCase("Pawn") && i == 0) {//Finding max in the sub-problems that we have separated.
							if (piece[j].getAttack_points() > max) {
								max = piece[j].getAttack_points();
								dynamic_selected[index] = piece[j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("Rook")) {//Finding max in the sub-problems that we have separated.
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("Archer")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("knight")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("bishop")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("war_ship")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("siege")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("queen")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
					if (piece[i + j] != null) {
						if (piece[i + j].getPiece_type().equalsIgnoreCase("king")) {
							if (piece[i + j].getAttack_points() > max) {
								max = piece[i + j].getAttack_points();
								dynamic_selected[index] = piece[i + j];
							}
						}
					}
				}
				
				if (gold_amount < dynamic_selected[index].getGold() ) 
					dynamic_selected[index] = null;//We cannot add this piece because there is not enough gold.
				else
					gold_amount = gold_amount - dynamic_selected[index].getGold();// Reduction of the amount of gold.
				index++;//At the end of the loop, we increase the index when each piece is added.
				i = i + number_of_available_pieces_per_level - 1;
			}
		}
	}

	public static void greedyApproach(Piece piece[], int gold_amount, int max_level_allowed,int number_of_available_pieces_per_level) {
		
		//Adding the attack points of all the pieces in the search selection space to the array.
		int sorted_selected[] = new int[max_level_allowed * number_of_available_pieces_per_level + 1];
		for (int i = 0; i < sorted_selected.length; i++) {
			if (piece[i] != null) {
				sorted_selected[i] = piece[i].getAttack_points();
			}
		}
		
		Arrays.sort(sorted_selected);//Sorting the array from small to large.
		//To prevent two pieces from being found from the same level.
		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;
		boolean flag6 = false;
		boolean flag7 = false;
		boolean flag8 = false;
		boolean flag9 = false;
		int index = 0;
		//Assigning to the array starting from the biggest attack points without separating them into any sub-problems.
	    for (int i = 0; i < piece.length; i++) {
			for (int j = 0; j < sorted_selected.length; j++) {
				if (piece[j] != null && sorted_selected.length - (index + 1) != -1) {//We start at the end because our series was sorted from small to large.			
					if (sorted_selected[sorted_selected.length - (index + 1)] == piece[j].getAttack_points()) {//If the highest-rated piece coincides with the state it will enter.
						if (piece[j].getPiece_type().equalsIgnoreCase("pawn") && gold_amount >= piece[j].getGold() && flag == false){//Checking whether the amount of gold is enough and preventing the selection of a stone at the same level again.
							greedy_selected[i] = piece[j];//Assigning a piece to the series.
							flag = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();//Reduction of the amount of gold.
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("rook") && gold_amount >= piece[j].getGold() && flag2 == false) {
							greedy_selected[i] = piece[j];;
							flag2 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("archer") && gold_amount >= piece[j].getGold() && flag3 == false) {
							greedy_selected[i] = piece[j];;
							flag3 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("knight") && gold_amount >= piece[j].getGold() && flag4 == false) {
							greedy_selected[i] = piece[j];;
							flag4 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("bishop") && gold_amount >= piece[j].getGold() && flag5 == false) {
							greedy_selected[i] = piece[j];;
							flag5 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("war_ship") && gold_amount >= piece[j].getGold() && flag6 == false) {
							greedy_selected[i] = piece[j];;
							flag6 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("siege") && gold_amount >= piece[j].getGold() && flag7 == false) {
							greedy_selected[i] = piece[j];;
							flag7 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("queen") && gold_amount >= piece[j].getGold() && flag8 == false) {
							greedy_selected[i] = piece[j];;
							flag8 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						} else if (piece[j].getPiece_type().equalsIgnoreCase("king") && gold_amount >= piece[j].getGold() && flag9 == false) {
							greedy_selected[i] = piece[j];
							flag9 = true;
							index++;
							gold_amount = gold_amount - greedy_selected[i].getGold();
							break;
						}
						else{
							index++;
							break;
						}
					}
				}
			}//As a result, the greedy selected array is selected without ranking according to their level.
		}   //That is, the list of the series is not ordered on the screen.The array on the screen is sorted by attack points.
	}

	public static void randomApproach(Piece piece[], int gold_amount, int max_level_allowed,int number_of_available_pieces_per_level) {
		Random rand = new Random();
		int rnd_number = rand.nextInt(number_of_available_pieces_per_level);
		int k = 0;
		for (int i = 0; i < piece.length; i++) {
			if (piece[i] != null) {	
				rnd_number = rand.nextInt(number_of_available_pieces_per_level);//generating random value
				//Checking whether we have enough gold to get the 'random piece'.
				if (gold_amount >= piece[rnd_number + k * number_of_available_pieces_per_level].getGold()) {
					//so that it can choose only one of each level, I sum it up with k * number_of_available_pieces_per_level
					random_selected[k] = piece[rnd_number + k * number_of_available_pieces_per_level];//We put the random value we chose into the array named 'random_selected'.
					gold_amount = gold_amount - piece[rnd_number + k * number_of_available_pieces_per_level].getGold();
					i = i + number_of_available_pieces_per_level;
					k++;//each time we select a random value, we increase k so that we can switch to the other piece type.
				}
			}
		}
	}

	public static void display(Piece piece[]) {
		for (int i = 0; i < piece.length; i++) {
			if (piece[i] != null) {
				System.out.println(piece[i].getHero() + " " + piece[i].getPiece_type() + " " + piece[i].getGold() + " "
						+ piece[i].getAttack_points());
			}
		}
	}

	public static int sum_attack_points(Piece piece[]) {
		int sum_attack_points = 0;
		for (int i = 0; i < piece.length; i++) {
			if (piece[i] != null) {
				sum_attack_points = sum_attack_points + piece[i].getAttack_points();
			}
		}
		return sum_attack_points;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int gold_amount;
		int max_level_allowed;
		int number_of_available_pieces_per_level;

		//Receiving data in required intervals from the user
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the gold amount: ");
		gold_amount = input.nextInt();
		if (gold_amount <= 5 || gold_amount >= 1201) {
			System.out.println("You entered a value in the wrong range, please run the program again.");
			System.exit(1);
		}
		System.out.print("Please enter the maximum allowed level: ");
		max_level_allowed = input.nextInt();
		if (max_level_allowed <= 1 || max_level_allowed >= 10) {
			System.out.println("You entered a value in the wrong range, please run the program again.");
			System.exit(1);
		}
		System.out.print("please enter the number of available pieces per level: ");
		number_of_available_pieces_per_level = input.nextInt();
		if (number_of_available_pieces_per_level <= 1 || number_of_available_pieces_per_level >= 26) {
			System.out.println("You entered a value in the wrong range, please run the program again.");
			System.exit(1);
		}
		
		Piece pieces[] = new Piece[90];
		//We read the file and created a part object and put it in the piece array one by one.
		File f = new File("input_1.csv");
		Scanner file = new Scanner(f);
		String text1 = file.nextLine();
		for (int i = 0; i < pieces.length; i++) {
			String text = file.nextLine();
			String values[] = text.split(",");
			int gold = Integer.parseInt(values[2]);
			int attack_point = Integer.parseInt(values[3]);
			Piece piece = new Piece(values[0], values[1], gold, attack_point);
			pieces[i] = piece;
		}
		file.close();
		//Selecting the pieces from the piece array according to the entered values and placing them in the selection search space directory.
		Piece selection_search_space[] = new Piece[90];
		
		for (int i = 0; i < selection_search_space.length; i++) {
			if (max_level_allowed>=1) {
				if (pieces[i].getPiece_type().equalsIgnoreCase("pawn")) {
					for (int j = 0; j < number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[j];
						i++;
					}
					//To go to the last of the pawn-type heroes in the piece array.
					//Because a different number of pawns may have been entered in each input file, 
					//so i didn't equalize i to a fixed value.
					for (int j = number_of_available_pieces_per_level; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("pawn")) 
							i++;
						else
							break;
					}
				}
		   	} if ( max_level_allowed>=2) {//If the allowed level is 1, ensuring that the rook type is not taken into space.
				if (pieces[i].getPiece_type().equalsIgnoreCase("rook")) {
					for (int j = number_of_available_pieces_per_level; j < 2* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {//To go to the last of the rook-type heroes in the piece array.
						if (pieces[j].getPiece_type().equalsIgnoreCase("rook")) 
							i++;
						else 
							break;
					}
				}
			} if (max_level_allowed>=3 ) {//If the allowed level is 2, ensuring that the archer type is not taken into space.
				if (pieces[i].getPiece_type().equalsIgnoreCase("archer")) {
					for (int j = 2 * number_of_available_pieces_per_level; j < 3* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("archer")) 
							i++;
						else 
							break;
					}
				}
			} if (max_level_allowed>=4) {//If the allowed level is 3, ensuring that the knight type is not taken into space.
				if (pieces[i].getPiece_type().equalsIgnoreCase("knight")) {
					for (int j = 3 * number_of_available_pieces_per_level; j < 4* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("knight")) 
							i++;
						else 
							break;
					}
				}
			}  if (max_level_allowed>=5) {
				if (pieces[i].getPiece_type().equalsIgnoreCase("bishop")) {
					for (int j = 4 * number_of_available_pieces_per_level; j < 5* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("bishop")) 
							i++;
						else 
							break;
					}
				}
			} if (max_level_allowed>=6) {
				if (pieces[i].getPiece_type().equalsIgnoreCase("war_ship")) {
					for (int j = 5 * number_of_available_pieces_per_level; j < 6* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("war_ship")) 
							i++;
						else 
							break;
					}
				}
			} if (max_level_allowed>=7) {
				if (pieces[i].getPiece_type().equalsIgnoreCase("siege")) {
					for (int j = 6 * number_of_available_pieces_per_level; j < 7* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("siege")) 
							i++;
						else 
							break;
					}
				}
			} if (max_level_allowed>=8) {
				if (pieces[i].getPiece_type().equalsIgnoreCase("queen")) {
					for (int j = 7 * number_of_available_pieces_per_level; j < 8* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("queen")) {
							i++;
						}
						else 
							break;
					}
				}
			} if ((max_level_allowed == 9 || max_level_allowed == 10)) {
				if (pieces[i].getPiece_type().equalsIgnoreCase("king")) {
					for (int j = 8 * number_of_available_pieces_per_level; j < 9* number_of_available_pieces_per_level; j++) {
						selection_search_space[j] = pieces[i];
						i++;
					}
					for (int j = i+1; j < pieces.length; j++) {
						if (pieces[j].getPiece_type().equalsIgnoreCase("king")) {
							i++;
						}
						else 
							break;
					}
				}
			} 
		}//As a result, a sample space consisting of the desired number of stones at the allowable level was formed.
		//You can see it occurs the same way as in the homework document by debugging it.
		
		long startTime0 = 0 ,  startTime1 = 0 ,startTime2 = 0;
		long estimatedTime0 = 0 , estimatedTime1 = 0 , estimatedTime2 = 0;
		//Instructed to measure their execution times.
		startTime0 = System.nanoTime();
		greedyApproach(selection_search_space, gold_amount, max_level_allowed, number_of_available_pieces_per_level);
		estimatedTime0 = System.nanoTime() - startTime0;
	   
		startTime1= System.nanoTime();
	    dynamicProgrammingApproach(selection_search_space, gold_amount, max_level_allowed,number_of_available_pieces_per_level);
		estimatedTime1 = System.nanoTime() - startTime1;
		
		startTime2 = System.nanoTime();
		randomApproach(selection_search_space, gold_amount, max_level_allowed, number_of_available_pieces_per_level);
		estimatedTime2 = System.nanoTime() - startTime2;
		
		//Writing values to the console.
		System.out.println("\n-------------------TRIAL #1-------------------");
		System.out.println("******Computer's Greedy Approach Results******");
		System.out.println("Total Attack Points: "+sum_attack_points(greedy_selected));
		System.out.println("Execution Time: "+estimatedTime0);
		System.out.println("Selected Pieces List: ");
		display(greedy_selected);

		System.out.println("\n******User's Dynamic Programming Results******");
		System.out.println("Total Attack Points: "+sum_attack_points(dynamic_selected));
		System.out.println("Execution Time: "+estimatedTime1);
		System.out.println("Selected Pieces List: ");
		display(dynamic_selected);

		System.out.println("\n-------------------TRIAL #2-------------------");
		System.out.println("******Computer's Random Approach Results******");
		System.out.println("Total Attack Points: "+sum_attack_points(random_selected));
		System.out.println("Execution Time: "+estimatedTime2);
		System.out.println("Selected Pieces List: ");
		display(random_selected);

		System.out.println("\n******User's Dynamic Programming Results******");
		System.out.println("Total Attack Points: "+sum_attack_points(dynamic_selected));
		System.out.println("Execution Time: "+estimatedTime1);
		System.out.println("Selected Pieces List: ");
		display(dynamic_selected);
	}
}