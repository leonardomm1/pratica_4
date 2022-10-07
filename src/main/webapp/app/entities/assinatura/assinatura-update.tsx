import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPadaria } from 'app/shared/model/padaria.model';
import { getEntities as getPadarias } from 'app/entities/padaria/padaria.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IAssinatura } from 'app/shared/model/assinatura.model';
import { getEntity, updateEntity, createEntity, reset } from './assinatura.reducer';

export const AssinaturaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const padarias = useAppSelector(state => state.padaria.entities);
  const users = useAppSelector(state => state.userManagement.users);
  const assinaturaEntity = useAppSelector(state => state.assinatura.entity);
  const loading = useAppSelector(state => state.assinatura.loading);
  const updating = useAppSelector(state => state.assinatura.updating);
  const updateSuccess = useAppSelector(state => state.assinatura.updateSuccess);

  const handleClose = () => {
    navigate('/assinatura' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getPadarias({}));
    dispatch(getUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.horarioRecebimento = convertDateTimeToServer(values.horarioRecebimento);

    const entity = {
      ...assinaturaEntity,
      ...values,
      padaria: padarias.find(it => it.id.toString() === values.padaria.toString()),
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
      ? {
          horarioRecebimento: displayDefaultDateTime(),
        }
      : {
          ...assinaturaEntity,
          horarioRecebimento: convertDateTimeFromServer(assinaturaEntity.horarioRecebimento),
          padaria: assinaturaEntity?.padaria?.id,
          user: assinaturaEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pratica4App.assinatura.home.createOrEditLabel" data-cy="AssinaturaCreateUpdateHeading">
            Criar ou editar Assinatura
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="assinatura-id" label="Id" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Valor"
                id="assinatura-valor"
                name="valor"
                data-cy="valor"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedField
                label="Nome"
                id="assinatura-nome"
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
                label="Quantidade Dias"
                id="assinatura-quantidadeDias"
                name="quantidadeDias"
                data-cy="quantidadeDias"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  min: { value: 1, message: 'Este campo deve ser maior que 1.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedField label="Ativa" id="assinatura-ativa" name="ativa" data-cy="ativa" check type="checkbox" />
              <ValidatedField
                label="Pagamento Recorrencia Id"
                id="assinatura-pagamentoRecorrenciaId"
                name="pagamentoRecorrenciaId"
                data-cy="pagamentoRecorrenciaId"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  min: { value: 1, message: 'Este campo deve ser maior que 1.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedBlobField label="Foto" id="assinatura-foto" name="foto" data-cy="foto" isImage accept="image/*" />
              <ValidatedField
                label="Quantidade"
                id="assinatura-quantidade"
                name="quantidade"
                data-cy="quantidade"
                type="text"
                validate={{
                  min: { value: 1, message: 'Este campo deve ser maior que 1.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedField
                label="Horario Recebimento"
                id="assinatura-horarioRecebimento"
                name="horarioRecebimento"
                data-cy="horarioRecebimento"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                }}
              />
              <ValidatedField
                label="Tipo Assinatura"
                id="assinatura-tipoAssinatura"
                name="tipoAssinatura"
                data-cy="tipoAssinatura"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  minLength: { value: 3, message: 'Este campo deve ter pelo menos 3 caracteres.' },
                }}
              />
              <ValidatedField
                label="Dia Da Semana"
                id="assinatura-diaDaSemana"
                name="diaDaSemana"
                data-cy="diaDaSemana"
                type="textarea"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                }}
              />
              <ValidatedField id="assinatura-padaria" name="padaria" data-cy="padaria" label="Padaria" type="select">
                <option value="" key="0" />
                {padarias
                  ? padarias.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="assinatura-user" name="user" data-cy="user" label="User" type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/assinatura" replace color="info">
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

export default AssinaturaUpdate;
