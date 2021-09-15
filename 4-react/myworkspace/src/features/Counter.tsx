// Counter 컴포넌트
// state(상태)

import { useState } from "react";

// 화면에 먼가 변경을 일으켜야함
// -> 내부에서 처리 state, 외부에서 온다 prop

const Counter = () => {
  // const [state명, state변경함수명] = useState(초깃값);

  // prop: 화면에 렌더링결과에 영향을 미치는 변수, 외부에서 매개변수로 옴
  // state: 화면에 렌더링결과에 영향을 미치는 변수, 내부에서 선언함

  // state변수는 변경불가(immutable, 불변성)
  // count = 1; // 직접적인 변경불가
  // setCount(count + 1); // state 변경함수로만 변경할 수 있음

  // state 변경함수로 state를 변경했을 때만 컴포넌트를 업데이트함(다시그림)
  // state 변경함수를 실행하면 state가 있는 컴포넌트가 모두 다시 그려짐
  // -> 실제로는 변동사항이 있는 부분만 다시 그림
  // 기존 virtual dom 객체와 변동된 virtual dom 객체를 비교함
  // 같으면 다시 그리지 않음.

  const [count, setCount] = useState(0);

  const increase = () => {
    // state변경함수(변경할값)
    setCount(count + 1);
  };

  return (
    <div>
      <h2>Counter</h2>
      <div>
        {/* button에 addEventListener("click", ()=>{}) 같음 */}
        <button
          onClick={() => {
            increase();
          }}
        >
          COUNT
        </button>
        <span>{count}</span>
      </div>
    </div>
  );
};

export default Counter;
