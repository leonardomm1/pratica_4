import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInformacoesAdicionais } from 'app/shared/model/informacoes-adicionais.model';
import { getEntities } from './informacoes-adicionais.reducer';

export const InformacoesAdicionais = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const informacoesAdicionaisList = useAppSelector(state => state.informacoesAdicionais.entities);
  const loading = useAppSelector(state => state.informacoesAdicionais.loading);
  const totalItems = useAppSelector(state => state.informacoesAdicionais.totalItems);

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
      <h2 id="informacoes-adicionais-heading" data-cy="InformacoesAdicionaisHeading">
        Informacoes Adicionais
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Atualizar lista
          </Button>
          <Link
            to="/informacoes-adicionais/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; Criar novo Informacoes Adicionais
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {informacoesAdicionaisList && informacoesAdicionaisList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('telefone')}>
                  Telefone <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('cpf')}>
                  Cpf <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('cep')}>
                  Cep <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rua')}>
                  Rua <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('bairro')}>
                  Bairro <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estado')}>
                  Estado <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('numero')}>
                  Numero <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('complemento')}>
                  Complemento <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  User <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {informacoesAdicionaisList.map((informacoesAdicionais, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/informacoes-adicionais/${informacoesAdicionais.id}`} color="link" size="sm">
                      {informacoesAdicionais.id}
                    </Button>
                  </td>
                  <td>{informacoesAdicionais.telefone}</td>
                  <td>{informacoesAdicionais.cpf}</td>
                  <td>{informacoesAdicionais.cep}</td>
                  <td>{informacoesAdicionais.rua}</td>
                  <td>{informacoesAdicionais.bairro}</td>
                  <td>{informacoesAdicionais.estado}</td>
                  <td>{informacoesAdicionais.numero}</td>
                  <td>{informacoesAdicionais.complemento}</td>
                  <td>{informacoesAdicionais.user ? informacoesAdicionais.user.id : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/informacoes-adicionais/${informacoesAdicionais.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Visualizar</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/informacoes-adicionais/${informacoesAdicionais.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/informacoes-adicionais/${informacoesAdicionais.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
          !loading && <div className="alert alert-warning">Nenhum Informacoes Adicionais encontrado</div>
        )}
      </div>
      {totalItems ? (
        <div className={informacoesAdicionaisList && informacoesAdicionaisList.length > 0 ? '' : 'd-none'}>
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

export default InformacoesAdicionais;
