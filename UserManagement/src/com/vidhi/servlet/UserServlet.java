package com.vidhi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vidhi.userdb.UserDb;
import com.vidhi.User;





public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDb ud;
	
	public void init() {
		ud = new UserDb();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
				dispatcher.forward(request, response);
				break;
			case "/insert":
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				
				User newUser = new User(name, email);
				ud.insertUser(newUser);
				response.sendRedirect("list");
				break;
			case "/delete":
				int id = Integer.parseInt(request.getParameter("id"));
				ud.deleteUser(id);
				response.sendRedirect("list");

				break;
			case "/edit":
				int id2 = Integer.parseInt(request.getParameter("id"));
				User existingUser = ud.selectUser(id2);
				RequestDispatcher dispatcher2 = request.getRequestDispatcher("user-form.jsp");
				request.setAttribute("user", existingUser);
				dispatcher2.forward(request, response);
				break;
			case "/update":
				int id1 = Integer.parseInt(request.getParameter("id"));
				String name1 = request.getParameter("name");
				String email1 = request.getParameter("email");
		

				User book = new User(id1, name1, email1);
				ud.updateUser(book);
				response.sendRedirect("list");
				break;
			default:
				List<User> listUser = ud.selectAllUsers();
				request.setAttribute("listUser", listUser);
				RequestDispatcher dispatcher3 = request.getRequestDispatcher("user-list.jsp");
				dispatcher3.forward(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
/*
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = ud.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = ud.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		User book = new User(id, name, email);
		ud.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ud.deleteUser(id);
		response.sendRedirect("list");

	}*/

}