import React from "react";
import { Link } from "react-router-dom";
import {CardContainer, CardUpperContainer, CardLowerContainer, CardDetailContainer, CardDetailArea, CardProfileImage, CardTitle, CardUserName, CardViewAndDate } from './Card.Style'

const cardDetails = [
  {
    username: "namhyun._.2",
    title: "남현이의 춤사위를 보세요",
    created_at: new Date(),
    view: 25,
  },
  {
    username: "jungsuu._.",
    title: "정수의 춤사위를 보세요",
    created_at: new Date(),
    view: "100K",
  },
  {
    username: "sunovo._.2",
    title: "해진이의 춤사위를 보세요",
    created_at: new Date(),
    view: 22,
  },
  {
    username: "seolyeon._.2",
    title: "설연이의 춤사위를 보세요",
    created_at: new Date(),
    view: 25,
  },
  {
    username: "blingblingminhow._.2",
    title: "민호의 춤사위를 보세요",
    created_at: new Date(),
    view: 25,
  },
  {
    username: "dongwooman._.2",
    title: "동우의 춤사위를 보세요",
    created_at: new Date(),
    view: 25,
  },
];


// 사용할 색상 배열
const colors = ["#fffbe5", "#d8fcf6", "#dfe5fe"]; 

export default function CustomCard() {
  const cards = [...Array(12)].map((_, index) => {
    // 줄 별로 색상 선택
    const color = colors[Math.floor(index / 3) % colors.length];
    
    return (
      <Link to={`/detail/${index}`} key={index}>
        <CardContainer key={index}>
          <CardUpperContainer src="/src/assets/thumbnail.png" />
          <CardLowerContainer color={color}>
            <CardDetailContainer>
              <CardProfileImage src="/src/assets/profileimage.png" />
              <CardDetailArea>
                <CardTitle>{cardDetails[index % cardDetails.length].title}</CardTitle>
                <CardUserName>{cardDetails[index % cardDetails.length].username}</CardUserName>
                <CardViewAndDate>
                  조회 수 {cardDetails[index % cardDetails.length].view}회 |{" "}
                  {cardDetails[index % cardDetails.length].created_at.toLocaleDateString()}
                </CardViewAndDate>
              </CardDetailArea>
            </CardDetailContainer>
          </CardLowerContainer>
        </CardContainer>
      </Link>
    );
  });

  return (
    <>{cards}</>
  );
}

