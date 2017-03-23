package phase2;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.*;

public class Driver {

	private static String login = "";
	// private static ArrayList currentList;

	public static void main(String[] args) {
		Connector con = null;
		try {
			// remember to replace the password
			con = new Connector();
			System.out.println("Database connection established");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable connect to databse!");
		}
		mainpage(con);
	}

	public static void mainpage(Connector c) {
		while (true) {
			System.out.println("Welcome to Uotel");
			System.out.println("Please enter 1 to login");
			System.out.println("Please enter 2 for new user registration");
			System.out.println("If you want to exit, please enter 3");
			Scanner sc = new Scanner(System.in);
			String optionstr = sc.nextLine();
			int option = 0;
			try {
				option = Integer.parseInt(optionstr);
			} catch (Exception e) {
				System.err.println("Please input valid number");
				continue;
			}
			if(option > 3 || option <1){
				System.err.println("Please input valid number");
				continue;
			}
			if(option ==1 ){
				String[] arr = userLogin(c);
				if(arr[1].equalsIgnoreCase("admin")){
					System.out.println("Welcome back " + arr[0] + "!");
					showAdminPage(c);
				}
				else{
					System.out.println("Welcome back " + arr[0] + "!");
					showUserPage(c);
				}
			}
			else if(option ==2){
				try {
					newUserRegistration(c);
				} catch (SQLException e) {
					System.err.println("User login already exist");
					continue;
				}
			}
			else if(option==3){
				System.out.println("Bye");
				break;
			}
			
		}
	}

	public static void showAdminPage(Connector c) {
		Scanner sc = new Scanner(System.in);
		String selection;
		int s;
		while (true) {
			System.out.println("\n\t\tWelcome to administartor page");
			System.out.println("Please enter number 1 to add new TH");
			System.out.println("Please enter number 2 to modify your TH");
			System.out.println("Please enter number 3 to show the degrees of separation");
			System.out.println("Please enter number 4 for awards");
			System.out.println("If you want to exist, enter number 5");
			System.out.println("Please make selection: ");
			while (true) {
				selection = sc.nextLine();
				try {
					s = Integer.parseInt(selection);
				} catch (Exception e) {
					System.err.println("Not a valid selection");
					continue;
				}
				if (selection.length() == 0 || s > 5 || s < 1) {
					System.out.println("Please enter a valid selection");
					continue;
				} else
					break;
			}
			switch (s) {
			case 1:
				createNewTH(c);
				break;
			case 2:
				updateTH(c);
				break;
			case 3:
				showDegreeSeparation(c);
				break;
			case 4:
				//fill award
				break;
			case 5:
			default:
				break;
			}

		}
	}

	public static void showUserPage(Connector c) {
		Scanner sc = new Scanner(System.in);
		String selection;
		int s;
		while (true) {
			System.out.println("\n\t\tWelcome to user page");
			System.out.println("Please enter number 1 to start booking your hotel");
			System.out.println("Please enter number 2 to provide your feedback");
			System.out.println("Please enter number 3 to modify and update your favorites");
			System.out.println("Please enter number 4 add visit recoding");
			System.out.println("Please enter number 5 trust or not trust other users");
			System.out.println("Please enter number 6 set useful or useless to other feedbacks");
			System.out.println("Please enter number 7 to request general information");
			System.out.println("If you want to exist, enter number 8");
			System.out.println("Please make selection: ");
			while (true) {
				selection = sc.nextLine();
				try {
					s = Integer.parseInt(selection);
				} catch (Exception e) {
					System.err.println("Not a valid selection");
					continue;
				}
				if (selection.length() == 0 || s > 5 || s < 1) {
					System.out.println("Please enter a valid selection");
					continue;
				} else
					break;
			}
			switch (s) {
			case 1:
				filter(c);
				while(true){
				System.out.println("Type c to check TH's feedback, r for reserve, q for exit");
				String option = sc.nextLine();
				if(option.equalsIgnoreCase("c")){
					getTHfeedback(c);
					continue;
				}
				else if(option.equalsIgnoreCase("r")){
					reserve(c);
				}
				else if(option.equalsIgnoreCase("q")){
					break;
				}
				else{
					System.out.println("Please give valid option..");
					continue;
				}
				}
				
				break;
			case 2:
				giveFeedback(c);
				break;
			case 3:
				manageFavorite(c);
				break;
			case 4:
				visit(c);
				break;
			case 5:
				setTrust(c);
				break;
			case 6:
				rateFeedback(c);
				break;
			case 7:
				getGeneralInfo(c);
				break;
			case 8:
				
			default:
				return;
			}
		}
	}

