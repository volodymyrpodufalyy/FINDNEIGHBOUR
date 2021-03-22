import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './app/App';
import ListOfNeighbor from "./components/ListOfNeighbor";
import registerServiceWorker from './registerServiceWorker';
import { BrowserRouter as Router } from 'react-router-dom';
import "./styles/index.scss";

ReactDOM.render(
    <Router>
        <App />
    </Router>,
    document.getElementById('root')
);

registerServiceWorker();