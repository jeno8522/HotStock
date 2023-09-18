const StockDetail = ({ params }: { params: { stock: string } }) => {
  return (
    <div className="max-w-screen-xl px-14 xl:px-24 mx-auto">
      <div className="my-12">
        <div>종목 상세 페이지</div>
        <div>{params.stock}</div>
      </div>
    </div>
  );
};

export default StockDetail;
