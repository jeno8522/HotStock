import { RankingList } from "@/components";
import localfont from "next/font/local";

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
  let today = new Date();

  let year = today.getFullYear(); // 년도
  let month = today.getMonth() + 1; // 월
  let date = today.getDate(); // 날짜
  let day = today.getDay(); // 요일
  let hours = today.getHours(); // 시
  let minutes = today.getMinutes(); // 분

  console.log(year + "/" + month + "/" + date + "/" + day);

  return (
    <div
      className={`flex flex-col justify-between h-screen max-w-screen-2xl lg:flex-row mx-auto ${NanumBarunGothicLight.className}`}
    >
      <div className="xl:w-1/4 bg-indigo-50">
        <div className="mt-20 ml-10">
          <div className="font-bold">Hot Stock</div>
          <p>실시간 뉴스들의 키워드를 알고싶다면?</p>
          <p>냥냥냥냥냥</p>
        </div>
      </div>
      <div className="text-end xl:w-3/4 mt-20 px-20 xl:pr-16">
        <div className="p-3 font-bold text-gray-500">
          {year}년 {month}월 {date}일 {hours}:{minutes} 기준
        </div>
        <RankingList />
      </div>
    </div>
  );
};

export default Ranking;
