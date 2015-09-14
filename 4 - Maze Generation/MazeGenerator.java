//Tara Moses
//CP3 Assignment 4: Maze Generation
//January 28, 2014

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.util.Scanner;

public class MazeGenerator {	
	public static int[] visitedX;
	public static int[] visitedY;
	public static int visitedCount=0;
	public static int[] stackX;
	public static int[] stackY;
	public static int stackSize=0;
	public static int[] neighborX;
	public static int[] neighborY;
	public static int[] pathsX;
	public static int[] pathsY;
	
	/**Takes a 2D array and makes it into a picture. Saves it!
	 * 
	 * @param array the 2D int array that needs to be made into a picture
	 * @param filename the filename to save the new image as
	 */
	public static void arrayToImage(int[][] array, String filename) {
		int width=array[0].length;
		int height=array.length;
		BufferedImage newImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int x=0;x<width;x++) {
			for (int y=0;y<height;y++) {
				int unpixelValue=array[y][x];
				if (array[y][x]==-8) newImage.setRGB(x,y,0xFF0000);
				else if (array[y][x]==-5) newImage.setRGB(x,y,0x00FF00);
				else if (array[y][x]==666) newImage.setRGB(x,y,0x1E90FF);
				//else if (array[y][x]==255) newImage.setRGB(x,y,0xFF8000);
				else newImage.setRGB(x,y,0x010101*array[y][x]);
			}
		}
		
