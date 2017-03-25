package phase2;

import java.text.SimpleDateFormat;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.sound.sampled.ReverbType;

public class tester {
	public static void main(String[] args) throws Exception{
		Connector c = new Connector();
		Users us = new Users();
		TH th = new TH();

	
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
//			String l = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(10));
//			String name = "hotel" + i;
//			String city = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(6));
//			String phone = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(12));
//			String state = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(5));
//			String add = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(20));
//			String keyword = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(20));
//			String url = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(20));
//			String category = generateString(r, "qwertyuioopasdfghjklzxcvbnm", r.nextInt(10));
			String login = "User" + r.nextInt(11);
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date start = df.parse("05-01-2013");
			java.sql.Date sqpdate = new java.sql.Date(start.getTime());
			
			java.util.Date end = df.parse("05-01-2013");
			java.sql.Date enddate = new java.sql.Date(end.getTime());
			java.sql.Date currentdate = new java.sql.Date(new Date().getTime());
			//f.giveFeedback(r.nextInt(6), login, "well", r.nextInt(11),currentdate , c.stmt);
			
			ArrayList<String> rrr =t.filter("category", 0, 10000, "condo", "st", "desc", c.stmt);
			
			ArrayList<String> result = t.filter("address", 0, 10000, "okjk", "st", "desc", c.stmt);
			if (result.isEmpty()) {
				result = new ArrayList<String>(rrr);
				
			}
	
		//	System.out.println(result.get(0));
			for (int j = 0 ; j < result.size();j++) {
				if (!rrr.contains(result.get(j))) {
					result.remove(result.get(j));
				}
			}
			
			
			for(String token:result){
				System.out.println(token);
			}
			t.updateTH("dana", 6, "ff", "d", c.stmt);
//		boolean b;
//			if(r.nextInt(2)==0){
//				b=false;
//			}
//			else
//				b=true;
//			u.trustRecording("User"+r.nextInt(10), "User"+r.nextInt(10), b, c.stmt);
			
//			for(String []arr:u.getTrustedUsers(c.stmt, "2")){
//				System.out.println(arr[0] + "\t" + arr[1] + "\t" + arr[2]);
//			}
//			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
//			java.util.Date start = df.parse("05-01-2013");
//			java.sql.Date sqpdate = new java.sql.Date(start.getTime());
//			
//			java.util.Date end = df.parse("05-01-2013");
//			java.sql.Date enddate = new java.sql.Date(end.getTime());
//			java.sql.Date currentdate = new java.sql.Date(new Date().getTime());
		
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

		Reserve rs = new Reserve();
		Period pr = new Period();
		Available av = new Available();

		

		String date = "01-02-2013";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date start = sdf.parse(date);
		java.sql.Date sqlStart = new java.sql.Date(start.getTime());
		
		String end = "01-03-2013";
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date endtime = sdf2.parse(end);
		java.sql.Date sqlend = new java.sql.Date(endtime.getTime());
		ArrayList<String[]> reserveList = new ArrayList<String[]>();
		
//		reserveList = th.getHighestRate("2", c.stmt);
//		//reserveList = th.getMostExpensiveTHs("2", c.stmt);
//		for(String[] s : reserveList)
//			System.out.println(s[0] + "\t"+ s[1] + "\t" + s[2]);
		
//		reserveList = fv.get(1, 1, c.stmt);
	
//		for(String s : reserveList)
//			System.out.println(s);
//		

		//pr.addPeriod(sqlStart, sqlend, c.stmt);
		
	//	av.addAvilable(0, 1, 10000, c.stmt);
//		
//		th.updateTH(2, "category", "condo", c.stmt);
//		th.updateTH(3, "category", "house", c.stmt);
//		th.updateTH(4, "category", "bedroom", c.stmt);
//		th.updateTH(5, "category", "condo", c.stmt);
//		th.updateTH(6, "category", "house", c.stmt);
		//th.updateTH(4, "category", "bedroom", c.stmt);s
		
		
//		Error Code: 1055. Expression #1 of SELECT list is not in GROUP BY 
//		clause and contains nonaggregated column '5530db50.t.h_id' which is not 
//		functionally dependent on columns in GROUP BY clause; this is 
//		incompatible with sql_mode=only_full_group_by	0.0027 sec

		
	//	rs.addReserve("fried", 0, 10000, sqlStart, sqlend, sqlStart, c.stmt);
		
//		Visit v = new Visit();
//		v.addVisit(sqlStart, sqlend, 1, c.stmt);
		ArrayList<Date[]> dateList = new ArrayList<Date[]>();
		
//		reserveList = rs.getReserve(1, "fried", c.stmt);
//		System.out.println(reserveList.get(0));
		
//		dateList = rs.getReserveDate(1, "fried",c.stmt);
//		System.out.println((dateList.get(0))[0].toString() + (dateList.get(0))[1]);
		//String login = "fried";
//		String sql = "select distinct Users.name from Favorites" + " left jpin Users" + " on Favorites.login = Users.login"
//				+ " where Favorites.h_id IN" + " (select f1.h_id FROM Favorites f1, Favorites f2"
//				+ " select f1.login != f2.login AND f1.login = '" + login + "' AND f1.h_id = f2.h_id)"
//				+ " AND Favorites.login != '" + login + "'";
		
//		String sql = "select distinct u.name from Users u, Favorites f1 where exists(select f2.login from Favorites where f1.login = '"
//				+ login + "' and f1.login != f2.login and f1.h_id = f2.h_id;";

		

	//	System.out.println(sql);
		
		Favorites ff = new Favorites();
//		 reserveList = ff.getFavorite("dfgvx", c.stmt);
//		 for(String[] s : reserveList)
//			 System.out.println(s[0] + s[1]);
		
//		ff.addFavorite("fried", "sodu", c.stmt);
//		ff.addFavorite("dwt", "sodu", c.stmt); 	
//		ff.addFavorite("fried", "hotel0", c.stmt);
//		ff.addFavorite("dwt", "hotel1", c.stmt);
//		ff.addFavorite("fried", "hotel1", c.stmt);
		
//		ff.addFavorite("dfgvx", "sodu", c.stmt);
//		ff.addFavorite("dfgvx", "hotel1", c.stmt);
//		ff.addFavorite("ll", "sodu", c.stmt);
//		ff.addFavorite("kikwe", "hotel1", c.stmt);
		
		
		
	//	reserveList = us.getTwoDegreeSeperation("ll", "kikwe", c.stmt);
//		if(reserveList.isEmpty())
//		{
//			System.out.println("SDSDSDSDSDSD");
//		}
//		for(String s : reserveList)
//			System.out.println(s);
		//ff.delete("fried", "1", c.stmt);
		//System.out.println(pr.getP_id(sqlStart, sqlend, c.stmt));
//		dateList = pr.getPeriod(1, c.stmt);
//		System.out.println((dateList.get(0))[0].toString() + (dateList.get(0))[1]);
		
		//th.updateTH(0, "address", "slc", c.stmt);
//		ArrayList<String[]> ass = th.getMostExpensiveTHs("ALL", c.stmt);
//		
//	//	us.trustRecording("fried", "salt", true, c.stmt);
////		ArrayList<String[]> aee = us.getTrustedUsers(c.stmt, "ALL");
////		System.out.println(aee.get(0)[0] + aee.get(0)[1] + aee.get(0)[2]);
//		for(String[] s : ass)
//			System.out.println(s[1]);
		System.out.println("Done");
	}

}
