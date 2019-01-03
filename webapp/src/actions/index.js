import shop from '../api/shop'
import * as types from '../constants/ActionTypes'
const request = require('superagent-promise')(require('superagent'), Promise)

// DUMMY ITEMS
const fetchDummyItems = products => ({
  type: types.DUMMY_ITEMS_REQUEST,
  products: products
})

export const fetchAllDummyItems = () => dispatch => {
  shop.getProducts(products => {
    dispatch(fetchDummyItems(products))
  })
}

export const fetchProducts = () => dispatch => {
    shop.getProducts(products => {
        dispatch
    })
}


export const addToCart = productId => (dispatch, getState) => {
    // dispatch(addToCartUnsafe(productId))
    // dispatch(showAddToCart())
    // setTimeout(() => {
    //     dispatch(resetItemAdded())
    // }, 2500)
}

export const loginCustomer = (loginObject) => (dispatch) => {
    let dispatchObj = {
        type: types.LOGIN_CUSTOMER,
        payload: {
            promise:
                request
                    .post('http://localhost:9005/users/login')
                    .set('Content-Type', 'application/json')
                    .accept('application/json')
                    .send(loginObject)
                    .end()
                    .then((res) => res.body)
        },
    }
    return dispatch(dispatchObj)
}

export const createCustomer = (signInObject) => (dispatch) => {
    let dispatchObj = {
        type: types.CREATE_CUSTOMER,
        payload: {
            promise:
                request
                    .post('http://localhost:9005/users/sign-in')
                    .set('Content-Type', 'application/json')
                    .accept('application/json')
                    .send(signInObject)
                    .end()
                    .then((res) => res.body)
        },
    }
    return dispatch(dispatchObj)
}
