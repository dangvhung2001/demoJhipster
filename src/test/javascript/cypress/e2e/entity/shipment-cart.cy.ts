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

describe('ShipmentCart e2e test', () => {
  const shipmentCartPageUrl = '/shipment-cart';
  const shipmentCartPageUrlPattern = new RegExp('/shipment-cart(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const shipmentCartSample = {};

  let shipmentCart;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/shipment-carts+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/shipment-carts').as('postEntityRequest');
    cy.intercept('DELETE', '/api/shipment-carts/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (shipmentCart) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/shipment-carts/${shipmentCart.id}`,
      }).then(() => {
        shipmentCart = undefined;
      });
    }
  });

  it('ShipmentCarts menu should load ShipmentCarts page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('shipment-cart');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('ShipmentCart').should('exist');
    cy.url().should('match', shipmentCartPageUrlPattern);
  });

  describe('ShipmentCart page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(shipmentCartPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create ShipmentCart page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/shipment-cart/new$'));
        cy.getEntityCreateUpdateHeading('ShipmentCart');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', shipmentCartPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/shipment-carts',
          body: shipmentCartSample,
        }).then(({ body }) => {
          shipmentCart = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/shipment-carts+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [shipmentCart],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(shipmentCartPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details ShipmentCart page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('shipmentCart');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', shipmentCartPageUrlPattern);
      });

      it('edit button click should load edit ShipmentCart page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ShipmentCart');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', shipmentCartPageUrlPattern);
      });

      it('edit button click should load edit ShipmentCart page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ShipmentCart');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', shipmentCartPageUrlPattern);
      });

      it('last delete button click should delete instance of ShipmentCart', () => {
        cy.intercept('GET', '/api/shipment-carts/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('shipmentCart').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', shipmentCartPageUrlPattern);

        shipmentCart = undefined;
      });
    });
  });

  describe('new ShipmentCart page', () => {
    beforeEach(() => {
      cy.visit(`${shipmentCartPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('ShipmentCart');
    });

    it('should create an instance of ShipmentCart', () => {
      cy.get(`[data-cy="firstName"]`).type('Geovanni');
      cy.get(`[data-cy="firstName"]`).should('have.value', 'Geovanni');

      cy.get(`[data-cy="lastName"]`).type('Harber');
      cy.get(`[data-cy="lastName"]`).should('have.value', 'Harber');

      cy.get(`[data-cy="street"]`).type('Torey Crest');
      cy.get(`[data-cy="street"]`).should('have.value', 'Torey Crest');

      cy.get(`[data-cy="postalCode"]`).type('who smooth gee');
      cy.get(`[data-cy="postalCode"]`).should('have.value', 'who smooth gee');

      cy.get(`[data-cy="city"]`).type('Hilllburgh');
      cy.get(`[data-cy="city"]`).should('have.value', 'Hilllburgh');

      cy.get(`[data-cy="country"]`).type('Thailand');
      cy.get(`[data-cy="country"]`).should('have.value', 'Thailand');

      cy.get(`[data-cy="phoneToTheReceiver"]`).type('compensate');
      cy.get(`[data-cy="phoneToTheReceiver"]`).should('have.value', 'compensate');

      cy.get(`[data-cy="firm"]`).type('take gosh');
      cy.get(`[data-cy="firm"]`).should('have.value', 'take gosh');

      cy.get(`[data-cy="taxNumber"]`).type('espalier throughout');
      cy.get(`[data-cy="taxNumber"]`).should('have.value', 'espalier throughout');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        shipmentCart = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', shipmentCartPageUrlPattern);
    });
  });
});
