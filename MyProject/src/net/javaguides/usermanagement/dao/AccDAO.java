package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.abc.config.Config;

import net.javaguides.usermanagement.model.Account;
import net.javaguides.usermanagement.model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class AccDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = Config.DB_ACCOUNT;
	private String jdbcPassword = Config.DB_PASSWORD;

	private static final String INSERT_ACCOUNTS_SQL = "INSERT INTO accounts" + "  (account, password) VALUES "
			+ " (?, ?);";

	private static final String SELECT_ALL_ACCOUNTS = "select * from accounts";
	
	public AccDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertAccount(Account account) throws SQLException {
		System.out.println(INSERT_ACCOUNTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_SQL)) {
			preparedStatement.setString(1, account.getAccount());
			preparedStatement.setString(2, account.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Account> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Account> accounts = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String account = rs.getString("account");
				String password = rs.getString("password");
				accounts.add(new Account(account, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

}
