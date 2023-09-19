import { Stock } from "@/types";
import UpIcon from "@/public/images/icons/up.png";
import DownIcon from "@/public/images/icons/down.png";
import Image from "next/image";

interface StockProps {
  stock: Stock;
}
const StockInfo = ({ stock }: StockProps) => {
  return (
    <div>
      <div className="font-bold text-gray-600 mb-3">{stock.code}</div>
      <div>
        {stock.fluctuationRate > 0 ? (
          <div className="text-red-500 flex font-bold items-end">
            <div className="font-bold text-3xl">{stock.currPrice}</div>
            <Image
              src={UpIcon}
              alt="up"
              width={10}
              className="ml-2 mr-1 my-2"
            />
            <div>{stock.diff}</div>
          </div>
        ) : stock.fluctuationRate < 0 ? (
          <div className="text-blue-500 flex font-bold items-end">
            <div className="font-bold text-3xl">{stock.currPrice}</div>
            <Image
              src={DownIcon}
              alt="down"
              width={10}
              className="ml-2 mr-1 my-2"
            />
            <div>{stock.diff}</div>
          </div>
        ) : (
          <div>{stock.currPrice}</div>
        )}
      </div>
      <div className="my-3">
        <div className="flex justify-between">
          <div>고가</div>
          <div className="text-rose-600 font-bold">{stock.highPrice}</div>
        </div>
        <div className="flex justify-between">
          <div>저가</div>
          <div className="text-blue-600 font-bold">{stock.lowPrice}</div>
        </div>
        <div className="flex justify-between">
          <div>거래량</div>
          <div className="font-bold">{stock.tradingVolume}</div>
        </div>
      </div>
    </div>
  );
};

export default StockInfo;
