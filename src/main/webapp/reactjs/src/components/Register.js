import React from 'react';
import{Form, Button, Col} from 'react-bootstrap';

import axios from 'axios';


class Register extends React.Component{

  constructor(props){
    super(props);
    this.state = this.initialState;
    this.userChange = this.userChange.bind(this);
    this.submitUser = this.submitUser.bind(this);
  }

  initialState = {
    username: '', password: '', role: ''
  }

  submitUser = event => {
    event.preventDefault();

    const user = {
      username: this.state.username,   
      password: this.state.password,
      role: this.state.role

    };

    axios.post("http://localhost:8080/register", user)
    .then(response => {
      if(response.data != null){
          this.setState(this.initialState);
          alert("user register succes")
      }

    });

  }

  userChange = event =>{
    this.setState({
      [event.target.name]:event.target.value
    })
  }

    render(){
        return(
        <div>
 <Form onSubmit={this.submitUser} id = "loginFormId">
  <Form.Group controlId="formBasicEmail">
    <Form.Label>User Name</Form.Label>
    <Form.Control required autoComplete="off"
    type="text" name="username" 
    value={this.state.username}  onChange={this.userChange}
       placeholder="Enter email" />
    <Form.Text className="text-muted">
      We'll never share your email with anyone else.
    </Form.Text>
  </Form.Group>
  <Form.Group controlId="formBasicPassword">
    <Form.Label>Password</Form.Label>
    <Form.Control required autoComplete="off"
    type="text" name="password"
    value={this.state.password} onChange={this.userChange}
     placeholder="Password" />
  </Form.Group>
  
  <Col xs="auto" className="my-1">
      <Form.Label className="mr-sm-2" htmlFor="inlineFormCustomSelect" srOnly>
        Preference
      </Form.Label>
      <Form.Control
      type= "text" name ="role"
       value={this.state.role} onChange={this.userChange}
        as="select"
        className="mr-sm-2"
        id="inlineFormCustomSelect"
        custom
      >
        <option value="0">Choose Role...</option>
        <option value="ROLE_USER" >USER</option>
        <option value="ROLE_ADMIN">ADMIN</option>
      </Form.Control>
    </Col>
    
  <Button variant="primary" type="submit">
    Submit
  </Button>
</Form>
                        
</div>
        );
    }
}
export default Register;