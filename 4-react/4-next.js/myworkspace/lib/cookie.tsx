const sessionCookieName = "sid";

const setSessionId = (sessionId: string) => {
  // 1일짜리 세션 쿠키 생성
  document.cookie = `${sessionCookieName}=${sessionId};max-age=86400`;
};

const getSessionId = () => {
  let matches = document.cookie.match(
    new RegExp(
      "(?:^|; )" +
        sessionCookieName.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, "\\$1") +
        "=([^;]*)"
    )
  );
  return matches ? decodeURIComponent(matches[1]) : undefined;
};

const removeSessionId = () => {
  document.cookie = `${sessionCookieName}=;max-age=-1`;
};

export { setSessionId, getSessionId, removeSessionId };
