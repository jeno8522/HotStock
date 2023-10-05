import { KeywordProps } from "@/types/index";
import { RankingCard } from "..";
import { fetchKeywords } from "@/utils";

interface keywordPropList {
  allKeywords: KeywordProps[];
}

const RankingList = async ({ allKeywords }: keywordPropList) => {
  // const allKeywords = await fetchKeywords();
  // console.log(allKeywords);
  const isEmpty =
    !Array.isArray(allKeywords) || allKeywords.length < 1 || !allKeywords;

  const eachKeywords: KeywordProps[][] = [];
  for (let i = 0; i < 25; i += 5) {
    eachKeywords.push(allKeywords.slice(i, i + 5));
  }
  // console.log(eachKeywords);
  return (
    <div className="relative bg-white px-16 pt-3 pb-10 h-fit ">
      {!isEmpty ? (
        <div className="flex justify-between mx-2 overflow-hidden ">
          {eachKeywords?.map((group, eIndex) => (
            <div key={eIndex} className="flex-1">
              {group?.map((keyword, gIndex) => (
                <RankingCard
                  key={gIndex}
                  index={eIndex * 5 + gIndex + 1}
                  keyword={keyword}
                />
              ))}
            </div>
          ))}
        </div>
      ) : (
        <div>현재 핫한 키워드가 존재하지 않아요</div>
      )}
    </div>
  );
};
export default RankingList;
