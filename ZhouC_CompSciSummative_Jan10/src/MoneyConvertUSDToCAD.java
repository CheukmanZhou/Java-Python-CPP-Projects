/*
 Cheukman Zhou
 This program is used to convert the amount from US Dollar to Canadian Dollar
 Version 1.0
 January 24, 2022
*/

public class MoneyConvertUSDToCAD extends MoneyConvertCADToCAD {
	//Constructor
	public MoneyConvertUSDToCAD(double amount) {
		super(amount);
	}


	/*
	 Method: convert
	 Description: The Method will convert the amount from US Dollar to Canadian Dollar
	 Parameters: None
	 Returns:
	 	amount stores the converted amount
	*/
	
	public double convert() {
		amount = amount * 1.26; //Converts the amount
		
		return amount;
	}
}
