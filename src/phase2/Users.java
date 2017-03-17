package phase2;

import java.sql.*;
import java.util.*;

public class Users {
	public Users() {
		// Default constructor
	}

	public void newUser(String login, String name, String userType, String contact_Num, String Address, String password,
			Statement st) {
		String sql = "insert into Users (login, name, userType, contact__Num, Address, password) " + "Values (" + login
				+ ", " + name + ", " + userType + ", " + contact_Num + ", " + Address + ", " + password + ");";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {

		} catch (Exception e) {

		}

	}

	public ArrayList<String[]> getTrustedUsers(Statement st, String amount) {
		String[] trusted;
		ArrayList<String[]> array = new ArrayList<String[]>();
		ResultSet rs = null;
		if (amount.equals("ALL")) {
			String sql = "select u.name, u.login, sum(t.isTrusted) AS TotalTrust From Trust t, Users u where t.login2 = u.login"
					+ " group by u.name, having sum(t.isTrusted) > 0 order by TotalTrust desc";
			try {
				st.executeQuery(sql);
				while (rs.next()) {
					trusted = new String[3];
					trusted[0] = rs.getString("name");
					trusted[1] = rs.getString("login");
					trusted[2] = rs.getString("TotalTrust");
					array.add(trusted);
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			int newAmo = Integer.parseInt(amount);
			String sql = "select u.name, u.login, sum(t.isTrusted) AS TotalTrust From Trust t, Users u where t.login2 = u.login"
					+ " group by u.name, having sum(t.isTrusted) > 0 order by TotalTrust desc limit " + newAmo;
			try {
				st.executeQuery(sql);
				while (rs.next()) {
					trusted = new String[3];
					trusted[0] = rs.getString("name");
					trusted[1] = rs.getString("login");
					trusted[2] = rs.getString("TotalTrust");
					array.add(trusted);
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return array;
	}

	/**
	 * public void setUserType(ArrayList<String[]> arr, Statement st) { for(int
	 * i = 0; i < arr.size(); i++) {
	 * 
	 * } }
	 */

	public void trustRecording(String login1, String login2, boolean isTrusted, Statement st) {
		int trustVal;
		if (isTrusted)
			trustVal = 1;
		else
			trustVal = -1;

		String sql = "insert into Trust (login1, login2, isTrusted)" + " values (" + login1 + ", " + login2 + ", "
				+ trustVal + ");";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<String> getUserLogin(String name, Statement st) {
		ResultSet rs = null;
		ArrayList<String> login = new ArrayList<String>();
		String sql = "select login from Users where name = " + name + ";";
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				login.add(rs.getString("login"));
			}
			rs.close();
			if (login.size() < 1)
				return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// finally
		// {
		// if(st == null)
		// {
		//
		// }
		// }

		return login;

	}

}
