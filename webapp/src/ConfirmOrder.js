import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavBar from './AppNavBar';

class ConfirmOrder extends Component {

    order = {
        email: '',
        productId: ''
    };

    product = {
        name: '',
        description: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.order
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        const search = this.props.location.search; // could be '?foo=bar'
        const params = new URLSearchParams(search);
        const productId = params.get('product-id');
        const productResponse = await (await fetch(`http://localhost:9003/products/${productId}`)).json();
        this.order.productId = productId;
        console.log("Response product:" + productResponse);
        this.product.name = productResponse.name;
        this.product.description = productResponse.description;
        console.log("This product:" + this.product);

    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('http://localhost:9002/orders', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });

        this.props.history.push('/products');
    }

    render() {

        return <div>
            <AppNavBar/>
            <Container>
                <h2>Order Confirmation</h2>
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email"
                               onChange={this.handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="product_description">Description</Label>
                        <Input type="text" name="product_description" id="product_description" value={this.product.description} readOnly/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="product_name">Product Name</Label>
                        <Input type="text" name="product_name" id="product_name" value={this.product.name} readOnly/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/products">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(ConfirmOrder);
