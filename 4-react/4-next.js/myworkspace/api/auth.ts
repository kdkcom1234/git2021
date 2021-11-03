import axios from "axios";

interface SignUpRequest {
  userId: string;
  password: string;
  username: string;
  email: string;
  role: string;
  img?: string;
}

interface SignInRequest {
  userId: string;
  password: string;
}

const authApi = {
  // POST /auth/signup -> success 반환
  signup: (req: SignUpRequest) =>
    axios.post<string>(`${process.env.NEXT_PUBLIC_AUTH_BASE}/auth/signup`, req),
  // POST /auth/signin -> sessionId 반환
  signin: (req: SignUpRequest) =>
    axios.post<string>(`${process.env.NEXT_PUBLIC_AUTH_BASE}/auth/signin`, req),
};

export default authApi;
