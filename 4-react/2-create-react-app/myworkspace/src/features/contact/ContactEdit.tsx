import { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { modifyContact } from "./contactSlice";

const ContactEdit = () => {
  // ------ 데이터를 가져오거나 변수를 선언하는 부분 --------
  const { id } = useParams<{ id: string }>();

  // dispatch 함수 만들기
  const dispatch = useDispatch<AppDispatch>();

  // history 객체 가져오기
  const history = useHistory();

  const contactItem = useSelector((state: RootState) =>
    state.contact.data.find((item) => item.id === +id)
  );

  const nameInput = useRef<HTMLInputElement>(null);
  const phoneInput = useRef<HTMLInputElement>(null);
  const emailInput = useRef<HTMLInputElement>(null);
  const memoTxta = useRef<HTMLTextAreaElement>(null);

  const handleSaveClick = () => {
    if (contactItem) {
      const item = { ...contactItem };
      item.name = nameInput.current ? nameInput.current.value : "";
      item.phone = phoneInput.current ? phoneInput.current.value : "";
      item.email = emailInput.current ? emailInput.current.value : "";
      item.memo = memoTxta.current ? memoTxta.current.value : "";

      dispatch(modifyContact(item));
      history.push("/contacts");
    }
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center mb-5">Contact Edit</h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th scope="row">이름</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  ref={nameInput}
                  defaultValue={contactItem?.name}
                />
              </td>
            </tr>
            <tr>
              <th scope="row">전화번호</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  ref={phoneInput}
                  defaultValue={contactItem?.phone}
                />
              </td>
            </tr>
            <tr>
              <th scope="row">이메일</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  ref={emailInput}
                  defaultValue={contactItem?.email}
                />
              </td>
            </tr>
            <tr>
              <th scope="row">메모</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "40vh" }}
                  ref={memoTxta}
                  defaultValue={contactItem?.memo}
                ></textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div>
        <button
          className="btn btn-secondary float-start"
          onClick={() => {
            history.push("/contacts");
          }}
        >
          <i className="bi bi-table me-1"></i>
          목록
        </button>
        <button
          className="btn btn-primary float-end"
          onClick={() => {
            handleSaveClick();
          }}
        >
          <i className="bi bi-check" />
          저장
        </button>
      </div>
    </div>
  );
};

export default ContactEdit;
