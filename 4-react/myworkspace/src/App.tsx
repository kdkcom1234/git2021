import Header from "./components/Header";
import Button from "./components/Button";
import Counter from "./components/Counter";
import Calculator from "./components/CalculatorRef";
import Generator from "./components/Generator";
import AccountManager from "./components/AccountManagerRef";
import Hello from "./components/Hello";

// React == 컴포넌트 개발 라이브러리
function App() {
  return (
    // main container
    <div style={{ width: "500px", margin: "0 auto" }}>
      {/* JSX 내부에서 주석 달기 */}
      {/* 재사용하지 않는 컴포넌트 */}
      {/* <h1 style={{ color: "red" }}>Hello React with Typescript !</h1> */}

      {/* 속성값을 변경하여 재사용하는 컴포넌트 */}
      {/* Component의 속성(prop)을 넘김 */}
      {/* 속성명={속성값} */}
      <Header color={"red"} title={"React"} />
      <Header color={"green"} title={"Typescript"} />
      <Header color={"blue"} title={"Function Component"} />

      {/* <Button color={"black"} backgroundColor={"red"} text={"Delete"} />
      <Button color={"black"} backgroundColor={"green"} text={"Done"} /> */}
      <Button variant={"primary"} text={"Add"} />
      <Button variant={"secondary"} text={"Delete"} />
      <Button variant={"warning"} text={"Delete"} />

      <Counter />
      <Calculator />
      <Generator />
      <AccountManager />
      <Hello />
    </div>
  );
}

// App.tsx 모듈의 기본 내보내기를 App 함수로 함
export default App;
