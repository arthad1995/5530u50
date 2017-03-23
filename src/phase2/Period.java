package phase2;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Period {

	public Period() {
	}

	public void addPeriod( Date from, Date to, Statement st) {
		String sql = "insert into Period (from, to) Values (" + ", '" + from + "', '" + to + "');";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {

		} catch (Exception e) {

		}
	}

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

			if (count == 0)
				return periodList;
		} catch (SQLException e) {

		}

		return periodList;

	}

	public int getP_id(Date from, Date to, Statement st) {
		
		int p_id = -1;
	//	ArrayList<Integer> p_idList = new ArrayList<Integer>();

		String sql = "SELECT Period.from, Period.to from Period where from = " + from + " to = " + to;

		int count = 0;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				p_id = rs.getInt("p_id");
				//p_idList.add(rs.getInt("p_id"));
				count++;
			}

			if (count == 0)
				return p_id;
		} catch (SQLException e) {

		}

		return p_id;

	}
}
