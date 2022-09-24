/*
 Cheukman Zhou
 This program is used to convert the amount from Renminbi to Canadian Dollar
 Version 1.0
 January 24, 2022
*/

public class MoneyConvertRenminbiToCAD extends MoneyConvertCADToCAD {
	//Constructor
	public MoneyConvertRenminbiToCAD(double amount) {
		super(amount);
	}
	
	
	/*
	 Method: convert
	 Description: The Method will convert the amount from Renminbi to Canadian Dollar
	 Parameters: None
	 Returns:
	 	amount stores the converted amount
	*/
	
	public double convert() {
		amount = amount * 0.2; //Converts the amount
		
		return amount;
	}
}
