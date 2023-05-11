/*
  Created by Erik Brehl
*/


import React from 'react';
import { FaFontAwesomeFlag  } from 'react-icons/fa';
import { BsFillPersonFill } from "react-icons/bs";
import { BsQuestionLg } from "react-icons/bs";
import { FaCommentAlt  } from 'react-icons/fa';


import './../../styles/navbar.css'

export default function Navbar() {
  return (
    <nav className="navigation">
    <div
      className="navigation-menu">
      <ul>
     
        <li><a href="/lang"><FaFontAwesomeFlag  fill="#0a0a0a" size={30} /></a></li>
        <li><a href="/user"> <BsFillPersonFill fill="#0a0a0a"  size={30}/></a></li>
        <li><a href="/mess"><FaCommentAlt fill="0a0a0a"  size={30}  /></a></li>
        <li><a href="/faq"><BsQuestionLg fill="0a0a0a" size={30} /></a></li>
     

        <a href="/" className="site"> DivLab</a>
        <input placeholder="Suche" className="searchbar"/> 
      </ul>
        

        
    </div>

    
  </nav>
    )
}