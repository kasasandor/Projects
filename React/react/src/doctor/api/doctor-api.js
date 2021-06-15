import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    doctor: '/doctor',
    delete: '/doctor/delete/',
    update: '/doctor/update/'
};

function getDoctors(callback) {
    let request = new Request(HOST.backend_api + endpoint.doctor, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getDoctorById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor + params.id, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deleteDoctor(doctor, callback){
    let request = new Request(HOST.backend_api + endpoint.delete + doctor.id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(doctor)
    });

    console.log("URL: " + request.url);
    RestApiClient.performRequest(request, callback);
}

function updateDoctor(doctor, callback){
    let request = new Request(HOST.backend_api + endpoint.update + doctor.id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(doctor)
    });

    console.log("URL: " + request.url);
    console.log(doctor);
    RestApiClient.performRequest(request, callback);
}

function postDoctor(user, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor , {
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
    deleteDoctor,
    updateDoctor,
    getDoctors,
    getDoctorById,
    postDoctor
};