import { useSession, signOut } from "next-auth/client";
import Image from "next/image";
import Link from "next/link";

const AppBar = () => {
  const [session] = useSession();
  return (
    <div style={{ width: "1200px", display: "flex", justifyContent: "end" }}>
      {session?.user && (
        <div>
          <Image
            src={session.user.image as string}
            alt={session.user.name as string}
            width={32}
            height={24}
            // layout="responsive"
            objectFit="cover"
          />
          {session.user.email}
          <span style={{ marginLeft: "0.5rem" }}>{session.user.name}</span>
          <span style={{ marginLeft: "1rem" }}>
            <button
              onClick={() => {
                signOut();
              }}
            >
              Sign Out
            </button>
          </span>
        </div>
      )}
      {!session?.user && <Link href="/api/auth/signin">Sign In</Link>}
    </div>
  );
};

export default AppBar;
