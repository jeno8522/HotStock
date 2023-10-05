import { RankingList, RankingNews } from "@/components";
import localfont from "next/font/local";
import { fetchKeywords } from "@/utils";
import { KeywordProps } from "@/types/index";
import Link from "next/link";

const NanumBarunGothicLight = localfont({
  src: [
    {
      path: "../../../public/fonts/NanumBarunGothicLight.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

interface keywordPropList {
  allKeywords: KeywordProps[];
}

const Ranking = async () => {
  let today = new Date();

  let year = today.getFullYear(); // 년도
  let month = today.getMonth() + 1; // 월
  let date = today.getDate(); // 날짜
  let day = today.getDay(); // 요일
  let hours = today.getHours(); // 시
  let minutes = today.getMinutes(); // 분

  const allKeywords = await fetchKeywords();

  const keywordForNews: KeywordProps[] = [];
  for (let i = 0; i < 3; i++) {
    keywordForNews.push(allKeywords[i]);
  }

  // console.log(keywordForNews);

  return (
    <div className="flex flex-col justify-between h-screen lg:flex-row">
      <div className="lg:w-1/4 bg-[#24364d] text-white">
        <div className="mt-40 mx-10">
          <h2 className="text-lg">최신 뉴스를 종합한 실시간 키워드 랭킹</h2>
          <div className="text-3xl tracking-wider">
            <h1 className="ranking_title">Hot Stock</h1>
          </div>
          <h2 className="mt-20 text-sm text-end">
            키워드를 클릭해 관련 주식 테마를 알아보세요
          </h2>

          <h2 className="text-sm text-end  sm:mb-5">
            Hot Stock이 알고싶다면?{" "}
            <Link href="/aboutus" className="hover:font-bold">
              여기
            </Link>
          </h2>
        </div>
      </div>
      <div className=" lg:w-3/4 ">
        <div className="ranking_right_box">
          <div className="my-10 px-36 xl:pr-16 overflow-auto">
            <div className="p-3 font-bold text-gray-500 text-end">
              {year}년 {month}월 {date}일 {hours}:{minutes} 현재
            </div>
            <RankingList allKeywords={allKeywords} />
          </div>
          <div className="border-[#24364d] border-8 border-x-0 border-b-0 my-3" />
          <div className="my-10 px-36  overflow-auto">
            <RankingNews keywords={keywordForNews} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Ranking;
