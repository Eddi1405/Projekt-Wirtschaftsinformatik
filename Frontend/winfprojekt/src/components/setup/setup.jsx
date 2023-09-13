/*
  Created by Erik Brehl
*/

import React, { useEffect } from "react";
import "./../../styles/setup.css";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import axios from "axios";
import {useNavigate} from 'react-router-dom';




function Setup() {
  const navigate = useNavigate();

  var contValue = false;
  var colorbValue = 0;
  var fontSizeValue = 0;
 
  const location = useLocation();
  console.log(location.state.name);
  console.log(location.state.id);

  const toMenu=()=>{

    axios.patch(
      "http://193.16.123.46:8080/users/"+location.state.id,
      {
        username: location.state.name, 
        contrast: contValue,
        fontSize: fontSizeValue,
        eyeTracking: false,
        colorBlindness: colorbValue,
        signLanguage: false

      }).then(function (response) {
        console.log(response);
  
        }).catch(function (error) {
        console.log(error);
      });

      if(contValue == true){
        navigate('/contmenu',{ state:{id:location.state.id,name:location.state.name,contrast:contValue,
          fontSize:fontSizeValue,eyeTracking:false,colorBlindness:colorbValue}
        });
    }else if(colorbValue == 1){
      navigate('/colorbbluemenu',{ state:{id:location.state.id,name:location.state.name,contrast:contValue,
        fontSize:fontSizeValue,eyeTracking:false,colorBlindness:colorbValue}
      });
      }else{
        navigate('/menu',{ state:{id:location.state.id,name:location.state.name,contrast:contValue,
          fontSize:fontSizeValue,eyeTracking:false,colorBlindness:colorbValue}
        });
      }

    
      }


   function changeFontsize () {
      
      if(fontSizeValue == 0){
        document.getElementById('fontvalue').innerHTML = "Groß"; 
        fontSizeValue++; 
      }
      else if(fontSizeValue == 1){
        document.getElementById('fontvalue').innerHTML = "Klein";  
        fontSizeValue++;
      }else if(fontSizeValue == 2){
        document.getElementById('fontvalue').innerHTML = "Normal";  
        fontSizeValue=0;
      }
  
  }


  function changeContrast(){
    
    var body = document.querySelector(".layout");
    var header = document.querySelector(".robot");
    var main = document.querySelector(".main");
    var footer = document.querySelector(".footer");
    var buttons = document.getElementsByTagName('button');

    if(contValue == false){
      body.style.backgroundColor = "#fffe00";
      header.style.backgroundColor = "#202021";
      main.style.backgroundColor= "#fffe00";
      footer.style.backgroundColor= "#202021";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#01c500";
    }
      contValue = true;
    }

    
    else if (contValue == true){
      body.style.backgroundColor = "#e7fdfe";
      header.style.backgroundColor = "#181e2f";
      main.style.backgroundColor= "#e7fdfe";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#e7fdfe";
    }
      contValue = false;
    }
    
    console.log(contValue);
  }
  
  function changeColorb(){
    var body = document.querySelector(".layout");
    var header = document.querySelector(".robot");
    var main = document.querySelector(".main");
    var footer = document.querySelector(".footer");
    var buttons = document.getElementsByTagName('button');
    


    //Blaublindheit(Tritanopie)
    if(colorbValue == 0){
      body.style.backgroundColor = "#ffb7c1";
      main.style.backgroundColor= "#ffb7c1";
      header.style.backgroundColor = "#00868f";
      footer.style.backgroundColor= "#00868f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#ffb7c1";
    }

      var colorbdiv = document.getElementById('colorb');
      colorbdiv.innerHTML = "Farbblindheit <br> Blaublindheit <br> (Tritanopie)";
      colorbValue = 1;
    }

    //Grünblindheit
    else if(colorbValue == 1){
      body.style.backgroundColor = "#febe3a";
      main.style.backgroundColor= "#febe3a";
      header.style.backgroundColor = "#181e2f";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#febe3a";
    }

      var colorbdiv = document.getElementById('colorb');
      colorbdiv.innerHTML = "Farbblindheit <br> Grünblindheit <br> (Deuteranopie)";
      colorbValue = 2;
    }

    //Rotblindheit
    else if(colorbValue == 2){
      body.style.backgroundColor = "#e6cb14";
      main.style.backgroundColor= "#e6cb14";
      header.style.backgroundColor = "#181e2f";
      footer.style.backgroundColor= "#181e2f";

      for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.style.backgroundColor= "#e6cb14";
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
          
          <div className="setup_logo">
            <img src="images/divlab.png" width="250" height="170" />
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
              <button type="button" className="fsize" onClick={changeFontsize}> </button>
              <div className="hiddDiv">Schriftgröße <p id="fontvalue">normal</p> </div>
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
        <button type="button" className="arrow" onClick={toMenu}></button> 
        </div>
      </section>
    </div>
  );
}

export default Setup;