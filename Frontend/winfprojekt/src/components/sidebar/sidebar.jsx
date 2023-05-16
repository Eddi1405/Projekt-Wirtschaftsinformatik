/*
  Created by Erik Brehl
*/

import React from 'react';

import './../../styles/sidebar.css'
import { HiOutlineSpeakerWave } from "react-icons/hi2";
import { AiOutlineFontSize } from "react-icons/ai";
import { ImContrast } from "react-icons/im";

export default function Sidebar() {
    return (
       
        <div>
            <nav className='sidebar'>
            <li> <HiOutlineSpeakerWave size={30} /> </li>
            <li> <AiOutlineFontSize fill="#0a0a0a" size={30}/> </li>
            <li> <ImContrast fill="#0a0a0a" size={30}/> </li>
            </nav>
            
        </div>

      );
  }


