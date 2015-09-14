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
	List<Pixel> path;
	Pixel[][] map;
	double[][] costs;
	
	public Map(String filename) {
		imageToMap(filename); //create the map whoohoo! also sets width and height
		
		costs=new double[height][width];
		activePixels=new ArrayList<Pixel>();
		visitedPixels=new ArrayList<Pixel>();
		path=new ArrayList<Pixel>();	
	}
	
	public void imageToMap(String filename) {
		File inFile=new File(filename);
		
		try {
			BufferedImage image=ImageIO.read(inFile);
			width=image.getWidth();
			height=image.getHeight();
			map=new Pixel[height][width];
			
			for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
				Color c=new Color(image.getRGB(x,y),true);
				int r=c.getRed();
				int g=c.getGreen();
				int b=c.getBlue();
				
				Pixel temp=new Pixel(x,y);
				temp.setColor(r);				
				map[y][x]=temp;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong D:");
		}
	}
	
	public Pixel[] getNeighbors(Pixel current) {
		int x=current.x;
		int y=current.y;
		List<Pixel> neighbors=new ArrayList<Pixel>();
		
		if (x>=1 && y>=1 && isAvailable(getPixel(x-1,y-1))) {
			neighbors.add(getPixel(x-1,y-1)); //northwest
		} else if (x==0 && y==0 && isAvailable(getPixel(width-1,height-1))) {
			neighbors.add(getPixel(width-1,height-1));
		}
		if (y>=1 && isAvailable(getPixel(x,y-1))) {
			neighbors.add(getPixel(x,y-1)); //north
		} else if (y==0 && isAvailable(getPixel(x,height-1))) {
			neighbors.add(getPixel(x,height-1));
		}
		if (x<=width-2 && y>=1 && isAvailable(getPixel(x+1,y-1))) {
			neighbors.add(getPixel(x+1,y-1)); //northeast
		} else if (x==width-1 && y==0 && isAvailable(getPixel(0,height-1))) {
			neighbors.add(getPixel(0,height-1));
		}
		if (x>=1 && isAvailable(getPixel(x-1,y))) {
			neighbors.add(getPixel(x-1,y)); //west
		} else if (x==0 && isAvailable(getPixel(width-1,y))) {
			neighbors.add(getPixel(width-1,y));
		}
		if (x<=width-2 && isAvailable(getPixel(x+1,y))) {
			neighbors.add(getPixel(x+1,y)); //east
		} else if (x==width-1 && isAvailable(getPixel(0,y))) {
			neighbors.add(getPixel(0,y));
		}
		if (x>=1 && y<=height-2 && isAvailable(getPixel(x-1,y+1))) {
			neighbors.add(getPixel(x-1,y+1)); //southwest
		} else if (x==0 && y==height-1 && isAvailable(getPixel(width-1,0))) {
			neighbors.add(getPixel(width-1,0));
		}
		if (y<=height-2 && isAvailable(getPixel(x,y+1))) {
			neighbors.add(getPixel(x,y+1)); //south
		} else if (y==height-1 && isAvailable(getPixel(x,0))) {
			neighbors.add(getPixel(x,0));
		}
		if (x<=width-2 && y<=height-2 && isAvailable(getPixel(x+1,y+1))) {
			neighbors.add(getPixel(x+1,y+1)); //southeast
		} else if (x==width-1 && y==height-1 && isAvailable(getPixel(0,0))) {
			neighbors.add(getPixel(0,0));
		}
		
		Pixel[] n=new Pixel[neighbors.size()];
		neighbors.toArray(n);
		return n;
	}
	
	public boolean isAvailable(Pixel p) {
		if (!visitedPixels.contains(p) && !path.contains(p)) return true;
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
	
	public void findAPath(Pixel start, Pixel end) {
		Pixel current=start;
		Pixel thisN;
		Pixel pixelWithMinCost;
		
		//fill costs with all infinity except start=0
		for (int i=0;i<width;i++) for (int j=0;j<height;j++) {
			if (getPixel(i,j).equals(start)) costs[j][i]=0;
			else costs[j][i]=999999;
		}
		
		while (visitedPixels.size()!=height*width) {
			//assign costs to all neighbors
			Pixel[] neighbors=getNeighbors(current);
				
			for (int i=0;i<neighbors.length;i++) {
				thisN=neighbors[i];
				///System.out.println("cost to move to ("+thisN.x+","+thisN.y+") is "+thisN.cost);
				if (costs[current.y][current.x]+current.getCost(thisN) < costs[thisN.y][thisN.x]) {
					costs[thisN.y][thisN.x]=costs[current.y][current.x]+current.getCost(thisN);
					
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
		traceBack(start, end);
	}
	
	public void traceBack(Pixel start, Pixel end) {
		Pixel current=end;
		Pixel pixelWithRightCost;
		activePixels=new ArrayList<Pixel>();
		visitedPixels=new ArrayList<Pixel>();
		
		System.out.println("total cost of path is "+costs[end.y][end.x]);
		while (!current.equals(start)) {
			///System.out.println("current pixel is ("+current.x+","+current.y+")");
			//get neighbors
			Pixel[] neighbors=getNeighbors(current);
			
			//find correctest cost in neighbors
			pixelWithRightCost=neighbors[0];
			for (int i=0;i<neighbors.length;i++) {
				pixelWithRightCost=neighbors[i];
				if (costs[current.y][current.x]==costs[pixelWithRightCost.y][pixelWithRightCost.x]+pixelWithRightCost.getCost(current)) {
					break;
				}
			}
			
			path.add(current);			
			current=pixelWithRightCost;
		}
	}
	
	public void colorPath() {
		for (int i=0;i<path.size();i++) {
			path.get(i).setColor(666);
		}
	}
	
	public void mapToImage(String filename) {
		BufferedImage newImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
			int pixelColor=map[y][x].color;
			if (pixelColor==666) newImage.setRGB(x,y,0x1E90FF);  //blue path!
			else newImage.setRGB(x,y,0x010101*pixelColor);	//EVERYTHING ELSE IS WHITEEEE	
		}
		
		try {
			File outFile=new File(filename);
			ImageIO.write(newImage, "png", outFile);
		} catch (Exception e) {
			System.out.println("couldn't turn map into image "+filename);
		}
	}
}
