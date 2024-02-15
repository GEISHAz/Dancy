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
import { alarmOccuredState, alarmListState } from "../../recoil/AlarmState";
import { allAlarms } from "../../api/alarm";

export default function Notification() {
  const [isActive, setIsActive] = useState(false);
  const dropdownRef = useRef(null);
  const [isAlarmOccur, setIsAlarmOccur] = useRecoilState(alarmOccuredState);
  const [alarms, setAlarms] = useState([]);
  const [alarmList, setAlarmList] = useRecoilState(alarmListState);

  // Notification Form 닫기, 활성화시켜주는 핸들러
  const handleClick = (event) => {
    setIsActive(!isActive);
    setIsAlarmOccur(false); // 알람을 확인했으니 배지를 바꿔줍시다.
    allAlarms()
      .then((res) => {
        console.log("알람리스트잘오니?", res);
        setAlarms(res);
        setAlarmList(res);
      })
      .catch((err) => {
        console.error(err);
      });
    event.stopPropagation();
  };

  // 드롭다운 메뉴 내부를 클릭한다면 form이 닫히지 않도록 하는 기능
  const dropdownClickHandler = (event) => {
    event.stopPropagation();
  };

  const getDateTransfer = (data) => {
    const [year, month, day, hour, minute, second] = data;
    const date = new Date(year, month - 1, day, hour, minute, second);
    console.log("date", date);
    return date;
  };

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

  useEffect(() => {
    // 컴포넌트가 렌더링될 때마다 알림을 가져와서 비교합니다.
    allAlarms()
      .then((res) => {
        console.log("알람리스트잘오니?", res);
        if (alarmList[0].notificationId !== res[0].notificationId) {
          console.log(alarmList[0]);
          console.log(res[0]);
          setIsAlarmOccur(true);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  // 상태에 따라 다른 이미지 경로 설정
  const getImageSrc = () => {
    if (isAlarmOccur) {
      return "/src/assets/notiLogo.png"; // 알람 발생 시 이미지
    } else {
      return "/src/assets/notification.png"; // 알람 미발생 시 이미지
    }
  };

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
                {alarms.map((item, index) => (
                  <DropdownItemContainer key={index}>
                    <DropdownItem>
                      <Link
                        to={`/profile/${item.makerUserNickname}`}
                        onClick={handleClick}
                      >
                        <ProfileImage src={item.makerUserProfileImageUrl} />
                      </Link>
                      <NotificationContent>
                        {item.articleId ? (
                          <Link
                            to={`/detail/${item.articleId}`}
                            onClick={handleClick}
                          >
                            <NotificationText>{item.content}</NotificationText>
                          </Link>
                        ) : (
                          <NotificationText>{item.content}</NotificationText>
                        )}
                      </NotificationContent>
                    </DropdownItem>
                    {/* created_at 기준으로 넣음 <- ERD 참고 */}
                    <TimeStamp>
                      {getTimeDifference(getDateTransfer(item.createdTime))}
                    </TimeStamp>
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
