import React, { useEffect, useState } from "react";
import { nanoid } from "nanoid/non-secure";
import {
  StyleSheet,
  View,
  Text,
  TextInput,
  Button,
  FlatList,
  Pressable,
} from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function Todo() {
  const [todoList, setTodoList] = useState<{ id: string; memo: string }[]>([]);

  const [memo, setMemo] = useState("");

  const handleAdd = async () => {
    // 저장할 배열객체
    const newTodoList = [{ id: nanoid(), memo }, ...todoList];

    // JSON 문자열로 변환해서 저장
    await AsyncStorage.setItem("todoList", JSON.stringify(newTodoList));

    // state를 업데이트함
    setTodoList(newTodoList);
    setMemo("");
  };

  const handleRemove = async (id: string) => {
    // 저장할 배열객체
    const newTodoList = todoList.filter((item) => item.id !== id);
    // JSON 문자열로 변환해서 저장
    await AsyncStorage.setItem("todoList", JSON.stringify(newTodoList));

    // state를 업데이트함
    setTodoList(newTodoList);
    setMemo("");
  };

  useEffect(() => {
    const getItem = async () => {
      const todoJson = await AsyncStorage.getItem("todoList");
      console.log(todoJson);
      todoJson && setTodoList(JSON.parse(todoJson));
    };

    getItem();
  }, []);

  return (
    <View style={styles.container}>
      {/* View: div 태그 */}
      <View style={styles.form}>
        <TextInput
          placeholder="memo..."
          style={styles.input}
          onChangeText={(val) => {
            setMemo(val);
          }}
          value={memo}
        ></TextInput>
        <Pressable
          style={styles.button}
          onPress={() => {
            handleAdd();
          }}
        >
          <Text style={styles.buttonText}>ADD</Text>
        </Pressable>
      </View>
      <View style={styles.list}>
        {/* Scrollview: scrolle이 있는 div 태그 */}
        {/* 다량의 컴포넌트가 보여야하는 반복적인 리스트 구조는 FlatList 권장(성능) */}
        <FlatList
          data={todoList}
          renderItem={({ item }) => (
            <Text
              onPress={() => {
                handleRemove(item.id);
              }}
              key={`todo-item-${item.id}`}
            >
              {item.memo}
            </Text>
          )}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    // flex: 1 -> 자식요소 주축으로 자동 늘리기, 교차축 자동늘리기, 기본크기는 없음
    flex: 1,
    // borderColor: "red",
    // borderWidth: 1,
    alignItems: "center",
    justifyContent: "center",
  },
  form: {
    flexBasis: "100%",
    flexDirection: "row",
    alignItems: "flex-end",
    justifyContent: "center",
    // borderColor: "green",
    // borderWidth: 1,
  },
  input: {
    height: 40,
    width: "50%",
  },
  button: {
    padding: 5,
    backgroundColor: "#03045e",
  },
  buttonText: {
    fontWeight: "bold",
    color: "#d0d0d0",
  },
  list: {
    flexGrow: 1,
    flexDirection: "column",
    alignItems: "flex-start",
    justifyContent: "flex-start",
    // borderColor: "blue",
    // borderWidth: 1,
  },
});