	public static String[] userLogin(Connector c) {
		String userName, pin, type;
		while (true) {
			System.out.println("\n\n\t\tUser Login");
			System.out.println("Please enter your user name: ");
			Scanner sc = new Scanner(System.in);
			userName = "";
			pin = "";
			while ((userName = sc.nextLine()).length() == 0) {
				break;
			}
			System.out.println("Please enter your password: ");
			while ((pin = sc.nextLine()).length() == 0) {
				break;
			}

			Users user = new Users();
			type = user.isLoginMatch(userName, pin, c.stmt);
			if (type.equals("false")) {
				System.err.println("User name or password is not correct");
				continue; // don't need, but it's OK to do that
			} else {
				System.out.println("sucessful to login");
				break;
			}
		}
		String[] arr = new String[2];
		arr[0] = userName;
		arr[1] = type;
		
		return arr;
	}

	public static void newUserRegistration(Connector c) throws SQLException {
		System.out.println("\n\tWelcome, Please to finish the follow steps to complete your registration");
		System.out.println("Please select and type your desired user type: user or admin");
		Scanner sc = new Scanner(System.in);
		String name, login, contact_Num, Address, password, confirmPin, userType;
		while (true) {
			userType = sc.nextLine();
			if (userType.length() == 0) {
				System.out.println("Your user type cannot be empty");
			} else if (!userType.equals("admin") && !userType.equals("user")) {
				System.out.println("Please enter a valid user type");
			} else
				break;
		}

		System.out.println("Please type your real full name");
		while ((name = sc.nextLine()).length() == 0) {
			if (name.length() == 0) {
				System.out.println("Your real name cannot be empty");
				continue;
			} else
				break;
		}
		System.out.println("Please type your user name");
		while ((login = sc.nextLine()).length() == 0) {
			if (login.length() == 0) {
				System.out.println("Please enter a valid user name");
				continue;
			} else
				break;
		}

		System.out.println("Please type your telephone number");
		while ((contact_Num = sc.nextLine()).length() == 0) {
			if (contact_Num.length() == 0) {
				System.out.println("Telephone number cannot be empty");
				continue;
			} else
				break;
		}

		System.out.println("Please type your address");
		while ((Address = sc.nextLine()).length() == 0) {
			if (Address.length() == 0) {
				System.out.println("Your address cannot be empty");
				continue;
			} else
				break;
		}

		System.out.println("Please set your password");
		while ((password = sc.nextLine()).length() == 0) {
			if (password.length() == 0) {
				System.out.println("Please set a valid password");
				continue;
			} else
				break;
		}

		while (true) {
			System.out.println("Please confirm your password");
			confirmPin = sc.nextLine();
			if (confirmPin.length() == 0) {
				System.out.println("Your confirm password cannot be empty");
			} else if (!password.equals(confirmPin)) {
				System.out.println("Your password does not match...");
			} else
				break;
		}

		Users user = new Users();
		user.newUser(login, name, userType, contact_Num, Address, password, c.stmt);
	}

