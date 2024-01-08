import dayjs from 'dayjs';
import { ProductCategoryEnum } from 'app/shared/model/enumerations/product-category-enum.model';

export interface IProduct {
  id?: number;
  productCategoryEnum?: keyof typeof ProductCategoryEnum | null;
  name?: string | null;
  quantity?: number | null;
  priceNet?: number | null;
  vat?: number | null;
  priceGross?: number | null;
  stock?: number | null;
  description?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateTime?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IProduct> = {};
