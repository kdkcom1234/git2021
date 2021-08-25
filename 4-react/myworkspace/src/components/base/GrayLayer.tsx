const GrayLayer = () => {
  return (
    <div
      className="position-fixed"
      style={{
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        width: "20000px",
        height: "20000px",
        left: 0,
        top: 0,
        zIndex: 1000,
      }}
    ></div>
  );
};

export default GrayLayer;
