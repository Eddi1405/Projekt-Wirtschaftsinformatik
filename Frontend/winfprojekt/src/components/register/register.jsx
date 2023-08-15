
/*
  Created by Kevin Gnade
  Edited by Erik Brehl
*/

import React from "react";
import "./../../styles/register.css";
import { Link } from "react-router-dom";
import axios from "axios";






export default function Register() {


    
  
   
  
    return (
    
    
    <body class="reg_kgbody2">
      <div class="reg_ueber">
        <img src="images/divlab.png" alt="Logo" class="reg_logo"/>
        <h1 class="reg_h1">Die Experimentelle Lernplattform</h1>
      </div>
       <div class="reg_kgcontainer2">
        
        
        <p class="registration">Registrierung</p>
        <div class="reg_forms-group">
          <input type="text" id="username" name="username" placeholder="Username" required/>
        </div>
        <div class="reg_forms-group">
          <input type="email" id="email" name="email" placeholder="E-Mail" required/>
        </div>
        <div class="reg_forms-group">
          <input type="password" id="password" name="password" placeholder="Passwort" required/>
        </div>
        <div class="reg_forms-group">
          <input type="password" id="password-confirm" name="password-confirm" placeholder="Passwort bestätigen" required/>
        </div>
        
        <div class="kgicons2">
            
        </div>
        <div class="reg_arrow">
          <a href="/setup"><img src="Icons/arrow.png" alt="Pfeil"/></a>
        </div>
      </div>
    </body>
    
      );
    }