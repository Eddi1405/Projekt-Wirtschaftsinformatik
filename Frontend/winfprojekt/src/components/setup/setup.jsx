/*
  Created by Erik Brehl
*/

import React from "react";
import "./../../styles/setup.css";

export default function Setup() {

  var contValue = 0;
  var colorbValue = 0;

/*
  function changeContrast() {

    var element = document.querySelector(".contrastDiv > button");
    var bg_color = window.getComputedStyle(element, null).backgroundColor;
    bg_color = bg_color.match(/\d+/g);
    //alert(rgbToHex(bg_color));

   /* if(rgbToHex(bg_color) == "#e7fdfe"){
      alert("Bingbong");
      element.style.backgroundColor= "green";
    }
    changeHeader();
    
  }*/

  function changeContrast(){
    
    var body = document.querySelector("body");
    var header = document.querySelector(".robot");
    var main = document.querySelector(".main");
    var footer = document.querySelector(".footer");

    if(contValue == 0){
      body.style.backgroundColor = "#e67e00";
      header.style.backgroundColor = "#760600";
      main.style.backgroundColor= "#e67e00";
      footer.style.backgroundColor= "#760600";
      contValue = 1;
    }

    else if(contValue == 1){
      body.style.backgroundColor = "#fff84d";
      header.style.backgroundColor = "#00c3cc";
      main.style.backgroundColor= "#fff84d";
      footer.style.backgroundColor= "#00c3cc";
      contValue = 2;
    }

    else if (contValue == 2){
      body.style.backgroundColor = "#e7fdfe";
      header.style.backgroundColor = "#181e2f";
      main.style.backgroundColor= "#e7fdfe";
      footer.style.backgroundColor= "#181e2f";
      contValue = 0;
    }
    

  }
  
  function changeColorb(){
    var body = document.querySelector("body");
    var header = document.querySelector(".robot");
    var main = document.querySelector(".main");
    var footer = document.querySelector(".footer");
    var buttons = document.getElementsByTagName('button');
    


    //Blaublindheit(Tritanopie)
    if(colorbValue == 0){
      body.style.backgroundColor = "#e6e6e6";
      main.style.backgroundColor= "#e6e6e6";
      header.style.backgroundColor = "#181e2f";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#e6e6e6";
    }

      var colorbdiv = document.getElementById('colorb');
      colorbdiv.innerHTML = "Farbblindheit <br> Blaublindheit <br> (Tritanopie)";
      colorbValue = 1;
    }

    //Grünblindheit
    else if(colorbValue == 1){
      body.style.backgroundColor = "#ff7676";
      main.style.backgroundColor= "#ff7676";
      header.style.backgroundColor = "#181e2f";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#ff7676";
    }

      var colorbdiv = document.getElementById('colorb');
      colorbdiv.innerHTML = "Farbblindheit <br> Grünblindheit <br> (Deuteranopie)";
      colorbValue = 2;
    }

    //Rotblindheit
    else if(colorbValue == 2){
      body.style.backgroundColor = "#3399ff";
      main.style.backgroundColor= "#3399ff";
      header.style.backgroundColor = "#181e2f";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#3399ff";
    }

      var colorbdiv = document.getElementById('colorb');
      colorbdiv.innerHTML = "Farbblindheit <br> Rotblindheit <br> (Protanopie)";
      colorbValue = 3;
    }

    //Standard
    else if(colorbValue == 3){
      body.style.backgroundColor = "#e7fdfe";
      main.style.backgroundColor= "#e7fdfe";
      header.style.backgroundColor = "#181e2f";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#e7fdfe";
    }

      var colorbdiv = document.getElementById('colorb');
      colorbdiv.innerHTML = "Farbblindheit";
      colorbValue = 0;
    }


  }
    
  function componentToHex(c) {
    var hex = c.toString(16);
    return hex.length == 1 ? "0" + hex : hex;
  }
  
  function rgbToHex(rgb) {
    return "#" + componentToHex(+rgb[0]) + componentToHex(+rgb[1]) + componentToHex(+rgb[2]);
  }

  

 

  return (
    <div>
      <section className="layout">
        <div className="header">
          <div className="robot">
          <div className="desc">"Gestalte die Website nach deinen<br></br> Vorstellungen und Wünschen um.<br></br>
          Navigiere dazu unten auf den Buttons!"</div>
          <p className="sent">:</p>
          <img src="Icons/robot.png" width="130" height="120" /> 
          
         
          </div>
          
          <div className="logo">
            <img src="images/logo.png" width="180" height="120" />
          </div>
        </div>

        <div className="main">
          <div className="icons">
            <div className="contrastDiv">
              <button type="button"
                className="contrast"
                onClick={changeContrast}
              ></button>
              <div className="hiddDiv">Kontrast </div>
            </div>

            <div className="langDiv">
              <button type="button" className="lang"></button>
              <div className="hiddDiv">Leichte Sprache </div>
            </div>

            <div className="signlangDiv">
              <button type="button" className="signlang"></button>
              <div className="hiddDiv">Gebärdensprache</div>
            </div>

            <div className="fsizeDiv">
              <button type="button" className="fsize"></button>
              <div className="hiddDiv">Schriftgröße </div>
            </div>

            <div className="colorbDiv">
              <button type="button" className="colorb" onClick={changeColorb}></button>
              <div id="colorb" className="hiddDiv">Farbblindheit </div>
            </div>

            <div className="txtspeechDiv">
              <button type="button" className="txtspeech"></button>
              <div className="hiddDiv">Text-to-Speech </div>
            </div>

            <div className="eyetrackDiv">
              <button type="button" className="eyetrack"></button>
              <div className="hiddDiv">Eye-Tracking </div>
            </div>
          </div>
        </div>


        <div className="footer"> 
        <div className="footerDiv">Die Experimentelle Plattform
        </div>
        <button type="button" className="arrow"></button>
        </div>
      </section>
    </div>
  );
}
