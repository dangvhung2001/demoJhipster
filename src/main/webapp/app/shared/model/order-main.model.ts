import dayjs from 'dayjs';
import { ICart } from 'app/shared/model/cart.model';
import { IUser } from 'app/shared/model/user.model';
import { OrderMainStatusEnum } from 'app/shared/model/enumerations/order-main-status-enum.model';

export interface IOrderMain {
  id?: number;
  buyerLogin?: string | null;
  buyerFirstName?: string | null;
  buyerLastName?: string | null;
  buyerEmail?: string | null;
  buyerPhone?: string | null;
  amountOfCartNet?: number | null;
  amountOfCartGross?: number | null;
  amountOfShipmentNet?: number | null;
  amountOfShipmentGross?: number | null;
  amountOfOrderNet?: number | null;
  amountOfOrderGross?: number | null;
  orderMainStatus?: keyof typeof OrderMainStatusEnum | null;
  createTime?: dayjs.Dayjs | null;
  updateTime?: dayjs.Dayjs | null;
  carts?: ICart[] | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IOrderMain> = {};
