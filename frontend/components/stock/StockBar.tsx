// import { Stock } from "@/types";

// interface StockProps {
//   stock: Stock;
// }

// const StockBar = ({ stock } : StockProps) = > {
// const { title, price, code } = stock;

const StockBar = () => {
  // dummy --------------------------------
  const title: string = "종목이름이얌";
  const price: number = 12312;
  const code: string = "142434";
  // --------------------------------------
  return (
    <div>
      <div className="flex justify-between rounded-md my-3 border-2 border-gray-300 p-4">
        <div className="flex">
          <div className="font-bold  pr-2">{title}</div> <div>{code}</div>
        </div>
        <div>{price}</div>
      </div>
    </div>
  );
};

export default StockBar;
