package phase2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Feedback {

	public boolean giveFeedback(int h_id, String login, String text, int score, Date date, Statement stmt) {

		// new connection??????
		if (!checkGive(h_id, login, stmt)) {
			return false;
		}
		String sql = "insert into Feedback (h_id, login, text, score, fbdate) " + "VALUES (" + h_id + ", '" + login
				+ "', '" + text + "', '" + score + "', '" + date + "');";

		int rs = 0;

		try {
			rs = stmt.executeUpdate(sql);

			if (rs > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	private boolean checkGive(int h_id, String login, Statement stmt) {

		String sql = "select * from Feedback where h_id = " + h_id + " and login= '" + login + "';";

		int rs = 0;

		try {
			rs = stmt.executeUpdate(sql);

			if (rs > 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<String> getTHFeedback(int h_id, String amount, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();

		// Rating query
		if (amount.equals("all")) {
			String sql = "select distinct f.text, f.score from Rates r, Feedback f"
					+ " where f.fid IN (select AVG(rating) as average,f_id from " + "Feedback where h_id = " + h_id
					+ ")" + " order by average;";

			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("text") + "\t" + rs.getString("score");
					result.add(temp);
				}
				return result;
			} catch (Exception e) {

				System.err.println(e.getMessage());
			}
			return result;
		} else {
			int count = Integer.parseInt(amount);
			String sql = "select distinct f.text, f.score from Rates r, Feedback f"
					+ " where f.fid IN (select AVG(rating) as average,f_id from " + "Feedback where h_id = " + h_id
					+ ")" + " order by average" + " limit " + count + ";";

			ResultSet rs = null;
			// System.out.println("executing "+sql);
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp;
					temp = rs.getString("text") + "\t" + rs.getString("score");
					result.add(temp);
				}
				return result;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			return result;
		}
	}

	public boolean rateFeedback(String login, int f_id, int rating, Statement stmt) {

		if (!checkRate(f_id, login, stmt)) {
			return false;
		}
		String sql = "INSERT INTO Rates (login, f_id, rating) " + "VALUES ('" + login + "', " + f_id + ", " + rating
				+ ");";
		int rs = 0;
		try {
			rs = stmt.executeUpdate(sql);

			if (rs > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<String> getrate(int h_id, String amount, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();

		// Rating query
		if (amount.equals("all")) {
			String sql = "select distinct r.login, AVG(r.rating) as averageRating from Rates r" + " group by (r.login)"
					+ " order by averageRating" + ";";

			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("text") + "\t" + rs.getString("score");
					result.add(temp);
				}
				return result;
			} catch (Exception e) {

				System.err.println(e.getMessage());
			}
			return result;
		} else {
			int count = Integer.parseInt(amount);
			String sql = "select distinct f.text, f.score from Rates r, Feedback f"
					+ " where f.fid IN (select AVG(rating) as average,f_id from " + "Feedback where h_id = " + h_id
					+ ")" + " order by average" + " limit " + count + ";";

			ResultSet rs = null;
			// System.out.println("executing "+sql);
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp;
					temp = rs.getString("text") + "\t" + rs.getString("score");
					result.add(temp);
				}
				return result;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			return result;
		}
	}

	private boolean checkRate(int f_id, String login, Statement stmt) {

		String sql = "select * from Rates r, Feedback f  where f.login= '" + login + "' and f.f_id = " + f_id + ";";

		int rs = 0;

		try {
			rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<String> gettopUserful(String amount, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();

		// Rating query
		if (amount.equalsIgnoreCase("all")) {
			String sql = "select distinct r.login, AVG(r.rating) as averageRating from Rates r" + " group by (r.login)"
					+ " order by averageRating" + ";";

			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("text") + "\t" + rs.getString("score");
					result.add(temp);
				}
				return result;
			} catch (Exception e) {

				System.err.println(e.getMessage());
			}
			return result;
		} else {
			String sql = "select distinct r.login, AVG(r.rating) as averageRating from Rates r" + " group by (r.login)"
					+ " order by averageRating limit " + Integer.parseInt(amount) + ";";

			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("text") + "\t" + rs.getString("score");
					result.add(temp);
				}
				return result;
			} catch (Exception e) {

				System.err.println(e.getMessage());
			}
			return result;
		}

	}
}