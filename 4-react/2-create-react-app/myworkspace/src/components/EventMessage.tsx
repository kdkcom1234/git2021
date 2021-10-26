import { nanoid } from "@reduxjs/toolkit";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../store";
import { addAlert } from "./alert/alertSlice";

const EventMessage = () => {
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    // 세션 저장소에 clientId를 가져오고 없으면 생성함
    // 서버에 client에 맞는 emitter를 생성할 때 재 생성을 방지하기 위함
    let clientId = sessionStorage.getItem("event-client-id");
    if (!clientId) {
      clientId = nanoid();
      sessionStorage.setItem("event-client-id", clientId);
    }

    // const clientId = nanoid();

    const eventUrl = `http://localhost:9090/event/${clientId}`;
    const eventSource = new EventSource(eventUrl);

    eventSource.onmessage = (event) => {
      console.log(new Date().getTime() + ": " + event.data);
      if (event.data !== "connected") {
        dispatch(
          addAlert({ id: nanoid(), variant: "info", message: event.data })
        );
      }
    };

    window.addEventListener("beforeunload", () => {
      console.log(alert(""));
    });
  }, [dispatch]);

  return <></>;
};

export default EventMessage;
