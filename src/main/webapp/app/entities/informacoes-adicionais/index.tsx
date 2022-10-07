import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InformacoesAdicionais from './informacoes-adicionais';
import InformacoesAdicionaisDetail from './informacoes-adicionais-detail';
import InformacoesAdicionaisUpdate from './informacoes-adicionais-update';
import InformacoesAdicionaisDeleteDialog from './informacoes-adicionais-delete-dialog';

const InformacoesAdicionaisRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InformacoesAdicionais />} />
    <Route path="new" element={<InformacoesAdicionaisUpdate />} />
    <Route path=":id">
      <Route index element={<InformacoesAdicionaisDetail />} />
      <Route path="edit" element={<InformacoesAdicionaisUpdate />} />
      <Route path="delete" element={<InformacoesAdicionaisDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InformacoesAdicionaisRoutes;
