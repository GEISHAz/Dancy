import React, { useState, useEffect, useRef } from "react";
import {
  NotificationImage,
  DropdownContainer,
  NotificationTitle,
  DropdownMenu,
  DropdownMenuContainer,
  DropdownItemContainer,
  DropdownItem,
  ProfileImage,
  NotificationContent,
  UserName,
  NotificationText,
  TimeStamp,
  NotificationArea,
} from "./Notification.style";
import { useSpring, animated } from "react-spring";
import { Link } from "react-router-dom";
import { useRecoilState } from "recoil";
import { alarmOccuredState } from "../../recoil/AlarmState";

export default function Notification() {
  const [isActive, setIsActive] = useState(false);
  const dropdownRef = useRef(null);
  const [isAlarmOccur, setIsAlarmOccur] = useRecoilState(alarmOccuredState);
  const [alarms, setAlarms] = useState([]);

  // Notification Form 닫기, 활성화시켜주는 핸들러
  const handleClick = (event) => {
    setIsActive(!isActive);
    setIsAlarmOccur(!alarmOccuredState); // 알람을 확인했으니 배지를 바꿔줍시다.

    event.stopPropagation();
  };

  // 드롭다운 메뉴 내부를 클릭한다면 form이 닫히지 않도록 하는 기능
  const dropdownClickHandler = (event) => {
    event.stopPropagation();
  };

  const dropdownItems = Array(7).fill({
    username: "south.hyun_99",
    notification: "님이 회원님의 게시물을 좋아합니다.",
    user_id: "1021555",
    created_at: new Date(),
  });

  // 현재 시간 기준으로 작성된 시간 차이
  const getTimeDifference = (prevDate) => {
    const diff = new Date() - prevDate;
    const seconds = Math.floor(diff / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
    const weeks = Math.floor(days / 7);
    if (weeks > 0) {
      return `${weeks}주 전`;
    } else if (days > 0) {
      return `${days}일 전`;
    } else if (hours > 0) {
      return `${hours}시간 전`;
    } else if (minutes > 0) {
      return `${minutes}분 전`;
    } else {
      return `${seconds}초 전`;
    }
  };

  // 애니메이션 등장
  const animation = useSpring({
    transform: isActive ? `translate3d(0,0,0)` : `translate3d(0,0,0)`,
    opacity: isActive ? 1 : 0,
  });

  // 드롭다운이 열려 있을 때, 모달의 바깥쪽을 눌렀을 때 창 닫기
  useEffect(() => {
    const clickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsActive(false);
        setIsAlarmOccur(false);
      }
    };

    if (isActive) {
      document.addEventListener("mousedown", clickOutside);
    }

    return () => {
      // 이벤트 활성화 X 부분
      document.removeEventListener("mousedown", clickOutside);
    };
  }, [isActive]);

  // 상태에 따라 다른 이미지 경로 설정
  const getImageSrc = () => {
    if (isAlarmOccur) {
      return "/src/assets/notiLogo.png"; // 알람 발생 시 이미지
    } else {
      return "/src/assets/notification.png"; // 알람 미발생 시 이미지
    }
  };

  // dropdownItems의 길이가 0이면 null 반환
  if (dropdownItems.length === 0) {
    return null;
  }

  return (
    <>
      <NotificationArea>
        <NotificationImage src={getImageSrc()} onClick={handleClick} />
      </NotificationArea>
      {isActive && (
        <animated.div style={animation} onClick={handleClick}>
          <DropdownContainer ref={dropdownRef} onClick={dropdownClickHandler}>
            <NotificationTitle>알람</NotificationTitle>
            <DropdownMenu>
              <DropdownMenuContainer>
                {dropdownItems.map((item, index) => (
                  <DropdownItemContainer key={index}>
                    <DropdownItem>
                      <Link to={`/profile/${item.user_id}`} onClick={handleClick}>
                        <ProfileImage src="/src/assets/profileimage.png" />
                      </Link>
                      <NotificationContent>
                        <Link to={`/profile/${item.username}`} onClick={handleClick}>
                          <UserName>{item.username} </UserName>
                        </Link>
                        <NotificationText>{item.notification}</NotificationText>
                      </NotificationContent>
                    </DropdownItem>
                    {/* created_at 기준으로 넣음 <- ERD 참고 */}
                    <TimeStamp>{getTimeDifference(item.created_at)}</TimeStamp>
                  </DropdownItemContainer>
                ))}
              </DropdownMenuContainer>
            </DropdownMenu>
          </DropdownContainer>
        </animated.div>
      )}
    </>
  );
}
