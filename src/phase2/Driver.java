package phase2;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Driver {

	ArrayList<String> currentList;
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
	private void createNewTH(Connector c){
		Scanner sc = new Scanner(System.in);
		while(true){
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
		    try{
		     price = Double.parseDouble(pricestr);
		     }
		    catch(Exception e){
		    	System.out.println("Please input valid number");
		    	continue;
		    }
		    
		    System.out.println("Please url year of TH");
		    String url = sc.nextLine();
		    System.out.println("Please category year of TH");
		    String category = sc.nextLine();
		    TH th = new TH();
		  boolean check=  th.newTH(name, address, startDate, telephone, keyword, price, url, category, c.stmt);
			if(check){
				System.out.println("New TH created");
				break;
			}
			else{
				System.out.println("There is something going wrong, press enter to try again, n for exit");
				String ans = sc.nextLine();
				if(ans.equals("n")){
					break;
				}
			}
			
		}
		sc.close();
	}
	private void updateTH(Connector c){
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Please input your h_id here");
			String h_idstr = sc.nextLine();
			int h_id = 0;
			  try{
				     h_id = Integer.parseInt(h_idstr);
				     }
				    catch(Exception e){
				    	System.out.println("Please input valid number");
				    	continue;
				    }
			  System.out.println("Please input the field you want to update here");
				String field = sc.nextLine();
				 System.out.println("Please input the value you want to update here");
					String value = sc.nextLine();
			TH th = new TH();
			boolean check = th.updateTH(h_id, field, value, c.stmt);
			if(check){
				System.out.println("TH updated");
			}
			else{
				System.out.println("There is something going wrong, press enter to try again, n for exit");
				String ans = sc.nextLine();
				if(ans.equals("n")){
					break;
				}
			}
		}
		sc.close();
	}
	
private ArrayList<String> filter(Connector con){
	ArrayList<String> result = new ArrayList<String>();
	if(!currentList.isEmpty()){
		result = new ArrayList<String>(currentList);
	}
	ArrayList<String> recevier = new ArrayList<String>();
	int min = 0;
	int max = 0;
	Scanner sc = new Scanner(System.in);
	while(true){
		System.out.println("Please input your requirements here, input q when you done");
		System.out.println("Input the field you want to constraint");
		String field = sc.nextLine();
		if(field.equals("q")){
			break;
		}
		if(field.equalsIgnoreCase("price")){
			System.out.println("please input your min");
			String minstr = sc.nextLine(); 
			 try{
			     min = Integer.parseInt(minstr);
			     }
			    catch(Exception e){
			    	System.out.println("Please input valid number");
			    	continue;
			    }
			 System.out.println("please input your max");
				String maxstr = sc.nextLine(); 
				 try{
				     max = Integer.parseInt(maxstr);
				     }
				    catch(Exception e){
				    	System.out.println("Please input valid number");
				    	continue;
				    }
		}
		System.out.println("Input the value you want to constraint");
		String value = sc.nextLine();
		System.out.println("Sort by price(put p), by rating(put s),"
				+ " by trusted user's rating(put st)");
		String sort = sc.nextLine();
		System.out.println("put ASC for acending order, DESC for descending order");
		String order = sc.nextLine();
		
		TH th = new TH();
		recevier = th.filter(field, min, max, value, sort, order, con.stmt);
		if(result.isEmpty()){
			result = new ArrayList<String>(recevier);
			break;
		}
		
		for(String s: result){
			if(!recevier.contains(s)){
				result.remove(s);
			}
		}
		
	}
	return result;
}
private ArrayList<String[]> getpmost(String most, Connector c){
	ArrayList<String[]> result = null;
	Scanner sc = new Scanner(System.in);
	TH th = new TH();
	System.out.println("Please input amount limit here, ALL for TH");
	String amount = sc.nextLine();
	while(true){
		System.out.println("Please input amount limit here, ALL for TH");
	if(!amount.equalsIgnoreCase("ALL")){
		 try{
		     Integer.parseInt(amount);
		     }
		    catch(Exception e){
		    	System.out.println("Please input valid number");
		    	continue;
		    }
	}
	break;
	}
	if(most.equals("pop"))
	 result = th.getPopularTHs(amount, c.stmt);
	else if(most.equals("me"))
		result = th.getMostExpensiveTHs(amount, c.stmt);
	else if(most.equals("hr"))
		result = th.getHighestRate(Integer.parseInt(amount), c.stmt);
	return result;
}

}
