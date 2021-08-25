// 1건에 대한 타입
interface TodoState {
  id: number;
  memo: string | undefined;
  createTime: number;
}

export type { TodoState };
