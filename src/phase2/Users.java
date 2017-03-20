package phase2;

import java.sql.*;
import java.util.*;

public class Users {
	public Users() {
		// Default constructor
	}

	public void newUser(String login, String name, String userType, String contact_Num, String Address, String password,
			Statement st) {
		String sql = "insert into Users (login, name, userType, contact__Num, Address, password) " + "Values ('" + login
				+ "', '" + name + "', '" + userType + "', '" + contact_Num + "', '" + Address + "', '" + password + "');";
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
		int resultCount = 0;
		if (amount.equals("ALL")) {
			String sql = "select u.name, u.login, sum(t.isTrusted) AS TotalTrust From Trust t, Users u where t.login2 = u.login"
					+ " group by u.login having sum(t.isTrusted) > 0 order by TotalTrust desc";
			try {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					trusted = new String[3];
					trusted[0] = rs.getString("name");
					trusted[1] = rs.getString("login");
					trusted[2] = rs.getString("TotalTrust");
					array.add(trusted);
					resultCount++;
				}
				rs.close();

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (resultCount == 0)
					return null;
			}

		} else {
			int newAmo = Integer.parseInt(amount);
			String sql = "select u.name, u.login, sum(t.isTrusted) AS TotalTrust From Trust t, Users u where t.login2 = u.login"
					+ " group by u.login having sum(t.isTrusted) > 0 order by TotalTrust desc limit " + newAmo;
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

		String sql = "insert into Trust (login1, login2, isTrusted)" + " values ('" + login1 + "', '" + login2 + "', '"
				+ trustVal + "');";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public String getUserLogin(String name, String contact_Num, Statement st) {
		ResultSet rs = null;
		String login = "";
		int loginCount = 0;

		// ArrayList<String> login = new ArrayList<String>();
		String sql = "select login from Users where name = '" + name + "' AND contact_Num = '" + contact_Num + "';";
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// login.add(rs.getString("login"));
				login = rs.getString("login");
				loginCount++;
			}
			if (loginCount == 0 || login.equals(""))
				return ""; // No result founded
			rs.close();
			// if (login.size() < 1)
			// return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// finally {
		// if(loginCount == 0) return ""; // No result founded
		// }

		return login;

	}

	public ArrayList<String> getOneDegreeSeperation(String nameA, String contact_Num, Statement st) {
		String nameALogin = getUserLogin(nameA, contact_Num, st);
		ArrayList<String> forReturn = new ArrayList<String>();

		// select u.login from Users u, Favorites f1 where exists(select
		// f2.login from Favorites f2 where f1.login != f2.login and f1.hid =
		// f2.hid);
		String sql = "select u.name from Users u, Favorites f1 where exists (select f2.login from Favorites where "
				+ "f1.login = '" + nameALogin + "' and f1.login != f2.login and f1.hid = f2.hid;";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			while (rs.next()) {
				forReturn.add(rs.getString("name"));
			}
			if (forReturn.isEmpty())
				return forReturn;
			rs.close();
		} catch (SQLException e) {

		} catch (Exception e) {

		}

		return forReturn;
	}

	public ArrayList<String> getTwoDegreeSeperation(String userNameA, String userNameB, String contact_NumA,
			String contact_NumB, Statement st) {
		ArrayList<String> A_oneDegreeList = getOneDegreeSeperation(userNameA, contact_NumA, st);
		ArrayList<String> B_oneDegreeList = getOneDegreeSeperation(userNameB, contact_NumB, st);
		ArrayList<String> forReturn = new ArrayList<String>();
		if(A_oneDegreeList.isEmpty() || B_oneDegreeList.isEmpty()) return forReturn;
	//	else if(A_oneDegreeList.contains(userNameB) || B_oneDegreeList.contains(userNameA)) return forReturn;
		else if(!A_oneDegreeList.contains(userNameB) && !B_oneDegreeList.contains(userNameA))
		{
			for(String s : A_oneDegreeList)
			{
				if(B_oneDegreeList.contains(s)) forReturn.add(s);
			}
		}
		return forReturn;
	}
}
