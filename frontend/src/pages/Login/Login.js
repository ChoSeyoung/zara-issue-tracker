import React, {useEffect, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../contexts/AuthContext';

function Login() {
  const { isAuthenticated, login, error } = useAuth();
  const navigate = useNavigate();
  const [credentials, setCredentials] = useState({ username: '', password: '' });

  useEffect(() => {
    // 이미 인증된 경우 /home으로 리다이렉트
    if (isAuthenticated) {
      navigate('/home');
    }
  }, [isAuthenticated, navigate]);

  const handleLogin = (event) => {
    event.preventDefault(); // 폼 제출 방지
    login(credentials.username, credentials.password);
  };

  const handleChange = (event) => {
    setCredentials({
      ...credentials,
      [event.target.name]: event.target.value,
    });
  };

  return (
      <div className="min-h-full h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <div className="max-w-md w-full space-y-8">
          <h2 className="mt-6 text-center text-3xl font-extrabold text-rose-600">
            Welcome
          </h2>

          <form className="my-5" onSubmit={handleLogin}>
            <div className="my-3">
              <label htmlFor="username" className="sr-only">
                Username
              </label>
              <input
                  onChange={handleChange}
                  value={credentials.username}
                  className="rounded-md appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-rose-500 focus:border-rose-500 focus:z-10 sm:text-sm"
                  id="username"
                  name="username"
                  type="text"
                  required
                  placeholder="Username"
              />
            </div>
            <div className="my-3">
              <label htmlFor="password" className="sr-only">
                Password
              </label>
              <input
                  onChange={handleChange}
                  value={credentials.password}
                  className="rounded-md appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-rose-500 focus:border-rose-500 focus:z-10 sm:text-sm"
                  id="password"
                  name="password"
                  type="password"
                  required
                  placeholder="Password"
              />
            </div>
            <div className="my-3">
              <button
                  type="submit"
                  className="group relative w-full flex justify-center py-2 border border-transparent text-sm font-medium rounded-md text-white bg-rose-600 hover:bg-rose-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-rose-500"
              >
                Login
              </button>
            </div>
          </form>

          {error && (
              <div className="rounded-md bg-red-100 p-4 mb-4">
                <p className="text-sm text-red-700">{error}</p>
              </div>
          )}

          <p className="mt-5 text-center text-sm text-gray-600">
            Don't have an account yet?{' '}
            <Link
                className="font-medium text-rose-600 hover:text-rose-500"
                to="/home"
            >
              Sign Up
            </Link>
          </p>
        </div>
      </div>
  );
}

export default Login;
