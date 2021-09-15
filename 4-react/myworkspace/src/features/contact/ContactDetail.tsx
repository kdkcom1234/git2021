import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { removeContact } from "./contactSlice";

const ContactDetail = () => {
  const { id } = useParams<{ id: string }>();

  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  const contactItem = useSelector((state: RootState) =>
    state.contact.data.find((item) => item.id === +id)
  );

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center mb-5">Contact Detail</h2>
      {!contactItem && (
        <div className="text-center my-5">데이터가 없습니다.</div>
      )}

      {contactItem && (
        <table className="table">
          <tbody>
            <tr>
              <th>이름</th>
              <td>{contactItem.name}</td>
            </tr>
            <tr>
              <th>전화번호</th>
              <td>{contactItem.phone}</td>
            </tr>
            <tr>
              <th>이메일</th>
              <td>{contactItem.email}</td>
            </tr>
            <tr>
              <th>메모</th>
              <td
                dangerouslySetInnerHTML={{
                  __html: contactItem.memo
                    ? contactItem.memo.replaceAll("\n", "<br>")
                    : "",
                }}
              ></td>
            </tr>
          </tbody>
        </table>
      )}
      <div className="d-flex">
        <div style={{ width: "50%" }}>
          <button
            className="btn btn-secondary me-1"
            onClick={() => {
              history.push("/contacts");
            }}
          >
            <i className="bi bi-table me-1"></i>
            목록
          </button>
        </div>
        <div style={{ width: "50%" }} className="d-flex justify-content-end">
          <button
            className="btn btn-primary me-1"
            onClick={() => {
              history.push(`/contacts/edit/${id}`);
            }}
          >
            <i className="bi bi-pencil me-1" />
            수정
          </button>
          <button
            className="btn btn-danger"
            onClick={() => {
              dispatch(removeContact(+id));
              history.push("/contacts");
            }}
          >
            <i className="bi bi-trash me-1" />
            삭제
          </button>
        </div>
      </div>
    </div>
  );
};

export default ContactDetail;
