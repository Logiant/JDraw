package twodee.shapes;

import java.awt.Color;

import canvas.JCanvas;
import util.Matrix4;

public abstract class Shape {

	protected Color color;
	
	public Shape(Color c) {
		color = c;
	}
	
	public void SetColor(Color c) {
		this.color = c;
	}
	
	public abstract void transform(Matrix4 trans);
	
	public abstract void draw(JCanvas canvas);
	
}
