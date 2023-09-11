import KeywordDetail from "../keyword/[key]/page";
import { Keyword } from "@/types";
import Link from "next/link";
import { RankingList } from "@/components";

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

const Ranking = () => {
    return (
        <div className="max-w-screen-xl px-8 xl:px-16 mx-auto">
            <div className="flex flex-col justify-center items-start row-start-2 sm:row-start-1">
                <h2>키워드 랭킹 페이지</h2>
                <p>지금 가장 핫한 키워드들을 보여드려요</p>
            </div>

            <div>
                {dummy.map((keyword, index) => (
                    <div key={index}>
                        <Link href={`/keyword/${keyword.id}`}>
                            {keyword.name}
                        </Link>
                    </div>
                ))}
            </div>
            {/* <RankingList/> */}
        </div>
    );
};

export default Ranking;
