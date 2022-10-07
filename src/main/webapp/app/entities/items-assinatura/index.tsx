import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ItemsAssinatura from './items-assinatura';
import ItemsAssinaturaDetail from './items-assinatura-detail';
import ItemsAssinaturaUpdate from './items-assinatura-update';
import ItemsAssinaturaDeleteDialog from './items-assinatura-delete-dialog';

const ItemsAssinaturaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ItemsAssinatura />} />
    <Route path="new" element={<ItemsAssinaturaUpdate />} />
    <Route path=":id">
      <Route index element={<ItemsAssinaturaDetail />} />
      <Route path="edit" element={<ItemsAssinaturaUpdate />} />
      <Route path="delete" element={<ItemsAssinaturaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ItemsAssinaturaRoutes;
