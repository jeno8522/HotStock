"use client"
import React from "react";
import Link from "next/link";
import Image from "next/image";
import logo from "../public/images/hotstocklogo.png";
import {Input} from "@nextui-org/react";
import searchIcon from "../public/images/icons/searchicon.png"
import { useState, useRef } from "react";
import {useRouter}  from "next/navigation";


export default function Navbar() {
  const [showInput, setShowInput] = useState<boolean>(false);
  const [searchInput, setSearchInput ] = useState<string>(""); 
  const searchInputRef = useRef<HTMLInputElement>(null);
  const router = useRouter()
  
  // 검색토글 여는 것
  const onSearchToggle = (e: React.MouseEvent<HTMLDivElement>) => {
    if (!searchInputRef.current) return;

    // console.log("열어");
    e.preventDefault();
    setShowInput(true);
    setSearchInput("");
    // console.log(showInput);
    // searchInputRef.current.focus();
  };

  // 입력값 받고 useState에 저장하는것.
  const updateSearchInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchInput(e.target.value);
  }

  // 검색 시키는거 - 라우팅ㅇ르 시키든 옮기든 해야됨
  const onSearch = () => {
    if(searchInput !== ""){
      console.log(searchInput);
      router.push(`/search/${searchInput}`);
      setSearchInput("");
  
    }
    setShowInput(false);
    
  };

  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      onSearch();
    }
  };

  return (
    <header>
      <nav
        className="mx-auto flex justify-between items-center sm:px-10 px-6 py-2 bg-[#2A509D]"
      >
        <div className="flex items-center justify-evenly gap-5 xs:gap-2">
          <Link href="/">
            <Image src={logo} alt="logo" width={90} className="object-contain" />
          </Link>
          <div>
            <Link className="px-5 text-white hover:text-black" href="/ranking">
              랭킹
            </Link>
            <Link className="px-5 text-white" href="/theme">
              업종
            </Link>
            <Link className="px-5 text-white" href="/aboutus">
              더보기
            </Link>
          </div>
        </div>
        <div>
          <div className="flex">
            <div className={`flex w-[15rem] flex-wrap md:flex-nowrap mb-6 md:mb-0 gap-4`}>
              <Input className={`transition-all duration-300 ${showInput ? "w-[15rem]" : "w-0 invisible"}`} type="email" ref={searchInputRef} color="primary" size="sm" onChange={updateSearchInput} onKeyDown={handleKeyPress} value={searchInput} placeholder="종목명 혹은 종목코드 검색" />
            </div>
            <div onClick={ showInput ? onSearch : onSearchToggle }>
                <Image src={searchIcon} width={32} className="p-1 ml-2 hover:cursor-pointer " alt="search"/>
              </div>
          </div>
        </div>
      </nav>
    </header>
  );
}
