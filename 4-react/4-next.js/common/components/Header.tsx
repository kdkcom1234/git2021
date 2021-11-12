const Header = ({ text, color }: { text: string; color?: string }) => {
  return <h1 style={{ color }}>{text}</h1>;
};

export default Header;
