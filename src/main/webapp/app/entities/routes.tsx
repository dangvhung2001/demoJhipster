import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Cart from './cart';
import CartItem from './cart-item';
import OrderMain from './order-main';
import Product from './product';
import ShipmentCart from './shipment-cart';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="cart/*" element={<Cart />} />
        <Route path="cart-item/*" element={<CartItem />} />
        <Route path="order-main/*" element={<OrderMain />} />
        <Route path="product/*" element={<Product />} />
        <Route path="shipment-cart/*" element={<ShipmentCart />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
