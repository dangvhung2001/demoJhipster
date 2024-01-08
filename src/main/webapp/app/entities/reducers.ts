import cart from 'app/entities/cart/cart.reducer';
import cartItem from 'app/entities/cart-item/cart-item.reducer';
import orderMain from 'app/entities/order-main/order-main.reducer';
import product from 'app/entities/product/product.reducer';
import shipmentCart from 'app/entities/shipment-cart/shipment-cart.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  cart,
  cartItem,
  orderMain,
  product,
  shipmentCart,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
