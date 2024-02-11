import React, { useState, useEffect, useRef } from "react";
import { styled } from "styled-components";
import * as SF from "./SettingForm.style";
import QuitModal from "./QuitModal";
import ChangePwdModal from "./ChangePwdModal";
import { useRecoilState, useRecoilValue } from "recoil";
import { userState } from "../../recoil/LoginState";
import { nickNameCheck } from "../../api/join";
import { httpStatusCode } from "../../util/http-status";
import { BrowserRouter as Router, Route, Link, useNavigate } from "react-router-dom";
import { userChangeNickName, userChangeIntro, userChangeImg } from "../../api/user";
import { selectedFileState } from "../../recoil/JoinState";

// 전체 폼 구성
export const JoinFormArea = styled.div`
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 16px;
`;

// 필수 항목 공지
export const NoticeArea = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  margin-bottom: 12px;
`;

// 폼 매 줄 마다 설정
export const FormDetailArea = styled.div`
  width: 100%;
  display: flex;
  justify-content: start;
  align-items: center;
  margin-top: 1rem;
  gap: 20px;
`;

// 텍스트 엔터 처리
export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const InputContainer = styled.div`
  position: relative;
`;

// 라디오 버튼 커스텀
export const RadioContainer = styled.div`
  display: flex;
  font-family: "NYJ Gothic B";
  font-size: 16px;
  gap: 12px;
  margin-right: ${(props) => props.margin || "0px"};

  input {
    accent-color: #f9405e;
  }
