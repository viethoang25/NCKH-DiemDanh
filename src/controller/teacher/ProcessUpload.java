package controller.teacher;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import manager.Constants;

@WebServlet("/ProcessUpload")
public class ProcessUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessUpload() {
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
		String unitId = (String) request.getSession().getAttribute("unitid");

		String message = "Upload thành công";
		try {
			Calendar calendar = Calendar.getInstance();
			Date date = new Date();
			calendar.setTime(date);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String d = dateFormat.format(date);
			DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
			String t = timeFormat.format(date);

			String filepath = Constants.DIRECTORY_UNITS + "\\" + unitId + "\\"
					+ d + "\\" + t;
			File file = new File(filepath);
			System.out.println("File create : " + file.getAbsolutePath());
			if (!file.exists()) {
				System.out.println(file.mkdirs());
				System.out.println(file.isDirectory());
			}

			if (ServletFileUpload.isMultipartContent(request)) {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory())
						.parseRequest(new ServletRequestContext(request));
				System.out.println(multiparts.size());
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(filepath + "\\" + name));
					}
				}
				new File(filepath + "\\"
						+ Constants.FILE_NAME_COORDINATES_STUDENT)
						.createNewFile();
				new File(filepath + "\\"
						+ Constants.FILE_NAME_COORDINATES_SYSTEM)
						.createNewFile();

			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Upload thất bại";
		}

		System.out.println("Upload unit id : " + unitId);

		// Set Attribute
		request.setAttribute("message", message);
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/ShowTeacherAction");
		rd.include(request, response);
	}

}
