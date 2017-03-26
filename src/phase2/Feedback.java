package phase2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Feedback {

	// good check
	public boolean giveFeedback(int h_id, String login, String text, int score, Date date, Statement stmt) {

		// new connection??????
		if (!checkGive(h_id, login, stmt)) {
			System.out.println("You can only give feedback once");
			return false;
		}
		String sql = "insert into Feedback (h_id, login, text, score, fbdate) " + "VALUES (" + h_id + ", '" + login
				+ "', '" + text + "', '" + score + "', '" + date + "');";

		int rs = 0;
		System.out.println(sql);
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

	// good check
	private boolean checkGive(int h_id, String login, Statement stmt) {
		String sql = "select * from Feedback where h_id = " + h_id + " and login= '" + login + "';";
		ResultSet rs = null;
		//System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				rs.close();
				return false;
			} else {
				rs.close();
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	// good check
	public ArrayList<String> getTHFeedback(int h_id, String amount, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> fidList = new ArrayList<String>();
		ArrayList<String> usefuleList = new ArrayList<String>();
		for (String ss : gettopUserful("all", stmt)) {
			usefuleList.add(ss.split("\t")[0]);
		}
		// Rating query
		if (amount.equalsIgnoreCase("all")) {
			String sql = "select * from Feedback f " + " where f.h_id= " + h_id + ";";
			//System.out.println("sql is " +sql);
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("text") + "\t" + rs.getString("fbdate") + "\t" + rs.getString("score") + "\t"
							+ rs.getString("login");

					fidList.add(temp);
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
			for (int i = 0; i < usefuleList.size(); i++) {
				String t = usefuleList.get(i);
				for (int j = 0; j < fidList.size(); j++) {
					if (fidList.get(j).split("\t")[3].equals(t)) {
						result.add(fidList.get(j));
						fidList.remove(j);
					}
				}
			}
			result.addAll(fidList);
			System.out.println("\ntext\tfbdate\t\tscore\tlogin\n");
			for (String s : result) {
				System.out.println(s);
			}
			return result;
		} else {
			int count = Integer.parseInt(amount);
			String sql = "select * from Feedback f " + " where f.h_id= " + h_id + ";";

			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("text") + "\t" + rs.getString("fbdate") + "\t" + rs.getString("score") + "\t"
							+ rs.getString("login");

					fidList.add(temp);
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
			for (int i = 0; i < count && i < usefuleList.size(); i++) {
				String t = usefuleList.get(i);
				for (int j = 0; j < fidList.size(); j++) {
					if (fidList.get(j).split("\t")[3].equals(t)) {
						result.add(fidList.get(j));
						fidList.remove(j);
					}
				}
			}
			if (result.size() < count) {
				for (int i = 0; i < fidList.size() && i < (count - result.size()); i++) {
					result.add(fidList.get(i));
				}
			}
			System.out.println("text\tfbdate\t\tscore\tlogin\n");
			for (String s : result) {
				System.out.println(s);
			}
			return result;
		}
	}

	// good check
	public boolean rateFeedback(String login, int f_id, int rating, Statement stmt) {
		if (!checkRate(f_id, login, stmt)) {
			System.out.println("you can't rate your self");
			return false;
		}
		String sql = "insert into Rate (login, f_id, rating) " + "VALUES ('" + login + "', " + f_id + ", " + rating
				+ ");";
		//System.out.println(sql);
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

	// public ArrayList<String> getrate(int h_id, String amount, Statement stmt)
	// {
	// ArrayList<String> result = new ArrayList<String>();
	//
	// // Rating query
	// if (amount.equals("all")) {
	// String sql = "select login, avg(rating) as average from Rate" + " group
	// by (r.login)"
	// + " order by average desc" + ";";
	//
	// ResultSet rs = null;
	// try {
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// String temp = "";
	// temp = rs.getString("text") + "\t" + rs.getString("score");
	// result.add(temp);
	// }
	// return result;
	// } catch (Exception e) {
	//
	// System.err.println(e.getMessage());
	// }
	// return result;
	// } else {
	// int count = Integer.parseInt(amount);
	// String sql = "select login, avg(rating) as average from Rate" + " group
	// by (r.login)"
	// + " order by average desc" + " limit " + count + ";";
	//
	// ResultSet rs = null;
	// // System.out.println("executing "+sql);
	// try {
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// String temp;
	// temp = rs.getString("login")+"\t"+rs.getString("text") + "\t" +
	// rs.getString("score");
	// System.out.println(temp);
	// result.add(temp);
	// }
	// return result;
	// } catch (Exception e) {
	// System.err.println(e.getMessage());
	// }
	//
	// return result;
	// }
	// }
	// good check
	private boolean checkRate(int f_id, String login, Statement stmt) {

		String sql = "select * from Rate r, Feedback f  where f.login= '" + login + "' and f.f_id = " + f_id + ";";

		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	// good check
	public ArrayList<String> gettopUserful(String amount, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		if (amount.equalsIgnoreCase("all")) {
			String sql = "select login, avg(rating) as average from Rate" + " group by (login)"
					+ " order by average desc" + ";";

			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp = "";
					temp = rs.getString("login") + "\t" + rs.getString("average");
					result.add(temp);
				}
				return result;
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
			return result;
		} else {
			int count = Integer.parseInt(amount);
			String sql = "select login, avg(rating) as average from Rate" + " group by (login)"
					+ " order by average desc" + " limit " + count + ";";

			ResultSet rs = null;
			// System.out.println("executing "+sql);
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String temp;
					temp = rs.getString("login") + rs.getString("average");
					System.out.println(temp);
					result.add(temp);
				}
				return result;
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
			return result;
		}
	}
}