package model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manager.Constants;
import model.bean.Account;

public class AccountDAO {

	private static AccountDAO instance = new AccountDAO();

	private AccountDAO() {

	}

	public static AccountDAO getInstance() {
		return instance;
	}
	
	public Account checkAccount(String username, String password) {
		Account account = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.FILE_ACCOUNT));
			String line = br.readLine();
			List<String> listId = new ArrayList<String>();
			while ((line = br.readLine()) != null && line.length() > 0) {
				String[] item = line.split("\\|");
				if (item[0].equals(username) && item[1].equals(password)) {
					account = new Account(item[0], item[1], item[2], item[3]);
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
		return account;
	}
}
