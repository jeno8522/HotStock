import { Keyword, Theme, News, Stock } from "@/types";
import { ArticleCard, StockBar } from "@/components";
import { fetchKeywordDetail, fetchContentsByTheme } from "@/utils";
import Link from "next/link";

const KeywordDetailWithTheme = async ({
  params,
}: {
  params: { key: string; themeId: string };
}) => {
  // ----------------------------------------
  const keyNumber = parseInt(params.key, 10);
  const themeNumber = parseInt(params.themeId, 10);

  const keywordDetails = await fetchKeywordDetail(keyNumber);
  const themeResult = await fetchContentsByTheme(themeNumber);
  const stockList = themeResult.stock;
  // console.log(stockList);

  const stockIsEmpty =
    !Array.isArray(stockList) || stockList.length < 1 || !stockList;

  // ----------------------------------------
  return (
    <div className="max-w-screen-lg px-8 xl:px-10 mt-10 mx-auto">
      <div className="flex xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
        {/* 왼쪽 이름 탭 */}
        <div className="text-[30px] text-gray-700 drop-shadow-[0_5px_5px_rgba(0,0,0,0.4)] xl:w-1/5 font-bold">
          {keywordDetails.keywordContent}
        </div>
        {/* 오른쪽 정보 탭  */}
        <div className="items-center xl:w-4/5">
          {/* 테마 탭 */}
          <div className="flex">
            {keywordDetails.themeByKeywordIdResponseDtoList.map(
              (themeItem: Theme, index: number) => (
                <div className="text-[20px] pr-5" key={index}>
                  {themeItem.themeId === themeNumber ? (
                    <div className="font-bold">
                      <Link href={`/keyword/${keyNumber}/${themeItem.themeId}`}>
                        {themeItem.name}
                      </Link>
                    </div>
                  ) : (
                    <div>
                      <Link href={`/keyword/${keyNumber}/${themeItem.themeId}`}>
                        {themeItem.name}
                      </Link>
                    </div>
                  )}
                </div>
              )
            )}
          </div>
          <div>
            {!stockIsEmpty ? (
              <div>
                {stockList.map((stock: Stock, index: number) => (
                  // <div key={index}>{stock.name}</div>
                  <StockBar key={index} stock={stock} />
                ))}
                {/* {stockDummy.map((stock: Stock, index: number) => (
                  // <div key={index}>{stock.name}</div>
                  <StockBar key={index} stock={stock} />
                ))} */}
              </div>
            ) : (
              <div>이 테마에 해당하는 종목이 존재하지 않습니다.</div>
            )}
          </div>
          {/* 기사 탭 */}
          <div className="flex items-center text-[20px]">관련 기사</div>
          <div>
            {keywordDetails.newsByKeywordIdResponseDtoList.map(
              (news: News, index: number) => (
                <div key={index}>
                  <ArticleCard news={news} />
                </div>
              )
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default KeywordDetailWithTheme;

const stockDummy: Stock[] = [
  {
    stockName: "1번종목종목종목가나다라마밧가",
    code: "858757",
    openPrice: 0,
    currPrice: 5700,
    fluctuationRate: -0.71,
    diff: 200,
    tradingVolume: 14770506,
    highPrice: 5830,
    lowPrice: 5650,
    newslist: [
      {
        id: 123,
        title: "이건기사제목이야",
        date: "2023-09-19",
        content: "이건기사내용이야",
        mediaCompanyNum: 28,
        link: "https://www.naver.com/",
      },
    ],
  },
  {
    stockName: "2번종목",
    code: "808080",
    openPrice: 0,
    currPrice: 5700,
    fluctuationRate: 0.71,
    diff: 200,
    tradingVolume: 14770506,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "3번종목",
    code: "989898",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "4번종목",
    code: "579598",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "5번종목",
    code: "367536",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
];
