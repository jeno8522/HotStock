"use client";

import getScrollAnimation from "@/utils/scroll";
import ScrollAnimationWrapper from "../ScrollAnimationWrapper";
import { motion } from "framer-motion";
import React, { useMemo } from "react";
import Image from "next/image";
import Link from "next/link";
import analyticImage from "@/public/images/undraw_Predictive_analytics_re_wxt8.png";

const Top = () => {
  const scrollAnimation = useMemo(() => getScrollAnimation(), []);

  return (
    <div className="max-w-screen-xl px-24 xl:px-32 mx-auto" id="about">
      <ScrollAnimationWrapper>
        <motion.div
          className="grid grid-flow-row sm:grid-flow-col grid-rows-2 md:grid-rows-1 sm:grid-cols-2 gap-8 py-32 my-10 sm:py-24"
          variants={scrollAnimation}
        >
          <div className="flex flex-col justify-center items-start row-start-2 sm:row-start-1">
            <h1
              className={` text-2xl lg:text-3xl xl:text-4xl font-medium text-black-600 leading-normal`}
            >
              지금 <span className={`text-orange-500`}>HOT</span> 키워드가 뭐지?
            </h1>
            <h1 className="text-2xl-black mt-4 mb-4">
              실시간 뉴스 기사에서 가장 많이 언급된 키워드들을 확인하세요
            </h1>

            <Link href="/ranking">
              <button className="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-md shadow-md transition duration-300 ease-in-out mb-12">
                랭킹 바로가기
              </button>
            </Link>

            <h1
              className={` text-2xl lg:text-3xl xl:text-4xl font-medium text-black-600 leading-normal`}
            >
              그렇다면, 어떤 주식이{" "}
              <span className={`text-orange-500`}>HOT</span> 하지?
            </h1>
            <h1 className="text-2xl-black mt-4 mb-4">
              키워드와 관련된 테마, 주식 종목을 확인하세요
            </h1>
            <Link href="/theme">
              <button className="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-md shadow-md transition duration-300 ease-in-out mb-12">
                테마 바로가기
              </button>
            </Link>
          </div>
          <div className="flex w-full">
            <motion.div className="h-full w-full" variants={scrollAnimation}>
              <Image
                src={analyticImage}
                alt="analytics"
                quality={100}
                layout="responsive"
              />
            </motion.div>
          </div>
        </motion.div>
      </ScrollAnimationWrapper>
    </div>
  );
};

export default Top;
