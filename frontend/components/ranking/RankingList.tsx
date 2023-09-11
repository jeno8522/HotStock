import { KeywordProps } from "../../types/index";
import { RankingCard } from "..";
import { fetchKeywords } from "@/utils";

const dummylist: KeywordProps[] = [
    { id: "1", name: "히히" },
    { id: "2", name: "히히" },
    { id: "3", name: "히히" },
    { id: "4", name: "히히" },
    { id: "5", name: "히히" },
    { id: "6", name: "히히" },
    { id: "7", name: "히히" },
    { id: "8", name: "히히" },
    { id: "9", name: "히히" },
    { id: "10", name: "히히" },
    { id: "11", name: "히히" },
    { id: "12", name: "히히" },
    { id: "13", name: "히히" },
    { id: "14", name: "히히" },
    { id: "15", name: "히히" },
    { id: "16", name: "히히" },
    { id: "17", name: "히히" },
    { id: "18", name: "히히" },
    { id: "19", name: "히히" },
    { id: "20", name: "히히" },
    { id: "21", name: "히히" },
    { id: "22", name: "히히" },
    { id: "23", name: "히히" },
    { id: "24", name: "히히" },
    { id: "25", name: "히히" },
];

const RankingList = async () => {
    // const allKeywords = await fetchKeywords();
    const allKeywords = dummylist;
    const isEmpty =
        !Array.isArray(allKeywords) || allKeywords.length < 1 || !allKeywords;

    const eachKeywords = [];
    for (let i = 0; i < allKeywords.length; i += 5) {
        eachKeywords.push(allKeywords.slice(i, i + 5));
    }
    // console.log(eachKeywords);
    return (
        <div className="m-10">
            {!isEmpty ? (
                <div className="flex justify-between mx-2">
                    {eachKeywords?.map((group, index) => (
                        <div key={index}>
                            {group?.map((keyword, index) => (
                                <RankingCard key={index} keyword={keyword} />
                            ))}
                        </div>
                    ))}
                </div>
            ) : (
                <div>없어안돼돌아가</div>
            )}
        </div>
    );
};
export default RankingList;
