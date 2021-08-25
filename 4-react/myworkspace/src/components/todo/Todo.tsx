import { useRef, useState } from "react";
import produce from "immer";

import TodoEditModal from "./TodoEditModal";
// ./type.ts/js/tsx가 없으면, ./type/index.ts/js/tsx 로딩함
import { TodoState } from "./type";
import { iteratorSymbol } from "immer/dist/internal";

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Todo = () => {
  console.log("--todowithmodal--");
  const [todoList, setTodoList] = useState<TodoState[]>([
    { id: 2, memo: "Typescript", createTime: new Date().getTime() },
    { id: 1, memo: "React State 연습", createTime: new Date().getTime() },
  ]);

  // 수정 팝업을 띄울지 아닐지
  const [isEdit, setIsEdit] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);
  const ulRef = useRef<HTMLUListElement>(null);

  const add = () => {
    const todo: TodoState = {
      id: todoList.length > 0 ? todoList[0].id + 1 : 1,
      memo: inputRef.current?.value,
      createTime: new Date().getTime(),
    };
    setTodoList(
      produce((state) => {
        state.unshift(todo);
      })
    );

    // 입력값 초기화
    formRef.current?.reset();
  };

  const del = (id: number, index: number) => {
    console.log(id);
    // immer로 state 배열 직접 조작(index로 삭제)
    setTodoList(
      produce((state) => {
        state.splice(index, 1);
      })
    );
  };

  // 컴포넌트가 업데이트 되도 유지시킬 수 있는 변수
  // 수정할 todo객체
  const editItem = useRef<TodoState>({ id: 0, memo: "", createTime: 0 });

  const edit = (item: TodoState) => {
    // 수정할 todo객체
    editItem.current = item;
    // 모달 팝업을 보여주기
    setIsEdit(true);
  };

  const save = (editItem: TodoState) => {
    console.log(editItem);
    setTodoList(
      produce((state) => {
        const item = state.find((item) => item.id === editItem.id);
        if (item) {
          item.memo = editItem.memo;
        }
      })
    );

    // 모달창 닫기
    setIsEdit(false);
  };

  return (
    <>
      <h2 className="text-center my-5">할 일 관리</h2>
      {/* isEdit state가 true일 때만 Modal 창이 보임 */}
      {isEdit && (
        <TodoEditModal
          item={editItem.current}
          onClose={() => {
            setIsEdit(false);
          }}
          onSave={(editItem) => {
            save(editItem);
          }}
        />
      )}
      <form
        className="d-flex"
        ref={formRef}
        onSubmit={(e) => e.preventDefault()}
      >
        <input
          type="text"
          className="form-control me-2"
          placeholder="할 일 ..."
          ref={inputRef}
        />
        <button
          type="button"
          className="btn btn-primary text-nowrap"
          onClick={() => {
            add();
          }}
        >
          추가
        </button>
      </form>
      <ul id="ul-list" className="list-group list-group-flush mt-3" ref={ulRef}>
        {/* 데이터와 UI요소 바인딩 */}
        {todoList.map((item, index) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              <span className="me-1">{item.memo}</span>
              <span style={{ fontSize: "0.75rem" }}>
                - {getTimeString(item.createTime)}
              </span>
            </div>
            <button
              className="btn btn-outline-secondary btn-sm text-nowrap me-1"
              onClick={() => {
                // 수정 모달 팝업 띄우고 데이터 객체 넘겨주기
                edit(item);
              }}
            >
              수정
            </button>
            <button
              className="btn btn-outline-secondary btn-sm text-nowrap"
              onClick={() => {
                del(item.id, index);
              }}
            >
              삭제
            </button>
          </li>
        ))}
      </ul>
    </>
  );
};

export default Todo;
