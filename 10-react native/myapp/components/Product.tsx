import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import React from "react";
import { FlatList, Pressable, Text, View } from "react-native";
import { StackParamList } from "../types/ParamList";

import { PRODUCTS } from "../data/products";
import { Avatar, ListItem } from "react-native-elements";

function ProductItem({ item }: { item: typeof PRODUCTS[0] }) {
  return (
    <ListItem>
      {/* 로컬 컨텐츠 표치, RN의 Image 컴포넌트 주소 넣는방법과 같음 */}
      {/* <Avatar source={require("../assets/favicon.png")}></Avatar> */}
      {/* 로컬 컨텐츠 표치, RN의 Image 컴포넌트 주소 넣는방법과 같음  */}
      <Avatar source={{ uri: item.image }}></Avatar>
      <ListItem.Content>
        <ListItem.Title>{item.title}</ListItem.Title>
        <ListItem.Subtitle>{item.subtitle}</ListItem.Subtitle>
      </ListItem.Content>
    </ListItem>
  );
}

export default function Product() {
  // useNavigation<내비게이션속성타입<매개변수타입>>
  const navigation = useNavigation<NativeStackNavigationProp<StackParamList>>();
  console.log(navigation);

  return (
    <View>
      <FlatList
        keyExtractor={(item) => `product-${item.id.toString()}`}
        data={PRODUCTS}
        // renderItem -> React Component: JSX Elment를 반환하는 함수
        // React Component에 prop으로 {item} 넘겨줌, item이 data속성의 배열에 1개 객체
        renderItem={({ item }) => (
          <Pressable
            onPress={() => {
              navigation.navigate("Detail", { id: item.id });
            }}
          >
            <ProductItem item={item} />
          </Pressable>
        )}
      />
    </View>
  );
}
