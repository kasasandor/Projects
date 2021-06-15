import React from "react";
import Table from "../../commons/tables/table";
import { ButtonGroup, Button } from 'reactstrap';
import * as API_USERS from "../api/person-api";

const filters = [
    {
        accessor: 'name',
    }
];

class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        
        this.state = {
            tableData: this.props.tableData
        };
    }

    deletePerson(doctor){
        return API_USERS.deletePerson(doctor, (result, status, error) => {
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

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={[
                    {
                        Header: 'Username',
                        accessor: 'username',
                    },
                    {
                        Header: 'Role',
                        accessor: 'role',
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
                                        //email: data.email,
                                        age: data.age,
                                        address: data.address
                                    };
                                    this.deletePerson(doctor);
                                    //data.splice(row.index, 1);
                                    //this.setState({data});
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
                                    this.updateDoctor(doctor);
                                    //data.splice(row.index, 1);
                                    //this.setState({data});
                                }}>Edit</Button>
                                </ButtonGroup>
                            );
                        }
                    }
                ]}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default PersonTable;