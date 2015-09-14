//Tara Moses
//Class for CP3 Assignment 5: Pixel
//February 9, 2014

public class Pixel {
	int color;
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
		else if (newPixel.color!=color) return false;
		return true;
	}
	
	//setters
	public void setColor(int n) {
		color=n;
	}
	
	public double getCost(Pixel destPixel) {
		//System.out.println("color equals "+color);
		double d=Math.sqrt((x-destPixel.x)*(x-destPixel.x)+(y-destPixel.y)*(y-destPixel.y));
		int h=Math.abs(destPixel.color-color);
		double cost=d+20*h;
		return cost;
	}
}
