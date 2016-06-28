package controller.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessTeacherUnits")
public class ProcessTeacherUnits extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessTeacherUnits() {
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

		// Get Parameter
		String unitId = request.getParameter("unitid");

		// Set Attribute
		request.getSession().setAttribute("unitid", unitId);

		// Handling view
		RequestDispatcher rd = request
				.getRequestDispatcher("/ShowTeacherAction");
		rd.forward(request, response);
	}

}
