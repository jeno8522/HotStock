import { Keyword } from "@/types";
import { fetchKeywordDetail, fetchStockByTheme } from "@/utils";

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
  const keywordDetails = await fetchKeywordDetail(params.key);
  const themeDetals = await fetchStockByTheme(params.themeId);
  return <div></div>;
};

export default KeywordDetailWithTheme;
