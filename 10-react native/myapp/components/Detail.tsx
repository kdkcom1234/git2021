import { RouteProp, useRoute } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import React, { useEffect, useState } from "react";
import { View, Text, StyleSheet } from "react-native";
import { Button, Card, Icon } from "react-native-elements";
import { PRODUCTS } from "../data/products";
import { StackParamList } from "../types/ParamList";

import { colors } from "../styles";
import { API_BASE } from "../_env";

export default function Detail() {
  const route = useRoute<RouteProp<StackParamList>>();
  console.log(route.params?.id);
  const id = route.params?.id as string;

  const [item, setItem] = useState<typeof PRODUCTS[0] | undefined>(undefined);

  useEffect(() => {
    const getItem = async (id: number) => {
      const result = await fetch(`${API_BASE}/products/${id}`);
      const data = await result.json();

      setItem(data);
    };

    getItem(+id);
  }, []);

  return (
    <View style={styles.container}>
      {item && (
        <Card containerStyle={styles.cardContainer}>
          <Card.Title>{item.title}</Card.Title>
          <Card.Divider />
          <Card.Image source={{ uri: item.image }} />
          <Card.Divider />
          <Text style={styles.description}>{item.description}</Text>
          <Button
            icon={<Icon name="heart" type="ionicon" color={colors.light} />}
            title=" ADD TO FAVORITES"
            buttonStyle={styles.favoriteButton}
          ></Button>
        </Card>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  cardContainer: {
    width: "100%",
  },
  description: {
    marginBottom: 10,
  },
  favoriteButton: {
    borderRadius: 0,
    marginLeft: 0,
    marginRight: 0,
    marginBottom: 0,
    backgroundColor: colors.primary,
    color: colors.light,
  },
});
