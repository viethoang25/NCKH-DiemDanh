package model.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import manager.Constants;
import manager.DateManager;

public class FileBO {

	public static String getFileExtension(String name) {
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static List<String> getImagesByUnit(String unitId) {
		List<String> files = new ArrayList<String>();
		String directory = Constants.DIRECTORY_UNITS + "/" + unitId;
		System.out.println("FileBO : " + "Directory : " + directory);
		listFiles(directory, files);
		return files;
	}
	
	private static void listFiles(String directoryName, List<String> files) {
		File directory = new File(directoryName);
		
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				if (getFileExtension(file.getName()).equals("jpg")){
					int index = file.getAbsolutePath().indexOf("\\data");
					String path = file.getAbsolutePath().substring(index);
					files.add(path);
				}
			} else if (file.isDirectory()) {
				listFiles(file.getAbsolutePath(), files);
			}
		}
	}
	


	public static List<String> getImagesByUnitAndDate(String unitId, String dateTime) {
		List<String> files = new ArrayList<String>();
		List<String> images = getImagesByUnit(unitId);
		for(String path : images){
			String[] items = path.split("\\\\");
			System.out.println(items[0]);
			String date = items[items.length - 3];
			String time = items[items.length - 2];

			path = path.replaceAll("\\\\", "/");
			if(DateManager.getHoursBetween(date + " " + time, dateTime) >= 0) {
				files.add(path);
			}
		}
		return files;
	}
	
}
