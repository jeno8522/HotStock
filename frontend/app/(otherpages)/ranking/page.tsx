import KeywordDetail from "../keyword/[key]/page";
import { Keyword } from "@/types";
import Link from "next/link";
import { RankingList } from "@/components";

const Ranking = () => {
    return (
        <div className="max-w-screen-xl px-8 xl:px-16 mx-auto">
            <div className="flex flex-col justify-center items-start row-start-2 sm:row-start-1">
                <h2
                    className="
                    font-extrabold
                    text-4xl
                    leading-10
                    text-gray-700"
                >
                    키워드 랭킹 페이지
                </h2>
                <p>지금 가장 핫한 키워드들을 보여드려요</p>
            </div>
            <RankingList />
        </div>
    );
};

export default Ranking;
