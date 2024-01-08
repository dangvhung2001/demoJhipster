import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IShipmentCart } from 'app/shared/model/shipment-cart.model';
import { getEntities as getShipmentCarts } from 'app/entities/shipment-cart/shipment-cart.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IOrderMain } from 'app/shared/model/order-main.model';
import { getEntities as getOrderMains } from 'app/entities/order-main/order-main.reducer';
import { ICart } from 'app/shared/model/cart.model';
import { getEntity, updateEntity, createEntity, reset } from './cart.reducer';

export const CartUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const shipmentCarts = useAppSelector(state => state.shipmentCart.entities);
  const users = useAppSelector(state => state.userManagement.users);
  const orderMains = useAppSelector(state => state.orderMain.entities);
  const cartEntity = useAppSelector(state => state.cart.entity);
  const loading = useAppSelector(state => state.cart.loading);
  const updating = useAppSelector(state => state.cart.updating);
  const updateSuccess = useAppSelector(state => state.cart.updateSuccess);

  const handleClose = () => {
    navigate('/cart');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getShipmentCarts({}));
    dispatch(getUsers({}));
    dispatch(getOrderMains({}));
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
    if (values.quantity !== undefined && typeof values.quantity !== 'number') {
      values.quantity = Number(values.quantity);
    }

    const entity = {
      ...cartEntity,
      ...values,
      shipmentCart: shipmentCarts.find(it => it.id.toString() === values.shipmentCart.toString()),
      user: users.find(it => it.id.toString() === values.user.toString()),
      orderMain: orderMains.find(it => it.id.toString() === values.orderMain.toString()),
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
          ...cartEntity,
          shipmentCart: cartEntity?.shipmentCart?.id,
          user: cartEntity?.user?.id,
          orderMain: cartEntity?.orderMain?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testShopApp.cart.home.createOrEditLabel" data-cy="CartCreateUpdateHeading">
            <Translate contentKey="testShopApp.cart.home.createOrEditLabel">Create or edit a Cart</Translate>
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
                  id="cart-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('testShopApp.cart.amountOfCartNet')}
                id="cart-amountOfCartNet"
                name="amountOfCartNet"
                data-cy="amountOfCartNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cart.amountOfCartGross')}
                id="cart-amountOfCartGross"
                name="amountOfCartGross"
                data-cy="amountOfCartGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cart.amountOfShipmentNet')}
                id="cart-amountOfShipmentNet"
                name="amountOfShipmentNet"
                data-cy="amountOfShipmentNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cart.amountOfShipmentGross')}
                id="cart-amountOfShipmentGross"
                name="amountOfShipmentGross"
                data-cy="amountOfShipmentGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cart.amountOfOrderNet')}
                id="cart-amountOfOrderNet"
                name="amountOfOrderNet"
                data-cy="amountOfOrderNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cart.amountOfOrderGross')}
                id="cart-amountOfOrderGross"
                name="amountOfOrderGross"
                data-cy="amountOfOrderGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cart.quantity')}
                id="cart-quantity"
                name="quantity"
                data-cy="quantity"
                type="text"
              />
              <ValidatedField
                id="cart-shipmentCart"
                name="shipmentCart"
                data-cy="shipmentCart"
                label={translate('testShopApp.cart.shipmentCart')}
                type="select"
              >
                <option value="" key="0" />
                {shipmentCarts
                  ? shipmentCarts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="cart-user" name="user" data-cy="user" label={translate('testShopApp.cart.user')} type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="cart-orderMain"
                name="orderMain"
                data-cy="orderMain"
                label={translate('testShopApp.cart.orderMain')}
                type="select"
              >
                <option value="" key="0" />
                {orderMains
                  ? orderMains.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/cart" replace color="info">
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

export default CartUpdate;
