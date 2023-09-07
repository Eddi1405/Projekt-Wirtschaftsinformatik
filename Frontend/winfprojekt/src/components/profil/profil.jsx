/*
  Created by Erik Brehl
*/

import React from "react";
import "./../../styles/profil.css";
import { Link } from "react-router-dom";
import axios from "axios";
import Navbar from '../navbar/navbar'
import Sidebar from '../sidebar/sidebar'
export default function Profil() {


    
  
   
  
  return (
  
  
  <body >
      <Navbar>
    </Navbar>
    <Sidebar>
      </Sidebar>

    <div className="profile-container">
        <div className="profile-pic">
            <img src="Icons/Avatar.png" alt="Profilbild"/><br/>
            <button className="change_img">Profilbild ändern</button>
        </div>
        <div className="personal-data">
            <div className="header">Persönliche Daten</div>
            <p className="personaldata">Name: Max Mustermann</p>
            <p className="personaldata">Alter: 23 Jahre</p>
            <p className="personaldata">Geschlecht: Männlich</p>
            <p className="personaldata">Studienfach: Angewandte Informatik - Wirtschaftsinformatik</p>
            <p className="personaldata">5.Semester</p><br/>
            <p className="personaldata">Studienstandort: TH OWL Höxter</p><br/>
            <p className="personaldata">Lerntyp-Grafik:</p>
            <img src="graph.jpg" alt="Profilbild"/>
        </div>
        </div>
    
  </body>
  
    );
  }