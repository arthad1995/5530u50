package phase2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Visit {

	// check good
	public boolean addVisit(Date from, Date to, int r_id, Statement stmt) {
		Reserve r = new Reserve();
		// new connection????
		ArrayList<Date[]> arr = r.getReserveDate(r_id, "", stmt);
		for (Date[] a : arr) {
			if (a[0].before(from) || a[1].after(to)) {
				return false;
			}
		}

		String sql = "insert into Visit (Visit.from, Visit.to, Visit.r_id) " + "VALUES ('" + from + "', '" + to + "', '"
				+ r_id + "');";

		System.out.println(sql);
		int success = 0;
		// System.out.println("executing "+ sql);
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

	/**
	 * it can accept either vid or login to get visit record 
	 * 
	 * @param vid
	 * @param login
	 * @param stmt
	 * @return
	 */
	//ok
	public ArrayList<String> getVisit(int vid, String login, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		if (vid != -1) {
			String sql = "select * from Visit " + "where vid ='" + vid + "';";
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result.add(rs.getString("login") + "\t" + rs.getString("from") + "\t" + rs.getString("to") + "\t"
							+ rs.getString("h_id"));
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if (rs != null & !rs.isClosed())
						rs.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} else {
			String sql = "select * from Visit v, Reserve r " + " where" + " r.login  = '" + login + "' and r.r_id = v.r_id;";
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result.add(rs.getString("login") + "\t" + rs.getString("from") + "\t" + rs.getString("to") + "\t"
							+ rs.getString("h_id"));
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if (rs != null & !rs.isClosed())
						rs.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return result;
	}

}