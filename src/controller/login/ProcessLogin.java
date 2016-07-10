package controller.login;

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
import model.bean.Unit;
import model.bo.AccountBO;
import model.bo.TeacherBO;

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
			RequestDispatcher rd = request.getRequestDispatcher("/ShowLogin");
			rd.include(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			if (account.getAuthorization().equals(Constants.ACCOUNT_ADMIN)) {
				// Admin action
				RequestDispatcher rd = request
						.getRequestDispatcher("/ShowAdminAction");
				rd.forward(request, response);
			} else if (account.getAuthorization().equals(
					Constants.ACCOUNT_TEACHER)) {
				// Teacher action
				session.setAttribute("teacherid", account.getId());
				RequestDispatcher rd = request
						.getRequestDispatcher("/ShowTeacherUnits");
				rd.forward(request, response);
			} else if (account.getAuthorization().equals(
					Constants.ACCOUNT_STUDENT)) {
				// Student action
				session.setAttribute("studentid", account.getId());
				RequestDispatcher rd = request
						.getRequestDispatcher("/ShowStudentUnits");
				rd.forward(request, response);
			}
		}
	}

}
