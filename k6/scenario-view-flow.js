import http from 'k6/http'
import {sleep, check} from 'k6'

export const options = {
    vus: 30,
    duration: '10s'
}

export default function() {

    const gameId = Math.floor(Math.random() * 5) + 1;
    const quarterId = Math.floor(Math.random() * 10) + 1;

    http.get('http://mirr-web-server:8080/');
    http.get('http://mirr-web-server:8080/score');
    http.get('http://mirr-web-server:8080/assist');
    http.get('http://mirr-web-server:8080/attend');
    http.get('http://mirr-web-server:8080/mom');
    http.get('http://mirr-web-server:8080/point/detail?quarterId=' + quarterId + '&gameId=' + gameId)
    http.get('http://mirr-web-server:8080/players');
    http.get('http://mirr-web-server:8080/quarter/detail/' + gameId);
    sleep(0.5);
}

