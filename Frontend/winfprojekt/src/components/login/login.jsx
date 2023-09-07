import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import "./../../styles/login.css";
import { Link } from "react-router-dom";
import axios from "axios";


export default function Login() {

  const USER_REGEX = /^\[A-z\][A-z0-9-_]{3,23}$/;
  const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
 



  function loginUser() {

    let username = document.getElementById('username').value
    let password = document.getElementById('password').value


    window.location.href="/setup";


    axios.get('http://193.16.123.46:8081/user?username='+username)
      .then(function (response) {
        // handle success
        console.log(response);
        window.location.href="/setup";
      })
      .catch(function (error) {
        // handle error
        console.log(error);
      })
      .finally(function () {
        // always executed
      });

      
     
  }

 

return (


<body className="kgbody">
  <div className="container">
  
    
    <div className="split-background"></div>
    <img src="images/divlab.png" alt="Logo" className="kglogo"/>
    <h1>Die Experimentelle Lernplattform</h1>
    <div className ="bg">
    <div className="form-group">
      <input type="text" id="username" name="username" placeholder="Username" required/>
    </div>
    <div className="form-group">
      <input type="password" id="password" name="password" placeholder="Passwort" required/>
    </div>
    
     <p class="registerText"> Noch kein Konto?<a href="/Register"> Hier</a> Regisitrieren! </p>
      
    <div className="kgicons">
      <figure>
        <img src="Icons/contr.png" alt="Icon 1"/>
        <figcaption>Kontrast</figcaption>
      </figure>
      <figure>
        <figure>Leichte Sprache</figure>
        <img src="Icons/lang.png" alt="Icon 2"/>
      </figure>
      <figure>
        <img src="Icons/signlang.png" alt="Icon 3"/>
        <figcaption>Gebärdesprache</figcaption>
      </figure>
      <figure>
        <figure>Schriftgröße</figure>
        <img src="Icons/fsize.png" alt="Icon 4"/>
      </figure>
      <figure>
        <img src="Icons/colorb.png" alt="Icon 5"/>
        <figcaption>Farbblindheit</figcaption>
      </figure>
      <figure>
        <figure>Text in Sprache</figure>
        <img src="Icons/txtspeech.png" alt="Icon 6"/>
      </figure>
      <figure>
        <img src="Icons/eyetrack.png" alt="Icon 7"/>
        <figcaption>Augenverfolgung</figcaption>
      </figure>
    </div>
    <div className="kgarrow"> 
   <img src="Icons/arrow.png" alt="Pfeil" onClick={loginUser}/>
  
    </div>
    </div> 
  </div>
  

</body>

  );
}