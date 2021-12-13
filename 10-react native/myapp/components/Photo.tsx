import React, { useState } from "react";
import { View, StyleSheet, Image } from "react-native";
import { FAB } from "react-native-elements";
import Ionicons from "@expo/vector-icons/Ionicons";
import { colors } from "../styles";

import * as ImagePicker from "expo-image-picker";

export default function Photo() {
  const [image, setImage] = useState("");
  const [photoList, setPhotoList] = useState<string[]>([]);

  const pickCamera = async () => {
    let result = await ImagePicker.launchCameraAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      // allowsEditing: true,
      // aspect: [4, 3],
      quality: 1,
    });

    console.log(result);

    if (!result.cancelled) {
      setPhotoList([result.uri, ...photoList]);
      // setImage(result.uri);
    }
  };

  const pickImage = async () => {
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      // allowsEditing: true,
      // aspect: [4, 3],
      quality: 1,
    });

    console.log(result);

    if (!result.cancelled) {
      setPhotoList([result.uri, ...photoList]);
      // setImage(result.uri);
    }
  };

  return (
    <View style={styles.container}>
      {photoList.length === 0 ? (
        <></>
      ) : (
        photoList.map((photo, index) => (
          <Image
            key={`photo-${index}`}
            style={{ width: "25%", height: "15%" }}
            source={{ uri: photo }}
          />
        ))
      )}
      <FAB
        onPress={() => {
          pickCamera();
        }}
        buttonStyle={styles.addButton}
        style={{ marginBottom: 80 }}
        placement="right"
        title={<Ionicons name="camera" color={styles.addButton.color} />}
      />
      <FAB
        onPress={() => {
          pickImage();
        }}
        buttonStyle={styles.addButton}
        placement="right"
        title={<Ionicons name="image" color={styles.addButton.color} />}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "flex-start",
    alignItems: "flex-start",
  },
  addButton: {
    backgroundColor: colors.primary,
    color: colors.light,
  },
});
