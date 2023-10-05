"use client";

import { Stock } from "@/types";
import { StockBar } from "@/components";
import { useState } from "react";
import { ThemeBar } from "@/components";
import { Pagination } from "@nextui-org/react";

interface stockList {
  stocks: Stock[];
}

const ThemeStockList = ({ stocks }: stockList) => {
  const [page, setPage] = useState(1);
  const offset = (page - 1) * 10;
  const numPages = Math.ceil(stocks.length / 10);
  const handlePageClick = (newPage: number) => {
    if (newPage >= 1 && newPage <= numPages) {
      setPage(newPage);
    }
  };
  return (
    <div className="flex flex-col p-10 min-h-screen">
      <div className="xl:mx-32 lg:mx-10">
        {stocks.slice(offset, offset + 10).map((stock, index) => (
          <StockBar key={index} stock={stock} />
        ))}
      </div>
      <div className="flex justify-center">
        <Pagination
          showControls
          total={numPages}
          initialPage={1}
          color="default"
          onChange={(page) => handlePageClick(page)}
        />
      </div>
    </div>
  );
};

export default ThemeStockList;
