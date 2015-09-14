//Tara Moses
//Class for CP3 Assignment 5: Map
//February 9, 2014

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Map {
	Pixel start;
	Pixel end;
	int width;
	int height;
	Pixel[] pathSize;
	List<Pixel> activePixels;
	List<Pixel> visitedPixels;
	//List<Pixel> path;
	Pixel[][] map;
	int[][] costs;
	
	public Map(String filename) {
		imageToMap(filename); //create the map whoohoo! also sets width and height, also endpoints
		
		costs=new int[height][width];
		activePixels=new ArrayList<Pixel>();
		visitedPixels=new ArrayList<Pixel>();
		//path=new ArrayList<Pixel>();	
	}
	
	public void imageToMap(String filename) {
		File inFile=new File(filename);
		
		try {
			BufferedImage image=ImageIO.read(inFile);
			width=image.getWidth();
			height=image.getHeight();
			map=new Pixel[height][width];
			
			for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
				Pixel temp=new Pixel(x,y);				
				int colorNum;
				
				Color c=new Color(image.getRGB(x,y),true);
				int r=c.getRed();
				int g=c.getGreen();
				int b=c.getBlue();
				
				if (r==0 && g==0 && b==0xFF) colorNum=2;  //blue water
				else if (r==0xFF && g==0 && b==0) {
					colorNum=-8;  //red end
					end=temp;
				}
				else if (r==0 && g==0xFF && b==0) {
					colorNum=-5;  //green start
					start=temp;
				}
				else if (r==0 && g==0 && b==0) colorNum=0;  //WALLS
				else colorNum=255;  //white! :D
				
				temp.setColor(colorNum);
				map[y][x]=temp;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong D:");
		}
	}
	
	//methods that people will actually call lol
	public Pixel[] getNeighbors(Pixel current) {
		int x=current.x;
		int y=current.y;
		List<Pixel> neighbors=new ArrayList<Pixel>();
		
		if (x>=1 && y>=1 && isAvailable(getPixel(x-1,y-1))) {
			neighbors.add(getPixel(x-1,y-1)); //northwest
		}
		if (y>=1 && isAvailable(getPixel(x,y-1))) {
			neighbors.add(getPixel(x,y-1)); //north
		}
		if (x<=width-2 && y>=1 && isAvailable(getPixel(x+1,y-1))) {
			neighbors.add(getPixel(x+1,y-1)); //northeast
		}
		if (x>=1 && isAvailable(getPixel(x-1,y))) {
			neighbors.add(getPixel(x-1,y)); //west
		}
		if (x<=width-2 && isAvailable(getPixel(x+1,y))) {
			neighbors.add(getPixel(x+1,y)); //east
		}
		if (x>=1 && y<=height-2 && isAvailable(getPixel(x-1,y+1))) {
			neighbors.add(getPixel(x-1,y+1)); //southwest
		}
		if (y<=height-2 && isAvailable(getPixel(x,y+1))) {
			neighbors.add(getPixel(x,y+1)); //south
		}
		if (x<=width-2 && y<=height-2 && isAvailable(getPixel(x+1,y+1))) {
			neighbors.add(getPixel(x+1,y+1)); //southeast
		}
		
		Pixel[] n=new Pixel[neighbors.size()];
		neighbors.toArray(n);
		return n;
	}
	
	public boolean isAvailable(Pixel p) {
		if (!p.color.equals("wall black") && !visitedPixels.contains(p)/* && !path.contains(p)*/) return true;
		return false;
	}
	
	public void printArray(Pixel[] p) {
		for (int i=0;i<p.length;i++) {
			System.out.println((i+1)+". pixel ("+p[i].x+","+p[i].y+") has color "+p[i].color);
		}
	}
	
	public void printList(List<Pixel> list) {
		Pixel thisP;
		for (int i=0;i<list.size();i++) {
			thisP=list.get(i);
			System.out.println((i+1)+". ("+thisP.x+","+thisP.y+") with cost "+costs[thisP.y][thisP.x]);
		}
	}
	
	public Pixel getPixel(int x, int y) {
		return map[y][x];
	}
	
	public void findAPath() {
		Pixel current=start;
		Pixel thisN;
		Pixel pixelWithMinCost;
		
		//fill costs with all infinity except start=0
		for (int i=0;i<width;i++) for (int j=0;j<height;j++) {
			if (getPixel(i,j).equals(start)) costs[j][i]=0;
			else costs[j][i]=999999;
		}
		
		while (!current.equals(end)) {
			//assign costs to all neighbors
			Pixel[] neighbors=getNeighbors(current);
			///System.out.println("cost so far is "+costs[current.y][current.x]);
			for (int i=0;i<neighbors.length;i++) {
				thisN=neighbors[i];
				///System.out.println("cost to move to ("+thisN.x+","+thisN.y+") is "+thisN.cost);
				if (costs[current.y][current.x]+thisN.cost < costs[thisN.y][thisN.x]) {
					costs[thisN.y][thisN.x]=costs[current.y][current.x]+thisN.cost;
					
					int j=0;
					for (;j<activePixels.size();j++) {
						Pixel thisP=activePixels.get(j);
						if (costs[thisP.y][thisP.x]>=costs[thisN.y][thisN.x]) {
							///System.out.println("("+thisN.x+","+thisN.y+") goes to index "+j);
							break;
						}
					}
					activePixels.add(j, thisN);
					///if (j!=0) System.out.println("j = "+j+" but size = "+activePixels.size());
					///System.out.println("("+thisN.x+","+thisN.y+") is now number "+(j+1)+" in the list! size="+activePixels.size());
				}
			}
			visitedPixels.add(current);
			
			//print active pixels
			/**System.out.println("\nNow printing activePixels:");
			printList(activePixels);
			System.out.println();
			
			//print visited pixels
			System.out.println("Now printing visitedPixels:");
			printList(visitedPixels);
			System.out.println();*/
			
			//find active cell with smallest cost
			pixelWithMinCost=activePixels.get(0);
			///System.out.println("pixel with min cost is ("+pixelWithMinCost.x+","+pixelWithMinCost.y+")");
			
			current=pixelWithMinCost;
			activePixels.remove(current);
			///System.out.println("("+current.x+","+current.y+") is now current");
		}
		
		System.out.println("\nNow tracing back... ");
		traceBack();
	}
	
	public void traceBack() {
		Pixel current=end;
		Pixel pixelWithMinCost;
		activePixels=new ArrayList<Pixel>();
		visitedPixels=new ArrayList<Pixel>();
		
		System.out.println("total cost of path is "+costs[end.y][end.x]);
		while (!current.equals(start)) {
			//get neighbors
			Pixel[] neighbors=getNeighbors(current);
			
			//find smallest cost in neighbors
			pixelWithMinCost=neighbors[0];
			for (int i=0;i<neighbors.length;i++) {
				Pixel currentPixel=neighbors[i];
				if (costs[currentPixel.y][currentPixel.x]<costs[pixelWithMinCost.y][pixelWithMinCost.x]) {
					pixelWithMinCost=currentPixel;
				}
			}
			///System.out.println("pixel with min cost is ("+pixelWithMinCost.x+","+pixelWithMinCost.y+")");
			visitedPixels.add(current);
			
			current=pixelWithMinCost;
			///System.out.println("("+current.x+","+current.y+") is now current");
			current.setColor(666);
		}
	}
	
	public void mapToImage(String filename) {
		BufferedImage newImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
			String pixelColor=map[y][x].color;
			if (pixelColor.equals("end red")) newImage.setRGB(x,y,0xFF0000);  //red end!
			else if (pixelColor.equals("start green")) newImage.setRGB(x,y,0x00FF00); //green start!
			else if (pixelColor.equals("wall black")) newImage.setRGB(x,y,0x00000);  //black walls :D
			else if (pixelColor.equals("path blue")) newImage.setRGB(x,y,0x1E90FF);  //trace path blueee
			else if (pixelColor.equals("water blue")) newImage.setRGB(x,y,0x0000FF);	 //waterrrrr
			else newImage.setRGB(x,y,0xFFFFFF);	//EVERYTHING ELSE IS WHITEEEE	
		}
		
		try {
			File outFile=new File(filename);
			ImageIO.write(newImage, "png", outFile);
		} catch (Exception e) {
			System.out.println("couldn't turn map into image "+filename);
		}
	}
}
