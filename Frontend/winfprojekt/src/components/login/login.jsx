/*
  Created by Kevin Gnade
  Edited by Erik Brehl
*/



import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import "./../../styles/login.css";
import axios from "axios";
import { useRef } from "react";
import {useNavigate} from 'react-router-dom';
import Alert from "@mui/material/Alert";
import Stack from "@mui/material/Stack";


function Login(props) {

 // const USER_REGEX = /^\[A-z\][A-z0-9-_]{3,23}$/;
 // const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

  const loginForm = useRef(null);  
  const navigate = useNavigate();

  const toSetup=(idUser,username)=>{
    navigate('/setup',{state:{id:idUser,name:username}});
      }


  function loginUser() {
    

    const form = loginForm.current;

   axios.post(
    "http://localhost:8080/login",
    {
      username: form["username"].value, 
      password: form["password"].value 
    }).then(function (response) {
      console.log(response.status);

      if(response.status == 200){
        document.getElementById("alert").style.display = "none";
        document.getElementById("alert2").style.display = "block";

        setTimeout(() => {
          toSetup(response['data']['id'],form["username"].value);
        },2000);
      
      }

      }).catch(function (error) {
      console.log(error);
      document.getElementById("alert").style.display = "block";
    });

  
  }





 

return (


<body className="kgbody">
<div className="alertDisplay" id="alert">
        <Stack sx={{ width: "1200px", height: "30px" }}>
          <Alert variant="filled" severity="warning">
            <p id="alertText">Login Daten nicht korrekt!</p>
          </Alert>
        </Stack>
      </div>

      <div className="alertDisplay2" id="alert2">
        <Stack sx={{ width: "1200px", height: "30px" }}>
          <Alert variant="filled" severity="success">
            <p id="alertText2">Login erfolgreich!</p>
          </Alert>
        </Stack>
      </div>


  <div className="container">

    
    <div className="split-background"></div>
    <img src="images/divlab.png" alt="Logo" className="kglogo"/>
    <h1>Die Experimentelle Lernplattform</h1>
    <div className ="bg">
    <form ref={loginForm}> 
    <div className="form-group">

    
      <input className="login_input" type="text" id="username" name="username" placeholder="Username" required/>
    </div>
    <div className="form-group">
      <input className="login_input"  type="password" id="password" name="password" placeholder="Passwort" required/>
    </div>
   
    </form>
     <p className="registerText"> Noch kein Konto?<a href="/Register"> Hier</a> Regisitrieren! </p>
      
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

export default Login;