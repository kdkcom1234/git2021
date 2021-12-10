import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import React from "react";
import { Pressable, Text, View } from "react-native";
import { StackParamList } from "../types/ParamList";

export default function Product() {
  const navigation = useNavigation<NativeStackNavigationProp<StackParamList>>();
  console.log(navigation);
  return (
    <View>
      <Text>Product Component</Text>
      <Pressable
        onPress={() => {
          navigation.navigate("Detail", { id: "1" });
        }}
      >
        <Text>Go To Product Detail</Text>
      </Pressable>
    </View>
  );
}
