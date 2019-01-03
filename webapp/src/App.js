import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AddProduct from "./AddProduct";
import ConfirmOrder from "./ConfirmOrder";
import Login from "./Login";
import Register from "./Register";
import reducer from './reducers'
import thunkMiddleware from 'redux-thunk'
import { createStore, applyMiddleware } from 'redux'
import {fetchAllDummyItems} from './actions'
import promiseMiddleware from 'redux-promise-middleware'
import ProductList from './components/ProductsList';
import ProductContainer from './containers/ProductsContainer'
import getMuiTheme from 'material-ui/styles/getMuiTheme'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import { Provider } from 'react-redux'
const middleware = [
    thunkMiddleware,
    promiseMiddleware({
        promiseTypeSuffixes: ['REQ', 'ACK', 'ERR'],
    }),
];


const muiTheme = getMuiTheme({
    textField: {
        focusColor: '#9fa5a8',
    },
})

const store = createStore(
    reducer,
    applyMiddleware(...middleware),
)

class App extends Component {


    render() {
        store.dispatch(fetchAllDummyItems());
        return (
            <Provider store={store}>
                <MuiThemeProvider muiTheme={muiTheme}>
                    <Router>
                        <Switch>
                            <Route path='/' exact={true} component={Home}/>
                            <Route path='/login' exact={true} component={Login}/>
                            <Route path='/register' exact={true} component={Register}/>
                            <Route path='/products' exact={true} component={ProductContainer}/>
                            <Route path='/products/:id' component={AddProduct}/>
                            <Route path='/orders' component={ConfirmOrder}/>
                        </Switch>
                    </Router>
                </MuiThemeProvider>
            </Provider>
        )
    }
}

export default App;
