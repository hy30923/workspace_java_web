package net.javaguides.usermanagement.model;

public class Account {

	protected int id;
	protected String account;
	protected String password;
	
	public Account() {
		super();
	}

	public Account(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
