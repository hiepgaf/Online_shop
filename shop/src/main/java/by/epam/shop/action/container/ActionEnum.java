package by.epam.shop.action.container;

import by.epam.shop.action.Action;
import by.epam.shop.action.locale.ChangeLocaleAction;
import by.epam.shop.action.order.CancelOrderAction;
import by.epam.shop.action.order.DeleteOrder;
import by.epam.shop.action.order.DeliverOrderAction;
import by.epam.shop.action.order.MakeOrderAction;
import by.epam.shop.action.order.ShowConcreteOrderAction;
import by.epam.shop.action.order.ShowOrdersAction;
import by.epam.shop.action.page.AddProductPageAction;
import by.epam.shop.action.page.AllOrdersPageAction;
import by.epam.shop.action.page.EditProductPageAction;
import by.epam.shop.action.page.GoToPageAction;
import by.epam.shop.action.page.UsersPageAction;
import by.epam.shop.action.product.AddProductAction;
import by.epam.shop.action.product.DeleteProductAction;
import by.epam.shop.action.product.EditProductAction;
import by.epam.shop.action.product.SearchProductAction;
import by.epam.shop.action.product.ShowConcreteProductAction;
import by.epam.shop.action.product.ShowProductsAction;
import by.epam.shop.action.user.AddToShoppingCartAction;
import by.epam.shop.action.user.ChangeBlockingAction;
import by.epam.shop.action.user.LogInAction;
import by.epam.shop.action.user.LogOutAction;
import by.epam.shop.action.user.RegisterAction;
import by.epam.shop.action.user.RemoveFromShoppingCartAction;
import by.epam.shop.action.user.ShowCartAction;

/**
 * The Enum ActionEnum. Contains all the actions available to the user.
 */
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
	},

	SHOW_ORDERS {
		{
			action = new ShowOrdersAction();
		}
	},

	SHOW_PRODUCT {
		{
			action = new ShowConcreteProductAction();
		}
	},

	SHOW_ORDER {
		{
			action = new ShowConcreteOrderAction();
		}
	},

	CANCEL_ORDER {
		{
			action = new CancelOrderAction();
		}
	},

	ADD_PRODUCT_PAGE {
		{
			action = new AddProductPageAction();
		}
	},

	ADD_PRODUCT {
		{
			action = new AddProductAction();
		}
	},

	ALL_ORDERS_PAGE {
		{
			action = new AllOrdersPageAction();
		}
	},

	DELETE_ORDER {
		{
			action = new DeleteOrder();
		}
	},

	DELIVER_ORDER {
		{
			action = new DeliverOrderAction();
		}
	},

	USERS_PAGE {
		{
			action = new UsersPageAction();
		}
	},

	CHANGE_BLOCKING {
		{
			action = new ChangeBlockingAction();
		}
	},

	DELETE_PRODUCT {
		{
			action = new DeleteProductAction();
		}
	},

	EDIT_PRODUCT_PAGE {
		{
			action = new EditProductPageAction();
		}
	},

	EDIT_PRODUCT {
		{
			action = new EditProductAction();
		}
	},

	SEARCH_PRODUCTS {
		{
			action = new SearchProductAction();
		}
	};

	Action action;

	/**
	 * Gets the current action.
	 *
	 * @return the current action
	 */
	public Action getCurrentAction() {
		return action;
	}
}
