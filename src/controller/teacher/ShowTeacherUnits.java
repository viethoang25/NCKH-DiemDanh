package controller.teacher;

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
import model.bo.TeacherBO;
import model.bo.UnitBO;

@WebServlet("/ShowTeacherUnits")
public class ShowTeacherUnits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowTeacherUnits() {
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
		String teacherId = (String) session.getAttribute("teacherid");
		
		// Handling data
		List<Unit> listUnits = UnitBO.getUnitsOfTeacher(teacherId);
		
		// Set Attribute
		request.setAttribute("listunits", listUnits);
		
		// Handling view
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/teacherUnits.jsp");
		rd.forward(request, response);
	}

}
