package model.bo;

import java.util.List;

import model.bean.Unit;
import model.dao.StudentDAO;
import model.dao.UnitDAO;

public class UnitBO {

	public static List<Unit> getUnitsOfStudent(String studentId) {
		return UnitDAO.getInstance().getUnitsOfStudent(studentId);
	}
	
	public static List<Unit> getAllUnits() {
		return UnitDAO.getInstance().getAllUnits();
	}
}
