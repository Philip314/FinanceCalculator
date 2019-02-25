import java.text.DecimalFormat;

public class FormulaMaths {
	
	public FormulaMaths() {
		
	}
	
	//set decimal format for results
	DecimalFormat df = new DecimalFormat("#.##");
	
	public double futureValue (int amount, double interestRate, int years) {
		double toReturn = amount * Math.pow(1+(interestRate/100), years);
		
		return Double.parseDouble(df.format(toReturn));
	}
	
	public double presentValue (int amount, double interestRate, int years) {
		double toReturn = amount / Math.pow(1+(interestRate/100), years);
		
		return Double.parseDouble(df.format(toReturn));
	}
	
	public double divide (double ratioOne, double ratioTwo) {
		
		return Double.parseDouble(df.format(ratioOne/ratioTwo));
	}
	
	public double minus (double ratioOne, double ratioTwo) {
		
		return ratioOne-ratioTwo;
	}
	
	
	

}
