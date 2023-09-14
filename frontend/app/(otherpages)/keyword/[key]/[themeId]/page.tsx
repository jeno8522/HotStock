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
        company: "한겨레",
      },
      {
        id: 412345,
        title: "반도체 기사 2번 제목임",
        content: "반도체 기사 2번 내용임",
        date: "string",
        company: "한겨레",
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
        company: "한겨레",
      },
      {
        id: 18324,
        title: "초전도체 기사 2번 제목임",
        content: "초전도체 기사 2번 내용임",
        date: "string",
        company: "한겨레",
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
  // const stockDetails = await fetchStockByTheme(themeNumber);

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
    <div className="max-w-screen-xl px-8 xl:px-10 mt-10 mx-auto">
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
            {/* {stockDetails.stock.map((stocks) => (
              <StockBar stock = {stock}/>
            ))} */}
            <StockBar />
            <StockBar />
            <StockBar />
            <StockBar />
            <StockBar />
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
