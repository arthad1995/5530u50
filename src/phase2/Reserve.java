package phase2;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Reserve {
	public boolean addReserve( String login,int h_id, int cost,Date from, Date to,
			Date reserve_date,Statement stmt) {
		String sql = "select p.from, p.to from Period p, Avaiable a where"
				+ " a.h_id = " + h_id + " a.p_id = p.p_id"+";";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);	

			while (rs.next()) {
				if(from.before(rs.getDate("from"))||to.after(rs.getDate("to"))){
					return false;
				}
	 			
	 		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		int success = 0;
		 sql = "insert into Reserve (login, h_id,cost,from, to, reserve_date) "
					+ "VALUES ('" + login + "', '" + h_id + "', '"
				+ cost   + "', '" + from + "', '"+to + "', '" + reserve_date+"');";

		
		try {
			success = stmt.executeUpdate(sql);

			if (success > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	public ArrayList<Date[]> getReserveDate(int r_id, String login,Statement stmt) {
		ArrayList<Date[]> result = new ArrayList<Date[]>();
		Date[] arr = new Date[2];
		if(r_id!=-1){ 
			String sql = "select * from Reserve " +
					  "where r_id ='" +r_id +"'";
			ResultSet rs = null;
			try{
			rs = stmt.executeQuery(sql);		
	 		while (rs.next()) {
	 			arr[0] = rs.getDate("from");
	 			arr[1] = rs.getDate("to");
	 			result.add(arr);
	 		}
			}
			catch(Exception e) {
		 		System.err.println(e.getMessage());
		 	}
		}
		else{
			String sql = "select * from Reserve r" +
					  "where" +"r.login  =" + login;
			ResultSet rs = null;
			try{
			rs = stmt.executeQuery(sql);		
	 		while (rs.next()) {
	 			arr[0] = rs.getDate("from");
	 			arr[1] = rs.getDate("to");
	 			result.add(arr);
	 		}
			}
			catch(Exception e) {
		 		System.err.println(e.getMessage());
		 	}
		}
		return result;
	}
	
	public ArrayList<String> getReserve(int r_id, String login,Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		if(r_id!=-1){ 
			String sql = "select * from Reserve " +
					  "where r_id ='" +r_id +"'";
			ResultSet rs = null;
			try{
			rs = stmt.executeQuery(sql);		
	 		while (rs.next()) {
	 			result.add(rs.getString("login")+"\t" +  rs.getString("h_id")+"\t"
	 		 			+rs.getString("cost")+"\t"+ rs.getString("from")+"\t"+ rs.getString("to")
	 		 			+ rs.getString("reserve_date"));
	 		}
			}
			catch(Exception e) {
		 		System.err.println(e.getMessage());
		 	}
		}
		else{
			String sql = "select * from Reserve r" +
					  "where" +"r.login  =" + login;
			ResultSet rs = null;
			try{
			rs = stmt.executeQuery(sql);		
	 		while (rs.next()) {
	 			result.add(rs.getString("login")+"\t" +  rs.getString("h_id")+"\t"
	 			+rs.getString("cost")+"\t"+ rs.getString("from")+"\t"+ rs.getString("to")
	 			+ rs.getString("reserve_date"));
	 		}
			}
			catch(Exception e) {
		 		System.err.println(e.getMessage());
		 	}
		}
		return result;
	}
}