import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './shipment-cart.reducer';

export const ShipmentCartDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const shipmentCartEntity = useAppSelector(state => state.shipmentCart.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="shipmentCartDetailsHeading">
          <Translate contentKey="testShopApp.shipmentCart.detail.title">ShipmentCart</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.id}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="testShopApp.shipmentCart.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="testShopApp.shipmentCart.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.lastName}</dd>
          <dt>
            <span id="street">
              <Translate contentKey="testShopApp.shipmentCart.street">Street</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.street}</dd>
          <dt>
            <span id="postalCode">
              <Translate contentKey="testShopApp.shipmentCart.postalCode">Postal Code</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.postalCode}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="testShopApp.shipmentCart.city">City</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.city}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="testShopApp.shipmentCart.country">Country</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.country}</dd>
          <dt>
            <span id="phoneToTheReceiver">
              <Translate contentKey="testShopApp.shipmentCart.phoneToTheReceiver">Phone To The Receiver</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.phoneToTheReceiver}</dd>
          <dt>
            <span id="firm">
              <Translate contentKey="testShopApp.shipmentCart.firm">Firm</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.firm}</dd>
          <dt>
            <span id="taxNumber">
              <Translate contentKey="testShopApp.shipmentCart.taxNumber">Tax Number</Translate>
            </span>
          </dt>
          <dd>{shipmentCartEntity.taxNumber}</dd>
        </dl>
        <Button tag={Link} to="/shipment-cart" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/shipment-cart/${shipmentCartEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ShipmentCartDetail;
