package com.vidhi.userdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.vidhi.User;
public class UserDb {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/userDB";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email) VALUES "
			+ " (?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ? where id = ?;";

	public UserDb() {
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

	public void insertUser(User user) throws SQLException {
		System.out.println("INSERT INTO users" + "  (name, email) VALUES "
				+ " (?, ?);");
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users" + "  (name, email) VALUES "
						+ " (?, ?);")) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
		
		}
	}

	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select id,name,email from users where id =?");) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				user = new User(id, name, email);
			}
		} catch (SQLException e) {
		
		}
		return user;
	}

	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users");) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
			
				users.add(new User(id, name, email));
			}
		} catch (SQLException e) {
			
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from users where id = ?;");) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("update users set name = ?,email= ? where id = ?;");) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
	
			statement.setInt(4, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}


}
