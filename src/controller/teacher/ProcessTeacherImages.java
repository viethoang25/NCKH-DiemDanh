package controller.teacher;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessTeacherImages")
public class ProcessTeacherImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessTeacherImages() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		// Get Parameter
		String imagePath = request.getParameter("imagepath");
		
		// Find directory contain image
		File temp = new File(imagePath).getParentFile();
		File parentDirectory = new File(getServletContext().getRealPath(temp.getPath()));
		System.out.println(this.getClass().getSimpleName() + " : Directory" + parentDirectory.getAbsolutePath());
		
		// Set Attribute
		request.getSession().setAttribute("imagepath", imagePath);
		request.getSession().setAttribute("directory", parentDirectory.getAbsolutePath());
		
		// Handling view
		RequestDispatcher rd = request
				.getRequestDispatcher("/ShowTeacherApprove");
		rd.forward(request, response);
	}

}