	private static void createNewTH(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input name of TH");
			String name = sc.nextLine();
			System.out.println("Please input TH address");
			String address = sc.nextLine();
			System.out.println("Please input year of TH");

			String startDateString = sc.nextLine();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			try {
				startDate = df.parse(startDateString);
				String newDateString = df.format(startDate);
				System.out.println(newDateString);
			} catch (ParseException e) {
				System.out.println("invalid date format");
				e.printStackTrace();
				continue;
			}

			System.out.println("Please telephone year of TH");
			String telephone = sc.nextLine();
			System.out.println("Please keyword of TH");
			String keyword = sc.nextLine();
			System.out.println("Please price of TH");
			String pricestr = sc.nextLine();
			double price = 0;
			try {
				price = Double.parseDouble(pricestr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}

			System.out.println("Please url year of TH");
			String url = sc.nextLine();
			System.out.println("Please category year of TH");
			String category = sc.nextLine();
			TH th = new TH();
			boolean check = th.newTH(name, address, startDate, telephone, keyword, price, url, category, c.stmt);
			if (check) {
				System.out.println("New TH created");
				break;
			} else {
				System.out.println("There is something going wrong, press enter to try again, n for exit");
				String ans = sc.nextLine();
				if (ans.equals("n")) {
					break;
				}
			}

		}
		sc.close();
	}

	private static void updateTH(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input your h_id here");
			String h_idstr = sc.nextLine();
			int h_id = 0;
			try {
				h_id = Integer.parseInt(h_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			System.out.println("Please input the field you want to update here");
			String field = sc.nextLine();
			System.out.println("Please input the value you want to update here");
			String value = sc.nextLine();
			TH th = new TH();
			boolean check = th.updateTH(h_id, field, value, c.stmt);
			if (check) {
				System.out.println("TH updated");
			} else {
				System.out.println("There is something going wrong, press enter to try again, n for exit");
				String ans = sc.nextLine();
				if (ans.equals("n")) {
					break;
				}
			}
		}
		sc.close();
	}

	private static void filter(Connector con) {
		ArrayList<String> result = new ArrayList<String>();
		// if (!currentList.isEmpty()) {
		// result = new ArrayList<String>(currentList);
		// }
		ArrayList<String> recevier = new ArrayList<String>();
		int min = 0;
		int max = 0;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input your requirements here, input q when you done");
			System.out.println("Input the field you want to constraint");
			String field = sc.nextLine();
			if (field.equals("q")) {
				break;
			}
			if (field.equalsIgnoreCase("price")) {
				System.out.println("please input your min");
				String minstr = sc.nextLine();
				try {
					min = Integer.parseInt(minstr);
				} catch (Exception e) {
					System.out.println("Please input valid number");
					continue;
				}
				System.out.println("please input your max");
				String maxstr = sc.nextLine();
				try {
					max = Integer.parseInt(maxstr);
				} catch (Exception e) {
					System.out.println("Please input valid number");
					continue;
				}
			}
			System.out.println("Input the value you want to constraint");
			String value = sc.nextLine();
			System.out.println("Sort by price(put p), by rating(put s)," + " by trusted user's rating(put st)");
			String sort = sc.nextLine();
			System.out.println("put ASC for acending order, DESC for descending order");
			String order = sc.nextLine();

			TH th = new TH();
			recevier = th.filter(field, min, max, value, sort, order, con.stmt);
			if (result.isEmpty()) {
				result = new ArrayList<String>(recevier);
				break;
			}

			for (String s : result) {
				if (!recevier.contains(s)) {
					result.remove(s);
				}
			}

		}
		for(String s :result)
		System.out.println(s);
	}

	public static void showDegreeSeparation(Connector con) {
		String degree, loginA, loginB;
		int degreeNum;
		ArrayList<String> separationUsersList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		Users user = new Users();
		System.out.println("\nPlease enter the degree 1 or 2 to show the separation, or press q to quit");
		while (true) {
			degree = sc.nextLine();
			if (degree.equalsIgnoreCase("q"))
				return;
			try {
				degreeNum = Integer.parseInt(degree);
			} catch (NumberFormatException e) {
				System.err.println("Please input a valid number");
				continue;
			}
			if (degree.equals("") || degreeNum < 1 || degreeNum > 2) {
				System.out.println("Please enter number 1 or 2 only");
				continue;
			} else
				break;
		}
		System.out.println("Please enter the first user login");
		while (true) {
			loginA = sc.nextLine();
			if (loginA.equals("")) {
				System.out.println("No empty user name is allowed");
				continue;
			} else
				break;
		}
		System.out.println("Please enter the second user login");
		while (true) {
			loginB = sc.nextLine();
			if (loginB.equals("")) {
				System.out.println("No empty user name is allowed");
				continue;
			} else
				break;
		}
		if (degreeNum == 1) {
			ArrayList<String> temp = new ArrayList<String>();
			separationUsersList = user.getOneDegreeSeperation(loginA, con.stmt);
			temp = user.getOneDegreeSeperation(loginB, con.stmt);
			System.out.println("User names of 1 degree separation from user one are shown below: " + "\n");
			for (String s : separationUsersList)
				System.out.println(s);
			System.out.println("\n");
			System.out.println("User names of 1 degree separation from user two are shown below: " + "\n");
			for (String s : temp)
				System.out.println(s);
			System.out.println("\n");

		} else {
			separationUsersList = user.getTwoDegreeSeperation(loginA, loginB, con.stmt);
			System.out
					.println("User names of 2 degrees separation from user one and user two are shown below: " + "\n");
			for (String s : separationUsersList)
				System.out.println(s);
			System.out.println("\n");
		}
	}

