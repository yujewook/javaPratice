package Practice_Class;

import java.math.BigDecimal;

public class ExPratice {
	public static void main(String [] args) {
		double a = 1.0 - 0.1 - 0.1 - 0.1;
		System.out.println("double "+a); //0.7000000000000001
		
		double b = 1.0; 
		double c = 0.1; 
		BigDecimal one = new BigDecimal(Double.toString(b));
        BigDecimal subtract = new BigDecimal(String.valueOf(c));
        BigDecimal value = one.subtract(subtract).subtract(subtract).subtract(subtract);
        String stringValue = value.toString();
        System.out.println("候单矫富 ::::: "+stringValue); // 免仿: "0.7"
		
		BigDecimal one2 = new BigDecimal(1.0);
        BigDecimal subtract2 = new BigDecimal(0.1);
        BigDecimal value2 = one2.subtract(subtract2).subtract(subtract2).subtract(subtract2);
        String stringValue2 = value2.toString();
        System.out.println("候单矫富2 ::::: "+stringValue2);
        
        
        
	}
	
}
