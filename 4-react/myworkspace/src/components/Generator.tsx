// Generator

import { useState } from "react";

// 숫자 값을 랜덤 -50 ~ 50 범위로 생성하고
// 배열 state에 추가
// 숫자 목록을 ul > li로 출력

// 기존 javascript
// DOM요소를 직접적으로 조작

// React
// Virtual DOM 요소와 관련된
// state 또는 prop를 조작

// 세부 컴포넌트
const ListItem = ({ num }: { num: number }) => {
  const color = num < 0 ? "red" : "green";
  return <li style={{ color: color }}>{num}</li>;
};

const Generator = () => {
  // useState<타입>
  // state의 타입을 지정해줄 수 있음
  const [numbers, setNumber] = useState<number[]>([]);

  const generate = () => {
    const num = Math.trunc(Math.random() * 100 - 50);

    // primitive type(원시타입): number, string, boolean
    // 값이 바뀌어야만 다시 렌더링함

    // reference type(참조타입): object, array
    // 참조가 바뀌어야만 다시 렌더링함
    // object -> 새로운 객체를 생성하고 state변경함수로 변경함
    // array -> 새로운 배열을 생성하고 state변경함수로 변경함

    // // numbers 배열 참조가 같으므로 변경이 일어나지 않음
    // numbers.push(num);
    // setNumber(numbers);

    // [0, 1, 2, 3]

    // []: 새로운 배열 생성
    // []

    // [...numbers]: 기존 배열 복사, ... 나열 연산
    // [0, 1, 2, 3]

    // [num, ...numbers]
    // [-17, 0, 1, 2, 3]

    // 새로운 배열에 첫번째요소로 num값, 나머지는 기존 배열
    setNumber([num, ...numbers]);

    // // 새로운 배열에 마지막요소로 num값, 나머지는 기존 배열
    // setNumber([...numbers, num]);
  };

  return (
    <div>
      <h2>Generator</h2>
      <button
        onClick={() => {
          generate();
        }}
      >
        GENERATE
      </button>
      <div>{numbers}</div>
      {/* JSX 내부에서는 중괄호로 코드를 침 */}
      <ul>
        {
          // JSX 내부에서는 한줄짜리 코드(식, expression)만 가능함.
          // 세미콜론(;)을 한번만 쓸 수 있는 코드
          // map: 기존 배열크기와 동일하나 요소가 변경된 배열을 반환
          // 숫자배열 -> JSX배열로 변환
          numbers.map(
            (num, index) => {
              // 세부 컴포넌트로 분리하여 처리
              // console.log(index);
              return <ListItem key={index} num={num} />;
            }
            // // 조건부 렌더링(conditional rendering)
            // num < 0 ? (
            //   <li style={{ color: "red" }} key={index}>
            //     {num}
            //   </li>
            // ) : (
            //   <li style={{ color: "green" }} key={index}>
            //     {num}
            //   </li>
            // )
          )
        }
      </ul>
    </div>
  );
};

export default Generator;
