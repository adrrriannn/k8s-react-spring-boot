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
import ProductContainer from './containers/ProductsContainer'
import getMuiTheme from 'material-ui/styles/getMuiTheme'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import { Provider } from 'react-redux'
import AppNavbar from "./AppNavBar";
import {
    getJwtToken,
    removeJwtToken, setJwtToken
} from './actions/storage';
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

    state = {
        authenticated: getJwtToken() != null
    };

    removeToken = () => {
        removeJwtToken();
        this.setState({
            authenticated: false
        });
        // this.setState(this.state);
        // this.props.history.push("/products");
    };

    saveToken = (token) => {
        console.log("Saving jwttoken :" + token);
        setJwtToken(token);
        this.setState({ authenticated: true });
    };

    render() {
        store.dispatch(fetchAllDummyItems());
        return (
            <Provider store={store}>
                <MuiThemeProvider muiTheme={muiTheme}>
                    <Router>
                    <div>
                        <AppNavbar authenticated={this.state.authenticated} removeToken={this.removeToken}/>
                        <Switch>
                            <Route path='/' exact={true} component={Home}/>
                            <Route path='/login' exact={true} component={() => <Login saveToken ={this.saveToken}/>}/>
                            <Route path='/register' exact={true} component={Register}/>
                            <Route path='/products' exact={true} component={ProductContainer}/>
                            <Route path='/products/:id' component={AddProduct}/>
                            <Route path='/orders' component={ConfirmOrder}/>
                        </Switch>
                    </div>
                    </Router>
                </MuiThemeProvider>
            </Provider>
        )
    }
}

export default App;
