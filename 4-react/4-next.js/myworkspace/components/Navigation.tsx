import Link from "next/link";

const Navigation = () => {
  return (
    <div>
      Navigation
      <ul>
        <li>
          <Link href="/"> Home </Link>
        </li>
        <li>
          <Link href="/about"> About </Link>
        </li>
        <li>
          <Link href="/contact"> Contact </Link>
        </li>
      </ul>
    </div>
  );
};

export default Navigation;
