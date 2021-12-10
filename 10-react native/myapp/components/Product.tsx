import { useNavigation } from "@react-navigation/native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import React from "react";
import { Text, View } from "react-native";
import { StackParamList } from "../types/ParamList";

export default function Product() {
  const { navigation, route } =
    useNavigation<NativeStackScreenProps<StackParamList, "Product">>();

  return (
    <View>
      <Text>Product Component</Text>
    </View>
  );
}
