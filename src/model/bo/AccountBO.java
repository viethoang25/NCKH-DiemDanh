package model.bo;

import model.bean.Account;
import model.dao.AccountDAO;

public class AccountBO {

	public static Account checkAccount(String username, String password) {
		return AccountDAO.getInstance().checkAccount(username, password);
	}
	
	public static void writeTokenKey(String tokenKey) {
		AccountDAO.getInstance().writeTokenKey(tokenKey);
	}
	
	public static String getTokenKey() {
		return AccountDAO.getInstance().getTokenKey();
	}
}
