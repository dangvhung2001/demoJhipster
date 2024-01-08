import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './shipment-cart.reducer';

export const ShipmentCart = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const shipmentCartList = useAppSelector(state => state.shipmentCart.entities);
  const loading = useAppSelector(state => state.shipmentCart.loading);

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
      <h2 id="shipment-cart-heading" data-cy="ShipmentCartHeading">
        <Translate contentKey="testShopApp.shipmentCart.home.title">Shipment Carts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="testShopApp.shipmentCart.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/shipment-cart/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="testShopApp.shipmentCart.home.createLabel">Create new Shipment Cart</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {shipmentCartList && shipmentCartList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="testShopApp.shipmentCart.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('firstName')}>
                  <Translate contentKey="testShopApp.shipmentCart.firstName">First Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('firstName')} />
                </th>
                <th className="hand" onClick={sort('lastName')}>
                  <Translate contentKey="testShopApp.shipmentCart.lastName">Last Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lastName')} />
                </th>
                <th className="hand" onClick={sort('street')}>
                  <Translate contentKey="testShopApp.shipmentCart.street">Street</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('street')} />
                </th>
                <th className="hand" onClick={sort('postalCode')}>
                  <Translate contentKey="testShopApp.shipmentCart.postalCode">Postal Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postalCode')} />
                </th>
                <th className="hand" onClick={sort('city')}>
                  <Translate contentKey="testShopApp.shipmentCart.city">City</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('city')} />
                </th>
                <th className="hand" onClick={sort('country')}>
                  <Translate contentKey="testShopApp.shipmentCart.country">Country</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('country')} />
                </th>
                <th className="hand" onClick={sort('phoneToTheReceiver')}>
                  <Translate contentKey="testShopApp.shipmentCart.phoneToTheReceiver">Phone To The Receiver</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phoneToTheReceiver')} />
                </th>
                <th className="hand" onClick={sort('firm')}>
                  <Translate contentKey="testShopApp.shipmentCart.firm">Firm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('firm')} />
                </th>
                <th className="hand" onClick={sort('taxNumber')}>
                  <Translate contentKey="testShopApp.shipmentCart.taxNumber">Tax Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('taxNumber')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {shipmentCartList.map((shipmentCart, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/shipment-cart/${shipmentCart.id}`} color="link" size="sm">
                      {shipmentCart.id}
                    </Button>
                  </td>
                  <td>{shipmentCart.firstName}</td>
                  <td>{shipmentCart.lastName}</td>
                  <td>{shipmentCart.street}</td>
                  <td>{shipmentCart.postalCode}</td>
                  <td>{shipmentCart.city}</td>
                  <td>{shipmentCart.country}</td>
                  <td>{shipmentCart.phoneToTheReceiver}</td>
                  <td>{shipmentCart.firm}</td>
                  <td>{shipmentCart.taxNumber}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/shipment-cart/${shipmentCart.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/shipment-cart/${shipmentCart.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/shipment-cart/${shipmentCart.id}/delete`)}
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
              <Translate contentKey="testShopApp.shipmentCart.home.notFound">No Shipment Carts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ShipmentCart;
