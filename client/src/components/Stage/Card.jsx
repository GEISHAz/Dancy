import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import * as C from './Card.Style'
import { allArticles } from "../../api/stage";

// 사용할 색상 배열
const colors = ["#fffbe5", "#d8fcf6", "#dfe5fe"]; 

export default function Card() {
  const [articles, setArticles] = useState([])

  useEffect(() => {
    allArticles()
    .then ((res) => {
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
        <C.CardContainer key={index} onClick={handleClick}>
          <C.CardUpperContainer src={item.articleThumbnail} />
          <C.CardLowerContainer color={color}>
            <C.CardDetailContainer>
              <C.CardProfileImage src={item.authorProfileImage} />
              <C.CardDetailArea>
                <C.CardTitle>{item.articleTitle}</C.CardTitle>
                <C.CardUserName>{item.authorName}</C.CardUserName>
                <C.CardViewAndDate>
                  조회 수 {item.articleView}회 |{" "} &nbsp;
                  {/* {item.created_at.toLocaleDateString()} */}
                  {`${item.createdDate[0]}. ${item.createdDate[1]}. ${item.createdDate[2]}.`}

                </C.CardViewAndDate>
              </C.CardDetailArea>
            </C.CardDetailContainer>
          </C.CardLowerContainer>
        </C.CardContainer>
      </Link>
    );
  });

  return (
    <>{cards}</>
  );
}

