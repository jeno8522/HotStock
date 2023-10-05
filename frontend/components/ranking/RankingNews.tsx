import { getNewsByStock } from "@/utils";
import { RankingCard } from "..";
import { fetchKeywords } from "@/utils";
import { KeywordProps } from "@/types/index";
import { NewsCardForStocks } from "@/components";

interface keywordPropList {
  keywords: KeywordProps[];
}

const RankingNews = async ({ keywords }: keywordPropList) => {
  //   console.log(keywords);

  const newsData1 = await getNewsByStock(`${keywords[0].text}`, 3);
  const newsData2 = await getNewsByStock(`${keywords[1].text}`, 3);
  const newsData3 = await getNewsByStock(`${keywords[2].text}`, 3);
  //   console.log(newsData2);

  const news1IsEmpty =
    !Array.isArray(newsData1) || newsData1.length < 1 || !newsData1;
  const news2IsEmpty =
    !Array.isArray(newsData2) || newsData2.length < 1 || !newsData2;
  const news3IsEmpty =
    !Array.isArray(newsData3) || newsData3.length < 1 || !newsData3;
  return (
    <div className="px-16">
      <div className="text-xl font-bold">주요 키워드 관련 기사 알아보기</div>
      <div className="my-5  mb-10">
        <span className="text-lg font-bold underline decoration-2 underline-offset-4 decoration-blue-800">
          {keywords[0].text}
        </span>
        <span className="text-lg font-bold"> 관련 기사</span>
        {!news1IsEmpty ? (
          newsData1.map((news, index) => (
            <div key={index}>
              <NewsCardForStocks news={news} />
            </div>
          ))
        ) : (
          <div> 해당하는 기사가 없습니다</div>
        )}
      </div>
      <div className="my-5 mb-10">
        <span className="text-lg font-bold underline decoration-2 underline-offset-4 decoration-blue-800">
          {keywords[1].text}
        </span>
        <span className="text-lg font-bold"> 관련 기사</span>
        {!news2IsEmpty ? (
          newsData2.map((news, index) => (
            <div key={index}>
              <NewsCardForStocks news={news} />
            </div>
          ))
        ) : (
          <div> 해당하는 기사가 없습니다</div>
        )}
      </div>
      <div className="mt-5 mb-10">
        <span className="text-lg font-bold underline decoration-2 underline-offset-4 decoration-blue-800">
          {keywords[2].text}
        </span>
        <span className="text-lg font-bold"> 관련 기사</span>
        {!news3IsEmpty ? (
          newsData3.map((news, index) => (
            <div key={index}>
              <NewsCardForStocks news={news} />
            </div>
          ))
        ) : (
          <div> 해당하는 기사가 없습니다</div>
        )}
      </div>
    </div>
  );
};

export default RankingNews;
