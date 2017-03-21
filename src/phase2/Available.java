
package phase2;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class Available {

	public boolean addAvilable( int h_id, int p_id, double price_per_night,Statement stmt) {
		String sql = "insert into Visit (h_id, p_id, price_per_night) " 
	+ "VALUES ('" + h_id + "', '" + p_id + "', '"
				+ price_per_night  + "');";

		int success = 0;
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
	public ArrayList<String> getAvilable(int h_id, int p_id,Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		 
			String sql = "select * from Avilable " +
					  "where h_id ='" +h_id + " and " + "p_id = " +p_id+ "'";
			ResultSet rs = null;
			try{
			rs = stmt.executeQuery(sql);		
	 		while (rs.next()) {
	 			result.add(rs.getString("h_id")+"\t" +  rs.getString("p_id")+"\t"
	 		 			+rs.getString("price_per_night"));
	 		}
			}
			catch(Exception e) {
		 		System.err.println(e.getMessage());
		 	}
		
		
		return result;
	}
	
}