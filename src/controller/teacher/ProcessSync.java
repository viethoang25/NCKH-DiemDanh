package controller.teacher;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.box.sdk.BoxItem;

import manager.BoxApi;
import manager.Constants;

@WebServlet("/ProcessSync")
public class ProcessSync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessSync() {
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

		BoxApi api = BoxApi.getInstance();
		HashMap<String, BoxItem.Info> fileMap = getFilesOfUnit(unitId);
		for(String filePath : fileMap.keySet()) {
			BoxItem.Info itemFile = fileMap.get(filePath);
			if(new File(filePath + "\\" + itemFile.getName()).exists()) continue;
			File downloadFile = api.downloadFile(itemFile.getID(), filePath + "\\" + itemFile.getName());
			new File(filePath + "\\"
					+ Constants.FILE_NAME_COORDINATES_STUDENT)
					.createNewFile();
			new File(filePath + "\\"
					+ Constants.FILE_NAME_COORDINATES_SYSTEM)
					.createNewFile();
		}
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/ShowTeacherAction");
		rd.include(request, response);
	}
	
	private HashMap<String, BoxItem.Info> getFilesOfUnit(String unitId) {
		HashMap<String, BoxItem.Info> fileMap = new HashMap<>();
		String unitFolderId = null;
		BoxApi api = BoxApi.getInstance();
		for (BoxItem.Info item : api.getFolderItems(api.getRootFolder())) {
			if (item.getName().equals(unitId)) {
				unitFolderId = item.getID();
			}
		}
		if (unitFolderId == null) {
			unitFolderId = api.createFolder(api.getRootFolder(), unitId)
					.getID();
		}
		for (BoxItem.Info itemDate : api.getFolderItems(api
				.getBoxFolder(unitFolderId))) {
			for (BoxItem.Info itemTime : api.getFolderItems(api
					.getBoxFolder(itemDate.getID()))) {
				for (BoxItem.Info itemFile : api.getFolderItems(api
						.getBoxFolder(itemTime.getID()))) {
					StringBuilder builder = new StringBuilder();
					builder.append(Constants.DIRECTORY_UNITS);
					builder.append("\\");
					builder.append(unitId);
					builder.append("\\");
					builder.append(itemDate.getName());
					builder.append("\\");
					builder.append(itemTime.getName());
					fileMap.put(builder.toString(), itemFile);
				}
			}
		}
		return fileMap;
	}

}
