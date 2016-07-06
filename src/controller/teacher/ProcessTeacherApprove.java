package controller.teacher;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Constants;
import model.bean.Coordinate;
import model.bean.Student;
import model.bo.CoordinateBO;
import model.bo.StudentBO;

@WebServlet("/ProcessTeacherApprove")
public class ProcessTeacherApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessTeacherApprove() {
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

		// Get Attribute
		String directory = (String) request.getSession().getAttribute("directory");
		String imagePath = (String) request.getSession().getAttribute(
				"imagepath");
		String unitId = (String) request.getSession().getAttribute("unitid");
		
		// Get Parameter
		String action = (String) request.getParameter("action");

		List<Student> listStudent = StudentBO.getStudentsByUnit(unitId);
		request.setAttribute("liststudent", listStudent);

		if (action.equals("apply")) {
			System.out.println("ProcessTeacherApprove : Apply");
			// Create Approved file
			new File(directory + "/" + Constants.FILE_NAME_APPROVED).createNewFile();
			
			// Get data and time
			String[] item = directory.split("\\\\");
			String date = item[item.length - 2];
			String time = item[item.length - 1];
			System.out.println("ProcessTeacherApprove : " + date + " " + time);
			
			List<Coordinate> listCoor = CoordinateBO.getAllCoordinateUnique(directory + "/" + Constants.FILE_NAME_COORDINATES_STUDENT);
			for (Coordinate coor : listCoor) {
				
				// Save image
				String desPath = Constants.DIRECTORY_IMAGES + "/" + coor.getStudentId() + "/" + unitId + "/" + date + "-" + time + ".jpg";
				new File(desPath).createNewFile();
				
				BufferedImage src = ImageIO.read(new File(Constants.SERVLET_REAL_PATH + "/" + imagePath));

				System.out.println("ProcessTeacherApprove : Check image exitst : " + Constants.SERVLET_REAL_PATH + imagePath);
				System.out.println("ProcessTeacherApprove : Check image exitst : " + new File(Constants.SERVLET_REAL_PATH + "/" + imagePath).exists());
				int width = Math.abs(coor.getX2() - coor.getX1());
				int height = Math.abs(coor.getY2() - coor.getY1());
				BufferedImage img = src.getSubimage(coor.getX1(), coor.getY1(), width, height);
				BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				dst.getGraphics().drawImage(img, 0, 0, null);
				ImageIO.write(dst, "jpg", new File(desPath));
				dst.flush();
				img.flush();
			}
			
			// Open ShowTeacherUnits view
			RequestDispatcher rd = request
					.getRequestDispatcher("/ShowTeacherUnits");
			rd.forward(request, response);

		} else if (action.equals("setposition")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/ShowTeacherSetPosition");
			rd.forward(request, response);

		}
	}

}
