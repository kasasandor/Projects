import React from 'react'
import logo from './commons/images/icon.png';
import { Redirect } from 'react-router';
import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Button,
    Nav,
    Navbar,
    NavbarBrand,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};
function logout(){
    sessionStorage.removeItem('userID');
    sessionStorage.removeItem('role');
    window.location.reload();
}
const NavigationBar = () => (
    
    <div>
        <Navbar color="dark" light expand="md">
            <NavbarBrand href="/">
                <img src={logo} width={"50"}
                     height={"35"} />
            </NavbarBrand>
            <Nav className="mr-auto" navbar>

                <UncontrolledDropdown nav inNavbar>
                    <DropdownToggle style={textStyle} nav caret>
                       Menu
                    </DropdownToggle>
                    <DropdownMenu right >
                        {
                            //sessionStorage.getItem('role') === 'Doctor' &&
                            <DropdownItem>
                                <NavLink href="/person">Persons</NavLink>
                            </DropdownItem>
                        }

                        {
                            sessionStorage.getItem('role') === 'Doctor' &&
                            <DropdownItem>
                                <NavLink href="/doctor">Doctors</NavLink>
                            </DropdownItem>
                        }
                        {
                            sessionStorage.getItem('role') === 'Doctor' &&
                            <DropdownItem>
                                <NavLink href="/caretaker">Caretakers</NavLink>
                            </DropdownItem>
                        }
                        {
                            (sessionStorage.getItem('role') === 'Doctor' || sessionStorage.getItem('role') === 'Caretaker') &&
                            <DropdownItem>
                                <NavLink href="/patient">Patients</NavLink>
                            </DropdownItem>
                        }
                        {
                            sessionStorage.getItem('role') === 'Doctor' &&
                            <DropdownItem>
                                <NavLink href="/medication">Medications</NavLink>
                            </DropdownItem>
                        }
                        {
                            sessionStorage.getItem('role') === 'Patient' &&
                            <DropdownItem>
                                <NavLink href="/profile">Profile</NavLink>
                            </DropdownItem>
                        }
                    </DropdownMenu>
                </UncontrolledDropdown>
           
            </Nav>

            {
                sessionStorage.getItem('userID') == null &&
                <NavLink href="/login">Login</NavLink>
            }

            {
                sessionStorage.getItem('userID') != null &&
                <NavLink>{sessionStorage.getItem('role')}</NavLink>
            }

            {
                sessionStorage.getItem('userID') != null &&
                <Button onClick={() => logout()}>Logout</Button>
            }
            
        </Navbar>
    </div>
);

export default NavigationBar
