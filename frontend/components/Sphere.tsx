"use client";

import React, { useEffect, useRef } from "react";
import { useRouter } from "next/navigation";
import TagCloud, { TagCloudOptions } from "TagCloud"; // TagCloud 라이브러리 사용.
import { KeywordProps } from "@/types";
import { fetchKeywordDetail } from "@/utils";
import "../styles/Sphere.css";

interface keywordTypes {
  data: string[];
  fullData: KeywordProps[];
}

const TextSphere = ({ data, fullData }: keywordTypes) => {
  const containerRef = useRef(null);

  useEffect(() => {
    const texts: Array<string> = data;
    const options: TagCloudOptions = {
      radius: 380,
      maxSpeed: "fast",
      initSpeed: "fast",
      // direction: 135,
      keep: true,
    };

    if (containerRef.current) {
      TagCloud(containerRef.current, texts, options);
    }
  }, []);

  const router = useRouter();

  const findKeywordId = (innerText: string) => {
    for (let i = 0; i < fullData.length; i++) {
      if (fullData[i].text == innerText) {
        return fullData[i].id;
      }
    }
  };

  const clickevent = async (
    e: React.MouseEvent<HTMLSpanElement, MouseEvent>
  ) => {
    const target = e.target as HTMLElement;

    if (target.className === "tagcloud--item") {
      console.log(target.innerText);
      const keywordId = findKeywordId(target.innerText);
      const id = keywordId !== undefined ? keywordId : 0;
      const getKeywordDetail = await fetchKeywordDetail(id);

      const firstThemeIdOfKeyword =
        getKeywordDetail.themeByKeywordIdResponseDtoList.length > 0
          ? getKeywordDetail.themeByKeywordIdResponseDtoList[0].themeId
          : 0;
      const firstThemeId: string = firstThemeIdOfKeyword.toString();
      router.push(`/keyword/${id}/${firstThemeId}`);
    }
  };

  return (
    <div className="flex w-full h-screen justify-center items-center">
      <span className="tagcloud" ref={containerRef} onClick={clickevent}></span>
    </div>
  );
};

export default TextSphere;
