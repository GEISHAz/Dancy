import React, { useState } from "react";
import { useRecoilState } from "recoil";
import { joinState } from "../../recoil/JoinState";
import * as JF from "./JoinForm.style";
import * as F from "./Form.style";
import CustomModal from "./PinModal";
import { emailCheck, nickNameCheck } from "../../api/join";
import { httpStatusCode } from "../../util/http-status";

export default function FormArea() {
  const [joinData, setJoinData] = useRecoilState(joinState);
  // const [inputValues, setInputValues] = useState({
  //   email: "",
  //   password: "",
  //   checkpassword: "",
  //   birthdate: "",
  //   gender: "",
  //   nickname: "",
  // });

  const [showWarnings, setShowWarnings] = useState({
    email: { show: false, message: "" },
    password: { show: false, message: "" },
    checkpassword: { show: false, message: "" },
    birthdate: { show: false, message: "" },
    gender: { show: false, message: "" },
    nickname: { show: false, message: "" },
  });

  const [isModalOpen, setIsModalOpen] = useState(false); // 모달의 열림/닫힘 상태를 관리
  const [submittedPin, setSubmittedPin] = useState(""); // 모달에서 제출된 PIN을 저장
  const [isPinChecked, setIsPinChecked] = useState(false);

  // 모달을 열기 위한 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달을 닫기 위한 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  // 모달에서 PIN이 제출됐을 때 실행되는 함수
  const handlePinSubmit = (pin) => {
    console.log(`Submitted PIN: ${pin}`);
    setSubmittedPin(pin); // 제출된 PIN을 상태값에 저장
    setIsPinChecked(true); // 핀이 제출되면 체크 된 것으로 가정!
  };

  const handleInputChange = (inputName, value) => {
    setJoinData((prevData) => ({
      ...prevData,
      [inputName]: value,
    }));
    //형식이 맞으면 경고 상태 초기화 해주기 !
    setShowWarnings((prevWarnings) => ({
      ...prevWarnings,
      [inputName]: { show: false, message: "" },
    }));

    if (inputName === "password") {
      const isValid = validatePW(value);
      setShowWarnings((prevWarnings) => ({
        ...prevWarnings,
        password: !isValid,
      }));
    }

    if (inputName === "checkpassword") {
      console.log(value);
      setShowWarnings((prevWarnings) => ({
        ...prevWarnings,
        checkpassword: value !== joinData.password,
      }));
    }

    // 날짜 데이터 입력값 체크
    if (inputName === "birthdate") {
      if (!value) {
        // 값이 비어 있는 경우
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          birthdate: true, // 경고 메시지를 표시합니다.
        }));
      } else {
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          birthdate: false, // 값이 존재하는 경우 경고 메시지를 초기화합니다.
        }));
      }
    }
  };

  const handleAuthentication = async () => {
    // 이메일 형식 체크
    if (!validateEmail(joinData.email)) {
      setShowWarnings((prevWarnings) => ({
        ...prevWarnings,
        email: { show: true, message: "유효하지 않은 이메일 형식입니다." },
      }));
      return;
    }

    try {
      // 서버에 이메일 중복 여부 확인 요청
      const isEmailDuplicate = await checkEmailDuplicate(joinData.email);

      if (isEmailDuplicate) {
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          email: { show: true, message: "이미 가입된 이메일입니다." },
        }));
        return;
      }
      // 서버 통신 로직
      openModal();
    } catch (error) {
      console.error("Authentication failed:", error);
      // 서버에서 에러가 발생한 경우에 대한 처리
    }
  };

  const handleNickNameCheck = async () => {
    // 닉네임 형식 체크
    if (!validateNickName(joinData.nickname)) {
      setShowWarnings((prevWarnings) => ({
        ...prevWarnings,
        nickname: { show: true, message: "불가능한 형식의 닉네임입니다." },
      }));
      return;
    }

    try {
      const response = await nickNameCheck(joinData.nickname);
      console.log("response", response);
      if (response === httpStatusCode.OK) {
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          nickname: { show: true, message: "사용 가능한 닉네임 입니다." }, // 값이 존재하는 경우 경고 메시지를 초기화합니다.
        }));
        return;
      }
    } catch (error) {
      if (error === httpStatusCode.CONFLICT) {
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          nickname: { show: true, message: "중복되는 닉네임입니다." },
        }));
        return;
      }
      // 400일때 대응 필요합니다.
    }
  };

  // 이메일 형식 체크 함수
  const validateEmail = (email) => {
    // 간단한 이메일 형식 체크 로직
    return /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/i.test(
      email
    );
  };

  // 서버에 이메일 중복 여부를 확인하는 함수
  const checkEmailDuplicate = async (email) => {
    // 서버와 통신하여 이메일 중복 여부를 확인하는 로직
    // true: 중복된 이메일, false: 중복되지 않은 이메일
    try {
      const response = await emailCheck(email);
      console.log("response", response);
      if (response === httpStatusCode.OK) {
        // 성공적인 응답
        return false;
      }
    } catch (error) {
      if (error === httpStatusCode.CONFLICT) {
        return true;
      }
      // 400일때 대응 필요합니다.
    }
  };

  // 비밀번호 형식 체크
  const validatePW = (password) => {
    const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@!^%#~?&])[A-Za-z\d$@!%~*^#?&]{8,}$/;
    return regex.test(password);
  };

  // 닉네임 형식 체크
  const validateNickName = (nickname) => {
    const regex = /^[A-Za-z0-9_.\-]{1,14}$/;
    console.log("test 진행중..");
    return regex.test(nickname);
  };

  return (
    <F.JoinFormArea>
      <F.NoticeArea>
        <JF.MustNoticeText>(&nbsp;</JF.MustNoticeText>
        <JF.MustIcon />
        <JF.MustNoticeText>&nbsp;)는 필수 입력 값입니다.</JF.MustNoticeText>
      </F.NoticeArea>
      <F.FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="76px">E-mail</JF.FormCategory>
        <F.InputColunmArea>
          <JF.FormInput
            type="email"
            value={joinData.email}
            onChange={(e) => handleInputChange("email", e.target.value)}
          ></JF.FormInput>
          <JF.InputNoticeText show={showWarnings.email.show}>
            {showWarnings.email.message}
          </JF.InputNoticeText>
        </F.InputColunmArea>
        <JF.FormBtn disabled={isPinChecked} onClick={handleAuthentication}>
          인증하기
        </JF.FormBtn>
        {/* CustomModal 컴포넌트를 렌더링하고 isOpen, onClose, onSubmit을 props로 전달 */}
        <CustomModal
          email={joinData.email}
          isOpen={isModalOpen}
          onClose={closeModal}
          onSubmit={handlePinSubmit}
        />
      </F.FormDetailArea>
      <F.FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="62px">비밀번호</JF.FormCategory>
        <F.InputContainer>
          <JF.FormInput
            type="password"
            value={joinData.password}
            onChange={(e) => handleInputChange("password", e.target.value)}
          ></JF.FormInput>
          <JF.InputNoticeText show={showWarnings.password}>
            형식을 만족하지 않는 비밀번호입니다.
          </JF.InputNoticeText>
        </F.InputContainer>
        <F.EnterArea>
          <JF.MustNoticeText>영문자, 숫자, 특수문자를 조합하여</JF.MustNoticeText>
          <JF.MustNoticeText>입력해주세요. (8자 이상)</JF.MustNoticeText>
        </F.EnterArea>
      </F.FormDetailArea>
      <F.FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="19px">비밀번호 확인</JF.FormCategory>
        <F.InputContainer>
          <JF.FormInput
            type="password"
            value={joinData.checkpassword}
            onChange={(e) => handleInputChange("checkpassword", e.target.value)}
          ></JF.FormInput>
          <JF.InputNoticeText show={showWarnings.checkpassword}>
            비밀번호가 일치하지 않습니다.
          </JF.InputNoticeText>
        </F.InputContainer>
      </F.FormDetailArea>
      <F.FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="62px">생년월일</JF.FormCategory>
        <F.InputContainer>
          <JF.FormInput
            type="date"
            value={joinData.birthdate}
            onChange={(e) => handleInputChange("birthdate", e.target.value)}
          ></JF.FormInput>
          <JF.InputNoticeText show={showWarnings.birthdate}>
            날짜를 입력해주세요.
          </JF.InputNoticeText>
        </F.InputContainer>
      </F.FormDetailArea>
      <F.FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="99px">성별</JF.FormCategory>
        <F.RadioContainer>
          <input
            type="radio"
            name="gender"
            value="MALE"
            onChange={(e) => handleInputChange("gender", e.target.value)}
          />{" "}
          남성
          <input
            type="radio"
            name="gender"
            value="FEMALE"
            onChange={(e) => handleInputChange("gender", e.target.value)}
          />{" "}
          여성
        </F.RadioContainer>
      </F.FormDetailArea>
      <F.FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="80px">닉네임</JF.FormCategory>
        <F.InputContainer>
          <JF.FormInput
            value={joinData.nickname}
            onChange={(e) => handleInputChange("nickname", e.target.value)}
          ></JF.FormInput>
          <JF.InputNoticeText show={showWarnings.nickname.show}>
            {showWarnings.nickname.message}
          </JF.InputNoticeText>
        </F.InputContainer>
        <JF.FormBtn onClick={handleNickNameCheck}>중복 확인</JF.FormBtn>
      </F.FormDetailArea>
    </F.JoinFormArea>
  );
}
