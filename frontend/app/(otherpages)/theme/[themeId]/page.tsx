import { fetchThemeDetail } from "@/utils";
import { Stock } from "@/types";
import { StockBar } from "@/components";

const ThemeDetail = async ({ params }: { params: { themeId: string } }) => {
  const themeNumber = parseInt(params.themeId, 10);
  // console.log(themeNumber);
  const stockList = await fetchThemeDetail(themeNumber);

  const stockIsEmpty =
    stockList === null ||
    !Array.isArray(stockList) ||
    stockList.length < 1 ||
    !stockList;

  return (
    <div className="flex justify-center items-center">
      <div className="w-1/3">
        {!stockIsEmpty ? (
          <div>
            {stockList.map((stock: Stock, index: number) => (
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

export default ThemeDetail;
