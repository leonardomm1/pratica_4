import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntity, deleteEntity } from './informacoes-adicionais.reducer';

export const InformacoesAdicionaisDeleteDialog = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams<'id'>();

  const [loadModal, setLoadModal] = useState(false);

  useEffect(() => {
    dispatch(getEntity(id));
    setLoadModal(true);
  }, []);

  const informacoesAdicionaisEntity = useAppSelector(state => state.informacoesAdicionais.entity);
  const updateSuccess = useAppSelector(state => state.informacoesAdicionais.updateSuccess);

  const handleClose = () => {
    navigate('/informacoes-adicionais' + location.search);
  };

  useEffect(() => {
    if (updateSuccess && loadModal) {
      handleClose();
      setLoadModal(false);
    }
  }, [updateSuccess]);

  const confirmDelete = () => {
    dispatch(deleteEntity(informacoesAdicionaisEntity.id));
  };

  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="informacoesAdicionaisDeleteDialogHeading">
        Confirme a exclusão
      </ModalHeader>
      <ModalBody id="pratica4App.informacoesAdicionais.delete.question">
        Tem certeza de que deseja excluir Informacoes Adicionais {informacoesAdicionaisEntity.id}?
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancelar
        </Button>
        <Button id="jhi-confirm-delete-informacoesAdicionais" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Excluir
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default InformacoesAdicionaisDeleteDialog;
