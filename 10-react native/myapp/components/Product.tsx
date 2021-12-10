import { useNavigation } from "@react-navigation/native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import React from "react";
import { Text, View } from "react-native";
import { StackParamList } from "../types/ParamList";

export default function Product() {
  const { navigation } =
    useNavigation<NativeStackScreenProps<StackParamList, "Product">>();

  navigation.navigate("Detail", { id: "1234" });

  return (
    <View>
      <Text>Product Component</Text>
    </View>
  );
}
