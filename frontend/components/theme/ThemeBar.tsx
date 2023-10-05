import React from "react";
import Link from "next/link";

interface ThemeProps {
  themeId: number;
  name: string;
}

const ThemeBar = ({ themeId, name }: ThemeProps) => {
  return (
    <div className="flex items-center justify-between rounded-md border-2 border-gray-300 p-4 sm:my-3 lg:my-0 lg:w-full">
      <div className="flex justify-between">
        <div className="pr-2">No.{themeId}</div>
        <div className="font-bold  px-2 truncate">{name}</div>
      </div>
      <Link
        href={{
          pathname: `/theme/${themeId}`,
          query: { name: `${name}` },
        }}
      >
        <button className=" bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-md shadow-md transition duration-300 ease-in-out">
          상세보기
        </button>
      </Link>
    </div>
  );
};

export default ThemeBar;
