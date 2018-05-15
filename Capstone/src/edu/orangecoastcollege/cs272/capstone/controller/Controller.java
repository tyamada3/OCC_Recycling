package edu.orangecoastcollege.cs272.capstone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.orangecoastcollege.cs272.capstone.model.Customer;
import edu.orangecoastcollege.cs272.capstone.model.DBModel;
import edu.orangecoastcollege.cs272.capstone.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "occ_recycling.db";

	// Steven edited this (well actually Mike did)

	// For Customer Table to login
	private static final String CUSTOMER_TABLE_NAME = "customer";
	private static final String[] CUSTOMER_FIELD_NAMES = {"_id", "name",  "user", "role", "email", "password"};
	private static final String[] CUSTOMER_FIELD_TYPES = {"INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT"};

	private Customer mCurrentCustomer;
	private DBModel mCustomerDB;
	
	// For Employee Table to login
	private static final String EMPLOYEE_TABLE_NAME = "employee";
	private static final String[] EMPLOYEE_FIELD_NAMES = {"_id", "name",  "user", "role", "email", "password"};
	private static final String[] EMPLOYEE_FIELD_TYPES = {"INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT"};

	private Employee mCurrentEmployee;
	private DBModel mEmployeeDB;

	private ObservableList<Customer> mAllCustomersList;
	private ObservableList<Employee> mAllEmployeesList;

	private Controller() {}

	public static Controller getInstance(){

		if(theOne == null) {

			theOne = new Controller();
			theOne.mAllCustomersList = FXCollections.observableArrayList();
			theOne.mAllEmployeesList = FXCollections.observableArrayList();

			try {
				// To create Customer Table
				theOne.mCustomerDB = new DBModel(DB_NAME, CUSTOMER_TABLE_NAME, CUSTOMER_FIELD_NAMES, CUSTOMER_FIELD_TYPES);
				ArrayList<ArrayList<String>> customerResultsList = theOne.mCustomerDB.getAllRecords();

				for (ArrayList<String> values : customerResultsList)
				{

					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String user = values.get(2);
					String role = values.get(3);
					String email = values.get(4);
					String password = values.get(5);


					theOne.mAllCustomersList.add(new Customer(id, name, user, role, email, password));

				}
				
				// To create Employee Table
				theOne.mEmployeeDB = new DBModel(DB_NAME, EMPLOYEE_TABLE_NAME, EMPLOYEE_FIELD_NAMES, EMPLOYEE_FIELD_TYPES);
				ArrayList<ArrayList<String>> employeeResultsList = theOne.mEmployeeDB.getAllRecords();
				
				for(ArrayList<String> values : employeeResultsList) {
					
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String user = values.get(2);
					String role = values.get(3);
					String email = values.get(4);
					String password = values.get(5);
					
					theOne.mAllEmployeesList.add(new Employee(id, name, user, role, email, password));
					
				}
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}

		return theOne;

	}

	public String customerLogin(String userName, String password) {

		for(Customer customer : theOne.mAllCustomersList) {

			if(customer.getmUser().equalsIgnoreCase(userName)) {

				try {

					ArrayList<ArrayList<String>> userResults = theOne.mCustomerDB.getRecord(String.valueOf(customer.getmId()));
					String storedPassword = userResults.get(0).get(5);
					if(password.equals(storedPassword)){

                        mCurrentCustomer = customer;
                        return "SUCCESS";

                    }else {
                         return "Wrong password";
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return "Wrong user name or/and password";
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

		//To check if the userName is already taken
		for(Customer customer : theOne.mAllCustomersList)
			if(user.equalsIgnoreCase(customer.getmUser()))
				return "User name is already taken. Please use different user name";

		String[] values = {name, user, "customer", email, password};

		try {

			int id = theOne.mCustomerDB.createRecord(Arrays.copyOfRange(CUSTOMER_FIELD_NAMES, 1, CUSTOMER_FIELD_NAMES.length), values);

            theOne.mCurrentCustomer = new Customer(id, name, user, "user", email, password);
            theOne.mAllCustomersList.add(theOne.mCurrentCustomer);
            System.out.println(theOne.mAllCustomersList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return "SUCCESS";
	}
	
	public String employeeLogin(String userName, String password) {

		for(Employee employee : theOne.mAllEmployeesList) {

			if(employee.getmUser().equalsIgnoreCase(userName)) {

				try {

					ArrayList<ArrayList<String>> userResults = theOne.mEmployeeDB.getRecord(String.valueOf(employee.getmId()));
					String storedPassword = userResults.get(0).get(5);
					if(password.equals(storedPassword)){

                        mCurrentEmployee = employee;
                        return "SUCCESS";

                    }else {
                         return "Wrong password";
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return "Wrong user name or/and password";
	}
	
	public String employeeSignUp(String name, String user, String email, String password) {

		//To check if the email is valid
		if(!isValidEmail(email))
			return "Email address is not valid. Please try again.";

		//To check if the email is already taken
		for(Employee employee: theOne.mAllEmployeesList)
			if(email.equalsIgnoreCase(employee.getmEmail()))
				return "Email address already used. Please sign in or use different address.";

		//To check if the password is valid
		if(!isValidPassword(password))
		    return "Password must be at least 8 characters, including 1 upper case letter, 1 number and 1 symbol.";

		//To check if the userName is already taken
		for(Employee employee : theOne.mAllEmployeesList)
			if(user.equalsIgnoreCase(employee.getmUser()))
				return "User name is already taken. Please use different user name";

		String[] values = {name, user, "employee", email, password};

		try {

			int id = theOne.mEmployeeDB.createRecord(Arrays.copyOfRange(EMPLOYEE_FIELD_NAMES, 1, EMPLOYEE_FIELD_NAMES.length), values);

            theOne.mCurrentEmployee = new Employee(id, name, user, "employee", email, password);
            theOne.mAllEmployeesList.add(theOne.mCurrentEmployee);
            System.out.println(theOne.mAllEmployeesList);

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

	

}
