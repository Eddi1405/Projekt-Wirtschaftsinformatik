/*
  Created by Edward Siebert
*/

import React from 'react';

import './../../styles/thread.css'
import Navbar from '../navbar/navbar'

export default function Thread(){



    return (


        <div class="page">

        <div class="navbar">
          <Navbar>
          </Navbar>
        </div>
  
        <div class="threadsite">
  
          <div class="threadoverviewframe">
            <div class="threadoverview">
  
              <div class="threadsheadline">
                <div class="hot_tags">
                  <button class="tasks_tag" type="button">
                    <h6>Aufgaben</h6>
                  </button>
                  <button class="more_tags" type="button">
                    <h6>weitere Tags...</h6>
                  </button>
                </div>
                <div class="thread_filter">
                  <h6><b>Filter</b></h6>
                </div>
              </div>
  
              <div class="thread_1">
                <div class="thread_1_topic">
                  <b>Prof. Dr Willy Mustermann hat Dateien Hochgeladen!<br></br></b>
                  <i>Hier die Übungsblätter, über welche wir in der letzten Vorlesung gesprochen hatten.<br></br></i>
                  <i>
                    Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text 
                    Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text 
                    Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text 
                    Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
                    Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
                  </i>
                </div>
                <div class="thread_1_button">
                  <button class="read_less_thread_1" type="button">
                    <h6>Weniger anzeigen</h6>
                  </button><br></br>
                  <button class="pdf_download" type="button">
                    <img class="PDF_icon" src="src/assets/img/PDF_icon.png"/><br></br>
                    <p>Übungsblätter.pdf</p>
                </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
        );
}


