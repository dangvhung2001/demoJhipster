import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './order-main.reducer';

export const OrderMain = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const orderMainList = useAppSelector(state => state.orderMain.entities);
  const loading = useAppSelector(state => state.orderMain.loading);

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
      <h2 id="order-main-heading" data-cy="OrderMainHeading">
        <Translate contentKey="testShopApp.orderMain.home.title">Order Mains</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="testShopApp.orderMain.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/order-main/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="testShopApp.orderMain.home.createLabel">Create new Order Main</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {orderMainList && orderMainList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="testShopApp.orderMain.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('buyerLogin')}>
                  <Translate contentKey="testShopApp.orderMain.buyerLogin">Buyer Login</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('buyerLogin')} />
                </th>
                <th className="hand" onClick={sort('buyerFirstName')}>
                  <Translate contentKey="testShopApp.orderMain.buyerFirstName">Buyer First Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('buyerFirstName')} />
                </th>
                <th className="hand" onClick={sort('buyerLastName')}>
                  <Translate contentKey="testShopApp.orderMain.buyerLastName">Buyer Last Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('buyerLastName')} />
                </th>
                <th className="hand" onClick={sort('buyerEmail')}>
                  <Translate contentKey="testShopApp.orderMain.buyerEmail">Buyer Email</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('buyerEmail')} />
                </th>
                <th className="hand" onClick={sort('buyerPhone')}>
                  <Translate contentKey="testShopApp.orderMain.buyerPhone">Buyer Phone</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('buyerPhone')} />
                </th>
                <th className="hand" onClick={sort('amountOfCartNet')}>
                  <Translate contentKey="testShopApp.orderMain.amountOfCartNet">Amount Of Cart Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfCartNet')} />
                </th>
                <th className="hand" onClick={sort('amountOfCartGross')}>
                  <Translate contentKey="testShopApp.orderMain.amountOfCartGross">Amount Of Cart Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfCartGross')} />
                </th>
                <th className="hand" onClick={sort('amountOfShipmentNet')}>
                  <Translate contentKey="testShopApp.orderMain.amountOfShipmentNet">Amount Of Shipment Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfShipmentNet')} />
                </th>
                <th className="hand" onClick={sort('amountOfShipmentGross')}>
                  <Translate contentKey="testShopApp.orderMain.amountOfShipmentGross">Amount Of Shipment Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfShipmentGross')} />
                </th>
                <th className="hand" onClick={sort('amountOfOrderNet')}>
                  <Translate contentKey="testShopApp.orderMain.amountOfOrderNet">Amount Of Order Net</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfOrderNet')} />
                </th>
                <th className="hand" onClick={sort('amountOfOrderGross')}>
                  <Translate contentKey="testShopApp.orderMain.amountOfOrderGross">Amount Of Order Gross</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountOfOrderGross')} />
                </th>
                <th className="hand" onClick={sort('orderMainStatus')}>
                  <Translate contentKey="testShopApp.orderMain.orderMainStatus">Order Main Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderMainStatus')} />
                </th>
                <th className="hand" onClick={sort('createTime')}>
                  <Translate contentKey="testShopApp.orderMain.createTime">Create Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createTime')} />
                </th>
                <th className="hand" onClick={sort('updateTime')}>
                  <Translate contentKey="testShopApp.orderMain.updateTime">Update Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updateTime')} />
                </th>
                <th>
                  <Translate contentKey="testShopApp.orderMain.user">User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderMainList.map((orderMain, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/order-main/${orderMain.id}`} color="link" size="sm">
                      {orderMain.id}
                    </Button>
                  </td>
                  <td>{orderMain.buyerLogin}</td>
                  <td>{orderMain.buyerFirstName}</td>
                  <td>{orderMain.buyerLastName}</td>
                  <td>{orderMain.buyerEmail}</td>
                  <td>{orderMain.buyerPhone}</td>
                  <td>{orderMain.amountOfCartNet}</td>
                  <td>{orderMain.amountOfCartGross}</td>
                  <td>{orderMain.amountOfShipmentNet}</td>
                  <td>{orderMain.amountOfShipmentGross}</td>
                  <td>{orderMain.amountOfOrderNet}</td>
                  <td>{orderMain.amountOfOrderGross}</td>
                  <td>
                    <Translate contentKey={`testShopApp.OrderMainStatusEnum.${orderMain.orderMainStatus}`} />
                  </td>
                  <td>{orderMain.createTime ? <TextFormat type="date" value={orderMain.createTime} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{orderMain.updateTime ? <TextFormat type="date" value={orderMain.updateTime} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{orderMain.user ? orderMain.user.login : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/order-main/${orderMain.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/order-main/${orderMain.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/order-main/${orderMain.id}/delete`)}
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
              <Translate contentKey="testShopApp.orderMain.home.notFound">No Order Mains found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default OrderMain;
