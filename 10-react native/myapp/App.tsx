import {
  NavigationContainer,
  ParamListBase,
  RouteProp,
} from "@react-navigation/native";
import { StatusBar } from "expo-status-bar";
import React from "react";
import { SafeAreaProvider } from "react-native-safe-area-context";

import {
  BottomTabNavigationOptions,
  createBottomTabNavigator,
} from "@react-navigation/bottom-tabs";

// 탭별 컴포넌트 import
import Home from "./components/Home";
import Todo from "./components/Todo";
import Product from "./components/Product";
import Favorite from "./components/Favorite";

// 스택 컴포넌트 import
import Detail from "./components/Detail";

// https://ionic.io/ionicons
import Ionicons from "@expo/vector-icons/Ionicons";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

// 탭 네비게이터 생성
// createBottomTabNavigator() => 탭내비게이터 함수 컴포넌트를 반환
// 함수안에서 함수를 반환해 -> 클로저(함수+변수의집합) 생성
// 함수형 프로그래밍에서 OOP관점의 객체 == 클로저
const Tab = createBottomTabNavigator();

// 스택 네비게이터 생성
const ProductStack = createNativeStackNavigator();

// 스택 스크린 생성
const ProductScreen = () => {
  return (
    <ProductStack.Navigator>
      <ProductStack.Screen name="Product" component={Product} />
      <ProductStack.Screen name="Detail" component={Detail} />
    </ProductStack.Navigator>
  );
};

// 탭네비게이터 옵션
// ({route}) => ({} as BottomTabNavigationOptions)
const screenOptions = ({
  route,
}: {
  route: RouteProp<ParamListBase, string>;
}) =>
  ({
    // 탭바 아이콘 설정
    // 포커스여부, 색상, 크기
    tabBarIcon: ({ focused, color, size }) => {
      // 경로명으로 아이콘 변경
      switch (route.name) {
        case "Home":
          return focused ? (
            <Ionicons name={"home"} size={size} color={color} />
          ) : (
            <Ionicons name={"home-outline"} size={size} color={color} />
          );
        case "Todo":
          return focused ? (
            <Ionicons name={"checkmark"} size={size} color={color} />
          ) : (
            <Ionicons name={"checkmark-outline"} size={size} color={color} />
          );
        case "ProductStack":
          return focused ? (
            <Ionicons name={"albums"} size={size} color={color} />
          ) : (
            <Ionicons name={"albums-outline"} size={size} color={color} />
          );
        case "Favorite":
          return focused ? (
            <Ionicons name={"heart"} size={size} color={color} />
          ) : (
            <Ionicons name={"heart-outline"} size={size} color={color} />
          );
      }
    },
    // 탭 활성화/비활성화 상태에 따른 컬러
    tabBarActiveTintColor: "#03045e",
    tabBarInactiveTintColor: "gray",
  } as BottomTabNavigationOptions);

export default function App() {
  return (
    <SafeAreaProvider>
      <StatusBar style="auto" />
      <NavigationContainer>
        {/* screenOptions는 객체 또는 객체반환함수 */}
        <Tab.Navigator screenOptions={screenOptions}>
          <Tab.Screen name="Home" component={Home} options={{}} />
          <Tab.Screen name="Todo" component={Todo} options={{}} />
          <Tab.Screen
            name="ProductStack"
            component={ProductScreen}
            options={{ headerShown: false, tabBarLabel: "Product" }}
          />
          <Tab.Screen name="Favorite" component={Favorite} options={{}} />
        </Tab.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
}
