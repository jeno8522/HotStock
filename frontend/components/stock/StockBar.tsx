import { Stock, StockPPP } from "@/types";
import Link from "next/link";
import Image from "next/image";
import UpIcon from "@/public/images/icons/up.png";
import DownIcon from "@/public/images/icons/down.png";

// interface StockProps {
//   stock: Stock;
// }

interface StockProps {
  stock: Stock;
}

const StockBar = ({ stock }: StockProps) => {
  // const { stockName, currPrice, code, fluctuationRate, diff } = stock;
  const { name, code } = stock;

  return (
    <div>
      <div className="flex justify-between rounded-md my-3 border-2 border-gray-300 p-4">
        <div className="flex px-3">
          <div>{code}</div>
          <div className="font-bold w-52 px-2 te">
            <Link href={`/stock/${code}`}>
              <div className="truncate">{name}</div>
            </Link>
          </div>
        </div>
        <div className="flex text-right">
          {/* <div className="font-bold">{stockId}</div> */}
          <div className="w-32">
            {/* {fluctuationRate > 0 ? (
              <div className="flex justify-end">
                <Image src={UpIcon} alt="up" width={10} className="m-2" />
                <div className="text-red-400 font-bold">{stock.diff}</div>
              </div>
            ) : fluctuationRate < 0 ? (
              <div className="flex justify-end">
                <Image src={DownIcon} alt="down" width={10} className="m-2" />
                <div className="text-blue-400 font-bold">{stock.diff}</div>
              </div>
            ) : (
              <div className="font-bold text-gray-500">-</div>
            )} */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default StockBar;
