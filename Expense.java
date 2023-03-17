import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Expense {
	int id;
	double price;
	String desc;
	Date date;
	String category;
	
	Expense () {
		id = 0;
		price = 0.0;
		desc = "";
		category="";
	}
	
	Expense(int id,double p , String de , String d, String c) throws ParseException {
		price = p > 0.0 ? p : 0.0;
		desc = de;
		category=c;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse(d);
	}

	double getPrice(){
		return price;
	}
	String getCategory() {
		return category;
	}
	
	String info () {
		return id + "-" + desc + "--" + price + "$ --" + date.toString() + "\n";
	}
	String getdesc () {
		return desc;
	}
	
	int getId () {
		return id;
	}
	
	void print () {
		System.out.println(desc + "--" + date + "--" + price);
	}
	
}
