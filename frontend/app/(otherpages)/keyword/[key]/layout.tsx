import { Theme, News } from "@/types";
import { ArticleCard } from "@/components";
import Link from "next/link";
import { fetchKeywordDetail } from "@/utils";
import { Metadata, ResolvingMetadata } from "next";

interface Props {
  children: React.ReactNode;
}

export async function generateMetadata(
  {
    params,
  }: {
    params: {
      key: string;
    };
  },
  parent: ResolvingMetadata
): Promise<Metadata> {
  const keyNumber = parseInt(params.key, 10);
  const keywordData = await fetchKeywordDetail(keyNumber);

  return {
    title: `${keywordData.keywordContent} - Hot Stock : 키워드로 찾는 주식`,
    description: `${keywordData.keywordContent} 키워드와 관련된 주식 테마와 기사들을 보여드려요`,
  };
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

  const isEmpty =
    !Array.isArray(keywordData.themeByKeywordIdResponseDtoList) ||
    keywordData.themeByKeywordIdResponseDtoList.length < 1 ||
    !keywordData.themeByKeywordIdResponseDtoList;

  return (
    <>
      <div className="max-w-screen-lg px-8 xl:px-10 my-10 mx-auto">
        <div className="flex xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
          {/* 왼쪽 이름 탭 */}
          <div className="xl:w-1/5">
            <div className="text-[30px] text-gray-700 drop-shadow-[0_5px_5px_rgba(0,0,0,0.4)] font-bold">
              {keywordData.keywordContent}
            </div>
            <div>
              <div className="mt-10">
                <div className="border-[#c7ced7] border-2 border-x-0 my-1 border-b-0 w-1/4" />
                <div className="font-bold text-gray-500">
                  {keywordData.keywordContent}의 테마
                </div>
                {!isEmpty ? (
                  keywordData.themeByKeywordIdResponseDtoList.map(
                    (themeList: Theme, index: number) => (
                      <div
                        className="text-[16px] p-1 my-2 drop-shadow-[1px_1px_1px_rgba(0,0,0,0.2)] bg-[#f0f4f9] rounded-md hover:font-bold"
                        key={index}
                      >
                        <Link
                          href={`/keyword/${keyNumber}/${themeList.themeId}`}
                          replace
                        >
                          {themeList.name}
                        </Link>
                      </div>
                    )
                  )
                ) : (
                  <div>테마가 존재하지 않습니다.</div>
                )}
              </div>
            </div>
          </div>
          {/* 오른쪽 정보 탭  */}
          <div className="items-center xl:w-4/5">
            <div className="keyword_right_box">
              {/* 테마 탭 */}
              <div>
                <div className="items-center">
                  <div>{children}</div>
                </div>
              </div>
              {/* 기사 탭 */}
              <div className="text-lg font-bold underline decoration-2 underline-offset-4 decoration-blue-800">
                {keywordData.keywordContent} 관련 기사
              </div>
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
      </div>
    </>
  );
}
