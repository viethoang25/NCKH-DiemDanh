package model.bo;

import java.util.List;

import model.bean.Teacher;
import model.bean.Unit;
import model.dao.TeacherDAO;

public class TeacherBO {

	public static List<Teacher> getAllTeachers() {
		return TeacherDAO.getInstance().getAllTeachers();
	}
	
}
