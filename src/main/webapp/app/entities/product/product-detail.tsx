import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product.reducer';

export const ProductDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productEntity = useAppSelector(state => state.product.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsHeading">
          <Translate contentKey="testShopApp.product.detail.title">Product</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="productCategoryEnum">
              <Translate contentKey="testShopApp.product.productCategoryEnum">Product Category Enum</Translate>
            </span>
          </dt>
          <dd>{productEntity.productCategoryEnum}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="testShopApp.product.name">Name</Translate>
            </span>
          </dt>
          <dd>{productEntity.name}</dd>
          <dt>
            <span id="quantity">
              <Translate contentKey="testShopApp.product.quantity">Quantity</Translate>
            </span>
          </dt>
          <dd>{productEntity.quantity}</dd>
          <dt>
            <span id="priceNet">
              <Translate contentKey="testShopApp.product.priceNet">Price Net</Translate>
            </span>
          </dt>
          <dd>{productEntity.priceNet}</dd>
          <dt>
            <span id="vat">
              <Translate contentKey="testShopApp.product.vat">Vat</Translate>
            </span>
          </dt>
          <dd>{productEntity.vat}</dd>
          <dt>
            <span id="priceGross">
              <Translate contentKey="testShopApp.product.priceGross">Price Gross</Translate>
            </span>
          </dt>
          <dd>{productEntity.priceGross}</dd>
          <dt>
            <span id="stock">
              <Translate contentKey="testShopApp.product.stock">Stock</Translate>
            </span>
          </dt>
          <dd>{productEntity.stock}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="testShopApp.product.description">Description</Translate>
            </span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="createTime">
              <Translate contentKey="testShopApp.product.createTime">Create Time</Translate>
            </span>
          </dt>
          <dd>{productEntity.createTime ? <TextFormat value={productEntity.createTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updateTime">
              <Translate contentKey="testShopApp.product.updateTime">Update Time</Translate>
            </span>
          </dt>
          <dd>{productEntity.updateTime ? <TextFormat value={productEntity.updateTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/product" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/${productEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
