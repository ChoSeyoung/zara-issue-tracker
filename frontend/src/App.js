import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./pages/Login/Login";
import {AuthProvider} from "./contexts/AuthContext";
import Home from "./pages/Home/Home";
import PrivateRoute from "./components/PrivateRoute";

function App() {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/home" element={<PrivateRoute element={Home} />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;
