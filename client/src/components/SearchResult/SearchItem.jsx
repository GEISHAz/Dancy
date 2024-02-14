import React, { useState, useEffect } from "react";
import * as SI from "./SearchItem.style";
import { Link } from "react-router-dom";

export default function SearchItem({ searchResults, activeButton, searchKeyword }) {
  const [filteredResults, setFilteredResults] = useState([]);

  useEffect(() => {
    // activeButton이 변경될 때마다 filteredResults 업데이트
    const updateFilteredResults = () => {
      const newFilteredResults = activeButton === 'title' 
        ? searchResults.filter(result => result.articleTitle.includes(searchKeyword))
        : searchResults.filter(result => result.authorName.includes(searchKeyword));
      setFilteredResults(newFilteredResults);
    };

    updateFilteredResults();
  }, [searchResults, activeButton, searchKeyword]);

  // 검색 결과가 없는 경우 처리
  if (filteredResults.length === 0) {
    return (
      <SI.VideoListArea>
        <SI.NoResultMessage>검색 결과가 없습니다.</SI.NoResultMessage>
      </SI.VideoListArea>
    );
  }

  return (
    <SI.VideoListArea>
      {filteredResults.map((result, index) => (
        <Link to={`/detail/${result.articleId}`} key={index}>
          <div key={index}>
            <SI.VideoContainer>
              <SI.SearchThumbNail src={result.articleThumbnail}></SI.SearchThumbNail>
              <SI.VideoContentArea>
                <SI.SearchResultCount>
                  <SI.SearchResultTitle>{result.articleTitle}</SI.SearchResultTitle>
                  <SI.SearchViewCreated>조회수 {result.articleView}회 | {result.createdDate}</SI.SearchViewCreated>
                </SI.SearchResultCount>
                <SI.UserContentArea>
                  <SI.SearchResultProfile src={result.authorProfileImage}></SI.SearchResultProfile>
                  <SI.SearchResultAuthor>{result.authorName}님의 비디오</SI.SearchResultAuthor>
                </SI.UserContentArea>
              </SI.VideoContentArea>
            </SI.VideoContainer>
          </div>
        </Link>
      ))}
    </SI.VideoListArea>
  );
}
