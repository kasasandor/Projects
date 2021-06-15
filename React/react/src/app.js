import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import PersonContainer from './person/person-container';
import DoctorContainer from './doctor/doctor-container';
import CaretakerContainer from './caretaker/caretaker-container';
import PatientContainer from './patient/patient-container';
import MedicationContainer from './medication/medication-container';
import LoginContainer from './person/login-container';
import PatientProfile from './patient/patient-profile';

class App extends React.Component {


    render() {

        return (
            <div className={styles.back}>
            <Router>
                <div>
                    <NavigationBar />
                    <Switch>
                    
                        <Route
                            exact
                            path='/'
                            render={() => <Home/>}
                        />
                        {
                            //sessionStorage.getItem('role') === 'Doctor' &&
                            <Route
                                exact
                                path='/person'
                                render={() => <PersonContainer/>}
                            />
                        }
                        {
                            sessionStorage.getItem('role') === 'Doctor' &&
                            <Route
                                exact
                                path='/caretaker'
                                render={() => <CaretakerContainer/>}
                            />
                        }
                        {
                            (sessionStorage.getItem('role') === 'Doctor' || sessionStorage.getItem('role') === 'Caretaker') &&
                            <Route
                                exact
                                path='/patient'
                                render={() => <PatientContainer/>}
                            />
                        }
                        {
                            sessionStorage.getItem('role') === 'Doctor' &&
                            <Route
                                exact
                                path='/doctor'
                                render={() => <DoctorContainer/>}
                            />
                        }
                        {
                            sessionStorage.getItem('role') === 'Doctor' &&
                            <Route
                                exact
                                path='/medication'
                                render={() => <MedicationContainer/>}
                            />
                        }
                        {
                            sessionStorage.getItem('role') === 'Patient' &&
                            <Route
                                exact
                                path='/profile'
                                render={() => <PatientProfile/>}
                            />
                        }
                        
                        <Route
                            exact
                            path='/login'
                            render={() => <LoginContainer/>}
                        />
                        
                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage/>}
                        />

                        <Route render={() =><ErrorPage/>} />
                    </Switch>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
