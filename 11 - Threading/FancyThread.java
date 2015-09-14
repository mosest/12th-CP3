//Tara Moses
//Class for CP3 Assignment 11: FancyThread
//April 23, 2014

public class FancyThread extends Thread {
	int n;
	boolean positive;
	
	public FancyThread(int n, boolean positive) {
		this.n=n;
		this.positive=positive;
	}
	
	public void run() {
		if (positive) Threading.sum+=4.0/(n*(n+1)*(n+2));
		else Threading.sum-=4.0/(n*(n+1)*(n+2));
	}
}
