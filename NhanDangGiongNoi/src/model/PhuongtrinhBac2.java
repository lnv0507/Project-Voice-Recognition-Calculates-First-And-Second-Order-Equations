package model;

import java.awt.Color;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

public class PhuongtrinhBac2 {
	private int a, b, c, d;

	public PhuongtrinhBac2(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public String getResult() {
		String ketqua = new String();
		if (a == 0) {
			ketqua += ("Giải phương trình: \n");
			ketqua += ("\t" + b + "x + " + c + " = " + d + "\n");
			ketqua += ("Chuyển số tự do sang bên phải rồi thu gọn, ta được: \n");
			int m = d - c;
			ketqua += ("\t" + b + "x = " + m + "\n");
			ketqua += ("Chia hai vế cho " + b + ", suy ra: \n");
			double re = ((double) m / (double) b);
			ketqua += ("\t" + "x = " + re);

		}
		if (a != 0) {
			double delta = Math.pow(b, 2) - 4 * a * (c - d);

			ketqua += ("Giải phương trình: \n");

			ketqua += ("\t" + a + "x² + " + b + "x + " + c + " = " + d + "\n");
			ketqua += ("Giải △, ta được: \n");
			int m = d - c;
			ketqua += ("\t" + "△ = " + b + "²" + "-4 ✖ " + a + " ✖ " + m + " = " + delta + "\n");

			if (delta > 0) {
				double x1 = Math.ceil(((-b + Math.sqrt(delta)) / (2 * a)) * 100) / 100;
				double x2 = Math.ceil(((-b - Math.sqrt(delta)) / (2 * a)) * 100) / 100;

				ketqua += ("Do △ > 0 nên phương trình có 2 nghiệm phân biệt: \n");
				ketqua += ("\t x₁ = " + "(" + (-b) + " +√" + delta + ") : (2 ✖ " + a + ") = " + x1 + "\n");
				ketqua += ("\t x₂ = " + "(" + (-b) + " -√" + delta + ") : (2 ✖ " + a + ") = " + x2 + "\n");

			}
			if (delta == 0) {
				double x = Math.ceil((-b / (2 * a)) * 100) / 100;

				ketqua += ("Do △ = 0 nên phương trình có nghiệm kép: \n");
				ketqua += ("\t x₁ = x₂ = " + (-b) + ":(2" + a + ") = " + x + "\n");

			}
			if (delta < 0) {
				ketqua += ("Do △ < 0 nên phương trình vô nghiệm \n");

			}

		}
		return ketqua;
	}

	public PhuongtrinhBac2() {
		super();
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public static void main(String[] args) {

		
	}

}