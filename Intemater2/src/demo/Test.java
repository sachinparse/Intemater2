package demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	
	public static void main(String[] args) throws ParseException {

		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String strDate = sdf.format(cal.getTime());
	    System.out.println("Current date in String Format: "+strDate);
	    System.out.println(new Date());
	    
	}

}
