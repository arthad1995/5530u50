package phase2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		//pr.addPeriod(sqlStart, sqlend, c.stmt);
		
	//	av.addAvilable(0, 1, 10000, c.stmt);
		
		
		
	//	rs.addReserve("fried", 0, 10000, sqlStart, sqlend, sqlStart, c.stmt);
		
//		Visit v = new Visit();
//		v.addVisit(sqlStart, sqlend, 1, c.stmt);
//		ArrayList<Date[]> dateList = new ArrayList<Date[]>();
//		ArrayList<String> reserveList = new ArrayList<String>();
//		reserveList = rs.getReserve(1, "fried", c.stmt);
//		System.out.println(reserveList.get(0));
		
//		dateList = rs.getReserveDate(1, "fried",c.stmt);
//		System.out.println((dateList.get(0))[0].toString() + (dateList.get(0))[1]);
		
		
		Favorites ff = new Favorites();
		
		ff.addFavorite("fried", "sodu", c.stmt);
		//ff.delete("fried", "1", c.stmt);
		
		
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
