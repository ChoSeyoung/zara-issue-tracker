import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../contexts/AuthContext';

function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const handleLogin = () => {
    console.log('login');
    login();
    navigate('/home');
  };

  return (
    <div className="min-h-full h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8">
        <h2 className="mt-6 text-center text-3xl font-extrabold text-rose-600">
          Welcome
        </h2>
        <form className="my-5">
          <label htmlFor="email-address" className="sr-only">
            email address
          </label>
          <input
            onChange={() => console.log('change')}
            className="rounded-md appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-rose-500 focus:border-rose-500 focus:z-10 sm:text-sm"
            id="email-address"
            name="email"
            type="email"
            required
            placeholder={'email'}
          />
          <label htmlFor="password" className="sr-only">
            password
          </label>
          <input
            onChange={() => console.log('change')}
            className="rounded-md appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-rose-500 focus:border-rose-500 focus:z-10 sm:text-sm"
            id="password"
            name="password"
            type="password"
            required
            placeholder={'password'}
          />
          <button
            type="button"
            className="group relative w-full flex justify-center py-2 border border-transparent text-sm font-medium rounded-md text-white bg-rose-600 hover:bg-rose-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-rose-500"
            onSubmit={handleLogin}
          >
            Login
          </button>
        </form>
        <p className="mt-5 text-center text-sm text-gray-600">
          Don't have an account yet?{' '}
          <Link className="font-medium text-rose-600 hover:text-rose-500" to="/home">
            Sign Up
          </Link>
        </p>
      </div>
    </div>
  );
}

export default Login;
