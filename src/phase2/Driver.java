package phase2;

import java.sql.SQLException;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Welcome to Utel");
		System.out.println("Please enter 1 to login");
		System.out.println("Please enter 2 for new user registration");
		System.out.println("If you want to exit, please enter 3");

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

	public void newUserRegistration(Connector c) throws SQLException {
		System.out.println("\n\tWelcome, Please to finish the follow steps to complete your registration");
		System.out.println("Please type your real full name");
		Scanner sc = new Scanner(System.in);
		String name, login, contact_Num, Address, password, confirmPin;
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
				// continue;
			} else if (!password.equals(confirmPin)) {
				System.out.println("Your password does not match...");
				// continue;
			} else
				break;
		}
		
		Users user = new Users();
		user.newUser(login, name, contact_Num, Address, password, c.stmt);

	}
}
