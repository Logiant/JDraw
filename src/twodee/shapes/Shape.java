package twodee.shapes;

import java.awt.Color;

import canvas.JCanvas;

public abstract class Shape {

	protected Color color;
	
	public Shape(Color c) {
		color = c;
	}
	
	public abstract void draw(JCanvas canvas);
	
}
