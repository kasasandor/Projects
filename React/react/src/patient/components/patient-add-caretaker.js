import React from 'react';
import Button from "react-bootstrap/Button";
import Table from "../../commons/tables/table";
import { ButtonGroup } from 'reactstrap';
import * as API_USERS from "../../caretaker/api/caretaker-api";

const filters = [
    {
        accessor: 'name',
    }
];

class AddCaretakerForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {
            tableData: this.props.tableData,
            selectedPatient: this.props.patientData,
            errorStatus: 0,
            error: null,
        };

    }

    sendData = (caretaker) =>{
        this.props.parentCallback(caretaker);
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    addPatient(caretaker, patient){
        return API_USERS.addPatient(caretaker, patient, (result, status, error) => {
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
                                    //console.log(this.state.assignedCaretaker);
                                    //this.deleteCaretaker(caretaker);
                                    console.log(caretaker);
                                    console.log(this.state.selectedPatient);
                                    this.addPatient(caretaker, this.state.selectedPatient);
                                }}>Add</Button>
                                </ButtonGroup>
                            );
                        }
                    }
                ]}
                search={filters}
                pageSize={5}
            />
            </div>
        ) ;
    }
}

export default AddCaretakerForm;