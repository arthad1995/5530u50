package phase2;

import java.text.SimpleDateFormat;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.ReverbType;

public class tester {
	public static void main(String[] args) throws Exception{
		Connector c = new Connector();
		Users us = new Users();
		TH th = new TH();
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
		
		ff.addFavorite("dfgvx", "sodu", c.stmt);
		ff.addFavorite("dfgvx", "hotel1", c.stmt);
		ff.addFavorite("ll", "sodu", c.stmt);
		ff.addFavorite("kikwe", "hotel1", c.stmt);
		
		
		
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
