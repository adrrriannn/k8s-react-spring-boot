import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';
import './styles.css';
import './globalStyles.css';
import {
    getJwtToken
} from './actions/storage';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
    }

    renderUnauthenticated() {
        return (
            <Nav className="ml-auto" navbar>
                <NavItem>
                    <NavLink tag={Link} to={"/register"}>Create User</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink tag={Link} to={"/login"}>Login</NavLink>
                </NavItem>
            </Nav>
        );
    }

    renderAuthenticated() {
        return (
            <Nav className="ml-auto" navbar>
                <NavItem>
                    <NavLink>Welcome</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="#" onClick={this.props.removeToken}>Log out</NavLink>
                </NavItem>
            </Nav>
        );
    }

    render() {
        console.log(this.props);
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            <NavLink href="https://github.com/adrrriannn">GitHub</NavLink>
            <Collapse navbar>
                {this.props.authenticated
                    ? this.renderAuthenticated()
                    : this.renderUnauthenticated()}
            </Collapse>
        </Navbar>;
    }
}
