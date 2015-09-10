package twodee.shapes;

import java.awt.Color;

import canvas.JCanvas;

public class Triangle extends Shape{

	public Point[] points;
	int currentPt = 0;
	
	public Triangle() {
		this(Color.white);
	}
	
	public Triangle(Color color) {
		super(color);
		points = new Point[3];
	}
	
	public Triangle(Point p0, Point p1, Point p2) {
		this(p0, p1, p2, Color.white);
	}
		
	public Triangle(Point p0, Point p1, Point p2, Color color) {
		super(color);
		points[0] = p0; points[1] = p1; points[2] = p2;
	}
	
	public boolean addPoint(Point p) {
		points[currentPt] = p;
		currentPt ++;
		return (currentPt = currentPt % 3) == 0;
	}
	
	@Override
	public void draw(JCanvas canvas) {
		Line l1 = new Line(points[0], points[1], color);
		Line l2 = new Line(points[0], points[2], color);
		Line l3 = new Line(points[1], points[2], color);
		l1.draw(canvas);
		l2.draw(canvas);
		l3.draw(canvas);

	}
}
