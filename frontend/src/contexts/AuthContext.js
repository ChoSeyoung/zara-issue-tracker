import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from 'axios';

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [error, setError] = useState(null);

    const login = async (username, password) => {
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/login`, {
                username,
                password,
            }, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

            setIsAuthenticated(true);
            console.log(response.data.token)
            localStorage.setItem('token', response.data.token); // Store token in localStorage (optional)
        } catch (error) {
            console.error('Login error:', error);
            setError(error.response?.data?.message || 'Login failed');
        }
    };

    const logout = async () => {
        try {
            const token = localStorage.getItem('token');
            if (token) {
                await axios.post(`${process.env.API_SERVER_URL}/logout`, {}, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
            }
            setIsAuthenticated(false);
            localStorage.removeItem('token'); // Remove token from localStorage
        } catch (error) {
            console.error('Logout error:', error);
            setError('Logout failed');
        }
    };


    useEffect(() => {
        console.log('Auth State Changed:', isAuthenticated);
    }, [isAuthenticated]);

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, error }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
