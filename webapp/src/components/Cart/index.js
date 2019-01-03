import React from 'react'
import CartIcon from '../../components/CartIcon'
import './styles.css'

const Cart = ({ total, showItemAdded }) => {
  return (
        <div className="cartQuantity">
          <CartIcon />
          <div className="cartDigit">
            {total}
          </div>
        </div>
  )
}

Cart.propTypes = {
  total: 0,
  showItemAdded: false
}

export default Cart
