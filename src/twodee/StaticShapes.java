package twodee;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import canvas.JCanvas;
import twodee.shapes.Line;
import twodee.shapes.Point;
import twodee.shapes.Shape;
import twodee.shapes.Triangle;

enum DrawMode{
	POINT, LINE, TRIANGLE;
}

public class StaticShapes {

	JCanvas canvas;
	
	int width = 800;
	int height = 600;
	
	DrawMode drawingMode;
	
	Shape currentShape;
	
	public StaticShapes() {
		JFrame frame = new JFrame();
		canvas = new JCanvas(width, height);
		canvas.addMouseListener(new MouseEventHandler());
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("JCanvas Drawing");
		frame.setVisible(true);

		frame.add(canvas);
		
		drawingMode = DrawMode.TRIANGLE;
		
	}
	
	
	void mouseEvent(int x, int y) {
		boolean draw = false;
		switch(drawingMode) {
		default:
		case POINT:
			currentShape = new Point(x,y); //create the point 
			draw = true;
			break;
		case LINE:
			if (!(currentShape instanceof Line)) {
				currentShape = new Line();
			}
			Line line = (Line)currentShape;
			draw = line.addPoint(new Point(x,y));
			break;
		case TRIANGLE:
			if (!(currentShape instanceof Triangle)) {
				currentShape = new Triangle();
			}
			Triangle tri = (Triangle)currentShape;
			draw = tri.addPoint(new Point(x,y));
			break;
		}
		if (draw) {
			currentShape.draw(canvas);
		}
	}
	
	
	public static void main(String[] args) {
		new StaticShapes();
	}
	
	private class MouseEventHandler implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {/*Do Nothing*/}
		@Override
		public void mouseEntered(MouseEvent e) {/*Do Nothing*/}
		@Override
		public void mouseExited(MouseEvent e) {/*Do Nothing*/}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseEvent(e.getX(), e.getY());
			} else {
				canvas.floodFill(e.getX(), e.getY(), Color.BLUE.getRGB());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {/*Do Nothing*/}
	}
}
