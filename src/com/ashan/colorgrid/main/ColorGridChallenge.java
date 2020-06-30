package com.ashan.colorgrid.main;

import java.awt.Color;
import java.util.Random;

import com.ashan.colorgrid.model.ColorNode;

/**
 * @author ashanper
 *
 */
public class ColorGridChallenge {

	static int gridXValue = 0;
	static int gridYValue = 0;
	
	static Color[] colorArray = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW };
	static String[] colorNameArr = {"R", "B", "G", "O", "Y"};

	// this array will be used to store all visited nodes when performing the BFS
	static int visited[][];

	// this array will store the final resulting grid
	static ColorNode result[][];

	// this variable will used to keep count of the highest traversed nodes
	static int COUNT;

	/**
	 * @param x
	 * @param y
	 * @param key
	 * @param gameContext
	 * @return boolean - if the node is valid (within the grid and if the color is matching)
	 */
	static boolean isValid(int x, int y, Color key, ColorNode gameContext[][]) {
		if (x < gridXValue && y < gridYValue && x >= 0 && y >= 0) {
			if (visited[x][y] == 0 && gameContext[x][y].getColor() == key) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
		
		if (x != y)
			return;

		visited[i][j] = 1;
		COUNT++;

		// below arrays show the possible movements to reach adjacent nodes
		int xMove[] = { 0, 0, 1, -1 };
		int yMove[] = { 1, -1, 0, 0 };

		// check all adjacent nodes connected to node(i,j)
		for (int u = 0; u < 4; u++)
			if ((isValid(i + yMove[u], j + xMove[u], x, gameContext)) == true)
				search(x, y, i + yMove[u], j + xMove[u], gameContext);
	}

	/**
	 * Reset visited array
	 */
	static void resetVisitedArray() {
		for (int i = 0; i < gridXValue; i++)
			for (int j = 0; j < gridYValue; j++)
				visited[i][j] = 0;
	}

	/**
	 * @param key
	 * @param gameContext
	 */
	static void resetResultsArray(Color key, ColorNode gameContext[][]) {
		for (int i = 0; i < gridXValue; i++) {
			for (int j = 0; j < gridYValue; j++) {
				result[i][j] = gameContext[i][j];
				
				if (visited[i][j] == 1 && gameContext[i][j].getColor() == key) {
					result[i][j].setResult(visited[i][j]);
				} else {
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
	static void printResult(int res) {
		
		// prints the whole grid with color codes
		for (int i = 0; i < gridXValue; i++) {
			for (int j = 0; j < gridYValue; j++) {
				System.out.print(result[i][j].getColorName() + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("The largest continuous color grid count is : " + res);
		System.out.println();
		System.out.println("Nodes forming the largest continuous grid are: ");
		
		for (int i = 0; i < gridXValue; i++) {
			for (int j = 0; j < gridYValue; j++) {
				if (result[i][j].getResult() != 0) {
					System.out.println("(" + result[i][j].getxPosition() + "," + result[i][j].getyPosition() + ")");
				}
			}
		}
		
		System.out.println();
		
		// prints the largest component only
		for (int i = 0; i < gridXValue; i++) {
			for (int j = 0; j < gridYValue; j++) {
				if (result[i][j].getResult() != 0)
					System.out.print(result[i][j].getColorName() + " ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
	}

	/**
	 * @param gameContext
	 * 
	 * calculated largest continuous grid with adjacent searches
	 */
	static void calculateContinuousColorGrid(ColorNode gameContext[][]) {
		int currentMax = Integer.MIN_VALUE;

		for (int i = 0; i < gridXValue; i++) {
			for (int j = 0; j < gridYValue; j++) {
				resetVisitedArray();
				COUNT = 0;

				// checking cell to the right
				if (j + 1 < gridYValue) {
					search(gameContext[i][j].getColor(), gameContext[i][j + 1].getColor(), i, j, gameContext);
				}

				// if a large value is found, update the results array
				if (COUNT >= currentMax) {
					currentMax = COUNT;
					resetResultsArray(gameContext[i][j].getColor(), gameContext);
				}
				resetVisitedArray();
				COUNT = 0;

				// checking cell downwards
				if (i + 1 < gridXValue) {
					search(gameContext[i][j].getColor(), gameContext[i + 1][j].getColor(), i, j, gameContext);
				}

				// if a large value is found, update the results array
				if (COUNT >= currentMax) {
					currentMax = COUNT;
					resetResultsArray(gameContext[i][j].getColor(), gameContext);
				}
			}
		}
		printResult(currentMax);
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
		
		gridXValue = resultNum;
		gridYValue = resultNum;
		
		visited = new int[gridXValue][gridYValue];
		result = new ColorNode[gridXValue][gridYValue];
		
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
