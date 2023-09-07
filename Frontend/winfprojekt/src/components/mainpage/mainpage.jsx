/*
  Created by Erik Brehl
*/

import React from "react";
import ReactDOM from "react-dom";
import "./../../styles/main.css";
import { Link } from "react-router-dom";
import Navbar from '../navbar/navbar'
import Sidebar from '../sidebar/sidebar'

export default function Main() {
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
      <Sidebar>
      </Sidebar>
     

      <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossOrigin="anonymous"
      ></script>

      <div id="bereiche">

      <div id="bereich">
      <img src="Icons/char.png" className="charIcon"/>
        <h3>Max Mustermann</h3>

       
      </div>

      <div id="bereich1">
        <p>Charakter bearbeiten <img src="images/arrow.png" className="imgArrow"/> </p>
        
      </div>

      <div id="bereich2">
        <p><strong>Trifft dich in Räumen <br></br>oder Bearbeite deinen Charakter</strong></p>

        
      </div>

      
        <label htmlFor="suche" id="bereich3">Suche einen Ort aus</label>
       
    <div id="orte">

      <div className="ortBereich">
        
      <img src="images/FP.jpg" className="imgDiv" />
      <h2>Freizeitpark  <img src="images/arrow.png" className="imgArrow"/></h2>
     
      </div>
      
      <div className="ortBereich">
      <img src="images/KS1.jpg" className="imgDiv" />
        <h2>Schulklasse1 <img src="images/arrow.png" className="imgArrow"/></h2>
      </div>

      <div className="ortBereich">
      <img src="images/P.jpg" className="imgDiv" />
        <h2>Park <img src="images/arrow.png" className="imgArrow"/></h2>
      </div>

      <div className="ortBereich">
      <img src="images/KS2.jpg" className="imgDiv" />
        <h2>Schulklasse2 <img src="images/arrow.png" className="imgArrow"/></h2>
      </div>

      <div className="ortBereich">
      <img src="images/C.jpg" className="imgDiv" />
        <h2>Cafe <img src="images/arrow.png" className="imgArrow"/></h2>
      </div>

      <div className="ortBereich">
      <img src="images/BR.jpg" className="imgDiv"  />
        <h2>Blank-Room <img src="images/arrow.png" className="imgArrow"/></h2>
      </div>
    </div>
    </div>

    </body>
  );
}
