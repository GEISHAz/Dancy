import React, { useState } from "react";
import * as RP from "./ResultPage.style";
import SearchItem from "./SearchItem";
import { useRecoilValue } from 'recoil';
import { searchResultsState, searchKeywordState } from '../../recoil/SearchState';

export default function ResultPage() {
  const nickColor = { color: "#252525" };
  const [activeButton, setActiveButton] = useState("title");
  const searchResults = useRecoilValue(searchResultsState);
  const searchKeyword = useRecoilValue(searchKeywordState);

  const archiveHandler = (lookWhere) => {
    setActiveButton(lookWhere);
  };

  return (
    <RP.SearchResultArea>
      <RP.SearchContainer>
        <RP.SearchHeader>
          <RP.Archive>
            <b style={nickColor}>"{searchKeyword}"</b>의 검색 결과
          </RP.Archive>
          <RP.BtnBox>
            <RP.BtnContainer>
              <RP.ArchiveBtn
                onClick={() => archiveHandler("title")}
                $active={activeButton === "title"}
              >
                제목
              </RP.ArchiveBtn>
              <RP.ArchiveBtnBg $active={activeButton === "title"} />
            </RP.BtnContainer>
            <RP.BtnContainer>
              <RP.ArchiveBtn
                onClick={() => archiveHandler("nickname")}
                $active={activeButton === "nickname"}
              >
                닉네임
              </RP.ArchiveBtn>
              <RP.ArchiveBtnBg $active={activeButton === "nickname"} />
            </RP.BtnContainer>
          </RP.BtnBox>
        </RP.SearchHeader>
        <RP.FeedBody>
          <SearchItem searchResults={searchResults} activeButton={activeButton} searchKeyword={searchKeyword}/>
        </RP.FeedBody>
      </RP.SearchContainer>
    </RP.SearchResultArea>
  );
}
