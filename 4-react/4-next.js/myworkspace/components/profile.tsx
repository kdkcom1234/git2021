import Link from "next/link";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getSessionId, removeSessionId } from "../lib/cookie";
import {
  requestClearSessionProfile,
  requestFetchSessionProfile,
} from "../middleware/modules/profile";
import { AppDispatch, RootState } from "../provider";

import { Nav, NavDropdown } from "react-bootstrap";
import { clearProfile } from "../provider/modules/profile";
import { useRouter } from "next/router";

const Profile = () => {
  const profile = useSelector((state: RootState) => state.profile);

  const dispatch = useDispatch<AppDispatch>();
  const router = useRouter();

  useEffect(() => {
    // redux에 프로필 정보가 없으면
    if (!profile.username) {
      const sessionId = getSessionId();
      if (sessionId) {
        // 세션정보가 있으면
        // 프로필 조회하고 프로필 정보 띄움
        dispatch(requestFetchSessionProfile());
      } else {
        // 세션정보가 없으면
        // SIGN-IN 링크 띄움
      }
    }
  }, []);

  const handleSignOut = () => {
    // profile redux state 삭제
    // dispatch(clearProfile());
    dispatch(requestClearSessionProfile());
    // session 쿠키 삭제
    removeSessionId();
    // 메인화면 이동 (state까지 비우기 위해서 페이지 이동함)
    window.location.href = "/";
  };

  return (
    <>
      {profile.username && (
        <NavDropdown
          title={<span className="text-light">{profile.username}</span>}
        >
          <NavDropdown.Item
            onClick={() => {
              handleSignOut();
            }}
          >
            SIGN OUT
          </NavDropdown.Item>
        </NavDropdown>
      )}
      {!profile.username && (
        <Nav.Item>
          <Link href="/signin">
            <a className="text-light">SIGN IN</a>
          </Link>
        </Nav.Item>
      )}
    </>
  );
};

export default Profile;