	public static void getGeneralInfo(Connector con) {
		String amount, selectionSt;// Default
		ArrayList<String[]> infoList = new ArrayList<String[]>();
		Scanner sc = new Scanner(System.in);
		TH t = new TH();
		int limit = -1, selection;
		System.out.println("How long list of record you would like to browse(\"ALL\" or a numeric value only): ");
		while (true) {
			amount = sc.nextLine();
			if (amount.equalsIgnoreCase("ALL")) {
				amount = "ALL";
				break;
			} else {
				try {
					limit = Integer.parseInt(amount);
				} catch (NumberFormatException e) {
					System.err.println("Please enter a valid limit");
					continue;
				}
				if (amount.length() == 0) {
					System.out.println("Please enter an non-empty limit");
					continue;
				} else
					break;
			}
		}
		if (limit != -1 && !amount.equals("ALL")) {
			amount = String.valueOf(limit);
		}
		System.out.println("Please enter 1 to browse the most popular TH by category");
		System.out.println("Please enter 2 to browse the most expensive TH by category");
		System.out.println("Please enter 3 to browse the highest rated TH by category");
		System.out.println("Please enter 4 to exist");
		System.out.println("Please enter make your selction");
		while (true) {
			selectionSt = sc.nextLine();
			try {
				selection = Integer.parseInt(selectionSt);
			} catch (NumberFormatException e) {
				System.err.println("Please enter a valid number to continue");
				continue;
			}
			if (selectionSt.length() == 0 || selection < 1 || selection > 4) {
				System.out.println("Please enter a valid number to continue");
				continue;
			} else
				break;
		}
		// if (limit == -1 && amount.equals("ALL")) {
		int count = 1;
		switch (selection) {
		case 1:
			infoList = t.getPopularTHs(amount, con.stmt);
			System.out.println("All the most popular THs by category are shown below: " + "\n");
			for (String[] s : infoList) {
				System.out.println("Num " + count + ". TH Name - " + s[1] + " |**| Category - " + s[2]
						+ " |**| VisitCount - " + s[3]);
				count++;
			}
			System.out.println("\n");
			break;
		case 2:
			infoList = t.getMostExpensiveTHs(amount, con.stmt);
			System.out.println("All the most expensive THs by category are shown below: " + "\n");
			for (String[] s : infoList) {
				System.out.println("Num " + count + ". TH Name - " + s[0] + " |**| Category - " + s[1]
						+ " |**| AverageCost - " + s[2]);
				count++;
			}
			System.out.println("\n");
			break;

		case 3:
			infoList = t.getHighestRate(amount, con.stmt);
			System.out.println("All the highest rated THs by category are shown below: " + "\n");
			for (String[] s : infoList) {
				System.out.println("Num " + count + ". TH Name - " + s[0] + " |**| Category - " + s[1]
						+ " |**| AverageRate - " + s[2]);
				count++;
			}
			System.out.println("\n");
			break;

		case 4:
		default:
			break;
		}
	}

