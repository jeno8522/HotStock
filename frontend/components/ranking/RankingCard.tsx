import { KeywordProps } from "../../types/index";
import Link from "next/link";
import localfont from "next/font/local";
import { fetchKeywordDetail } from "@/utils";

interface getKeywordsProps {
  keyword: KeywordProps;
  index: number;
}

const RankingCard = async ({ keyword, index }: getKeywordsProps) => {
  const getKeywordDetail = await fetchKeywordDetail(keyword.id);

  const firstThemeIdOfKeyword =
    getKeywordDetail.themeByKeywordIdResponseDtoList.length > 0
      ? getKeywordDetail.themeByKeywordIdResponseDtoList[0].themeId
      : 0;
  const firstThemeId: string = firstThemeIdOfKeyword.toString();
  const { text } = keyword;
  return (
    <div className="mx-3 my-10 text-lg">
      <Link href={`/keyword/${keyword.id}/${firstThemeId}`} className="flex">
        <div className="text-black-500 font-bold text-center w-5 m-2">
          {index}
        </div>
        <div className="text-gray-700 m-2 truncate">{text}</div>
      </Link>
    </div>
  );
};

export default RankingCard;
