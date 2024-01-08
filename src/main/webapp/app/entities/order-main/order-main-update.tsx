import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IOrderMain } from 'app/shared/model/order-main.model';
import { OrderMainStatusEnum } from 'app/shared/model/enumerations/order-main-status-enum.model';
import { getEntity, updateEntity, createEntity, reset } from './order-main.reducer';

export const OrderMainUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const orderMainEntity = useAppSelector(state => state.orderMain.entity);
  const loading = useAppSelector(state => state.orderMain.loading);
  const updating = useAppSelector(state => state.orderMain.updating);
  const updateSuccess = useAppSelector(state => state.orderMain.updateSuccess);
  const orderMainStatusEnumValues = Object.keys(OrderMainStatusEnum);

  const handleClose = () => {
    navigate('/order-main');
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

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.amountOfCartNet !== undefined && typeof values.amountOfCartNet !== 'number') {
      values.amountOfCartNet = Number(values.amountOfCartNet);
    }
    if (values.amountOfCartGross !== undefined && typeof values.amountOfCartGross !== 'number') {
      values.amountOfCartGross = Number(values.amountOfCartGross);
    }
    if (values.amountOfShipmentNet !== undefined && typeof values.amountOfShipmentNet !== 'number') {
      values.amountOfShipmentNet = Number(values.amountOfShipmentNet);
    }
    if (values.amountOfShipmentGross !== undefined && typeof values.amountOfShipmentGross !== 'number') {
      values.amountOfShipmentGross = Number(values.amountOfShipmentGross);
    }
    if (values.amountOfOrderNet !== undefined && typeof values.amountOfOrderNet !== 'number') {
      values.amountOfOrderNet = Number(values.amountOfOrderNet);
    }
    if (values.amountOfOrderGross !== undefined && typeof values.amountOfOrderGross !== 'number') {
      values.amountOfOrderGross = Number(values.amountOfOrderGross);
    }
    values.createTime = convertDateTimeToServer(values.createTime);
    values.updateTime = convertDateTimeToServer(values.updateTime);

    const entity = {
      ...orderMainEntity,
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
      ? {
          createTime: displayDefaultDateTime(),
          updateTime: displayDefaultDateTime(),
        }
      : {
          orderMainStatus: 'WaitingForBankTransfer',
          ...orderMainEntity,
          createTime: convertDateTimeFromServer(orderMainEntity.createTime),
          updateTime: convertDateTimeFromServer(orderMainEntity.updateTime),
          user: orderMainEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testShopApp.orderMain.home.createOrEditLabel" data-cy="OrderMainCreateUpdateHeading">
            <Translate contentKey="testShopApp.orderMain.home.createOrEditLabel">Create or edit a OrderMain</Translate>
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
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="order-main-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('testShopApp.orderMain.buyerLogin')}
                id="order-main-buyerLogin"
                name="buyerLogin"
                data-cy="buyerLogin"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.buyerFirstName')}
                id="order-main-buyerFirstName"
                name="buyerFirstName"
                data-cy="buyerFirstName"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.buyerLastName')}
                id="order-main-buyerLastName"
                name="buyerLastName"
                data-cy="buyerLastName"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.buyerEmail')}
                id="order-main-buyerEmail"
                name="buyerEmail"
                data-cy="buyerEmail"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.buyerPhone')}
                id="order-main-buyerPhone"
                name="buyerPhone"
                data-cy="buyerPhone"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.amountOfCartNet')}
                id="order-main-amountOfCartNet"
                name="amountOfCartNet"
                data-cy="amountOfCartNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.amountOfCartGross')}
                id="order-main-amountOfCartGross"
                name="amountOfCartGross"
                data-cy="amountOfCartGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.amountOfShipmentNet')}
                id="order-main-amountOfShipmentNet"
                name="amountOfShipmentNet"
                data-cy="amountOfShipmentNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.amountOfShipmentGross')}
                id="order-main-amountOfShipmentGross"
                name="amountOfShipmentGross"
                data-cy="amountOfShipmentGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.amountOfOrderNet')}
                id="order-main-amountOfOrderNet"
                name="amountOfOrderNet"
                data-cy="amountOfOrderNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.amountOfOrderGross')}
                id="order-main-amountOfOrderGross"
                name="amountOfOrderGross"
                data-cy="amountOfOrderGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.orderMainStatus')}
                id="order-main-orderMainStatus"
                name="orderMainStatus"
                data-cy="orderMainStatus"
                type="select"
              >
                {orderMainStatusEnumValues.map(orderMainStatusEnum => (
                  <option value={orderMainStatusEnum} key={orderMainStatusEnum}>
                    {translate('testShopApp.OrderMainStatusEnum.' + orderMainStatusEnum)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('testShopApp.orderMain.createTime')}
                id="order-main-createTime"
                name="createTime"
                data-cy="createTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('testShopApp.orderMain.updateTime')}
                id="order-main-updateTime"
                name="updateTime"
                data-cy="updateTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField id="order-main-user" name="user" data-cy="user" label={translate('testShopApp.orderMain.user')} type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order-main" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default OrderMainUpdate;
