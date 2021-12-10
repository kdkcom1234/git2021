// 각 컴포넌트별(스크린)로 이동할 때 전달하는 매개변수를 정의
type StackParamList = {
  Product: undefined;
  Detail: { id: string };
};

export type { StackParamList };
