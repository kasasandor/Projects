import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    caretaker: '/caretaker',
    delete: '/caretaker/delete/',
    update: '/caretaker/update/',
    patients: '/patients',
};

function getCaretakers(callback) {
    let request = new Request(HOST.backend_api + endpoint.caretaker, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getCaretakerById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.caretaker + params.id, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deleteCaretaker(caretaker, callback){
    let request = new Request(HOST.backend_api + endpoint.delete + caretaker.id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caretaker)
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function updateCaretaker(caretaker, callback){
    let request = new Request(HOST.backend_api + endpoint.update + caretaker.id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caretaker)
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function addPatient(caretaker, patient, callback){
    let request = new Request(HOST.backend_api + endpoint.caretaker + "/patient/" + caretaker.id, {
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

function getPatients(caretaker, callback){
    let request = new Request(HOST.backend_api + endpoint.caretaker + "/" + caretaker.id + endpoint.patients, {
        method: 'GET',
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function postCaretaker(user, callback){
    let request = new Request(HOST.backend_api + endpoint.caretaker , {
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
    deleteCaretaker,
    updateCaretaker,
    getCaretakers,
    getPatients,
    addPatient,
    getCaretakerById,
    postCaretaker
};