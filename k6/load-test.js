import http from 'k6/http';
import { sleep } from 'k6';
import { check } from 'k6';

export const options = {
  vus: 30,
  duration: '1m',
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95% 요청이 500ms 이하
  },
};

export default function () {
  const res = http.get('http://mirr-web-server:8080/players');
  check(res, { 'status 200': (r) => r.status === 200 });
  sleep(1);
}