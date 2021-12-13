import React from "react";
import { useNavigation } from "@react-navigation/native";
import { View, Text, StyleSheet } from "react-native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { StackParamList } from "../types/ParamList";
import { Button } from "react-native-elements";

export default function Home() {
  const navigation = useNavigation<NativeStackNavigationProp<StackParamList>>();
  console.log(navigation);

  return (
    <View style={styles.container}>
      <Text>Home Component</Text>
      <Button
        title="Go To Detail"
        onPress={() => {
          navigation.navigate("Detail", { id: "1" });
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});
