package model.bo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manager.Constants;
import model.bean.Student;
import model.bean.Unit;
import model.dao.StudentDAO;

public class StudentBO {

	public static List<Student> getAllStudents() {
		return StudentDAO.getInstance().getAllStudents();
	}
	
	public static List<Student> getStudentsByUnit(String unitId) {
		return StudentDAO.getInstance().getStudentsByUnit(unitId);
	}
}
