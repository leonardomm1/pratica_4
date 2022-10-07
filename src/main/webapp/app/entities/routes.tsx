import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InformacoesAdicionais from './informacoes-adicionais';
import Padaria from './padaria';
import Assinatura from './assinatura';
import ItemsAssinatura from './items-assinatura';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="informacoes-adicionais/*" element={<InformacoesAdicionais />} />
        <Route path="padaria/*" element={<Padaria />} />
        <Route path="assinatura/*" element={<Assinatura />} />
        <Route path="items-assinatura/*" element={<ItemsAssinatura />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
