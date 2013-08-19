package firstExample;

import org.lwjgl.opengl.GL11;

public class GraphicsHelper {

	public static void plotPixel(int x, int y) {
		// just one pixel
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2i(x, y);
		GL11.glVertex2i(x, y + 1);
		GL11.glEnd();
	}

}
