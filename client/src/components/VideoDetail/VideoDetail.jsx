import React, { useState, useEffect } from "react";
import { VideoDetailContainer, VideoDetailArea, VideoContentArea, VideoTitle, VideoUserDetailArea, VideoUserName, VideoFollowArea, VideoFollower, VideoUserProfileImage, VideoFollowBtn, VideoAccuracyArea, VideoUpperContainer, VideoLowerContainer, VideoUserDetail, BtnContainer, HashTagArea, AccuracyBtn, HashTagBtn, HashTagContainer } from './VideoDetail.style'

const cardDetails = [
  {
    username: "namhyun._.2",
    title: "남현이의 춤사위를 보세요",
    created_at: new Date(),
    view: 25,
  },
];

const Hashtags = [
  { text: "#그녀가", color: "#FFF4B5" },
  { text: "#보고싶어", color: "#C0FFF4" },
  { text: "#ㅠㅠ", color: "#D4DCFF" }
];

export default function VideoDetail() {
  const [follow, setFollow] = useState(false);
  const [cardDetail, setCardDetail] = useState();
  const [hashtags, setHashtags] = useState();

  useEffect(() => {
    setCardDetail(cardDetails);
    setHashtags(Hashtags);
  }, []);

  const toggleFollow = () => {
    setFollow(!follow);
  };

  return (
    <VideoDetailContainer>
      {cardDetail && cardDetail.map((card, index) => (
        <VideoDetailArea key={index}>
          <VideoTitle>{card.title}</VideoTitle>
          <VideoContentArea>
            <VideoUserDetailArea>
              <VideoUserProfileImage />
              <VideoUserDetail>
                <VideoUserName>{card.username}</VideoUserName>
                <VideoFollowArea>
                  <VideoFollower>
                    <div>팔로워 672</div>
                  </VideoFollower>
                  <VideoFollowBtn $follow={follow} onClick={toggleFollow}>
                    <div>{follow ? '팔로잉' : '팔로우'}</div>
                  </VideoFollowBtn>
                </VideoFollowArea>
              </VideoUserDetail>
            </VideoUserDetailArea>
            <VideoAccuracyArea>
              <VideoUpperContainer>
                <div>{card.created_at.toLocaleDateString()} | 조회 수 {card.view}회</div>
              </VideoUpperContainer>
              <VideoLowerContainer>
                <div>너가 내 옆에 없어서 단 한숨의 잠도 이루지 못해..</div>
                <BtnContainer>
                  <AccuracyBtn>87 %</AccuracyBtn>
                  <HashTagContainer>
                    <HashTagArea>
                      {hashtags && hashtags.map((hashtag, index) => (
                        <HashTagBtn key={index} color={hashtag.color}>
                          {hashtag.text}
                        </HashTagBtn>
                      ))}
                    </HashTagArea>
                  </HashTagContainer>
                </BtnContainer>
              </VideoLowerContainer>
            </VideoAccuracyArea>
          </VideoContentArea>
        </VideoDetailArea>
      ))}
    </VideoDetailContainer>
  );
}
