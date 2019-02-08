import React, { Component } from 'react';
import './App.css';
import ProductContainer from './containers/ProductsContainer'
class Home extends Component {
    render() {
        return (
            <div>
                <ProductContainer/>
            </div>
        );
    }
}

export default Home;
