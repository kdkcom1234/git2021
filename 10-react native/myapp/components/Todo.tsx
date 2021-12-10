import React from "react";
import {
  StyleSheet,
  View,
  Text,
  TextInput,
  Button,
  FlatList,
} from "react-native";

export default function Todo() {
  return (
    <View style={styles.container}>
      {/* View: div 태그 */}
      <View style={styles.form}>
        <TextInput placeholder="memo..." style={{ height: 40 }}></TextInput>
        <Button title="ADD" onPress={() => {}} />
        {/* Scrollview: scrolle이 있는 div 태그 */}
        {/* 다량의 컴포넌트가 보여야하는 반복적인 리스트 구조는 FlatList 권장(성능) */}
      </View>
      <View style={styles.list}>
        <FlatList
          data={[
            { id: 1, memo: "rn 개발" },
            { id: 2, memo: "expo 문서확인" },
          ]}
          renderItem={({ item }) => <Text>{item.memo}</Text>}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    // flex: 1 -> 자식요소 주축으로 자동 늘리기, 교차축 자동늘리기, 기본크기는 없음
    flex: 1,
    backgroundColor: "#fff",
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
  list: {
    flexGrow: 1,
    flexDirection: "column",
    alignItems: "flex-start",
    justifyContent: "flex-start",
    // borderColor: "blue",
    // borderWidth: 1,
  },
});
