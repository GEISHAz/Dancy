import { styled } from "styled-components";
import * as JF from "./SettingForm.style";
import DefaultImg from "../../assets/join/picture.png";

export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const Header = styled.div`
  display: flex;
  margin-top: 3.5rem;
  margin-bottom: 3.5rem;
  text-align: center;
  justify-content: center;
  align-items: center;
`;

export default function FormHeader() {
  return (
    <Header>
      <JF.ProfileLogo margin="35px">
        <img src={DefaultImg}></img>
      </JF.ProfileLogo>
      <EnterArea margin="114px">
        <JF.PhotoNotice>프로필 사진은 10MB 이하의 파일만 업로드할 수 있습니다.</JF.PhotoNotice>
        <JF.PhotoNotice>(확장자: jpg, jpeg, png, svg)</JF.PhotoNotice>
      </EnterArea>
      <JF.FormBtn>사진 변경</JF.FormBtn>
    </Header>
  );
}
