import Link from "next/link";

const Profile = () => {
  return (
    <div>
      Profile Page
      <Link href={"/about"}>about</Link>
    </div>
  );
};

export default Profile;
