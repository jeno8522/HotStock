"use client";
import { useEffect, useState } from "react"; // 추가
import { Stock } from "@/types";
import { ThemeStockList, StockBar } from "@/components";
import { fetchContentsByTheme } from "@/utils";

interface ThemeProp {
  themeNumber: number;
}

interface KeywordByThemeId {
  keywordId: number;
  content: string;
}

interface KeywordByThemeIdResponseDtoList {
  keywordByThemeId: KeywordByThemeId[];
}

interface StockByThemeId {
  code: string;
  name: string;
  reason: string;
}

interface StockByThemeIdResponseDtoList {
  Stock: Stock[];
}

interface ThemeResultProp {
  name: string;
  stockByThemeIdResponseDtoList: Stock[];
  keywordByThemeIdResponseDtoList: KeywordByThemeIdResponseDtoList;
}

const StockComp = ({ themeNumber }: ThemeProp) => {
  const [themeResult, setThemeResult] = useState<ThemeResultProp>();

  const fetchThemeResult = async (themeNumber: number) => {
    try {
      const res = await fetch(
        `https://hot-stock.shop/api/theme/${themeNumber}`,
        {
          cache: "no-store",
        }
      );
      const data = await res.json();
      return data;
    } catch (error) {
      console.error(error);
      return null;
    }
  };

  useEffect(() => {
    const loadData = async () => {
      const result = await fetchThemeResult(themeNumber);
      if (result) {
        setThemeResult(result);
      }
    };

    loadData();
  }, [themeNumber]);

  return (
    <div className="flex flex-col justify-between h-fit lg:flex-row">
      {themeResult ? (
        <div className="lg:w-1/3 bg-[#24364d] text-white">
          <div className="mt-40 mx-10">
            <h1 className="text-3xl font-bold mb-2">{themeResult.name}</h1>
            <h2 className="text-lg">
              {themeResult.name} 테마의 종목들을 보여드려요
            </h2>
          </div>
        </div>
      ) : (
        <div>Loading...</div>
      )}

      <div className="lg:w-2/3">
        {themeResult && themeResult.stockByThemeIdResponseDtoList ? (
          <div>
            <ThemeStockList
              stocks={themeResult.stockByThemeIdResponseDtoList}
            />
          </div>
        ) : (
          <div>테마 혹은 이 테마에 해당하는 종목이 존재하지 않습니다.</div>
        )}
      </div>
    </div>
  );
};

export default StockComp;