	public static void manageFavorite(Connector con) {
		Scanner sc = new Scanner(System.in);
		Favorites fv = new Favorites();
		String answer, fvTH;
		ArrayList<String[]> isfvExists = fv.getFavorite(login, con.stmt);
		if (isfvExists.size() == 0) {
			System.out.println("You have not set any TH as your favorite yet, do you want to set it right now?"
					+ " Please type Y or N");
			while (true) {
				answer = sc.nextLine();
				if (answer.length() == 0 || (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"))) {
					System.out.println("Please enter a valid answer");
					continue;
				} else
					break;
			}
			if (answer.equalsIgnoreCase("Y")) {
				System.out.println("Please type your favorite TH: ");
				while (true) {
					fvTH = sc.nextLine();
					if (fvTH.length() == 0) {
						System.out.println("No empty TH name is allowed");
						continue;
					} else
						break;
				}

				if (fv.addFavorite(login, fvTH, con.stmt)) {
					System.out.println("Your favorite TH has been recorded");
				} else {
					System.out.println("Failed to record your favorite, please try again");
				}
			} else {
				return;
			}
		} else {
			String changeName, hid, selection;
			int count = 1;
			int sel;
			System.out.println(
					"We found that you have some favorite records, " + "we have shown them for you below: " + "\n");
			for (String[] s : isfvExists) {
				System.out.println("Num " + count + ". TH ID - " + s[0] + " |**| TH Name - " + s[1]);
			}
			System.out.println(
					"\nDo you want to add or remove favorite? Type 1 to add new favorite and type 2 to delete exist favorite. Press q to quit");
			while (true) {
				selection = sc.nextLine();
				if (selection.equalsIgnoreCase("q"))
					return;
				try {
					sel = Integer.parseInt(selection);
				} catch (NumberFormatException e) {
					System.err.println("Please enter a valid selection");
					continue;
				}
				if (selection.length() == 0 || sel > 2 || sel < 1) {
					System.out.println("Please enter a valid selection");
					continue;
				} else
					break;
			}
			switch (sel) {
			case 1:
				System.out.println("Please type your favorite TH to add: ");
				while (true) {
					changeName = sc.nextLine();
					if (changeName.length() == 0) {
						System.out.println("No empty TH name is allowed");
						continue;
					} else
						break;
				}

				if (fv.addFavorite(login, changeName, con.stmt)) {
					System.out.println("Your favorite TH has been recorded");
				} else {
					System.out.println("Failed to record your favorite, please try again");
				}
				break;
			case 2:
				System.out.println("Please type the TH id to delete: ");
				while (true) {
					hid = sc.nextLine();
					if (hid.length() == 0) {
						System.out.println("No empty TH name is allowed");
						continue;
					} else
						break;
				}
				if (fv.delete(login, hid, con.stmt)) {
					System.out.println("Your favorite TH has been recorded");
				} else {
					System.out.println("Failed to record your favorite, please try again");
				}
				break;
			default:
				break;
			}

		}

	}

	// public static ArrayList<String[]> getpmost(String most, Connector c) {
	// ArrayList<String[]> result = null;
	//// Scanner sc = new Scanner(System.in);
	// TH th = new TH();
	//// String amount = sc.nextLine();
	//// while (true) {
	//// System.out.println("Please input amount limit here, ALL for TH");
	//// if (!amount.equalsIgnoreCase("ALL")) {
	//// try {
	//// Integer.parseInt(amount);
	//// } catch (Exception e) {
	//// System.out.println("Please input valid number");
	//// continue;
	//// }
	//// }
	//// break;
	//// }
	// if (most.equals("pop"))
	// result = th.getPopularTHs(most, c.stmt);
	// else if (most.equals("me"))
	// result = th.getMostExpensiveTHs(most, c.stmt);
	// else if (most.equals("hr"))
	// result = th.getHighestRate(Integer.parseInt(most), c.stmt);
	// return result;
	// }

	private static void reserve(Connector c) {
		Scanner sc = new Scanner(System.in);
		Date from = new Date();
		Date to = new Date();
		int h_id = 0;
		while (true) {
			System.out.println("Please input t_id you want to reserve, press q to quit");
			String h_idstr = sc.nextLine();
			if (h_idstr.equals("q")) {
				break;
			}
			try {
				h_id = Integer.parseInt(h_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			System.out.println("Please input date you want to check in mm/dd/yyyy format");
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String fromstr = sc.nextLine();

			try {
				from = df.parse(fromstr);
			} catch (Exception e) {
				System.out.println("Please input valid date format");
				continue;
			}
			System.out.println("Please input date you want to check out mm/dd/yyyy format");
			String tostr = sc.nextLine();

			try {
				to = df.parse(tostr);
			} catch (Exception e) {
				System.out.println("Please input valid date format");
				continue;
			}
			System.out.println("Are you confirm to reserve? y for yes, n for no");
			String confirm = sc.nextLine();
			if (confirm.equalsIgnoreCase("y")) {
				continue;
			}
			Period p = new Period();
			int p_id = p.getP_id(from, to, c.stmt);
			Available a = new Available();
			String price_per_nightstr = a.getAvilable(h_id, p_id, c.stmt).get(0).split("\t")[2];
			double price_per_night = Double.parseDouble(price_per_nightstr);
			long diff = to.getTime() - from.getTime();
			int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			Reserve r = new Reserve();
			Date now = new Date();
			boolean check = r.addReserve(login, h_id, (int) (days * price_per_night), from, to, now, c.stmt);
			if (check) {
				System.out.println("Congratulations you have successfully reserve " + h_id);
			} else {
				System.out.println("oooops, seems something going wrong, please check your input");
				continue;
			}
			break;
		}
	}

	private static void giveFeedback(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input h_id you want to give feedback, press q to quit");
			String h_idstr = sc.nextLine();
			if (h_idstr.equals("q")) {
				break;
			}
			int h_id = 0;
			try {
				h_id = Integer.parseInt(h_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			System.out.println("Please input your text feedback, press q to quit");
			String text = sc.nextLine();
			System.out.println("Please input your score, press q to quit");
			String scorestr = sc.nextLine();
			int score = -1;
			try {
				score = Integer.parseInt(scorestr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			Feedback f = new Feedback();
			boolean check = f.giveFeedback(h_id, h_idstr, text, score, new Date(), c.stmt);
			if (check) {
				System.out.println("You successfully gave feedback");
				break;
			} else {
				System.out.println("oooops, seems something going wrong, please check your input");
				continue;
			}
		}
	}

	private static void rateFeedback(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input f_id you want to give feedback, press q to quit");
			String f_idstr = sc.nextLine();
			if (f_idstr.equals("q")) {
				break;
			}
			int f_id = 0;
			try {
				f_id = Integer.parseInt(f_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}

			System.out.println("Please input your rating score, press q to quit");
			String ratingstr = sc.nextLine();
			int rating = -1;
			try {
				rating = Integer.parseInt(ratingstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			Feedback f = new Feedback();
			boolean check = f.rateFeedback(f_idstr, f_id, rating, c.stmt);
			if (check) {
				System.out.println("You successfully gave rating");
				break;
			} else {
				System.out.println("oooops, seems something going wrong, please check your input");
				continue;
			}
		}
	}

	private static ArrayList<String> getTHfeedback(Connector c) {
		ArrayList<String> result = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input h_id you want to check, press q to quit");
			String h_idstr = sc.nextLine();
			if (h_idstr.equals("q")) {
				break;
			}
			int h_id = 0;
			try {
				h_id = Integer.parseInt(h_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			System.out.println("Please input amount you want to limit, ALL for all feedbacks, press q to quit");
			String amount = sc.nextLine();
			if (!amount.equalsIgnoreCase("ALL")) {
				try {
					Integer.parseInt(amount);
				} catch (Exception e) {
					System.out.println("Please input valid number");
					continue;
				}
				Feedback f = new Feedback();
				result = f.getTHFeedback(h_id, amount, c.stmt);
				break;
			}
		}
		return result;
	}

	private static ArrayList<String> getuserful(Connector c) {
		ArrayList<String> result = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input h_id you want to check, press q to quit");
			String h_idstr = sc.nextLine();
			if (h_idstr.equals("q")) {
				break;
			}
			int h_id = 0;
			try {
				h_id = Integer.parseInt(h_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			System.out.println("Please input amount you want to limit, ALL for all feedbacks, press q to quit");
			String amount = sc.nextLine();
			if (!amount.equalsIgnoreCase("ALL")) {
				try {
					Integer.parseInt(amount);
				} catch (Exception e) {
					System.out.println("Please input valid number");
					continue;
				}
				Feedback f = new Feedback();
				result = f.getrate(h_id, amount, c.stmt);
				break;
			}
		}
		return result;
	}

	private static void setTrust(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input user that you want to trust/untrust, input q for quit");
			String login2 = sc.nextLine();
			if (login2.equals("q")) {
				break;
			}
			if (login.equals(login2)) {
				System.out.println("You can't trust yourself..");
				continue;
			}
			System.out.println("Do you trust him/her? y for yes, n for no");
			String boo = sc.nextLine();
			boolean f = false;
			if (boo.equalsIgnoreCase("y")) {
				f = true;
			} else if (boo.equalsIgnoreCase("n")) {
				f = false;
			} else {
				System.out.println("please input something valid..");
				continue;
			}
			Users u = new Users();
			u.trustRecording(login, login2, f, c.stmt);
			break;
		}
	}

	private static void visit(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input date that you check in, press q to quit");
			String fromstr = sc.nextLine();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			if (fromstr.equals("q")) {
				break;
			}
			fromstr = sc.nextLine();
			Date from = new Date();
			try {
				from = df.parse(fromstr);
			} catch (Exception e) {
				System.out.println("Please input valid date format");
				continue;
			}
			System.out.println("Please input date you want to check out mm/dd/yyyy format");
			String tostr = sc.nextLine();
			Date to = new Date();
			try {
				to = df.parse(tostr);
			} catch (Exception e) {
				System.out.println("Please input valid date format");
				continue;
			}
			System.out.println("Please r_id that you visited");
			String r_idstr = sc.nextLine();
			int r_id = 0;
			try {
				r_id = Integer.parseInt(r_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}

			System.out.println("Are you confirm to add visit? y for yes, n for no");
			String confirm = sc.nextLine();
			if (confirm.equalsIgnoreCase("y")) {
				continue;
			}
			Visit v = new Visit();
			boolean check = v.addVisit(from, to, r_id, c.stmt);
			if (check) {
				System.out.println("You successfully add visit");
				break;
			} else {
				System.out.println("oooops, seems something going wrong, please check your input");
				continue;
			}
		}
	}

	private static void addPeriod(Connector c) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please input h_id you want to add period, press q to quit");
			String h_idstr = sc.nextLine();
			if (h_idstr.equals("q")) {
				break;
			}
			int h_id = 0;
			try {
				h_id = Integer.parseInt(h_idstr);
			} catch (Exception e) {
				System.out.println("Please input valid number");
				continue;
			}
			System.out.println("Please input date that you check in");
			String fromstr = sc.nextLine();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			fromstr = sc.nextLine();
			Date from = new Date();
			try {
				from = df.parse(fromstr);
			} catch (Exception e) {
				System.out.println("Please input valid date format");
				continue;
			}
			System.out.println("Please input date you want to check out mm/dd/yyyy format");
			String tostr = sc.nextLine();
			Date to = new Date();
			try {
				to = df.parse(tostr);
			} catch (Exception e) {
				System.out.println("Please input valid date format");
				continue;
			}
			Period p = new Period();
			p.addPeriod(from, to, c.stmt);
			int pid = p.getP_id(from, to, c.stmt);
			Available a = new Available();
			TH th = new TH();
			String price = th.filter("h_id", 0, 0, h_id + "", "p", "DESC", c.stmt).get(0).split("\t")[6];
			boolean check = a.addAvilable(h_id, pid, Double.parseDouble(price), c.stmt);
			if (check) {
				System.out.println("You successfully add Period");
				break;
			} else {
				System.out.println("oooops, seems something going wrong, please check your input");
				continue;
			}
		}
	}

	private static ArrayList<String> getVisit(Connector c) {
		ArrayList<String> result = new ArrayList<String>();
		Visit v = new Visit();
		result = v.getVisit(-1, login, c.stmt);
		return result;
	}
}
