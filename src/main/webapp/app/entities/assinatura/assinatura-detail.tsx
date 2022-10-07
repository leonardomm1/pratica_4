import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './assinatura.reducer';

export const AssinaturaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const assinaturaEntity = useAppSelector(state => state.assinatura.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="assinaturaDetailsHeading">Assinatura</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{assinaturaEntity.id}</dd>
          <dt>
            <span id="valor">Valor</span>
          </dt>
          <dd>{assinaturaEntity.valor}</dd>
          <dt>
            <span id="nome">Nome</span>
          </dt>
          <dd>{assinaturaEntity.nome}</dd>
          <dt>
            <span id="quantidadeDias">Quantidade Dias</span>
          </dt>
          <dd>{assinaturaEntity.quantidadeDias}</dd>
          <dt>
            <span id="ativa">Ativa</span>
          </dt>
          <dd>{assinaturaEntity.ativa ? 'true' : 'false'}</dd>
          <dt>
            <span id="pagamentoRecorrenciaId">Pagamento Recorrencia Id</span>
          </dt>
          <dd>{assinaturaEntity.pagamentoRecorrenciaId}</dd>
          <dt>
            <span id="foto">Foto</span>
          </dt>
          <dd>
            {assinaturaEntity.foto ? (
              <div>
                {assinaturaEntity.fotoContentType ? (
                  <a onClick={openFile(assinaturaEntity.fotoContentType, assinaturaEntity.foto)}>
                    <img src={`data:${assinaturaEntity.fotoContentType};base64,${assinaturaEntity.foto}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {assinaturaEntity.fotoContentType}, {byteSize(assinaturaEntity.foto)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>Padaria</dt>
          <dd>{assinaturaEntity.padaria ? assinaturaEntity.padaria.id : ''}</dd>
          <dt>User</dt>
          <dd>{assinaturaEntity.user ? assinaturaEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/assinatura" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Voltar</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/assinatura/${assinaturaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AssinaturaDetail;
