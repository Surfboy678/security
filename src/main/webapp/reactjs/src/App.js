
import './App.css';
import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Footer from './components/Footer';
import {Container, Row,  Col } from 'react-bootstrap';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import Login from './components/Register';

function App() {
  const marginTop = {
    marginTop : "10px"
  }
  return (
    <Router>
      <NavigationBar/>
        <Container>
          <Row>
            <Col lg = {12} style = {marginTop}>
              <Switch>
                  <Route path="/" exact component ={Welcome}/>
                  <Route path="/login" exact component ={Login}/>               
              </Switch>            
            </Col>
          </Row>
        </Container>
        <Footer/>
        </Router>
  );
}
export default App;
