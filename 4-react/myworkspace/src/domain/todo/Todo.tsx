import { useRef, useState } from "react";
import produce from "immer";

import TodoEditModal from "./TodoEditModal";
// ./type.ts/js/tsx가 없으면, ./type/index.ts/js/tsx 로딩함
import { TodoItemState } from "./type";
import { useSelector } from "react-redux";
import { RootState } from "../../store";

const getTimeString = (unixtime: number) => {
  // 1초: 1000
  // 1분: 60 * 1000
  // 1시간: 60 * 60 * 1000
  // 1일: 24 * 60 * 60 * 1000
  const day = 24 * 60 * 60 * 1000;

  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);

  // 현재시간보다 24시간 이전이면 날짜를 보여주고
  // 현재시간보다 24시간 미만이면 시간을 보여줌
  return unixtime - new Date().getTime() >= day
    ? dateTime.toLocaleDateString()
    : dateTime.toLocaleTimeString();
};

const Todo = () => {
  // profile state를 가져옴 + state가 변경되면 컴포넌트를 업데이트(diff+render)함
  const profile = useSelector((state: RootState) => state.profile);

  const [todoList, setTodoList] = useState<TodoItemState[]>([
    {
      id: 2,
      memo: "Typescript",
      username: profile.username,
      createTime: new Date().getTime(),
    },
    {
      id: 1,
      memo: "React State 연습",
      username: profile.username,
      createTime: new Date().getTime(),
    },
  ]);

  // 수정 팝업을 띄울지 아닐지
  const [isEdit, setIsEdit] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);
  const ulRef = useRef<HTMLUListElement>(null);

  const add = () => {
    const todo: TodoItemState = {
      id: todoList.length > 0 ? todoList[0].id + 1 : 1,
      memo: inputRef.current?.value,
      username: profile.username,
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
  const editItem = useRef<TodoItemState>({
    id: 0,
    memo: "",
    username: profile.username,
    createTime: 0,
  });

  const edit = (item: TodoItemState) => {
    // 수정할 todo객체
    editItem.current = item;
    // 모달 팝업을 보여주기
    setIsEdit(true);
  };

  const save = (editItem: TodoItemState) => {
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
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">할 일 관리</h2>
      {/* profile 정보 확인용 */}
      {/* <div>
        <img
          src={profile.image}
          width={150}
          height={100}
          alt={profile.username}
        />
        <span>{profile.username}</span>
      </div> */}
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
                - {item.username}, {getTimeString(item.createTime)}
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
    </div>
  );
};

export default Todo;