`;

export default function FormArea() {
  const [showWarnings, setShowWarnings] = useState({
    nickname: { show: false, message: "" },
  });
  const [isChangePwdModalOpen, setIsChangePwdModalOpen] = useState(false);
  const [isQuitModalOpen, setIsQuitModalOpen] = useState(false);
  const [user, setUser] = useRecoilState(userState);

  // 임시로 저장할 nickname과 introduceText의 상태값
  const [nickname, setNickname] = useState(user.nickname);
  const [introduceText, setIntroduceText] = useState(user.introduceText);
  const [isChecked, setIsChecked] = useState(false);

  // 사진 파일 가져오기
  const selectedFile = useRecoilValue(selectedFileState);

  const navigate = useNavigate();

  console.log("user", user);

  // 각 모달을 열기 위한 함수
  const openChangePwdModal = () => {
    setIsChangePwdModalOpen(true);
  };

  const openQuitModal = () => {
    setIsQuitModalOpen(true);
  };

  // 각 모달을 닫기 위한 함수
  const closeChangePwdModal = () => {
    setIsChangePwdModalOpen(false);
  };

  const closeQuitModal = () => {
    setIsQuitModalOpen(false);
  };

  const handleChange = (inputName, value) => {
    //형식이 맞으면 경고 상태 초기화 해주기 !
    setShowWarnings((prevWarnings) => ({
      ...prevWarnings,
      [inputName]: { show: false, message: "" },
    }));

    // nickname 또는 introduceText인 경우에만 상태값 업데이트
    if (inputName === "nickname") {
      setNickname(value);
      // 닉네임이 변경되면 isChecked를 false로 초기화
      setIsChecked(false);
    } else if (inputName === "introduceText") {
      setIntroduceText(value);
    }
  };

  // 닉네임 형식 체크
  const validateNickName = (nickname) => {
    const regex = /^[A-Za-z_.\-]?[A-Za-z_.\-]{1,8}$/;
    return regex.test(nickname);
  };

  const handleNickNameCheck = async () => {
    // 닉네임 형식 체크
    if (!validateNickName(nickname)) {
      setShowWarnings((prevWarnings) => ({
        ...prevWarnings,
        nickname: { show: true, message: "불가능한 형식의 닉네임입니다." },
      }));
      setIsChecked(false);
      return;
    }

    try {
      const response = await nickNameCheck(nickname);
      console.log("response", response);
      if (response === httpStatusCode.OK) {
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          nickname: { show: true, message: "사용 가능한 닉네임 입니다." }, // 값이 존재하는 경우 경고 메시지를 초기화합니다.
        }));
        setIsChecked(true);
        return;
      }
    } catch (error) {
      console.log("error", error);
      if (error === httpStatusCode.CONFLICT) {
        setShowWarnings((prevWarnings) => ({
          ...prevWarnings,
          nickname: { show: true, message: "중복되는 닉네임입니다." },
        }));
        setIsChecked(false);
        return;
      }
      // 400일때 대응 필요합니다.
    }
  };

  // 서버로 제출 요청하기
  const readyToSubmit = async () => {
    // 닉네임 체크가 되지 않았다면 수행 불가해요. 그치만 닉네임 수정을 안하고 싶을수도 있잖아?
    try {
      // 닉네임과 상태메시지가 변경된 경우에만 서버 요청
      if (nickname !== user.nickname) {
        // 닉네임 변경 전에 중복 체크를 수행했는지 확인
        if (!isChecked) {
          alert("닉네임 중복체크를 수행해주세요.");
          return;
        }

        // 닉네임 변경
        const nicknameStatusCode = await userChangeNickName(nickname);
        console.log("닉네임잘바뀌엇니?", nicknameStatusCode);
      }

      console.log("-----------");

      if (introduceText !== user.introduceText) {
        // 상태메시지 변경
        const introStatusCode = await userChangeIntro(introduceText);
        console.log("상메잘바뀌었니?", introStatusCode);
      }

      // 이미지가 있으면 일단 수정 요청을 해봅시다.
      if (selectedFile !== null) {
        const formData = new FormData();
        formData.set("profileImage", selectedFile);
        const imgStatusCode = await userChangeImg(formData);
        console.log("프사잘바뀌었니?", imgStatusCode);
      }

      // recoil 상태 업데이트
      setUser({
        ...user,
        nickname: nickname,
        introduceText: introduceText,
        profileImageUrl: selectedFile,
      });

      console.log("---------------");

      // 알림 및 페이지 이동
      alert("정보가 성공적으로 변경되었습니다.");
      navigate(`/profile/${nickname}`);
      window.location.reload();
    } catch (error) {
      console.error("서버 요청 중 에러가 발생했습니다.", error);
      // recoil 상태 업데이트
      setUser({
        ...user,
        nickname: nickname,
        introduceText: introduceText,
        profileImageUrl: selectedFile,
      });
      alert("서버 요청 중 에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
    }
  };

  return (
    <JoinFormArea>
      <NoticeArea>
        <SF.MustNoticeText>(&nbsp;</SF.MustNoticeText>
        <SF.MustIcon />
        <SF.MustNoticeText>&nbsp;)는 필수 입력 값입니다.</SF.MustNoticeText>
      </NoticeArea>
      <FormDetailArea>
        <SF.MustIcon />
        <SF.FormCategory margin="72px">닉네임</SF.FormCategory>
        <InputContainer>
          <SF.FormInput
            type="text"
            name="nickname"
            value={nickname}
            onChange={(e) => handleChange("nickname", e.target.value)}
          ></SF.FormInput>
          <SF.InputNoticeText show={showWarnings.nickname.show}>
            {showWarnings.nickname.message}
          </SF.InputNoticeText>
        </InputContainer>
        <SF.FormBtn onClick={handleNickNameCheck}>중복 체크</SF.FormBtn>
      </FormDetailArea>
      <FormDetailArea>
        <SF.MustIcon visibility="hidden" />
        <SF.FormCategory margin="36px">상태메세지</SF.FormCategory>
        <InputContainer>
          <SF.FormInput
            type="text"
            name="introduceText"
            value={introduceText}
            onChange={(e) => handleChange("introduceText", e.target.value)}
          ></SF.FormInput>
        </InputContainer>
      </FormDetailArea>
      <FormDetailArea>
        <SF.MustIcon />
        <SF.FormCategory margin="68px">E-mail</SF.FormCategory>
        <InputContainer>
          <SF.FormInput
            type="email"
            name="email"
            value={user.email}
            onChange={handleChange}
            readOnly
          ></SF.FormInput>
        </InputContainer>
      </FormDetailArea>
      <FormDetailArea>
        <SF.MustIcon />
        <SF.FormCategory margin="54px">생년월일</SF.FormCategory>
        <SF.FormInput
          type="date"
          name="birthDate"
          value={user.birthDate}
          onChange={handleChange}
          readOnly
        ></SF.FormInput>
      </FormDetailArea>
      <FormDetailArea>
        <SF.MustIcon />
        <SF.FormCategory margin="91px">성별</SF.FormCategory>
        <RadioContainer margin="104.1px">
          <input
            type="radio"
            name="gender"
            value="MALE"
            checked={user.gender === "MALE"}
            disabled="true"
          />{" "}
          남성
          <input
            type="radio"
            name="gender"
            value="FEMALE"
            checked={user.gender === "FEMALE"}
            disabled="true"
          />{" "}
          여성
        </RadioContainer>
        <SF.FormBtn width="167px" onClick={openChangePwdModal}>
          비밀번호 변경
        </SF.FormBtn>
        {/*ChangePwdModal 컴포넌트를 렌더링하고 isOpen, onClose을 props로 전달 */}
        <ChangePwdModal isOpen={isChangePwdModalOpen} onClose={closeChangePwdModal} />
      </FormDetailArea>
      <FormDetailArea>
        <SF.QuitText onClick={openQuitModal}>회원 탈퇴</SF.QuitText>
        {/* QuitModal 컴포넌트를 렌더링하고 isOpen, onClose을 props로 전달 */}
        <QuitModal isOpen={isQuitModalOpen} onClose={closeQuitModal} />
      </FormDetailArea>
      <FormDetailArea>
        <SF.RegisterBtn onClick={readyToSubmit} margin="217px">
          완료
        </SF.RegisterBtn>
      </FormDetailArea>
    </JoinFormArea>
  );
}
