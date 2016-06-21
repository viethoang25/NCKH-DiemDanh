package model.bo;

import model.bean.Account;
import model.dao.AccountDAO;

public class AccountBO {

	public static Account checkAccount(String username, String password) {
		return AccountDAO.getInstance().checkAccount(username, password);
	}
}
