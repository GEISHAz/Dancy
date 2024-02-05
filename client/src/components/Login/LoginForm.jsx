import { Link, useNavigate } from "react-router-dom";
import { useState, useRecoilValue } from "react";
import { useRecoilState, useSetRecoilState } from "recoil";
import * as L from "./LoginForm.style.jsx";
import { userState, loginState } from "../../recoil/LoginState.js";
import { login } from "../../api/auth.js";

export default function Login() {
  const [isOpen, setIsOpen] = useState(false);
  const [modalTxt, setModalTxt] = useState("로그인에 성공했습니다 ♬");
  const navigate = useNavigate();

  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };

  const [formData, setFormData] = useState({ email: "", password: "" });

  const setUser = useSetRecoilState(userState);
  const setLogin = useSetRecoilState(loginState);
  // const setFailLogin = useRecoilState(loginFailState);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const loggedInUser = await login(formData);
      setLogin(true);
      setUser(loggedInUser);
      navigate("/");
    } catch (error) {
      const errorMsg = error.response.data[0].message;

      setModalTxt(errorMsg);
      openModalHandler();
    }
  };

  const handleEnter = (e) => {
    // e.preventDefault();
    if (e.key === "Enter") {
      handleSubmit(e);
    }
  };

  return (
    <L.Container>
      {isOpen && (
        <L.ModalBackdrop>
          <L.ModalView>
            <L.ModalTxt>{modalTxt}</L.ModalTxt>
            <L.ModalBtn onClick={openModalHandler}>확인</L.ModalBtn>
          </L.ModalView>
        </L.ModalBackdrop>
      )}

      <div className="inline-flex flex-col gap-y-8">
        <L.DancyLogo />

        {/* E-mail 입력란 */}
        <div className="flex flex-col gap-y-1">
          <L.InputTitle>E-mail</L.InputTitle>
          <L.InputBox
            type="email"
            name="email"
            placeholder="Email을 입력해주세요 ♬"
            value={formData.email}
            onChange={handleChange}
          />
        </div>

        {/* PW 입력란 */}
        <div className="flex flex-col gap-y-1">
          <L.InputTitle>Password</L.InputTitle>
          <L.InputBox
            type="password"
            name="password"
            placeholder="비밀번호를 입력해주세요 ♬"
            value={formData.password}
            onChange={handleChange}
            onKeyDown={handleEnter}
          />
        </div>

        {/* 자동로그인 + 소셜로그인 */}
        <div className="flex flex-row justify-between">
          {/* 자동로그인 체크박스 */}
          <div className="flex flex-row items-center gap-x-2">
            <L.AutoLoginChkBox id="autologin" />
            <L.AutoLogin htmlFor="autologin">자동 로그인</L.AutoLogin>
          </div>

          {/* 소셜로그인 아이콘 */}
          <div className="flex flex-row gap-x-4">
            <L.SocialGoogle />
            <L.SocialKakao />
            <L.SocialNaver />
          </div>
        </div>

        {/* 로그인 버튼 */}
        <L.LoginButton onClick={handleSubmit}>로그인</L.LoginButton>

        {/* 회원가입 + 비밀번호 찾기 */}
        <div>
          <div className="flex flex-row items-center gap-x-2 justify-end">
            <L.ExplainJoinFindPw>아직 회원이 아니신가요?</L.ExplainJoinFindPw>
            <L.GoJoinFindPw>
              <Link to="/signup">회원가입</Link>
            </L.GoJoinFindPw>
          </div>

          <div className="flex flex-row items-center gap-x-2 justify-end">
            <L.ExplainJoinFindPw>
              비밀번호를 잊어버리셨나요?
            </L.ExplainJoinFindPw>
            <L.GoJoinFindPw>
              <Link to="/">비밀번호</Link>
            </L.GoJoinFindPw>
          </div>
        </div>
      </div>
    </L.Container>
  );
}
