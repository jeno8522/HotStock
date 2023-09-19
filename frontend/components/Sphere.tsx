"use client"

import React, { useEffect, useRef } from "react";
import TagCloud, {TagCloudOptions} from "TagCloud"; // TagCloud 라이브러리 사용.
// import { TagCloudOptions } from "TagCloud";

import "../styles/Sphere.css"

function TextSphere() {
  const containerRef = useRef(null);

  useEffect(() => {
    const container = containerRef.current;
     const texts: Array<string> = [
        "반도체",
        "초전도체",
        "코로나",
        "윤석열",
        "이재명",
        "주창훈",
        "삼성전자",
        "LG전자",
        "SK하이닉스",
        "카카오",
        "소비자 물가 인상",
        "원유가",
        "유가급등",
        "미국",
        "중국",
        "일본",
        "우크라이나",
        "미국 연준",
        "바이든",
        "트럼프",
        "코로나 백신",
        "코로나 확진자",
        "오염수 방류",
        "미세먼지",
      ];


    const options: TagCloudOptions = {
      radius: 300,
      maxSpeed: "fast",
      initSpeed: "fast",
      // direction: 135,
      keep: true,
    };

    if (container) {
      // 컨테이너가 존재하면 워드 클라우드 초기화
      TagCloud(container, texts, options);
    }
  }, [containerRef]); // containerRef를 의존성 배열에 추가

  return (
    <div className="text-shpere">
      <span className="tagcloud" ref={containerRef}></span>
    </div>
  );
}

export default TextSphere;
