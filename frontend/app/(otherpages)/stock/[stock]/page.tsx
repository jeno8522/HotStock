import { fetchStockDetail, getNewsByStock, fetchStockTheme } from "@/utils";
import { Stock } from "@/types";
import { NewsCardForStocks, Mindmap } from "@/components";
import StockInfo from "@/components/stock/StockInfo";

const StockDetail = async ({ params }: { params: { stock: string } }) => {
  const stockInfo = await fetchStockDetail(`${params.stock}`);
  const curStock = stockInfo[0];
  const stockThemelist = await fetchStockTheme(curStock.code);
  // console.log(stockThemelist);

  // const { code } = curStock;
  // console.log(stockInfo[0]);
  const newsData = await getNewsByStock(`${stockInfo[0].name}`, 5);
  // console.log(newData);

  // 더미데이터 --------------------------
  // const stockInfo: Stock = {
  //   stockName: "1번종목",
  //   code: `${params.stock}`,
  //   openPrice: 0,
  //   currPrice: 5700,
  //   fluctuationRate: -0.71,
  //   diff: 200,
  //   tradingVolume: 14770506,
  //   highPrice: 5830,
  //   lowPrice: 5650,
  //   newslist: [
  //     {
  //       id: 123,
  //       title: "이건기사제목이야",
  //       date: "2023-09-19",
  //       content: "이건기사내용이야",
  //       mediaCompanyNum: 28,
  //       link: "https://www.naver.com/",
  //     },
  //   ],
  // };
  // ------------------------------------

  const newsIsEmpty =
    !Array.isArray(newsData) || newsData.length < 1 || !newsData;

  return (
    <div className="max-w-screen-lg px-8 xl:px-10 mt-10 mx-auto">
      <div className="flex xl:flex-row flex-col gap-5 relative z-0 max-w-[1440px] mx-auto">
        {/* 왼쪽 이름 탭 */}
        <div className="xl:w-1/5">
          <a
            target="_blank"
            href={`https://finance.daum.net/quotes/A${curStock.code}#home`}
          >
            <div className="text-[30px] text-gray-700 drop-shadow-[0_5px_5px_rgba(0,0,0,0.4)] font-bold">
              {curStock.name}
            </div>
          </a>
          <StockInfo stock={curStock} />
        </div>
        {/* 오른쪽 정보 탭  */}
        <div className="items-center xl:w-4/5">
          {/* 테마 탭 */}
          <div>
            <Mindmap stock={curStock} themes={stockThemelist} />
          </div>
          {/* 기사 탭 */}
          <div className="flex items-center text-[20px]">관련 기사</div>
          <div>
            {!newsIsEmpty ? (
              newsData.map((news, index) => (
                <div key={index}>
                  <NewsCardForStocks news={news} />
                </div>
              ))
            ) : (
              <div> 해당하는 기사가 없습니다</div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default StockDetail;
