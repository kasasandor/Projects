import React from "react";
import Table from "../../commons/tables/table";
import { 
    ButtonGroup, 
    Button, 
    Modal,
    ModalBody,
    ModalHeader, } from 'reactstrap';
import * as API_USERS from "../api/doctor-api";
import DoctorUpdateForm from "./doctor-update-modal";

const filters = [
    {
        accessor: 'name',
    }
];

class DoctorTable extends React.Component {

    constructor(props) {
        super(props);
    
        this.reloadHandler = this.props.reloadHandler;
        this.toggleForm = this.toggleForm.bind(this);

        this.state = {
            tableData: this.props.tableData,
            selected: false,
            errorStatus: 0,
            selectedDoctor: [],
            error: null,
        };

        this.deleteDoctor = this.deleteDoctor.bind(this);
    }
    
    
    deleteDoctor(doctor){
        return API_USERS.deleteDoctor(doctor, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully deleted doctor with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    updateDoctor(doctor){
        return API_USERS.updateDoctor(doctor, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully updated doctor with id: " + result);
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
                        Header: 'Age',
                        accessor: 'age',
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
                                    console.log(data.id);
                                    let doctor = {
                                        id: data.id,
                                        name: data.name,
                                        email: data.email,
                                        age: data.age,
                                        address: data.address
                                    };
                                    this.deleteDoctor(doctor);
                                }}>Delete</Button>
                                <Button size="sm" color="primary" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    console.log(data.id);
                                    let doctor = {
                                        id: data.id,
                                        name: data.name,
                                        email: data.email,
                                        age: data.age,
                                        address: data.address
                                    };
                                    this.setState({selectedDoctor: doctor});
                                    this.toggleForm();
                                }}>Edit</Button>
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
            <ModalHeader toggle={this.toggleForm}> Modify Doctor: </ModalHeader>
                <ModalBody>
                    <DoctorUpdateForm reloadHandler={this.reloadHandler} doctorData={this.state.selectedDoctor}/>
            </ModalBody>
            </Modal>
            </div>
        )
    }
}

export default DoctorTable;