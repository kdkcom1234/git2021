// Calculator 컴포넌트

import { useState } from "react";

// 1. 버튼을 클릭하면 prompt로 입력값을 두번 받음
// a = prompt, b = prompt

// 2. 그다음에 연산자를 prompt로 받음
// +, -, *, /

// 3. 입력값 두개에 대한 연산 결과를 화면 출력
// state를 사용해야함

// 예) 입력값1: 10
//     입력값2: 5
//     연산자: *
//     결과값: 50
//     <div>50</div>

const Calculator = () => {
  const [result, setResult] = useState(0);
  const calculate = () => {
    const a = prompt("첫번째 숫자");
    const b = prompt("두번째 숫자");
    const op = prompt("연산자, (+, -, *, /)");

    console.log(`${a}${op}${b}`);

    // eval(문자열)
    // 문자열이 자바스크립트코드로 실행할 수 있으면 실행
    // const code = `alert(${a}${op}${b})`;
    // eval(code);
    // eslint-disable-next-line
    setResult(eval(`${a}${op}${b}`));

    // state 값에 변동이 없으면 컴포넌트를 업데이트하지 않음
    // 기존 result == 20
    // 변동 result == 20, 컴포넌트를 업데이트하지 않음
  };

  return (
    <div>
      <h2>Calculator</h2>
      <button
        onClick={() => {
          calculate();
        }}
      >
        Start
      </button>
      <div>{result}</div>
    </div>
  );
};

export default Calculator;
