const BootStrap = () => {
  return (
    <div>
      <button className="btn btn btn-primary">Primary</button>
      <button className="btn btn btn-danger">Danger</button>
      <div className="card" style={{ width: "18rem" }}>
        <img src="..." className="card-img-top" alt="penguin" />
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </p>
          <button className="btn btn btn-primary">Primary</button>
        </div>
      </div>
    </div>
  );
};

export default BootStrap;
