import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from 'recoil';
import { searchKeywordState, searchResultsState } from '../../recoil/SearchState';
import { searchResultList } from '../../api/searchResult';
import * as SB from './SearchBar.style'

export default function SearchBar({ cardDetails }) {
  const [placeholder, setPlaceholder] = useState("search");
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();
  const [searchKeyword, setSearchKeyword] = useRecoilState(searchKeywordState);
  const [, setSearchResults] = useRecoilState(searchResultsState);

  // 검색어 입력 핸들러
  const handleSearchChange = (event) => {
    const searchText = event.target.value;
    setSearchTerm(searchText);
  };

  // 검색 실행 핸들러
  const handleSearchSubmit = async () => {
    setSearchKeyword(searchTerm);
    try {
      const res = await searchResultList(searchTerm);
      setSearchResults(res);
      navigate(`/results?query=${searchTerm}`);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <SB.SearchContainer>
      <SB.SearchBarOutline />
      <SB.SearchBarInput
        type="text"
        placeholder={placeholder}
        onFocus={() => setPlaceholder("")}
        onBlur={() => setPlaceholder("search")}
        onChange={handleSearchChange}
      />
      <SB.SearchButtonContainer>
        <SB.SearchButtonImage src="/src/assets/search.png" onClick={handleSearchSubmit}/>
      </SB.SearchButtonContainer>
    </SB.SearchContainer>
  );
};