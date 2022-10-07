import informacoesAdicionais from 'app/entities/informacoes-adicionais/informacoes-adicionais.reducer';
import padaria from 'app/entities/padaria/padaria.reducer';
import assinatura from 'app/entities/assinatura/assinatura.reducer';
import itemsAssinatura from 'app/entities/items-assinatura/items-assinatura.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  informacoesAdicionais,
  padaria,
  assinatura,
  itemsAssinatura,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
