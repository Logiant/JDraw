package twodee.shapes;

import java.awt.Color;

import canvas.JCanvas;

public class Point extends Shape {
	public int x;
	public int y;
	
	public Point(int x, int y, Color color) {
		super(color);
		this.x = x; this.y = y;
	}
	
	public Point(int x, int y) {
		this(x, y, Color.white);
	}

	@Override
	public void draw(JCanvas canvas) {
		canvas.setPixel(x, y, color);
	}
}
