import { useNavigation } from "@react-navigation/native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import React from "react";
import { View, Text } from "react-native";
import { StackParamList } from "../types/ParamList";

export default function Detail() {
  const { route } =
    useNavigation<NativeStackScreenProps<StackParamList, "Detail">>();

  console.log(route.params.id);
  return (
    <View>
      <Text>Detail Component</Text>
    </View>
  );
}
