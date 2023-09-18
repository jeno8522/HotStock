import { KeywordProps } from "../../types/index";
import { RankingCard } from "..";
import { fetchKeywords } from "@/utils";

const dummylist: KeywordProps[] = [
  { id: 1, text: "개구리", value: 123 },
  { id: 2, text: "강아지", value: 123 },
  { id: 3123, text: "멍멍이", value: 123 },
  { id: 453, text: "오므라이스", value: 123 },
  { id: 52352, text: "배불러", value: 123 },
  { id: 246, text: "하이디라오", value: 123 },
  { id: 724362, text: "훠궈", value: 123 },
  { id: 658, text: "냠냠", value: 123 },
  { id: 954, text: "히히", value: 123 },
  { id: 10, text: "히히", value: 123 },
  { id: 11, text: "히히", value: 123 },
  { id: 12, text: "히히", value: 123 },
  { id: 13, text: "히히", value: 123 },
  { id: 14, text: "히히", value: 123 },
  { id: 15, text: "히히", value: 123 },
  { id: 16, text: "히히", value: 123 },
  { id: 17, text: "히히", value: 123 },
  { id: 18, text: "히히", value: 123 },
  { id: 19, text: "히히", value: 123 },
  { id: 20, text: "히히", value: 123 },
  { id: 21, text: "히히", value: 123 },
  { id: 22, text: "히히", value: 123 },
  { id: 23, text: "히히", value: 123 },
  { id: 24, text: "히히", value: 123 },
  { id: 25, text: "히히", value: 123 },
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
