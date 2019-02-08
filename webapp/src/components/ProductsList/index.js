import React, { Component} from 'react'
import PropTypes from 'prop-types'
import ProductItem from '../../components/ProductItem'
import AppNavBar from '../../AppNavBar';

import './styles.css'

export default class ProductsList extends Component {

  render() {
    const { products, addToCart } = this.props
    return (
      <div>
        {/*<AppNavBar/>*/}
        <div className="productListWrapper">
          {products.map(product => (
            <ProductItem
              key={product.productId}
              product={product}
              onAddToCartClicked={addToCart}
            />
          ))}
        </div>
      </div>
    )
  }
}

ProductsList.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    description: PropTypes.string,
    image: PropTypes.string,
  })).isRequired,
  addToCart: PropTypes.func.isRequired,
}

