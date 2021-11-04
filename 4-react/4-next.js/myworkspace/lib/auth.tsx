import { getSessionId } from "./cookie";

const checkAuth = () => {
  if (!getSessionId()) {
    window.location.replace("/signin");
  }
};

export { checkAuth };
