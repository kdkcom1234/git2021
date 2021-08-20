import Header from "./Header";
import Button from "./Button";

const Components = () => {
  return (
    <div>
      {/* JSX 내부에서 주석 달기 */}
      {/* 재사용하지 않는 컴포넌트 */}
      {/* <h1 style={{ color: "red" }}>Hello React with Typescript !</h1> */}

      {/* 속성값을 변경하여 재사용하는 컴포넌트 */}
      {/* Component의 속성(prop)을 넘김 */}
      {/* 속성명={속성값} */}
      <Header color={"red"} title={"React"} />
      <Header color={"green"} title={"Typescript"} />
      <Header color={"blue"} title={"Function Component"} />

      <Button variant={"primary"} text={"Add"} />
      <Button variant={"secondary"} text={"Delete"} />
      <Button variant={"warning"} text={"Delete"} />
    </div>
  );
};

export default Components;
