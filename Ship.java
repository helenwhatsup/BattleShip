package finalproject;

import java.util.Arrays;

import cse131.NotYetImplementedException;

/**
 * @author Mariah Yelenick and Adam Kern
 * @version 11/18/18
 */
public class Ship {
	private int topLeftX;
	private int topLeftY;
	private int length;
	private boolean isHorizontal;
	private boolean[] xa;
	private int[] xCoordinates;
	private int[] yCoordinates;

	/**
	 * Create an instance of the ship class, recording all necessary information
	 * into any instance variables you choose to create
	 * 
	 * @param topLeftX
	 *            the x coordinate of the ship's uppermost, leftmost space
	 * @param topLeftY
	 *            the y coordinate of the ship's uppermost, leftmost space
	 * @param length
	 *            the number of spaces the ship occupies
	 * @param isHorizontal
	 *            if true, the ship is placed horizontally, else the ship is placed
	 *            vertically
	 */
	public Ship(int topLeftX, int topLeftY, int length, boolean isHorizontal) {

		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.length = length;
		this.isHorizontal = isHorizontal;
		this.xCoordinates = new int[length];
		this.yCoordinates = new int[length];
		this.xa = new boolean[length];
		Arrays.fill(xa, false);

		if (isHorizontal == true) {
			for (int i = 0; i < length; i++) {
				this.xCoordinates[i] = topLeftX + i;
				this.yCoordinates[i]=topLeftY;
			}
		} else {
			for (int i = 0; i < length; i++) {
				this.yCoordinates[i] = topLeftY + i;
				
			}
			Arrays.fill(xCoordinates, topLeftX);
		}

	}

	public int[] getxCoordinates() {
		return xCoordinates;
	}

	public int[] getyCoordinates() {
		return yCoordinates;
	}

	public boolean getIsHorizontal() {
		return isHorizontal;
	}

	public int getLength() {
		return length;
	}

	/**
	 * Check if the ship has been sunk This should only be true if the ship was hit
	 * in all the spaces it occupies
	 * 
	 * @return true if the ship has been sunk
	 */
	public boolean isSunk() {
		for(int i = 0; i < length; ++i) {
			if(!this.xa[i]==true) {
				return false;
			}
		}
		return true;
		
	}

	@Override
	public String toString() {
		return "Ship [topLeftX=" + topLeftX + ", topLeftY=" + topLeftY + ", length=" + length + ", isHorizontal="
				+ isHorizontal + ", xa=" + Arrays.toString(xa) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isHorizontal ? 1231 : 1237);
		result = prime * result + length;
		result = prime * result + topLeftX;
		result = prime * result + topLeftY;
		result = prime * result + Arrays.hashCode(xa);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (isHorizontal != other.isHorizontal)
			return false;
		if (length != other.length)
			return false;
		if (topLeftX != other.topLeftX)
			return false;
		if (topLeftY != other.topLeftY)
			return false;
		if (!Arrays.equals(xa, other.xa))
			return false;
		return true;
	}

	/**
	 * Check if the ship occupies a space at (x, y) Note: Be sure to update the hits
	 * array so that you can check if the ship is sunk!
	 * 
	 * @param x
	 *            the x coordinate to shoot at
	 * @param y
	 *            the y coordinate to shoot at
	 * @return true if this ship occupies that spot (hit), false otherwise (miss)
	 */
	public boolean isHit(int x, int y) {
		for (int i = 0; i < length; ++i) {
			if (this.xCoordinates[i] == x && this.yCoordinates[i] == y) {
				this.xa[i] = true;
				return true;
			}
		}
		return false;
	}

	public boolean checkOverlap(Ship other) {
		for (int i = 0; i < this.length; ++i) {
			for (int j = 0; j < other.length; ++j) {
				if (this.getxCoordinates()[i] == other.getxCoordinates()[j]
						&& this.getyCoordinates()[i] == other.getyCoordinates()[j]) {
					return true;
				}
			}
		}
		return false;
	}
}
