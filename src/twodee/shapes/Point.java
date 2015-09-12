package twodee.shapes;

import java.awt.Color;

import canvas.JCanvas;
import util.Matrix4;
import util.Vector3;

public class Point extends Shape implements Comparable<Point> {
	public float x;
	public float y;
	public float z;
	
	public Point(int x, int y, int z, Color color) {
		super(color);
		this.x = x; this.y = y; this.z = z;
	}
	
	public Point(int x, int y, Color color) {
		this(x, y, 0, color);
	}
	
	public Point(int x, int y) {
		this(x, y, Color.white);
	}

	@Override
	public void draw(JCanvas canvas) {
		canvas.setPixel((int)(x + 0.5), (int)(y + 0.5), color);
	}

	@Override
	public int compareTo(Point p) {
		//negative is less, 0 is same, positive is greater
		return (int)(y - p.y+0.5);
	}
	
	@Override
	public void transform(Matrix4 transform) {
		Vector3 point = new Vector3(x, y, 0);
		point = Matrix4.transform(transform, point);
		this.x = point.x; this.y = point.y; this.z = point.z;
		
	}
	
}
