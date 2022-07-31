package secao_19_GenericsSetMap.entities;

public class Circle implements Shape{
	private double radius;

	public Circle(double radius) {		
		this.radius = radius;
	}

	public double area() {
		return Math.PI * radius * radius;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}
