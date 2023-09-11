import { Keyword } from "@/types";
import { ArticleCard } from "@/components";

// interface KeywordProps {
//     keyword: Keyword;
// }
const dummy: Keyword[] = [
    {
        name: "반도체",
        themes: ["반도체장비", "반도체부품"],
        id: "1",
        articles: [
            {
                title: "반도체 기사 1번 제목임",
                content: "반도체 기사 1번 내용임",
            },
            {
                title: "반도체 기사 2번 제목임",
                content: "반도체 기사 2번 내용임",
            },
        ],
    },
    {
        name: "초전도체",
        themes: ["히히히", "헤헤헤"],
        id: "2",
        articles: [
            {
                title: "초전도체 기사 1번 제목임",
                content: "초전도체 기사 1번 내용임",
            },
            {
                title: "초전도체 기사 2번 제목임",
                content: "초전도체 기사 2번 내용임",
            },
            {
                title: "초전도체 기사 3번 제목임",
                content: "초전도체 기사 3번 내용임",
            },
        ],
    },
];

const KeywordDetail = ({ params }: { params: { key: string } }) => {
    // ----------------------------------------
    // 더미데이터 활용하려고 작성함
    // api 호출하면 response된 데이터로 받을거니까 아래 내용은 필요없을것임
    const selectedKeyword: Keyword = {
        name: "",
        themes: [],
        id: "",
        articles: [],
    };

    for (const keyword of dummy) {
        if (keyword.id === params.key) {
            selectedKeyword.name = keyword.name;
            selectedKeyword.themes = keyword.themes;
            selectedKeyword.id = keyword.id;
            selectedKeyword.articles = keyword.articles;
            break;
        }
    }
    // ----------------------------------------

    return (
        <div className="flex items-center  xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
            <div className=" 2xl:text-[30px] sm:text-[15px] text-[30px] font-bold">
                {selectedKeyword.name}
            </div>
            <div>
                <div className="flex items-center text-[20px]">
                    {selectedKeyword.themes.map((themeItem, index) => (
                        <div className="" key={index}>
                            {themeItem}
                        </div>
                    ))}
                </div>
                <div>
                    여기에 주식들 들어갈거임 위에서 선택한 업종에 따른 주식 상위
                    N개
                </div>
                <div className="flex items-center text-[20px]">관련 기사</div>
                <div>
                    {selectedKeyword.articles.map((article, index) => (
                        <div key={index}>
                            <ArticleCard article={article} />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default KeywordDetail;
