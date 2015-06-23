package by.epam.shop.action.container;

import by.epam.shop.action.Action;
import by.epam.shop.action.locale.ChangeLocaleAction;
import by.epam.shop.action.order.MakeOrderAction;
import by.epam.shop.action.page.GoToPageAction;
import by.epam.shop.action.product.ShowProductsAction;
import by.epam.shop.action.user.AddToShoppingCartAction;
import by.epam.shop.action.user.LogInAction;
import by.epam.shop.action.user.LogOutAction;
import by.epam.shop.action.user.RegisterAction;
import by.epam.shop.action.user.RemoveFromShoppingCartAction;
import by.epam.shop.action.user.ShowCartAction;

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
	},
	SHOW_CART_ACTION {
		{
			action = new ShowCartAction();
		}
	},
	REMOVE_FROM_CART {
		{
			action = new RemoveFromShoppingCartAction();
		}
	},
	GO_TO_PAGE {
		{
			action = new GoToPageAction();
		}
	},
	MAKE_ORDER {
		{
			action = new MakeOrderAction();
		}
	};
	Action action;

	public Action getCurrentAction() {
		return action;
	}
}
