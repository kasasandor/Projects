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
import CaretakerForm from "./components/caretaker-form";

import * as API_USERS from "./api/caretaker-api"
import CaretakerTable from "./components/caretaker-table";

class CaretakerContainer extends React.Component {

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
        this.fetchCaretakers();
    }

    fetchCaretakers() {
        return API_USERS.getCaretakers((result, status, err) => {

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
        this.fetchCaretakers();
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.fetchCaretakers();
    }

    render() {  
        return (
            <div>
                <CardHeader>
                    <strong> Caretaker Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Caretaker </Button>
                        </Col>
                    </Row>
                    <Row>
                        
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <CaretakerTable reloadHandler={this.reload} tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Caretaker: </ModalHeader>
                    <ModalBody>
                        <CaretakerForm reloadHandler={this.reloadAfterAdd}/>
                    </ModalBody>
                </Modal>
            </div>
        )

    }
}

export default CaretakerContainer;
