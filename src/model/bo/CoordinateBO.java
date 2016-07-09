package model.bo;

import java.util.List;

import model.bean.Coordinate;
import model.dao.CoordinateDAO;

public class CoordinateBO {

	public static List<Coordinate> getAllCoordinate(String filePath) {
		return CoordinateDAO.getInstance().getAllCoordinate(filePath);
	}

	public static Coordinate getCoordinate(String filePath, String studentId) {
		return CoordinateDAO.getInstance().getCoordinate(filePath, studentId);
	}
	
	public static void writeCoordinate(String filePath, Coordinate coor) {
		CoordinateDAO.getInstance().writeCoordinate(filePath, coor);
	}
	
	public static List<Coordinate> getAllCoordinateUnique(String filePath) {
		return CoordinateDAO.getInstance().getAllCoordinateUnique(filePath);
	}
	
	public static int countStudentApproveTimes(String filePath, String studentId) {
		return CoordinateDAO.getInstance().countStudentApproveTimes(filePath, studentId);
	}
}
