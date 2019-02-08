import React, { Component } from "react";
import { Button, Container} from 'reactstrap';
import { FormGroup, ControlLabel } from "react-bootstrap";
import "./Login.css";
import AppNavBar from './AppNavBar';
import { Input} from 'reactstrap';
import {SubmissionError} from "redux-form";
import {setJwtToken} from "./actions/storage";

export default class Login extends Component {
    credentials = {
        email: '',
        password: ''
    };

    constructor(props) {
        super(props);

        this.state = {
            item: this.credentials
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    validateForm() {
        return this.state.item.email.length > 0 && this.state.item.password.length > 0;
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    handleLoginSuccess(signInObject) {
        this.setState({ createUserSuccessful: true });

        // temporary sleep so that login will work
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if (new Date().getTime() - start > 1000) {
                break;
            }
        }
        console.log("Sending token to app: " + signInObject.token);
        this.props.saveToken(signInObject.token);
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('http://localhost:9005/users/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        })
        .then((response) => {
            this.handleLoginSuccess(response.body);
            // this.props.history.push('/products');
            window.location.href = '/products';
        })
        .catch(err => {
            console.log("Error logging in: " + err);
        });
    }

    render() {
        const {item} = this.state;
        return (
            <div>
                {/*<AppNavBar/>*/}
                <Container fluid>
                    <div className="Login">
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="email" bsSize="large">
                                <ControlLabel>Email</ControlLabel>
                                <Input type="text" name="email" id="email" value={item.email || ''}
                                       onChange={this.handleChange} autoComplete="email"/>
                            </FormGroup>
                            <FormGroup controlId="password" bsSize="large">
                                <ControlLabel>Password</ControlLabel>
                                <Input type="password" name="password" id="password" value={item.password || ''}
                                       onChange={this.handleChange} autoComplete="password"/>
                            </FormGroup>
                            <Button size="lg" color="success" disabled={!this.validateForm()} type="submit" block >Login</Button>
                        </form>
                    </div>
                </Container>
            </div>
        );
    }
}
