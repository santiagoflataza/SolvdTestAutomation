package com.solvd.webOnlineShop.user.registeredUser;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.GenerateRandomData;
import com.solvd.webOnlineShop.Main;
import com.solvd.webOnlineShop.exceptions.UserAlreadyExistException;
import com.solvd.webOnlineShop.lambda.IRegexCompare;
import com.solvd.webOnlineShop.payment.Cart;
import com.solvd.webOnlineShop.user.AbstractUser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends AbstractUser {
    private static final Logger logger = LogManager.getLogger(GenerateRandomData.class);
    private final Cart cart = new Cart();
    private String creationCode;
    private String userName;
    private String password;
    private String name;
    private String email;
    private final Date creationDate = new Date();
    private Date lastUpdate = new Date();

    private final static IRegexCompare regex = ((pattern, input) -> {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(input);
            return m.matches();});

    public Customer() {
        this.userName = getUserNameAndCheckIt();
        this.password = writeAlphaNumeric("Password (Only letters and numbers, without spaces): ");
        this.name = writeAlphabetical("Name (Only letters and spaces): ");
        this.email = writeEmail();
        this.creationCode = userName + password + name + email;
        CustomerDAO.getCustomerDAO().save(this);
        logger.info("User created.");
    }

    public Customer(String userName, String password, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        DatabaseSimulation.getUsersSet().add(this);
        DatabaseSimulation.getUsersUserNames().add(this.getUserName());
        logger.info("User created.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return getCreationCode().equals(customer.getCreationCode()) && getCreationDate().equals(customer.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCreationCode(), getCreationDate());
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "userName='" + this.getUserName() +
                ", name='" + this.getName() +
                ", email='" + this.getEmail() +
                '}';
    }

    public Cart getCart() {
        return cart;
    }

    public String getCreationCode() {
        return creationCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    //General checks for all fields
    
    static public String writeInput(String pattern, String message){

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = console.readLine();
        } catch (IOException e) {
            logger.error(e);
            input = writeInput(pattern, message);
        }

        boolean inputIsLegal = regex.validateInput(pattern, input);

        if (!inputIsLegal){
            logger.warn("Illegal input: " + message);
            input = writeInput(pattern, message);
        }
        return input;
    }

    public static String writeAlphaNumeric(String message) {
        String pattern = "[a-zA-Z0-9]+";
        if(Main.isIsDummyOn()){
            return CustomerTester.generateRandomInput();
        }else{
            return writeInput(pattern, message);
        }
    }

    static public String writeAlphabetical(String message) {
        String pattern = "[a-zA-Z]+";
        if(Main.isIsDummyOn()){
            return CustomerTester.generateRandomInput();
        }else{
            return writeInput(pattern, message);
        }
    }

    static public String writeEmail() {
        String message = "Enter a valid Email: ";
        String pattern = "^(.+)@(.+)";
        if(Main.isIsDummyOn()){
            return CustomerTester.generateRandomEmail();
        }else{
            return writeInput(pattern, message);
        }
    }

    public String getUserNameAndCheckIt(){
        String userName;

        try{
            userName = checkUsedUserName();
        } catch (UserAlreadyExistException e) {
            logger.error(e + "This User Name is already used. try another");
            userName = getUserNameAndCheckIt();
        }
    return userName;
    }

    public String checkUsedUserName() throws UserAlreadyExistException {
        String userName = writeAlphaNumeric("Username (Only letters and numbers, without spaces): ");

        if(DatabaseSimulation.getUsersUserNames().contains(userName)){
            throw new UserAlreadyExistException("UserAlreadyExistException Occurred: ");
        }
        return userName;
    }
}

