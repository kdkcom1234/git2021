import { PrismaClient, todo } from ".prisma/client";
import axios from "axios";
import { MutableRefObject, useRef, useState } from "react";
import { toSerializable } from "../lib/json";
import produce from "immer";

const Home = ({ todos }: { todos: todo[] }) => {
  const [todoList, setTodoList] = useState<todo[]>(todos);

  const memoRef = useRef() as MutableRefObject<HTMLInputElement>;

  const handleClick = async () => {
    const todoData = toSerializable<todo>({
      id: BigInt(0),
      created_time: BigInt(0),
      memo: memoRef.current.value,
    });
    const result = await axios.post<todo>("/api/todos", todoData);

    setTodoList([result.data, ...todoList]);

    memoRef.current.value = "";
  };

  return (
    <div>
      <input type="text" ref={memoRef} />{" "}
      <button
        onClick={() => {
          handleClick();
        }}
      >
        ADD
      </button>
      <ul>
        {todoList.map((item) => (
          <li key={`todo-${item.id}`}>{item.memo}</li>
        ))}
      </ul>
    </div>
  );
};

export async function getServerSideProps() {
  const prisma = new PrismaClient();
  const todos = await prisma.todo.findMany({ orderBy: { id: "desc" } });

  return { props: { todos: toSerializable<typeof todos>(todos) } };
}

export default Home;
