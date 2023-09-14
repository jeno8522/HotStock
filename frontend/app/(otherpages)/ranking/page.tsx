import { RankingList } from "@/components";
import localfont from "next/font/local";

const Cafe24SsurroundAir = localfont({
  src: [
    {
      path: "../../../public/fonts/Cafe24SsurroundAir.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

const Ranking = () => {
  return (
    <div
      className={`max-w-screen-xl px-8 xl:px-16 mx-auto ${Cafe24SsurroundAir.className}`}
    >
      <div className="flex flex-col m-5 justify-center lg:items-end row-start-2 sm:flex-row sm:justify-start sm:items-start">
        <h3
          className="
                    font-bold
                    text-2xl
                    text-gray-700"
        >
          키워드 랭킹 페이지
        </h3>
        <p
          className="
                    text-gray-700"
        >
          지금 가장 핫한 키워드들을 보여드려요
        </p>
      </div>
      <div>
        <RankingList />
      </div>
    </div>
  );
};

export default Ranking;
