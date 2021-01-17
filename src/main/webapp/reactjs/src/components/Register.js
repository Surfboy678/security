import React from 'react';
import{Form, Button} from 'react-bootstrap';

import axios from 'axios';


class Login extends React.Component{

  constructor(props){
    super(props);
    this.state = this.initialState;
    this.userChange = this.userChange.bind(this);
    this.submitUser = this.submitUser.bind(this);
  }

  initialState = {
    username: '', password: ''
  }

  submitUser = event => {
    event.preventDefault();

    const user = {
      username: this.state.username,   
      password: this.state.password

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
  <Button variant="primary" type="submit">
    Submit
  </Button>
</Form>
                        
</div>
        );
    }
}
export default Login;