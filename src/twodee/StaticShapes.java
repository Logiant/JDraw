package twodee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	boolean fill;

	Color color = Color.white;

	Shape currentShape;

	//functions to add - Clear, Shape Select, Fill, Color Select, Save, fill shape

	JButton clearButton, saveButton, shapeToggle, fillShape, colorToggle;
	JPanel functionPanel;


	public StaticShapes() {
		setupUI();
		drawingMode = DrawMode.POINT;		
	}

	private void setupUI() {
		JFrame frame = new JFrame();
		canvas = new JCanvas(width, height);
		canvas.addMouseListener(new MouseEventHandler());

		frame.setSize(width, height + 65);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("JCanvas Drawing");
		frame.setLayout(new BorderLayout());
		frame.add(canvas);
		setupFunctionPanel();
		frame.add(functionPanel, BorderLayout.SOUTH);

		frame.setVisible(true);	
	}

	private void setupFunctionPanel() {
		functionPanel = new JPanel();
		clearButton = new JButton("Clear");
		saveButton = new JButton("Save");
		shapeToggle = new JButton("Point");
		fillShape = new JButton("Don't Fill");
		colorToggle = new JButton("White");

		clearButton.addActionListener(new ButtonListener());
		saveButton.addActionListener(new ButtonListener());
		shapeToggle.addActionListener(new ButtonListener());
		fillShape.addActionListener(new ButtonListener());
		colorToggle.addActionListener(new ButtonListener());

		functionPanel.setLayout(new GridLayout(1,3));
		functionPanel.add(clearButton);
		functionPanel.add(saveButton);
		functionPanel.add(shapeToggle);
		functionPanel.add(fillShape);
		functionPanel.add(colorToggle);


	}

	void mouseEvent(int x, int y) {
		boolean draw = false;
		switch(drawingMode) {
		default:
		case POINT:
			currentShape = new Point(x,y, color); //create the point 
			draw = true;
			break;
		case LINE:
			if (!(currentShape instanceof Line)) {
				currentShape = new Line(color);
			}
			Line line = (Line)currentShape;
			draw = line.addPoint(new Point(x,y, color));
			break;
		case TRIANGLE:
			if (!(currentShape instanceof Triangle)) {
				currentShape = new Triangle(color);
			}
			Triangle tri = (Triangle)currentShape;
			tri.fill = fill;
			draw = tri.addPoint(new Point(x,y, color));
			break;
		}
		if (draw) {
			currentShape.SetColor(color);
			currentShape.draw(canvas);
		}
	}


	public static void main(String[] args) {
		new StaticShapes();
	}


	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Clear")) {
				canvas.fillCanvas(Color.black);
			} else if (cmd.equals("Point")) {
				shapeToggle.setText("Line");
				drawingMode = DrawMode.LINE;
			} else if (cmd.equals("Line")) {
				shapeToggle.setText("Triangle");
				drawingMode = DrawMode.TRIANGLE;
			} else if (cmd.equals("Triangle")) {
				shapeToggle.setText("Point");
				drawingMode = DrawMode.POINT;
			} else if (cmd.equals("Don't Fill")) {
				fillShape.setText("Fill");
				fill=true;
			} else if (cmd.equals("Fill")) {
				fillShape.setText("Don't Fill");
				fill=false;
			}  else if (cmd.equals("White")) {
				colorToggle.setText("Red");
				color=Color.red;
			}  else if (cmd.equals("Red")) {
				colorToggle.setText("Green");
				color=Color.green;
			} else if (cmd.equals("Green")) {
				colorToggle.setText("Blue");
				color=Color.blue;
			} else if (cmd.equals("Blue")) {
				colorToggle.setText("White");
				color=Color.white;
			} else if (cmd.equals("Save")) {
				File outputfile = new File("saved.png");
				try {
					ImageIO.write(canvas.canvas, "png", outputfile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
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
