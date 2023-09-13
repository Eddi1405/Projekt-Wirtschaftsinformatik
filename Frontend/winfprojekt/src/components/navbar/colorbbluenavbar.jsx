
import React from 'react';
import { FaFontAwesomeFlag  } from 'react-icons/fa';
import { BsFillPersonFill } from "react-icons/bs";
import { BsQuestionLg } from "react-icons/bs";
import { FaCommentAlt  } from 'react-icons/fa';
import { Link } from "react-router-dom";

import './../../styles/colorblind/bluenavmenu.css'

export default function Navbar() {



  
  return (
    <nav className='colorbblue_nav'>
    <ul>
      <li>
       
        <Link to="/menu"> <img src="Icons/menue.png" className='colorbblue_img' width="30px" height="30px" /> </Link>
          <p>Menu</p>
        
      </li>

      <li>
        <a href="#">
        <Link to="/faq"> <img src="Icons/help.png" className='colorbblue_img' width="30px" height="30px" /> </Link>
          <p>Hilfe</p>
        </a>
      </li>

      <li>
        <a href="#">
          <img src="Icons/chat.png" className='colorbblue_img'  width="30px" height="30px" />
          <p>Chat</p>
        </a>
      </li>

      <li>
        <a href="#">
        <Link to="/profil"> <img src="Icons/person.png" className='colorbblue_img' width="30px" height="30px" /> </Link>
          <p>Profil</p>
        </a>
      </li>
    </ul>

    <div className="searchbar">
      <img src="Icons/search.png" width="30px" height="30px" />
      <input type="text" placeholder="Suche" className='colorbblue_search'/>
    </div>

    <div className="colorbblue_navRobot">
      <img src="Icons/robot.png" width="80px" height="70px" />
      <p className="navRobotText">
        Du bist nun im Hauptmenü <br />
        <br />
        Du kannst rechts an der Leiste die Anzeigeeinstellungen nach
        Belieben ändern{" "}
      </p>
      <a href="#">
        <img
          src="Icons/loudness.png"
          width="70px"
          height="60px"
          className="iconLoud"
        />
      </a>
    </div>

    <img
      src="images/divlab.png"
      classname="menuLogo"
      width="180px"
      height="120px"
    />
  </nav>
    )
}