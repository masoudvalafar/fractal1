package firstExample;

import java.awt.Point;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayExample {

	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final double THRESHOLD  = 1;
	
	public void start() {
		
		Julia julia = new Julia(WIDTH, HEIGHT);
		
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 
		double[][] board = new double[WIDTH][HEIGHT];
		
		while (!Display.isCloseRequested()) {
			Long before = System.nanoTime() / 1000000;
			
			board = julia.update(pollInput());
			System.out.println(System.nanoTime() / 1000000 - before);
			
			// Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 

			
			for (int i = 0; i < WIDTH; i++){
				for (int j = 0; j < HEIGHT; j++){
					if (board[i][j] > (THRESHOLD * THRESHOLD)) GL11.glColor3f(1f, 1f, 1f);
					else GL11.glColor3d( board[i][j] / (THRESHOLD * THRESHOLD), board[i][j] / (THRESHOLD * THRESHOLD), 1 - board[i][j] / (THRESHOLD * THRESHOLD));
					GraphicsHelper.plotPixel(i, j);
				}
			}
			
			Display.update();
		}
		
		Display.destroy();
	}
	
	public Point pollInput() {
		
		return new Point(Mouse.getX(), Mouse.getY());
		
	}
		
	public static void main(String[] argv) {
		DisplayExample displayExample = new DisplayExample();
		displayExample.start();
	}
	
}
