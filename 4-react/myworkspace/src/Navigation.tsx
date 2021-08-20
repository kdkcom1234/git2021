import { Link } from "react-router-dom";

const Navigation = () => {
  return (
    <ul>
      <li>
        <Link to="/">Home</Link>
      </li>
      <li>
        <Link to="/components">Components</Link>
      </li>
      <li>
        <Link to="/counter">Counter</Link>
      </li>
      <li>
        <Link to="/calculator">Calculator</Link>
      </li>
      <li>
        <Link to="/generator">Generator</Link>
      </li>
      <li>
        <Link to="/account-manager">AccountManager</Link>
      </li>
      <li>
        <Link to="/bootstrap">BootStrap</Link>
      </li>
    </ul>
  );
};

export default Navigation;
