import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Assinatura from './assinatura';
import AssinaturaDetail from './assinatura-detail';
import AssinaturaUpdate from './assinatura-update';
import AssinaturaDeleteDialog from './assinatura-delete-dialog';

const AssinaturaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Assinatura />} />
    <Route path="new" element={<AssinaturaUpdate />} />
    <Route path=":id">
      <Route index element={<AssinaturaDetail />} />
      <Route path="edit" element={<AssinaturaUpdate />} />
      <Route path="delete" element={<AssinaturaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AssinaturaRoutes;
