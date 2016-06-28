package controller.login;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Constants;


@WebServlet("/ShowLogin")
public class ShowLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
				
		addServletPath();
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(request, response);
	}

	private void addServletPath() {
		File f = new File(this.getServletContext().getRealPath(""));
		String absolutePath = f.getAbsolutePath();
		System.out.println(this.getClass().getSimpleName() + " : " + absolutePath);
		if(Constants.SERVLET_REAL_PATH == null) {
			Constants.SERVLET_REAL_PATH = absolutePath;
			Constants.FILE_STUDENTS_LIST = absolutePath + Constants.FILE_STUDENTS_LIST;
			Constants.FILE_TEACHERS_LIST = absolutePath + Constants.FILE_TEACHERS_LIST;
			Constants.FILE_ACCOUNT = absolutePath + Constants.FILE_ACCOUNT;
			Constants.DIRECTORY_UNITS = absolutePath + Constants.DIRECTORY_UNITS;
			Constants.DIRECTORY_IMAGES = absolutePath + Constants.DIRECTORY_IMAGES;
		}
	}
}
