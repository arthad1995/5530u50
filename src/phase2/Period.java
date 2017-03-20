package phase2;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Period {

	public Period() {
	}

	public void addPeriod(int p_id, Date from, Date to, Statement st) {
		String sql = "insert into Period (p_id, from, to) Values (" + p_id + ", '" + from + "', '" + to + "');";
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

}
