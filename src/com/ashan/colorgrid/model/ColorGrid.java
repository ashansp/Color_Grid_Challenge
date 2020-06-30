package com.ashan.colorgrid.model;

import java.util.List;

/**
 * @author ashanper
 *
 */
public class ColorGrid {

	private int rows;
	private int columns;

	private List<ColorNode> nodeList;

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @return the nodeList
	 */
	public List<ColorNode> getNodeList() {
		return nodeList;
	}

	/**
	 * @param nodeList the nodeList to set
	 */
	public void setNodeList(List<ColorNode> nodeList) {
		this.nodeList = nodeList;
	}
}
