import { fetchStockDetail } from "@/utils";
import { StockBar } from "@/components";

// 검색결과 페이지
// 검색 내용에 따라 해당하는 주식 종목들을 StockBar 형태로 출력
const SearchResult = async ({ params }: { params: { slug: string } }) => {
  const searchResult = await fetchStockDetail(params.slug);
  const searchedText = decodeURIComponent(params.slug);
  // console.log(searchResult);
  const isEmpty =
    !Array.isArray(searchResult) || searchResult.length < 1 || !searchResult;

  return (
    <div className="max-w-screen-lg px-14 xl:px-24 mx-auto">
      <div className="my-12">
        <div className="font-bold">{searchedText} 검색 결과</div>
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
