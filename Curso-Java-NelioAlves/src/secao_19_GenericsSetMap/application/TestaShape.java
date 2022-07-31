package secao_19_GenericsSetMap.application;

import java.util.ArrayList;
import java.util.List;

import secao_19_GenericsSetMap.entities.Circle;
import secao_19_GenericsSetMap.entities.Rectangle;
import secao_19_GenericsSetMap.entities.Shape;

public class TestaShape {

	public static void main(String[] args) {
		List<Shape> myShapes = new ArrayList<>();
		myShapes.add(new Rectangle(3.0, 2.0));
		myShapes.add(new Circle(2.0));
		
		List<Circle> myCircles = new ArrayList<>();
		myCircles.add(new Circle(2.0));
		myCircles.add(new Circle(3.0));
		
		System.out.println("Tota area: " + totalArea(myShapes));
		System.out.println();
		System.out.println("Tota area: " + totalArea(myCircles));
	}
	
	public static double totalArea(List<? extends Shape> list) {
		/*	List com tipo Coringa delimitado, nao permite adcionar elementos na lista	
		 * 	list.add(new Circle(2.0));
		 */
		double sum = 0.0;
		for (Shape shape : list) {
			sum += shape.area();
		}
		
		return sum;
	}

}

