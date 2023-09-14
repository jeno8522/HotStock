"use client";

import getScrollAnimation from "@/utils/scroll";
import ScrollAnimationWrapper from "../ScrollAnimationWrapper";
import { motion } from "framer-motion";
import React, { useMemo } from "react";
import Image from "next/image";
import analyticImage from "@/public/images/undraw_Predictive_analytics_re_wxt8.png";

const Top = () => {
  const scrollAnimation = useMemo(() => getScrollAnimation(), []);

  return (
    <div className="max-w-screen-xl px-8 xl:px-16 mx-auto" id="about">
      <ScrollAnimationWrapper>
        <motion.div
          className="grid grid-flow-row sm:grid-flow-col grid-rows-2 md:grid-rows-1 sm:grid-cols-2 gap-8 py-6 sm:py-16"
          variants={scrollAnimation}
        >
          <div className=" flex flex-col justify-center items-start row-start-2 sm:row-start-1">
            <h1
              className={` text-3xl lg:text-4xl xl:text-5xl font-medium text-black-600 leading-normal`}
            >
              지금 가장 핫한 키워드 보여드림 <strong>Hot Stock</strong>.
            </h1>
            <p className="text-black-500 mt-4 mb-6">
              유사 빅데이터 유사 인공지능이지만 그래도 나름 핀테크임 (진짜)
            </p>
            <button>랭킹 바로가기</button>
          </div>
          <div className="flex w-full">
            <motion.div className="h-full w-full" variants={scrollAnimation}>
              <Image
                src={analyticImage}
                alt="analytics"
                quality={100}
                // width={612}
                // height={350}
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
