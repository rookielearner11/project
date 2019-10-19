/**
* This class is to use Firebase to implement the function that users can creat accounts
* There is one and only one admin account (username: admin, pwd:5T5ptQ)
* Employee and patients can be created infinitly
* @author Heng(Scott) Zhang (a Universite d'Ottawa, numero etudient: 300067988)
* Date Started: October 16, 2019
*/

public class Account{
	protected String email;
	protected String password;
	protected String role;
	protected String name;
	final protected Account = Account()
    public Account(String email, String password, String role, String name){
    	this.email = email.toUpperCase();
    	this.password = password;
    	this.role = role.toUpperCase();
    	this.name = name;
    }
    public String getEmail(){
    	return this.email;
    }
    public String getPwd(){
    	return this.password;
    }
    public String getRole(){
    	return this.role;
    }
    public String getName(){
    	return this.name;
    }
}
DatabaseReference accountsRef = ref.child("accounts");

Map<String, User> accounts = new HashMap<>();
//accounts.put("alanisawesome", new Account("June 23, 1912", "Alan Turing"));
//accounts.put("gracehop", new Account("December 9, 1906", "Grace Hopper"));
accountsRef.setValueAsync(accounts);