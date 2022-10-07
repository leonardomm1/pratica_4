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
import { IPadaria } from 'app/shared/model/padaria.model';
import { getEntity, updateEntity, createEntity, reset } from './padaria.reducer';

export const PadariaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const padariaEntity = useAppSelector(state => state.padaria.entity);
  const loading = useAppSelector(state => state.padaria.loading);
  const updating = useAppSelector(state => state.padaria.updating);
  const updateSuccess = useAppSelector(state => state.padaria.updateSuccess);

  const handleClose = () => {
    navigate('/padaria' + location.search);
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
      ...padariaEntity,
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
          ...padariaEntity,
          user: padariaEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pratica4App.padaria.home.createOrEditLabel" data-cy="PadariaCreateUpdateHeading">
            Criar ou editar Padaria
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="padaria-id" label="Id" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Nome"
                id="padaria-nome"
                name="nome"
                data-cy="nome"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 3, message: 'Este campo deve ter pelo menos 3 caracteres.' },
                  maxLength: { value: 255, message: 'Este campo não pode ter mais de 255 caracteres.' },
                }}
              />
              <ValidatedField
                label="Fantasia"
                id="padaria-fantasia"
                name="fantasia"
                data-cy="fantasia"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 3, message: 'Este campo deve ter pelo menos 3 caracteres.' },
                  maxLength: { value: 255, message: 'Este campo não pode ter mais de 255 caracteres.' },
                }}
              />
              <ValidatedField
                label="Telefone"
                id="padaria-telefone"
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
                label="Cnpj"
                id="padaria-cnpj"
                name="cnpj"
                data-cy="cnpj"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 14, message: 'Este campo deve ter pelo menos 14 caracteres.' },
                  maxLength: { value: 14, message: 'Este campo não pode ter mais de 14 caracteres.' },
                }}
              />
              <ValidatedField
                label="Cep"
                id="padaria-cep"
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
                id="padaria-rua"
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
                id="padaria-bairro"
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
                id="padaria-estado"
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
                id="padaria-numero"
                name="numero"
                data-cy="numero"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 1, message: 'Este campo deve ter pelo menos 1 caracteres.' },
                  maxLength: { value: 255, message: 'Este campo não pode ter mais de 255 caracteres.' },
                }}
              />
              <ValidatedField label="Complemento" id="padaria-complemento" name="complemento" data-cy="complemento" type="text" />
              <ValidatedField label="Contato" id="padaria-contato" name="contato" data-cy="contato" type="text" />
              <ValidatedField id="padaria-user" name="user" data-cy="user" label="User" type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/padaria" replace color="info">
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

export default PadariaUpdate;
