import React from 'react';
import{Form, Button} from 'react-bootstrap';


class Login extends React.Component{

  constructor(props){
    super(props);
    this.state = {userName: '', password: ''}
    this.userChange = this.userChange.bind(this);
    this.submitUser = this.submitUser.bind(this);
  }

  submitUser(event){
    alert('userName: ' + this.state.userName+  ' password: ' +this.state.password );
    event.preventDefault();
  }

  userChange(event){
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
    <Form.Control required
    type="text" name="userName" 
    value={this.state.userName}
    onChange={this.userChange}
    placeholder="Enter userName" />
    <Form.Text className="text-muted">
      We'll never share your email with anyone else.
    </Form.Text>
  </Form.Group>
  <Form.Group controlId="formBasicPassword">
    <Form.Label>Password</Form.Label>
    <Form.Control required
    type="text" name="password"
    value={this.state.password}
    onChange={this.userChange}
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