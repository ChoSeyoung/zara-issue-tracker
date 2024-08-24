import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import TurndownService from 'turndown';
import 'react-quill/dist/quill.snow.css';
import axios from 'axios';

function IssuePopup({ closePopup }) {
  const [project, setProject] = useState('메인');
  const [issueType, setIssueType] = useState('작업');
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // TurndownService 인스턴스 생성
    const turndownService = new TurndownService();
    // HTML을 마크다운으로 변환
    const markdownDescription = turndownService.turndown(description);

    // 변환된 마크다운 데이터를 백엔드로 전송
    const issueData = {
      project,
      issueType,
      title,
      description: markdownDescription,
    };

    console.log('전송할 데이터:', issueData);

    try {
      const token = localStorage.getItem('token');
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/logout`,
        issueData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      console.log('Response:', response.data);
    } catch (error) {
      console.error('Error:', error);
    }

    // 팝업 닫기 (필요에 따라)
    closePopup();
  };

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white rounded-lg shadow-lg max-w-xl w-full p-6">
        <h2 className="text-2xl font-bold mb-4">이슈 만들기</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">
              프로젝트
            </label>
            <select
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={project}
              onChange={(e) => setProject(e.target.value)}
            >
              <option>메인</option>
              {/* 추가 프로젝트 옵션이 있으면 여기에 추가 */}
            </select>
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">
              이슈 유형
            </label>
            <select
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={issueType}
              onChange={(e) => setIssueType(e.target.value)}
            >
              <option>작업</option>
              <option>버그</option>
              {/* 추가 이슈 유형이 있으면 여기에 추가 */}
            </select>
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">
              제목
            </label>
            <input
              type="text"
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
          </div>
          <div className="mb-14">
            <label className="block text-sm font-medium text-gray-700">
              설명
            </label>
            <ReactQuill
              theme="snow"
              value={description}
              onChange={setDescription}
              className="mt-1 h-48"
            />
          </div>
          <div className="flex justify-end">
            <button
              type="button"
              className="bg-white text-black px-4 py-2 rounded mr-2"
              onClick={closePopup}
            >
              취소
            </button>
            <button
              type="submit"
              className="bg-black text-white px-4 py-2 rounded"
            >
              만들기
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default IssuePopup;
