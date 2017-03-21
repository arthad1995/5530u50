package phase2;

import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale.Category;

public class TH {
	public boolean newTH(String name, String address, String yearbuild, String telephone, String keyword, String price,
			String url, String category, Statement stmt) {
		String sql = "insert into TH (name, address, url, telephone, yearBuild, price, category,keyword) " + "values ('"
				+ name + "','" + address + "','" + url + "','" + telephone + "','" + yearbuild + "','" + price + "','"
				+ category + "','" + keyword + "');";
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

	public boolean updateTH(int h_id, String updateField, String updateValue, Statement stmt) {
		String sql = "UPDATE TH " + "set " + updateField + "='" + updateValue + "'" + " where h_id='" + h_id + "';";

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
						+ "' and r.r_id = v.r_id group by t.name order by VisitCount desc";
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
						+ "' and r.r_id = v.r_id group by t.name order by VisitCount desc limit " + limit;
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

	public ArrayList<String[]> getSuggestion(String login, int h_id, String amount, Statement st) {
		ArrayList<Integer> suggestionList = new ArrayList<Integer>();
		ArrayList<String[]> tempList = new ArrayList<String[]>();
		String sql = "select r.h_id from Reserve r, Visit v where r.login = '" + login + "' and r.r_id = v.r_id;";
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
		for (int i = 0; i < tempList.size(); i++) {
			int hid = Integer.parseInt((tempList.get(i))[0]);
			if (hid == suggestionList.get(i)) {
				popularList.add(tempList.get(i));
				getSort.add(Integer.parseInt((tempList.get(i)[3])));
			}
		}
		Collections.sort(getSort, Collections.reverseOrder());
		ArrayList<String[]> popularLists = new ArrayList<String[]>();
		if (popularList.size() == getSort.size()) {
			for (int i = 0; i < popularList.size(); i++) {
				for (int j = 0; j < popularList.size(); j++) {
					if (Integer.parseInt((popularList.get(j))[3]) == getSort.get(i)) {
						popularLists.add(popularList.get(i));
					}
				}
			}
		}
		return popularLists;
	}
}
