//Tara Moses
//Class for CP3 Assignment 5: Pixel
//February 9, 2014

public class Pixel {
	String color;
	int cost;
	int x;
	int y;
	
	public Pixel(int xParam, int yParam, int nParam) {  //constructor
		x=xParam;
		y=yParam;
		setColor(nParam);
	}
	
	public Pixel(int xParam, int yParam) {
		x=xParam;
		y=yParam;
	}
	
	public boolean equals(Pixel newPixel) {
		if (newPixel.x!=x) return false;
		else if (newPixel.y!=y) return false;
		else if (!newPixel.color.equals(color)) return false;
		return true;
	}
	
	//setters
	public void setColor(int n) {
		if (n==-5) color="start green";
		else if (n==-8) color="end red";
		else if (n==0) color="wall black";
		else if (n==2) color="water blue";
		else if (n==666) color="path blue";
		else color="white";
		setCost();
	}
	
	public void setCost() {
		//System.out.println("color equals "+color);
		if (color.equals("water blue")) cost=2;
		else if (!color.equals("wall black")) cost=1;
	}
}
