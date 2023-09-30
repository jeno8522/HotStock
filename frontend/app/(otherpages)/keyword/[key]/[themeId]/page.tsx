import { Stock } from "@/types";
import { StockBar } from "@/components";
import { fetchContentsByTheme } from "@/utils";

const KeywordDetailWithTheme = async ({
  params,
}: {
  params: { key: string; themeId: string };
}) => {
  // ----------------------------------------
  // const keyNumber = parseInt(params.key, 10);
  const themeNumber = parseInt(params.themeId, 10);

  // const keywordDetails = await fetchKeywordDetail(keyNumber);
  const themeResult = await fetchContentsByTheme(themeNumber);

  const stockList = themeResult.stockByThemeIdResponseDtoList;

  // console.log(themeResult);

  const stockIsEmpty =
    !Array.isArray(stockList) || stockList.length < 1 || !stockList;

  // ----------------------------------------
  return (
    <div className="items-center">
      <div>{themeResult.name}</div>
      {/* 테마 탭 */}
      {/* <div className="flex">
        {keywordDetails.themeByKeywordIdResponseDtoList.map(
          (themeItem: Theme, index: number) => (
            <div className="text-[20px] pr-5" key={index}>
              {themeItem.themeId === themeNumber ? (
                <div className="font-bold">
                  <Link href={`/keyword/${keyNumber}/${themeItem.themeId}`}>
                    {themeItem.name}
                  </Link>
                </div>
              ) : (
                <div>
                  <Link href={`/keyword/${keyNumber}/${themeItem.themeId}`}>
                    {themeItem.name}
                  </Link>
                </div>
              )}
            </div>
          )
        )}
      </div> */}
      <div>
        {!stockIsEmpty ? (
          <div className="h-96 overflow-y-auto scroll-smooth">
            {stockList.map((stock: Stock, index: number) => (
              // <div key={index}>{stock.name}</div>
              <StockBar key={index} stock={stock} />
            ))}
          </div>
        ) : (
          <div>테마 혹은 이 테마에 해당하는 종목이 존재하지 않습니다.</div>
        )}
      </div>
    </div>
  );
};

export default KeywordDetailWithTheme;
