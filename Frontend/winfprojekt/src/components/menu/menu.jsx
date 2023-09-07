/*
  Created by Erik Brehl
*/

import React from "react";
import ReactDOM from "react-dom";
import "./../../styles/menu.css";
import { Link } from "react-router-dom";
import Navbar from '../navbar/navbar'
import Sidebar from '../sidebar/sidebar'
export default function Menu() {
  /* let toolbar_toggle = document.querySelector(".toolbar_toggle");
    toolbar_toggle.addEventListener("click", function() {
    document.querySelector("body").classList.toggle("active");
    });*/

  function openBar() {
    document.querySelector("body").classList.toggle("active");
  }

  return (
    <body>
      <Navbar>
      </Navbar>
      
     <div className="menu_container">
     <div className="menu_control-section1">
       <div className="blue-rectangle">
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
       <Link to="/main"><button className="button1">
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

       <div className="blue-rectangle2">
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
       <button className="button2">
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
       <div className="blue-rectangle">
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

       <button className="button1">
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

       <div className="blue-rectangle2">
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

       <button className="button2">
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
