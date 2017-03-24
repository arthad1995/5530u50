package phase2;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class tester {

	public static void main(String[] arge) throws SQLException, ParseException{
		String login = "fried";
		TH th = new TH();
		Connector c = null;
		try {
			c = new Connector();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("there");
		Feedback f= new Feedback();
		java.sql.Date sqlStartDate = new java.sql.Date(new Date().getTime());  
//		f.giveFeedback(0, "fried", "very good", 10, sqlStartDate, c.stmt);
		TH t = new TH();
		Users u = new Users();
		Reserve res = new Reserve();
		ArrayList loginlist = new ArrayList<String>();
		for(int i = 0; i < 1; i++){
			Random r = new Random();
			String l = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(10));
			String name = "hotel" + i;
			String city = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(6));
			String phone = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(12));
			String state = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(5));
			String add = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(20));
			String keyword = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(20));
			String url = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(20));
			String category = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(10));
			
		//	u.newUser(login, name, "user", phone, add, "k", c.stmt);
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date start = df.parse("01-05-2013");
			java.sql.Date sqpdate = new java.sql.Date(start.getTime());
			
			java.util.Date end = df.parse("01-07-2013");
			java.sql.Date enddate = new java.sql.Date(start.getTime());
			java.sql.Date currentdate = new java.sql.Date(new Date().getTime());
			res.addReserve(login, r.nextInt(6), 484, sqpdate, enddate, currentdate, c.stmt);
//			t.newTH(login, name, city, state, add, 1995, phone, keyword, r.nextInt(2000), 
//					url, category, c.stmt);
		}
		
		//f.getTHFeedback(0, "5", c.stmt);
	//	System.out.println("\nbad input\n");
	//	f.getTHFeedback(10, "5", c.stmt);
	//	f.getTHFeedback(0, "5", c.stmt);
		
		//f.getTHFeedback(10, "5", c.stmt);
		
		
//		ArrayList<String> arr =th.filter("category", 0, 0, "house", "p", "ASC", c.stmt);
//		System.out.println("here");
//		System.out.println(arr.isEmpty());
//		System.out.println(arr.get(0));
		
	}
	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
}
