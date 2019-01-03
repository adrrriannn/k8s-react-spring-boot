import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavBar from './AppNavBar';
import { Link } from 'react-router-dom';

class ProductList extends Component {

    constructor(props) {
        super(props);
        this.state = {products: [], isLoading: true};
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('http://localhost:9003/products')
            .then(response => response.json())
            .then(data => this.setState({products: data, isLoading: false}));
    }

    // async remove(id) {
    //     await fetch(`/api/group/${id}`, {
    //         method: 'DELETE',
    //         headers: {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json'
    //         }
    //     }).then(() => {
    //         let updatedGroups = [...this.state.groups].filter(i => i.id !== id);
    //         this.setState({groups: updatedGroups});
    //     });
    // }

    render() {
        const {products, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const productList = products.map(product => {
            return <tr key={product.id}>
                <td style={{whiteSpace: 'nowrap'}}>{product.name}</td>
                <td style={{whiteSpace: 'nowrap'}}>{product.description}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/orders?product-id=" + product.id }>Order</Button>
                        {/*<Button size="sm" color="danger" onClick={() => this.remove(group.id)}>Delete</Button>*/}
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/products/new">Add Product</Button>
                    </div>
                    <h3>Products</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Description</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {productList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ProductList;
