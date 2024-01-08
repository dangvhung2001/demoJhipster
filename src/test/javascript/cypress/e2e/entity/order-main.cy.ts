import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('OrderMain e2e test', () => {
  const orderMainPageUrl = '/order-main';
  const orderMainPageUrlPattern = new RegExp('/order-main(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const orderMainSample = {};

  let orderMain;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/order-mains+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/order-mains').as('postEntityRequest');
    cy.intercept('DELETE', '/api/order-mains/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (orderMain) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/order-mains/${orderMain.id}`,
      }).then(() => {
        orderMain = undefined;
      });
    }
  });

  it('OrderMains menu should load OrderMains page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('order-main');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('OrderMain').should('exist');
    cy.url().should('match', orderMainPageUrlPattern);
  });

  describe('OrderMain page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(orderMainPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create OrderMain page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/order-main/new$'));
        cy.getEntityCreateUpdateHeading('OrderMain');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', orderMainPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/order-mains',
          body: orderMainSample,
        }).then(({ body }) => {
          orderMain = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/order-mains+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [orderMain],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(orderMainPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details OrderMain page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('orderMain');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', orderMainPageUrlPattern);
      });

      it('edit button click should load edit OrderMain page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('OrderMain');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', orderMainPageUrlPattern);
      });

      it('edit button click should load edit OrderMain page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('OrderMain');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', orderMainPageUrlPattern);
      });

      it('last delete button click should delete instance of OrderMain', () => {
        cy.intercept('GET', '/api/order-mains/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('orderMain').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', orderMainPageUrlPattern);

        orderMain = undefined;
      });
    });
  });

  describe('new OrderMain page', () => {
    beforeEach(() => {
      cy.visit(`${orderMainPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('OrderMain');
    });

    it('should create an instance of OrderMain', () => {
      cy.get(`[data-cy="buyerLogin"]`).type('across');
      cy.get(`[data-cy="buyerLogin"]`).should('have.value', 'across');

      cy.get(`[data-cy="buyerFirstName"]`).type('prompt');
      cy.get(`[data-cy="buyerFirstName"]`).should('have.value', 'prompt');

      cy.get(`[data-cy="buyerLastName"]`).type('even than hovel');
      cy.get(`[data-cy="buyerLastName"]`).should('have.value', 'even than hovel');

      cy.get(`[data-cy="buyerEmail"]`).type('polished well-to-do papaya');
      cy.get(`[data-cy="buyerEmail"]`).should('have.value', 'polished well-to-do papaya');

      cy.get(`[data-cy="buyerPhone"]`).type('duff but');
      cy.get(`[data-cy="buyerPhone"]`).should('have.value', 'duff but');

      cy.get(`[data-cy="amountOfCartNet"]`).type('20352.94');
      cy.get(`[data-cy="amountOfCartNet"]`).should('have.value', '20352.94');

      cy.get(`[data-cy="amountOfCartGross"]`).type('19883.9');
      cy.get(`[data-cy="amountOfCartGross"]`).should('have.value', '19883.9');

      cy.get(`[data-cy="amountOfShipmentNet"]`).type('16769.55');
      cy.get(`[data-cy="amountOfShipmentNet"]`).should('have.value', '16769.55');

      cy.get(`[data-cy="amountOfShipmentGross"]`).type('371.73');
      cy.get(`[data-cy="amountOfShipmentGross"]`).should('have.value', '371.73');

      cy.get(`[data-cy="amountOfOrderNet"]`).type('18618.88');
      cy.get(`[data-cy="amountOfOrderNet"]`).should('have.value', '18618.88');

      cy.get(`[data-cy="amountOfOrderGross"]`).type('15080.52');
      cy.get(`[data-cy="amountOfOrderGross"]`).should('have.value', '15080.52');

      cy.get(`[data-cy="orderMainStatus"]`).select('PreparationForShipping');

      cy.get(`[data-cy="createTime"]`).type('2024-01-07T14:45');
      cy.get(`[data-cy="createTime"]`).blur();
      cy.get(`[data-cy="createTime"]`).should('have.value', '2024-01-07T14:45');

      cy.get(`[data-cy="updateTime"]`).type('2024-01-07T19:52');
      cy.get(`[data-cy="updateTime"]`).blur();
      cy.get(`[data-cy="updateTime"]`).should('have.value', '2024-01-07T19:52');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        orderMain = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', orderMainPageUrlPattern);
    });
  });
});
