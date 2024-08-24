import React from 'react';

const NotFound = () => {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen">
            <h1 className="text-9xl font-bold text-gray-800">OOPS!</h1>
            <p className="text-xl mt-4">404 - The Page Can't Be Found</p>
            <button
                onClick={() => window.location.href = '/'}
                className="mt-6 px-8 py-3 bg-orange-600 text-white font-semibold rounded"
            >
                GO TO HOMEPAGE
            </button>
        </div>
    );
};

export default NotFound;
