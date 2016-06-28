package model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manager.Constants;
import model.bean.Unit;

public class UnitDAO {

	private static UnitDAO instance = new UnitDAO();

	private UnitDAO() {

	}

	public static UnitDAO getInstance() {
		return instance;
	}
	
	public List<Unit> getAllUnits() {
		List<Unit> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(Constants.FILE_STUDENTS_LIST));
			String line = br.readLine();
			List<String> listId = new ArrayList<String>();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (!listId.contains(item[2])) {
					listId.add(item[2]);
					list.add(new Unit(item[2], item[3]));
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
	
	public List<Unit> getUnitsOfStudent(String studentId) {
		List<Unit> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(Constants.FILE_STUDENTS_LIST));
			String line = br.readLine();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[0].equals(studentId)) {
					list.add(new Unit(item[2], item[3]));
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
	
	public List<Unit> getUnitsOfTeacher(String teacherId) {
		List<Unit> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(Constants.FILE_TEACHERS_LIST));
			String line = br.readLine();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[0].equals(teacherId)) {
					list.add(new Unit(item[2], item[3]));
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
