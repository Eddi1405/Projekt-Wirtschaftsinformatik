import React from 'react';
import ReactDOM from 'react-dom';
import "./../../styles/faq.css";
import { Link } from "react-router-dom";
import Navbar from '../navbar/navbar'
import Sidebar from '../sidebar/sidebar'


/*
  Created by Lars Eric 
*/



export default function Faq() {


 

return (

    
    <body>
        <Navbar>
      </Navbar>
      <Sidebar>
      </Sidebar>
        
        <div class="faq-header">
        <h1>FAQ</h1>
        </div>
        <div id="faq">
            <div class="faq-item">
                <div class="faq-question">Was ist der Zweck dieser Webseite?</div>
                <div class="faq-answer">Die Webseite soll ein möglichst barrierefreies Lernen ermöglichen. 
                    Ebenfalls soll es ein Ort sein an dem Studenten sich Online verabreden und austauschen können.
                Studenten können in der WebApp Ihren Lerntyp bestimmen.</div>
            </div>
            
            <div class="faq-item">
                <div class="faq-question">Wo finde ich weitere Unterstützung?</div>
                <div class="faq-answer">Weitere Unterstützung beim Umgang bietet dir "CHIP". Er hilft dir mit der Bedienung der App und steht dir bei Fragen zur Verfügung.</div>
            </div>
    
            <div class="faq-item">
                <div class="faq-question">Welche Unterstützungen bietet mir die WebApp bei der Bedienung?</div>
                <div class="faq-answer">Zur Auswahl stehen verschiedene Modis. Es kann der Kontrast verändert werden, falls man Probleme bei der Sicht auf die Webseite hat.
                    Es kann die "Leichte Sprache" aktiviert werden, falls man Probleme mit dem Lesen hat. Die Bedienung der WebApp kann durch Zeichensprache unterstützt werden.
                    Die Schriftgröße kann entsprechend der Bedürfnisse eingestellt werden. Es gibt Modis für verschiedene Farbblindheiten. Die Webseite kann Text-to-Speech wiedergegeben werden. 
                    Durch Eye-Tracking nimmt die WebApp ebenfalls weitere Barrieren.
                </div>
            </div>
    
            <div class="faq-item">
                <div class="faq-question">Kann ich mit anderen Leuten chatten?</div>
                <div class="faq-answer">Du kannst dich mit anderen Studenten in Chatrooms treffen und chatten oder Ihnen privat schreiben.
                </div>
            </div>
    
            <div class="faq-item">
                <div class="faq-question">Wo kann ich Content einsehen?</div>
                <div class="faq-answer">Im Hauptmenü findest du den Weg zum Content den andere Studenten posten. Dort bleibst du auf dem neusten Stand.
                </div>
            </div>
           
        </div>
        

    </body>
   

  );
}