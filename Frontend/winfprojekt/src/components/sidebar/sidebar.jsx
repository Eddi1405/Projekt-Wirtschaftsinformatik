/*
  Created by Erik Brehl
*/

import React from 'react';

import './../../styles/sidebar.css'
import { HiOutlineSpeakerWave } from "react-icons/hi2";
import { AiOutlineFontSize } from "react-icons/ai";
import { ImContrast } from "react-icons/im";

export default function Sidebar() {


  function openBar() {
    document.querySelector("body").classList.toggle("active");
  }


    return (
       
       
      <div className="basic_page">
      <div className="content">
        <div className="basic_content-toolbar_toggle-container">
          <button
            id="basic_toolbar_toggle"
            className="btn toolbar_toggle"
            onClick={openBar}
          >
            <img className="tools-icon" src="Icons/tools_icon.png" />
          </button>
        </div>
      </div>

      <div className="basic_toolbar">
        <div className="basic_toolbar-darkmode-container">
          <img className="darkmode-icon" src="Icons/darkmode_icon.png" />
          <p>Dunkelmodus</p>
        </div>
        <div className="basic_toolbar-language-container">
          <img className="language-icon" src="Icons/language_icon.png" />
          <p>Deutsch/Englisch</p>
        </div>
        <div className="basic_toolbar-easy_language-container">
          <img
            className="easy_language-icon"
            src="Icons/easy_language_icon.png"
          />
          <p>Einfache Sprache</p>
        </div>
        <div className="basic_toolbar-contrast-container">
          <img className="contrast-icon" src="Icons/contrast_icon.png" />
          <p>Kontrast</p>
        </div>
        <div className="basic_toolbar-font_size-container">
          <img className="font_size-icon" src="Icons/font_size_icon.png" />
          <p>Schriftgröße</p>
        </div>
        <div className="basic_toolbar-eyetracking-container">
          <img
            className="eyetracking-icon"
            src="Icons/eyetracking_icon.png"
          />
          <p>Augensteuerung</p>
        </div>
        <div className="basic_toolbar-colorblind-container">
          <img className="colorblind-icon" src="Icons/colorblind_icon.png" />
          <p>Farbenblindheit</p>
        </div>
        <div className="basic_toolbar-sign_language-container">
          <img
            className="sign_language-icon"
            src="Icons/sign_language_icon.png"
          />
          <p>Gebärdensprache</p>
        </div>
      </div>
    </div>

      );
  }


