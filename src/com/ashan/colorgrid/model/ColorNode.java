package com.ashan.colorgrid.model;

import java.awt.Color;

/**
 * @author ashanper
 *
 */
public class ColorNode {

	private int xPosition;
	private int yPosition;
	private Color color;
	private String colorName;
	private int result;
	
	/**
	 * @param xPosition
	 * @param yPosition
	 * @param color
	 */
	public ColorNode(int xPosition, int yPosition, Color color, String colorName) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.color = color;
		this.colorName = colorName;
	}

	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * @param xPosition the xPosition to set
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * @param yPosition the yPosition to set
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the colorName
	 */
	public String getColorName() {
		return colorName;
	}

	/**
	 * @param colorName the colorName to set
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

}
