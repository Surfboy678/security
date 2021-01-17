import React from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';


class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <Navbar.Brand href="/">Security</Navbar.Brand>
                </Link>
                <Nav className="mr-auto">
                    <Link to={"/login"} className="nav-link">Register</Link>                   
                </Nav>
            </Navbar>
        );
    }

}

export default NavigationBar;