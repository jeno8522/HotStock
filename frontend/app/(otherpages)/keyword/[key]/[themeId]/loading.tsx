import StockBarLoading from "@/components/skeleton/StockBarLoading";

export default function Loading() {
  const len = 5;

  return (
    <div>
      <h2>테마와 종목들을 조회중입니다</h2>
      <div className="my-3">
        {Array.from({ length: len }).map((_, index) => (
          <StockBarLoading key={index} />
        ))}
      </div>
      <div className="border-[#f0f4f9] border-4 border-x-0 border-b-0 my-10" />
    </div>
  );
}
