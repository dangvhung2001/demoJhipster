import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './cart.reducer';

export const CartDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cartEntity = useAppSelector(state => state.cart.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cartDetailsHeading">
          <Translate contentKey="testShopApp.cart.detail.title">Cart</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{cartEntity.id}</dd>
          <dt>
            <span id="amountOfCartNet">
              <Translate contentKey="testShopApp.cart.amountOfCartNet">Amount Of Cart Net</Translate>
            </span>
          </dt>
          <dd>{cartEntity.amountOfCartNet}</dd>
          <dt>
            <span id="amountOfCartGross">
              <Translate contentKey="testShopApp.cart.amountOfCartGross">Amount Of Cart Gross</Translate>
            </span>
          </dt>
          <dd>{cartEntity.amountOfCartGross}</dd>
          <dt>
            <span id="amountOfShipmentNet">
              <Translate contentKey="testShopApp.cart.amountOfShipmentNet">Amount Of Shipment Net</Translate>
            </span>
          </dt>
          <dd>{cartEntity.amountOfShipmentNet}</dd>
          <dt>
            <span id="amountOfShipmentGross">
              <Translate contentKey="testShopApp.cart.amountOfShipmentGross">Amount Of Shipment Gross</Translate>
            </span>
          </dt>
          <dd>{cartEntity.amountOfShipmentGross}</dd>
          <dt>
            <span id="amountOfOrderNet">
              <Translate contentKey="testShopApp.cart.amountOfOrderNet">Amount Of Order Net</Translate>
            </span>
          </dt>
          <dd>{cartEntity.amountOfOrderNet}</dd>
          <dt>
            <span id="amountOfOrderGross">
              <Translate contentKey="testShopApp.cart.amountOfOrderGross">Amount Of Order Gross</Translate>
            </span>
          </dt>
          <dd>{cartEntity.amountOfOrderGross}</dd>
          <dt>
            <span id="quantity">
              <Translate contentKey="testShopApp.cart.quantity">Quantity</Translate>
            </span>
          </dt>
          <dd>{cartEntity.quantity}</dd>
          <dt>
            <Translate contentKey="testShopApp.cart.shipmentCart">Shipment Cart</Translate>
          </dt>
          <dd>{cartEntity.shipmentCart ? cartEntity.shipmentCart.id : ''}</dd>
          <dt>
            <Translate contentKey="testShopApp.cart.user">User</Translate>
          </dt>
          <dd>{cartEntity.user ? cartEntity.user.login : ''}</dd>
          <dt>
            <Translate contentKey="testShopApp.cart.orderMain">Order Main</Translate>
          </dt>
          <dd>{cartEntity.orderMain ? cartEntity.orderMain.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/cart" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cart/${cartEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CartDetail;
