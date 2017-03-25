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
			login = "User" + i;
//		boolean b;
//			if(r.nextInt(2)==0){
//				b=false;
//			}
//			else
//				b=true;
//			u.trustRecording("User"+r.nextInt(10), "User"+r.nextInt(10), b, c.stmt);
			
			for(String []arr:u.getTrustedUsers(c.stmt, "2")){
				System.out.println(arr[0] + "\t" + arr[1] + "\t" + arr[2]);
			}
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date start = df.parse("05-01-2013");
			java.sql.Date sqpdate = new java.sql.Date(start.getTime());
			
			java.util.Date end = df.parse("05-01-2013");
			java.sql.Date enddate = new java.sql.Date(end.getTime());
			java.sql.Date currentdate = new java.sql.Date(new Date().getTime());
		
			Available a = new Available();
			Period p = new Period();
			//f.getTHFeedback(3, "all", c.stmt);
			//f.giveFeedback(r.nextInt(7), "y", "good", r.nextInt(10), currentdate, c.stmt);
			//f.getTHFeedback(3, "5", c.stmt);
		//	f.rateFeedback("tb", r.nextInt(26), r.nextInt(3), c.stmt);
			int h = 5;
		//	p.addPeriod(sqpdate, enddate, c.stmt);
		//	a.addAvilable(h, 47, 898.0, c.stmt);
		//	res.addReserve(login, h, 484, sqpdate, enddate, currentdate, c.stmt);
			Visit v = new Visit();
	//		v.addVisit(sqpdate, enddate, 2, c.stmt);
	//		t.newTH(login, name, city, state, add, 1995, phone, keyword, r.nextInt(2000), 
	//				url, "house", c.stmt);
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
