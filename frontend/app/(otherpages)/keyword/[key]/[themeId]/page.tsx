import { Stock } from "@/types";
import { StockBar } from "@/components";
import { fetchContentsByTheme } from "@/utils";

const KeywordDetailWithTheme = async ({
  params,
}: {
  params: { key: string; themeId: string };
}) => {
  // ----------------------------------------
  const themeNumber = parseInt(params.themeId, 10);
  const themeResult = await fetchContentsByTheme(themeNumber);
  const stockList = themeResult.stockByThemeIdResponseDtoList;
  const stockIsEmpty =
    !Array.isArray(stockList) || stockList.length < 1 || !stockList;

  // ----------------------------------------
  return (
    <div className="items-center">
      <div className="text-lg font-bold underline decoration-2 underline-offset-4 decoration-blue-800">
        {themeResult.name}
      </div>

      <div className="my-3">
        {!stockIsEmpty ? (
          <div className="max-h-[370px] overflow-y-auto scroll-smooth">
            {stockList.map((stock: Stock, index: number) => (
              // <div key={index}>{stock.name}</div>
              <StockBar key={index} stock={stock} />
            ))}
          </div>
        ) : (
          <div>테마 혹은 이 테마에 해당하는 종목이 존재하지 않습니다.</div>
        )}
      </div>
      <div className="border-[#c7ced7] border-2 border-x-0 border-b-0 my-10" />
    </div>
  );
};

export default KeywordDetailWithTheme;
