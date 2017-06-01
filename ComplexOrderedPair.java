package mandelBrotSetViz;

public class ComplexOrderedPair {
	 final double x;
	 final double y;
	
	/*
	 * Complex ordered pair, z, is represented as (x,y).
	 * In normal complex notation z = x + iy
	 */
	
	public ComplexOrderedPair(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public ComplexOrderedPair add(ComplexOrderedPair a, ComplexOrderedPair b){
		return new ComplexOrderedPair(a.x + b.x, a.y + b.y);
	}
	
	public ComplexOrderedPair square(ComplexOrderedPair z){
		// good ole foil
		
		double a = (z.x * z.x) - (z.y * z.y);
		double b = (z.x * z.y) + (z.x * z.y);
		return new ComplexOrderedPair(a, b);
	}
	
	public double magnitude(ComplexOrderedPair c){
		
		return Math.sqrt( (c.x*c.x) + (c.y*c.y) );
	}
	
	
	public int numIterations(){
		ComplexOrderedPair z = new ComplexOrderedPair(0, 0);
		
		int i=0;
		for(; i<=255; i++){
			
			if(magnitude(z) > 2){
				return i;
			}
			z = add(square(z), this);
		}
		
		return i;
	}
	
	public String toString(){
		return this.x + " + " + this.y + "i";
	}

}
