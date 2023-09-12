/*
  Created by Erik Brehl
*/


import React, { useState } from "react";
import ReactDOM from "react-dom";
import("./../../styles/contrast/contmenu.css");
import { Link } from "react-router-dom";
import Sidebar from '../sidebar/contsidebar'
import {useNavigate} from 'react-router-dom';
import { useLocation } from "react-router-dom";
import Navbar from '../navbar/contnavbar'
import { useEffect } from 'react';







export default function Menu() {
  /* let toolbar_toggle = document.querySelector(".toolbar_toggle");
    toolbar_toggle.addEventListener("click", function() {
    document.querySelector("body").classList.toggle("active");
    });*/

   const navigate = useNavigate();
    const location = useLocation();
    console.log(location.state.name);
    console.log(location.state.id);
    console.log(location.state.contrast);
    console.log(location.state.colorBlindness);
 
    

  function openBar() {
    document.querySelector("body").classList.toggle("active");
  }





  return (

    
    
    <body >
        <Navbar>
        </Navbar>



      
     <div className="cont_menu_container " >
     <div className="menu_control-section1">
       <div className="cont_blue-rectangle">
         <div className="text-overlay">
           <p className="ka_text">
             Hier ist ein Beispieltext, der über dem blauen Rechteck liegt.
           </p>
           <img
             src="Icons/loud-speaker.png"
             alt="Beschreibung des Bildes"
             className="image-rectangle"
           />
         </div>
       </div>
       <Link to="/main"><button className="cont_button1">
         <img
           src="Icons/conversation.png"
           alt="Beschreibung des Bildes"
           className="button-image"
         />
         <img
           src="Icons/next.png"
           alt="Beschreibung des Bildes"
           className="arrow-rectangle"
         />
         Room Chat
       </button>
       </Link>

       <div className="cont_blue-rectangle2">
         <div className="text-overlay">
           <p className="ka_text">
             Hier ist ein Beispieltext, der über dem blauen Rechteck liegt.
           </p>
           <img
             src="Icons/loud-speaker.png"
             alt="Beschreibung des Bildes"
             className="image-rectangle"
           />
         </div>
       </div>
       <button className="cont_button2">
         <img
           src="Icons/content.png"
           alt="Beschreibung des Bildes"
           className="button-image"
         />
         <img
           src="Icons/next.png"
           alt="Beschreibung des Bildes"
           className="arrow-rectangle"
         />
         Content
       </button>
     </div>
     <div className="control-section2">
       <div className="cont_blue-rectangle">
         <div className="text-overlay">
           <p className="ka_text">
             Hier ist ein Beispieltext, der über dem blauen Rechteck liegt.
           </p>
           <img
             src="Icons/loud-speaker.png"
             alt="Beschreibung des Bildes"
             className="image-rectangle"
           />
         </div>
       </div>

       <button className="cont_button1">
         <img
           src="Icons/book.png"
           alt="Beschreibung des Bildes"
           className="button-image"
         />
         <img
           src="Icons/next.png"
           alt="Beschreibung des Bildes"
           className="arrow-rectangle"
         />
         Lernmaterial
       </button>

       <div className="cont_blue-rectangle2">
         <div className="text-overlay">
           <p className="ka_text">
             Hier ist ein Beispieltext, der über dem blauen Rechteck liegt.
           </p>
           <img
             src="Icons/loud-speaker.png"
             alt="Beschreibung des Bildes"
             className="image-rectangle"
           />
         </div>
       </div>

       <button className="cont_button2">
         <img
           src="Icons/chatting.png"
           alt="Beschreibung des Bildes"
           className="button-image"
         />
         <img
           src="Icons/next.png"
           alt="Beschreibung des Bildes"
           className="arrow-rectangle"
         />
         Forum
       </button>
     </div>
   </div><Sidebar>
      </Sidebar>
      
   </body>
);
}
