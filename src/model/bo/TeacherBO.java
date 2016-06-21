package model.bo;

import java.util.List;

import model.bean.Teacher;
import model.bean.Unit;
import model.dao.TeacherDAO;

public class TeacherBO {

	public static List<Teacher> getAllTeachers() {
		return TeacherDAO.getInstance().getAllTeachers();
	}

	public static List<Unit> getUnitsOfTeacher(String teacherId) {
		return TeacherDAO.getInstance().getUnitsOfTeacher(teacherId);
	}
	
}
