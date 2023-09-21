import { Keyword, Theme } from "@/types";
import { ArticleCard, StockBar } from "@/components";
import { fetchKeywordDetail, fetchStockByTheme } from "@/utils";
import Link from "next/link";

const dummy: Keyword[] = [
  {
    id: 1,
    name: "반도체",
    themes: [
      {
        id: 12,
        name: "반도체의 첫번째 테마",
      },
      {
        id: 13,
        name: "반도체의 두번째 테마",
      },
    ],

    newslist: [
      {
        id: 5453245,
        title: "반도체 기사 1번 제목임",
        content: "반도체 기사 1번 내용임",
        date: "string",
        company: 23,
        url: "",
      },
      {
        id: 412345,
        title: "반도체 기사 2번 제목임",
        content: "반도체 기사 2번 내용임",
        date: "string",
        company: 23,
        url: "",
      },
    ],
  },
  {
    id: 2,
    name: "초전도체",
    themes: [
      {
        id: 21,
        name: "초전도체의 첫번째 테마",
      },
      {
        id: 22,
        name: "초전도체의 두번째 테마",
      },
    ],

    newslist: [
      {
        id: 98562,
        title: "초전도체 기사 1번 제목임",
        content: "초전도체 기사 1번 내용임",
        date: "string",
        company: 23,
        url: "",
      },
      {
        id: 18324,
        title: "초전도체 기사 2번 제목임",
        content: "초전도체 기사 2번 내용임",
        date: "string",
        company: 23,
        url: "",
      },
    ],
  },
];

const KeywordDetailWithTheme = async ({
  params,
}: {
  params: { key: string; themeId: string };
}) => {
  // ----------------------------------------
  // 더미데이터 활용하려고 작성함
  // api 호출하면 response된 데이터로 받을거니까 아래 내용은 필요없을것임
  const keyNumber = parseInt(params.key, 10);
  const themeNumber = parseInt(params.themeId, 10);

  // const keywordDetails = await fetchKeywordDetail(keyNumber);
  // const stockList = await fetchStockByTheme(themeNumber);
  // console.log(stockList);

  const stockIsEmpty =
    !Array.isArray(stockList) || stockList.length < 1 || !stockList;

  const selectedKeyword: Keyword = {
    name: "",
    themes: [],
    id: 0,
    newslist: [],
  };
  const selectedTheme: Theme = {
    id: 0,
    name: "",
  };

  for (const keyword of dummy) {
    if (keyword.id === keyNumber) {
      selectedKeyword.name = keyword.name;
      selectedKeyword.themes = keyword.themes;
      selectedKeyword.id = keyword.id;
      selectedKeyword.newslist = keyword.newslist;
      break;
    }
  }

  for (const theme of selectedKeyword.themes) {
    if (theme.id === themeNumber) {
      selectedTheme.name = theme.name;
    }
  }

  // ----------------------------------------
  return (
    <div className="max-w-screen-lg px-8 xl:px-10 mt-10 mx-auto">
      <div className="flex xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
        {/* 왼쪽 이름 탭 */}
        <div className="text-[30px] text-gray-700 drop-shadow-[0_5px_5px_rgba(0,0,0,0.4)] xl:w-1/5 font-bold">
          {selectedKeyword.name}
        </div>
        {/* 오른쪽 정보 탭  */}
        <div className="items-center xl:w-4/5">
          {/* 테마 탭 */}
          <div className="flex">
            {selectedKeyword.themes.map((themeItem, index) => (
              <div className="text-[20px] pr-5" key={index}>
                {themeItem.id === themeNumber ? (
                  <div className="font-bold">
                    <Link
                      href={`/keyword/${selectedKeyword.id}/${themeItem.id}`}
                    >
                      {themeItem.name}
                    </Link>
                  </div>
                ) : (
                  <div>
                    <Link
                      href={`/keyword/${selectedKeyword.id}/${themeItem.id}`}
                    >
                      {themeItem.name}
                    </Link>
                  </div>
                )}
              </div>
            ))}
          </div>
          <div>
            {!stockIsEmpty ? (
              <div>
                {stockList?.map((stock, index) => (
                  // <div key={index}>{stock.name}</div>
                  <StockBar key={index} stock={stock} />
                ))}
              </div>
            ) : (
              <div>이 테마에 해당하는 종목이 존재하지 않습니다.</div>
            )}
          </div>
          {/* 기사 탭 */}
          <div className="flex items-center text-[20px]">관련 기사</div>
          <div>
            {selectedKeyword.newslist.map((news, index) => (
              <div key={index}>
                <ArticleCard news={news} />
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default KeywordDetailWithTheme;

const stockList = [
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
        company: 28,
        url: "https://www.naver.com/",
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
