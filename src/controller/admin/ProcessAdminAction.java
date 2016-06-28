package controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import manager.Constants;
import model.bean.Student;
import model.bean.Unit;
import model.bo.StudentBO;
import model.bo.UnitBO;

@WebServlet("/ProcessAdminAction")
public class ProcessAdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessAdminAction() {
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

		String action = (String) request.getParameter("action");

		if (action.equals("create")) {
			for (Unit unit : UnitBO.getAllUnits()) {
				File file = new File(Constants.DIRECTORY_UNITS + "\\"
						+ unit.getId());
				if (!file.exists()) {
					file.mkdirs();
				}
			}

			for (Student student : StudentBO.getAllStudents()) {
				for (Unit unit : UnitBO.getUnitsOfStudent(student.getId())) {
					File file = new File(Constants.DIRECTORY_IMAGES + "\\"
							+ student.getId() + "\\" + unit.getId());
					if (!file.exists()) {
						file.mkdirs();
					}
				}
			}

			out.println("<script type=\"text/javascript\">");
			out.println("alert('Ghi thông tin thành công');");
			out.println("</script>");

			RequestDispatcher rd = request
					.getRequestDispatcher("/ShowAdminAction");
			rd.include(request, response);

		} else if (action.equals("delete")) {
			String message = "Xóa dữ liệu thành công";
			try {
				FileUtils.cleanDirectory(new File(Constants.DIRECTORY_UNITS));
				FileUtils.cleanDirectory(new File(Constants.DIRECTORY_IMAGES));
			} catch (Exception e) {
				message = "Xóa dữ liệu thất bại";
			}
			out.println("<script type=\"text/javascript\">");
			out.println("alert('" + message + "');");
			out.println("</script>");

			RequestDispatcher rd = request
					.getRequestDispatcher("/ShowAdminAction");
			rd.include(request, response);
		} else if (action.equals("logout")) {
			response.sendRedirect(request.getContextPath() + "/ShowLogin");
		}
	}

}
