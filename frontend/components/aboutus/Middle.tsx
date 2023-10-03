"use client";

import getScrollAnimation from "@/utils/scroll";
import ScrollAnimationWrapper from "../ScrollAnimationWrapper";
import { motion } from "framer-motion";
import React, { useMemo } from "react";
import Image from "next/image";
import backG from "@/public/images/manh.jpg";

const Middle = () => {
  const scrollAnimation = useMemo(() => getScrollAnimation(), []);
  return (
    <div className="relative h-screen w-full">
      <div className="absolute bg-neww inset-0 bg-cover bg-blur opacity-40"></div>

      <div className="relative opacity-100">
        <div className="max-w-screen-xl px-8 xl:px-16 mx-auto" id="about">
          <ScrollAnimationWrapper>
            <motion.div
              className="gap-8 py-6 sm:py-16"
              variants={scrollAnimation}
            >
              <div className="flex flex-col justify-center items-center py-10 drop-shadow-lg">
                <h1 className="aboutus-middle-title">
                  세상의 수많은 소식과 기사들
                </h1>
                <h1 className="aboutus-middle-title">
                  Hot Stock은 실시간으로 기사를 수집해 키워드로 요약합니다
                </h1>
                <br />
                <h2 className="text-gray-500 text-2xl">
                  지금 가장 Hot한 키워드로 원하는 종목을 찾아줄 단 하나의 플랫폼
                </h2>
                <div className="border-[#a0a0a0] border-1 border-x-0 my-10 border-b-0 w-1/3" />
                <br />
                <div className="text-gray-500 text-2xl my-5 italic">
                  Hot Stock은
                </div>
                <div className="grid grid-cols-3 text-[20px] text-gray-600 font-bold">
                  <div className="p-10 w-96">
                    <div className="text-[30px] mb-5 font-extrabold text-blue-950">
                      1
                    </div>
                    <h3>10분마다 지속적으로 세상의 뉴스들을 수집합니다</h3>
                  </div>
                  <div className="p-10 w-96">
                    <div className="text-[30px] mb-5 font-extrabold text-blue-950">
                      2
                    </div>
                    <h3>
                      수집된 뉴스들을 키워드로 요약하고, 빈도에 따른 랭킹을
                      만듭니다
                    </h3>
                  </div>
                  <div className="p-10 w-96">
                    <div className="text-[30px] mb-5 font-extrabold text-blue-950">
                      3
                    </div>
                    <h3>
                      선별된 키워드에 적절한 주식 테마를 제공하며, 각 테마에
                      해당하는 주식 종목을 조회해드립니다
                    </h3>
                  </div>
                </div>
              </div>
            </motion.div>
          </ScrollAnimationWrapper>
        </div>
      </div>
    </div>
  );
};

export default Middle;
