import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ShipmentCart from './shipment-cart';
import ShipmentCartDetail from './shipment-cart-detail';
import ShipmentCartUpdate from './shipment-cart-update';
import ShipmentCartDeleteDialog from './shipment-cart-delete-dialog';

const ShipmentCartRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ShipmentCart />} />
    <Route path="new" element={<ShipmentCartUpdate />} />
    <Route path=":id">
      <Route index element={<ShipmentCartDetail />} />
      <Route path="edit" element={<ShipmentCartUpdate />} />
      <Route path="delete" element={<ShipmentCartDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ShipmentCartRoutes;
