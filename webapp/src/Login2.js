import React, { Component } from "react";
import { Button, Container} from 'reactstrap';
import { FormGroup, ControlLabel,  } from "react-bootstrap";
import { Input} from 'reactstrap';
import "./Login.css";
import AppNavBar from './AppNavBar';
import { setJwtToken } from './actions/storage';
import {SubmissionError} from "redux-form";

export default class Login extends Component {
    loginObject = {
        email: '',
        password: ''
    }

    constructor(props) {
        super(props);

        this.state = {
            credentials: this.loginObject
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    validateForm() {
        return this.state.credentials.email.length > 0 && this.state.credentials.password.length > 0;
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let credentials = {...this.state.credentials};
        credentials[name] = value;
        this.setState({credentials});
    }

    // handleLoginSuccess = (loginResponse) => {
    //     setJwtToken(loginResponse.token);
    //     this.setState({ authenticated: true });
    //     this.setState({ loginSuccessful: true });
    // };

    handleLoginSuccess(signInObject) {
        this.setState({ createUserSuccessful: true });

        // temporary sleep so that login will work
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if (new Date().getTime() - start > 1000) {
                break;
            }
        }

        setJwtToken(signInObject.token);
        this.setState({ authenticated: true });
        this.setState({ loginSuccessful: true });
    }

    // async handleSubmit(event) {
    //     event.preventDefault();
    //     const {credentials} = this.state;
    //
    //     fetch('http://localhost:9005/users/login', {
    //         method: 'POST',
    //         headers: {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(credentials),
    //     }).then(res => res.json())
    //         .then(response => this.handleLoginSuccess(response))
    //         .catch(error => console.error('Error:', error));
    //     this.props.history.push("/products");
    // }

    async handleSubmit(event) {
        event.preventDefault();
        const {credentials} = this.state;

        await fetch('http://localhost:9005/users/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
        })
            .then((response) => {
                this.handleCreateUserSuccess(response.body);
                this.props.history.push('/products');
            })
            .catch(err => {
                console.log("Error.");
            });
    }

    render() {

        const {credentials} = this.state;
        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="Login">
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="email" bsSize="large">
                                <ControlLabel>Email</ControlLabel>
                                <Input type="text" name="email" id="email" value={credentials.email || ''}
                                       onChange={this.handleChange} autoComplete="email"/>
                            </FormGroup>
                            <FormGroup controlId="password" bsSize="large">
                                <ControlLabel>Password</ControlLabel>
                                <Input type="password" name="password" id="password" value={credentials.password || ''}
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
