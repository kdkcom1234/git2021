// https://react.vlpt.us/styling/02-css-module.html
// css module
// 파일명.module.css
// css를 사용하는 컴포넌트 범위로 css class 사용범위를 좁힐 수 있음.

import "./App.scss";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Suspense, lazy } from "react";

import Home from "./components/Home";
import Navigation from "./Navigation";

// SPA(Single Page Application)
// : 페이지 파일이 1개, index.html
// : 특정 영역(Switch)에 컴포넌트(js)를 로딩함
// : 애플리케이션이 컴파일될 때 import한 컴포넌트가 같이 컴파일됨
//   -> 컴파일됐을 때 파일크기가 커짐, 초기 로딩할 때 시간 걸림

// Lazy-Loading 처리
// 컴포넌트를 방문하는 시점에 로딩함
const Counter = lazy(() => import("./components/Counter"));
const Calculator = lazy(() => import("./components/CalculatorRef"));
const Generator = lazy(() => import("./components/Generator"));
const AccountManager = lazy(() => import("./components/AccountManagerRef"));
const Components = lazy(() => import("./components/Components"));
const BootStrap = lazy(() => import("./components/Bootstrap"));

// React == 컴포넌트 개발 라이브러리
function App() {
  return (
    <Router>
      {/* main container */}
      <div style={{ width: "900px" }} className="mx-auto">
        <nav
          style={{ width: "200px", height: "100vh", top: "20px" }}
          className="position-fixed"
        >
          <Navigation />
        </nav>
        <main style={{ marginLeft: "200px", marginTop: "20px" }}>
          {/* Suspense 컴포넌트로 로딩중에 보여줄 화면을 처리하는 것 */}
          {/* fallback={로딩중에 보여줄 컴포넌트} */}
          <Suspense fallback={<div>Loading...</div>}>
            <Switch>
              {/* Switch 영역에 컴포넌트가 로딩됨 */}

              {/* 해당 경로에 대해서 로딩할 컴포넌트 목록을 작성 */}
              <Route path="/" component={Home} exact />
              <Route path="/components" component={Components} />
              <Route path="/counter" component={Counter} />
              <Route path="/calculator" component={Calculator} />
              <Route path="/generator" component={Generator} />
              <Route path="/account-manager" component={AccountManager} />
              <Route path="/bootstrap" component={BootStrap} />
            </Switch>
          </Suspense>
        </main>
      </div>
    </Router>
  );
}

// App.tsx 모듈의 기본 내보내기를 App 함수로 함
export default App;
