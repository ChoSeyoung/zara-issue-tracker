import React from 'react';
import { useNavigate } from 'react-router-dom';
import {useAuth} from "../contexts/AuthContext";

function Login() {
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleLogin = () => {
        login();
        navigate('/'); // 로그인 후 홈 페이지로 이동
    };

    return (
        <div>
            <h1>Login Page</h1>
            <button onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;
