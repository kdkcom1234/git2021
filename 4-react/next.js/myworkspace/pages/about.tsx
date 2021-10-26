import Link from "next/link";

const About = () => {
  return (
    <div>
      About Page
      <Link href={"/profile"}>profile</Link>
    </div>
  );
};

export default About;
