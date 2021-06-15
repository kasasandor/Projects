import React from 'react';
import Table from "../../commons/tables/table";

const filters = [
    {
        accessor: 'name',
    }
];

class ViewPatientsForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {
            tableData: this.props.tableData,
            errorStatus: 0,
            error: null,
        };

    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
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
                ]}
                search={filters}
                pageSize={5}
            />
            </div>
        ) ;
    }
}

export default ViewPatientsForm;