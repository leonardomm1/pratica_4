import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './items-assinatura.reducer';

export const ItemsAssinaturaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const itemsAssinaturaEntity = useAppSelector(state => state.itemsAssinatura.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="itemsAssinaturaDetailsHeading">Items Assinatura</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{itemsAssinaturaEntity.id}</dd>
          <dt>
            <span id="padariaId">Padaria Id</span>
          </dt>
          <dd>{itemsAssinaturaEntity.padariaId}</dd>
          <dt>
            <span id="nome">Nome</span>
          </dt>
          <dd>{itemsAssinaturaEntity.nome}</dd>
          <dt>
            <span id="valor">Valor</span>
          </dt>
          <dd>{itemsAssinaturaEntity.valor}</dd>
          <dt>
            <span id="foto">Foto</span>
          </dt>
          <dd>
            {itemsAssinaturaEntity.foto ? (
              <div>
                {itemsAssinaturaEntity.fotoContentType ? (
                  <a onClick={openFile(itemsAssinaturaEntity.fotoContentType, itemsAssinaturaEntity.foto)}>
                    <img
                      src={`data:${itemsAssinaturaEntity.fotoContentType};base64,${itemsAssinaturaEntity.foto}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {itemsAssinaturaEntity.fotoContentType}, {byteSize(itemsAssinaturaEntity.foto)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="quantidade">Quantidade</span>
          </dt>
          <dd>{itemsAssinaturaEntity.quantidade}</dd>
          <dt>Assinatura</dt>
          <dd>{itemsAssinaturaEntity.assinatura ? itemsAssinaturaEntity.assinatura.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/items-assinatura" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Voltar</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/items-assinatura/${itemsAssinaturaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ItemsAssinaturaDetail;
