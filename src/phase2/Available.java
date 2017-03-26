package phase2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Available {

	// good check, mod try
	public boolean addAvilable(String login, int h_id, int p_id, double price_per_night, Statement stmt) {
		if (!check(h_id, login, stmt)) {
			System.out.println("You can only add available to your THs");
			return false;
		}
		String sql = "insert into Available (h_id, p_id, price_per_night) " + "VALUES ('" + h_id + "', '" + p_id
				+ "', '" + price_per_night + "');";

		int success = 0;
		try {
			success = stmt.executeUpdate(sql);

			if (success > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Unable to add availabe, the error message has been shown here: " + e.getMessage());
		}
		return false;
	}

	private boolean check(int h_id, String login, Statement stmt) {
		boolean b = false;
		String sql = "select * from TH " + "where " + "h_id = " + h_id + " and " + "login = '" + login + "';";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				b = true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (!rs.isClosed() && rs != null)
					rs.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		}

		return b;
	}

	// check good
	public ArrayList<String> getAvilable(int h_id, int p_id, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();

		String sql = "select * from Available " + "where h_id = " + h_id + " and " + "p_id = " + p_id + ";";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result.add(rs.getString("h_id") + "\t" + rs.getString("p_id") + "\t" + rs.getString("price_per_night"));
			}
		} catch (Exception e) {
			System.err.println("Unable to get availabe, the error message has been shown here: " + e.getMessage());
		} finally {
			try {
				if (!rs.isClosed() && rs != null)
					rs.close();
			} catch (Exception e) {
				System.err.println("Unable to close result set, the error message has been shown here: " + e.getMessage());
			}
		}
		return result;
	}

}