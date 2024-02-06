import { Link, useNavigate } from "react-router-dom";
import { useState, useRecoilValue } from "react";
import { useRecoilState, useSetRecoilState } from "recoil";
import * as L from "./LoginForm.style.jsx";
import { userState, loginState } from "../../recoil/LoginState.js";
import { login } from "../../api/auth.js";
import { userDetails } from "../../api/user.js";

export default function Login() {
  const [isOpen, setIsOpen] = useState(false); // 모달 On/Off 관리
  const [modalTxt, setModalTxt] = useState("로그인에 성공했습니다 ♬"); // 모달 문구 설정
  const navigate = useNavigate();

  // 모달 On/Off 관리
  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };

  // 사용자 로그인 데이터 관리 (id, password)
  const [formData, setFormData] = useState({ email: "", password: "" });
  const emailRegEx = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/i;
  const [isEmailCorrect, setIsEmailCorrect] = useState(true);
  const [userInfo, setUserInfo] = useRecoilState(userState)

  // const setUser = useSetRecoilState(userState); // userState에 로그인한 회원정보 저장
  const setLogin = useSetRecoilState(loginState); // login유무 저장
  // const user = useRecoilValue(userState)

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    if (e.target.name === "email") {
      if (emailRegEx.test(e.target.value)) {
        setIsEmailCorrect(true);
      } else {
        setIsEmailCorrect(false);
    }}
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      await login(formData);
      setLogin(true);
      navigate("/");
  
      // 로그인 이후 user 정보를 가져옴
      const userDetailsData = await userDetails();
      console.log("User Details:", userDetailsData.userInfo);
      setUserInfo(userDetailsData.userInfo)
    } catch (error) {
      console.error("Login Error:", error);
      const errorMsg = error.response?.data[0]?.message || "An error occurred";
  
      setModalTxt(errorMsg);
      openModalHandler();
    }
    // login(formData)
    // .then((res) => {
    //   console.log(res)
    //   setLogin(true);
    //   navigate("/");
    // })
    // .then(() => {
    //   const res = userDetails()
    //   console.log(res)
    //   // userInfo = res.userInfo
    //   // setUserInfo(userInfo)
    // })
    // .catch((err) => {
    //   console.error(err)
    //   const errorMsg = err.response.data[0].message;
      
    //   setModalTxt(errorMsg);
    //   openModalHandler();
    // })
  };



  const handleEnter = (e) => {
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
          {isEmailCorrect ? null : (
            <L.ErrorEmail>이메일 형식이 올바르지 않습니다.</L.ErrorEmail>
          )}
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
              <Link to="/findpassword">비밀번호 찾기</Link>
            </L.GoJoinFindPw>
          </div>
        </div>
      </div>
    </L.Container>
  );
}
