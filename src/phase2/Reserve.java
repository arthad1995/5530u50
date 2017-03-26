package phase2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Reserve {

	/** good check, mod try */
	public boolean addReserve(String login, int h_id, int cost, Date from, Date to, Date reserve_date, Statement stmt) {
		String sql = "select p.from, p.to from Period p, Available a where" + " a.h_id = " + h_id
				+ " and a.p_id = p.p_id" + ";"; // correct
		Date from1 = new Date();
		Date to1 = new Date();
		ResultSet rs = null;
		boolean check = false;
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				from1 = rs.getDate("from");
				to1 = rs.getDate("to");

				if ((from1.after(rs.getDate("from")) && to1.before(rs.getDate("to")))
						|| (from1.equals(rs.getDate("from")) && to1.equals(rs.getDate("to")))) {
					check = true;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (!check) {
			// System.out.println("outttttttttttt");
			return false;
		}

		sql = "select * from Reserve r where r.h_id = " + h_id + " and r.login = '" + login + "' and r.from = '" + from + "' and r.to = '"
				+ to + "';";

		rs = null;
		int count = 0;
		try {
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				count++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null & !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		if (count > 0) {
			return false;
		}

		int success = 0;
		sql = "insert into Reserve (Reserve.login, Reserve.h_id, Reserve.cost, Reserve.from, Reserve.to, Reserve.reserve_date) "
				+ "VALUES ('" + login + "', '" + h_id + "', '" + cost + "', '" + from + "', '" + to + "', '"
				+ reserve_date + "');";
		// System.out.println(sql);

		try {
			success = stmt.executeUpdate(sql);

			if (success <= 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	// check good
	public ArrayList<Date[]> getReserveDate(int r_id, String login, Statement stmt) {
		ArrayList<Date[]> result = new ArrayList<Date[]>();
		Date[] arr = new Date[2];
		if (r_id != -1) {
			String sql = "select * from Reserve " + "where r_id ='" + r_id + "'";
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					arr[0] = rs.getDate("from");
					arr[1] = rs.getDate("to");
					result.add(arr);
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
			String sql = "select * from Reserve r" + "where" + "r.login  =" + login;
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					arr[0] = rs.getDate("from");
					arr[1] = rs.getDate("to");
					result.add(arr);
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

	// check good
	public ArrayList<String> getReserve(int r_id, String login, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		if (r_id != -1) {
			String sql = "select * from Reserve " + "where r_id ='" + r_id + "'";
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result.add(rs.getString("login") + "\t" + rs.getString("h_id") + "\t" + rs.getString("cost") + "\t"
							+ rs.getString("from") + "\t" + rs.getString("to") + "\t" + rs.getString("reserve_date"));
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
			String sql = "select * from Reserve r" + "where" + "r.login  =" + login;
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result.add(rs.getString("login") + "\t" + rs.getString("h_id") + "\t" + rs.getString("cost") + "\t"
							+ rs.getString("from") + "\t" + rs.getString("to") + "\t" + rs.getString("reserve_date"));
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