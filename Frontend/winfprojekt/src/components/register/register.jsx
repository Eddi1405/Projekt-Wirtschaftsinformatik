
/*
  Created by Kevin Gnade
  Edited by Erik Brehl
*/

import React from "react";
import "./../../styles/register.css";
import { Link } from "react-router-dom";
import axios from "axios";
import Alert from '@mui/material/Alert';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';


export default function Register() {
  

  function registerUser() {
    
    
   
   // document.getElementById("alert").style.display = "block";
   document.getElementById("alert").style.display = "block";

    setTimeout(() => {
      document.getElementById("alert").style.display = "none";
    }, 5000);

    let getusername = document.getElementById('username').value
    let getemail = document.getElementById('email').value
    let getpassword = document.getElementById('password').value
    let getpasswordconf = document.getElementById('password-confirm').value


    if(getpassword != getpasswordconf ){
      document.getElementById('alertText').innerHTML = "bigblackc";
    }
    
    const headers = {
      'Access-Control-Allow-Origin' :'*',
      'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, HEAD'
  };

  

  
    /*
  axios.post(
    "http://localhost:8080/register",
    {
      username: getusername, 
      email: getemail,
      password: getpassword,
      learningtype: "visual",
    }).then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });

*/


/*
      axios.get("http://localhost:8080/users/-1",{ headers }).then(function (response) {
        // handle success
        console.log(response);
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");  
      })
      .catch(function (error) {
        // handle error
        console.log(error);
      })
      .finally(function () {
        // always executed
      });
*/


   

  }
    
  
   
  
    return (

      
    
    
    <div className="reg_kgbody2">

  <div className="alertDisplay" id = "alert">
    <Stack sx={{ width: '1200px', height: '30px'}} >
      <Alert severity="warning" ><p id="alertText">This is a warning alert — check it out!</p></Alert>
    </Stack>
    </div>

      <div className="reg_ueber">
        <img src="images/divlab.png" alt="Logo" className="reg_logo"/>
        <h1 className="reg_h1">Die Experimentelle Lernplattform</h1>
      </div>
       <div className="reg_kgcontainer2">
        
        
        <p className="registration">Registrierung</p>
        <div className="reg_forms-group">
          <input type="text" id="username" name="username" placeholder="Username" required/>
        </div>
        <div className="reg_forms-group">
          <input type="email" id="email" name="email" placeholder="E-Mail" required/>
        </div>
        <div className="reg_forms-group">
          <input type="password" id="password" name="password" placeholder="Passwort" required/>
        </div>
        <div className="reg_forms-group">
          <input type="password" id="password-confirm" name="password-confirm" placeholder="Passwort bestätigen" required/>
        </div>
        
        <div className="reg_arrow">

          <button  onClick={registerUser}></button>

          <a href="/setup" ><img src="Icons/arrow.png" alt="Pfeil"/></a>
          
        </div>
      </div>
    </div>
  
      );
    }