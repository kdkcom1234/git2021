// 1건에 대한 타입
interface TodoItemState {
  id: number;
  memo: string | undefined;
  createTime: number;
}

export type { TodoItemState };
