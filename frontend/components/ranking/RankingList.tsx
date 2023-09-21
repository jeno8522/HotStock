import { KeywordProps } from "../../types/index";
import { RankingCard } from "..";
import { fetchKeywords } from "@/utils";

const RankingList = async () => {
  const allKeywords = await fetchKeywords();
  // console.log(allKeywords);
  const isEmpty =
    !Array.isArray(allKeywords) || allKeywords.length < 1 || !allKeywords;

  const eachKeywords: KeywordProps[][] = [];
  for (let i = 0; i < allKeywords.length; i += 5) {
    eachKeywords.push(allKeywords.slice(i, i + 5));
  }
  // console.log(eachKeywords);
  return (
    <div className="relative bg-white px-6 pt-3 pb-3 shadow-xl h-[30rem] ring-1 rounded-2xl ring-gray-900/5">
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
        <div>없어안돼돌아가</div>
      )}
    </div>
  );
};
export default RankingList;
