import axios from "axios";
import { MutableRefObject, useRef, useState } from "react";
import { todo } from "@prisma/client";
import { prisma } from "../lib/db";
import { toSerializable } from "../lib/json";

const Home = ({ todos }: { todos: todo[] }) => {
  const [todoList, setTodoList] = useState<todo[]>(todos);

  const memoRef = useRef() as MutableRefObject<HTMLInputElement>;

  const handleAddClick = async () => {
    const todoData = toSerializable<todo>({
      id: BigInt(0),
      created_time: BigInt(0),
      memo: memoRef.current.value,
    });
    const result = await axios.post<todo>("/api/todos", todoData);

    setTodoList([result.data, ...todoList]);

    memoRef.current.value = "";
  };

  const handleRemoveClick = async (id: bigint) => {
    const result = await axios.delete<string>(`/api/todos/${id}`);
    console.log(result.data);

    setTodoList(todoList.filter((item) => item.id !== id));
  };

  return (
    <div>
      <input type="text" ref={memoRef} />
      <button
        onClick={() => {
          handleAddClick();
        }}
      >
        ADD
      </button>
      <ul>
        {todoList.map((item) => (
          <li key={`todo-${item.id}`}>
            {item.memo}
            <button
              onClick={() => {
                handleRemoveClick(item.id);
              }}
            >
              X
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export async function getServerSideProps() {
  const todos = await prisma.todo.findMany({ orderBy: { id: "desc" } });

  return { props: { todos: toSerializable<typeof todos>(todos) } };
}

export default Home;
