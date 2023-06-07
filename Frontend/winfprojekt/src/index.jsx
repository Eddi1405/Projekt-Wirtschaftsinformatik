import React from 'react';
import { BrowserRouter,
    Routes,Route } from 'react-router-dom';

import Setup from './components/setup/setup'
import Login from './components/login/login'
import Main from './components/mainpage/mainpage'

const Index = () => {
  return (
    
    <Routes> {/* The Switch decides which component to show based on the current URL.*/}
      <Route exact path='/' element={<Login />}></Route>
      <Route exact path='/setup' element={<Setup />}></Route>
      <Route exact path='/main' element={<Main />}></Route>
    </Routes>
   



  );
}

export default Index;