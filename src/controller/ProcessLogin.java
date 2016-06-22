package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Constants;
import model.bean.Account;
import model.bo.AccountBO;

@WebServlet("/ProcessLogin")
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = AccountBO.checkAccount(username, password);
		if (account == null) {
			request.setAttribute("message", "Tên đăng nhập hoặc mật khẩu không chính xác");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			rd.include(request, response);
		} else {
			HttpSession session = request.getSession();
			if (account.getAuthorization().equals(Constants.ACCOUNT_ADMIN)) {
				// Admin action
				RequestDispatcher rd = request
						.getRequestDispatcher("/ShowAdminAction");
				rd.forward(request, response);
			} else if (account.getAuthorization().equals(
					Constants.ACCOUNT_TEACHER)) {
				// Teacher action
			} else if (account.getAuthorization().equals(
					Constants.ACCOUNT_STUDENT)) {
				// Studetn action
			}
		}
	}

}
