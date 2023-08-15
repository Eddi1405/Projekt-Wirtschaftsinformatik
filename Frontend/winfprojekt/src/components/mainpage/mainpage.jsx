/*
  Created by Erik Brehl
*/

import React from "react";
import ReactDOM from "react-dom";
import "./../../styles/main.css";
import { Link } from "react-router-dom";

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
      <nav>
        <ul>
          <li>
            <a href="#">
              <img src="Icons/menue.png" width="30px" height="30px" />
              <p>Menu</p>
            </a>
          </li>

          <li>
            <a href="#">
              <img src="Icons/help.png" width="30px" height="30px" />
              <p>Hilfe</p>
            </a>
          </li>

          <li>
            <a href="#">
              <img src="Icons/chat.png" width="30px" height="30px" />
              <p>Chat</p>
            </a>
          </li>

          <li>
            <a href="#">
            <Link to="/profil"> <img src="Icons/person.png" width="30px" height="30px" /> </Link>
              <p>Profil</p>
            </a>
          </li>
        </ul>

        <div className="searchbar">
          <img src="Icons/search.png" width="30px" height="30px" />
          <input type="text" placeholder="Suche" />
        </div>

        <div className="navRobot">
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

      <div className="page">
        <div className="content">
          <div className="content-toolbar_toggle-container">
            <button
              id="toolbar_toggle"
              className="btn toolbar_toggle"
              onClick={openBar}
            >
              <img className="tools-icon" src="Icons/tools_icon.png" />
            </button>
          </div>
        </div>

        <div className="toolbar">
          <div className="toolbar-darkmode-container">
            <img className="darkmode-icon" src="Icons/darkmode_icon.png" />
            <p>Dunkelmodus</p>
          </div>
          <div className="toolbar-language-container">
            <img className="language-icon" src="Icons/language_icon.png" />
            <p>Deutsch/Englisch</p>
          </div>
          <div className="toolbar-easy_language-container">
            <img
              className="easy_language-icon"
              src="Icons/easy_language_icon.png"
            />
            <p>Einfache Sprache</p>
          </div>
          <div className="toolbar-contrast-container">
            <img className="contrast-icon" src="Icons/contrast_icon.png" />
            <p>Kontrast</p>
          </div>
          <div className="toolbar-font_size-container">
            <img className="font_size-icon" src="Icons/font_size_icon.png" />
            <p>Schriftgröße</p>
          </div>
          <div className="toolbar-eyetracking-container">
            <img
              className="eyetracking-icon"
              src="Icons/eyetracking_icon.png"
            />
            <p>Augensteuerung</p>
          </div>
          <div className="toolbar-colorblind-container">
            <img className="colorblind-icon" src="Icons/colorblind_icon.png" />
            <p>Farbenblindheit</p>
          </div>
          <div className="toolbar-sign_language-container">
            <img
              className="sign_language-icon"
              src="Icons/sign_language_icon.png"
            />
            <p>Gebärdensprache</p>
          </div>
        </div>
      </div>

      <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossOrigin="anonymous"
      ></script>

      <div id="bereiche">

      <div id="bereich">
        <h3>Max Mustermann</h3>
      </div>

      <div id="bereich1">
        <p>Charakter bearbeiten</p>
      </div>

      <div id="bereich2">
        <p>Trifft dich in Räumen </p>
        <p>Bearbeite deinen Charakter</p>
      </div>

      
        <label for="suche" id="bereich3">Suche einen Ort aus</label>
       
    <div id="orte">

      <div className="ortBereich">
        <h2>Freizeitpark</h2>
      </div>

      <div className="ortBereich">
        <h2>Schulklasse1</h2>
      </div>

      <div className="ortBereich">
        <h2>Park</h2>
      </div>

      <div className="ortBereich">
        <h2>Schulklasse2</h2>
      </div>

      <div className="ortBereich">
        <h2>Cafe</h2>
      </div>

      <div className="ortBereich">
        <h2>Blank-Room</h2>
      </div>
    </div>
    </div>

    </body>
  );
}
