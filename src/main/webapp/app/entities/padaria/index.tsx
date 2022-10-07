import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Padaria from './padaria';
import PadariaDetail from './padaria-detail';
import PadariaUpdate from './padaria-update';
import PadariaDeleteDialog from './padaria-delete-dialog';

const PadariaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Padaria />} />
    <Route path="new" element={<PadariaUpdate />} />
    <Route path=":id">
      <Route index element={<PadariaDetail />} />
      <Route path="edit" element={<PadariaUpdate />} />
      <Route path="delete" element={<PadariaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PadariaRoutes;
