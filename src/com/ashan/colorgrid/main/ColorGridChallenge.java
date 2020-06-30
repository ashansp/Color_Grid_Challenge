package com.ashan.colorgrid.main;

import java.awt.Color;
import java.util.Random;

import com.ashan.colorgrid.model.ColorNode;

/**
 * @author ashanper
 *
 */
public class ColorGridChallenge {

	static int n = 0;
	static int m = 0;
	
	static Color[] colorArray = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW };
	static String[] colorNameArr = {"R", "B", "G", "O", "Y"};

	// stores information about which cell
	// are already visited in a particular BFS
	static int visited[][];

	// result stores the final result grid
	static ColorNode result[][];

	// stores the count of
	// cells in the largest
	// connected component
	static int COUNT;

	// Function checks if a cell
	// is valid i.e it is inside
	// the grid and equal to the key
	static boolean is_valid(int x, int y, Color key, ColorNode gameContext[][]) {
		if (x < n && y < m && x >= 0 && y >= 0) {
			if (visited[x][y] == 0 && gameContext[x][y].getColor() == key)
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 * @param gameContext
	 * 
	 * Bread-First-Search to search through graph
	 */
	static void search(Color x, Color y, int i, int j, ColorNode gameContext[][]) {
		// terminating case for BFS
		if (x != y)
			return;

		visited[i][j] = 1;
		COUNT++;

		// x_move and y_move arrays
		// are the possible movements
		// in x or y direction
		int x_move[] = { 0, 0, 1, -1 };
		int y_move[] = { 1, -1, 0, 0 };

		// checks all four points
		// connected with input[i][j]
		for (int u = 0; u < 4; u++)
			if ((is_valid(i + y_move[u], j + x_move[u], x, gameContext)) == true)
				search(x, y, i + y_move[u], j + x_move[u], gameContext);
	}

	// called every time before
	// a BFS so that visited
	// array is reset to zero
	static void reset_visited() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				visited[i][j] = 0;
	}

	// If a larger connected component
	// is found this function is
	// called to store information
	// about that component.
	/**
	 * @param key
	 * @param gameContext
	 */
	static void reset_result(Color key, ColorNode gameContext[][]) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i][j] = gameContext[i][j];
				
				if (visited[i][j] == 1 && gameContext[i][j].getColor() == key) {
					//result[i][j] = visited[i][j];
					result[i][j].setResult(visited[i][j]);
				} else {
					//result[i][j] = 0;
					result[i][j].setResult(0);
				}
			}
		}
	}

	/**
	 * @param res
	 * 
	 * this function prints out the result in text form
	 */
	static void print_result(int res) {
		
		// prints the whole grid with color codes
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(result[i][j].getColorName() + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("The largest continuous color grid count is : " + res);
		System.out.println();
		System.out.println("Nodes forming the largest continuous grid are: ");
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (result[i][j].getResult() != 0) {
					System.out.println("(" + result[i][j].getxPosition() + "," + result[i][j].getyPosition() + ")");
				}
			}
		}
		
		System.out.println();
		
		// prints the largest component
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (result[i][j].getResult() != 0)
					System.out.print(result[i][j].getColorName() + " ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
	}

	// function to calculate the
	// largest connected component
	static void calculateContinuousColorGrid(ColorNode gameContext[][]) {
		int current_max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				reset_visited();
				COUNT = 0;

				// checking cell to the right
				if (j + 1 < m)
					search(gameContext[i][j].getColor(), gameContext[i][j + 1].getColor(), i, j, gameContext);

				// updating result
				if (COUNT >= current_max) {
					current_max = COUNT;
					reset_result(gameContext[i][j].getColor(), gameContext);
				}
				reset_visited();
				COUNT = 0;

				// checking cell downwards
				if (i + 1 < n)
					search(gameContext[i][j].getColor(), gameContext[i + 1][j].getColor(), i, j, gameContext);

				// updating result
				if (COUNT >= current_max) {
					current_max = COUNT;
					reset_result(gameContext[i][j].getColor(), gameContext);
				}
			}
		}
		print_result(current_max);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Random randomGenerator = new Random();
		int randomColorNum;
		int low = 10;
		int high = 100;
		int resultNum = randomGenerator.nextInt(high-low) + low;
		
		n = resultNum;
		m = resultNum;
		
		visited = new int[n][m];
		result = new ColorNode[n][m];
		
		low = 0;
		high = 4;
		
		ColorNode gameContext[][] = new ColorNode[resultNum][resultNum];
		
		for (int i = 0; i < resultNum; i++) {
			for (int j = 0; j < resultNum; j++) {
				randomColorNum = randomGenerator.nextInt(high-low) + low;
				gameContext[i][j] = new ColorNode(i, j, colorArray[randomColorNum], colorNameArr[randomColorNum]);
			}
		}
		
		calculateContinuousColorGrid(gameContext);
		
		
	}

}
