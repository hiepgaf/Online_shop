package by.epam.shop.action.container;

import by.epam.shop.action.Action;
import by.epam.shop.action.locale.ChangeLocaleAction;
import by.epam.shop.action.product.ShowProductsAction;
import by.epam.shop.action.user.AddToShoppingCartAction;
import by.epam.shop.action.user.LogInAction;
import by.epam.shop.action.user.LogOutAction;
import by.epam.shop.action.user.RegisterAction;

public enum ActionEnum {
	LOGIN {
		{
			action = new LogInAction();
		}
	},
	LOGOUT {
		{
			action = new LogOutAction();
		}
	},
	REGISTRATION {
		{
			action = new RegisterAction();
		}
	},
	CHANGE_LOCALE {
		{
			action = new ChangeLocaleAction();
		}
	},
	SHOW_PRODUCTS {
		{
			action = new ShowProductsAction();
		}
	},
	ADD_TO_SHOPPING_CART {
		{
			action = new AddToShoppingCartAction();
		}
	};
	Action action;

	public Action getCurrentAction() {
		return action;
	}
}
