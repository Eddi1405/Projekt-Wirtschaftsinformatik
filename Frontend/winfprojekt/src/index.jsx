import React from 'react';
import { BrowserRouter,
    Routes,Route } from 'react-router-dom';

import Setup from './components/setup/setup'
import Login from './components/login/login'

const Index = () => {
  return (
    
    <Routes> {/* The Switch decides which component to show based on the current URL.*/}
      <Route exact path='/' element={<Login />}></Route>
      <Route exact path='/setup' element={<Setup />}></Route>
    </Routes>
   



  );
}

export default Index;