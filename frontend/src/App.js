import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Routes, useLocation} from "react-router-dom";
import Login from "./pages/Login/Login";
import {AuthProvider} from "./contexts/AuthContext";
import Home from "./pages/Home/Home";
import PrivateRoute from "./components/PrivateRoute";
import Register from "./pages/Register/Register";
import Header from "./components/Header";
import IssuePopup from "./components/IssuePopup";
import IssueDetail from "./pages/Issue/IssueDetail";
import IssueList from "./pages/Issue/IssueList";
import NotFound from "./pages/Global/NotFound";

function App() {
    return (
        <AuthProvider>
            <Router>
                <AppContent />
            </Router>
        </AuthProvider>
    );
}

function AppContent() {
    const location = useLocation();

    // 로그인 및 회원가입 페이지에서는 헤더를 숨깁니다.
    const hideHeader = location.pathname === '/' || location.pathname === '/register';

    const [isPopupOpen, setIsPopupOpen] = useState(false);

    const openPopup = () => setIsPopupOpen(true);
    const closePopup = () => setIsPopupOpen(false);

    return (
        <>
            {!hideHeader && <Header openPopup={openPopup} />}
            <Routes>
                {/* 로그인 */}
                <Route path="/" element={<Login />} />
                {/* 회원가입 */}
                <Route path="/register" element={<Register />} />
                {/* 홈 페이지 */}
                <Route path="/home" element={<PrivateRoute element={Home} />} />
                {/* 이슈 목록 페이지 */}
                <Route exact path="/issues" component={IssueList} />
                {/* 특정 이슈 조회 페이지 */}
                <Route path="/issues/:id" component={IssueDetail} />
                {/* 404 Not Found */}
                <Route path="*" element={<NotFound />} />
            </Routes>
            {isPopupOpen && <IssuePopup closePopup={closePopup} />}

        </>
    );
}


export default App;
