import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-main.reducer';

export const OrderMainDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderMainEntity = useAppSelector(state => state.orderMain.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderMainDetailsHeading">
          <Translate contentKey="testShopApp.orderMain.detail.title">OrderMain</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.id}</dd>
          <dt>
            <span id="buyerLogin">
              <Translate contentKey="testShopApp.orderMain.buyerLogin">Buyer Login</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.buyerLogin}</dd>
          <dt>
            <span id="buyerFirstName">
              <Translate contentKey="testShopApp.orderMain.buyerFirstName">Buyer First Name</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.buyerFirstName}</dd>
          <dt>
            <span id="buyerLastName">
              <Translate contentKey="testShopApp.orderMain.buyerLastName">Buyer Last Name</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.buyerLastName}</dd>
          <dt>
            <span id="buyerEmail">
              <Translate contentKey="testShopApp.orderMain.buyerEmail">Buyer Email</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.buyerEmail}</dd>
          <dt>
            <span id="buyerPhone">
              <Translate contentKey="testShopApp.orderMain.buyerPhone">Buyer Phone</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.buyerPhone}</dd>
          <dt>
            <span id="amountOfCartNet">
              <Translate contentKey="testShopApp.orderMain.amountOfCartNet">Amount Of Cart Net</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.amountOfCartNet}</dd>
          <dt>
            <span id="amountOfCartGross">
              <Translate contentKey="testShopApp.orderMain.amountOfCartGross">Amount Of Cart Gross</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.amountOfCartGross}</dd>
          <dt>
            <span id="amountOfShipmentNet">
              <Translate contentKey="testShopApp.orderMain.amountOfShipmentNet">Amount Of Shipment Net</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.amountOfShipmentNet}</dd>
          <dt>
            <span id="amountOfShipmentGross">
              <Translate contentKey="testShopApp.orderMain.amountOfShipmentGross">Amount Of Shipment Gross</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.amountOfShipmentGross}</dd>
          <dt>
            <span id="amountOfOrderNet">
              <Translate contentKey="testShopApp.orderMain.amountOfOrderNet">Amount Of Order Net</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.amountOfOrderNet}</dd>
          <dt>
            <span id="amountOfOrderGross">
              <Translate contentKey="testShopApp.orderMain.amountOfOrderGross">Amount Of Order Gross</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.amountOfOrderGross}</dd>
          <dt>
            <span id="orderMainStatus">
              <Translate contentKey="testShopApp.orderMain.orderMainStatus">Order Main Status</Translate>
            </span>
          </dt>
          <dd>{orderMainEntity.orderMainStatus}</dd>
          <dt>
            <span id="createTime">
              <Translate contentKey="testShopApp.orderMain.createTime">Create Time</Translate>
            </span>
          </dt>
          <dd>
            {orderMainEntity.createTime ? <TextFormat value={orderMainEntity.createTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updateTime">
              <Translate contentKey="testShopApp.orderMain.updateTime">Update Time</Translate>
            </span>
          </dt>
          <dd>
            {orderMainEntity.updateTime ? <TextFormat value={orderMainEntity.updateTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="testShopApp.orderMain.user">User</Translate>
          </dt>
          <dd>{orderMainEntity.user ? orderMainEntity.user.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/order-main" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-main/${orderMainEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderMainDetail;
