import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderMain from './order-main';
import OrderMainDetail from './order-main-detail';
import OrderMainUpdate from './order-main-update';
import OrderMainDeleteDialog from './order-main-delete-dialog';

const OrderMainRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OrderMain />} />
    <Route path="new" element={<OrderMainUpdate />} />
    <Route path=":id">
      <Route index element={<OrderMainDetail />} />
      <Route path="edit" element={<OrderMainUpdate />} />
      <Route path="delete" element={<OrderMainDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OrderMainRoutes;
