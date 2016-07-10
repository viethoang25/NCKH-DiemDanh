package controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Person;
import model.bean.Teacher;
import model.bo.StudentBO;
import model.bo.TeacherBO;

@WebServlet("/ShowAccountDetails")
public class ShowAccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowAccountDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		// Get Attribute
		Account account = (Account) request.getSession().getAttribute("account");
		
		Person person = null;
		if(account.getAuthorization().equals("1")) {
			// Teacher
			person = TeacherBO.getTeacherById(account.getId());	
		} else if (account.getAuthorization().equals("2")) {
			// Student
			person = StudentBO.getStudentById(account.getId());
		}
		
		// Set Attribute
		request.setAttribute("person", person);

		RequestDispatcher rd = request
				.getRequestDispatcher("/jsp/accountDetails.jsp");
		rd.forward(request, response);
	}
}
