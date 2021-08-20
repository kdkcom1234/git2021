// 계좌관리

import { useRef, useState } from "react";

// 버튼: 입금버튼, 출금버튼
// 버튼 클릭시에 입금 금액 또는 출금 금액을 입력 받을 수 있음.

// 목록: 입금, 출금액 목록을 ul > li로 표시한다.
// 입금 금액은 <li> 입금: 금액 (녹색)</li> 으로 표시
// 출금 금액은 <li> 출금: -금액 (빨간색)</li> 으로 표시

// 잔액: 잔액을 입금, 출금 버튼 우측에 표시한다.

const ListItem = ({ money }: { money: number }) => {
  return (
    <li style={{ color: money < 0 ? "red" : "green" }}>
      {money < 0 ? "출금: " : "입금: "}
      {money}
    </li>
  );
};

const AccountManager = () => {
  // useState<state타입>(초깃값);
  // [state변수, state변경함수]  반환

  // 입출금 이력을 표시하는 state
  const [logs, setLogs] = useState<number[]>([]);

  const inputRef = useRef<HTMLInputElement>(null);

  // 입/출금 처리하는 함수
  // transact: 거래하다, trasction: 거래
  const transact = (mod: "deposit" | "withdraw") => {
    // 입금이면 양수, 출금이면 음수
    let money = 0;
    if (inputRef.current) {
      const value = inputRef.current.value;
      money = mod === "deposit" ? +value : -value;
    }

    if (mod === "deposit") {
      // 입출금 이력 state에 입력값을 추가
      setLogs([money, ...logs]);
    } else {
      // 출금일 때
      // 출금이 가능하면
      if (check(logs, money)) {
        // 입출금 이력 state에 입력값을 추가
        setLogs([money, ...logs]);
      } else {
        alert("잔액이 부족합니다.");
      }
    }

    // 값 비우기
    inputRef.current && (inputRef.current.value = "");
  };

  // 출금할 수 있는지 확인하는 함수
  // 현재 입출금기록, 출금금액을 매개변수로 받음
  const check = (logs: number[], money: number) => {
    let sum = 0;
    // 입출금 이력이 있으면 잔액을 합산
    if (logs.length > 0) {
      // 입출금 이력으로 잔액 합산
      sum = logs.reduce((acc, log) => acc + log);
    }

    // 잔액 + (-출금액) >= 0 : true
    // 잔액 + (-출금액) < 0 : false
    return sum + money >= 0;
  };

  return (
    <div>
      <h2>Account Manager</h2>
      {/* 입력박스 1개, 금액 */}
      <input type="text" ref={inputRef} placeholder="금액을 입력해주세요" />
      <button
        onClick={() => {
          transact("deposit");
        }}
      >
        입금
      </button>
      <button
        onClick={() => {
          transact("withdraw");
        }}
      >
        출금
      </button>
      {
        // 입출금 이력이 있을 때만
        logs.length > 0 && (
          <span>잔액: {logs.reduce((acc, log) => acc + log)} </span>
        )
      }
      <ul>
        {
          // 입출금 이력을 표시하는 문
          logs.map((money, index) => (
            // 반복적으로 표시되는 요소/컴포넌트에는 key 속성이 필수
            // 렌더링 속도에 영향을 미침
            <ListItem key={index} money={money} />
          ))
        }
      </ul>
    </div>
  );
};

export default AccountManager;
