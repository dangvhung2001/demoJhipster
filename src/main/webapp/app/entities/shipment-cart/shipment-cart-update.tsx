import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICart } from 'app/shared/model/cart.model';
import { getEntities as getCarts } from 'app/entities/cart/cart.reducer';
import { IShipmentCart } from 'app/shared/model/shipment-cart.model';
import { getEntity, updateEntity, createEntity, reset } from './shipment-cart.reducer';

export const ShipmentCartUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const carts = useAppSelector(state => state.cart.entities);
  const shipmentCartEntity = useAppSelector(state => state.shipmentCart.entity);
  const loading = useAppSelector(state => state.shipmentCart.loading);
  const updating = useAppSelector(state => state.shipmentCart.updating);
  const updateSuccess = useAppSelector(state => state.shipmentCart.updateSuccess);

  const handleClose = () => {
    navigate('/shipment-cart');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCarts({}));
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

    const entity = {
      ...shipmentCartEntity,
      ...values,
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
          ...shipmentCartEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testShopApp.shipmentCart.home.createOrEditLabel" data-cy="ShipmentCartCreateUpdateHeading">
            <Translate contentKey="testShopApp.shipmentCart.home.createOrEditLabel">Create or edit a ShipmentCart</Translate>
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
                  id="shipment-cart-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('testShopApp.shipmentCart.firstName')}
                id="shipment-cart-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
                validate={{
                  maxLength: { value: 500, message: translate('entity.validation.maxlength', { max: 500 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.lastName')}
                id="shipment-cart-lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
                validate={{
                  maxLength: { value: 500, message: translate('entity.validation.maxlength', { max: 500 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.street')}
                id="shipment-cart-street"
                name="street"
                data-cy="street"
                type="text"
                validate={{
                  maxLength: { value: 500, message: translate('entity.validation.maxlength', { max: 500 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.postalCode')}
                id="shipment-cart-postalCode"
                name="postalCode"
                data-cy="postalCode"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.city')}
                id="shipment-cart-city"
                name="city"
                data-cy="city"
                type="text"
                validate={{
                  maxLength: { value: 500, message: translate('entity.validation.maxlength', { max: 500 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.country')}
                id="shipment-cart-country"
                name="country"
                data-cy="country"
                type="text"
                validate={{
                  maxLength: { value: 500, message: translate('entity.validation.maxlength', { max: 500 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.phoneToTheReceiver')}
                id="shipment-cart-phoneToTheReceiver"
                name="phoneToTheReceiver"
                data-cy="phoneToTheReceiver"
                type="text"
                validate={{
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.firm')}
                id="shipment-cart-firm"
                name="firm"
                data-cy="firm"
                type="text"
                validate={{
                  maxLength: { value: 10000, message: translate('entity.validation.maxlength', { max: 10000 }) },
                }}
              />
              <ValidatedField
                label={translate('testShopApp.shipmentCart.taxNumber')}
                id="shipment-cart-taxNumber"
                name="taxNumber"
                data-cy="taxNumber"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/shipment-cart" replace color="info">
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

export default ShipmentCartUpdate;
