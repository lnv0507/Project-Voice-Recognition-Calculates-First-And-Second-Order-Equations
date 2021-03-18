package model;

public class PhuongtrinhBac1 {
	private int a;
	private int b;
	private int c;
	public PhuongtrinhBac1(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public PhuongtrinhBac1() {
	}


	public String getResult() {
		String ketqua = new String();
		ketqua += "Giải Phương Trình: \n";
		ketqua += ("\t" + a + "x + " + b + " = " + c  + "\n");
		ketqua += ("Chuyển số tự do sang bên phải rồi thu gọn, ta được: \n");
		int m = c-b;
		ketqua += ("\t" + a + "x = " + m + "\n");
		ketqua += ("Chia hai vế cho " + a + ", suy ra: \n");
		double re =   ((double)m /(double)a);
		ketqua += ("\t" + "x = "  + re);
		return ketqua;
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

	public static void main(String[] args) {
		System.out.println("x²");
	}
	
	
	
	
	
	

}
