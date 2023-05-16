/*
  Created by Erik Brehl
*/


import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './styles/App.css'
import Navbar from './components/navbar/navbar'
import Sidebar from './components/sidebar/sidebar'


function App() {
  return (
    <div>
      <Sidebar/>
      <Navbar/>
      <div className="container">
        
      </div>
    </div>
  )
}

export default App