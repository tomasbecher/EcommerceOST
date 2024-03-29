import { useDispatch, useSelector } from 'react-redux';
import { ecommerceApi } from '../api';
import { clearErrorMessage, onChecking, onLogin, onLogout } from '../store';
import Swal from 'sweetalert2';
import {useState} from "react";


export const useAuthStore = () => {

    const { status, user, errorMessage } = useSelector( state => state.auth );
    const dispatch = useDispatch();
    const [error, setError] = useState(null);

    const startLogin = async({ username, password }) => {
        try {
            const { data } = await ecommerceApi.post('/login',{ username, password });
            localStorage.setItem('token', data.token );
            localStorage.setItem('refreshToken', data.refreshToken );
            localStorage.setItem('token-init-date', new Date().getTime() );
            localStorage.setItem('status', "authenticated" );
            localStorage.setItem('username', data.username);
            dispatch( onLogin({ username: data.username}) );
        } catch (error) {
            setError('Incorrect credentials');
            dispatch( onLogout('Incorrect credentials') );
            setTimeout(() => {
                dispatch( clearErrorMessage() );
            }, 10);
        }
    }

    const startRegister = async({ name, lastName, username, email, password, admin, receiveNewsletter}) => {
        try {
            const { data } = await ecommerceApi.post('/user/register',{ username, password, name, lastName, email, receiveNewsletter, admin});
            Swal.fire({
                title: "Registration Successful!",
                text: "Please proceed to login.",
                icon: "success",
                didClose: () => {
                    window.location.href ='/auth/login';
                }
              });
            
        } catch (error) {
            console.log(error.response.data.text);
            setError(error.response.data.text);
            dispatch( onLogout( error.response.data?.msg || '--' ) );
            setTimeout(() => {
                dispatch( clearErrorMessage() );
            }, 10);
        }
    }

    const startLogout = () => {
        localStorage.clear();
        dispatch(onLogout());
    }

    return {
        errorMessage,
        status, 
        user,
        error,
        startLogin,
        startLogout,
        startRegister,
    }

}