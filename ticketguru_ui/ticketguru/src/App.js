import React from 'react';
import './App.css';
import { AuthProvider } from './utils/AuthContext';
import { BrowserRouter } from 'react-router-dom';
import Header from './components/Header';
import PrivateRouter from './components/PrivateRouter';

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Header />
        <PrivateRouter />
      </AuthProvider>
    </BrowserRouter>
  );
}
export default App;
