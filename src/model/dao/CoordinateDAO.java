package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Coordinate;

public class CoordinateDAO {

	private static CoordinateDAO instance = new CoordinateDAO();

	private CoordinateDAO() {

	}

	public static CoordinateDAO getInstance() {
		return instance;
	}

	public List<Coordinate> getAllCoordinate(String filePath) {
		List<Coordinate> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			// String line = br.readLine();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				list.add(new Coordinate(item[0], Integer.parseInt(item[1]),
						Integer.parseInt(item[2]), Integer.parseInt(item[3]),
						Integer.parseInt(item[4])));
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

	public List<Coordinate> getAllCoordinateUnique(String filePath) {
		List<Coordinate> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			// String line = br.readLine();
			// System.out.println(line);
			// line = br.readLine();
			// System.out.println(line);
			List<String> listId = new ArrayList<String>();
			while ((line = br.readLine()) != null && line.length() > 0) {
				System.out.println(line);
				String[] item = line.split("\\|");
				int index = listId.indexOf(item[0]);
				Coordinate coor = new Coordinate(item[0],
						Integer.parseInt(item[1]), Integer.parseInt(item[2]),
						Integer.parseInt(item[3]), Integer.parseInt(item[4]));
				if (index == -1) {
					listId.add(item[0]);
					list.add(coor);
				} else {
					list.set(index, coor);
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

	public Coordinate getCoordinate(String filePath, String studentId) {
		Coordinate coor = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[0].equals(studentId)) {
					coor = new Coordinate(item[0], Integer.parseInt(item[1]),
							Integer.parseInt(item[2]),
							Integer.parseInt(item[3]),
							Integer.parseInt(item[4]));
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
		return coor;
	}

	public int countStudentApproveTimes(String filePath, String studentId) {
		int count = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[0].equals(studentId)) {
					count++;
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
		return count;
	}

	public void writeCoordinate(String filePath, Coordinate coor) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write(coor.getStudentId() + "|" + coor.getX1() + "|"
					+ coor.getY1() + "|" + coor.getX2() + "|" + coor.getY2());
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
				}
		}
	}
}
