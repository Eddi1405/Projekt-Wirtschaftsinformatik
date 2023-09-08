/*
  Created by Kevin Gnade
  Edited by Erik Brehl
*/

import React from "react";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import "./../../styles/register.css";

import axios from "axios";
import Alert from "@mui/material/Alert";
import Stack from "@mui/material/Stack";



export default function Register()  {



  function isValidEmail(email) {
    return /\S+@\S+\.\S+/.test(email);
  }

  function throwMessage(error) {
    if (error == true) {
      document.getElementById("alert2").style.display = "none";
      document.getElementById("alert").style.display = "block";
    } else {
      document.getElementById("alert").style.display = "none";
      document.getElementById("alert2").style.display = "block";
    }
  }

  const registerForm = useRef(null);
  const [error, isError] = useState(true);
  const headers = {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET, POST, OPTIONS, HEAD",
  };


  function registerUser() {
    const navigate=useNavigate();
    const form = registerForm.current;

    document.getElementById("alertText2").innerHTML = "Alles korrekt";
    isError(false);



    if(form["username"].value.trim().length < 3){
      document.getElementById("alertText").innerHTML =
        "Bitte gültigen Username eingeben";
      isError(true);
    }else if (!isValidEmail(form["email"].value)) {
      document.getElementById("alertText").innerHTML =
        "Bitte gültige Email eingeben";
      isError(true);
    } else if (
      form["password"].value != form["password-confirm"].value ||
      form["password"].value.trim().length < 3 ||
      form["password-confirm"].value.trim().length < 3
    ) {
      document.getElementById("alertText").innerHTML =
        "Passwörter stimmen nicht überein";
      isError(true);
    }else{/*
      axios.post(
        "http://localhost:8080/register",
        {
          username: form["username"].value, 
          email: form["email"].value,
          password: form["password"].value,
          learningtype: "visual",
        }).then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });*/
        PerformLogin = () => {
          navigate('/setup');
      }
        



    }

    throwMessage(error);

    
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
      <div className="alertDisplay" id="alert">
        <Stack sx={{ width: "1200px", height: "30px" }}>
          <Alert variant="filled" severity="warning">
            <p id="alertText"></p>
          </Alert>
        </Stack>
      </div>

      <div className="alertDisplay2" id="alert2">
        <Stack sx={{ width: "1200px", height: "30px" }}>
          <Alert variant="filled" severity="success">
            <p id="alertText2">Registrierung erfolgreich!</p>
          </Alert>
        </Stack>
      </div>

      <div className="reg_ueber">
        <img src="images/divlab.png" alt="Logo" className="reg_logo" />
        <h1 className="reg_h1">Die Experimentelle Lernplattform</h1>
      </div>
      <div className="reg_kgcontainer2">
        <p className="registration">Registrierung</p>

        <form ref={registerForm}>
          <div className="reg_forms-group">
            <input
              type="text"
              id="username"
              name="username"
              placeholder="Username"
              required
            />
          </div>
          <div className="reg_forms-group">
            <input
              type="email"
              id="email"
              name="email"
              placeholder="E-Mail"
              required
            />
          </div>
          <div className="reg_forms-group">
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Passwort"
              required
            />
          </div>
          <div className="reg_forms-group">
            <input
              type="password"
              id="password-confirm"
              name="password-confirm"
              placeholder="Passwort bestätigen"
              required
            />
          </div>
        </form>

        <div className="reg_arrow">

         
            <img src="Icons/arrow.png" alt="Pfeil" onClick={registerUser} />
          
        </div>
      </div>
    </div>
  );
}
