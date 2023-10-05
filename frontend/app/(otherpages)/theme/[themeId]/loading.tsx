import { StockBarLoading } from "@/components";

const Loading = () => {
  return (
    <div className="flex flex-col justify-between h-fit lg:flex-row">
      <div className="lg:w-1/3 bg-[#24364d] text-white">
        <div className="mt-40 mx-10">
          <h1 className="text-3xl font-bold mb-2">로딩중...</h1>
          <h2 className="text-lg">잠시만 기다려주세요</h2>
        </div>
      </div>
      <div className="lg:w-2/3">
        <div className="flex flex-col justify-center p-10">
          <div className="xl:mx-32 lg:mx-10">
            {Array.from({ length: 10 }).map((_, index) => (
              <StockBarLoading key={index} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Loading;
