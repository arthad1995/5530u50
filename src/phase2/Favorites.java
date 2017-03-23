package phase2;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Favorites {
	public ArrayList<String[]> getFavorite(String login, Statement stmt) {
		String sql = "select TH.h_id, TH.name from TH " + "where h_id IN " + "(select h_id from Favorites where login = '"
				+ login + "');";
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] arr;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				arr = new String[2];
				arr[0] = String.valueOf(rs.getInt("h_id"));
				arr[1] = rs.getString("name");
				result.add(arr);
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean addFavorite(String login, String favorite, Statement stmt) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String sql = "insert into Favorites (h_id, login, fvdate) " + "VALUES ((select h_id from TH where name ='"
				+ favorite + "'), '" + login + "', '" + currentDate + "');";
		int count = 0;
		try {
			count = stmt.executeUpdate(sql);

			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	// delete?

	public boolean updateFavorite(String login, String changeValue, Statement stmt) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		String sql = "UPDATE Favorites " + "SET h_id = (select h_id from TH where name ='" + changeValue
				+ "'), fvdate ='" + currentDate + "'" + " WHERE login='" + login + "';";

		int count = 0;
		try {
			count = stmt.executeUpdate(sql);

			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public boolean delete(String login, String h_id, Statement stmt) {

		String sql = "delete * from Favorites "

				+ " where login='" + login + "and" + " h_id='" + h_id + "';";

		int count = 0;
		try {
			count = stmt.executeUpdate(sql);

			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
