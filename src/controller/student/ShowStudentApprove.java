package controller.student;

import java.io.IOException;
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

@WebServlet("/ShowStudentApprove")
public class ShowStudentApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowStudentApprove() {
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
		String studentId = (String) request.getSession().getAttribute("studentid");
		String directory = (String) request.getSession().getAttribute("directory");
		
		// Get coordinate
		Coordinate coor = null;
		Coordinate sysCoor = CoordinateBO.getCoordinate(directory + "/" + Constants.FILE_NAME_COORDINATES_SYSTEM, studentId);
		Coordinate stuCoor = CoordinateBO.getCoordinate(directory + "/" + Constants.FILE_NAME_COORDINATES_STUDENT, studentId);
		
		if(stuCoor != null) {
			coor = stuCoor;
		} else if (sysCoor != null){
			coor = sysCoor;
		}
		
		// Set Attribute
		request.getSession().setAttribute("coordinate", coor);
		
		// Handling view
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/studentApprove.jsp");
		rd.forward(request, response);
	}

}
