import { useEffect, useRef } from "react";

interface AlertProp {
  message: string;
  variant: string;
  onClose?: () => void;
}

const Alert = ({ message, variant, onClose }: AlertProp) => {
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    // 1초 후에 0.05초마다 opacity(투명도) 변경함수를 실행
    setTimeout(() => {
      let count = 1;
      setInterval(() => {
        if (containerRef.current) {
          // console.log(count / 20);
          containerRef.current.style.opacity = `${(20 - count) / 20}`;
          count++;
        }
      }, 50);
    }, 1000);

    // 2초후에 onClose 함수가 있으면 onClose 함수를 실행
    setTimeout(() => {
      onClose && onClose();
    }, 2000);
  }, [onClose]);

  return (
    <>
      <div
        ref={containerRef}
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
    </>
  );
};

export default Alert;
