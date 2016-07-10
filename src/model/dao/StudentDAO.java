package model.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import manager.Constants;
import model.bean.Student;
import model.bean.Unit;

public class StudentDAO {

	private static StudentDAO instance = new StudentDAO();

	private StudentDAO() {

	}

	public static StudentDAO getInstance() {
		return instance;
	}
	
	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					Constants.FILE_STUDENTS_LIST), "UTF-8"));
			String line = br.readLine();
			List<String> listId = new ArrayList<String>();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (!listId.contains(item[0])) {
					listId.add(item[0]);
					list.add(new Student(item[0], item[1]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public List<Student> getStudentsByUnit(String unitId) {
		List<Student> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					Constants.FILE_STUDENTS_LIST), "UTF-8"));
			String line = br.readLine();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[2].equals(unitId)) {
					list.add(new Student(item[0], item[1]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public Student getStudentById(String id) {
		Student student = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					Constants.FILE_STUDENTS_LIST), "UTF-8"));
			String line = br.readLine();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[0].equals(id)) {
					student = new Student(item[0], item[1]);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return student;
	}
}
