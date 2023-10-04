import { fetchThemeDetail, fetchContentsByTheme } from "@/utils";
import { Stock } from "@/types";
import { StockBar, ThemeStockList } from "@/components";

const ThemeDetail = async ({ params }: { params: { themeId: string } }) => {
  const themeNumber = parseInt(params.themeId, 10);
  // console.log(themeNumber);
  const stockList = await fetchThemeDetail(themeNumber);
  const themeName = await fetchContentsByTheme(themeNumber);
  // console.log(themeName.name);
  const stockIsEmpty =
    stockList === null ||
    !Array.isArray(stockList) ||
    stockList.length < 1 ||
    !stockList;

  return (
    <div className="flex flex-col justify-between h-screen lg:flex-row">
      <div className="lg:w-1/3 bg-[#24364d] text-white">
        <div className="mt-40 mx-10">
          <h1 className="text-3xl font-bold mb-2">{themeName.name}</h1>
          <h2 className="text-lg">
            {themeName.name} 테마의 종목들을 보여드려요
          </h2>
        </div>
      </div>
      <div className="lg:w-2/3">
        {!stockIsEmpty ? (
          <div>
            <ThemeStockList stocks={stockList} />
          </div>
        ) : (
          <div>테마 혹은 이 테마에 해당하는 종목이 존재하지 않습니다.</div>
        )}
      </div>
    </div>
  );
};

export default ThemeDetail;
