import styled from "styled-components";
import downBtn from "../..//assets/Practice/downloadBtn.png";

export const Container = styled.div`
  padding-right: 2px;
  display: flex;
  justify-content: end;
`;

export const Label = styled.div`
  height: 49px;
  border: 1px solid black;
  font-family: "NYJ Gothic";
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const LabelTitle = styled(Label)`
  width: 90px;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  background-color: #b1c0ff;
  font-size: 16px;
`;

export const LabelAccuracy = styled(Label)`
  width: 168px;
  border-left: none;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #eff3ff;
  font-size: 24px;
`;

export const Btns = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  column-gap: 22px;
`;

export const DownloadBtn = styled.button`
  width: 34px;
  height: 34px;
  background-image: url(${downBtn});
  background-size: cover;
`;

export const Toggle = styled.div`
  height: 34px;
  display: flex;
  justify-content: center;
  align-items: center;
  column-gap: 7px;
`;

export const OnOffTxt = styled.div`
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 20px;
`;

export const OnOffBtnContain = styled.div`
  width: 70px;
  height: 34px;
  text-align: center;
  margin: 50px auto;
  /* z-index: -1; */
`;

export const OnOffSwitch = styled.input.attrs({
  type: "checkbox",
  id: "switch",
})`
  position: absolute;
  /* hidden */
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
`;

export const OnOffSwitchLabel = styled.label`
  position: relative;
  cursor: pointer;
  display: inline-block;
  width: 70px;
  height: 34px;
  background: #ffa2a2;
  border: 1px solid #000000;
  border-radius: 20px;
  transition: 0.2s;

  /* checking style */
  ${OnOffSwitch}:checked + & {
    background: #ffffff;
  }
`;

export const OnOffBtn = styled.div`
  position: absolute;
  top: 4px;
  left: 3px;
  display: inline-block;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  background: #ffffff;
  transition: 0.2s;

  ${OnOffSwitch}:checked + ${OnOffSwitchLabel} & {
    left: 40px;
    background: #ff5d5d;
  }
`;
