import React from 'react';
import { FaSearch, FaBell, FaQuestionCircle, FaCog } from 'react-icons/fa';

function Header({ openPopup }) {
  return (
    <header className="fixed top-0 left-0 right-0 bg-white shadow-md z-50">
      <div className="mx-auto justify-between px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* 왼쪽 메뉴 아이콘 및 네비게이션 */}
          <div className="flex items-center space-x-4">
            <span className="text-black font-bold cursor-default">zara</span>
            <a href="/home" className="text-black">
              대시보드
            </a>
            <a href="/" className="text-black">
              프로젝트
            </a>
            <button
              className="bg-black text-white px-3 py-1 rounded"
              onClick={openPopup}
            >
              티켓생성
            </button>
          </div>

          {/* 오른쪽 아이콘 및 검색 */}
          <div className="flex items-center space-x-4">
            <div className="relative">
              <input
                type="text"
                placeholder="검색"
                className="pl-8 pr-4 py-1 rounded-full focus:outline-none bg-gray-100"
              />
              <FaSearch className="absolute left-2 top-2 text-black" />
            </div>
            <FaBell className="text-black" />
            <FaQuestionCircle className="text-black" />
            <FaCog className="text-black" />
            <img
              src="https://via.placeholder.com/30"
              alt="User"
              className="rounded-full w-8 h-8"
            />
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
