import { Theme, News } from "@/types";
import { ArticleCard } from "@/components";
import Link from "next/link";
import { fetchKeywordDetail } from "@/utils";

interface Props {
  children: React.ReactNode;
}

export default async function KeywordDetailLayout({
  children,
  params,
}: {
  children: React.ReactNode;
  params: {
    key: string;
  };
}) {
  const keyNumber = parseInt(params.key, 10);
  const keywordData = await fetchKeywordDetail(keyNumber);

  return (
    <>
      <div className="max-w-screen-lg px-8 xl:px-10 mt-10 mx-auto">
        <div className="flex xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
          {/* 왼쪽 이름 탭 */}
          <div className="text-[30px] text-gray-700 drop-shadow-[0_5px_5px_rgba(0,0,0,0.4)] xl:w-1/5 font-bold">
            {keywordData.keywordContent}
          </div>
          {/* 오른쪽 정보 탭  */}
          <div className="items-center xl:w-4/5">
            {/* 테마 탭 */}
            <div>
              <div className="items-center">
                {/* 테마 탭 */}
                <div className="flex">
                  {keywordData.themeByKeywordIdResponseDtoList.map(
                    (themeList: Theme, index: number) => (
                      // <div className="text-[20px] pr-5" key={index}>
                      //   {themeList.themeId === themeNumber ? (
                      //     <div className="font-bold">
                      //       <Link
                      //         href={`/keyword/${keyNumber}/${themeList.themeId}`}
                      //       >
                      //         {themeList.name}
                      //       </Link>
                      //     </div>
                      //   ) : (
                      //     <div>
                      //       <Link
                      //         href={`/keyword/${keyNumber}/${themeList.themeId}`}
                      //       >
                      //         {themeList.name}
                      //       </Link>
                      //     </div>
                      //   )}
                      // </div>
                      <div className="text-[20px] pr-5" key={index}>
                        <Link
                          href={`/keyword/${keyNumber}/${themeList.themeId}`}
                          replace
                        >
                          {themeList.name}
                        </Link>
                      </div>
                    )
                  )}
                </div>
                <div>{children}</div>
              </div>
            </div>
            {/* 기사 탭 */}
            <div className="flex items-center text-[20px]">관련 기사</div>
            <div>
              {keywordData.newsByKeywordIdResponseDtoList.map(
                (news: News, index: number) => (
                  <div key={index}>
                    <ArticleCard news={news} />
                  </div>
                )
              )}
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
