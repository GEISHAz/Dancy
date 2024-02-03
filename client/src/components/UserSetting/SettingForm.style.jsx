import { styled } from "styled-components";

// Dancy 로고
export const JoinLogo = styled.div`
  width: 200px;
  height: 168px;
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 100%;
    height: 100%;
  }
`;

//회원정보 입력 title
export const FormTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic B";
  font-size: 28px;
  font-weight: 400;
  margin-left: 16px;
`;

// 프로필 사진 로고
export const ProfileAvatar = styled.div`
  width: 128px;
  height: 128px;
  border-radius: 50%;
  border-color: black;
  border: 1px solid;
  background-color: #e7e4e4;
  background-size: contain;
  background-position: center center;
  background-size: 100% 100%;
`;

export const ProfileLogo = styled(ProfileAvatar)`
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 60%;
    height: 60%;
  }
  margin-right: ${(props) => props.margin || "0px"};
`;

//프로필 사진 안내 text
export const PhotoNotice = styled.div`
  color: black;
  font-family: "NanumSquareRound";
  font-size: 14px;
`;

// 버튼
export const FormBtn = styled.button`
  width: ${(props) => props.width || "99px"};
  height: 46px;
  border: 1px solid;
  border-color: black;
  border-radius: 5px;
  background-color: #e23e59;
  color: #ffffff;
  font-family: "NYJ Gothic B";
  font-size: 16px;
  cursor: pointer;

  &:hover {
    background-color: #c0354c;
  }
`;

// 완료 버튼
export const RegisterBtn = styled.button`
  width: 167px;
  height: 46px;
  border: 1px solid;
  border-color: black;
  border-radius: 5px;
  background-color: #e23e59;
  color: #ffffff;
  font-family: "NYJ Gothic B";
  font-size: 16px;
  cursor: pointer;
  margin-left: ${(props) => props.margin || "0px"};

  &:hover {
    background-color: #c0354c;
  }
`;

//필수 입력 아이콘
export const MustIcon = styled.div`
  width: 10px;
  height: 10px;
  border: 1px solid;
  border-color: black;
  border-radius: 5px;
  background-color: #e23e59;
  visibility: ${(props) => props.visibility || "visible"};
`;

// 필수 입력 안내 text
export const MustNoticeText = styled.div`
  color: #6f6f6f;
  font-family: "NanumSquareRound";
  font-size: 16px;
  line-height: normal;
`;

// form 항목 text
export const FormCategory = styled.div`
  color: black;
  font-family: "NYJ Gothic B";
  font-size: 20px;
  font-weight: 400;
  line-height: normal;
  margin-right: ${(props) => props.margin || "5px"};
`;

// form input
export const FormInput = styled.input`
  border: 1px solid black;
  border-radius: 5px;
  width: 412px;
  height: 46px;
  padding: 10px;
  font-family: "NYJ Gothic L";
  font-size: 16px;
  align-self: flex-start;

  &:focus {
    outline: 2px solid #e23e59;
    border: none;
  }
`;

// input 안내문구 -> 형식 체크
export const InputNoticeText = styled.div`
  position: absolute;
  bottom: -24px;
  right: 0;
  font-family: "NYJ Gothic L";
  font-size: 16px;
  font-weight: 400;
  line-height: nomal;
  color: #e2030f;
  margin-top: 12px;
  display: ${(props) => (props.show ? "block" : "none")};
`;

// 회원 탈퇴 텍스트
export const QuitText = styled.div`
  font-family: "NYJ Gothic L";
  font-size: 12px;
  color: #ababab;
  text-decoration: underline;
  margin-top: 32px;
`;
