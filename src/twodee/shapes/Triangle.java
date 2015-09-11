package twodee.shapes;

import java.awt.Color;
import java.util.Arrays;

import canvas.JCanvas;

public class Triangle extends Shape{

	public Point[] points;
	int currentPt = 0;
	public boolean fill;
	
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
		
		Arrays.sort(points); //sorts the points by Y value
		
		
		Line l1 = new Line(points[0], points[1], color);
		Line l2 = new Line(points[0], points[2], color);
		Line l3 = new Line(points[1], points[2], color);
		l1.draw(canvas);
		l2.draw(canvas);
		l3.draw(canvas);
		
		if (fill) {
			fillTop(canvas, l1, l2);
			fillBottom(canvas, l1, l2);
		}
		

	}
	
	private void fillTop(JCanvas canvas, Line l1, Line l2) {
		int x0 = l1.points[0].x; int y0 = l1.points[0].y; //the highest point
		int x1 = l1.points[1].x; int y1 = l1.points[1].y; //second highest point
		int x2 = l2.points[1].x; int y2 = l2.points[1].y; //lowest highest point
		
		int dy = y1-y0;
		
		float slope1 = (float)(x1-x0)/(float)(y1-y0); //inverted slope - we are varying X along Y
		float slope2 = (float)(x2-x0)/(float)(y2-y0); //inverted slope - we are varying X along Y
		float leftSlope = 0;
		float rightSlope = 0;
		
		if (dy*(slope1) < dy * (slope2)) {
			leftSlope = slope1;
			rightSlope = slope2;
		} else {
			leftSlope = slope2;
			rightSlope = slope1;
		}
		//start at (x0, y) and follow the line slope down to fill
		float xLeft = x0; float xRight = x0;
				
		for (int y = y0; y <= y1; y++) { //sweep downward
			for (float x = xLeft; x <= xRight; x++) {
				canvas.setPixel((int)(x+0.5f), y, color);
			}
			xLeft += leftSlope;
			xRight += rightSlope;
		}
	}
	
	private void fillBottom(JCanvas canvas, Line l1, Line l2) {
		int x0 = l1.points[0].x; int y0 = l1.points[0].y; //the highest point
		int x1 = l1.points[1].x; int y1 = l1.points[1].y; //second highest point
		int x2 = l2.points[1].x; int y2 = l2.points[1].y; //lowest highest point
		
		float slope1 = (float)(x2-x1)/(float)(y2-y1); //inverted slope - we are varying X along Y
		float slope2 = (float)(x2-x0)/(float)(y2-y0); //inverted slope - we are varying X along Y
		float leftSlope = 0;
		float rightSlope = 0;
		
		int dy = y2-y1;
		
		if (dy*slope1 > dy*slope2) {
			leftSlope = slope1;
			rightSlope = slope2;
		} else {
			leftSlope = slope2;
			rightSlope = slope1;
		}
		//start at (x2, y2) and follow the line slope up to fill
		float xLeft = x2; float xRight = x2;
		for (int y = y2; y > y1; y--) { //sweep downward
			for (float x = xLeft; x <= xRight; x++) {
				canvas.setPixel((int)(x+0.5f), y, color);
			}
			xLeft -= leftSlope;
			xRight -= rightSlope;
		}
	}
	
}
