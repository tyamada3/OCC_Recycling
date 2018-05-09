package edu.orangecoastcollege.cs272.capstone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.orangecoastcollege.cs272.capstone.model.Customer;
import edu.orangecoastcollege.cs272.capstone.model.DBModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "occ_recycling.db";


	// For Customer Table
	private static final String CUSTOMER_TABLE_NAME = "customer";
	private static final String[] CUSTOMER_FIELD_NAMES = {"_id", "name",  "user", "role", "email"};
	private static final String[] CUSTOMER_FIELD_TYPES = {"INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT"};


	private Customer mCurrentUser;
	private DBModel mCustomerDB;

	private ObservableList<Customer> mAllCustomersList;

	private Controller() {}

	public static Controller getInstance(){

		if(theOne == null) {

			theOne = new Controller();
			theOne.mAllCustomersList = FXCollections.observableArrayList();


			try {
				// To create User Table
				theOne.mCustomerDB = new DBModel(DB_NAME, CUSTOMER_TABLE_NAME, CUSTOMER_FIELD_NAMES, CUSTOMER_FIELD_TYPES);
				ArrayList<ArrayList<String>> resultsList = theOne.mCustomerDB.getAllRecords();

				for (ArrayList<String> values : resultsList)
				{

					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String user = values.get(2);
					String role = values.get(3);
					String email = values.get(4);


					theOne.mAllCustomersList.add(new Customer(id, name, user, role, email));

				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}

		return theOne;

	}

	public String customerSignIn(String userName, String password) {

		for(Customer customer : theOne.mAllCustomersList) {

			if(customer.getmUser().equalsIgnoreCase(userName)) {

				try {

					ArrayList<ArrayList<String>> userResults = theOne.mCustomerDB.getRecord(String.valueOf(customer.getmId()));
					String storedPassword = userResults.get(0).get(4);
					if(password.equals(storedPassword)){

                        mCurrentUser = customer;
                        return "SUCCESS";

                    }else
                        return "Incorrect email and/or password. Please try again";

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return "Email address not found.  Please try again.";
	}

	public String customerSignUp(String name, String user, String email, String password) {

		//To check if the email is valid
		if(!isValidEmail(email))
			return "Email address is not valid. Please try again.";

		//To check if the email is aready taken
		for(Customer customer: theOne.mAllCustomersList)
			if(email.equalsIgnoreCase(customer.getmEmail()))
				return "Email address already used. Please sign in or use different address.";

		//To check if the password is valid
		if(!isValidPassword(password))
		    return "Password must be at least 8 characters, including 1 upper case letter, 1 number and 1 symbol.";

		//To check if the userName is valid
		//Fixing bug
		//if(!isValidUserName(user))
			//return "White Space is not allowed for user name";

		//To check if the userName is already taken
		for(Customer customer : theOne.mAllCustomersList)
			if(user.equalsIgnoreCase(customer.getmUser()))
				return "User name is already taken. Please use different user name";

		String[] values = {name, user, "customer", email, password};

		try {

			int id = theOne.mCustomerDB.createRecord(Arrays.copyOfRange(CUSTOMER_FIELD_NAMES, 1, CUSTOMER_FIELD_NAMES.length), values);

            theOne.mCurrentUser = new Customer(id, name, user, "user", email);
            theOne.mAllCustomersList.add(theOne.mCurrentUser);
            System.out.println(theOne.mAllCustomersList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return "SUCCESS";
	}

	public boolean isValidEmail(String email){
		return email.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}


    public boolean isValidPassword(String password)
    {
        // Valid password must contain (see regex below):
        // At least one digit
        // At least one lower case letter
        // At least one upper case letter
        // At least one special character !@#$%^&*()_+\-=[]{};':"\|,.<>/?
        // At least 8 characters long, but no more than 16
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\\\|,.<>\\/?]).{8,16}$");
    }

	public boolean isValidUserName(String userName) {

		return userName.matches("(?=\\\\S+$)");

	}

}
