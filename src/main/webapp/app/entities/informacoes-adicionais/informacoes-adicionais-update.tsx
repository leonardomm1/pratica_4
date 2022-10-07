import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IInformacoesAdicionais } from 'app/shared/model/informacoes-adicionais.model';
import { getEntity, updateEntity, createEntity, reset } from './informacoes-adicionais.reducer';

export const InformacoesAdicionaisUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const informacoesAdicionaisEntity = useAppSelector(state => state.informacoesAdicionais.entity);
  const loading = useAppSelector(state => state.informacoesAdicionais.loading);
  const updating = useAppSelector(state => state.informacoesAdicionais.updating);
  const updateSuccess = useAppSelector(state => state.informacoesAdicionais.updateSuccess);

  const handleClose = () => {
    navigate('/informacoes-adicionais' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...informacoesAdicionaisEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...informacoesAdicionaisEntity,
          user: informacoesAdicionaisEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pratica4App.informacoesAdicionais.home.createOrEditLabel" data-cy="InformacoesAdicionaisCreateUpdateHeading">
            Criar ou editar Informacoes Adicionais
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="informacoes-adicionais-id" label="Id" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Telefone"
                id="informacoes-adicionais-telefone"
                name="telefone"
                data-cy="telefone"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 10, message: 'Este campo deve ter pelo menos 10 caracteres.' },
                  maxLength: { value: 15, message: 'Este campo não pode ter mais de 15 caracteres.' },
                }}
              />
              <ValidatedField
                label="Cpf"
                id="informacoes-adicionais-cpf"
                name="cpf"
                data-cy="cpf"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 11, message: 'Este campo deve ter pelo menos 11 caracteres.' },
                  maxLength: { value: 11, message: 'Este campo não pode ter mais de 11 caracteres.' },
                }}
              />
              <ValidatedField
                label="Cep"
                id="informacoes-adicionais-cep"
                name="cep"
                data-cy="cep"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 8, message: 'Este campo deve ter pelo menos 8 caracteres.' },
                  maxLength: { value: 8, message: 'Este campo não pode ter mais de 8 caracteres.' },
                }}
              />
              <ValidatedField
                label="Rua"
                id="informacoes-adicionais-rua"
                name="rua"
                data-cy="rua"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 3, message: 'Este campo deve ter pelo menos 3 caracteres.' },
                  maxLength: { value: 255, message: 'Este campo não pode ter mais de 255 caracteres.' },
                }}
              />
              <ValidatedField
                label="Bairro"
                id="informacoes-adicionais-bairro"
                name="bairro"
                data-cy="bairro"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 3, message: 'Este campo deve ter pelo menos 3 caracteres.' },
                  maxLength: { value: 255, message: 'Este campo não pode ter mais de 255 caracteres.' },
                }}
              />
              <ValidatedField
                label="Estado"
                id="informacoes-adicionais-estado"
                name="estado"
                data-cy="estado"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 2, message: 'Este campo deve ter pelo menos 2 caracteres.' },
                  maxLength: { value: 2, message: 'Este campo não pode ter mais de 2 caracteres.' },
                }}
              />
              <ValidatedField
                label="Numero"
                id="informacoes-adicionais-numero"
                name="numero"
                data-cy="numero"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 1, message: 'Este campo deve ter pelo menos 1 caracteres.' },
                  maxLength: { value: 255, message: 'Este campo não pode ter mais de 255 caracteres.' },
                }}
              />
              <ValidatedField
                label="Complemento"
                id="informacoes-adicionais-complemento"
                name="complemento"
                data-cy="complemento"
                type="text"
              />
              <ValidatedField id="informacoes-adicionais-user" name="user" data-cy="user" label="User" type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/informacoes-adicionais" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Voltar</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Salvar
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default InformacoesAdicionaisUpdate;
