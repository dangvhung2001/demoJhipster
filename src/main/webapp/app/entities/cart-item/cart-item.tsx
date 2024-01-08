import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { byteSize, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './cart-item.reducer';

export const CartItem = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const cartItemList = useAppSelector(state => state.cartItem.entities);
  const loading = useAppSelector(state => state.cartItem.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="cart-item-heading" data-cy="CartItemHeading">
        <Translate contentKey="testShopApp.cartItem.home.title">Cart Items</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="testShopApp.cartItem.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/cart-item/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="testShopApp.cartItem.home.createLabel">Create new Cart Item</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cartItemList && cartItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="testShopApp.cartItem.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('quantity')}>
                  <Translate contentKey="testShopApp.cartItem.quantity">Quantity</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('quantity')} />
                </th>
                <th className="hand" onClick={sort('priceNet')}>
                  <Translate contentKey="testShopApp.cartItem.priceNet">Price Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('priceNet')} />
                </th>
                <th className="hand" onClick={sort('vat')}>
                  <Translate contentKey="testShopApp.cartItem.vat">Vat</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('vat')} />
                </th>
                <th className="hand" onClick={sort('priceGross')}>
                  <Translate contentKey="testShopApp.cartItem.priceGross">Price Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('priceGross')} />
                </th>
                <th className="hand" onClick={sort('totalPriceNet')}>
                  <Translate contentKey="testShopApp.cartItem.totalPriceNet">Total Price Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalPriceNet')} />
                </th>
                <th className="hand" onClick={sort('totalPriceGross')}>
                  <Translate contentKey="testShopApp.cartItem.totalPriceGross">Total Price Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalPriceGross')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="testShopApp.cartItem.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th>
                  <Translate contentKey="testShopApp.cartItem.product">Product</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="testShopApp.cartItem.cart">Cart</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cartItemList.map((cartItem, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/cart-item/${cartItem.id}`} color="link" size="sm">
                      {cartItem.id}
                    </Button>
                  </td>
                  <td>{cartItem.quantity}</td>
                  <td>{cartItem.priceNet}</td>
                  <td>{cartItem.vat}</td>
                  <td>{cartItem.priceGross}</td>
                  <td>{cartItem.totalPriceNet}</td>
                  <td>{cartItem.totalPriceGross}</td>
                  <td>{cartItem.description}</td>
                  <td>{cartItem.product ? <Link to={`/product/${cartItem.product.id}`}>{cartItem.product.id}</Link> : ''}</td>
                  <td>{cartItem.cart ? <Link to={`/cart/${cartItem.cart.id}`}>{cartItem.cart.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/cart-item/${cartItem.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/cart-item/${cartItem.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/cart-item/${cartItem.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="testShopApp.cartItem.home.notFound">No Cart Items found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CartItem;
