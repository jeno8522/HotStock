import { Stock } from "@/types";
import Link from "next/link";

interface StockProps {
  stock: Stock;
}

const StockBar = ({ stock }: StockProps) => {
  const { stockName, currPrice, code } = stock;

  // const StockBar = () => {
  // dummy --------------------------------
  // const stockName: string = "종목이름이얌";
  // const currPrice: number = 12312;
  // const code: string = "142434";
  // --------------------------------------
  return (
    <div>
      <div className="flex justify-between rounded-md my-3 border-2 border-gray-300 p-4">
        <div className="flex">
          <Link href={`/stock/${code}`}>
            <div className="font-bold  pr-2">{stockName}</div>
          </Link>
          <div>{code}</div>
        </div>
        <div>{currPrice}</div>
      </div>
    </div>
  );
};

export default StockBar;
