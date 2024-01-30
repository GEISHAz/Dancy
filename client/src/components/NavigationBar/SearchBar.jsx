import React, { useState } from 'react';

export default function SearchBar(){
  const [placeholder, setPlaceholder] = useState('search');
  
  return (
    <div className="w-[232px] h-[38px] relative">
      <div className="w-[232px] h-[38px] left-0 absolute bg-white rounded-[20px] border-2 border-neutral-800" />
      <input
        type="text"
        className="w-[180px] h-[38px] left-[4px] top-0 absolute placeholder-mainblack bg-transparent border-0 outline-none pl-3"
        placeholder={placeholder}
        onFocus={() => setPlaceholder('')}
        onBlur={() => setPlaceholder('search')}
      />
      <div className="-z-1 w-11 h-[38px] left-[188px] top-0 absolute bg-rose-400 rounded-tr-[20px] rounded-br-[20px] border-2 border-black flex items-center justify-center">
        { /* 검색 기능 만들기 */}
        <img src='/src/assets/search.png' className="z-10 cursor-pointer"/>
      </div>
      <div className="-z-1 w-7 h-7 left-[195px] top-[5px] absolute" />
    </div>
  )
}