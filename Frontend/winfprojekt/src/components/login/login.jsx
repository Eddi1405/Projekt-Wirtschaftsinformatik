import React from 'react';
import ReactDOM from 'react-dom';
import "./../../styles/login.css";
import { Link } from "react-router-dom";

export default function Login() {


return (


<body className="kgbody">
  <div className="container">

    
    <div className="split-background"></div>
    <img src="images/logo.png" alt="Logo" className="kglogo"/>
    <h1>Die Experimentelle Lernplattform</h1>
    <div className ="bg">
    <div className="form-group">
      <input type="text" id="username" name="username" placeholder="Username" required/>
    </div>
    <div className="form-group">
      <input type="password" id="password" name="password" placeholder="Passwort" required/>
    </div>
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
    <Link to="/setup">  <img src="Icons/arrow.png" alt="Pfeil"/></Link>
    </div>
    </div> 
  </div>
  

</body>

  );
}