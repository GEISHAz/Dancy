import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {CardContainer, CardUpperContainer, CardLowerContainer, CardDetailContainer, CardDetailArea, CardProfileImage, CardTitle, CardUserName, CardViewAndDate } from './Card.Style'
import { allArticles } from "../../api/stage";

// 사용할 색상 배열
const colors = ["#fffbe5", "#d8fcf6", "#dfe5fe"]; 

export default function Card() {
  const [articles, setArticles] = useState([])

  useEffect(() => {
    allArticles()
    .then ((res) => {
      console.log(res)
      setArticles(res)
    })
    .catch ((err) => {
      console.error(err)
    })
  }, [])

  const handleClick = () => {
    const videoUrl = "your_video_url_here.mp4";
    onClick(videoUrl)
  }
  const cards = articles.map((_, index) => {
    // 줄 별로 색상 선택
    const color = colors[Math.floor(index / 3) % colors.length];
    const item = articles[index % articles.length]
    
    return (
      <Link to={`/detail/${item.articleId}`} key={index}>
        <CardContainer key={index} onClick={handleClick}>
          <CardUpperContainer src={item.articleThumbnail} />
          <CardLowerContainer color={color}>
            <CardDetailContainer>
              <CardProfileImage src={item.authorProfileImage} />
              <CardDetailArea>
                <CardTitle>{item.articleTitle}</CardTitle>
                <CardUserName>{item.authorName}</CardUserName>
                <CardViewAndDate>
                  조회 수 {item.articleView}회 |{" "}
                  {/* {item.created_at.toLocaleDateString()} */}
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

