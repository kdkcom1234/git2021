import { useEffect, useRef, useState } from "react";
import Alert from "../../components/alert/Alert";

import produce from "immer";

// state 1건에 대한 타입
interface TodoItemState {
  id: number;
  memo: string | undefined;
  createdTime: number;
  modifyTime?: number;
  isEdit?: boolean; // 수정모드인지 여부
}

// 서버로 부터 받아오는 데이터 1건에 대한 타입
interface TodoItemReponse {
  id: number;
  memo: string;
  createdTime: number;
}

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Todo = () => {
  // todo 여러건에 대한 state
  // 참고) new Date().getTime() -> unix time 생성됨
  const [todoList, setTodoList] = useState<TodoItemState[]>([]);
  // 데이터 로딩처리 여부를 표시
  const [isLoading, setLoading] = useState<boolean>(true);

  // 빈 값 여부 state
  const [isError, setIsError] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);
  const ulRef = useRef<HTMLUListElement>(null);

  // useEffect: 특정조건일 때 작동하는 코드를 작성할 수 있게하는 React Hook
  // React Hook: 클래스컴포넌트에서만 할 수 있었던 작업을 함수형 컴포넌트에서 사용할 수 있게함
  // -> 클래스컴포넌트 state, 컴포넌트 라이프사이클을 처리할 수 있음(stateful)
  // -> 함수형컴포넌트 다른컴포넌트로부터 받은 prop으로 화면에 렌더링만(stateless)

  // useEffect(이펙트를처리할함수, [의존변수])
  // 의존변수의 값/참조가 바뀔때마나 함수가 처리됨
  // ex) props가 바뀌거나 state가 바뀔때 추가적인 처리를 함.

  // [] 의존변수목록이 빈 배열 -> 컴포넌트 렌더링되고 마운팅된후에 시점에 처리가됨

  // async: 비동기처리를 할 수 있는 함수
  // 코드가 순차적인 처리가 아닌 다른 컨텐스트에 수행될 수 있도록 함
  // await 키워드는 async 함수 안에서만 사용가능함
  // Promise 객체를 반환하는 함수만 await 키워드를 사용할 수 있음.
  const fetchData = async () => {
    // 함수를 호출하고 리턴값을 받는것과 비슷한구조
    // resolve(해결함수)가 실행되기 전까지 대기
    // resolve가 실행되면 resolve함수의 매개변수를 반환함
    const res = await fetch("http://localhost:8080/todos");
    const data: TodoItemReponse[] = await res.json();

    // 서버로부터 받은 데이터를 state 객체로 변환함
    const todos = data.map((item) => ({
      id: item.id,
      memo: item.memo,
      createdTime: item.createdTime,
    })) as TodoItemState[];

    setLoading(false); // 로딩중 여부 state 업데이트
    setTodoList(todos); // todo state 업데이트

    console.log("--2. await fetch completed--");
  };

  useEffect(() => {
    console.log("--1. mounted--");
    // 백엔드에서 데이터를 받아올 것임
    // ES8 style로 async-await 기법을 이용해서 데이터를 조회해옴
    fetchData();
    console.log("--3. completed--");
  }, []);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    // 이벤트 객체가 있을 때는 입력박스에서 엔터 입력
    if (e) {
      if (e.code !== "Enter") return;
    }

    // 입력값이 없으면 에러 메시지 표시
    if (!inputRef.current?.value) {
      setIsError(true);
      return;
    }

    const todo: TodoItemState = {
      id: todoList.length > 0 ? todoList[0].id + 1 : 1,
      // optional chaning
      memo: inputRef.current?.value,
      createdTime: new Date().getTime(),
    };

    // console.log(todoList);
    // immer 없이 새로운 배열을 생성하여 state 변경
    // setTodoList([todo, ...todoList]);

    // current state -> draft state -> next state
    // draft state를 조작함
    setTodoList(
      // produce(([draftstate변수]) => {draft state 변수 조작})
      // 반환 객체는 변경된 state(next state)
      produce((state) => {
        // draft state 배열에 추가
        // draft state의 타입은 TodoItemState[]
        state.unshift(todo);
      })
    );

    // 입력값 초기화
    formRef.current?.reset();
    // 에러 메시지 제거
    setIsError(false);
  };

  const del = (id: number, index: number) => {
    console.log(id);

    // 불변성 때문에 splice를 사용할 수 없음
    // 주로 filter 함수를 사용
    // filter 함수로 해당 id를 제외하고 새로운 배열로 리턴함.
    // immer 없이 사용
    // setTodoList(todoList.filter((item) => item.id !== id));

    // immer로 state 배열 직접 조작
    // setTodoList(
    //   produce((state) => {
    //     // id로 해당 item을 찾음
    //     const item = state.find((item) => item.id === id);
    //     if (item) {
    //       // 해당 item의 index로 배열에서 삭제
    //       state.splice(state.indexOf(item), 1);
    //     }
    //   })
    // );

    // immer로 state 배열 직접 조작(index로 삭제)
    setTodoList(
      produce((state) => {
        state.splice(index, 1);
      })
    );
  };

  const edit = (id: number, mod: boolean) => {
    // 해당 id에 해당하는 item만 edit 모드로 변경함
    // 해당 item의 속성을 변경한 후 변경된 item을 반환
    // map 함수는 새로운 배열을 반환하는 함수, 배열길이는 기존 배열 길이와 같음
    // immer 없이 사용
    // setTodoList(
    //   todoList.map((item) => {
    //     if (item.id === id) {
    //       item.isEdit = mod;
    //     }
    //     return item;
    //   })
    // );
    // immer를 사용해서 해당 요소를 변경
    setTodoList(
      produce((state) => {
        // 해당 id값에 해당하는 요소를 찾음
        const item = state.find((item) => item.id === id);
        if (item) {
          item.isEdit = mod;
        }
      })
    );
  };

  const save = (id: number, index: number) => {
    console.log(ulRef.current);
    console.log(index);

    // ul 밑에 있는 입력박스중에서 index번째 입력박스만 선택
    // 버그: item index와 input index가 일치하지 않음, input박스는 안 열렸을 수도 있기 때문
    // const input = ulRef.current?.querySelectorAll("input")[index];

    // 2021.08.28 버그 수정
    // ul > li[index] 밑에 input박스를 찾음
    const input = ulRef.current
      ?.querySelectorAll("li")
      [index].querySelector("input");
    // console.log(li);
    console.log(input);

    // immer 없이 map함수로 새로운 배열을 반환 후 변경
    // setTodoList(
    //   todoList.map((item) => {
    //     // 해당 id의 item의 값을 변경
    //     if (item.id === id) {
    //       item.memo = input?.value;
    //       item.modifyTime = new Date().getTime();
    //       item.isEdit = false;
    //     }

    //     return item;
    //   })
    // );

    // immer를 사용하여 해당 요소를 직접변경
    setTodoList(
      produce((state) => {
        const item = state.find((item) => item.id === id);
        if (item) {
          item.memo = input?.value;
          item.modifyTime = new Date().getTime();
          item.isEdit = false;
        }
      })
    );
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">할 일 관리</h2>
      <form
        className="d-flex"
        ref={formRef}
        /* 
          event.preventDefault(); 
          - 기본이벤트 작업을 처리하지 않음 
          - submit form
        */
        onSubmit={(e) => e.preventDefault()}
      >
        <input
          type="text"
          className="form-control me-2"
          placeholder="할 일 ..."
          ref={inputRef}
          onKeyPress={(e) => {
            add(e);
          }}
        />
        <button
          type="button"
          className="btn btn-primary text-nowrap"
          onClick={() => {
            add(null);
          }}
        >
          추가
        </button>
      </form>
      {isError && (
        <Alert
          message={"내용을 입력해주세요."}
          variant={"danger"}
          // 닫기 버튼을 클릭할 때 처리하는 함수를 넘김
          onClose={() => {
            setIsError(false);
          }}
        />
      )}

      <ul id="ul-list" className="list-group list-group-flush mt-3" ref={ulRef}>
        {/* 로딩중 처리 표시 */}
        {isLoading && (
          <li className="list-group-item text-center">
            <div className="spinner-border text-primary" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
          </li>
        )}
        {/* 빈 데이터 표시 */}
        {!isLoading && todoList.length === 0 && (
          <li className="list-group-item">데이터가 없습니다.</li>
        )}
        {/* 데이터와 UI요소 바인딩 */}
        {todoList.map((item, index) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              {/* 보기모드일 때 보이는 내용 */}
              {!item.isEdit && <span className="me-1">{item.memo}</span>}
              {!item.isEdit && (
                <span style={{ fontSize: "0.75rem" }}>
                  -{" "}
                  {getTimeString(
                    item.modifyTime ? item.modifyTime : item.createdTime
                  )}
                </span>
              )}
              {/* 수정모드일 때 보이는 입력폼 */}
              {item.isEdit && (
                <input type="text" className="w-100" defaultValue={item.memo} />
              )}
            </div>
            {/* 보기모드일 때 보이는 버튼 */}
            {!item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm ms-2 me-1 text-nowrap"
                onClick={() => {
                  edit(item.id, true);
                }}
              >
                수정
              </button>
            )}
            {!item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm text-nowrap"
                onClick={() => {
                  del(item.id, index);
                }}
              >
                삭제
              </button>
            )}
            {/* 수정모드일 때 보이는 버튼 */}
            {item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm ms-2 me-1 text-nowrap"
                onClick={() => {
                  save(item.id, index);
                }}
              >
                저장
              </button>
            )}
            {item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm text-nowrap"
                onClick={() => {
                  edit(item.id, false);
                }}
              >
                취소
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Todo;
