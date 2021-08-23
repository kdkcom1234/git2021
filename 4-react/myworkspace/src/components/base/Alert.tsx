interface AlertProp {
  message: string;
  variant: string;
  onClose?: () => void;
}

const Alert = ({ message, variant, onClose }: AlertProp) => {
  return (
    <div
      className={`alert alert-${variant} alert-dismissible my-2`}
      role="alert"
    >
      {message}
      <button
        type="button"
        className="btn-close"
        data-bs-dismiss="alert"
        aria-label="Close"
        // 부모 컴포넌트에서 받은 함수를 실햄
        // () => { setIsError(false);}
        // event-up: 부모로 이벤트를 넘겨줌
        onClick={onClose}
      ></button>
    </div>
  );
};

export default Alert;
