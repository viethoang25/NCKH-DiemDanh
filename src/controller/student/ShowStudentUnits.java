package controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Unit;
import model.bo.UnitBO;

@WebServlet("/ShowStudentUnits")
public class ShowStudentUnits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowStudentUnits() {
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
		HttpSession session = request.getSession();
		String studentId = (String) session.getAttribute("studentid");
		
		// Handling data
		List<Unit> listUnits = UnitBO.getUnitsOfStudent(studentId);
		
		// Set Attribute
		request.setAttribute("listunits", listUnits);
		
		// Handling view
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/studentUnits.jsp");
		rd.forward(request, response);
	}

}
