package mandelBrotSetViz;


public class App {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		View v = new View();
		v.draw();
		
		while(true){
			if(StdDraw.mousePressed()){
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();
				v.zoom(x,y);
			}	
		}	
	}

}
