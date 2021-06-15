import React from 'react';
import {
    Col,
    Row
} from 'reactstrap';
import * as API_USERS from "./api/patient-api"

class PatientProfile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            patientData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPatient();
    }


    fetchPatient() {
        return API_USERS.getPatients((result, status, err) => {

            if (result !== null && status === 200) {
                console.log(result[0]);
                this.setState({
                    patientData: result[0],
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

    formatDate(date){
        let [month, day, year]    = new Date(date).toLocaleDateString("en-US").split("/")
        let newDate = year + "-" + month + "-" + day;
        return newDate;
    }

    render() {  
        return (
            <div>
                <Row>
                    <Col sm={{size: '4', offset: 2}}>
                        Name: {this.state.patientData['name']}
                    </Col>
                </Row>
                <Row>
                    <Col sm={{size: '4', offset: 2}}>
                        Date of Birth: {this.formatDate(this.state.patientData['dob'])}
                    </Col>
                </Row>
                <Row>
                    <Col sm={{size: '4', offset: 2}}>
                        Gender: {this.state.patientData['address']}
                    </Col>
                </Row>
            </div>
        )

    }
}

export default PatientProfile;