import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { ICart } from 'app/shared/model/cart.model';
import { getEntities as getCarts } from 'app/entities/cart/cart.reducer';
import { ICartItem } from 'app/shared/model/cart-item.model';
import { getEntity, updateEntity, createEntity, reset } from './cart-item.reducer';

export const CartItemUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const products = useAppSelector(state => state.product.entities);
  const carts = useAppSelector(state => state.cart.entities);
  const cartItemEntity = useAppSelector(state => state.cartItem.entity);
  const loading = useAppSelector(state => state.cartItem.loading);
  const updating = useAppSelector(state => state.cartItem.updating);
  const updateSuccess = useAppSelector(state => state.cartItem.updateSuccess);

  const handleClose = () => {
    navigate('/cart-item');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProducts({}));
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
    if (values.quantity !== undefined && typeof values.quantity !== 'number') {
      values.quantity = Number(values.quantity);
    }
    if (values.priceNet !== undefined && typeof values.priceNet !== 'number') {
      values.priceNet = Number(values.priceNet);
    }
    if (values.vat !== undefined && typeof values.vat !== 'number') {
      values.vat = Number(values.vat);
    }
    if (values.priceGross !== undefined && typeof values.priceGross !== 'number') {
      values.priceGross = Number(values.priceGross);
    }
    if (values.totalPriceNet !== undefined && typeof values.totalPriceNet !== 'number') {
      values.totalPriceNet = Number(values.totalPriceNet);
    }
    if (values.totalPriceGross !== undefined && typeof values.totalPriceGross !== 'number') {
      values.totalPriceGross = Number(values.totalPriceGross);
    }

    const entity = {
      ...cartItemEntity,
      ...values,
      product: products.find(it => it.id.toString() === values.product.toString()),
      cart: carts.find(it => it.id.toString() === values.cart.toString()),
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
          ...cartItemEntity,
          product: cartItemEntity?.product?.id,
          cart: cartItemEntity?.cart?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testShopApp.cartItem.home.createOrEditLabel" data-cy="CartItemCreateUpdateHeading">
            <Translate contentKey="testShopApp.cartItem.home.createOrEditLabel">Create or edit a CartItem</Translate>
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
                  id="cart-item-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('testShopApp.cartItem.quantity')}
                id="cart-item-quantity"
                name="quantity"
                data-cy="quantity"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cartItem.priceNet')}
                id="cart-item-priceNet"
                name="priceNet"
                data-cy="priceNet"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  max: { value: 1000000, message: translate('entity.validation.max', { max: 1000000 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('testShopApp.cartItem.vat')}
                id="cart-item-vat"
                name="vat"
                data-cy="vat"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  max: { value: 100, message: translate('entity.validation.max', { max: 100 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('testShopApp.cartItem.priceGross')}
                id="cart-item-priceGross"
                name="priceGross"
                data-cy="priceGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cartItem.totalPriceNet')}
                id="cart-item-totalPriceNet"
                name="totalPriceNet"
                data-cy="totalPriceNet"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cartItem.totalPriceGross')}
                id="cart-item-totalPriceGross"
                name="totalPriceGross"
                data-cy="totalPriceGross"
                type="text"
              />
              <ValidatedField
                label={translate('testShopApp.cartItem.description')}
                id="cart-item-description"
                name="description"
                data-cy="description"
                type="textarea"
              />
              <ValidatedField
                id="cart-item-product"
                name="product"
                data-cy="product"
                label={translate('testShopApp.cartItem.product')}
                type="select"
              >
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="cart-item-cart" name="cart" data-cy="cart" label={translate('testShopApp.cartItem.cart')} type="select">
                <option value="" key="0" />
                {carts
                  ? carts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/cart-item" replace color="info">
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

export default CartItemUpdate;
