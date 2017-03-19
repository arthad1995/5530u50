package phase2;

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
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return categories;
	}

	public ArrayList<String[]> getPopularTHs(String amount, Statement st) {
		ArrayList<String[]> forReturn = new ArrayList<String[]>();
		ArrayList<String> catagoryList = new ArrayList<String>();
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
				sql = "select t.name, t.category, count(*) AS VisitCount from TH t, "
						+ "Visit v, Reserve r where t.h_id = r.h_id and t.category = "
						+ s + " and r.r_id = v.r_id group by t.name order by VisitCount desc";
				
			}

		} else {
			limit = Integer.parseInt(amount);
		}
		return forReturn;

	}

}
