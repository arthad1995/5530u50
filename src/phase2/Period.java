package phase2;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class Period {

	public Period() {
	}

	/** good check, mod try */
	public void addPeriod(Date from, Date to, Statement st) {
		String sql = "insert into Period (Period.from, Period.to) Values ('" + from + "', '" + to + "');";
		// System.out.println(sql);
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	//good check
	public ArrayList<Date[]> getPeriod(int p_id, Statement st) {
		Date[] period;

		ArrayList<Date[]> periodList = new ArrayList<Date[]>();

		String sql = "SELECT Period.from, Period.to from Period where p_id = " + p_id;

		int count = 0;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				period = new Date[2];
				period[0] = rs.getDate("from");
				period[1] = rs.getDate("to");
				periodList.add(period);
				count++;
			}
			if (count == 0){
				rs.close();
				return periodList;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return periodList;
	}

	// Good check
	public int getP_id(Date from, Date to, Statement st) {

		int p_id = -1;
		// ArrayList<Integer> p_idList = new ArrayList<Integer>();
		String sql = "SELECT p.p_id from Period p where p.from = '" + from + "' and p.to = '" + to + "';";
		// System.out.println(sql);
		int count = 0;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				p_id = rs.getInt("p_id");
				// p_idList.add(rs.getInt("p_id"));
				count++;
			}
			if (count == 0) {
				rs.close();
				return p_id;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return p_id;
	}
}
