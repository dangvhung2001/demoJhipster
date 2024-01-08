import { IShipmentCart } from 'app/shared/model/shipment-cart.model';
import { IUser } from 'app/shared/model/user.model';
import { ICartItem } from 'app/shared/model/cart-item.model';
import { IOrderMain } from 'app/shared/model/order-main.model';

export interface ICart {
  id?: number;
  amountOfCartNet?: number | null;
  amountOfCartGross?: number | null;
  amountOfShipmentNet?: number | null;
  amountOfShipmentGross?: number | null;
  amountOfOrderNet?: number | null;
  amountOfOrderGross?: number | null;
  quantity?: number | null;
  shipmentCart?: IShipmentCart | null;
  user?: IUser | null;
  cartItems?: ICartItem[] | null;
  orderMain?: IOrderMain | null;
}

export const defaultValue: Readonly<ICart> = {};
