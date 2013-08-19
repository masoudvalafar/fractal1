package firstExample;

import java.awt.Point;
import java.util.Random;

import org.apache.commons.math3.complex.Complex;

public class Julia {

	Random random = new Random();
	private Complex c = new Complex(-0.223, 0.745);
	private double minX = -1.5;
	private double maxX = 1.5;
	private double minY = -1.5;
	private double maxY = 1.5;
	private int width;
	private int height;
	private int iteration = 50;

	public Julia(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public double[][] update(Point point) {

		// c = new Complex(2 * random.nextFloat() - 1, 2 * random.nextFloat() -
		// 1);
		c = new Complex(2 * point.getX() / width - 1, 2 * point.getY() / height - 1);

		double[][] values = new double[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				double a = (double) i * (maxX - minX) / (double) width + minX;
				double b = (double) j * (maxY - minY) / (double) height + minY;

				values[i][j] = isInSet(new Complex(a, b));
			}
		}

		return values;
	}

	private double isInSet(Complex complex) {
		for (int i = 0; i < iteration; i++) {
			complex = complex.pow(2).add(c);
		}

		return complex.getReal() * complex.getReal() + complex.getImaginary()
				* complex.getImaginary();
	}
}