		try {
			File outFile=new File(filename);
			ImageIO.write(newImage, "png", outFile);
		} catch (Exception e) {
			System.out.println("It didn't work.");
		}
	}
	
	/**Takes in a picture and returns a 2D array.
	 * 
	 * @param filename the filename of the picture to change into a 2D array
	 * @return the array full of ints that each stand for a shade of grey
	 */
	public static int[][] imageToArray(String filename) {
		File inFile=new File(filename);
		
		try {
			BufferedImage image=ImageIO.read(inFile);
			int width=image.getWidth();
			int height=image.getHeight();
			int[][] array=new int[height][width];
			
			for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
				Color c=new Color(image.getRGB(x,y),true);
				int r=c.getRed();
				int g=c.getGreen();
				int b=c.getBlue();
				
				if (r==0xFF && g==0 && b==0) array[y][x]=-8;
				else if (g==0xFF && r==0 && b==0) array[y][x]=-5;
				else if (r<=128) array[y][x]=0;
				else array[y][x]=255;
			}
			return array;
		} catch (Exception e) {
			System.out.println("Something went wrong D:");
			return new int[0][0];
		}
	}
	
	/**
	 * checks whether a cell has been visited by searching
	 * through visitedX and visitedY arrays to see if the
	 * point is in there.
	 * @param x the x-coordinate of the cell
	 * @param y the y-coordinate of the cell
	 * @return true if cell coordinates aren't found, false if they are
	 **/
	public static boolean isUnvisited(int x, int y) {
		int index=-1;
		
		for (int i=0;i<visitedX.length;i++) {
			if (visitedX[i]==x && visitedY[i]==y) index=i;
		}
		
		if (index==-1) return true;
		return false;
	}
	
	/**
	 * adds a cell's unvisited neighbors to neighborX and neighborY arrays.
	 * @param maze the maze grid containing the cell to get neighbors of
	 * @param exitX the x-coordinate of the cell to get neighbors of
	 * @param exitY the y-coordinate of the cell to get neighbors of
	 * @return the number of neighbors the cell has
	 **/
	public static int getNeighbors(int[][] maze, int exitX, int exitY) {
		int neighborCount=0;
		neighborX=new int[4];  //4 possible neighbors
		neighborY=new int[4];
		
		if (isUnvisited(exitX-2,exitY) && exitX>=3) {
			//System.out.println("west is okay for ("+exitX+","+exitY+")!");
			neighborX[neighborCount]=exitX-2;
			neighborY[neighborCount]=exitY;
			neighborCount++;
		}
		if (isUnvisited(exitX+2,exitY) && exitX<maze[0].length-3) {
			//System.out.println("east is okay for ("+exitX+","+exitY+")!");
			neighborX[neighborCount]=exitX+2;
			neighborY[neighborCount]=exitY;
			neighborCount++;
		}
		if (isUnvisited(exitX,exitY-2) && exitY>=3) {
			//System.out.println("north is okay for ("+exitX+","+exitY+")!");
			neighborX[neighborCount]=exitX;
			neighborY[neighborCount]=exitY-2;
			neighborCount++;
		}
		if (isUnvisited(exitX,exitY+2) && exitY<maze.length-3) {
			//System.out.println("south is okay for ("+exitX+","+exitY+")!");
			neighborX[neighborCount]=exitX;
			neighborY[neighborCount]=exitY+2;
			neighborCount++;
		}
		
		return neighborCount;
	}
	
	public static int getPaths(int[][] maze, int x, int y, int prevX, int prevY) {
		int pathsCount=0;
		pathsX=new int[4]; 
		pathsY=new int[4];
		
		if (x>=2 && isUnvisited(x-1,y) && maze[y][x-1]!=0 && !(x-1==prevX && y==prevY)) {
			pathsX[pathsCount]=x-1;
			pathsY[pathsCount]=y;
			pathsCount++;
		}
		if (x<maze[0].length-2 && isUnvisited(x+1,y) && maze[y][x+1]!=0 && !(x+1==prevX && y==prevY)) {
			pathsX[pathsCount]=x+1;
			pathsY[pathsCount]=y;
			pathsCount++;
		}
		if (y>=2 && isUnvisited(x,y-1) && maze[y-1][x]!=0 && !(x==prevX && y-1==prevY)) {
			pathsX[pathsCount]=x;
			pathsY[pathsCount]=y-1;
			pathsCount++;
		}
		if (y<maze.length-2 && isUnvisited(x,y+1) && maze[y+1][x]!=0 && !(x==prevX && y+1==prevY)) {
			pathsX[pathsCount]=x;
			pathsY[pathsCount]=y+1;
			pathsCount++;
		}
		
		//System.out.println("x: "+x+", y: "+y+", numPaths: "+pathsCount+"");
		
		return pathsCount;
	}
	
	/**
	 * takes a cell, randomly chooses one of its neighbors, moves there,
	 * and marks the current cell as visited
	 * @param maze the grid that soon will be a maze
	 * @param x the x-coordinate of the current cell
	 * @param y the y-coordinate of the current cell
	 **/
	public static void createMaze(int[][] maze, int x, int y) {
		int currentX=x;
		int currentY=y;
		int nextX=x;
		int nextY=y;
		int randomNum;
		
		maze[1][0]=-5; //make start square green
		
		markAsVisited(maze,1,1);
		
		while (visitedCount<visitedX.length && visitedCount<visitedY.length) {	
			if (getNeighbors(maze,currentX,currentY)!=0) {
				randomNum=(int)(Math.random()*getNeighbors(maze,currentX,currentY));
				//System.out.println("random num = "+randomNum);
				
				//choose where to go!!
				nextX=neighborX[randomNum];
				nextY=neighborY[randomNum];
				
				//push current cell to stack
				stackX[stackSize]=currentX;
				stackY[stackSize]=currentY;
				stackSize++;
				
				//remove wall between current and chosen
				maze[nextY+(currentY-nextY)/2][nextX+(currentX-nextX)/2]=255;
				
				currentX=nextX;
				currentY=nextY;
				
				markAsVisited(maze,currentX,currentY);
			} else if (stackSize>0) {
				nextX=stackX[stackSize-1];
				nextY=stackY[stackSize-1];
				//System.out.println("now popping ("+nextX+","+nextY+")");
				stackSize--;
				
				currentX=nextX;
				currentY=nextY;
			}
		}
		
		maze[maze.length-2][maze[0].length-1]=-8;  //make end square red
	}
	
	/**
	 * marks the cell as visited by adding its x and y coordinates to 
	 * the visitedX and visitedY arrays, respectively
	 * @param maze the maze containing the visited cell
	 * @param x the x-coordinate of the visited cell
	 * @param y the y-coordinate of the visited cell
	 **/
	public static void markAsVisited(int[][] maze, int x, int y) {
		visitedX[visitedCount]=x;
		visitedY[visitedCount]=y;
		visitedCount++;
		//System.out.println("("+x+","+y+") has been visited.");
	}
	
	public static void solveMaze(int[][] maze, int x, int y) {
		stackSize=0;
		visitedX=new int[(maze.length-1)*(maze[0].length-1)];
		visitedY=new int[(maze.length-1)*(maze[0].length-1)];
		visitedCount=0;
		
		helper(maze,x,y,x-1,y);
	}
	
	public static void helper(int[][] maze, int x, int y, int prevX, int prevY) {
		int nextX;
		int nextY;
		int randomNum;
		
		if (maze[y][x-1]!=-8 && maze[y+1][x]!=-8) {
			if (getPaths(maze,x,y,prevX,prevY)>0) {
				//find a way to go
				randomNum=(int)(Math.random()*getPaths(maze,x,y,prevX,prevY));
				nextX=pathsX[randomNum];
				nextY=pathsY[randomNum];
				
				//push current cell to stackkckckck
				stackX[stackSize]=x;
				stackY[stackSize]=y;
				stackSize++;
				
				//go that way
				prevX=x;
				prevY=y;
				x=nextX;
				y=nextY;
				
				//System.out.println("moving on to ("+x+","+y+")");
				markAsVisited(maze,x,y);
				helper(maze,x,y,prevX,prevY);
				
				//System.out.println("coloring ("+x+","+y+")");
				if (maze[y][x]==254) maze[y][x]=255;
				else maze[y][x]=666;
			} else if (stackSize>0) {
				nextX=stackX[stackSize-1];
				nextY=stackY[stackSize-1];
				stackSize--;
				
				maze[y][x]=254;
				//System.out.println("recoloring ("+x+","+y+") to white!");
				
				prevX=x;
				prevY=y;
				x=nextX;
				y=nextY;
				
				//System.out.println("moving on to ("+x+","+y+"). also STACK");
				helper(maze,x,y,prevX,prevY);
			}
			
		}
	}
	
	/**
	 * the main function. asks how big you want your maze, then creates
	 * and saves it!
	 **/
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int width;
		int height;
		
		System.out.print("Do you want to (1) build or (2) solve? ");
		int option=scan.nextInt();
		
		if (option==1) {
			System.out.print("How many cells wide would you like your maze to be? ");
			width=scan.nextInt();
			System.out.print("How many cells high would you like your maze to be? ");
			height=scan.nextInt();
			System.out.println();
			
			visitedX=new int[width*height];
			visitedY=new int[width*height];
			stackX=new int[(2*width-1)*(2*height-1)];
			stackY=new int[(2*width-1)*(2*height-1)];
			
			int[][] mazeGrid=new int[2*height+1][2*width+1];
			for (int x=0;x<2*width+1;x++) {
				for (int y=0;y<2*height+1;y++) {
					if (y==0 || y==2*height || x==0 || x==2*width) mazeGrid[y][x]=0;
					else if (x%2==0 || y%2==0) mazeGrid[y][x]=0;
					else mazeGrid[y][x]=255;
				}
			}
			
			//tear down these walls!!
			createMaze(mazeGrid,1,1);		
			arrayToImage(mazeGrid,"newMaze.png");
			System.out.println("hey i finished making your maze c:");
		} else {
			System.out.print("What is the filename of the maze to be solved? ");
			String filename=scan.next();
			//System.out.println("filename is "+filename);
			
			int[][] mazeToSolve=imageToArray(filename);
			width=(mazeToSolve[0].length-1)/2;
			height=(mazeToSolve.length-1)/2;
			
			visitedX=new int[width*height];
			visitedY=new int[width*height];
			stackX=new int[(2*width-1)*(2*height-1)];
			stackY=new int[(2*width-1)*(2*height-1)];
			
			long time1=System.nanoTime();
			solveMaze(mazeToSolve,287,0);
			long time2=System.nanoTime();
			long duration=time2-time1;
			System.out.println("It took "+duration+" nanoseconds to solve.");
			
			arrayToImage(mazeToSolve,"SOLVED_maze.png");
		}
	}
}
