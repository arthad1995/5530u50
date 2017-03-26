package phase2;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Favorites {

	// ok
	public ArrayList<String[]> getFavorite(String login, Statement stmt) {
		String sql = "select TH.h_id, TH.name from TH " + "where h_id IN "
				+ "(select h_id from Favorites where login = '" + login + "');";
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
		return result;
	}

	// good check
	public boolean addFavorite(String login, String favorite, Statement stmt) {

		// String date = "01-02-2013";
		// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		// java.util.Date start = sdf.parse(date);
		// java.sql.Date sqlStart = new java.sql.Date(start.getTime());
		//
		//
		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		// String currentDate = dateFormat.format(date);
		java.sql.Date sqlStart = new java.sql.Date(date.getTime());
		String sql = "insert into Favorites (h_id, login, fvdate) " + "VALUES ((select h_id from TH where name ='"
				+ favorite + "'), '" + login + "', '" + sqlStart + "');";
		int count = 0;
		try {
			count = stmt.executeUpdate(sql);

			if (count <= 0) {
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
	public boolean delete(String login, String h_id, Statement stmt) {

		String sql = "delete from Favorites "

				+ " where login='" + login + "' and" + " h_id = '" + h_id + "';";

		int count = 0;
		try {
			count = stmt.executeUpdate(sql);

			if (count <= 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
