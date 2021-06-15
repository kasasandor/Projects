import React from "react";
import Table from "../../commons/tables/table";
import { 
    ButtonGroup, 
    Button, 
    Modal,
    ModalBody,
    ModalHeader, } from 'reactstrap';
import * as API_USERS from "../api/patient-api";
import PatientUpdateForm from "./patient-update-form";
import AddCaretakerForm from "./patient-add-caretaker";
import * as CARETAKER_API from "../../caretaker/api/caretaker-api"

const filters = [
    {
        accessor: 'name',
    }
];

class PatientTable extends React.Component {

    constructor(props) {
        super(props);
    
        this.reloadHandler = this.props.reloadHandler;
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleCaretaker = this.toggleCaretaker.bind(this);
        this.setPatient = this.setPatient.bind(this);
        this.fetchCaretakers = this.fetchCaretakers.bind(this);

        this.state = {
            tableData: this.props.tableData,
            selected: false,
            addCaretaker: false,
            caretakers: [],
            assignedCaretaker: null,
            errorStatus: 0,
            selectedPatient: [],
            error: null,
        };

        this.deletePatient = this.deletePatient.bind(this);
    }
    
    
    deletePatient(patient){
        return API_USERS.deletePatient(patient, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully deleted patient with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }
    componentDidMount(){
        this.fetchCaretakers();
    }

    updatePatient(patient){
        return API_USERS.updatePatient(patient, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully updated patient with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    fetchCaretakers(){
        return CARETAKER_API.getCaretakers((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    caretakers: result
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

    setPatient(patient){
        this.setState({selectedPatient: patient});
    }

    toggleCaretaker(){
        this.setState({addCaretaker: !this.state.addCaretaker});
    }

    getCaretaker = (childData) => {
        this.setState({assignedCaretaker: childData});
        console.log(childData);
    }

    render() {
        return (
            <div>
            <Table
                data={this.state.tableData}
                columns={[
                    {
                        Header: 'Name',
                        accessor: 'name',
                    },
                    {
                        Header: 'Address',
                        accessor: 'address'
                    },
                    {
                        Header: 'Actions',
                        accessor: 'actions',
                        Cell: (row) => {
                            return (
                                <ButtonGroup>
                                <Button size="sm" color="danger" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    //console.log(data.id);
                                    let patient = {
                                        id: data.id,
                                        name: data.name,
                                        address: data.address
                                    };
                                    this.deletePatient(patient);
                                }}>Delete</Button>
                                <Button size="sm" color="primary" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    console.log(data);
                                    let patient = {
                                        id: data.id,
                                        name: data.name,
                                        address: data.address,
                                        gender: data.gender,
                                        dob: data.dob
                                    };
                                    this.setPatient(patient);
                                    this.toggleForm();
                                }}>Edit</Button>
                                </ButtonGroup>
                            );
                        }
                    },
                    {
                        Header: 'Caretaker',
                        accessor: 'caretaker',
                        Cell: (row) => {
                            return (
                                <ButtonGroup>
                                    <Button size="sm" color="success" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    //console.log(data);
                                    let patient = {
                                        id: data.id,
                                        name: data.name,
                                        address: data.address,
                                    };
                                    console.log(this.state.caretakers);
                                    this.setPatient(patient);
                                    this.toggleCaretaker();
                                }}>Add Caretaker</Button>
                                </ButtonGroup>
                            )
                        }
                    }
                ]}
                search={filters}
                pageSize={5}
            />

            <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
            <ModalHeader toggle={this.toggleForm}> Modify Patient: </ModalHeader>
                <ModalBody>
                    <PatientUpdateForm reloadHandler={this.reloadHandler} patientData={this.state.selectedPatient}/>
            </ModalBody>
            </Modal>

            <Modal isOpen={this.state.addCaretaker} toggle={this.toggleCaretaker}
                       className={this.props.className} size="lg">
            <ModalHeader toggle={this.toggleCaretaker}> Assign Caretaker: </ModalHeader>
                <ModalBody>
                    <AddCaretakerForm reloadHandler={this.reloadHandler} tableData={this.state.caretakers}
                                    patientData={this.state.selectedPatient}/>
            </ModalBody>
            </Modal>
            </div>
        )
    }
}

export default PatientTable;