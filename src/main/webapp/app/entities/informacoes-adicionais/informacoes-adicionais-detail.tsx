import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './informacoes-adicionais.reducer';

export const InformacoesAdicionaisDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const informacoesAdicionaisEntity = useAppSelector(state => state.informacoesAdicionais.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="informacoesAdicionaisDetailsHeading">Informacoes Adicionais</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.id}</dd>
          <dt>
            <span id="telefone">Telefone</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.telefone}</dd>
          <dt>
            <span id="cpf">Cpf</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.cpf}</dd>
          <dt>
            <span id="cep">Cep</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.cep}</dd>
          <dt>
            <span id="rua">Rua</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.rua}</dd>
          <dt>
            <span id="bairro">Bairro</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.bairro}</dd>
          <dt>
            <span id="estado">Estado</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.estado}</dd>
          <dt>
            <span id="numero">Numero</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.numero}</dd>
          <dt>
            <span id="complemento">Complemento</span>
          </dt>
          <dd>{informacoesAdicionaisEntity.complemento}</dd>
          <dt>User</dt>
          <dd>{informacoesAdicionaisEntity.user ? informacoesAdicionaisEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/informacoes-adicionais" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Voltar</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/informacoes-adicionais/${informacoesAdicionaisEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InformacoesAdicionaisDetail;
