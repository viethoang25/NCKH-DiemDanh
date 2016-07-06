package controller.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Constants;
import model.bean.Coordinate;
import model.bo.CoordinateBO;

@WebServlet("/ProcessStudentSetPosition")
public class ProcessStudentSetPosition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessStudentSetPosition() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Get Attribute
		String directory = (String) request.getSession().getAttribute("directory");
		String studentId = (String) request.getSession().getAttribute("studentid");
		
		// Get Parameter
		int x1 = Integer.parseInt(request.getParameter("x1"));
		int y1 = Integer.parseInt(request.getParameter("y1"));
		int x2 = Integer.parseInt(request.getParameter("x2"));
		int y2 = Integer.parseInt(request.getParameter("y2"));
		if (x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		Coordinate coor = new Coordinate(studentId, x1, y1, x2, y2);
		
		// Write data in Student coordinate
		CoordinateBO.writeCoordinate(directory + "/" + Constants.FILE_NAME_COORDINATES_STUDENT, coor);
			
		// Go to ShowTeacherApprove
		RequestDispatcher rd = request
				.getRequestDispatcher("/ShowStudentApprove");
		rd.forward(request, response);
	}

}
