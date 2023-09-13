import { KeywordProps } from "../../types/index";
import Link from "next/link";
import localfont from "next/font/local";
import { fetchKeywordDetail } from "@/utils";

interface getKeywordsProps {
  keyword: KeywordProps;
  index: number;
}

const cafe24SsurroundAir = localfont({
  src: [
    {
      path: "../../public/fonts/cafe24SsurroundAir.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

const RankingCard = async ({ keyword, index }: getKeywordsProps) => {
  // const getKeywordDetail = await fetchKeywordDetail(keyword.id);
  // const firstThemeIdOfKeyword = getKeywordDetail.theme[0].themeId;
  const firstThemeIdOfKeyword = 12;
  const { text } = keyword;
  console.log(keyword);
  return (
    <div className="mx-3 my-5">
      {/* <Link href={`/keyword/${keyword.id}`} className="flex"> */}
      <Link
        href={`/keyword/${keyword.id}/${firstThemeIdOfKeyword}`}
        className="flex"
      >
        <div className="text-black-500 m-2">{index}</div>
        <div
          className={`text-gray-700 m-2 truncate ${cafe24SsurroundAir.className}`}
        >
          {text}
        </div>
      </Link>
    </div>
  );
};

export default RankingCard;
