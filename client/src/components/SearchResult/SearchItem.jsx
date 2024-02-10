import React from "react";
import * as SI from "./SearchItem.style";
import { Link } from "react-router-dom";

export default function SearchItem({ searchResults }) {
  return (
    <SI.VideoListArea>
      {searchResults.map((result, index) => (
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