import { Keyword } from "@/types";
import { ArticleCard } from "@/components";

// interface KeywordProps {
//     keyword: Keyword;
// }
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

const KeywordDetail = ({ params }: { params: { key: string } }) => {
  // ----------------------------------------
  // 더미데이터 활용하려고 작성함
  // api 호출하면 response된 데이터로 받을거니까 아래 내용은 필요없을것임
  const keyNumber = parseInt(params.key, 10);
  const selectedKeyword: Keyword = {
    name: "",
    themes: [],
    id: 0,
    newslist: [],
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
  // ----------------------------------------

  return (
    <div className="max-w-screen-xl px-8 xl:px-16 mx-auto">
      <div className="flex items-center xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
        <div className="text-[30px] font-bold">{selectedKeyword.name}</div>
        <div>
          <div className="items-center text-[20px]">
            {selectedKeyword.themes.map((themeItem, index) => (
              <div className="" key={index}>
                {themeItem.name}
              </div>
            ))}
          </div>
          <div>
            여기에 주식들 들어갈거임 위에서 선택한 업종에 따른 주식 상위 N개
          </div>
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

export default KeywordDetail;
