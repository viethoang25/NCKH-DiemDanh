package controller.student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Constants;
import manager.DateManager;
import model.bo.CoordinateBO;
import model.bo.FileBO;

@WebServlet("/ShowStudentImages")
public class ShowStudentImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowStudentImages() {
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
		String unitId = (String) session.getAttribute("unitid");
		String studentId = (String) session.getAttribute("studentid");
		
		// Get list image path
		List<String> listImagePath = FileBO.getImagesByUnitAndDateOfStudent(unitId, DateManager.getCurrentDateTime());
		List<String> listDisableImagePath = new ArrayList<>();
		
		// Check if image is approved && time to approve < 3
		for(String path : listImagePath) {
			File temp = new File(path).getParentFile();
			File directory = new File(getServletContext().getRealPath(temp.getPath()));
			if(new File(directory + "/" + Constants.FILE_NAME_APPROVED).exists()
					||	CoordinateBO.countStudentApproveTimes(directory + "/" + Constants.FILE_NAME_COORDINATES_STUDENT, studentId) >= Constants.MAX_TIMES_TO_APPROVE) {
				listDisableImagePath.add(path);
			}
		}
		
		// Set Attribute
		request.setAttribute("listimagepath", listImagePath);
		request.setAttribute("listdisableimagepath", listDisableImagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/studentImages.jsp");
		rd.forward(request, response);
	}

}
