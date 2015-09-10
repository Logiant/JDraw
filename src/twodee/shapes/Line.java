package twodee.shapes;

import java.awt.Color;

import canvas.JCanvas;

public class Line extends Shape{
	
	public Point[] points;
	int currentPt = 0;
		
	public Line(Point a, Point b, Color color) {
		super(color);
		points = new Point[2];
		points[0] = a;
		points[1] = b;
	}
	
	public Line(Point a, Point b) {
		this(a, b, Color.white);
	}
	
	public Line() {
		this(Color.white);
	}
	
	public Line(Color color) {
		super(color);
		points = new Point[3];
	}
	
	public boolean addPoint(Point p) {
		points[currentPt] = p;
		currentPt ++;
		return (currentPt = currentPt % 2) == 0;
	}

	@Override
	public void draw(JCanvas canvas) {
		int x0 = points[0].x; int y0 = points[0].y;
		int x1 = points[1].x; int y1 = points[1].y;
		int dx = Math.abs(x1-x0); float dy = Math.abs(y1-y0);
		
		if (dx == 0) { //vertical line
			drawVertical(canvas, x0, y0, y1);
		} else if (dy == 0) { //horizontal line
			drawHorizontal(canvas, x0, x1, y0);
		} else if (dx == dy) { //45 degree line
			drawDiagonal(canvas, x0, y0, dx);
		} else { //special algorithm
			drawBresen(canvas, x0, x1, y0, y1);
		}
	}
	
	
	private void drawVertical(JCanvas canvas, int x, int y0, int y1) {
		int sign = y1>y0 ? 1 : -1;
		for (int y = y0; y != y1 + sign; y += sign) {
			canvas.setPixel(x, y, color);
		}
	}
	
	private void drawHorizontal(JCanvas canvas, int x0, int x1, int y) {
		int sign = x1>x0 ? 1 : -1;
		for (int x = x0; x != x1 + sign; x += sign) {
			canvas.setPixel(x, y, color);
		}
	}
	
	private void drawDiagonal(JCanvas canvas, int x0, int y0, int dx) {
		int sign = dx>0 ? 1 : -1;
		for (int i = 0; i < dx*sign; i++) {
			canvas.setPixel(x0+i*sign, y0+i*sign, color);
		}
	}
	
	private void drawBresen(JCanvas canvas, int x0, int x1, int y0, int y1) {
		int dx = x1-x0; int dxSign = dx>0 ? 1 : -1;
		int dy = y1-y0; int dySign = dy>0 ? 1 : -1;
		boolean xMajor = dx*dxSign > dy*dySign; //are we on the x major axis?
		int dMajor = Math.max(dx*dxSign, dy*dySign); //get the major axis (largest)
		
		float slope = (float)(dy*dySign)/(float)(dx*dxSign); //slope
		if (!xMajor) {
			slope = 1/slope; //invert slope if we're y major
		}
		
		float error = slope-1;
		int minor = 0;
		
		
		
		for (int major = 0; major <= dMajor; major++) {
			error += slope;
			if (xMajor) {
				canvas.setPixel(x0 + major*dxSign, y0 + minor*dySign, color);
			} else {
				canvas.setPixel(x0 + minor*dxSign, y0 + major*dySign, color);
			}		
			if (error > 0) {
				minor ++;
				error --;
			}
		}
		
		
	}
	
	
}
