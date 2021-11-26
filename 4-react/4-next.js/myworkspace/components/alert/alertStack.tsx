import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../provider";
import Alert from "./alert";
import { removeAlert } from "../../provider/modules/alert";
import { useCallback } from "react";

const AlertStack = () => {
  const alert = useSelector((state: RootState) => state.alert);
  const dispatch = useDispatch<AppDispatch>();

  const handleOnClose = (id: string) => {
    dispatch(removeAlert(id));
  };

  return (
    <div className="fixed-bottom d-flex justify-content-center">
      <div className="w-25">
        {alert.map((item) => (
          <Alert
            key={`alert-${item.id}`}
            variant={item.variant}
            message={item.message}
            onClose={useCallback(() => {
              handleOnClose(item.id);
            }, [])}
          />
        ))}
      </div>
    </div>
  );
};

export default AlertStack;
