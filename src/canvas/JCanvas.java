package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JCanvas extends JPanel {

	protected static final long serialVersionUID = 1L;

	public BufferedImage canvas;
	public int width = 50;
	public int height = 50;

	Timer timer;
	int delay = 20; //ms between repaints

	public  JCanvas(int width, int height) {
		this.width = width;
		this.height = height;

		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		fillCanvas(Color.BLACK);


		this.setVisible(true);
		timer = new Timer(delay, new RedrawAction());
		timer.start();
	}

	public void fillCanvas(Color c) {
		int color = c.getRGB();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				canvas.setRGB(i, j, color);
			}
		}
	}

	public void floodFill(int x0, int y0, int color) {

		Queue<int[]> q  = new LinkedList<int[]>();
		int[] pos = {x0, y0};
		int overwriteColor = canvas.getRGB(x0, y0);

		if (overwriteColor == color) {
			return;
		}

		q.add(pos);
		while(!q.isEmpty()) {
			pos = q.poll();
			int x = pos[0]; int y = pos[1];

			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					if (x+dx >= 0 && x+dx < width && y+dy >= 0 && y+dy < height && 
							!(dx == 0 && dy == 0) && !(dx==dy) && !(-dx == dy) && canvas.getRGB(x+dx, y+dy) == overwriteColor) {
						canvas.setRGB(x+dx, y+dy, color);
						int[] temp = {x+dx, y+dy};
						q.add(temp);
					}
				}
			}			
		}
	}

	public void setPixel(int x, int y, Color c) {
		if (x >= 0 && y >= 0 && x < width && y <height) {
			canvas.setRGB(x, y, c.getRGB());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(canvas, 0, 0,  null);
	}

	private class RedrawAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}

}
