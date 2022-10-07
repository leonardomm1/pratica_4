import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './padaria.reducer';

export const PadariaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const padariaEntity = useAppSelector(state => state.padaria.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="padariaDetailsHeading">Padaria</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{padariaEntity.id}</dd>
          <dt>
            <span id="nome">Nome</span>
          </dt>
          <dd>{padariaEntity.nome}</dd>
          <dt>
            <span id="fantasia">Fantasia</span>
          </dt>
          <dd>{padariaEntity.fantasia}</dd>
          <dt>
            <span id="telefone">Telefone</span>
          </dt>
          <dd>{padariaEntity.telefone}</dd>
          <dt>
            <span id="cnpj">Cnpj</span>
          </dt>
          <dd>{padariaEntity.cnpj}</dd>
          <dt>
            <span id="cep">Cep</span>
          </dt>
          <dd>{padariaEntity.cep}</dd>
          <dt>
            <span id="rua">Rua</span>
          </dt>
          <dd>{padariaEntity.rua}</dd>
          <dt>
            <span id="bairro">Bairro</span>
          </dt>
          <dd>{padariaEntity.bairro}</dd>
          <dt>
            <span id="estado">Estado</span>
          </dt>
          <dd>{padariaEntity.estado}</dd>
          <dt>
            <span id="numero">Numero</span>
          </dt>
          <dd>{padariaEntity.numero}</dd>
          <dt>
            <span id="complemento">Complemento</span>
          </dt>
          <dd>{padariaEntity.complemento}</dd>
          <dt>
            <span id="contato">Contato</span>
          </dt>
          <dd>{padariaEntity.contato}</dd>
          <dt>User</dt>
          <dd>{padariaEntity.user ? padariaEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/padaria" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Voltar</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/padaria/${padariaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PadariaDetail;
