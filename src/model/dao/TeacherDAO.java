package model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manager.Constants;
import model.bean.Teacher;
import model.bean.Unit;

public class TeacherDAO {

	private static TeacherDAO instance = new TeacherDAO();

	private TeacherDAO() {

	}

	public static TeacherDAO getInstance() {
		return instance;
	}
	
	public List<Teacher> getAllTeachers() {
		List<Teacher> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(Constants.FILE_TEACHERS_LIST));
			String line = br.readLine();
			List<String> listId = new ArrayList<String>();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (!listId.contains(item[0])) {
					listId.add(item[0]);
					list.add(new Teacher(item[0], item[1]));
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
	
}
