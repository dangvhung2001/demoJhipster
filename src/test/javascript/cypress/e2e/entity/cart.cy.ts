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

describe('Cart e2e test', () => {
  const cartPageUrl = '/cart';
  const cartPageUrlPattern = new RegExp('/cart(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const cartSample = {};

  let cart;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/carts+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/carts').as('postEntityRequest');
    cy.intercept('DELETE', '/api/carts/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (cart) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/carts/${cart.id}`,
      }).then(() => {
        cart = undefined;
      });
    }
  });

  it('Carts menu should load Carts page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('cart');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Cart').should('exist');
    cy.url().should('match', cartPageUrlPattern);
  });

  describe('Cart page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(cartPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Cart page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/cart/new$'));
        cy.getEntityCreateUpdateHeading('Cart');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', cartPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/carts',
          body: cartSample,
        }).then(({ body }) => {
          cart = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/carts+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [cart],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(cartPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Cart page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('cart');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', cartPageUrlPattern);
      });

      it('edit button click should load edit Cart page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Cart');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', cartPageUrlPattern);
      });

      it('edit button click should load edit Cart page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Cart');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', cartPageUrlPattern);
      });

      it('last delete button click should delete instance of Cart', () => {
        cy.intercept('GET', '/api/carts/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('cart').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', cartPageUrlPattern);

        cart = undefined;
      });
    });
  });

  describe('new Cart page', () => {
    beforeEach(() => {
      cy.visit(`${cartPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Cart');
    });

    it('should create an instance of Cart', () => {
      cy.get(`[data-cy="amountOfCartNet"]`).type('32134.09');
      cy.get(`[data-cy="amountOfCartNet"]`).should('have.value', '32134.09');

      cy.get(`[data-cy="amountOfCartGross"]`).type('5142.71');
      cy.get(`[data-cy="amountOfCartGross"]`).should('have.value', '5142.71');

      cy.get(`[data-cy="amountOfShipmentNet"]`).type('2597.79');
      cy.get(`[data-cy="amountOfShipmentNet"]`).should('have.value', '2597.79');

      cy.get(`[data-cy="amountOfShipmentGross"]`).type('7492.03');
      cy.get(`[data-cy="amountOfShipmentGross"]`).should('have.value', '7492.03');

      cy.get(`[data-cy="amountOfOrderNet"]`).type('8760.41');
      cy.get(`[data-cy="amountOfOrderNet"]`).should('have.value', '8760.41');

      cy.get(`[data-cy="amountOfOrderGross"]`).type('10573.52');
      cy.get(`[data-cy="amountOfOrderGross"]`).should('have.value', '10573.52');

      cy.get(`[data-cy="quantity"]`).type('5621');
      cy.get(`[data-cy="quantity"]`).should('have.value', '5621');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        cart = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', cartPageUrlPattern);
    });
  });
});
