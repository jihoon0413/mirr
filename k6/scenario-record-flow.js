import http from 'k6/http'
import {sleep, check} from 'k6'

const stadiums = ['서울월드컵경기장','대전월드컵경기장','광주월드컵경기장','전주월드컵경기장','DGB 대구은행파크','포항스틸야드'];
const opponents = ['FC서울','광주FC','울산HD','대구FC','전북 현대','안양FC','수원FC','포항','제주sk','대전 하나 시티즌','강원FC']
const pointType = ['GOAL','ASSIST']

export const options = {
    vus: 30,
    duration: '10s',
};

function randomDate() {
    const month = Math.floor(Math.random() * 12) + 1; // 1~30
    const day = Math.floor(Math.random() * 25) + 1; // 1~30
    return `2025-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
}

export default function() {

    const day = randomDate();

    const gameReq = JSON.stringify({
        stadium : stadiums[Math.floor(Math.random() * stadiums.length)],
        matchDay : day
//        matchDay : '2025-11-15' // 1~30
    });
    const gameRes = http.post('http://mirr-web-server:8080/game',
        gameReq,
        { headers: {"Content-Type": "application/json"}}
    );
    sleep(1);

    const gameId = gameRes.json('result.id');

    check(gameRes, {'게임 생성됨': r => r.status === 200});

    for(let i = 1 ; i <= 3 ; i++) {
        const quarterReq = JSON.stringify({
            gameId: gameId,
            quarterNum: i,
            opponent: opponents[Math.floor(Math.random() * opponents.length)],
            point: Math.floor(Math.random() * 10),
            losePoint: Math.floor(Math.random() * 10)
        })

        const quarterRes = http.post('http://mirr-web-server:8080/quarter',
            quarterReq,
            {headers : {"Content-Type" : "application/json"}}
        );

        check(quarterRes, {'쿼터 생성됨': r => r.status === 200});


        const quarterId = quarterRes.json('result.id')

        for(let j = 0 ; j < 4 ; j++) {
            const listSize = Math.floor(Math.random() * 4) + 1;
            const ids = [];

            for (let k = 0; k < listSize; k++) {
                ids.push(Math.floor(Math.random() * 70) + 1);
            }

            const pointReq = JSON.stringify({
                quarterId : quarterId,
                playerId : ids,
                type : pointType[Math.floor(Math.random() * pointType.length)]
            });

            const pointRes = http.post('http://mirr-web-server:8080/point',
                pointReq,
                {headers : {"Content-Type" : "application/json"}}
            );

            check(pointRes, {'포인트 기록': r => r.status === 200});
        }
    }

    const momReq = JSON.stringify({
        gameId : gameId,
        playerId : [Math.floor(Math.random() * 70) + 1]
    });

    const momRes = http.post('http://mirr-web-server:8080/mom',
        momReq,
        {headers : {"Content-Type" : "application/json"}}
    )

    check(momRes, {'mom 선정': r => r.status === 200});

    const attendIds = []
    for(let i = 0 ; i < 15 ; i++) {
        attendIds.push(Math.floor(Math.random() * 70) + 1);
    }

    const attendReq = JSON.stringify({
        gameId : gameId,
        playerId : attendIds
    });

    const attendRes = http.post('http://mirr-web-server:8080/attend',
        attendReq,
        {headers : {"Content-Type" : "application/json"}}
    )

    check(attendRes, {'참석자 기록': r => r.status === 200});

    http.get('http://mirr-web-server:8080/quarter/detail/${gameId}')
    http.get('http://mirr-web-server:8080/point/detail?quarterId=${quarterId}&gameId=${gameId}')
}