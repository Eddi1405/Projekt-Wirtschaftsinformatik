import React , { useState } from 'react';
import { BrowserRouter,
    Routes,Route } from 'react-router-dom';

import Setup from './components/setup/setup'
import Login from './components/login/login'
import Main from './components/mainpage/mainpage'
import Profil from './components/profil/profil'
import Register from './components/register/register'
import Menu from './components/menu/menu'
import MenuColorbblue from './components/menu/colorbbluemenu'
import ContMenu from './components/menu/contmenu'
import Faq from './components/faq/faq'
import Thread_list from './components/thread_list/thread_list';
import Thread from './components/thread/thread';


const Index = () => {


  return (
    
    <Routes> {/* The Switch decides which component to show based on the current URL.*/}
      <Route exact path='/' element={<Login />}></Route>
      <Route exact path='/setup' element={<Setup />}></Route>
      <Route exact path='/main' element={<Main />}></Route>
      <Route exact path='/profil' element={<Profil />}></Route>
      <Route exact path='/register' element={<Register />}></Route>
      <Route exact path='/menu' element={<Menu />}></Route>
      <Route exact path='/contmenu' element={<ContMenu />}></Route>
      <Route exact path='/colorbbluemenu' element={<MenuColorbblue />}></Route>
      <Route exact path='/faq' element={<Faq />}></Route>
      <Route exact path='/threadlist' element={<Thread_list />}></Route>
      <Route exact path='/thread' element={<Thread />}></Route>
    </Routes>
   



  );
}

export default Index;