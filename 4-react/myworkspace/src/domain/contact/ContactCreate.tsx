import { useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { addContact, ContactItem } from "./contactSlice";

const ContactCreate = () => {
  const nameInput = useRef<HTMLInputElement>(null);
  const phoneInput = useRef<HTMLInputElement>(null);
  const emailInput = useRef<HTMLInputElement>(null);
  const memoTxta = useRef<HTMLTextAreaElement>(null);

  // 연락처 데이터 배열 가져오기
  const contactData = useSelector((state: RootState) => state.contact.data);

  // dispatch 함수 만들기
  const dispatch = useDispatch<AppDispatch>();

  // history 객체 가져오기
  const history = useHistory();

  const handleAddClick = () => {
    const contact: ContactItem = {
      id: contactData.length ? contactData[0].id + 1 : 1,
      name: nameInput.current ? nameInput.current.value : "",
      phone: phoneInput.current ? phoneInput.current.value : "",
      email: emailInput.current ? emailInput.current.value : "",
      memo: memoTxta.current ? memoTxta.current.value : "",
      createdTime: new Date().getTime(),
    };

    dispatch(addContact(contact));
    history.push("/contacts");
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Contact Create</h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th scope="row">이름</th>
              <td>
                <input className="form-control" type="text" ref={nameInput} />
              </td>
            </tr>
            <tr>
              <th scope="row">전화번호</th>
              <td>
                <input className="form-control" type="text" ref={phoneInput} />
              </td>
            </tr>
            <tr>
              <th scope="row">이메일</th>
              <td>
                <input className="form-control" type="text" ref={emailInput} />
              </td>
            </tr>
            <tr>
              <th scope="row">메모</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "40vh" }}
                  ref={memoTxta}
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
            handleAddClick();
          }}
        >
          <i className="bi bi-check" />
          저장
        </button>
      </div>
    </div>
  );
};

export default ContactCreate;
