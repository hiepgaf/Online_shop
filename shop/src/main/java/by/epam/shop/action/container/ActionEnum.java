package by.epam.shop.action.container;

import by.epam.shop.action.Action;
import by.epam.shop.action.user.LogInAction;

public enum ActionEnum {
	LOGIN {
		{
			this.action = new LogInAction();
		}
	},
	LOGOUT, REGISTRATION;
	Action action;

	public Action getCurrentAction() {
		return action;
	}
}
