import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAssinatura } from 'app/shared/model/assinatura.model';
import { getEntities } from './assinatura.reducer';

export const Assinatura = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const assinaturaList = useAppSelector(state => state.assinatura.entities);
  const loading = useAppSelector(state => state.assinatura.loading);
  const totalItems = useAppSelector(state => state.assinatura.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  return (
    <div>
      <h2 id="assinatura-heading" data-cy="AssinaturaHeading">
        Assinaturas
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Atualizar lista
          </Button>
          <Link to="/assinatura/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Criar novo Assinatura
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {assinaturaList && assinaturaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('valor')}>
                  Valor <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nome')}>
                  Nome <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('quantidadeDias')}>
                  Quantidade Dias <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ativa')}>
                  Ativa <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('pagamentoRecorrenciaId')}>
                  Pagamento Recorrencia Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('foto')}>
                  Foto <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('quantidade')}>
                  Quantidade <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('horarioRecebimento')}>
                  Horario Recebimento <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tipoAssinatura')}>
                  Tipo Assinatura <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('diaDaSemana')}>
                  Dia Da Semana <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Padaria <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  User <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {assinaturaList.map((assinatura, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/assinatura/${assinatura.id}`} color="link" size="sm">
                      {assinatura.id}
                    </Button>
                  </td>
                  <td>{assinatura.valor}</td>
                  <td>{assinatura.nome}</td>
                  <td>{assinatura.quantidadeDias}</td>
                  <td>{assinatura.ativa ? 'true' : 'false'}</td>
                  <td>{assinatura.pagamentoRecorrenciaId}</td>
                  <td>
                    {assinatura.foto ? (
                      <div>
                        {assinatura.fotoContentType ? (
                          <a onClick={openFile(assinatura.fotoContentType, assinatura.foto)}>
                            <img src={`data:${assinatura.fotoContentType};base64,${assinatura.foto}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {assinatura.fotoContentType}, {byteSize(assinatura.foto)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{assinatura.quantidade}</td>
                  <td>
                    {assinatura.horarioRecebimento ? (
                      <TextFormat type="date" value={assinatura.horarioRecebimento} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{assinatura.tipoAssinatura}</td>
                  <td>{assinatura.diaDaSemana}</td>
                  <td>{assinatura.padaria ? <Link to={`/padaria/${assinatura.padaria.id}`}>{assinatura.padaria.id}</Link> : ''}</td>
                  <td>{assinatura.user ? assinatura.user.id : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/assinatura/${assinatura.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Visualizar</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/assinatura/${assinatura.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/assinatura/${assinatura.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Excluir</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">Nenhum Assinatura encontrado</div>
        )}
      </div>
      {totalItems ? (
        <div className={assinaturaList && assinaturaList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Assinatura;
