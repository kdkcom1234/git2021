import { useRef, useState } from "react";
import { penguin } from "../../common/data";

// 모듈명(컴포넌트명).module.scss
// 해당 컴포넌트에서만 사용할 스타일시트
// import 스타일변수 from "./모듈명.module.scss"
import style from "./Profile.module.scss";

interface ProfileState {
  image: string | undefined;
  username: string | undefined;
}

const Profile = () => {
  const [profile, setProfile] = useState<ProfileState>({
    image: penguin,
    username: "Daekeun Ko",
  });

  const [isShow, setIsShow] = useState(false); // 프로필 상세보기 제어
  const [isEdit, setIsEdit] = useState(false); // 수정모드 제어
  const [url, setUrl] = useState<string | undefined>(profile.image); // 이미지 url

  const inputRef = useRef<HTMLInputElement>(null);

  const changeImage = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files?.length) {
      const file = e.target.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        setUrl(reader.result?.toString());
      };

      reader.readAsDataURL(file);
    }
  };

  const save = () => {
    setProfile({ image: url, username: inputRef.current?.value });
    setIsEdit(false);
  };

  return (
    <>
      {/* profile 영역 */}
      <div className="dropdown me-5">
        {/* 앱바 프로필 */}
        <div
          style={{ cursor: "pointer" }}
          className="d-flex"
          onClick={() => {
            setIsShow(!isShow);
          }}
        >
          {/* 스타일변수명.클래스명 */}
          {/* 스타일 시트의 클래스를 객체화함
            style: {
              thumb: "Profile_thumb__sdfsdfsdf"
            }
          */}
          <div
            className={`${style.thumb} me-1`}
            style={{ backgroundImage: `url(${profile.image})` }}
          ></div>
          <span className={`${style.username} text-light`}>
            {profile.username}
          </span>
        </div>
        {/* 프로필 상세보기 */}
        {isShow && (
          <div
            className="dropdown-menu d-flex flex-column align-items-center"
            style={{ right: "-30px" }}
          >
            {/* 보기 모드 */}
            {!isEdit && (
              <>
                <div
                  // 스타일변수명["클래스명"]
                  className={`${style["thumb-large"]}`}
                  style={{ backgroundImage: `url(${profile.image})` }}
                ></div>
                <p>{profile.username}</p>
              </>
            )}
            {/* 수정 모드 */}
            {isEdit && (
              <>
                <div
                  className={`${style["thumb-large"]}`}
                  style={{ backgroundImage: `url(${url})` }}
                ></div>
                <input
                  type="file"
                  className="form-control form-control-sm me-1"
                  accept="image/png, image/jpeg"
                  onChange={(e) => {
                    changeImage(e);
                  }}
                />
                <input
                  type="text"
                  defaultValue={profile.username}
                  ref={inputRef}
                />
              </>
            )}

            {/* 하단 링크버튼 */}
            <div className="d-flex">
              {/* 보기모드 */}
              {!isEdit && (
                <>
                  <a
                    href="#!"
                    className="link-secondary fs-6 text-nowrap me-2"
                    onClick={(e) => {
                      e.preventDefault();
                      setIsEdit(true);
                    }}
                  >
                    edit
                  </a>
                  <a
                    href="#!"
                    className="link-secondary fs-6 text-nowrap"
                    onClick={(e) => {
                      e.preventDefault();
                      setIsShow(!isShow);
                    }}
                  >
                    close
                  </a>
                </>
              )}
              {/* 수정모드 */}
              {isEdit && (
                <>
                  <a
                    href="#!"
                    className="link-secondary fs-6 text-nowrap me-2"
                    onClick={(e) => {
                      e.preventDefault();
                      save();
                    }}
                  >
                    save
                  </a>
                  <a
                    href="#!"
                    className="link-secondary fs-6 text-nowrap"
                    onClick={(e) => {
                      e.preventDefault();
                      setUrl(profile.image);
                      setIsEdit(false);
                    }}
                  >
                    cancel
                  </a>
                </>
              )}
            </div>
          </div>
        )}
      </div>
    </>
  );
};

export default Profile;
