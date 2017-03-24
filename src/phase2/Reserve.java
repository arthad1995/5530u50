package phase2;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Reserve {
	public boolean addReserve( String login,int h_id, int cost,Date from, Date to,
			Date reserve_date,Statement stmt) {
		String sql = "select p.from, p.to from Period p, Avaiable a where"
				+  " a.h_id = '" + h_id + "' and a.p_id = p.p_id"+";";
		Date from1 = new Date();
		Date to1 = new Date();
		ResultSet rs = null;
		boolean check = false;
		try {
			rs = stmt.executeQuery(sql);	

			while (rs.next()) {
				from1 = rs.getDate("from");
				to1 = rs.getDate("to");
				if(from.after(rs.getDate("from"))&&to.before(rs.getDate("to"))){
					check = true;
				}
	 			
	 		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(!check){
			return false;
		}
		
		 sql = "select * from Reserve r where"
				+  " r.login = '" + login + "' and r.from = '" + from +"' r.to = '" +  to+"';";
		
		 rs = null;
			int count = 0;
		try {
			rs = stmt.executeQuery(sql);	
		
			while (rs.next()) {
				count++;
	 			
	 		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(count>0){
			return false;
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
					  "where r_id =" +r_id +"";
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
					  "where" +"r.login  ='" + login+"';";
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
					  "where r_id =" +r_id +";";
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