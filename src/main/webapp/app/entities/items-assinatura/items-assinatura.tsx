import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IItemsAssinatura } from 'app/shared/model/items-assinatura.model';
import { getEntities } from './items-assinatura.reducer';

export const ItemsAssinatura = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const itemsAssinaturaList = useAppSelector(state => state.itemsAssinatura.entities);
  const loading = useAppSelector(state => state.itemsAssinatura.loading);
  const totalItems = useAppSelector(state => state.itemsAssinatura.totalItems);

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
      <h2 id="items-assinatura-heading" data-cy="ItemsAssinaturaHeading">
        Items Assinaturas
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Atualizar lista
          </Button>
          <Link to="/items-assinatura/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Criar novo Items Assinatura
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {itemsAssinaturaList && itemsAssinaturaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('padariaId')}>
                  Padaria Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nome')}>
                  Nome <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('valor')}>
                  Valor <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('foto')}>
                  Foto <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('quantidade')}>
                  Quantidade <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Assinatura <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemsAssinaturaList.map((itemsAssinatura, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/items-assinatura/${itemsAssinatura.id}`} color="link" size="sm">
                      {itemsAssinatura.id}
                    </Button>
                  </td>
                  <td>{itemsAssinatura.padariaId}</td>
                  <td>{itemsAssinatura.nome}</td>
                  <td>{itemsAssinatura.valor}</td>
                  <td>
                    {itemsAssinatura.foto ? (
                      <div>
                        {itemsAssinatura.fotoContentType ? (
                          <a onClick={openFile(itemsAssinatura.fotoContentType, itemsAssinatura.foto)}>
                            <img
                              src={`data:${itemsAssinatura.fotoContentType};base64,${itemsAssinatura.foto}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {itemsAssinatura.fotoContentType}, {byteSize(itemsAssinatura.foto)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{itemsAssinatura.quantidade}</td>
                  <td>
                    {itemsAssinatura.assinatura ? (
                      <Link to={`/assinatura/${itemsAssinatura.assinatura.id}`}>{itemsAssinatura.assinatura.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/items-assinatura/${itemsAssinatura.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Visualizar</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/items-assinatura/${itemsAssinatura.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/items-assinatura/${itemsAssinatura.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
          !loading && <div className="alert alert-warning">Nenhum Items Assinatura encontrado</div>
        )}
      </div>
      {totalItems ? (
        <div className={itemsAssinaturaList && itemsAssinaturaList.length > 0 ? '' : 'd-none'}>
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

export default ItemsAssinatura;
