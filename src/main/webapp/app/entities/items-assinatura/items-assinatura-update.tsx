import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAssinatura } from 'app/shared/model/assinatura.model';
import { getEntities as getAssinaturas } from 'app/entities/assinatura/assinatura.reducer';
import { IItemsAssinatura } from 'app/shared/model/items-assinatura.model';
import { getEntity, updateEntity, createEntity, reset } from './items-assinatura.reducer';

export const ItemsAssinaturaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const assinaturas = useAppSelector(state => state.assinatura.entities);
  const itemsAssinaturaEntity = useAppSelector(state => state.itemsAssinatura.entity);
  const loading = useAppSelector(state => state.itemsAssinatura.loading);
  const updating = useAppSelector(state => state.itemsAssinatura.updating);
  const updateSuccess = useAppSelector(state => state.itemsAssinatura.updateSuccess);

  const handleClose = () => {
    navigate('/items-assinatura' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAssinaturas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...itemsAssinaturaEntity,
      ...values,
      assinatura: assinaturas.find(it => it.id.toString() === values.assinatura.toString()),
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
          ...itemsAssinaturaEntity,
          assinatura: itemsAssinaturaEntity?.assinatura?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pratica4App.itemsAssinatura.home.createOrEditLabel" data-cy="ItemsAssinaturaCreateUpdateHeading">
            Criar ou editar Items Assinatura
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
                <ValidatedField name="id" required readOnly id="items-assinatura-id" label="Id" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Padaria Id"
                id="items-assinatura-padariaId"
                name="padariaId"
                data-cy="padariaId"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  min: { value: 1, message: 'Este campo deve ser maior que 1.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedField
                label="Nome"
                id="items-assinatura-nome"
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
                label="Valor"
                id="items-assinatura-valor"
                name="valor"
                data-cy="valor"
                type="text"
                validate={{
                  required: { value: true, message: 'O campo é obrigatório.' },
                  min: { value: 1, message: 'Este campo deve ser maior que 1.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedBlobField label="Foto" id="items-assinatura-foto" name="foto" data-cy="foto" isImage accept="image/*" />
              <ValidatedField
                label="Quantidade"
                id="items-assinatura-quantidade"
                name="quantidade"
                data-cy="quantidade"
                type="text"
                validate={{
                  min: { value: 1, message: 'Este campo deve ser maior que 1.' },
                  validate: v => isNumber(v) || 'Este campo é do tipo numérico.',
                }}
              />
              <ValidatedField id="items-assinatura-assinatura" name="assinatura" data-cy="assinatura" label="Assinatura" type="select">
                <option value="" key="0" />
                {assinaturas
                  ? assinaturas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/items-assinatura" replace color="info">
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

export default ItemsAssinaturaUpdate;
