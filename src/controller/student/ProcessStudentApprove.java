package controller.student;

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

import manager.Constants;
import model.bean.Coordinate;
import model.bo.CoordinateBO;

@WebServlet("/ProcessStudentApprove")
public class ProcessStudentApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessStudentApprove() {
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

		// Get Attribute
		String directory = (String) request.getSession().getAttribute("directory");
		String imagePath = (String) request.getSession().getAttribute(
						"imagepath");
		String unitId = (String) request.getSession().getAttribute("unitid");
		Coordinate coor = (Coordinate) request.getAttribute("newcoor");
		if (coor == null) {
			coor = (Coordinate) request.getSession().getAttribute("coordinate");
		}
		
		// Get Parameter
		String action = (String) request.getParameter("action");

		if (action.equals("apply")) {
			if (coor != null) {
				// Write data in Student coordinate
				CoordinateBO.writeCoordinate(directory + "/" + Constants.FILE_NAME_COORDINATES_STUDENT, coor);
			
				// Open ShowStudentUnits view
				RequestDispatcher rd = request
									.getRequestDispatcher("/ShowStudentUnits");
				rd.forward(request, response);
			}
		} else if (action.equals("setposition")) {
			RequestDispatcher rd = request.getRequestDispatcher("/ShowStudentSetPosition");
			rd.forward(request, response);

		}
	}

}
