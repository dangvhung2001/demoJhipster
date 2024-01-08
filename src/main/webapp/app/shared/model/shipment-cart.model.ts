import { ICart } from 'app/shared/model/cart.model';

export interface IShipmentCart {
  id?: number;
  firstName?: string | null;
  lastName?: string | null;
  street?: string | null;
  postalCode?: string | null;
  city?: string | null;
  country?: string | null;
  phoneToTheReceiver?: string | null;
  firm?: string | null;
  taxNumber?: string | null;
  cart?: ICart | null;
}

export const defaultValue: Readonly<IShipmentCart> = {};
