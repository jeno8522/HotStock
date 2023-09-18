import { searchStocks } from "@/utils";
import { StockBar } from "@/components";

const searchResult = [
  { name: "1번 주식" },
  { name: "2번 주식" },
  { name: "3번 주식" },
];

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
                <StockBar key={index} />
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
