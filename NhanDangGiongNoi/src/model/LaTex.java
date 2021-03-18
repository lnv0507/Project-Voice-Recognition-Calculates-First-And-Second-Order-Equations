package model;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class LaTex {
	public LaTex() {
	}
	public TeXIcon getIconLaTex(String latex) {
		String math = latex;
		try {
			TeXFormula formula = new TeXFormula(math);
			return formula.createTeXIcon(TeXConstants.ALIGN_RIGHT, 20);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
	
	

}