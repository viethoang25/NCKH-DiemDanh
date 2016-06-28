package controller.teacher;

import java.io.File;
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

@WebServlet("/ShowTeacherApprove")
public class ShowTeacherApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowTeacherApprove() {
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
		String directory = (String) request.getSession().getAttribute("directory");
		
		// Get system coordinate
		List<Coordinate> list = CoordinateBO.getAllCoordinateUnique(directory + "/" + Constants.FILE_NAME_COORDINATES_STUDENT);
		
		// Set Attribute
		request.setAttribute("listcoordinate", list);
		
		// Handling view
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/teacherApprove.jsp");
		rd.forward(request, response);
	}

}
