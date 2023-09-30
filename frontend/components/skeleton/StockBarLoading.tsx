"use client";
import React from "react";
import { Skeleton } from "@nextui-org/skeleton";

const StockBarLoading = () => {
  return (
    <div className="flex justify-between rounded-md my-3 border-2 border-gray-300 p-4">
      <div className="flex px-3">
        <Skeleton className="h-6 w-16 rounded-lg" />
        <div className="w-52 px-2">
          <Skeleton className="h-6 w-52 rounded-lg" />
        </div>
      </div>
    </div>
  );
};

export default StockBarLoading;
