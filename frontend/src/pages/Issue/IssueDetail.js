import React, { useEffect, useState } from 'react';
import axios from 'axios';

function IssueDetail({ issueId }) {
  const [issue, setIssue] = useState(null);

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/api/issues/${issueId}`)
      .then((response) => {
        setIssue(response.data);
      })
      .catch((error) => {
        console.error('There was an error fetching the issue data!', error);
      });
  }, [issueId]);

  if (!issue) {
    return <div>Loading...</div>;
  }

  return (
    <div className="flex">
      {/* 이슈 정보 섹션 */}
      <div className="w-2/3 p-6 bg-white rounded-lg shadow-md">
        <div className="mb-4">
          <h1 className="text-2xl font-bold">{issue.title}</h1>
          <div className="text-gray-500 mb-2">설명</div>
          <p>{issue.description}</p>
        </div>

        {/* 활동 로그 */}
        <div className="mt-6">
          <h2 className="text-xl font-semibold mb-2">활동</h2>
          <div className="flex space-x-4 mb-4">
            <button className="px-4 py-2 bg-gray-200 rounded">댓글</button>
            <button className="px-4 py-2 bg-gray-200 rounded">기록</button>
            <button className="px-4 py-2 bg-gray-200 rounded">
              Zendesk Support
            </button>
            <button className="px-4 py-2 bg-gray-200 rounded">
              Checklist history
            </button>
          </div>

          {/* Zendesk Support for Jira 예시 */}
          <div className="p-4 bg-gray-100 rounded-lg shadow-sm">
            <div className="text-sm text-gray-600 mb-2">
              Zendesk Support for Jira
            </div>
            <div className="text-gray-800">2020년 7월 21일 오후 5:26</div>
            <div className="mt-2">
              This notification was sent from Zendesk Support ticket #349135 to
              all linked JIRA issues by Alarmy Support. – test
            </div>
          </div>
        </div>
      </div>

      {/* 사이드바 */}
      <div className="w-1/3 p-6 bg-gray-50 rounded-lg shadow-md ml-6">
        <div className="mb-4">
          <label className="text-gray-500">상태</label>
          <div className="font-semibold">{issue.status}</div>
        </div>

        <div className="mb-4">
          <label className="text-gray-500">담당자</label>
          <div className="font-semibold">{issue.assignee || '할당 해제됨'}</div>
        </div>

        <div className="mb-4">
          <label className="text-gray-500">리포터</label>
          <div className="font-semibold">{issue.reporter || '없음'}</div>
        </div>

        <div className="mb-4">
          <label className="text-gray-500">Zendesk Ticket IDs</label>
          <div className="font-semibold">{issue.zendeskTicketIds}</div>
        </div>

        <div className="mb-4">
          <label className="text-gray-500">Zendesk Ticket Count</label>
          <div className="font-semibold">{issue.zendeskTicketCount}</div>
        </div>
      </div>
    </div>
  );
}

export default IssueDetail;
