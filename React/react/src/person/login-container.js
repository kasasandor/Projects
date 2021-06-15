import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import validate from "./components/validators/person-validators"
import Button from 'react-bootstrap/Button';
import { Input, FormGroup, Label, Row, Col} from 'reactstrap';
import "../commons/styles/Login.css";
import * as API_USERS from "./api/person-api";
import { Redirect } from 'react-router'

class LoginContainer extends React.Component {


    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            errorStatus: 0,
            error: null,
            userData: [],
            loggedIn: false,
            formControls: {
                username: {
                    value: '',
                    placeholder: 'Username',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },
                password: {
                    value: '',
                    placeholder: 'Password',
                    valid: false,
                    touched: false,
                }
            }
        };
    }
    
    loginUser(person){
        return API_USERS.loginPerson(person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                this.setState({userData: result, loggedIn: true});
                sessionStorage.setItem('userID', result['id']);
                sessionStorage.setItem('role', result['role']);
                window.location.reload();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    handleSubmit() {
        let person = {
            username: this.state.formControls.username.value,
            password: this.state.formControls.password.value,
        };

        console.log(person);
        this.loginUser(person);
    }
    
    render(){
        return(
            <div className="center">
            
              <FormGroup id='username'>
                    <Label for='usernameField'> Username: </Label>
                    <Input name='username' id='usernameField' placeholder={this.state.formControls.username.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.username.value}
                           touched={this.state.formControls.username.touched? 1 : 0}
                           valid={this.state.formControls.username.valid}
                           required
                    />
                    {this.state.formControls.username.touched && !this.state.formControls.username.valid &&
                    <div className={"error-message row"}> * Userame must have at least 3 characters </div>}
                </FormGroup>

                <FormGroup id='password'>
                    <Label for='passwordField'> Password: </Label>
                    <Input type='password' name='password' id='passwordField' placeholder={this.state.formControls.password.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.password.value}
                           touched={this.state.formControls.password.touched? 1 : 0}
                           valid={this.state.formControls.password.valid}
                           required
                    />
                </FormGroup>
            
                <Row>
                        <Col>
                            <Button type={"submit"} 
                                    block 
                                    disabled={!this.state.formIsValid} 
                                    onClick={this.handleSubmit}>Login </Button>
                        </Col>
                </Row>
                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }

                {
                    this.state.loggedIn &&
                    <Redirect to="/" />
                }
                
          </div>
        
        );
    }
}

export default LoginContainer;