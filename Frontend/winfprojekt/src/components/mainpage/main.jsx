import React from 'react';
import ReactDOM from 'react-dom';
import "./../../styles/main.css";
import { Link } from "react-router-dom";



export default function Main() {


   /* let toolbar_toggle = document.querySelector(".toolbar_toggle");
    toolbar_toggle.addEventListener("click", function() {
    document.querySelector("body").classList.toggle("active");
    });*/


    function openBar(){
        document.querySelector("body").classList.toggle("active");
    }

    return (
        
        
        <body>
    <div class="page">
        <div class="content">
             <div class="content-toolbar_toggle-container">
                <button id="toolbar_toggle" class="btn toolbar_toggle" onClick={openBar}>
                  <img class="tools-icon" src="Icons/tools_icon.png"/>
                </button>
            </div>
        </div>

        <div class="toolbar">
            <div class="toolbar-darkmode-container">
                <img class="darkmode-icon" src="Icons/darkmode_icon.png"/>
                <p>Dunkelmodus</p>                
            </div>
            <div class="toolbar-language-container">
                <img class="language-icon" src="Icons/language_icon.png"/>
                <p>Deutsch/Englisch</p>
            </div>
            <div class="toolbar-easy_language-container">
                <img class="easy_language-icon" src="Icons/easy_language_icon.png"/>
                <p>Einfache Sprache</p>
            </div>
            <div class="toolbar-contrast-container">
                <img class="contrast-icon" src="Icons/contrast_icon.png"/>
                <p>Kontrast</p>
            </div>
            <div class="toolbar-font_size-container">
                <img class="font_size-icon" src="Icons/font_size_icon.png"/>
                <p>Schriftgröße</p>
            </div>
            <div class="toolbar-eyetracking-container">
                <img class="eyetracking-icon" src="Icons/eyetracking_icon.png"/>
                <p>Augensteuerung</p>
            </div>
            <div class="toolbar-colorblind-container">
                <img class="colorblind-icon" src="Icons/colorblind_icon.png"/>
                <p>Farbenblindheit</p>
            </div>
            <div class="toolbar-sign_language-container">
              <img class="sign_language-icon" src="Icons/sign_language_icon.png"/>
              <p>Gebärdensprache</p>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
  </body>
    


    
      );
    }