import React from "react";
import { render, screen } from "@testing-library/react";
import App from "./App";

{
  /* <a href="https://reactjs.com">Learn React</a> */
}

test("renders learn react link", () => {
  // 컴포넌트를 렌더링함
  render(<App />);
  // 화면에서 특정텍스트(id, class, 특정속성)로 엘리먼트찾음
  const linkElement = screen.getByText(/learn react/i);
  // 찾은 엘리먼트가 렌더링된 화면에 있나
  expect(linkElement).toBeInTheDocument();
});
