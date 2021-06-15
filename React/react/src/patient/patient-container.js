import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import PatientForm from "./components/patient-form";

import * as API_USERS from "./api/patient-api"
import PatientTable from "./components/patient-table";

class PatientContainer extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadAfterAdd = this.reloadAfterAdd.bind(this);
        this.reload = this.reload.bind(this);
        this.state = {
            selected: false,
            selectedDelete: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPatients();
    }

    fetchPatients() {
        return API_USERS.getPatients((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    reloadAfterAdd() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchPatients();
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.fetchPatients();
    }

    render() {  
        return (
            <div>
                <CardHeader>
                    <strong> Patient Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Patient </Button>
                        </Col>
                    </Row>
                    <Row>
                        
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <PatientTable reloadHandler={this.reload} tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Patient: </ModalHeader>
                    <ModalBody>
                        <PatientForm reloadHandler={this.reloadAfterAdd}/>
                    </ModalBody>
                </Modal>
            </div>
        )

    }
}

export default PatientContainer;
