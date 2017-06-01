package mandelBrotSetViz;

import java.awt.Color;
import mandelBrotSetViz.StdDraw;

public class View {
	private final int height = 400;
	private final int width = 400;
	private double minX = 2;
	private double maxX = -2;
	private double minY = 2;
	private double maxY = -2;
	private ComplexOrderedPair[][] set = new ComplexOrderedPair[width][height];
	private int g = (int)(Math.random() * 255);
	private int b = (int)(Math.random() * 255);
	private ComplexOrderedPair center = new ComplexOrderedPair(0, 0);


	public View(){

		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenRadius(.005);
		setScale();

	}

	public void setScale(){
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY);
	}
	
	public void draw(){
		
		StdDraw.filledRectangle(0, 0, width, height);
		setComplexPlane();

		plot();

		StdDraw.show();
	}


	private void setComplexPlane(){
		double minX = this.minX;
		double minY = this.minY;
		double maxX = this.maxX;
		double maxY = this.maxY;
		double stepX = (maxX - minX) / width;
		double stepY = (maxY - minY) / height;

		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				this.set[x][y] = new ComplexOrderedPair(minX + x*stepX, minY+y*stepY);
			}
		}
	}


	int numZooms = 0;
	double zoomFactor = 1.1;
	//x and y are the coordinates where the user clicked and where the zoom should be centered
	public void zoom(double x, double y){
		numZooms++;
		System.out.println("x:" + x + ", y:" + y);
		center(x,y);
		
		//zoom in according to the zoom factor that increases as we go further
		this.maxX /= zoomFactor;
		this.maxY /= zoomFactor;
		this.minX /= zoomFactor;
		this.minY /= zoomFactor;

		if(numZooms % 10 == 0){
			//zoomFactor = zoomFactor * 1.1;
		}

		// at the end of the zoom we gotta draw again
		StdDraw.clear();
		setScale();
		draw();

	}


	private void plot(){
		StdDraw.setPenRadius(0);
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				ComplexOrderedPair cop = this.set[x][y];
				int numIterations = cop.numIterations();
				if (numIterations < 254) {
					Color c = new Color(g, numIterations*2 % 254, b);
					StdDraw.setPenColor(c);
					StdDraw.point(cop.x, cop.y);
				}else{

				}
				StdDraw.setPenColor();
			}
		}

	}

	public void center(double x, double y) {
		// TODO Auto-generated method stub
		double deltaX = Math.abs(x - this.center.x);
		double deltaY = Math.abs(y - this.center.y);
		
		this.minX += deltaX;
		this.maxX += deltaX;
		this.minY += deltaY;
		this.maxY += deltaY;
		
		double newCenterX = this.center.x + x;
		double newCenterY = this.center.y + y;
		
		this.center = new ComplexOrderedPair(newCenterX, newCenterY);
		
	}

}