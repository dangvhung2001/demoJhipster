import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './cart.reducer';

export const Cart = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const cartList = useAppSelector(state => state.cart.entities);
  const loading = useAppSelector(state => state.cart.loading);

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
      <h2 id="cart-heading" data-cy="CartHeading">
        <Translate contentKey="testShopApp.cart.home.title">Carts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="testShopApp.cart.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/cart/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="testShopApp.cart.home.createLabel">Create new Cart</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cartList && cartList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="testShopApp.cart.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('amountOfCartNet')}>
                  <Translate contentKey="testShopApp.cart.amountOfCartNet">Amount Of Cart Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfCartNet')} />
                </th>
                <th className="hand" onClick={sort('amountOfCartGross')}>
                  <Translate contentKey="testShopApp.cart.amountOfCartGross">Amount Of Cart Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfCartGross')} />
                </th>
                <th className="hand" onClick={sort('amountOfShipmentNet')}>
                  <Translate contentKey="testShopApp.cart.amountOfShipmentNet">Amount Of Shipment Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfShipmentNet')} />
                </th>
                <th className="hand" onClick={sort('amountOfShipmentGross')}>
                  <Translate contentKey="testShopApp.cart.amountOfShipmentGross">Amount Of Shipment Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfShipmentGross')} />
                </th>
                <th className="hand" onClick={sort('amountOfOrderNet')}>
                  <Translate contentKey="testShopApp.cart.amountOfOrderNet">Amount Of Order Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfOrderNet')} />
                </th>
                <th className="hand" onClick={sort('amountOfOrderGross')}>
                  <Translate contentKey="testShopApp.cart.amountOfOrderGross">Amount Of Order Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfOrderGross')} />
                </th>
                <th className="hand" onClick={sort('quantity')}>
                  <Translate contentKey="testShopApp.cart.quantity">Quantity</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('quantity')} />
                </th>
                <th>
                  <Translate contentKey="testShopApp.cart.shipmentCart">Shipment Cart</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="testShopApp.cart.user">User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="testShopApp.cart.orderMain">Order Main</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cartList.map((cart, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/cart/${cart.id}`} color="link" size="sm">
                      {cart.id}
                    </Button>
                  </td>
                  <td>{cart.amountOfCartNet}</td>
                  <td>{cart.amountOfCartGross}</td>
                  <td>{cart.amountOfShipmentNet}</td>
                  <td>{cart.amountOfShipmentGross}</td>
                  <td>{cart.amountOfOrderNet}</td>
                  <td>{cart.amountOfOrderGross}</td>
                  <td>{cart.quantity}</td>
                  <td>{cart.shipmentCart ? <Link to={`/shipment-cart/${cart.shipmentCart.id}`}>{cart.shipmentCart.id}</Link> : ''}</td>
                  <td>{cart.user ? cart.user.login : ''}</td>
                  <td>{cart.orderMain ? <Link to={`/order-main/${cart.orderMain.id}`}>{cart.orderMain.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/cart/${cart.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/cart/${cart.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/cart/${cart.id}/delete`)}
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
              <Translate contentKey="testShopApp.cart.home.notFound">No Carts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Cart;
