package by.epam.shop.action.container;

import by.epam.shop.action.Action;
import by.epam.shop.action.user.LoginAction;

public enum ActionEnum {
	LOGIN {
		{
			this.action = new LoginAction();
		}
	},
	LOGOUT, REGISTRATION;
	Action action;

	public Action getCurrentAction() {
		return action;
	}
}
