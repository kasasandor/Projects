import React from "react";
import Table from "../../commons/tables/table";
import { 
    ButtonGroup, 
    Button, 
    Modal,
    ModalBody,
    ModalHeader, } from 'reactstrap';
import * as API_USERS from "../api/medication-api";
import MedicationUpdateForm from "./medication-update-form";

const filters = [
    {
        accessor: 'name',
    }
];

class MedicationTable extends React.Component {

    constructor(props) {
        super(props);
    
        this.reloadHandler = this.props.reloadHandler;
        this.toggleForm = this.toggleForm.bind(this);

        this.state = {
            tableData: this.props.tableData,
            selected: false,
            errorStatus: 0,
            selectedMedication: null,
            error: null,
        };

        //this.deleteMedication = this.deleteMedication.bind(this);
    }
    
    
    deleteMedication(medication){
        return API_USERS.deleteMedication(medication, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully deleted medication with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    updateMedication(medication){
        return API_USERS.updateMedication(medication, (result, status, error) => {
            if(result !== null && (status === 200 || status === 201)){
                console.log("Successfully updated medication with id: " + result);
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
                        Header: 'Dosage',
                        accessor: 'dosage',
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
                                    let medication = {
                                        id: data.id,
                                        name: data.name,
                                        dosage: data.dosage
                                    };
                                    this.deleteMedication(medication);
                                }}>Delete</Button>
                                <Button size="sm" color="primary" onClick={() => {
                                    let data = this.state.tableData[row.index];
                                    console.log(data.id);
                                    let medication = {
                                        id: data.id,
                                        name: data.name,
                                        sideEffects: data.sideEffects,
                                        dosage: data.dosage
                                    };
                                    this.setState({selectedMedication: medication});
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
            <ModalHeader toggle={this.toggleForm}> Modify Medication: </ModalHeader>
                <ModalBody>
                    <MedicationUpdateForm reloadHandler={this.reloadHandler} medicationData={this.state.selectedMedication}/>
            </ModalBody>
            </Modal>
            </div>
        )
    }
}

export default MedicationTable;