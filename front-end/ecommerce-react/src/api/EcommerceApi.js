import axios from 'axios';
import { getEnvVariables } from '../helpers';

const { API_URL } = getEnvVariables()

const ecommerceApi = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": 'application/json'
      }
});

ecommerceApi.interceptors.request.use( config => {
    const token = localStorage.getItem('token');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`; 
  }
  return config;
});

export default ecommerceApi;