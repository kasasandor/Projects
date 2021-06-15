import React from "react";
import Table from "../../commons/tables/table";
import { 
    ButtonGroup, 
    Button, 
    Modal,
    ModalBody,
    ModalHeader, } from 'reactstrap';
import * as API_USERS from "../api/caretaker-api";
import CaretakerUpdateForm from "./caretaker-update-form";
import ViewPatientsForm from "./caretaker-patients";

const filters = [
    {
        accessor: 'name',
    }
];

class CaretakerTable extends React.Component {

    constructor(props) {
        super(props);
    
        this.reloadHandler = this.props.reloadHandler;
        this.toggleForm = this.toggleForm.bind(this);
        this.togglePatients = this.togglePatients.bind(this);

        this.state = {
            tableData: this.props.tableData,
            selected: false,
            showPatients: false,
            patients: [],
            isLoaded: false,
            errorStatus: 0,
            selectedCaretaker: null,
            error: null,
        };

        //this.deleteCaretaker = this.deleteCaretaker.bind(this);
    }
    
    togglePatients(){
        this.setState({showPatients: !this.state.showPatients});
    }

    deleteCaretaker(caretaker){
        return API_USERS.deleteCaretaker(caretaker, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully deleted caretaker with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    updateCaretaker(caretaker){
        return API_USERS.updateCaretaker(caretaker, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully updated caretaker with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }
    
    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    handleFetch(caretaker){
        this.fetchPatients(caretaker);
    }

    fetchPatients(caretaker){
        return API_USERS.getPatients(caretaker, (result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    patients: result,
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
                                    let caretaker = {
                                        id: data.id,
                                        name: data.name,
                                        address: data.address
                                    };
                                    this.deleteCaretaker(caretaker);
                                }}>Delete</Button>
                                <Button size="sm" color="primary" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    console.log(data);
                                    let caretaker = {
                                        id: data.id,
                                        name: data.name,
                                        address: data.address,
                                        gender: data.gender,
                                        dob: data.dob
                                    };
                                    this.setState({selectedCaretaker: caretaker});
                                    this.toggleForm();
                                }}>Edit</Button>
                                </ButtonGroup>
                            );
                        }
                    },
                    {
                        Header: 'Patients',
                        accessor: 'patients',
                        Cell: (row) => {
                            return (
                                <ButtonGroup>
                                <Button size="sm" color="warning" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    let caretaker = {
                                        id: data.id,
                                        name: data.name,
                                        address: data.address
                                    };
                                    //this.setState({selectedCaretaker: caretaker})
                                    this.handleFetch(caretaker);
                                    //console.log(this.state.patients);
                                    this.togglePatients();
                                }}>View Patients</Button>
                                </ButtonGroup>
                            );
                        }
                    }
                ]}
                search={filters}
                pageSize={5}
            />

            <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
            <ModalHeader toggle={this.toggleForm}> Modify Caretaker: </ModalHeader>
                <ModalBody>
                    <CaretakerUpdateForm reloadHandler={this.reloadHandler} caretakerData={this.state.selectedCaretaker}/>
            </ModalBody>
            </Modal>

            <Modal isOpen={this.state.showPatients} toggle={this.togglePatients}
                       className={this.props.className} size="lg">
            <ModalHeader toggle={this.togglePatients}> Patients: </ModalHeader>
                <ModalBody>
                    <ViewPatientsForm reloadHandler={this.reloadHandler} tableData={this.state.patients}/>
            </ModalBody>
            </Modal>
            </div>
        )
    }
}

export default CaretakerTable;