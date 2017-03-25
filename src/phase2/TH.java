package phase2;

import java.util.*;
import java.util.Date;
import java.sql.*;

public class TH {

	/** good check, mod try */
	public boolean newTH(String login, String name, String city, String state, String address, int yearbuild,
			String telephone, String keyword, double price, String url, String category, Statement stmt) {
		String sql = "insert into TH (name, address, url, telephone, yearBuilt, price, category,keyword,city,state,login) "
				+ "values ('" + name + "','" + address + "','" + url + "','" + telephone + "','" + yearbuild + "','"
				+ price + "','" + category + "','" + keyword + "','" + city + "','" + state + "','" + login + "');";
		int result = 0;
		try {
			result = stmt.executeUpdate(sql);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			System.err.println(e.getMessage());

		}
		return false;
	}

	/** good check */
	public boolean updateTH(int h_id, String updateField, String updateValue, Statement stmt) {
		String sql = "UPDATE TH " + "set " + updateField + " = '" + updateValue + "'" + " where h_id= " + h_id + ";";

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

	// leave
	public ArrayList<Integer> geth_id(String name, Statement stmt) {

		ArrayList<Integer> resultList = new ArrayList<Integer>();
		String sql = "select h_id from TH " + "where name ='" + name + "'";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("pid") == null || rs.getString("pid").length() == 0
						|| rs.getString("pid").equals(" ")) {
					break;
				}
				resultList.add(Integer.parseInt(rs.getString("pid")));
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return resultList;
	}

