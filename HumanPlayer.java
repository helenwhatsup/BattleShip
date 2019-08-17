package finalproject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import cse131.ArgsProcessor;
import cse131.NotYetImplementedException;
import sedgewick.StdDraw;

public class HumanPlayer implements Player {

	private final ArgsProcessor ap; // Don't change this!
	private String name;
	private int height;
	private int width;
	private Map<Ship, Boolean> ships;
	private int[][] board;
	private int[][] hitmiss;
	private String[][]da;
	/**
	 * Creates an instance of the HumanPlayer class Note that we already dealt with
	 * taking in an ArgsProcessor object We know you've never seen this before,
	 * which is why it's given to you You can treat this ArgsProcessor (ap)
	 * throughout the HumanPlayer class like any other ArgsProcessor you've used in
	 * 131 We leave the rest of the constructor to you
	 * 
	 * @param name
	 *            the name of the player
	 * @param height
	 *            the height of the board
	 * @param width
	 *            the width of the board
	 * @param ap
	 *            the ArgsProcessor object
	 */
	public HumanPlayer(String name, int height, int width, ArgsProcessor ap) {
		this.name = name;
		this.height = height;
		this.width = width;
		this.ap = ap;
		this.board = new int[width][height];
		this.hitmiss=new int [width][height];
		this.da=new String[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				hitmiss[i][j]=0;
			}

		}

		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				da[i][j]=".";
			}

		}


		this.ships = new HashMap<Ship, Boolean>();
	}

	public boolean addShip(Ship s) {
		if (!this.isValidShipToAdd(s)) {
			return false;
		}
		ships.put(s, false);
		return true;
	}

	@Override
	public int[] getTargetLocation() {
		int x = -1, y = -1;
		int location[] = new int[2];
		while(x < 0 || x >= width || y < 0 || y >= height)
		{
			x = ap.nextInt();
			y = ap.nextInt();
		} 
		location[0] = x;
		location[1] = y;
		return location;

	}

	@Override
	public void recordHitOrMiss(int x, int y, boolean isHit) {
		if (isHit==true) {
			hitmiss[x][y]=100;
		}else {
			hitmiss[x][y]=-100;
		}


		if (isHit==true) {
			da[x][y]="x";
		}else {
			da[x][y]="o";
		}


		//		if (isHit==true)
		//			if int[]board= new int [x][y];
		//	}else if (isHit==false)
		//
		//		int[]location = this.getTargetLocation()
		//		


	}

	@Override
	public Ship decideShipPlacement(int length) {;
	boolean valid = false;
	Ship s = new Ship(0,0,1,true);
	while(!valid)
	{
		int x = -1, y = -1;
		//				while(x < 0 || x >= width || y < 0 || y >= height)
		//				{
		x = ap.nextInt();
		y = ap.nextInt();

		int[] location = new int[] {x,y};

		boolean isHorizontalS = ap.nextBoolean();
		s = new Ship(location[0], location[1], length, isHorizontalS);
		//			Set<Ship> shipSet = ships.keySet();
		//			boolean overlapped = false;
		//			for (Ship existingShip : shipSet) {
		//				if(s.checkOverlap(existingShip)) overlapped = true;
		//			}
		valid = this.isValidShipToAdd(s);
	};
	return s;
	}

	@Override
	public String getName() {
		return this.name;

	}

	@Override
	public boolean respondToFire(int x, int y) {
		Set<Ship> shipSet = ships.keySet();
		for (Ship existingShip : shipSet) {
			boolean hit = existingShip.isHit(x, y);
			if (hit==true)
				return true;
		}
		return false;
	}

	@Override
	public int numShipsStillAfloat() {
		Set<Ship> shipSet = ships.keySet();//set of ship
		int count = shipSet.size();//number of ship in the set
		for (Ship existingShip : shipSet) {
			if (existingShip.isSunk())//if the ship is sunk, the total num of ship -1
				count--;
		}
		return count;
	}

	@Override
	public boolean addRandomShip(int length) {
		boolean success;
		do {
			int topLeftXRand = (int) (Math.random() * (width - 1));
			int topLeftYRand = (int) (Math.random() * (height - 1));
			boolean isHorizontalRand = Math.random() > 0.5;
			Ship s = new Ship(topLeftXRand, topLeftYRand, length, isHorizontalRand);
			success = this.addShip(s);
		} while (!success);
		return true;
	}


	@Override
	public boolean isValidShipToAdd(Ship s) {
		if (s.getxCoordinates()[s.getxCoordinates().length - 1] >= width || s.getxCoordinates()[0] < 0
				|| s.getyCoordinates()[s.getyCoordinates().
				                       length - 1] >= height || s.getyCoordinates()[0] < 0)
			return false;
		Set<Ship> shipSet = ships.keySet();
		for (Ship existingShip : shipSet) {
			if(s.checkOverlap(existingShip))
				return false;
		}
		return true;
	}

	@Override
	public void printRadar() {
		for(int i=0;i<da.length;i++) {
			for(int p=0;p<da[i].length;p++) {
				
				double y=(1-(double)i/(width-1.0));
				double x=((double)p/(height-1.0));
			
				System.out.print(da[i][p]);
				if(hitmiss[i][p]==0) {
					
				StdDraw.setPenColor(StdDraw.PINK);
				StdDraw.setPenRadius(1);
		        double radius=0.03;
		        StdDraw.filledCircle( x, y, radius); 
				
				} else if(hitmiss[i][p]==100){
					StdDraw.setPenColor(StdDraw.CYAN);
					StdDraw.setPenRadius(1);
			        double radius=0.03;
			        StdDraw.filledCircle( x, y, radius); 
				}else if(hitmiss[i][p]==-100) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.setPenRadius(1);
			        double radius=0.03;
			        StdDraw.filledCircle( x, y, radius); 
				}
				
				
				
				
				
				
			}
			System.out.println();
			
			
			
		}

	}

}
