import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    patient: '/patient',
    delete: '/patient/delete/',
    update: '/patient/update/'
};

function getPatients(callback) {
    let request = new Request(HOST.backend_api + endpoint.patient, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.patient + params.id, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deletePatient(patient, callback){
    let request = new Request(HOST.backend_api + endpoint.delete + patient.id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function updatePatient(patient, callback){
    let request = new Request(HOST.backend_api + endpoint.update + patient.id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function postPatient(user, callback){
    let request = new Request(HOST.backend_api + endpoint.patient , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);
    
    RestApiClient.performRequest(request, callback);
}

export {
    deletePatient,
    updatePatient,
    getPatients,
    getPatientById,
    postPatient
};