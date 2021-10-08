import { nanoid } from "@reduxjs/toolkit";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../store";
import { addAlert } from "./alert/alertSlice";

const EventMessage = () => {
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    const clientId = nanoid();
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
  }, [dispatch]);

  return <></>;
};

export default EventMessage;
