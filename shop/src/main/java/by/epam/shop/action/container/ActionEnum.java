package by.epam.shop.action.container;

import by.epam.shop.action.Action;
import by.epam.shop.action.locale.ChangeLocaleAction;
import by.epam.shop.action.user.LogInAction;
import by.epam.shop.action.user.RegisterAction;

public enum ActionEnum {
	LOGIN {
		{
			this.action = new LogInAction();
		}
	},
	LOGOUT, REGISTRATION {
		{
			this.action = new RegisterAction();
		}
	},
	CHANGE_LOCALE {
		{
			this.action = new ChangeLocaleAction();
		}
	};
	Action action;

	public Action getCurrentAction() {
		return action;
	}
}
