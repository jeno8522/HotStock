import { KeywordProps } from "../../types/index";
import { RankingCard } from "..";
import { fetchKeywords } from "@/utils";

const RankingList = async () => {
    const allKeywords = await fetchKeywords();
    const isEmpty =
        !Array.isArray(allKeywords) || allKeywords.length < 1 || !allKeywords;

    return (
        <div>
            {!isEmpty ? (
                <div>
                    {allKeywords?.map((keyword, index) => (
                        <RankingCard key={index} keyword={keyword} />
                    ))}
                </div>
            ) : (
                <div>없어안돼돌아가</div>
            )}
        </div>
    );
};
export default RankingList;
