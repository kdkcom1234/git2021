import { RouteProp, useRoute } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import React from "react";
import { View, Text } from "react-native";
import { StackParamList } from "../types/ParamList";

export default function Detail() {
  const route = useRoute<RouteProp<StackParamList>>();
  console.log(route.params?.id);
  return (
    <View>
      <Text>id: {route.params?.id}</Text>
    </View>
  );
}
