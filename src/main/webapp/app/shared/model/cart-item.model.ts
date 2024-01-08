import { IProduct } from 'app/shared/model/product.model';
import { ICart } from 'app/shared/model/cart.model';

export interface ICartItem {
  id?: number;
  quantity?: number | null;
  priceNet?: number | null;
  vat?: number | null;
  priceGross?: number | null;
  totalPriceNet?: number | null;
  totalPriceGross?: number | null;
  description?: string | null;
  product?: IProduct | null;
  cart?: ICart | null;
}

export const defaultValue: Readonly<ICartItem> = {};
