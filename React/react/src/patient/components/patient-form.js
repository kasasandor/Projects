import React from 'react';
import validate from "./validators/patient-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/patient-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';
import Select from 'react-dropdown-select';

class PatientForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            genderValue: null,
            dayValue: null,
            monthValue: null,
            yearValue: null,

            errorStatus: 0,
            error: null,

            formIsValid: true,

            formControls: {
                name: {
                    value: '',
                    placeholder: 'What is your name?...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },
                gender: {
                    options: [{value: 'male', label: 'Male'},
                            {value: 'female', label: 'Female'}],
                                
                },
                address: {
                    value: '',
                    placeholder: 'Cluj, Zorilor, Str. Lalelelor 21...',
                    valid: false,
                    touched: false,
                }
            },
        };

        this.handleChange = this.handleChange.bind(this);
        //this.handelSelectChange = this.handelSelectChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
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

    registerDoctor(doctor) {
        return API_USERS.postPatient(doctor, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted patient with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    fillOptions(){
        var days = [];
        for(var i = 1; i <= 31; i++){
            days.push({value: i, label: i});
        }
        //console.log(days);
        var months = [];
        for(i = 1; i <= 12; i++){
            months.push({value: i, label: i})
        }
        var years = [];
        for(i = 2020; i >= 1900; i--){
            years.push({value: i, label: i})
        }

        var options = {
            days: days,
            months: months,
            years: years
        }

        return options;
    }

    handleSubmit() {
        let dob = new Date(this.state.yearValue, 
                        this.state.monthValue - 1, 
                        this.state.dayValue);
        let patient = {
            name: this.state.formControls.name.value,
            address: this.state.formControls.address.value,
            gender: this.state.genderValue,
            dob: dob
        };

        console.log(patient);
        this.registerDoctor(patient);
    }

    render() {
        return (
            <div>

                <FormGroup id='name'>
                    <Label for='nameField'> Name: </Label>
                    <Input name='name' id='nameField' placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                    {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                    <div className={"error-message row"}> * Name must have at least 3 characters </div>}
                </FormGroup>

                <FormGroup id='gender'>
                    <Label for='genderField'> Gender: </Label>
                    <Select name='gender' id='gendereSelect' 
                            options={this.state.formControls.gender.options} 
                            onChange={value => this.setState({genderValue: value[0]["value"]})}/>
                </FormGroup>

                <FormGroup id='address'>
                    <Label for='addressField'> Address: </Label>
                    <Input name='address' id='addressField' placeholder={this.state.formControls.address.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.address.value}
                           touched={this.state.formControls.address.touched? 1 : 0}
                           valid={this.state.formControls.address.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='dob'>
                    <Label for='dobField'> Date of Birth: </Label>
                    <Row>
                        <Col>
                            <Select options={this.fillOptions().days} 
                                    placeholder='Day' 
                                    onChange={value => this.setState({dayValue: value[0]["value"]})}/>
                        </Col>
                        <Col>
                            <Select options={this.fillOptions().months} 
                                    placeholder='Month'
                                    onChange={value => this.setState({monthValue: value[0]["value"]})} />
                        </Col>
                        <Col>
                            <Select options={this.fillOptions().years} 
                                    placeholder='Year'
                                    onChange={value => this.setState({yearValue: value[0]["value"]})} />
                        </Col>
                    </Row>
                </FormGroup>

                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={this.state.formIsValid} onClick={this.handleSubmit}>  Submit </Button>
                        </Col>
                    </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default PatientForm;