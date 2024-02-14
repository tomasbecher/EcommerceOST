import { useEffect, useState } from 'react';
import Swal from 'sweetalert2';
import { useAuthStore, useForm } from '../../hooks';
import ostLogo from '../../assets/ost.svg'

import '../../App.css'
import './LoginPage.css';

const loginFormFields = {
    loginUsername:    '',
    loginPassword: '',
}

const registerFormFields = {
    registerName:      '',
    registerLastName:  '',
    registerUsername:  '',
    registerEmail:     '',
    registerPassword:  '',
    registerPassword2: '',
}

export const LoginPage = () => {

    const { startLogin, errorMessage, startRegister, error } = useAuthStore();

    const { loginUsername, loginPassword, onInputChange:onLoginInputChange } = useForm( loginFormFields );
    const { registerEmail, registerName, registerLastName, registerUsername, registerPassword, registerPassword2, onInputChange:onRegisterInputChange } = useForm( registerFormFields );
    const [receiveNewsletter, setReceiveNewsletter] = useState(true);
    const [showLogin, setShowLogin] = useState(true);

    const loginSubmit = ( event ) => {
        event.preventDefault();
        startLogin({ username: loginUsername, 
            password: loginPassword 
        });
    }

    const registerSubmit = ( event ) => {
        event.preventDefault();
        if ( registerPassword !== registerPassword2 ) {
            Swal.fire("Registration error", "Passwords do not match", "error");
            return;
        }
        startRegister({ name: registerName, 
            lastName: registerLastName,
            username: registerUsername,
            email: registerEmail, 
            password: registerPassword,
            admin: false,
            receiveNewsletter: receiveNewsletter
        });
    }

    useEffect(() => {
        if ( error !== null) {
          Swal.fire('Authentication error', error, 'error');
        }    
      }, [errorMessage])

      const onCheckboxChange = () => {
        if(receiveNewsletter == true)
            setReceiveNewsletter(false);
        else
            setReceiveNewsletter(true);
      };

    const toggleForm = () => {
        setShowLogin(!showLogin);
    };

    return (
        <>
        <div className="container">
            <div className="logo-container">
                <a href="https://www.oneseventech.com/" target="_blank">
                    <img src={ostLogo} className="logo" alt="Vite logo" />
                </a>
            </div>
            <div className="login-container">
                <div className="row justify-content">
                    {showLogin ? (
                        <div className="col-md-6 login-form-1">
                            <h3>Login</h3>
                            <form onSubmit={ loginSubmit }>
                                <div className="form-group mb-2">
                                    <input 
                                        type="text"
                                        className="form-control"
                                        placeholder="Username"
                                        name="loginUsername"
                                        value={ loginUsername }
                                        onChange={ onLoginInputChange }
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <input
                                        type="password"
                                        className="form-control"
                                        placeholder="Password"
                                        name="loginPassword"
                                        value={ loginPassword }
                                        onChange={ onLoginInputChange }
                                    />
                                </div>
                                <div className="d-grid gap-2">
                                    <input 
                                        type="submit"
                                        className="btnSubmit"
                                        value="Login" 
                                    />
                                </div>
                                <div className="d-grid gap-2">
                                    <input 
                                        type="button"
                                        className="btnChangeCurrentForm"
                                        value="Go to Register" 
                                        onClick={toggleForm}
                                    />
                                </div>
                            </form>
                        </div>
                    ) : (
                        <div className="col-md-6 login-form-2">
                            <h3>Register</h3>
                            <form onSubmit={ registerSubmit }>
                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Name"
                                        name="registerName"
                                        value={ registerName }
                                        onChange={ onRegisterInputChange }
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Last Name"
                                        name="registerLastName"
                                        value={ registerLastName }
                                        onChange={ onRegisterInputChange }
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Username"
                                        name="registerUsername"
                                        value={ registerUsername }
                                        onChange={ onRegisterInputChange }
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <input
                                        type="email"
                                        className="form-control"
                                        placeholder="Email"
                                        name="registerEmail"
                                        value={ registerEmail }
                                        onChange={ onRegisterInputChange }
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <input
                                        type="password"
                                        className="form-control"
                                        placeholder="Password" 
                                        name="registerPassword"
                                        value={ registerPassword }
                                        onChange={ onRegisterInputChange }
                                    />
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="password"
                                        className="form-control"
                                        placeholder="Repeat password" 
                                        name="registerPassword2"
                                        value={ registerPassword2 }
                                        onChange={ onRegisterInputChange }
                                    />
                                </div>
                                <div className="form-check mb-2">
                                    <input
                                        type="checkbox"
                                        className="form-check-input"
                                        id="newsletterCheckbox"
                                        name="receiveNewsletter"
                                        checked={receiveNewsletter}
                                        onChange={onCheckboxChange}
                                    />
                                    <label className="form-check-label newsletter-question" htmlFor="newsletterCheckbox">
                                        I would like to subscribe to the newsletter
                                    </label>
                                </div>

                                <div className="d-grid gap-2">
                                    <input 
                                        type="submit" 
                                        className="btnSubmit" 
                                        value="Register" />
                                </div>
                                <div className="d-grid gap-2">
                                    <input 
                                        type="button"
                                        className="btnChangeCurrentForm"
                                        value="Go to Login" 
                                        onClick={toggleForm}
                                    />
                                </div>
                            </form>
                        </div>
                    )}
                </div>
            </div>
            </div>
        </>
    )
}


