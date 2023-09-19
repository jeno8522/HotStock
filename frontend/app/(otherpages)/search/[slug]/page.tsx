import { searchStocks } from "@/utils";
import { StockBar } from "@/components";

// 검색결과 페이지
// 검색 내용에 따라 해당하는 주식 종목들을 StockBar 형태로 출력
const SearchResult = async ({ params }: { params: { slug: string } }) => {
  // const searchResult = await searchStocks(params.slug);
  const isEmpty =
    !Array.isArray(searchResult) || searchResult.length < 1 || !searchResult;

  return (
    <div className="max-w-screen-xl px-14 xl:px-24 mx-auto">
      <div className="my-12">
        <div className="font-bold">{params.slug} 검색 결과</div>
        <div>
          {!isEmpty ? (
            <div>
              {searchResult?.map((stock, index) => (
                // <div key={index}>{stock.name}</div>
                <StockBar key={index} stock={stock} />
              ))}
            </div>
          ) : (
            <div>해당하는 종목이 존재하지 않습니다.</div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SearchResult;

const searchResult = [
  {
    stockName: "1번종목",
    code: "090909",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "2번종목",
    code: "808080",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "3번종목",
    code: "989898",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "4번종목",
    code: "579598",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
  {
    stockName: "5번종목",
    code: "367536",
    openPrice: 0,
    currPrice: 0,
    fluctuationRate: 0,
    diff: 0,
    tradingVolume: 0,
    highPrice: 0,
    lowPrice: 0,
    newslist: [],
  },
];
