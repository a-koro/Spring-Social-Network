import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { RecoilRoot } from "recoil";
import recoilPersist from "recoil-persist";

const { RecoilPersist, updateState } = recoilPersist([], {
    key: "recoil-persist",
    storage: sessionStorage,
  });

ReactDOM.render(

<RecoilRoot initializeState={updateState}>
<RecoilPersist />
<App/>

</RecoilRoot>
, document.getElementById('root'));
