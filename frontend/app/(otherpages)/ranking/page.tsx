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

const NanumBarunGothicLight = localfont({
  src: [
    {
      path: "../../../public/fonts/NanumBarunGothicLight.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

const Ranking = () => {
  return (
    <div
      className={`flex flex-col justify-between h-screen max-w-screen-2xl lg:flex-row mx-auto ${NanumBarunGothicLight.className}`}
    >
      <div className="xl:w-1/4 bg-indigo-50">
        <div className="mt-20 ml-10">
          <div className="font-bold">Hot Stock</div>
          <p>실시간 뉴스들의 키워드를 알고싶다면?</p>
          
        </div>
        
      </div>
      <div className="xl:w-3/4 mt-20 px-20 xl:pr-16">
        <div>현재 시간 기준 랭킹</div>
        <RankingList />
      </div>
    </div>
  );
};

export default Ranking;
