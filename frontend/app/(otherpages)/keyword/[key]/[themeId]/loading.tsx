import StockBarLoading from "@/components/skeleton/StockBarLoading";

export default function Loading() {
  const len = 5;

  return (
    <div>
      <h2>테마와 종목들을 조회중입니다</h2>
      {Array.from({ length: len }).map((_, index) => (
        <StockBarLoading key={index} />
      ))}
    </div>
  );
}
