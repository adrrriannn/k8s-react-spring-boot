import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';
import './styles.css';
import './globalStyles.css';
import {
    getJwtToken,
    removeJwtToken, setJwtToken
} from './actions/storage';
import Cart from "./components/Cart";

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            authenticated: getJwtToken() != null
        };
    }

    renderUnauthenticated() {
        return (
            <Nav className="ml-auto" navbar>
                <Cart/>
                <NavItem>
                    <NavLink tag={Link} to={"/register"}>Create User</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink tag={Link} to={"/login"}>Login</NavLink>
                </NavItem>
            </Nav>
        );
    }

    removeToken = () => {
        this.setState({
            authenticated: false
        });
        this.setState(this.state);
        this.props.history.push("/products");
    };

    renderAuthenticated() {
        return (
            <Nav className="ml-auto" navbar>
                <Cart/>
                <NavItem>
                    <NavLink>Welcome</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="#" onClick={removeJwtToken()}>Log out</NavLink>
                </NavItem>
            </Nav>
        );
    }

    render() {
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            <NavLink href="https://github.com/adrrriannn">GitHub</NavLink>
            <Collapse navbar>
                {this.state.authenticated
                    ? this.renderAuthenticated()
                    : this.renderUnauthenticated()}
            </Collapse>
        </Navbar>;
    }
}
