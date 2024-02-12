import DancyImg from "../../assets/join/BigLogo.png";
import * as SF from "./SettingForm.style";
import Form from "./Form";
import FormHeader from "./FormHeader";

export default function SettingForm() {
  return (
    <SF.JoinArea>
      <SF.AlignArea>
        <SF.LogoArea>
          <SF.CenterContainer>
            <SF.JoinLogo>
              <img src={DancyImg}></img>
            </SF.JoinLogo>
            <SF.FormTitle>회원 상세정보</SF.FormTitle>
          </SF.CenterContainer>
        </SF.LogoArea>
        <SF.ContextArea>
          <SF.CenterContainer>
            <FormHeader />
            <Form />
          </SF.CenterContainer>
        </SF.ContextArea>
      </SF.AlignArea>
    </SF.JoinArea>
  );
}
