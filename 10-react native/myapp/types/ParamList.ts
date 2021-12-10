// 각 컴포넌트별로 이동할 때 전달하는 매개변수를 정의
type StackParamList = {
  Home: undefined;
  Todo: undefined;
  Product: undefined;
  Detail: { id: string };
  Favorite: undefined;
};

export type { StackParamList };
