import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    medication: '/medication',
    delete: '/medication/delete/',
    update: '/medication/update/'
};

function getMedications(callback) {
    let request = new Request(HOST.backend_api + endpoint.medication, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMedicationById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.medication + params.id, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deleteMedication(medication, callback){
    let request = new Request(HOST.backend_api + endpoint.delete + medication.id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medication)
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function updateMedication(medication, callback){
    let request = new Request(HOST.backend_api + endpoint.update + medication.id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medication)
    });

    console.log("URL: " + request.url);
    console.log(medication);
    RestApiClient.performRequest(request, callback);
}

function postMedication(user, callback){
    let request = new Request(HOST.backend_api + endpoint.medication , {
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
    deleteMedication,
    updateMedication,
    getMedications,
    getMedicationById,
    postMedication
};