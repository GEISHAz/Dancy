import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { SearchContainer, SearchBarOutline, SearchBarInput, SearchButtonContainer, SearchButtonImage } from "./SearchBar.style";

export default function SearchBar({ cardDetails }) {
  const [placeholder, setPlaceholder] = useState("search");
  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const navigate = useNavigate();

  // 검색어 입력 핸들러
  const handleSearchChange = (event) => {
    const searchText = event.target.value;
    setSearchTerm(searchText);
    
    // 검색어가 비어 있으면 모든 카드를 표시합니다.
    if (!searchText.trim()) {
      setSearchResults([]);
      return;
    }

    // 검색어가 포함된 카드만 결과로 표시합니다.
    const filteredResults = cardDetails.filter(card => 
      card.title.toLowerCase().includes(searchText.toLowerCase())
    );
    setSearchResults(filteredResults);
  };
  
  // 검색 실행 핸들러
  const handleSearchSubmit = () => {
    // 검색 결과를 보여주는 페이지로 이동
    navigate(`/results?query=${searchTerm}`);
  };

  return (
    <SearchContainer>
      <SearchBarOutline />
      <SearchBarInput
        type="text"
        placeholder={placeholder}
        onFocus={() => setPlaceholder("")}
        onBlur={() => setPlaceholder("search")}
        onChange={handleSearchChange}
      />
      <SearchButtonContainer>
        {/* 검색 기능 만들기 */}
        <SearchButtonImage src="/src/assets/search.png" onClick={handleSearchSubmit}/>
      </SearchButtonContainer>
    </SearchContainer>
  );
};