	/** check good */
	public ArrayList<String> getCategories(Statement stmt) {
		ArrayList<String> categories = new ArrayList<String>();

		ResultSet rs = null;
		String sql = "select category from TH" + " group by category";

		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				categories.add(rs.getString("category"));
			}
		} catch (SQLException e) {

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return categories;
	}

	// check ok
	public ArrayList<String[]> getPopularTHs(String amount, Statement st) {
		ArrayList<String[]> forReturn = new ArrayList<String[]>();
		ArrayList<String> catagoryList = new ArrayList<String>();
		String[] arr;
		ResultSet rs = null;
		int limit;
		String sql;

		if (amount.equals("ALL")) {
			sql = "select category from TH group by category";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					catagoryList.add(rs.getString("category"));
				}
			} catch (Exception e) {

			}

			for (String s : catagoryList) {
				sql = "select t.h_id, t.name, t.category, count(*) AS VisitCount from TH t, "
						+ "Visit v, Reserve r where t.h_id = r.h_id and t.category = '" + s
						+ "' and r.r_id = v.r_id group by t.h_id, t.name, t.category order by t.category, VisitCount desc";
				rs = null;
				try {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						arr = new String[4];
						arr[0] = String.valueOf(rs.getInt("h_id"));
						arr[1] = rs.getString("name");
						arr[2] = rs.getString("category");
						arr[3] = String.valueOf(rs.getInt("VisitCount"));
						forReturn.add(arr);
					}
					rs.close();
				} catch (SQLException e) {

				}
			}

		} else {
			limit = Integer.parseInt(amount);
			sql = "select category from TH group by category";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					catagoryList.add(rs.getString("category"));
				}
			} catch (Exception e) {

			}

			for (String s : catagoryList) {
				sql = "select t.h_id, t.name, t.category, count(*) AS VisitCount from TH t, "
						+ "Visit v, Reserve r where t.h_id = r.h_id and t.category = '" + s
						+ "' and r.r_id = v.r_id group by t.h_id, t.name, t.category order by t.category, VisitCount desc limit "
						+ limit;
				rs = null;
				try {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						arr = new String[4];
						arr[0] = String.valueOf(rs.getInt("h_id"));
						arr[1] = rs.getString("name");
						arr[2] = rs.getString("category");
						arr[3] = String.valueOf(rs.getInt("VisitCount"));
						forReturn.add(arr);
					}
					rs.close();
				} catch (SQLException e) {

				}
			}
		}
		return forReturn;
	}

	public ArrayList<String[]> getHighestRate(String amount, Statement st) {
		// ArrayList<String[]> result = new ArrayList<String[]>();
		// String[] arr;
		// String sql = "select * from TH t, " + "Feedback f where t.h_id =
		// f.h_id group by t.category"
		// + "having (select AVG(f.score) as AverageRate order by (AverageRate)
		// limit " + amount + ")" + ";";
		// String sql = "select ";
		// ResultSet rs = null;
		// try {
		// rs = stmt.executeQuery(sql);
		// while (rs.next()) {
		// arr = new String[3];
		// arr[0] = rs.getString("name");
		// arr[1] = rs.getString("category");
		// arr[2] = String.valueOf(rs.getFloat("AverageRate"));
		// result.add(arr);
		// }
		// rs.close();
		// } catch (SQLException e) {
		//
		// }
		//
		// return result;
		ArrayList<String[]> forReturn = new ArrayList<String[]>();
		ArrayList<String> catagoryList = new ArrayList<String>();
		String[] arr;
		ResultSet rs = null;
		int limit;
		String sql;
		if (amount.equals("ALL")) {
			sql = "select category from TH group by category";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					catagoryList.add(rs.getString("category"));
				}
			} catch (Exception e) {

			}

			for (String s : catagoryList) {
				sql = "select t.name, t.category, avg(f.score) as AveScore from TH t, Feedback f "
						+ "where t.h_id = f.h_id and t.category = '" + s
						+ "' group by t.name, t.category order by t.category, AveScore desc;";
				rs = null;
				try {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						arr = new String[3];
						arr[0] = rs.getString("name");
						arr[1] = rs.getString("category");
						arr[2] = String.valueOf(rs.getInt("AveScore"));
						forReturn.add(arr);
					}
					rs.close();
				} catch (SQLException e) {

				}
			}

		} else {
			limit = Integer.parseInt(amount);
			sql = "select category from TH group by category";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					catagoryList.add(rs.getString("category"));
				}
			} catch (Exception e) {

			}
			for (String s : catagoryList) {
				sql = "select t.name, t.category, avg(f.score) as AveScore from TH t, Feedback f "
						+ "where t.h_id = f.h_id and t.category = '" + s
						+ "' group by t.name, t.category order by t.category, AveScore desc limit " + limit + ";";
				rs = null;
				try {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						arr = new String[3];
						arr[0] = rs.getString("name");
						arr[1] = rs.getString("category");
						arr[2] = String.valueOf(rs.getInt("AveScore"));
						forReturn.add(arr);
					}
					rs.close();
				} catch (SQLException e) {

				}
			}
		}
		return forReturn;

	}

	public ArrayList<String[]> getSuggestion(String login, int h_id, String amount, Statement st) {
		ArrayList<Integer> suggestionList = new ArrayList<Integer>();
		ArrayList<String[]> tempList = new ArrayList<String[]>();
		String sql = "select distinct r.h_id from Reserve r, Visit v where r.login = '" + login
				+ "' and r.r_id = v.r_id;";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("h_id") != h_id) {
					suggestionList.add(rs.getInt("h_id"));
				}
			}
			rs.close();

		} catch (SQLException e) {

		}
		ArrayList<String[]> popularList = new ArrayList<String[]>();
		List<Integer> getSort = new ArrayList<Integer>();
		tempList = getPopularTHs(amount, st);
		boolean isFull = false;
		for (int i = 0; i < suggestionList.size(); i++) {
			if (isFull)
				break;
			for (int j = 0; j < tempList.size(); j++) {
				int hid = Integer.parseInt((tempList.get(j))[0]);
				if (popularList.size() == suggestionList.size()) {
					isFull = true;
					break;
				} else if (hid == suggestionList.get(i)) {
					popularList.add(tempList.get(j));
					getSort.add(Integer.parseInt((tempList.get(j)[3])));
				}
			}

		}
		Collections.sort(getSort, Collections.reverseOrder());
		ArrayList<String[]> popularLists = new ArrayList<String[]>();
		// int maxVisit = 0;
		if (popularList.size() == getSort.size()) {
			for (int i = 0; i < getSort.size(); i++) {
				for (int j = 0; j < popularList.size(); j++) {
					// maxVisit = getSort.get(i);
					if (Integer.parseInt((popularList.get(j))[3]) == getSort.get(i)) {
						// && Integer.parseInt((popularList.get(j))[3]) ==
						// maxVisit) {
						// getSort.set(i, -1);
						popularLists.add(popularList.get(j));
						popularList.remove(j);
					}
				}
			}
		}
		return popularLists;
	}

	// check good
	public ArrayList<String[]> getMostExpensiveTHs(String amount, Statement st) {
		ArrayList<String[]> forReturn = new ArrayList<String[]>();
		ArrayList<String> categoryList = new ArrayList<String>();
		String[] arr;
		ResultSet rs = null;
		String sql = "";
		if (amount.equals("ALL")) {
			sql = "select category from TH group by category;";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					categoryList.add(rs.getString("category"));
				}
				rs.close();
			} catch (SQLException e) {
			}

			for (String s : categoryList) {
				sql = "select t.name, t.category, AVG(r.cost) AS AverageCost from "
						+ "TH t, Reserve r, Visit v where t.h_id = r.h_id and r.r_id = v.r_id and t.category = '" + s
						+ "' group by t.name, t.category order by t.category, AverageCost desc;";

				rs = null;
				try {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						arr = new String[3];
						arr[0] = rs.getString("name");
						arr[1] = rs.getString("category");
						arr[2] = String.valueOf(rs.getFloat("AverageCost"));
						forReturn.add(arr);
					}
					// rs.close();
				} catch (SQLException e) {

				} finally {
					try {
						if (rs == null || rs.isClosed()) {
							rs.close();
						}
					} catch (Exception e) {

					}
				}
			}

		} else {
			int limit = Integer.parseInt(amount);
			sql = "select category from TH group by category;";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					categoryList.add(rs.getString("category"));
				}
				rs.close();
			} catch (SQLException e) {
			}

			for (String s : categoryList) {
				sql = "select t.name, t.category, AVG(r.cost) AS AverageCost from "
						+ "TH t, Reserve r, Visit v where t.h_id = r.h_id and r.r_id = v.r_id and t.category = '" + s
						+ "' group by t.name, t.category order by t.category, AverageCost desc LIMIT " + limit + ";";

				rs = null;
				try {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						arr = new String[3];
						arr[0] = rs.getString("name");
						arr[1] = rs.getString("category");
						arr[2] = String.valueOf(rs.getFloat("AverageCost"));
						forReturn.add(arr);
					}
					// rs.close();
				} catch (SQLException e) {

				} finally {
					try {
						if (rs != null && !rs.isClosed()) {
							rs.close();
						}
					} catch (Exception e) {

					}
				}
			}
		}
		return forReturn;

	}

	public ArrayList<String> filter(String field, int min, int max, String value, String sort, String increase,
			Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();
		if (field.equals("Price")) {
			ResultSet rs = null;
			String sql = "select * from TH" + " where Price< " + max + " and price< " + min;
			if (sort.equals("p")) {
				sql = sql + " order by " + "Price" + increase + ";";
			} else if (sort.equals("s")) {
				sql = sql + " group by (h_id) having (select h_id, AVG(score) AS average)"
						+ "from Feedback group by h_id)" + "order by average " + increase + ";";
			} else if (sort.equals("st")) {
				sql = "select * from TH th ,Trust t, Users u,Feedback f where t.login2 = u.login and "
						+ "f.login = t.login2" + "Price< " + max + " and price< " + min + " group by (f.h_id) having "
						+ " (select h_id, AVG(score) AS average" + "where sum(t.isTrusted) > 0" + "order by average "
						+ increase + ";";
			}
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result.add(rs.getString("h_id") + "\t" + rs.getString("login") + "\t" + rs.getString("category")
							+ "\t" + rs.getString("address") + "\t" + rs.getString("city") + "\t"
							+ rs.getString("state") + "\t" + rs.getString("price") + "\t" + rs.getString("name") + "\t"
							+ rs.getString("telephone") + "\t" + rs.getString("keyword") + "\t"
							+ rs.getString("yearBuilt") + "\t" + rs.getString("url") + "\t");
				}
			}

			catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return result;
		} else {
			ResultSet rs = null;
			String sql = "select * from TH" + " where " + field + " = " + value;
			if (sort.equals("p")) {
				sql = sql + " order by " + "Price" + increase + ";";
			} else if (sort.equals("s")) {
				sql = sql + " group by (h_id) having (select h_id, AVG(score) AS average)"
						+ "from Feedback group by h_id)" + "order by average " + increase + ";";
			} else if (sort.equals("st")) {
				sql = "select * from TH th ,Trust t, Users u,Feedback f where t.login2 = u.login and "
						+ "f.login = t.login2" + field + " = " + value + " group by (f.h_id) having "
						+ " (select h_id, AVG(score) AS average" + "where sum(t.isTrusted) > 0" + "order by average "
						+ increase + ";";
			}
			try {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result.add(rs.getString("h_id") + rs.getString("login") + "\t" + "\t" + rs.getString("category")
							+ "\t" + rs.getString("address") + "\t" + rs.getString("city") + "\t"
							+ rs.getString("state") + "\t" + rs.getString("price") + "\t" + rs.getString("name") + "\t"
							+ rs.getString("telephone") + "\t" + rs.getString("keyword") + "\t"
							+ rs.getString("yearBuilt") + "\t" + rs.getString("url") + "\t");
				}
			}

			catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return result;
		}
	}

	private ArrayList<String> avgScore(String sort, Statement stmt) {
		ArrayList<String> result = new ArrayList<String>();

		String sql = "select f.h_id, avg (f.score) as average from TH t, Feedback f " + "where t.h_id=f.h_id "
				+ "group by f.h_id " + "order by avg(f.score) desc";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			while (rs.next()) {
				result.add(rs.getString("h_id") + "\t" + rs.getString("average"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return result;
	}
}
